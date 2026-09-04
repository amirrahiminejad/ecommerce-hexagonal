package com.example.ecommercehexagonal.application.port.in;

import com.example.ecommercehexagonal.domain.model.Order;

import java.util.List;

public interface OrderUseCase {

    Order placeOrder(PlaceOrderCommand command);

    List<Order> listOrders();
}
