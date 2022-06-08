package com.example.finalproject.dto;

import java.math.BigDecimal;

public record CurrencyExternalAPIResponseDTO(String fromCode,
                                             String toCode,
                                             BigDecimal currency) {
}
