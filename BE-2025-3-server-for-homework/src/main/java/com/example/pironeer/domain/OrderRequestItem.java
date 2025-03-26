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

    public OrderRequestItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.ordered_price = product.getPrice() * quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return this.product;
    }
}
