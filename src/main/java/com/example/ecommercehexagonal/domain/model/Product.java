package com.example.ecommercehexagonal.domain.model;

import com.example.ecommercehexagonal.domain.exception.InsufficientStockException;

import java.math.BigDecimal;
import java.util.Objects;

public record Product(String id, String name, BigDecimal price, int stock) {

    public Product {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(price, "price must not be null");

        if (name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (price.signum() < 0) {
            throw new IllegalArgumentException("price must not be negative");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("stock must not be negative");
        }
    }

    public Product reserve(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than zero");
        }
        if (stock < quantity) {
            throw new InsufficientStockException(name, stock, quantity);
        }
        return new Product(id, name, price, stock - quantity);
    }
}
