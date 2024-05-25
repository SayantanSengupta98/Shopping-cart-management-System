package com.learn.shoppingApp.inventoryservice.service;

import java.util.List;

public interface InventoryService {
	
	List<Integer> isEverythingInStock(List<String> skuCode);
}
