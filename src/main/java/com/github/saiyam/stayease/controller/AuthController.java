package com.github.saiyam.stayease.controller;


import com.github.saiyam.stayease.dto.user.UserLoginRequestBody;
import com.github.saiyam.stayease.dto.user.UserRegistrationRequestBody;
import com.github.saiyam.stayease.dto.user.response.ResponseToken;
import com.github.saiyam.stayease.dto.user.response.UserRegistrationResponseBody;
import com.github.saiyam.stayease.entity.User;
import com.github.saiyam.stayease.service.user.IUserService;
import com.github.saiyam.stayease.service.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stayease/v1/auth")
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponseBody> register(@RequestBody UserRegistrationRequestBody userRegistrationRequestBody){
        User user =userService.register(userRegistrationRequestBody);
        return new ResponseEntity<UserRegistrationResponseBody>(new UserRegistrationResponseBody(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseToken> logIn(@RequestBody UserLoginRequestBody userLoginRequestBody){
        String token= userService.logIn(userLoginRequestBody);
        return new ResponseEntity<ResponseToken>(new ResponseToken(token), HttpStatus.OK);
    }


}
