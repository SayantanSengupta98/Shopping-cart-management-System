package com.learn.shoppingApp.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learn.shoppingApp.productservice.dto.ProductRequest;
import com.learn.shoppingApp.productservice.dto.ProductResponse;
import com.learn.shoppingApp.productservice.model.Product;
import com.learn.shoppingApp.productservice.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public ProductResponse create(ProductRequest productRequest) {
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice()).build();
		
		Product savedProduct = productRepository.save(product);
		
		return ProductResponse.builder()
				.id(savedProduct.getId())
				.name(savedProduct.getName())
				.description(savedProduct.getDescription())
				.price(savedProduct.getPrice()).build();
		
	}

	@Override
	public List<ProductResponse> findAll() {
		List<Product> products = productRepository.findAll();
		List<ProductResponse> responses = products.stream().map(product -> convertToProductResponse(product)).toList();
		return responses;
	}

	private ProductResponse convertToProductResponse(Product product) {
		return ProductResponse.builder() 
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice()).build();
	}

}
