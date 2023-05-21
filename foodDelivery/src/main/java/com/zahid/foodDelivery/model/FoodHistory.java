package com.zahid.foodDelivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food-history")
public class FoodHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodHistoryId;
    @ManyToOne
    @JoinColumn(name = "foodItemId")
    private FoodItem foodItem;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
