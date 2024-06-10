package com.company.userservice.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Integer userId;
    private String firstname;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private List<Integer> cardIdList;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private LocalDateTime deletedAt;

}
