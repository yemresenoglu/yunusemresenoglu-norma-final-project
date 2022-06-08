package com.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

@Getter
@Setter
@Entity
public class CustomerContact extends BaseModel{

    @Column(nullable = false)
    @Email
    private String firstEmail;
    @Email
    private String secondEmail;

    @Column(nullable = false)
    private String firstCallNumber;

    private String secondCallNumber;

    @OneToOne
    private Customer customer;
}
