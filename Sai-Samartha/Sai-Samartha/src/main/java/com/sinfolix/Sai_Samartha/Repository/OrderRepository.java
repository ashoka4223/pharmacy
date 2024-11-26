package com.sinfolix.Sai_Samartha.Repository;


import com.sinfolix.Sai_Samartha.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByCustomerEmail(String customerEmail);



}
