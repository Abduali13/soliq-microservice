package com.company.cardservice.repository;

import com.company.cardservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Optional<Card> findCardByCardIdAndDeletedAtIsNull(Integer cardId);

    List<Card> findCardByCardOwnerIdAndDeletedAtIsNull(Integer userId);

}
