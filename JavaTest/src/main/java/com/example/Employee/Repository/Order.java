package com.example.Employee.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Order extends JpaRepository<Order, Long> {
	
	 public Order createOrder(Order order);
	 public Order getOrderById(Long orderId);
	 public List<Order> getAllOrders();
}


