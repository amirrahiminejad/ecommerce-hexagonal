package com.example.ecommercehexagonal.adapter.in.web;

import com.example.ecommercehexagonal.adapter.in.web.dto.CreateOrderRequest;
import com.example.ecommercehexagonal.adapter.in.web.dto.OrderResponse;
import com.example.ecommercehexagonal.application.port.in.OrderUseCase;
import com.example.ecommercehexagonal.application.port.in.PlaceOrderCommand;
import com.example.ecommercehexagonal.application.port.in.PlaceOrderItemCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderUseCase orderUseCase;

    public OrderController(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    @GetMapping
    public List<OrderResponse> listOrders() {
        return orderUseCase.listOrders().stream().map(OrderResponse::from).toList();
    }

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@Valid @RequestBody CreateOrderRequest request) {
        List<PlaceOrderItemCommand> items = request.items().stream()
            .map(item -> new PlaceOrderItemCommand(item.productId(), item.quantity()))
            .toList();

        OrderResponse response = OrderResponse.from(
                orderUseCase.placeOrder(new PlaceOrderCommand(request.customerName(), request.customerEmail(), items))
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
