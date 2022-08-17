package com.test.authorizingapi.domain.repository;

import com.test.authorizingapi.domain.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
