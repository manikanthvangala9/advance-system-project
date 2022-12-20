package com.asp.ecommerce.controller;


import org.springframework.web.bind.annotation.*;

import com.asp.ecommerce.dto.AdminResponse;
import com.asp.ecommerce.entity.Product;
import com.asp.ecommerce.service.AdminService;
import com.fasterxml.jackson.annotation.JsonProperty;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	private AdminService adminService;
	
	
	@PutMapping("/updateProduct/{id}")
	public AdminResponse updateProductQuantity(@PathVariable Long id, @RequestBody Product product) {
		
//		System.out.println("the quantity is "+quantity);
		
		AdminResponse adminResponse=adminService.updateProductQuantity(id,product);		
		
		return adminResponse;
		
	}

}
