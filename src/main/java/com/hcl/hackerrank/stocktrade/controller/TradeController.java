package com.hcl.hackerrank.stocktrade.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
    public List<Trade> getAllTradessByUserId(@PathVariable String userId) {
        List<Trade> trades = TradeService.getAllTradesByUserId(Integer.parseInt(userId));
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
    public List<Event> getAllEventsByUserId(@PathVariable String repoID) {
        List<Event> result = new ArrayList<Event>();
        
        List<Event> events = (List<Event>)eventRepository.findAll();
        
        System.out.println("-------------------------------");
        System.out.println(  events.size());
        
        Iterator<Event> itr = events.iterator();
        
        while(itr.hasNext()) {
            Event event = itr.next();
                if(event.getRepo().getId() == Long.valueOf(repoID)) {
                    result.add(event);
                }
            }
//        Collections.sort(result);
        return result;
    }
    
    @RequestMapping(value = "/events/actors/{actorID}", method = RequestMethod.GET)
    public List<Event> getAllEventsByActorId(@PathVariable String actorID) {
        List<Event> result = new ArrayList<Event>();
        
        List<Event> events = (List<Event>)eventRepository.findAll();
        
        Iterator<Event> itr = events.iterator();
        
        while(itr.hasNext()) {
            Event event = itr.next();
                if(event.getActor().getId() == Long.valueOf(actorID)) {
                    result.add(event);
                }
            }
        
        return result;
    }
    
    @RequestMapping(value = "/events/repos/{repoID}/actor/{actorID}", method = RequestMethod.GET)
    public List<Event> getAllEventsByRepoIdAndActorId(@PathVariable String repoID, @PathVariable String actorID) {
    	List<Event> result = new ArrayList<Event>();
    	
    	 List<Event> events = (List<Event>)eventRepository.findAll();
    	 Iterator<Event> itr = events.iterator();
    	 
    	 while(itr.hasNext()) {
             Event event = itr.next();
             if(event.getActor().getId() == Long.valueOf(actorID) 
            		 &&
            		 event.getRepo().getId() == Long.valueOf(repoID)) {
                 result.add(event);
             }
    	 }
    	 
    	return result;
    }
}
