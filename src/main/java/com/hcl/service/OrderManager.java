package com.hcl.service;

import com.hcl.api.OrderService;
import com.hcl.exception.CustomException;
import com.hcl.model.MyOrder;
import com.hcl.model.OrderRequest;
import com.hcl.repository.MyOrderRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

/*
 * Class responsible to provide all implementation of order service
 */
@Service
public class OrderManager implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderManager.class);
    
    @Autowired
    private MyOrderRepository myOrderRepository;

    /**
     *  {@inheritDoc}
     */
    @Override
    public MyOrder createOrder(OrderRequest orderRequest) throws CustomException {
        LOGGER.debug("Processing create order request");

//        String s = orderRequest.getOrderDate().replace("Z", "+00:00");
//        try {
//            s = s.substring(0, 22) + s.substring(23);
//        } catch (IndexOutOfBoundsException e) {
//            LOGGER.error("Error occurred while parsing date : {} ", e.getMessage());
//        }
//        Date input = new Date();
//        LocalDateTime localDateTime = LocalDateTime.parse(input.toLocaleString(),
//            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"));
//        
//        Date orderDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        
        MyOrder order = new MyOrder(orderRequest.getId(), orderRequest.getName(), orderRequest.getDescription(), orderRequest.getCustomer().getId());
        try {
            order = myOrderRepository.save(order);
        } catch(JpaObjectRetrievalFailureException ex) {
            LOGGER.error("Exception occurred while creating order. Details are{}", ex.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        }
        return order;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public MyOrder updateOrder(MyOrder order) {
        LOGGER.debug("Processing to update existing order");
        return myOrderRepository.save(order);
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public List<MyOrder> getAllOrdersById(Integer customerid) {
        List<MyOrder> orders = new ArrayList<MyOrder>();
        myOrderRepository.findByCustomerId(customerid).forEach(orders::add);
        return orders;
    }

    /**
     *  {@inheritDoc}
     */
    @Override

    public List<MyOrder> getAllOrders() {
        List<MyOrder> myOrders = new ArrayList<MyOrder>();
        myOrderRepository.findAll().forEach(myOrders::add);
        return myOrders;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public MyOrder getOrder(Integer id) {
        return myOrderRepository.findOne(id);
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public void deleteOrder(Integer id) {
        myOrderRepository.delete(id);
    }

}
