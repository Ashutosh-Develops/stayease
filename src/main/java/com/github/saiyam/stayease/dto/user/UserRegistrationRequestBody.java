package com.github.saiyam.stayease.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistrationRequestBody {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}
