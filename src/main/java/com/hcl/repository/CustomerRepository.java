package com.hcl.repository;

import com.hcl.model.Customer;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    /**
     * here we can can add as many as criteria to find Customer
     */
    public List<Customer> findAll();

    public List<Customer> findByName(String name);

    public List<Customer> findByDesignation(String designation);

}
