package com.zahid.foodDelivery.service;

import com.zahid.foodDelivery.model.AuthenticationToken;
import com.zahid.foodDelivery.model.User;
import com.zahid.foodDelivery.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    TokenRepository tokenRepository;
    public void saveToken(AuthenticationToken token) {
        tokenRepository.save(token);
    }

    public boolean authenticate(String email, String token) {

        if(token==null && email==null){
            return false;
        }

        AuthenticationToken authToken = tokenRepository.findFirstByToken(token);

        if(authToken==null){
            return false;
        }

        String expectedEmail = authToken.getUser().getEmail();


        return expectedEmail.equals(email);
    }


    public void deleteToken(String token) {
        AuthenticationToken token1 = tokenRepository.findFirstByToken(token);

        tokenRepository.deleteById(token1.getTokenId());
    }

    public User findUserByToken(String token)
    {
        return tokenRepository.findFirstByToken(token).getUser();
    }
}
