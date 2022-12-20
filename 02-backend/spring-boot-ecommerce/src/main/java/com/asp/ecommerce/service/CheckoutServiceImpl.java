 package com.asp.ecommerce.service;


import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asp.ecommerce.dao.CustomerRepository;
import com.asp.ecommerce.dao.OrderItemRepository;
import com.asp.ecommerce.dao.OrderRepository;
import com.asp.ecommerce.dao.ProductRepositoty;
import com.asp.ecommerce.dto.CancelResponse;
import com.asp.ecommerce.dto.Purchase;
import com.asp.ecommerce.dto.PurchaseResponse;
import com.asp.ecommerce.entity.Customer;
import com.asp.ecommerce.entity.Order;
import com.asp.ecommerce.entity.OrderItem;
import com.asp.ecommerce.entity.Product;


@Service
public class CheckoutServiceImpl implements CheckoutService {
	
	
	private CustomerRepository customerRepository;
	private OrderRepository orderRepository;
	private OrderItemRepository orderItemRepository;
	private ProductRepositoty productRepository;
	//private OrderRepository orderRepository;
	

	@Autowired
	public CheckoutServiceImpl(CustomerRepository customerRepository, OrderRepository orderRepository,
			OrderItemRepository orderItemRepository,
			ProductRepositoty productRepository) {
		this.customerRepository = customerRepository;
		this.orderRepository= orderRepository;
		this.orderItemRepository=orderItemRepository;
		this.productRepository=productRepository;
	}
	
	
	@Override
	@Transactional
	public CancelResponse cancelOrder(Long id) {
//		User userFromDb = userRepository.findById(u.getid());
//	    // crush the variables of the object found
//	    userFromDb.setFirstname("john"); 
//	    userFromDb.setLastname("dew");
//	    userFromDb.setAge(16);
//	    userRepository.save(userFromDb);
		
		String response=null;
		
		Order order=orderRepository.findById(id).get();
		
		order.setOrderStatus("cancelled");
		orderRepository.save(order);
		response=order.getOrderStatus();
		
		Set<OrderItem> orderItems=order.getOrderItems();
		for(OrderItem item: orderItems) {
			
			Product product=productRepository.findById(item.getProductId()).get();
			product.setUnitsInStock(product.getUnitsInStock()+item.getQuantity());
			
			productRepository.save(product);
		}
		
		
		return new CancelResponse(response);
		
	}


	@Override
	@Transactional
 	public PurchaseResponse placeOrder(Purchase purchase) throws Exception {
				
		// Get the order from dto
		Order order =purchase.getOrder();
		
		//Generate tracking Number
		String orderTrackingNumber = generateOrderTrakingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
 		
		
		//Populate Order with order items
		Set<OrderItem> orderItems= purchase.getOrderItems();
		orderItems.forEach(item->order.add(item));
		
		for(OrderItem item: orderItems) {
			
			
			Product product=productRepository.findById(item.getProductId()).get();
			if(product.getUnitsInStock()<item.getQuantity()) {
				
				throw new Exception("for Product "+product.getName()+" only "+product.getUnitsInStock()+" units are available.");
				
				//throw new QuantityUnavaulableExcepting(product.getName(),product.getUnitsInStock(),item.getQuantity());
				
			}else {
				product.setUnitsInStock(product.getUnitsInStock()-item.getQuantity());
			}

			
			
		}
	
		
		//populate order with shipping address
		order.setShippingAddress(purchase.getShippingAddress());
		
		//Populate order with billing address
		order.setBillingAddress(purchase.getBillingAddress());
		
		//populate customer with order
		Customer customer=purchase.getCustomer();
		
		//check the whether is it existing customer
		Customer customerInDb= customerRepository.findByEmail(customer.getEmail());
		if(customerInDb != null) {
			customer=customerInDb;
		}
		customer.add(order);
		
		//save to the data base
		customerRepository.save(customer);
		Order order1=orderRepository.findTopByOrderByDateCreatedDesc();
	
		
		
		
		//return a response
		return new PurchaseResponse(orderTrackingNumber,order1.getId());
	}


	private String generateOrderTrakingNumber() {
		
		//Generating randam uuid(Universal Unique Id) version 4
		return UUID.randomUUID().toString(); 
		
	}

}
 