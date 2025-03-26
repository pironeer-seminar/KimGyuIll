package com.example.pironeer.service;

import com.example.pironeer.domain.Order;
import com.example.pironeer.domain.OrderRequestItem;
import com.example.pironeer.domain.Product;
import com.example.pironeer.domain.User;
import com.example.pironeer.repository.OrderRepository;
import com.example.pironeer.repository.ProductRepository;
import com.example.pironeer.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    public Long createOrder(Long userId, List<OrderRequestItem> items) {
        // 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        // 주문 객체 생성
        Order order = new Order("2025-03-26", "ORDERED", user);

        // 각 아이템 처리
        for (OrderRequestItem item : items) {
            Product product = item.getProduct();

            productService.decreaseStock(product.getId(), item.getQuantity());

            item.setOrder(order);
            order.addOrderItem(item);
        }

        Order saved = orderRepository.save(order);
        return saved.getId();
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));

        // 주문 상태 변경
        order.setStatus("CANCELED");

        // 재고 복원
        for (OrderRequestItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            product.setStockQuantity(product.getStockQuantity() + item.getQuantity());
        }
    }

}

