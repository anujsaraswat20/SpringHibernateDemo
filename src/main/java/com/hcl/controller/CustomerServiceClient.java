package com.hcl.controller;

import com.hcl.model.Customer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServiceClient {
  private final RestTemplate restTemplate;
  
  public CustomerServiceClient(RestTemplateBuilder restTemplateBuilder) {
      restTemplate = restTemplateBuilder.build();
  }

  public Customer getCustomer(Integer customerId) {
      return restTemplate.getForObject("/customer/{id}", Customer.class, customerId);
  }
}
