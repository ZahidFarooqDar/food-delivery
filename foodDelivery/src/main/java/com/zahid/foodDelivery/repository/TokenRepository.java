package com.zahid.foodDelivery.repository;

import com.zahid.foodDelivery.model.AuthenticationToken;
import com.zahid.foodDelivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Long> {
    AuthenticationToken findFirstByToken(String token);
    List<AuthenticationToken> findByUser(User user);



}
