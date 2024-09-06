package com.example._20240904ordersysquardem00.controller;

import com.example._20240904ordersysquardem00.model.DeliveryStaff;
import com.example._20240904ordersysquardem00.service.DeliveryStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // API - get all deliveryStaff
    @GetMapping("/api")
    @ResponseBody
    public List<DeliveryStaff> getDeliveryStaffApi() {
        return deliveryStaffService.getAllDeliveryStaff();
    }

    // API - get single data of deliveryStaff
    @GetMapping("/api/{id}")
    @ResponseBody
    public DeliveryStaff getDeliveryStaffApi(@PathVariable String id) {
        return deliveryStaffService.getDeliveryStaffById(id).orElse(null);
    }

    // API - create deliveryStaff
    @PostMapping("/api")
    @ResponseBody
    public DeliveryStaff createDeliveryStaffApi(@RequestBody DeliveryStaff deliveryStaff) {
        return deliveryStaffService.saveDeliveryStaff(deliveryStaff);
    }

    // API - update deliveryStaff
    @PutMapping("/api/{id}")
    @ResponseBody
    public DeliveryStaff updateDeliveryStaffApi(@PathVariable String id, @RequestBody DeliveryStaff deliveryStaff) {
        deliveryStaff.setDeliveryId(id);
        return deliveryStaffService.saveDeliveryStaff(deliveryStaff);
    }

    // API - delete deliveryStaff
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deleteDeliveryStaffApi(@PathVariable String id) {
        deliveryStaffService.deleteDeliveryStaff(id);
    }


}