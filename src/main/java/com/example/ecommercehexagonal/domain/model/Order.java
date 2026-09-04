package com.example.ecommercehexagonal.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public record Order(String id, String customerName, String customerEmail, List<OrderItem> items, OrderStatus status, Instant createdAt, BigDecimal total) {

    public Order {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(customerName, "customerName must not be null");
        Objects.requireNonNull(customerEmail, "customerEmail must not be null");
        Objects.requireNonNull(items, "items must not be null");
        Objects.requireNonNull(status, "status must not be null");
        Objects.requireNonNull(createdAt, "createdAt must not be null");
        Objects.requireNonNull(total, "total must not be null");

        if (customerName.isBlank()) {
            throw new IllegalArgumentException("customerName must not be blank");
        }
        if (customerEmail.isBlank()) {
            throw new IllegalArgumentException("customerEmail must not be blank");
        }
        if (items.isEmpty()) {
            throw new IllegalArgumentException("items must not be empty");
        }
        items = List.copyOf(items);
    }

    public static Order create(String id, String customerName, String customerEmail, List<OrderItem> items) {
        BigDecimal total = items.stream()
                .map(OrderItem::lineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Order(id, customerName, customerEmail, items, OrderStatus.CREATED, Instant.now(), total);
    }
}
