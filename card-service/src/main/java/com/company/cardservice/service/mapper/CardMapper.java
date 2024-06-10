package com.company.cardservice.service.mapper;

import com.company.cardservice.dto.CardRequest;
import com.company.cardservice.dto.CardResponse;
import com.company.cardservice.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public Card toEntity(CardRequest dto){
        return Card.builder()
                .name(dto.getName())
                .cardType(dto.getCardType())
                .currency(dto.getCurrency())
                .amount(dto.getAmount())
                .password(dto.getPassword())
                .build();
    }

    public CardResponse toDto(Card card){
        return CardResponse.builder()
                .cardId(card.getCardId())
                .cardOwnerId(card.getCardOwnerId())
                .name(card.getName())
                .amount(card.getAmount())
                .currency(card.getCurrency())
                .cardType(card.getCardType())
                .password(card.getPassword())
                .createdAt(card.getCreatedAt())
                .updatedAt(card.getUpdatedAt())
                .deletedAt(card.getDeletedAt())
                .build();
    }

    public Card update(Card card, CardRequest dto){
        if (dto.getName() != null){
            card.setName(dto.getName());
        }
        return card;
    }


}
