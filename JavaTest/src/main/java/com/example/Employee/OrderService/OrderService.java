package com.example.Employee.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Employee.Entity.Order;
import java.util.List;

@Service
public class OrderService {
	private final Order orderRepository;

    @Autowired
    public OrderService(Order orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        // Set the order status to "New" before saving to the database
        order.setOrderStatus("New");
        return orderRepository.save(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
