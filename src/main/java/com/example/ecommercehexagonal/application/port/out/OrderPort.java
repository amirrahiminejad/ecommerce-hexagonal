package com.example.ecommercehexagonal.application.port.out;

import com.example.ecommercehexagonal.domain.model.Order;

import java.util.List;

public interface OrderPort {

    Order save(Order order);

    List<Order> findAll();
}
