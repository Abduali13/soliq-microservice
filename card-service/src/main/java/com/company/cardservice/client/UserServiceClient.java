package com.company.cardservice.client;

import com.company.cardservice.client.dto.UserResponse;
import com.company.cardservice.config.LoadBalancerConfiguration;
import com.company.cardservice.dto.ResponseDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-service", path = "/api/user")
@LoadBalancerClient(name = "card-service", configuration= LoadBalancerConfiguration.class)
public interface UserServiceClient {

    @GetMapping
    ResponseEntity<ResponseDto<UserResponse>> get(@RequestParam(value = "id") Integer userId);

}
