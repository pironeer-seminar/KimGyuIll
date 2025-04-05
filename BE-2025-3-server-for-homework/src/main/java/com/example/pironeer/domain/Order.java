package com.example.pironeer.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String order_date;
    private String status;


    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    protected Order() {}

    public Order(String order_date, String status, User user) {
        this.order_date = order_date;
        this.status = status;
        this.user = user;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderRequestItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderRequestItem item) {
        orderItems.add(item);
        item.setOrder(this);
    }
    public List<OrderRequestItem> getOrderItems() {
        return orderItems;
    }

}
