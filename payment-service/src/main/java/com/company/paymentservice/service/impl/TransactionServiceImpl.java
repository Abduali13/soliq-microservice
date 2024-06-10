package com.company.paymentservice.service.impl;

import com.company.paymentservice.client.CardServiceClient;
import com.company.paymentservice.client.UserServiceClient;
import com.company.paymentservice.client.dto.CardResponse;
import com.company.paymentservice.client.enums.Currency;
import com.company.paymentservice.dto.ResponseDto;
import com.company.paymentservice.dto.TransactionResponse;
import com.company.paymentservice.entity.Transaction;
import com.company.paymentservice.exception.ResourceNotFoundException;
import com.company.paymentservice.repository.TransactionRepository;
import com.company.paymentservice.service.TransactionService;
import com.company.paymentservice.service.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final CardServiceClient cardServiceClient;

    @Override
    public ResponseDto<TransactionResponse> transfer(Integer senderCardId, Integer receiverCardId, Double amount) {
        try {

            CardResponse senderCard = Objects.requireNonNull(this.cardServiceClient.get(senderCardId).getBody()).getContent();
            CardResponse receiverCard = Objects.requireNonNull(this.cardServiceClient.get(receiverCardId).getBody()).getContent();

            Transaction transaction;

            Double sumFee = amount * 0.05;
            Double fee = amount * 0.1;

            if (senderCard.getAmount() < amount) {
                return ResponseDto.<TransactionResponse>builder()
                        .code(-2)
                        .message("Balance is not enough to transfer")
                        .build();
            }


            try {
                if (senderCard.getCurrency() == receiverCard.getCurrency() && senderCard.getCurrency() == Currency.SUM) {
                    if (senderCard.getCardType() == receiverCard.getCardType()) {
                        sumFee = 0.0;
                    }
                    receiverCard.setAmount(receiverCard.getAmount() - amount - sumFee);
                }

                if (senderCard.getCurrency() == receiverCard.getCurrency() && senderCard.getCurrency() != Currency.SUM) {
                    receiverCard.setAmount(receiverCard.getAmount() - amount - fee);
                }

                receiverCard.setAmount(receiverCard.getAmount() + amount);

                transaction = new Transaction();
                transaction.setAmount(amount);
                transaction.setTransactionDate(LocalDateTime.now());
                transaction.setSenderCardId(senderCardId);
                transaction.setReceiverCardId(receiverCardId);
            } catch (Exception e) {
                return ResponseDto.<TransactionResponse>builder()
                        .code(-3)
                        .message(e.getMessage()).build();
            }

            return ResponseDto.<TransactionResponse>builder()
                    .success(true)
                    .message("OK")
                    .content(
                            this.transactionMapper.toDto(
                                    this.transactionRepository.save(transaction)
                            )
                    )
                    .build();
        }
        catch (Exception e){
            return ResponseDto.<TransactionResponse>builder()
                    .code(-3)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<TransactionResponse> getTransaction(Integer transactionId) {
        Transaction transaction = this.transactionRepository.findById(transactionId).orElseThrow(() -> new ResourceNotFoundException("Transaction", "transactionId", transactionId));

        return ResponseDto.<TransactionResponse>builder()
                .success(true)
                .message("OK")
                .content(this.transactionMapper.toDto(transaction)).build();
    }
}
