package com.learn.shoppingApp.orderservice.customException;

public class OutOfStockException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public OutOfStockException(String message) {
		super(message);
	}
	
	

}
