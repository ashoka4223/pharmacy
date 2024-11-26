package com.sinfolix.Sai_Samartha.Repository;


import com.sinfolix.Sai_Samartha.Entities.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {
}
