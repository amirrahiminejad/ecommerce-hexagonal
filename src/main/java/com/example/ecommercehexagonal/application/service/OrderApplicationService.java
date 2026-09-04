package com.example.ecommercehexagonal.application.service;

import com.example.ecommercehexagonal.application.port.in.OrderUseCase;
import com.example.ecommercehexagonal.application.port.in.PlaceOrderCommand;
import com.example.ecommercehexagonal.application.port.in.PlaceOrderItemCommand;
import com.example.ecommercehexagonal.application.port.out.OrderPort;
import com.example.ecommercehexagonal.application.port.out.ProductPort;
import com.example.ecommercehexagonal.domain.exception.NotFoundException;
import com.example.ecommercehexagonal.domain.model.Order;
import com.example.ecommercehexagonal.domain.model.OrderItem;
import com.example.ecommercehexagonal.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderApplicationService implements OrderUseCase {

    private final ProductPort productPort;
    private final OrderPort orderPort;

    public OrderApplicationService(ProductPort productPort, OrderPort orderPort) {
        this.productPort = productPort;
        this.orderPort = orderPort;
    }

    @Override
    public Order placeOrder(PlaceOrderCommand command) {
        List<Product> products = new ArrayList<>();
        for (PlaceOrderItemCommand item : command.items()) {
            Product product = productPort.findById(item.productId())
                    .orElseThrow(() -> new NotFoundException("Product not found: " + item.productId()));
            products.add(product);
        }

        List<Product> updatedProducts = new ArrayList<>();
        List<OrderItem> orderItems = new ArrayList<>();
        for (int index = 0; index < command.items().size(); index++) {
            PlaceOrderItemCommand item = command.items().get(index);
            Product product = products.get(index);
            Product updatedProduct = product.reserve(item.quantity());
            updatedProducts.add(updatedProduct);
            orderItems.add(new OrderItem(product.id(), product.name(), item.quantity(), product.price()));
        }

        for (Product updatedProduct : updatedProducts) {
            productPort.save(updatedProduct);
        }

        Order order = Order.create(UUID.randomUUID().toString(), command.customerName(), command.customerEmail(), orderItems);
        return orderPort.save(order);
    }

    @Override
    public List<Order> listOrders() {
        return orderPort.findAll();
    }
}
