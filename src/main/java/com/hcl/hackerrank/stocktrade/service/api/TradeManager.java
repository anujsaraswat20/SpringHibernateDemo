package com.hcl.hackerrank.stocktrade.service.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hackerrank.stocktrade.model.Trade;
import com.hcl.hackerrank.stocktrade.repository.TradeRepository;

/*
 * Class responsible to provide all implementation of trade service
 */
@Service
public class TradeManager implements TradeService {
    
	@Autowired
	TradeRepository TradeRepository;
	
      /**
     *  {@inheritDoc}
     */
    @Override
    public void deleteAllTrade() {
        
    }
    
      /**
     *  {@inheritDoc}
     */
    @Override
    public Trade addTrade(Trade trades) {
    	TradeRepository.save(trades);
		return null;
        
    }
    
    public List<Trade> getAllTradesByUserId(Integer userId) {
    	List<Trade> result = new ArrayList<Trade>();
    	
        List<Trade> trades = (List<Trade>) TradeRepository.findAll();
        
        Iterator<Trade> itr = trades.iterator();
        while(itr.hasNext()) {
        	Trade trade = itr.next();
        	if(trade.getUser().getId().intValue() == userId) {
        		result.add(trade);	
        	}
        }
        
        return result;
    }

	@Override
	public List<Trade> getAllTrades() {
		
		return (List<Trade>) TradeRepository.findAll();
	}

	@Override
	public List<Trade> getAllTradesByStockSymbol(String stockSymbol) {
		
		return TradeRepository.findBySymbol(stockSymbol);
	}
}
