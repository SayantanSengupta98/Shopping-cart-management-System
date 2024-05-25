package com.learn.shoppingApp.orderservice.service;

import com.learn.shoppingApp.orderservice.dto.OrderDto;

public interface OrderService {

	String createOrder(OrderDto orderRequest);
}
