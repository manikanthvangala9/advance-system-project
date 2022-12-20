package com.asp.ecommerce.controller;

import org.springframework.web.bind.annotation.*;

import com.asp.ecommerce.dto.CancelResponse;
import com.asp.ecommerce.dto.Purchase;
import com.asp.ecommerce.dto.PurchaseResponse;
import com.asp.ecommerce.service.CheckoutService;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
	
	private CheckoutService checkoutService;
	
	

	public CheckoutController(CheckoutService checkoutService) {
		this.checkoutService = checkoutService;
	}
	
	@PostMapping("/purchase")
	public PurchaseResponse placeOrder(@RequestBody Purchase purchase) throws Exception {
		
		PurchaseResponse purchaseResponse=checkoutService.placeOrder(purchase); 
		return purchaseResponse;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PatchMapping("/cancelOrder/{id}")
	public CancelResponse cancelOrder(@PathVariable Long id) {
		
		CancelResponse cancelResponse=checkoutService.cancelOrder(id);		
		
		return cancelResponse;
		
	}

}
