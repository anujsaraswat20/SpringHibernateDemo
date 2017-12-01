package com.hcl.api;

import com.hcl.exception.CustomException;
import com.hcl.model.MyOrder;
import com.hcl.model.OrderRequest;
import java.util.List;

/**
 * Interface exposing contract to handle Orders of Customers
 * @author Anuj Saraswat
 */
public interface OrderService {

    /**
     * Create order and persist in database
     * @param customer Requested customer object to be persisted in database
     * @throws CustomException exception with necessary reason
     */
    public MyOrder createOrder(OrderRequest order) throws CustomException;

    /**
     * Updates order and persist in database
     * @param customer Requested customer object to be update and persisted in database
     */
    public MyOrder updateOrder(MyOrder order);

    /**
     * Return all existing orders from database associated with requested order Id 
     * @param customerName Customer name to search for in database 
     * @return All existing orders from database associated with requested order Id 
     */
    public List<MyOrder> getAllOrdersById(Integer orderId);

    /**
     * Return all existing orders persisted in database
     * @return All existing orders persisted in database
     */
    public List<MyOrder> getAllOrders();

    /**
     * Return order associated with specified ID
     * @param id Unique identifier to get order instance from database
     * @return Order associated with specified ID
     */
    public MyOrder getOrder(Integer orderId);

    /**
     * Deletes order which is associated with specified Id
     * @param id Unique identifier to delete order from database
     */
    public void deleteOrder(Integer orderId);
}
