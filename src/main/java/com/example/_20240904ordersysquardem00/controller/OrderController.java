package com.example._20240904ordersysquardem00.controller;

import com.example._20240904ordersysquardem00.model.Order;
import com.example._20240904ordersysquardem00.service.CustomerService;
import com.example._20240904ordersysquardem00.service.DeliveryStaffService;
import com.example._20240904ordersysquardem00.service.OrderService;
import com.example._20240904ordersysquardem00.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    private CustomerService customerService;
    private RestaurantService restaurantService;
    private DeliveryStaffService deliveryStaffService;

    public OrderController(CustomerService customerService, RestaurantService restaurantService, DeliveryStaffService deliveryStaffService) {
        this.customerService = customerService;
        this.restaurantService = restaurantService;
        this.deliveryStaffService = deliveryStaffService;
    }


    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @GetMapping("/{id}")
    public String viewOrder(@PathVariable String id, Model model) {
        orderService.getOrderById(id).ifPresent(order -> model.addAttribute("order", order));
        return "order-details";
    }

    @GetMapping("/new")
    public String newOrderForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        model.addAttribute("deliveryStaff", deliveryStaffService.getAllDeliveryStaff());
        model.addAttribute("orderStatuses", Arrays.asList("待處理", "處理中", "已完成", "已取消"));
        return "order-form";
    }

    @PostMapping
    public String saveOrder(@ModelAttribute Order order) {
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String editOrderForm(@PathVariable String id, Model model) {
        orderService.getOrderById(id).ifPresent(order -> model.addAttribute("order", order));
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        model.addAttribute("deliveryStaff", deliveryStaffService.getAllDeliveryStaff());
        model.addAttribute("orderStatuses", Arrays.asList("待處理", "處理中", "已完成", "已取消"));
        return "order-form";
    }

    @PostMapping("/{id}")
    public String updateOrder(@PathVariable String id, @ModelAttribute Order order) {
        order.setOrderId(id);
        orderService.saveOrder(order);
        return "redirect:/order-form";
    }

    @GetMapping("/{id}/delete")
    public String deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}