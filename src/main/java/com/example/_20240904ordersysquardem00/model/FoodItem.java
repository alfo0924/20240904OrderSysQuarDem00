package com.example._20240904ordersysquardem00.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "food_items")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "itemsId")
public class FoodItem {
    @Id
    private String itemsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Menu menu;

    private String foodName;
    private Double foodPrice;
    private String foodStatus;

    @JsonIgnore
    public Menu getMenu() {
        return menu;
    }

    @JsonProperty
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @JsonProperty("menuId")
    public String getMenuId() {
        return menu != null ? menu.getMenuId() : null;
    }
}