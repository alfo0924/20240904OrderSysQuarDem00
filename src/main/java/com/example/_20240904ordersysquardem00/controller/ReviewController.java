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

import java.util.List;

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

    // API - get all reviews
    @GetMapping("/api")
    @ResponseBody
    public List<Review> getReviewsApi() {
        return reviewService.getAllReviews();
    }


    // API - get single data from reviews
    @GetMapping("/api/{id}")
    @ResponseBody
    public Review getReviewApi(@PathVariable String id) {
        return reviewService.getReviewById(id).orElse(null);
    }

    // API - create review
    @PostMapping("/api")
    @ResponseBody
    public Review createReviewApi(@RequestBody Review review) {
        return reviewService.saveReview(review);
    }

    // API - update review
    @PutMapping("/api/{id}")
    @ResponseBody
    public Review updateReviewApi(@PathVariable String id, @RequestBody Review review) {
        review.setReviewId(id);
        return reviewService.saveReview(review);
    }

    // API - delete review
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deleteReviewApi(@PathVariable String id) {
        reviewService.deleteReview(id);
    }

}