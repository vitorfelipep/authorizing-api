package com.test.authorizingapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "cards")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @Column(name = "card_number")
    private Long cardNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "status")
    private Boolean status;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime creatAt;

    @UpdateTimestamp
    @Column(name = "change_date")
    private LocalDateTime updateAt;

}
