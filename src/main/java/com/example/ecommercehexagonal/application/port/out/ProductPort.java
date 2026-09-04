package com.example.ecommercehexagonal.application.port.out;

import com.example.ecommercehexagonal.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductPort {

    Product save(Product product);

    Optional<Product> findById(String id);

    List<Product> findAll();
}
