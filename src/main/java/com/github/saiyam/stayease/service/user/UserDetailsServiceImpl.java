package com.github.saiyam.stayease.service.user;

import com.github.saiyam.stayease.entity.User;
import com.github.saiyam.stayease.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService,IUserDetailsService {

    @Autowired
    private IUserRepository iUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username){

        String email = username;

        User user = iUserRepository.findByEmail(email);

        if(user==null){
            throw new UsernameNotFoundException("User with email "+email+" not found");
        }


        return org.springframework.security.core.userdetails.User.withUsername(email)
                .password(user.getPassword())
                .roles(new String[]{user.getRole()})
                .build();

    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
