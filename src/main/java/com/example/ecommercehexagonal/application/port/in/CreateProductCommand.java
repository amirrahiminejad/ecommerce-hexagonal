package com.example.ecommercehexagonal.application.port.in;

import java.math.BigDecimal;

public record CreateProductCommand(String name, BigDecimal price, int stock) {
}
