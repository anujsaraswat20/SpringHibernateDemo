package com.hcl.controller;

import com.hcl.api.CustomerService;
import com.hcl.exception.CustomException;
import com.hcl.exception.CustomerNotFoundException;
import com.hcl.model.Customer;
import java.net.URI;
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

/**
 * Controller class responsible to expose REST APIs 
 * @author Anuj Saraswat
 */
@RestController
@RequestMapping("/")
public class CustomerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customerRequest) {
        LOGGER.debug("Processing create customer request ");

        ResponseEntity<Void> response = null;

        Customer customer = customerService.createCustomer(customerRequest);
        final URI customerURI = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/customer/{customerId}").build()
            .expand(customer.getId()).toUri();
        
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(customerURI);
        response = new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);

        LOGGER.debug("Processing done by creating customer : {}", customer);

        return response;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateCustomer(@RequestBody Customer customerRequest) throws CustomException {
        ResponseEntity<Void> response = null;

        Customer customer = customerService.updateCustomer(customerRequest);
        final URI customerURI = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/customer/{customerId}").build()
            .expand(customer.getId()).toUri();

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(customerURI);
        response = new ResponseEntity<Void>(httpHeaders, HttpStatus.OK);
        return response;

    }

    @RequestMapping(value = "/customer/getall", method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return customers;
    }

    @RequestMapping(value = "/customer/getallbyname/name/{name}", method = RequestMethod.GET)
    public List<Customer> getAllCustomersByName(@PathVariable String name) {
        List<Customer> customers = customerService.getAllCustomersByName(name);
        return customers;
    }

    @RequestMapping(value = "/customer/getallbydesignation/designation/{designation}", method = RequestMethod.GET)
    public List<Customer> getAllCustomersByDesignation(@PathVariable String designation) {
        List<Customer> customers = customerService.getAllCustomersDesignation(designation);
        return customers;
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable Integer id) throws CustomerNotFoundException {
        Customer customer = customerService.getCustomer(id);
        return customer;
    }

    @RequestMapping(value = "/customer/{customerid}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable Integer customerid) throws CustomException {
        customerService.deleteCustomer(customerid);
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void exportAllCustomerList() throws CustomException {
        customerService.exportAllCustomersList();
    }
    
    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public void importCustomerList() throws CustomException {
        customerService.importCustomerList();
    }
}
