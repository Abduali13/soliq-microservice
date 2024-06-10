package com.company.paymentservice.client;

import com.company.paymentservice.client.dto.CardResponse;
import com.company.paymentservice.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "card-service", path = "/card-service/card")
public interface CardServiceClient {

    @GetMapping
    ResponseEntity<ResponseDto<CardResponse>> get(@RequestParam(value = "id") Integer cardId);


}
