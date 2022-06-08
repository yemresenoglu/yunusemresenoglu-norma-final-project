package com.example.finalproject.dto;

import com.example.finalproject.model.CustomerAddressType;

public record CustomerAddressDTO(String country,
                                 String city,
                                 String postalCode,
                                 String description) {
}
