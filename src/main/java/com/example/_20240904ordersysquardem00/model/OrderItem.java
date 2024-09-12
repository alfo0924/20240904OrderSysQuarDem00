package com.example._20240904ordersysquardem00.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderItemsId")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orderItemsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "items_id")
    @JsonIdentityReference(alwaysAsId = true)
    private FoodItem foodItem;

    private Integer counts;
    private Double amount;

    @JsonIgnore
    public Order getOrder() {
        return order;
    }

    @JsonProperty
    public void setOrder(Order order) {
        this.order = order;
    }

    @JsonIgnore
    public FoodItem getFoodItem() {
        return foodItem;
    }

    @JsonProperty
    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    @JsonProperty("orderId")
    public String getOrderId() {
        return order != null ? order.getOrderId() : null;
    }

    @JsonProperty("foodItemId")
    public String getFoodItemId() {
        return foodItem != null ? foodItem.getItemsId() : null;
    }
}