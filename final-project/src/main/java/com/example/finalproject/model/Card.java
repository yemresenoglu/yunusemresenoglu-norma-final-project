package com.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Card extends BaseExtendedModel {

    @Enumerated(EnumType.STRING)
    private CardType cardType = CardType.DEBIT_CARD;

    @Column(nullable = false, length = 16, unique = true, updatable = false)
    @Pattern(regexp = "^4[0-9]{12}(?:[0-9]{3})?$", message = "Invalid card number!")
    private String cardNumber;

    @Column(nullable = false)
    @FutureOrPresent
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    @Column(nullable = false, length = 3, updatable = false)
    private int cvv;

    @Column(nullable = false)
    private String holderName;

    @Column(nullable = false)
    @PositiveOrZero
    private BigDecimal cardLimit;

    @Column(nullable = false)
    @PositiveOrZero
    private BigDecimal cardBalance= BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus = CardStatus.ACTIVE;

    @ManyToOne
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "card")
    private Set<Transaction> transactions = new HashSet<>();

    //Add cardholder name and surname from account.
    public void addCardHolder(Account account)
    {
        this.setHolderName(account.getCustomer().getName() + " " + account.getCustomer().getLastname());
    }



}
