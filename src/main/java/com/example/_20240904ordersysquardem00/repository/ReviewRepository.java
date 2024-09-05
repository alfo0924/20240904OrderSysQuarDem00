package com.example._20240904ordersysquardem00.repository;

import com.example._20240904ordersysquardem00.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
}