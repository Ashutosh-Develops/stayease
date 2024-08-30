package com.github.saiyam.stayease.service.jwt;

import com.github.saiyam.stayease.constant.jwt.JwtConstant;
import com.github.saiyam.stayease.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService implements IJwtService{

    private static SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    @Override
    public String generateToken(Authentication authentication) {

        UserDetails userDetails=(UserDetails)authentication.getPrincipal();

        String jwt= Jwts.builder().setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email",userDetails.getUsername())
                .signWith(key)
                .compact();

        return jwt;
    }

    @Override
    public String getEmailFromToken(String jwt) {
        Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String email = String.valueOf(claims.get("email"));

        return email;
    }
}
