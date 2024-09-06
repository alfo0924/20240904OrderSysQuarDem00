package com.example._20240904ordersysquardem00.controller;

import com.example._20240904ordersysquardem00.model.FoodItem;
import com.example._20240904ordersysquardem00.service.FoodItemService;
import com.example._20240904ordersysquardem00.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/food-items")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @Autowired
    private MenuService menuService;

    @GetMapping
    public String listFoodItems(Model model) {
        model.addAttribute("foodItems", foodItemService.getAllFoodItems());
        return "food-items";
    }

    @GetMapping("/{id}")
    public String viewFoodItem(@PathVariable String id, Model model) {
        foodItemService.getFoodItemById(id).ifPresent(foodItem -> model.addAttribute("foodItem", foodItem));
        return "food-item-details";
    }

    @GetMapping("/new")
    public String newFoodItemForm(Model model) {
        model.addAttribute("foodItem", new FoodItem());
        model.addAttribute("menus", menuService.getAllMenus());
        return "food-item-form";
    }

    @PostMapping
    public String saveFoodItem(@ModelAttribute FoodItem foodItem) {
        foodItemService.saveFoodItem(foodItem);
        return "redirect:/food-items";
    }

    @GetMapping("/{id}/edit")
    public String editFoodItemForm(@PathVariable String id, Model model) {
        foodItemService.getFoodItemById(id).ifPresent(foodItem -> model.addAttribute("foodItem", foodItem));
        model.addAttribute("menus", menuService.getAllMenus());
        return "food-item-form";
    }

    @PostMapping("/{id}")
    public String updateFoodItem(@PathVariable String id, @ModelAttribute FoodItem foodItem) {
        foodItem.setItemsId(id);
        foodItemService.saveFoodItem(foodItem);
        return "redirect:/food-items";
    }

    @GetMapping("/{id}/delete")
    public String deleteFoodItem(@PathVariable String id) {
        foodItemService.deleteFoodItem(id);
        return "redirect:/food-items";
    }

    // API - get all foodItems
    @GetMapping("/api")
    @ResponseBody
    public List<FoodItem> getFoodItemsApi() {
        return foodItemService.getAllFoodItems();
    }
    // API - get a single data from foodItems
    @GetMapping("/api/{id}")
    @ResponseBody
    public FoodItem getFoodItemApi(@PathVariable String id) {
        return foodItemService.getFoodItemById(id).orElse(null);
    }

    // API - create foodItem
    @PostMapping("/api")
    @ResponseBody
    public FoodItem createFoodItemApi(@RequestBody FoodItem foodItem) {
        return foodItemService.saveFoodItem(foodItem);
    }
    // API - update foodItem
    @PutMapping("/api/{id}")
    @ResponseBody
    public FoodItem updateFoodItemApi(@PathVariable String id, @RequestBody FoodItem foodItem) {
        foodItem.setItemsId(id);
        return foodItemService.saveFoodItem(foodItem);
    }
    // API - delete foodItem
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deleteFoodItemApi(@PathVariable String id) {
        foodItemService.deleteFoodItem(id);
    }


}