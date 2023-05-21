package com.zahid.foodDelivery.service;

import com.zahid.foodDelivery.dto.SignInOutput;
import com.zahid.foodDelivery.dto.UserDto;
import com.zahid.foodDelivery.model.AuthenticationToken;
import com.zahid.foodDelivery.model.User;
import com.zahid.foodDelivery.model.constants.UserRole;
import com.zahid.foodDelivery.repository.TokenRepository;
import com.zahid.foodDelivery.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.Session;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.Cookie;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    TokenService tokenService;
    @Autowired
    TokenRepository tokenRepository;

    public SignInOutput signIn(UserDto userDto, HttpServletResponse httpServletResponse) {

        //check if user exists or not based on email
        User user = userRepo.findFirstByEmail(userDto.getEmail());

        if(user == null)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(userDto.getPassword());
        }
        catch (NoSuchAlgorithmException e) {

            throw new IllegalStateException("Encryption Invalid!!!");

        }

        //match it with database encrypted password

        boolean isPasswordValid = encryptedPassword.equals(user.getPassword());

        if(!isPasswordValid)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }
        List<AuthenticationToken> users = tokenRepository.findByUser(user);
        users.stream().forEach(item->{
            item.setExpiredToken(true);
        });


        AuthenticationToken token = new AuthenticationToken(user);
        users.add(token);
        tokenRepository.saveAll(users);

       // tokenRepository.save(token);

        //set up output response

        Cookie cookie = new Cookie( "email",user.getEmail());
        httpServletResponse.addCookie(cookie);
        return new SignInOutput("Authentication Successfull !!!", token.getToken());


    }
    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);

        return hash;

    }
    public UserDto signUp(UserDto userDto ) {


        //check if user exists or not based on email
        User user = userRepo.findFirstByEmail(userDto.getEmail());

        if(user != null)
        {
            throw new IllegalStateException("User already exists!!!!...sign in instead");
        }

//      encryption
        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(userDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        userDto.setPassword(encryptedPassword);
       User savedUser =  userRepo.save(userDtoToUser(userDto));
       return userToUserDto(savedUser);
    }
    private User userDtoToUser(UserDto userDto){
        User user =  new User();
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setRole(UserRole.REGULAR);
        return user;
    }
    private UserDto userToUserDto(User user) {
        return new UserDto(user.getName(), user.getEmail(), user.getPassword());
    }
}
