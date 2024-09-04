package com.example._20240904ordersysquardem00.controller;

import com.example._20240904ordersysquardem00.model.Review;
import com.example._20240904ordersysquardem00.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public String listReviews(Model model) {
        List<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);
        return "reviews";
    }

    @GetMapping("/{id}")
    public String getReview(@PathVariable String id, Model model) {
        Review review = reviewService.getReviewById(id).orElse(null);
        model.addAttribute("review", review);
        return "review-details";
    }
}