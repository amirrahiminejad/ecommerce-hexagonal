package com.example.ecommercehexagonal.adapter.in.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank String name,
        @NotNull @PositiveOrZero BigDecimal price,
        @Min(0) int stock
) {
}
