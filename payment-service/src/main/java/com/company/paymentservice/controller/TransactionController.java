package com.company.paymentservice.controller;

import com.company.paymentservice.dto.ResponseDto;
import com.company.paymentservice.dto.TransactionResponse;
import com.company.paymentservice.entity.Transaction;
import com.company.paymentservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.paymentservice.dto.SimpleResponseDto.convertStatusCodeByData;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/payment")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<ResponseDto<TransactionResponse>> transfer(@RequestParam Integer senderCardId, @RequestParam  Integer receiverCardId,@RequestParam Double amount){
        return convertStatusCodeByData(this.transactionService.transfer(senderCardId, receiverCardId, amount));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<TransactionResponse>> getTransaction(@RequestParam(value = "id") Integer transactionId){
        return convertStatusCodeByData(this.transactionService.getTransaction(transactionId));
    }

}
