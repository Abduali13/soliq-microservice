package com.company.userservice.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String firstname;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;

}
