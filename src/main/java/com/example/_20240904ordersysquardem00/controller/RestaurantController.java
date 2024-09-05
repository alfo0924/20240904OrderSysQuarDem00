package com.example._20240904ordersysquardem00.controller;

import com.example._20240904ordersysquardem00.model.Restaurant;
import com.example._20240904ordersysquardem00.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public String listRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        return "restaurant";
    }

    @GetMapping("/{id}")
    public String viewRestaurant(@PathVariable Long id, Model model) {
        restaurantService.getRestaurantById(id).ifPresent(restaurant -> model.addAttribute("restaurant", restaurant));
        return "restaurant-details";
    }

    @GetMapping("/new")
    public String newRestaurantForm(Model model) {
        model.addAttribute("restaurant", new Restaurant());
        return "restaurant-form";
    }

    @PostMapping
    public String saveRestaurant(@ModelAttribute Restaurant restaurant) {
        restaurantService.saveRestaurant(restaurant);
        return "redirect:/restaurant";
    }

    @GetMapping("/{id}/edit")
    public String editRestaurantForm(@PathVariable Long id, Model model) {
        restaurantService.getRestaurantById(id).ifPresent(restaurant -> model.addAttribute("restaurant", restaurant));
        return "restaurant-form";
    }

    @PostMapping("/{id}")
    public String updateRestaurant(@PathVariable Long id, @ModelAttribute Restaurant restaurant) {
        restaurant.setRestaurantId(id);
        restaurantService.saveRestaurant(restaurant);
        return "redirect:/restaurant";
    }

    @GetMapping("/{id}/delete")
    public String deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return "redirect:/restaurant";
    }
}