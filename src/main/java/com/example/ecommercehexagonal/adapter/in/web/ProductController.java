package com.example.ecommercehexagonal.adapter.in.web;

import com.example.ecommercehexagonal.adapter.in.web.dto.ProductRequest;
import com.example.ecommercehexagonal.adapter.in.web.dto.ProductResponse;
import com.example.ecommercehexagonal.application.port.in.CreateProductCommand;
import com.example.ecommercehexagonal.application.port.in.ProductUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductUseCase productUseCase;

    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @GetMapping
    public List<ProductResponse> listProducts() {
        return productUseCase.listProducts().stream().map(ProductResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable String id) {
        return ProductResponse.from(productUseCase.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        var product = productUseCase.createProduct(new CreateProductCommand(request.name(), request.price(), request.stock()));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponse.from(product));
    }
}
