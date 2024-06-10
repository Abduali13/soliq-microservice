package com.company.userservice.service;

import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.UserRequest;
import com.company.userservice.dto.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseDto<UserResponse> create(UserRequest dto);

    ResponseDto<UserResponse> get(Integer userId);

    ResponseDto<UserResponse> update(Integer userId, UserRequest dto);

    ResponseDto<UserResponse> delete(Integer userId);


}
