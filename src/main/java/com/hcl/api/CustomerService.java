package com.hcl.api;

import com.hcl.exception.CustomException;
import com.hcl.exception.CustomerNotFoundException;
import com.hcl.model.Customer;
import java.util.List;

/**
 * Interface exposing contract to handle Customers
 * @author Anuj Saraswat
 */
public interface CustomerService {

    /**
     * Create customer and persist in database
     * @param customer Requested customer object to be persisted in database
     */
    public Customer createCustomer(Customer customer);

    /**
     * Updates customer and persist in database
     * @param customer Requested customer object to be update and persisted in database
     * @throws CustomException exception with necessary reason  
     */
    public Customer updateCustomer(Customer customer) throws CustomException;

    /**
     * Return all existing customers persisted in database
     * @return All existing customers persisted in database
     */
    public List<Customer> getAllCustomers();

    /**
     * Return all existing customers from database associated with requested customer name 
     * @param customerName Customer name to search for in database 
     * @return All existing customers from database which have customer name as requested
     */
    public List<Customer> getAllCustomersByName(String customerName);

    /**
     * Return all existing customers from database associated with requested customer designation 
     * @param designation Customer's designation to be search in database 
     * @return All existing customers from database associated with requested customer designation
     */
    public List<Customer> getAllCustomersDesignation(String designation);

    /**
     * Return customer associated with specified ID
     * @param id Unique identifier to get customer instance from database
     * @return Customer associated with specified ID
     */
    public Customer getCustomer(Integer customerId) throws CustomerNotFoundException;

    /**
     * Deletes customer my identifying with specified Id
     * @param id Unique identifier to delete order from database
     * @throws CustomException exception with necessary reason
     */
    public void deleteCustomer(Integer customerId) throws CustomException;

}
