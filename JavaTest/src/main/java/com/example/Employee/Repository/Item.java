package com.example.Employee.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Item extends JpaRepository<Item, Long> {
    List<Item> findByOrderOrderId(Long orderId);
}
