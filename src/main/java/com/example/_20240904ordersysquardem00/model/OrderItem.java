package com.example._20240904ordersysquardem00.model;

import jakarta.persistence.*;


@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    private String orderItemsId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "items_id")
    private FoodItem foodItem;

    private Integer counts;
    private Double amount;

    // Getters and setters
    // ... (省略getter和setter方法，與之前的類似)
}