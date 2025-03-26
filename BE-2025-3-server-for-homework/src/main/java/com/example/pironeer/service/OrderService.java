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
    private final ProductRepository productRepository;

    public OrderService(OrderRepository OrderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = OrderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Long createOrder(Long userId, List<OrderRequestItem> items) {
        // 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        // 주문 객체 생성
        Order order = new Order("2025-03-26", "CREATED", user);

        // 각 아이템 처리
        for (OrderRequestItem item : items) {
            Product product = item.getProduct();

            // 재고 확인 및 감소
            if (product.getStockQuantity() < item.getQuantity()) {
                throw new IllegalStateException("재고 부족");
            }
            product.setStockQuantity(product.getStockQuantity() - item.getQuantity());

            item.setOrder(order);
            order.addOrderItem(item);
        }

        Order saved = orderRepository.save(order);
        return saved.getId();
    }

}

