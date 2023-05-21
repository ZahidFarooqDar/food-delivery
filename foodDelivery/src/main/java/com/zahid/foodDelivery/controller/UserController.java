package com.zahid.foodDelivery.controller;

import com.zahid.foodDelivery.dto.SignInOutput;
import com.zahid.foodDelivery.dto.UserDto;
import com.zahid.foodDelivery.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/signin")

    public SignInOutput signIn(@Valid @RequestBody UserDto userDto, HttpServletResponse httpServletResponse){
        return userService.signIn(userDto,httpServletResponse);
    }

    @PostMapping("/signup")
    public UserDto signUp(@Valid @RequestBody UserDto userDto){
        return userService.signUp(userDto);
    }

}
