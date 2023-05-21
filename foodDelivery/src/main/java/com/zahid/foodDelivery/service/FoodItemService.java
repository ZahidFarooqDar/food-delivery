package com.zahid.foodDelivery.service;
import com.zahid.foodDelivery.dto.FoodItemDto;
import com.zahid.foodDelivery.model.FoodItem;
import com.zahid.foodDelivery.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {
    @Autowired
    FoodItemRepository foodItemRepository;

    public FoodItemDto createFoodItems(FoodItemDto foodItemDto) {
        FoodItem foodItem = foodItemDtoToFoodItem(foodItemDto);
        return foodItemToFoodItemDto(foodItemRepository.save(foodItem));
    }

    private FoodItemDto foodItemToFoodItemDto(FoodItem foodItem){
       return new FoodItemDto(foodItem.getFoodName(), foodItem.getPrice());

    }
    private FoodItem foodItemDtoToFoodItem(FoodItemDto foodItemDto){
        return new FoodItem(foodItemDto.getFoodName(),foodItemDto.getPrice());

    }
}
