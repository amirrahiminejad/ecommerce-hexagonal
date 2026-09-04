package com.example.ecommercehexagonal.adapter.in.web.dto;

import com.example.ecommercehexagonal.domain.model.Order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderResponse(
        String id,
        String customerName,
        String customerEmail,
        String status,
        Instant createdAt,
        BigDecimal total,
        List<OrderItemResponse> items
) {

    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.id(),
                order.customerName(),
                order.customerEmail(),
                order.status().name(),
                order.createdAt(),
                order.total(),
                order.items().stream().map(OrderItemResponse::from).toList()
        );
    }
}
