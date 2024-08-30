package com.github.saiyam.stayease.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
