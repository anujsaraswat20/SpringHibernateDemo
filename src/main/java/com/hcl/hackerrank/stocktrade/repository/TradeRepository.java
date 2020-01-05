package com.hcl.hackerrank.stocktrade.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hcl.hackerrank.stocktrade.model.Trade;

public interface TradeRepository extends CrudRepository<Trade, Long> {

	List<Trade> findBySymbol(String symbol);
	List<Trade> findById(Long id);
}
