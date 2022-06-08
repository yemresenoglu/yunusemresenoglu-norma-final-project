package com.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity


public class Customer extends BaseExtendedModel {


    @Column(nullable = false)
    private Long customerNumber;

    @Column(nullable = false, unique = true, length = 11)
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$", message = "Invalid Turkish Identification Number!")
    private String idNumber;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastname;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @Past(message = "Date of birthday must be before today.")
    private Date birthday;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private String education;
    @Column(nullable = false)
    private String job;
    @Column(nullable = false)
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus = CustomerStatus.ACTIVE;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
    private Set<CustomerAddress> customerAddresses = new HashSet<>();

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private CustomerContact customerContact;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "customer")
    private Set<Account> accounts = new HashSet<>();


    @Transient
    public String getFullName() {
        return name + " " + lastname;
    }

    public void addAddress(CustomerAddress customerAddress) {
        customerAddress.setCustomer(this);
        this.customerAddresses.add(customerAddress);
    }

    public void addAccount(Account account) {
        account.setCustomer(this);
        this.accounts.add(account);
    }


}
