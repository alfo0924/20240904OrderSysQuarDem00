package com.example._20240904ordersysquardem00.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "menus")
public class Menu {
    @Id
    private String menuId;

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus;
    }

    public void setMenuDescribe(String menuDescribe) {
        this.menuDescribe = menuDescribe;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    private String menuName;
    private String menuDescribe;
    private String menuStatus;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    // Getters and setters
}