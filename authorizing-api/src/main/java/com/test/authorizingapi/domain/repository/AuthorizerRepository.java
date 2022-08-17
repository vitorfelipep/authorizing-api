package com.test.authorizingapi.domain.repository;

import com.test.authorizingapi.domain.entity.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorizerRepository extends JpaRepository<Authorization, UUID> {
}
