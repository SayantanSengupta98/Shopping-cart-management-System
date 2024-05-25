package com.learn.shoppingApp.inventoryservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learn.shoppingApp.inventoryservice.model.Inventory;
import com.learn.shoppingApp.inventoryservice.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

	InventoryRepository invRepository;

	public InventoryServiceImpl(InventoryRepository invRepository) {
		this.invRepository = invRepository;
	}


	@Override
	public List<Integer> isEverythingInStock(List<String> skuCode) {
		
		

//		try { 
//			Thread.sleep(50000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		List<Integer> quantityAvailable = skuCode.stream().map(eachCode -> this.isInStock(eachCode)).toList();
		
		System.out.println(quantityAvailable);
		return quantityAvailable;
		
	}
	
	private Integer isInStock(String skuCode ) {
		Optional<Inventory> res = invRepository.findBySkuCode(skuCode);
		if(res.isPresent())
		{
			return res.get().getQuantity();
		}
		else {
			return 0;
		}
		
	}
}
