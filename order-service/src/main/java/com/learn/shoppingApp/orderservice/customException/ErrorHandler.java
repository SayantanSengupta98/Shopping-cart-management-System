package com.learn.shoppingApp.orderservice.customException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(value= MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> argumentInvalidExceptionHandler(MethodArgumentNotValidException ex)
	{
		List<ObjectError> errors = ex.getAllErrors();
		List<String> errorResponses = new ArrayList<>();
		
		for (ObjectError e : errors) {
			errorResponses.add(e.getDefaultMessage());
		};
		
		return new ResponseEntity<List<String>>(errorResponses, HttpStatus.NOT_ACCEPTABLE);
		
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
