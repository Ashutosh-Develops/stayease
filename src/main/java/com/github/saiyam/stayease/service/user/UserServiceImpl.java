package com.github.saiyam.stayease.service.user;

import com.github.saiyam.stayease.constant.UserRole;
import com.github.saiyam.stayease.dto.user.UserLoginRequestBody;
import com.github.saiyam.stayease.dto.user.UserRegistrationRequestBody;
import com.github.saiyam.stayease.entity.User;
import com.github.saiyam.stayease.exception.InvalidInputException;
import com.github.saiyam.stayease.repository.IUserRepository;
import com.github.saiyam.stayease.service.jwt.IJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private IJwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User register(UserRegistrationRequestBody userRegistrationRequestBody) {

        UserRegistrationRequestBody validatedRequest=validateUserRegistrationRequest(userRegistrationRequestBody);


        User user =User.createUser(validatedRequest.getFirstName(),
                 validatedRequest.getLastName(),
                validatedRequest.getEmail(),
                passwordEncoder.encode(validatedRequest.getPassword()),
                validatedRequest.getRole());

        User savedUSer=iUserRepository.save(user);

      //  String token = jwtService.generateToken(user);
      // String token ="Token coming soon or perhaps not!";
        return savedUSer;
    }

    @Override
    public String logIn(UserLoginRequestBody userLoginRequestBody) {

        String email = userLoginRequestBody.getEmail();
        String password= userLoginRequestBody.getPassword();

        if(email==null || email.isEmpty()){
            throw new InvalidInputException("Invalid email "+email);
        }

        if(password==null|| password.isEmpty()){
            throw new InvalidInputException("Invalid password "+password);
        }

        // Authentication manager authenticates email and password and return authentication
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtService.generateToken(authentication);
        return token;

    }

    private UserRegistrationRequestBody validateUserRegistrationRequest(UserRegistrationRequestBody userRegistrationRequestBody){

        String firstName = userRegistrationRequestBody.getFirstName();
        String lastName = userRegistrationRequestBody.getLastName();
        String email= userRegistrationRequestBody.getEmail();
        String password= userRegistrationRequestBody.getPassword();
        String role = userRegistrationRequestBody.getRole();

        if(firstName==null || firstName.isEmpty()){
            throw new InvalidInputException("Invalid firstname");
        }

        if(lastName==null || lastName.isEmpty()){
            throw new InvalidInputException("Invalid lastname");
        }

        if(email==null || email.isEmpty()){
            throw new InvalidInputException("Invalid email");
        }

        if(password==null || password.isEmpty()){
            throw new InvalidInputException("Invalid password");
        }

        if(role==null || role.isEmpty()){
            role = UserRole.CUSTOMER_ROLE;
        }

        return new UserRegistrationRequestBody(firstName,lastName,email,password,role);
    }
}
