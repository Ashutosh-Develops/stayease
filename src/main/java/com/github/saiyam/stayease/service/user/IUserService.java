package com.github.saiyam.stayease.service.user;

import com.github.saiyam.stayease.dto.user.UserLoginRequestBody;
import com.github.saiyam.stayease.dto.user.UserRegistrationRequestBody;
import com.github.saiyam.stayease.entity.User;

public interface IUserService {

    public User register(UserRegistrationRequestBody userRegistrationRequestBody);

    public String logIn(UserLoginRequestBody userLoginRequestBody);
}
