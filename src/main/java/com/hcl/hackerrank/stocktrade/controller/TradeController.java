package com.hcl.hackerrank.stocktrade.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hcl.hackerrank.stocktrade.exception.CustomException;
import com.hcl.hackerrank.stocktrade.model.Event;
import com.hcl.hackerrank.stocktrade.model.Trade;
import com.hcl.hackerrank.stocktrade.repository.EventRepository;
import com.hcl.hackerrank.stocktrade.service.api.TradeService;

/**
 * Controller class responsible to expose REST APIs 
 * @author Anuj Saraswat
 */
@RestController
@RequestMapping("/")
public class TradeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TradeController.class);
    
    @Autowired
    TradeService TradeService;
    
    @Autowired
    EventRepository eventRepository;
    
    
    @RequestMapping(value = "/erase", method = RequestMethod.DELETE)
    public void deleteCustomer() throws CustomException {
        TradeService.deleteAllTrade();
    }
    
    @RequestMapping(value = "/trades", method = RequestMethod.POST)
    public ResponseEntity<Void> createCustomer(@RequestBody Trade trade) {
    LOGGER.debug("Processing create trade request ");
    
    ResponseEntity<Void> response = null;
    
    Trade resultTrade = TradeService.addTrade(trade);
    
    final URI tradeURI = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/trade/{tradeId}").build()
            .expand(trade.getUser()).toUri();
        
        final HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(tradeURI);
        response = new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);

        LOGGER.debug("Processing done by creating trade : {}", resultTrade);

    
    return response;
    
    }
    
    @RequestMapping(value = "/trades", method = RequestMethod.GET)
    public List<Trade> getAllTrades() {
        List<Trade> trades = TradeService.getAllTrades();
        return trades;
    }
    
    @RequestMapping(value = "/trades/users/{userId}", method = RequestMethod.GET)
    public List<Trade> getAllTradessByUserId(@PathVariable long userId) {
        List<Trade> trades = TradeService.getAllTradesByUserId(userId);
        return trades;
    }

    @RequestMapping(value = "/trades/stocks/{stockSymbol}", method = RequestMethod.GET)
    public List<Trade> getAllTradessByStockSymble(@PathVariable String stockSymbol) {
        List<Trade> trades = TradeService.getAllTradesByStockSymbol(stockSymbol);
        return trades;
    }
    
    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public void createEvent(@RequestBody Event event) {
        ResponseEntity<Void> response = null;        
        eventRepository.save(event);
        
//        final HttpHeaders httpHeaders = new HttpHeaders();
//         response = new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
//        return response;
    }

 @RequestMapping(value = "/eraseallevent", method = RequestMethod.DELETE)
    public void deleteAllEvent() {
        eventRepository.deleteAll();
    }
    
    
    @RequestMapping(value = "/getallevents", method = RequestMethod.GET)
    public List<Event> getAllEvents() {
        List<Event> events = (List<Event>)eventRepository.findAll();
        
        return events;
    }
        
    @RequestMapping(value = "/events/repos/{repoID}", method = RequestMethod.GET)
    public List<Event> getAllEventsByUserId(@PathVariable long repoID) {
        List<Event> result = new ArrayList<Event>();
        
        List<Event> events = (List<Event>)eventRepository.findAll();
        
        System.out.println("-------------------------------");
        System.out.println(  events.size());
        
        result = events.stream().filter(e->(e.getId() == repoID)).collect(Collectors.toList());
        
//        Iterator<Event> itr = events.iterator();
//        
//        while(itr.hasNext()) {
//            Event event = itr.next();
//                if(event.getRepo().getId() == repoID) {
//                    result.add(event);
//                }
//            }
//        Collections.sort(result);
        return result;
    }
    
    @RequestMapping(value = "/events/actors/{actorID}", method = RequestMethod.GET)
    public List<Event> getAllEventsByActorId(@PathVariable long actorID) {
        List<Event> result = new ArrayList<Event>();
        
        List<Event> events = (List<Event>)eventRepository.findAll();

        result = events.stream().filter(e->e.getActor().getId() == actorID).collect(Collectors.toList());
        
//        Iterator<Event> itr = events.iterator();
//        
//        while(itr.hasNext()) {
//            Event event = itr.next();
//                if(event.getActor().getId() == actorID) {
//                    result.add(event);
//                }
//            }
        
        return result;
    }
    
    @RequestMapping(value = "/events/repos/{repoID}/actor/{actorID}", method = RequestMethod.GET)
    public List<Event> getAllEventsByRepoIdAndActorId(@PathVariable long repoID, @PathVariable long actorID) {
    	List<Event> result = new ArrayList<Event>();
    	
    	 List<Event> events = (List<Event>)eventRepository.findAll();
    	 
    	 result = events.stream().filter(e->
    	 e.getActor().getId() == actorID && e.getRepo().getId() == repoID).collect(Collectors.toList());
    	 
//    	 Iterator<Event> itr = events.iterator();
//    	 
//    	 while(itr.hasNext()) {
//             Event event = itr.next();
//             if(event.getActor().getId() == actorID 
//            		 &&
//            		 event.getRepo().getId().intValue() == repoID) {
//                 result.add(event);
//             }
//    	 }
    	 
    	return result;
    }
}
