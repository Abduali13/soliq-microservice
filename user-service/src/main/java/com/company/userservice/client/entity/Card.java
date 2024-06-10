package com.company.userservice.client.entity;


import com.company.userservice.client.enums.CardType;
import com.company.userservice.client.enums.Currency;
import com.company.userservice.entity.template.AbsEntity;
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

    private Long amount;

    private String password;
    private Integer cardOwnerId; // userId

}
