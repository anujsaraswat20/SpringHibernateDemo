package com.hcl.repository;

import com.hcl.model.MyOrder;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface MyOrderRepository extends CrudRepository<MyOrder, Integer> {

    /**
     * here we can can add as many as criteria to find orders
     */
    public List<MyOrder> findAll();

    public List<MyOrder> findByCustomerId(Integer customerId);

//    public List<MyOrder> findByDescription();
}
