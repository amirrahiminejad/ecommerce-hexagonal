package com.example.ecommercehexagonal.application.port.in;

import java.util.List;

public record PlaceOrderCommand(String customerName, String customerEmail, List<PlaceOrderItemCommand> items) {
}
