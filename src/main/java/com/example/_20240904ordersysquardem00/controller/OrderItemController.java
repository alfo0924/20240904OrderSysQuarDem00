package com.example._20240904ordersysquardem00.controller;

import com.example._20240904ordersysquardem00.model.OrderItem;
import com.example._20240904ordersysquardem00.service.OrderItemService;
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

    @GetMapping
    public String listOrderItems(Model model) {
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        model.addAttribute("orderItems", orderItems);
        return "order-items";
    }

    @GetMapping("/{id}")
    public String getOrderItem(@PathVariable String id, Model model) {
        OrderItem orderItem = orderItemService.getOrderItemById(id).orElse(null);
        model.addAttribute("orderItem", orderItem);
        return "order-item-details";
    }

    @GetMapping("/new")
    public String newOrderItemForm(Model model) {
        model.addAttribute("orderItem", new OrderItem());
        return "order-item-form";
    }

    @PostMapping
    public String createOrderItem(@ModelAttribute OrderItem orderItem) {
        orderItemService.saveOrderItem(orderItem);
        return "redirect:/order-items";
    }

    @GetMapping("/{id}/edit")
    public String editOrderItemForm(@PathVariable String id, Model model) {
        OrderItem orderItem = orderItemService.getOrderItemById(id).orElse(null);
        model.addAttribute("orderItem", orderItem);
        return "order-item-form";
    }

    @PostMapping("/{id}")
    public String updateOrderItem(@PathVariable String id, @ModelAttribute OrderItem orderItem) {
        orderItem.setOrderItemsId(id);
        orderItemService.saveOrderItem(orderItem);
        return "redirect:/order-items";
    }

    @GetMapping("/{id}/delete")
    public String deleteOrderItem(@PathVariable String id) {
        orderItemService.deleteOrderItem(id);
        return "redirect:/order-items";
    }
}