package com.example.pironeer.domain;

import jakarta.persistence.*;

@Entity
public class OrderRequestItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private int ordered_price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    protected OrderRequestItem() {}

    public OrderRequestItem(Long id, int quantity, int ordered_price, Order order, Product product) {
        this.quantity = quantity;
        this.ordered_price = ordered_price;
    }
}
