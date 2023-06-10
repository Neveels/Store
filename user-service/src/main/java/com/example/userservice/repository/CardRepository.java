package com.example.userservice.repository;


import com.example.userservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findAllByUserId(Long userById);

    Optional<Card> findCardByUserIdAndAndId(Long userId, Integer cardId);


}
