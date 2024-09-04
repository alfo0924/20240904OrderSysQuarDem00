package com.example._20240904ordersysquardem00.model;

import com.example._20240904ordersysquardem00.model.Customer;
import com.example._20240904ordersysquardem00.model.DeliveryStaff;
import com.example._20240904ordersysquardem00.model.Restaurant;

import com.example._20240904ordersysquardem00.model.Order;  // 使用你的包路径
import jakarta.persistence.*;


@Entity
@Table(name = "reviews")
public class Review {
    @Id
    private String reviewId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private DeliveryStaff deliveryStaff;

    private String comment;

    // Getters and setters
    // ... (省略getter和setter方法，與之前的類似)
}