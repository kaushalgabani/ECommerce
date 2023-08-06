package com.gabanik.ecommerce.dto;

import java.util.Set;

import com.gabanik.ecommerce.entity.*;

import lombok.Data;

@Data
public class Purchase {

	private Customer customer;
	
	private Address shippingAddress;
	
	private Address billingAddress;
	
	private Order order;
	
	private Set<OrderItem> orderItems;
}
