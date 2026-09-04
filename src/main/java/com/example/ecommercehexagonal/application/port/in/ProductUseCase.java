package com.example.ecommercehexagonal.application.port.in;

import com.example.ecommercehexagonal.domain.model.Product;

import java.util.List;

public interface ProductUseCase {

    Product createProduct(CreateProductCommand command);

    List<Product> listProducts();

    Product getProductById(String id);
}
