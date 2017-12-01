package com.hcl.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int version;
    private String name;
    private String description;
    private Date orderDate;

    @ManyToOne
    private Customer customer;

    public MyOrder(Integer id, String name, String description, Integer customerId) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.orderDate = new Date();
        this.customer = new Customer(customerId, "", "", "");
    }

    public MyOrder() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Version
    public long getVersion() {
    return version;
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
    
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "MyOrder [id=" + id + ", name=" + name + ", description=" + description + ", customer=" + customer + "]";
    }

}
