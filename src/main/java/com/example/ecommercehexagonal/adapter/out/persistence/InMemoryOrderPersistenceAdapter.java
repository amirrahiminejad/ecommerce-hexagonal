package com.example.ecommercehexagonal.adapter.out.persistence;

import com.example.ecommercehexagonal.application.port.out.OrderPort;
import com.example.ecommercehexagonal.domain.model.Order;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InMemoryOrderPersistenceAdapter implements OrderPort {

    private final List<Order> storage = new CopyOnWriteArrayList<>();

    @Override
    public Order save(Order order) {
        storage.add(order);
        return order;
    }

    @Override
    public List<Order> findAll() {
        return storage.stream()
                .sorted(Comparator.comparing(Order::createdAt).reversed())
                .toList();
    }
}
