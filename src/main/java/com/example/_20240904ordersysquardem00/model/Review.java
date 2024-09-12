package com.example._20240904ordersysquardem00.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "reviewId")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    @JsonIdentityReference(alwaysAsId = true)
    private DeliveryStaff deliveryStaff;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @JsonIgnore
    public Order getOrder() {
        return order;
    }

    @JsonProperty
    public void setOrder(Order order) {
        this.order = order;
    }

    @JsonIgnore
    public Customer getCustomer() {
        return customer;
    }

    @JsonProperty
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @JsonIgnore
    public Restaurant getRestaurant() {
        return restaurant;
    }

    @JsonProperty
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @JsonIgnore
    public DeliveryStaff getDeliveryStaff() {
        return deliveryStaff;
    }

    @JsonProperty
    public void setDeliveryStaff(DeliveryStaff deliveryStaff) {
        this.deliveryStaff = deliveryStaff;
    }

    @JsonProperty("orderId")
    public String getOrderId() {
        return order != null ? order.getOrderId() : null;
    }

    @JsonProperty("customerId")
    public Long getCustomerId() {
        return customer != null ? customer.getCustomerId() : null;
    }

    @JsonProperty("restaurantId")
    public Long getRestaurantId() {
        return restaurant != null ? restaurant.getRestaurantId() : null;
    }

    @JsonProperty("deliveryStaffId")
    public String getDeliveryStaffId() {
        return deliveryStaff != null ? deliveryStaff.getDeliveryId() : null;
    }
}