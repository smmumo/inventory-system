package com.smumo.inventorysystem.repository;

import com.smumo.inventorysystem.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
    List<Orders> findByCustomerId(long customerId);
    //List<Orders> findByProductId(long productId);
}
