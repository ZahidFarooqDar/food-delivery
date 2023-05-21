package com.zahid.foodDelivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food-items")
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodItemId;
    private String foodName;
    private Double price;

    @OneToMany
    private List<FoodHistory> foodHistory;

    public FoodItem(String foodName, Double price) {
        this.foodName = foodName;
        this.price = price;
    }
}
