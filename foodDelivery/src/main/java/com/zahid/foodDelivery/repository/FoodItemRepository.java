package com.zahid.foodDelivery.repository;

import com.zahid.foodDelivery.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
}
