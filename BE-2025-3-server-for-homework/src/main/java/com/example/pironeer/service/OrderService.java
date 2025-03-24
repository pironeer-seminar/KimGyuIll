package com.example.pironeer.service;

import com.example.pironeer.domain.Order;
import com.example.pironeer.repository.OrderRepository;

import java.util.List;

public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository OrderRepository) {
        this.orderRepository = OrderRepository;
    }

    public Long createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }

}

