package com.example.finalproject.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@Entity
public class Transaction extends BaseModel {

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;


    @ManyToOne
    @Column(nullable = true)
    private Card card;

    @ManyToOne
    @Column(nullable = false)
    private Account fromAccount;
    @ManyToOne
    @Column(nullable = false)
    private Account toAccount;

    @Column(nullable = false)
    @PositiveOrZero
    private BigDecimal amount;
    @PositiveOrZero
    private BigDecimal fee = BigDecimal.ZERO;
    @Column(nullable = false)
    @PositiveOrZero
    private BigDecimal totalAmount;

    @Column(nullable = false)
    @PositiveOrZero
    private BigDecimal Balance;

    @Column(nullable = false)
    @PositiveOrZero
    private BigDecimal previousBalance;


    @CreatedDate
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transferDate;


}
