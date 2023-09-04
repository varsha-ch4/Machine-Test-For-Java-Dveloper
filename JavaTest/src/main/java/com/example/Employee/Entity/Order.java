package com.example.Employee.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
	@Table(name = "orders")
	public class Order {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long orderId;
	    
	    @Column(nullable = false)
	    private Date orderDate;
	    
	    @Enumerated(EnumType.STRING)
	    private String orderStatus;
	    
	    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	    private List<Item> items;

		public Long getOrderId() {
			return orderId;
		}

		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}

		public Date getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(Date orderDate) {
			this.orderDate = orderDate;
		}

		public String getOrderStatus() {
			return orderStatus;
		}

		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}

		public List<Item> getItems() {
			return items;
		}

		public void setItems(List<Item> items) {
			this.items = items;
		}

		public Order(Date orderDate, String orderStatus, List<Item> items) {
			super();
			this.orderDate = orderDate;
			this.orderStatus = orderStatus;
			this.items = items;
		}

	
	    
	    
	}

	



