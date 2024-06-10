package com.company.cardservice.entity;

import com.company.cardservice.entity.enums.CardType;
import com.company.cardservice.entity.enums.Currency;
import com.company.cardservice.entity.template.AbsEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
public class Card extends AbsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;
    private String name;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private Double amount;

    private String password;
    private Integer cardOwnerId; // userId

}
