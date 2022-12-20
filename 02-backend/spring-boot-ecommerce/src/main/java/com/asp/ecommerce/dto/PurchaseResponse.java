package com.asp.ecommerce.dto;

import lombok.Data;

@Data
public class PurchaseResponse {
	
	private final String orderTrackingNumber;//Lambok will generate constructor only with final can also use @NonNull annotation
	private final Long id;

}
