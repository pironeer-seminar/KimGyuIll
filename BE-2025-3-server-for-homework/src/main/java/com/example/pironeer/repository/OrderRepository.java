package com.example.pironeer.repository;

import com.example.pironeer.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public Order findById(long id);
    public Order findALl();
}
