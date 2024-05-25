package com.learn.shoppingApp.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.shoppingApp.productservice.dto.ProductRequest;
import com.learn.shoppingApp.productservice.dto.ProductResponse;
import com.learn.shoppingApp.productservice.service.ProductService;

@RestController
@RequestMapping("api/product")
public class ProductController {
	
	ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}



	@PostMapping
	ResponseEntity<ProductResponse> create(@RequestBody ProductRequest productRequest){
		return new ResponseEntity<ProductResponse>(productService.create(productRequest),HttpStatus.CREATED); 
	}
	
	@GetMapping
	List<ProductResponse> findAll(){
		return productService.findAll(); 
	}
}
