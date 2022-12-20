package com.asp.ecommerce.service;

import com.asp.ecommerce.dto.AdminResponse;
import com.asp.ecommerce.entity.Product;

public interface AdminService {

	AdminResponse updateProductQuantity(Long id, Product product);

}
