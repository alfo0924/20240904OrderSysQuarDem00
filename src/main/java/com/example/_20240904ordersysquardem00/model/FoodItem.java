package com.example._20240904ordersysquardem00.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "food_items")
@Getter
@Setter
public class FoodItem {
    @Id
    private String itemsId;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private String foodName;
    private Double foodPrice;
    private String foodStatus;
}