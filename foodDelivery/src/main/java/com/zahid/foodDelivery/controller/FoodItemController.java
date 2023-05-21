package com.zahid.foodDelivery.controller;
import com.zahid.foodDelivery.authentication.UserAuthentication;
import com.zahid.foodDelivery.dto.FoodItemDto;
import com.zahid.foodDelivery.service.FoodItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food-items")
public class FoodItemController {
    @Autowired
    FoodItemService foodItemService;
    @Autowired
    UserAuthentication userAuthentication;

    @PostMapping("/create")
    public FoodItemDto create(@RequestBody FoodItemDto foodItemDto,
                              @CookieValue(value = "email", defaultValue = "") String email,
                              @RequestHeader("Authorization") String token
    ) throws Exception {
        if (!userAuthentication.validateUser(email, token))
          throw new Exception("User Not Authorized!!!");
        return foodItemService.createFoodItems(foodItemDto);
    }
   }
