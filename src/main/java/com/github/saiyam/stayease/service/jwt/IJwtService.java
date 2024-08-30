package com.github.saiyam.stayease.service.jwt;

import com.github.saiyam.stayease.entity.User;
import org.springframework.security.core.Authentication;

public interface IJwtService {


    public String generateToken(Authentication authentication);


    public  String getEmailFromToken(String jwt);

}
