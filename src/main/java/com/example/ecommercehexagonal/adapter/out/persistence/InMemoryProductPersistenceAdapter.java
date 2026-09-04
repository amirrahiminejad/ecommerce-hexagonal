package com.example.ecommercehexagonal.adapter.out.persistence;

import com.example.ecommercehexagonal.application.port.out.ProductPort;
import com.example.ecommercehexagonal.domain.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class InMemoryProductPersistenceAdapter implements ProductPort {

    private final ConcurrentMap<String, Product> storage = new ConcurrentHashMap<>();

    public InMemoryProductPersistenceAdapter() {
        save(new Product("prod-1", "Laptop", new BigDecimal("45000000"), 5));
        save(new Product("prod-2", "Mouse", new BigDecimal("750000"), 20));
    }

    @Override
    public Product save(Product product) {
        storage.put(product.id(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Product> findAll() {
        return storage.values().stream()
                .sorted(Comparator.comparing(Product::name))
                .toList();
    }
}
