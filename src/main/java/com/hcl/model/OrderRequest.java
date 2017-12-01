package com.hcl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
//    private String orderDate;

    @ManyToOne
    private Customer customer;

    public OrderRequest(Integer id, String name, String description, Integer customerId) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
//        this.orderDate = orderDate;
        this.customer = new Customer(customerId, "", "", "");
    }

    public OrderRequest() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
//    public String getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(String orderDate) {
//        this.orderDate = orderDate;
//    }

    @Override
    public String toString() {
        return "MyOrder [id=" + id + ", name=" + name + ", description=" + description + ", customer=" + customer + "]";
    }

}
