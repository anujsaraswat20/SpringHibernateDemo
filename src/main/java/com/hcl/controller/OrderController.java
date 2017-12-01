package com.hcl.controller;

import com.hcl.api.OrderService;
import com.hcl.exception.CustomException;
import com.hcl.model.Customer;
import com.hcl.model.MyOrder;
import com.hcl.model.OrderRequest;
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

@RestController
@RequestMapping("/")
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "customer/{customerid}/order", method = RequestMethod.POST)
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequest myOrderRequest, @PathVariable Integer customerid) throws CustomException {
        ResponseEntity<Void> response = null;
        
        myOrderRequest.setCustomer(new Customer(customerid, "", "", ""));
        MyOrder myOrder = orderService.createOrder(myOrderRequest);
        
        final URI orderURI = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/customer/{customerId}/order/"+myOrder.getId()).build()
            .expand(myOrder.getId()).toUri();
        
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(orderURI);
        response = new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
        return response;
    }

    @RequestMapping(value = "customer/{customerid}/order", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateOrder(@RequestBody MyOrder myOrderRequest, @PathVariable Integer customerid) {
        ResponseEntity<Void> response = null;
        
        myOrderRequest.setCustomer(new Customer(customerid, "", "", ""));
        MyOrder order = orderService.updateOrder(myOrderRequest);
        
        final URI orderURI = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/customer/{customerId}/order").build()
            .expand(order.getId()).toUri();
        
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(orderURI);
        response = new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
        return response;
    }
    
    @RequestMapping(value = "/order/getall/customer/{customerid}/order", method = RequestMethod.GET)
    public List<MyOrder> getAllOrders(@PathVariable Integer customerid) {
        List<MyOrder> myOrders = orderService.getAllOrdersById(customerid);
        return myOrders;
    }

    @RequestMapping(value = "/order/getall", method = RequestMethod.GET)
    public List<MyOrder> getAllOrders() {
        List<MyOrder> orders = orderService.getAllOrders();
        return orders;
    }

    @RequestMapping(value = "/order/{orderid}", method = RequestMethod.GET)
    public MyOrder getOrder(@PathVariable Integer orderid) {
        MyOrder myOrder = orderService.getOrder(orderid);
        return myOrder;
    }

     @RequestMapping(value = "/order/{orderid}", method = RequestMethod.DELETE)
     public void deleteOrder(@PathVariable Integer orderid) {
     orderService.deleteOrder(orderid);
     }

}
