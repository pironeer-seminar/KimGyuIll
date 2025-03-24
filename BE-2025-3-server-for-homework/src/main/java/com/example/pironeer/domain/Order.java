package com.example.pironeer.domain;

import jakarta.persistence.*;

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
    }


    public Long getId() {
        return id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public String getStatus() {
        return status;
    }

}
