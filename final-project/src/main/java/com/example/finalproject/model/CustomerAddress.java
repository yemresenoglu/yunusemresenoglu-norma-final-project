package com.example.finalproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class CustomerAddress extends BaseModel {

    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String city;
    private String postalCode;
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CustomerAddressType customerAddressType= CustomerAddressType.HOME;

    @ManyToOne()
    Customer customer;

}
