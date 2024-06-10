package com.company.paymentservice.service.mapper;

import com.company.paymentservice.dto.TransactionRequest;
import com.company.paymentservice.dto.TransactionResponse;
import com.company.paymentservice.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public Transaction toEntity(TransactionRequest dto){
        return Transaction.builder()
                .amount(dto.getAmount())
                .transactionDate(dto.getTransactionDate())
                .build();
    }

    public TransactionResponse toDto(Transaction transaction){
        return TransactionResponse.builder()
                .transactionId(transaction.getTransactionId())
                .amount(transaction.getAmount())
                .receiverCardId(transaction.getReceiverCardId())
                .senderCardId(transaction.getSenderCardId())
                .status(transaction.isStatus())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }
}
