package com.github.saiyam.stayease.dto.user.response;

import com.github.saiyam.stayease.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistrationResponseBody {

    private long Userid;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    public UserRegistrationResponseBody(User user){
        this.Userid=user.getId();
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.email=user.getEmail();
        this.role=user.getRole();
    }

}
