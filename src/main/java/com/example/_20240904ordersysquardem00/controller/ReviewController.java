package com.example._20240904ordersysquardem00.controller;

import com.example._20240904ordersysquardem00.model.Review;
import com.example._20240904ordersysquardem00.service.ReviewService;
import com.example._20240904ordersysquardem00.service.OrderService;
import com.example._20240904ordersysquardem00.service.CustomerService;
import com.example._20240904ordersysquardem00.service.RestaurantService;
import com.example._20240904ordersysquardem00.service.DeliveryStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DeliveryStaffService deliveryStaffService;

    @GetMapping
    public String listReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "reviews";
    }

    @GetMapping("/{id}")
    public String viewReview(@PathVariable String id, Model model) {
        reviewService.getReviewById(id).ifPresent(review -> model.addAttribute("review", review));
        return "review-details";
    }

    @GetMapping("/new")
    public String newReviewForm(Model model) {
        model.addAttribute("review", new Review());
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        model.addAttribute("deliveryStaff", deliveryStaffService.getAllDeliveryStaff());
        return "review-form";
    }

    @PostMapping
    public String saveReview(@ModelAttribute Review review) {
        reviewService.saveReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/{id}/edit")
    public String editReviewForm(@PathVariable String id, Model model) {
        reviewService.getReviewById(id).ifPresent(review -> model.addAttribute("review", review));
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        model.addAttribute("deliveryStaff", deliveryStaffService.getAllDeliveryStaff());
        return "review-form";
    }

    @PostMapping("/{id}")
    public String updateReview(@PathVariable String id, @ModelAttribute Review review) {
        review.setReviewId(id);
        reviewService.saveReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/{id}/delete")
    public String deleteReview(@PathVariable String id) {
        reviewService.deleteReview(id);
        return "redirect:/reviews";
    }
}