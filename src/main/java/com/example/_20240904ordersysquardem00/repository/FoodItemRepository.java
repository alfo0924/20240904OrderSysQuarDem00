package com.example._20240904ordersysquardem00.repository;

import com.example._20240904ordersysquardem00.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, String> {
}