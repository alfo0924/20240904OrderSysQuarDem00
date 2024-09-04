package com.example._20240904ordersysquardem00.controller;

import com.example._20240904ordersysquardem00.model.DeliveryStaff;
import com.example._20240904ordersysquardem00.service.DeliveryStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/delivery-staff")
public class DeliveryStaffController {

    @Autowired
    private DeliveryStaffService deliveryStaffService;

    @GetMapping
    public String listDeliveryStaff(Model model) {
        List<DeliveryStaff> deliveryStaffList = deliveryStaffService.getAllDeliveryStaff();
        model.addAttribute("deliveryStaffList", deliveryStaffList);
        return "delivery-staff";
    }

    @GetMapping("/{id}")
    public String getDeliveryStaff(@PathVariable String id, Model model) {
        DeliveryStaff deliveryStaff = deliveryStaffService.getDeliveryStaffById(id).orElse(null);
        model.addAttribute("deliveryStaff", deliveryStaff);
        return "delivery-staff-details";
    }
}