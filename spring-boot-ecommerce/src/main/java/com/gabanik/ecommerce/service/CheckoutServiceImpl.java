package com.gabanik.ecommerce.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabanik.ecommerce.dao.CustomerRepository;
import com.gabanik.ecommerce.dto.Purchase;
import com.gabanik.ecommerce.dto.PurchaseResponse;
import com.gabanik.ecommerce.entity.Customer;
import com.gabanik.ecommerce.entity.Order;
import com.gabanik.ecommerce.entity.OrderItem;

import jakarta.transaction.Transactional;

@Service
public class CheckoutServiceImpl implements CheckoutService{

	private CustomerRepository customerRepository;
	
	public CheckoutServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		// retrieve the order info from the dto
		Order order = purchase.getOrder();
		
		//generate tracking number
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		//populate order with orderItems
		Set<OrderItem> orderItems = purchase.getOrderItems();
		if (orderItems != null) {
		    orderItems.forEach(item -> order.add(item));
		}
		
		//populate order with shippingAddress and billingAddress
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		
		//populate customer with order
		Customer customer = purchase.getCustomer();
		customer.add(order);
		
		//save to database
		customerRepository.save(customer);
		
		//return a response
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber() {
		
		// generate a random UUID number (UUID version-4)
		return UUID.randomUUID().toString();
	}
}
