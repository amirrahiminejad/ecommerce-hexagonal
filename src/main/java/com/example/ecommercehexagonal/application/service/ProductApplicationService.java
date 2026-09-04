package com.example.ecommercehexagonal.application.service;

import com.example.ecommercehexagonal.application.port.in.CreateProductCommand;
import com.example.ecommercehexagonal.application.port.in.ProductUseCase;
import com.example.ecommercehexagonal.application.port.out.ProductPort;
import com.example.ecommercehexagonal.domain.exception.NotFoundException;
import com.example.ecommercehexagonal.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductApplicationService implements ProductUseCase {

    private final ProductPort productPort;

    public ProductApplicationService(ProductPort productPort) {
        this.productPort = productPort;
    }

    @Override
    public Product createProduct(CreateProductCommand command) {
        Product product = new Product(UUID.randomUUID().toString(), command.name(), command.price(), command.stock());
        return productPort.save(product);
    }

    @Override
    public List<Product> listProducts() {
        return productPort.findAll();
    }

    @Override
    public Product getProductById(String id) {
        return productPort.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found: " + id));
    }
}
