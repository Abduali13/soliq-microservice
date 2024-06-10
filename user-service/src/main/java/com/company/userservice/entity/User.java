package com.company.userservice.entity;

import com.company.userservice.client.entity.Card;
import com.company.userservice.entity.template.AbsEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends AbsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstname;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;

    @ElementCollection
    @CollectionTable(name = "user_cards", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "card_id")
    private List<Integer> cardIdList;

}
