package com.example._20240904ordersysquardem00.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "menus")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "menuId")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String menuId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Restaurant restaurant;

    private String menuName;
    private String menuDescribe;
    private String menuStatus;

    @JsonIgnore
    public Restaurant getRestaurant() {
        return restaurant;
    }

    @JsonProperty
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @JsonProperty("restaurantId")
    public Long getRestaurantId() {
        return restaurant != null ? restaurant.getRestaurantId() : null;
    }
}