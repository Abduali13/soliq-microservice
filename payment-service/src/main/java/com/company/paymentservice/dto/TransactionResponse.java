package com.company.paymentservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private Integer transactionId;
    private Integer senderCardId;
    private Integer receiverCardId;
    private Double amount;
    private LocalDateTime transactionDate;
    private boolean status;

}
