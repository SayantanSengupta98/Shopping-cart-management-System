package com.learn.shoppingApp.productservice.service;

import java.util.List;

import com.learn.shoppingApp.productservice.dto.ProductRequest;
import com.learn.shoppingApp.productservice.dto.ProductResponse;

public interface ProductService {
	
	ProductResponse create(ProductRequest productRequest);
	
	List<ProductResponse> findAll();
	
}
