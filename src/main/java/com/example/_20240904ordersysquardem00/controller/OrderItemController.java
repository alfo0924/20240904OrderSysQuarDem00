package com.example._20240904ordersysquardem00.controller;

import com.example._20240904ordersysquardem00.model.OrderItem;
import com.example._20240904ordersysquardem00.service.OrderItemService;
import com.example._20240904ordersysquardem00.service.OrderService;
import com.example._20240904ordersysquardem00.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private FoodItemService foodItemService;

    @GetMapping
    public String listOrderItems(Model model) {
        model.addAttribute("orderItems", orderItemService.getAllOrderItems());
        return "order-items";
    }

    @GetMapping("/{id}")
    public String viewOrderItem(@PathVariable String id, Model model) {
        orderItemService.getOrderItemById(id).ifPresent(orderItem -> model.addAttribute("orderItem", orderItem));
        return "order-item-details";
    }

    @GetMapping("/new")
    public String newOrderItemForm(Model model) {
        model.addAttribute("orderItem", new OrderItem());
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("foodItems", foodItemService.getAllFoodItems());
        return "order-item-form";
    }

    @PostMapping
    public String saveOrderItem(@ModelAttribute OrderItem orderItem) {
        orderItemService.saveOrderItem(orderItem);
        return "redirect:/order-items";
    }

    @GetMapping("/{id}/edit")
    public String editOrderItemForm(@PathVariable String id, Model model) {
        orderItemService.getOrderItemById(id).ifPresent(orderItem -> model.addAttribute("orderItem", orderItem));
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("foodItems", foodItemService.getAllFoodItems());
        return "order-item-form";
    }

    @PostMapping("/{id}")
    public String updateOrderItem(@PathVariable String id, @ModelAttribute OrderItem orderItem) {
        orderItem.setOrderItemsId(id);
        orderItemService.saveOrderItem(orderItem);
        return "redirect:/order-item-form";
    }

    @GetMapping("/{id}/delete")
    public String deleteOrderItem(@PathVariable String id) {
        orderItemService.deleteOrderItem(id);
        return "redirect:/order-items";
    }

    // API - get all OrderItems
    @GetMapping("/api")
    @ResponseBody
    public List<OrderItem> getOrderItemsApi() {
        return orderItemService.getAllOrderItems();
    }


    // API - get single data from OrderItems
    @GetMapping("/api/{id}")
    @ResponseBody
    public OrderItem getOrderItemApi(@PathVariable String id) {
        return orderItemService.getOrderItemById(id).orElse(null);
    }


    // API - create orderItem
    @PostMapping("/api")
    @ResponseBody
    public OrderItem createOrderItemApi(@RequestBody OrderItem orderItem) {
        return orderItemService.saveOrderItem(orderItem);
    }

    // API - update orderItem
    @PutMapping("/api/{id}")
    @ResponseBody
    public OrderItem updateOrderItemApi(@PathVariable String id, @RequestBody OrderItem orderItem) {
        orderItem.setOrderItemsId(id);
        return orderItemService.saveOrderItem(orderItem);
    }
    // API - delete orderItem
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deleteOrderItemApi(@PathVariable String id) {
        orderItemService.deleteOrderItem(id);
    }




}