package com.test.authorizingapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "authorizations")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authorization {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "card_number")
    private Long cardNumber;

    @Column(name = "transaction_value")
    private BigDecimal transactionValue;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime creatAt;

}
