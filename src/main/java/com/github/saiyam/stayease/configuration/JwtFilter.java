package com.github.saiyam.stayease.configuration;

import com.github.saiyam.stayease.constant.jwt.JwtConstant;
import com.github.saiyam.stayease.service.jwt.IJwtService;
import com.github.saiyam.stayease.service.user.IUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Service
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private IJwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

     String jwt=request.getHeader(JwtConstant.JWT_HEADER);

     if(jwt!=null){
         jwt=jwt.substring(7);   // Fetch token after bearer keyword
         try{

             String email = jwtService.getEmailFromToken(jwt);

             if (email != null&&SecurityContextHolder.getContext().getAuthentication()==null) {
                 UserDetails userDetail = userDetailsService.loadUserByUsername(email);
                 UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                         userDetail,
                         null,
                         userDetail.getAuthorities()
                 );
                 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(authToken);
             }

             // Continue the filter chain
             filterChain.doFilter(request, response);

         }catch(Exception e){
             throw new BadCredentialsException(e.getMessage());
         }
     }

        filterChain.doFilter(request, response);


    }



}
