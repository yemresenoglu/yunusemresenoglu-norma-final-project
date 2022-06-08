package com.example.finalproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder

public record GetCurrencyResponseDTO(String name,
                                     String code,
                                     String sign) {
}
