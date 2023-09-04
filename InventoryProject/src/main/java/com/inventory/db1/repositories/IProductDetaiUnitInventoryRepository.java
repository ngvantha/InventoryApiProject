package com.inventory.db1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.db1.entities.ProductDetailUnitInventory;

public interface IProductDetaiUnitInventoryRepository extends JpaRepository<ProductDetailUnitInventory, Integer> {

}
