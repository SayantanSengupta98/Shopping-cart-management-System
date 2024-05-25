package com.learn.shoppingApp.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.shoppingApp.productservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
