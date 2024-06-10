package com.company.userservice.service.mapper;

import com.company.userservice.client.CardServiceClient;
import com.company.userservice.client.dto.CardResponse;
import com.company.userservice.dto.UserRequest;
import com.company.userservice.dto.UserResponse;
import com.company.userservice.entity.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final CardServiceClient cardServiceClient;


    public User toEntity(UserRequest dto){
        return User.builder()
                .firstname(dto.getFirstname())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public UserResponse toDto(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .firstname(user.getFirstname())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .createdAt(user.getCreatedAt())
                .updateAt(user.getUpdatedAt())
                .deletedAt(user.getDeletedAt())
                .build();
    }

    public UserResponse toDtoWithCard(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .firstname(user.getFirstname())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .cardIdList(Objects.requireNonNull(this.cardServiceClient.getByUserId(user.getUserId()).getBody()).getContent().stream().map(CardResponse::getCardId).toList())
                .createdAt(user.getCreatedAt())
                .updateAt(user.getUpdatedAt())
                .deletedAt(user.getDeletedAt())
                .build();
    }



    public User update(User user, UserRequest dto){
        if (dto.getFirstname() != null){
            user.setFirstname(dto.getFirstname());
        }
        if (dto.getLastName() != null){
            user.setLastName(dto.getLastName());
        }
        if (dto.getPhoneNumber() != null){
            user.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getEmail() != null){
            user.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null){
            user.setPassword(dto.getPassword());
        }
        return user;
    }
}
