package com.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Account extends BaseExtendedModel {

    @ManyToOne
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private AccountType accountType = AccountType.CHECKING_ACCOUNT;


    @Column(nullable = false)
    private String bankCode;
    @Column(nullable = false)
    private String branchCode;
    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "[A-Z]{2}\\d{2} ?\\d{4} ?\\d{4} ?\\d{4} ?\\d{4} ?[\\d]{0,2}", message = "Incorrect IBAN format")
    private String ıban;

    @Column(nullable = false)
    @PositiveOrZero
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(nullable = false)
    @PositiveOrZero
    private BigDecimal lockedBalance = BigDecimal.ZERO;

    @ManyToOne()
    private Currency currency;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "account")
    private Set<Card> cards = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "account")
    private Set<Transaction> transactions = new HashSet<>();

    @Transient
    public void addCard(Card card) {
        card.setAccount(this);
        this.cards.add(card);
    }




}
