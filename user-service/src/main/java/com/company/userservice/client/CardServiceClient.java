package com.company.userservice.client;


import com.company.userservice.client.dto.CardResponse;
import com.company.userservice.config.LoadBalancerConfiguration;
import com.company.userservice.dto.ResponseDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "card-service", path = "/api/card")
@LoadBalancerClient(name = "card-service", configuration= LoadBalancerConfiguration.class)
public interface CardServiceClient {

    @GetMapping
    ResponseEntity<ResponseDto<List<CardResponse>>> getByUserId(@RequestParam(value = "id") Integer userId);


}