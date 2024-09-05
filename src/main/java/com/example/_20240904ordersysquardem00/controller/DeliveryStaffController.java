package com.example._20240904ordersysquardem00.controller;

import com.example._20240904ordersysquardem00.model.DeliveryStaff;
import com.example._20240904ordersysquardem00.service.DeliveryStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/delivery-staff")
public class DeliveryStaffController {

    @Autowired
    private DeliveryStaffService deliveryStaffService;

    @GetMapping
    public String listDeliveryStaff(Model model) {
        model.addAttribute("deliveryStaffList", deliveryStaffService.getAllDeliveryStaff());
        return "delivery-staff";
    }

    @GetMapping("/{id}")
    public String viewDeliveryStaff(@PathVariable String id, Model model) {
        deliveryStaffService.getDeliveryStaffById(id).ifPresent(staff -> model.addAttribute("deliveryStaff", staff));
        return "delivery-staff-details";
    }

    @GetMapping("/new")
    public String newDeliveryStaffForm(Model model) {
        model.addAttribute("deliveryStaff", new DeliveryStaff());
        return "delivery-staff-form";
    }

    @PostMapping
    public String saveDeliveryStaff(@ModelAttribute DeliveryStaff deliveryStaff) {
        deliveryStaffService.saveDeliveryStaff(deliveryStaff);
        return "redirect:/delivery-staff";
    }

    @GetMapping("/{id}/edit")
    public String editDeliveryStaffForm(@PathVariable String id, Model model) {
        deliveryStaffService.getDeliveryStaffById(id).ifPresent(staff -> model.addAttribute("deliveryStaff", staff));
        return "delivery-staff-form";
    }

    @PostMapping("/{id}")
    public String updateDeliveryStaff(@PathVariable String id, @ModelAttribute DeliveryStaff deliveryStaff) {
        deliveryStaff.setDeliveryId(id);
        deliveryStaffService.saveDeliveryStaff(deliveryStaff);
        return "redirect:/delivery-staff";
    }

    @GetMapping("/{id}/delete")
    public String deleteDeliveryStaff(@PathVariable String id) {
        deliveryStaffService.deleteDeliveryStaff(id);
        return "redirect:/delivery-staff";
    }
}