package com.example.ecommercehexagonal.domain.exception;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(String productName, int available, int requested) {
        super("Not enough stock for " + productName + ". Available: " + available + ", requested: " + requested);
    }
}
