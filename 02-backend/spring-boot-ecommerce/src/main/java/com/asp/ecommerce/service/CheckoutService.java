package com.asp.ecommerce.service;

import com.asp.ecommerce.dto.CancelResponse;
import com.asp.ecommerce.dto.Purchase;
import com.asp.ecommerce.dto.PurchaseResponse;
import com.asp.ecommerce.entity.Order;

public interface CheckoutService {
	
	PurchaseResponse placeOrder(Purchase purchase) throws Exception;
	
	CancelResponse cancelOrder(Long id);

}
