package com.example.ecommercehexagonal.adapter.in.web.dto;

import com.example.ecommercehexagonal.domain.model.OrderItem;

import java.math.BigDecimal;

public record OrderItemResponse(
        String productId,
        String productName,
        int quantity,
        BigDecimal unitPrice,
        BigDecimal lineTotal
) {

    public static OrderItemResponse from(OrderItem item) {
        return new OrderItemResponse(
                item.productId(),
                item.productName(),
                item.quantity(),
                item.unitPrice(),
                item.lineTotal()
        );
    }
}
