package com.asp.ecommerce.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asp.ecommerce.dao.OrderRepository;
import com.asp.ecommerce.dao.ProductRepositoty;
import com.asp.ecommerce.dto.AdminResponse;
import com.asp.ecommerce.entity.Product;


@Service
public class AdminServiceImpl implements AdminService {
	
	private ProductRepositoty productRepository;
	private OrderRepository orderRepository;
	
	@Autowired
	public AdminServiceImpl(ProductRepositoty productRepository,
			OrderRepository orderRepository) {
		this.productRepository=productRepository;
		this.orderRepository=orderRepository;
		
		
	}
	
	@Override
	@Transactional
	public AdminResponse updateProductQuantity(Long id,Product product) {
		
		
		
		Product product1=productRepository.findById(id).get();
		
		product1.setUnitsInStock(product.getUnitsInStock());
		
		productRepository.save(product1);
		
		String response="product updated Successfully";
		

		return new AdminResponse(response);
		
	}

}
