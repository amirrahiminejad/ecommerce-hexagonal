package com.example.ecommercehexagonal.adapter.in.web.dto;

import com.example.ecommercehexagonal.domain.model.Product;

import java.math.BigDecimal;

public record ProductResponse(
        String id,
        String name,
        BigDecimal price,
        int stock
) {

    public static ProductResponse from(Product product) {
        return new ProductResponse(product.id(), product.name(), product.price(), product.stock());
    }
}
