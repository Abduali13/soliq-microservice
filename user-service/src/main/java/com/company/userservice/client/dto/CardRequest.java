package com.company.userservice.client.dto;


import com.company.userservice.client.enums.CardType;
import com.company.userservice.client.enums.Currency;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {

    private String name;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private Long amount;

    private String password;

    private String cardOwnerId;
}
