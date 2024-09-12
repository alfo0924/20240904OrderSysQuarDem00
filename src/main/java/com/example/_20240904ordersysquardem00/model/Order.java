package com.example._20240904ordersysquardem00.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderId")
public class Order {
    @Id
    private String orderId;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDate;

    private Double orderPrice;
    private String orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderItem> orderItems;

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