package com.zahid.foodDelivery.authentication;

import com.zahid.foodDelivery.model.AuthenticationToken;
import com.zahid.foodDelivery.model.User;
import com.zahid.foodDelivery.repository.TokenRepository;
import com.zahid.foodDelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthentication {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    public Boolean validateUser(String email, String token){
        if(token==null || email==null){
            return false;
        }
        User user = userRepository.findFirstByEmail(email);
        AuthenticationToken authenticationToken = tokenRepository.findFirstByToken(token);

        if(user.equals(authenticationToken.getUser())){
            return true;
        }else{
            return false;
        }
    }
}
