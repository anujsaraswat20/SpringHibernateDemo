package com.hcl.hackerrank.stocktrade.repository;

import org.springframework.data.repository.CrudRepository;

import com.hcl.hackerrank.stocktrade.model.Event;

public interface EventRepository extends CrudRepository<Event, Long>{
    
    
}
