package com.learn.shoppingApp.inventoryservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.shoppingApp.inventoryservice.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	
	Optional<Inventory> findBySkuCode(String skuCode);

}
