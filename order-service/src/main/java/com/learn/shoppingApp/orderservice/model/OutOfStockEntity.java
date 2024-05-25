package com.learn.shoppingApp.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutOfStockEntity {
	
	private int errorCode;
	private String errorResponse;
	
	

}
