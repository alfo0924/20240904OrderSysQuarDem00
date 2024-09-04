package com.example._20240904ordersysquardem00.controller;

import com.example._20240904ordersysquardem00.model.FoodItem;
import com.example._20240904ordersysquardem00.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/food-items")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @GetMapping
    public String listFoodItems(Model model) {
        List<FoodItem> foodItems = foodItemService.getAllFoodItems();
        model.addAttribute("foodItems", foodItems);
        return "food-items";
    }

    @GetMapping("/{id}")
    public String getFoodItem(@PathVariable String id, Model model) {
        FoodItem foodItem = foodItemService.getFoodItemById(id).orElse(null);
        model.addAttribute("foodItem", foodItem);
        return "food-item-details";
    }
}