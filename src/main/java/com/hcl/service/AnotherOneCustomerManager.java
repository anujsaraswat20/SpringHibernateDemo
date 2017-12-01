package com.hcl.service;

import com.hcl.api.CustomerService;
import com.hcl.exception.CustomException;
import com.hcl.model.Customer;
import com.hcl.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/*
 * Class responsible to provide all implementation of customer service
 */
//@Service
public class AnotherOneCustomerManager implements CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnotherOneCustomerManager.class);

    @Autowired
    private CustomerRepository customerRepository;

    /**
     *  {@inheritDoc}
     */
    @Override
    public Customer createCustomer(Customer customerRequest) {
        LOGGER.debug("Processing to create a new customer");
        return customerRepository.save(customerRequest);
    }

    /**
     *  {@inheritDoc}
     * @throws CustomException 
     */
    @Override
    public Customer updateCustomer(Customer customer) throws CustomException {
        LOGGER.debug("Processing to update existing customer");
        if(!customerRepository.exists(customer.getId())) {
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Entity with specified not exists");
        }
        
        return customerRepository.save(customer);
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public List<Customer> getAllCustomers() {
        LOGGER.debug("Processing to get all customers");
        List<Customer> customers = new ArrayList<Customer>();
        customerRepository.findAll().forEach(customers::add);
        LOGGER.debug("Processing finished with list of customers as {}", customers);
        return customers;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public List<Customer> getAllCustomersByName(String name) {
        LOGGER.debug("Processing starts to get all customers associated to specfied name");
        List<Customer> customers = new ArrayList<Customer>();
        customerRepository.findByName(name).forEach(customers::add);
        LOGGER.debug("Processing finished with all customers associated to specfied name are {}", customers);
        return customers;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public List<Customer> getAllCustomersDesignation(String designation) {
        if(designation == null) {
            LOGGER.error("Designation found null");
        }

        LOGGER.debug("Processing starts to get all customers associated to specfied designation");
        List<Customer> customers = new ArrayList<Customer>();
        customerRepository.findByDesignation(designation).forEach(customers::add);
        LOGGER.debug("Processing finished with all customers associated to specfied designation are {}", customers);
        return customers;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public Customer getCustomer(Integer customerId) {
        LOGGER.debug("Processing starts to get customer associated to specfied id");
        if(customerId == null) {
            LOGGER.error("Customer id found null");
        }
        return customerRepository.findOne(customerId);
    }

    /**
     *  {@inheritDoc}
     * @throws CustomException 
     */
    @Override
    public void deleteCustomer(Integer customerId) throws CustomException {
        if(customerId == null) {
            LOGGER.error("Customer id found null");
        }
        try {
            customerRepository.delete(customerId);    
        } catch(ConstraintViolationException ex) {
            LOGGER.error("Delete customer failed. Detail is {}", ex.getMessage());
            throw new CustomException(ex.getErrorCode(), ex.getMessage());
        }
    }
}
