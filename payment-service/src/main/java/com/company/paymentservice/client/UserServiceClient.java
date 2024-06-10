package com.company.paymentservice.client;

import com.company.paymentservice.client.dto.UserResponse;
import com.company.paymentservice.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-service", path = "/user-service/user")
public interface UserServiceClient {

    @GetMapping
    ResponseEntity<ResponseDto<UserResponse>> get(@RequestParam(value = "id") Integer userId);
}
