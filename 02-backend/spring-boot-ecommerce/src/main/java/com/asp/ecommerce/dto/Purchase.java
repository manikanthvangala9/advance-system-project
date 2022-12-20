package com.asp.ecommerce.dto;

import java.util.Set;

import com.asp.ecommerce.entity.Address;
import com.asp.ecommerce.entity.Customer;
import com.asp.ecommerce.entity.Order;
import com.asp.ecommerce.entity.OrderItem;

import lombok.Data;

@Data
public class Purchase {
	
	private Customer customer;
	private Address shippingAddress;
	private Address billingAddress;
	private Order order;
	private Set<OrderItem> orderItems;
	

}
