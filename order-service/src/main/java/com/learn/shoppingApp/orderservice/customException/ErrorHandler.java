package com.learn.shoppingApp.orderservice.customException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.learn.shoppingApp.orderservice.model.OutOfStockEntity;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(value= OutOfStockException.class)
	public ResponseEntity<OutOfStockEntity> outOfStockExceptionHandler(OutOfStockException ex)
	{
		OutOfStockEntity oos = new OutOfStockEntity();
		
		oos.setErrorCode(1001);
		oos.setErrorResponse(ex.getMessage());
		
		return new ResponseEntity<OutOfStockEntity>(oos, HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<String> CallNotPermittedExceptionHandler(CallNotPermittedException ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@ExceptionHandler
	public ResponseEntity<String> RuntimeExceptionHandler(RuntimeException ex){
		return new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);	
	}
}
