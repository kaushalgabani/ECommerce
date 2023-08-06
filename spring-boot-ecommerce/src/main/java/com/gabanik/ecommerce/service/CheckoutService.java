package com.gabanik.ecommerce.service;

import com.gabanik.ecommerce.dto.Purchase;
import com.gabanik.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
	
	PurchaseResponse placeOrder(Purchase purchase);
}
