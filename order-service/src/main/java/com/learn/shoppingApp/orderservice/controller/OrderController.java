package com.learn.shoppingApp.orderservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.shoppingApp.orderservice.dto.OrderDto;
import com.learn.shoppingApp.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("api/order")
public class OrderController {
	
	/*
	 * TimeLimiterConfig config = TimeLimiterConfig.custom()
	 * .cancelRunningFuture(true) .timeoutDuration(Duration.ofMillis(2000))
	 * .build(); TimeLimiter inventoryServiceTimeLimiter =
	 * TimeLimiter.of("inventory-tl",config);
	 */
	
	OrderService orderService;
	
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	@CircuitBreaker(name = "inventory-cb")
	@Retry(name = "inventory-rty")//, fallbackMethod = "fallbackCreateOrder")
	ResponseEntity<String> create(@RequestBody OrderDto orderDto)
	{
		System.out.println("Retry check!!");
		String orderCreated=orderService.createOrder(orderDto);
		return new ResponseEntity<String>(orderCreated,HttpStatus.CREATED);
	}
	
//	ResponseEntity<String> fallbackCreateOrder(Throwable th)
//	{ 
//		return new ResponseEntity<String>(th.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}
