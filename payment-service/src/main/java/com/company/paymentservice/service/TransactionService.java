package com.company.paymentservice.service;

import com.company.paymentservice.dto.ResponseDto;
import com.company.paymentservice.dto.TransactionResponse;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    ResponseDto<TransactionResponse> transfer(Integer senderCardId, Integer receiverCardId, Double amount);

    ResponseDto<TransactionResponse> getTransaction(Integer transactionId);
}
