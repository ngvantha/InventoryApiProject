package com.baydep.inventory.db1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baydep.inventory.db1.entities.ProductDetailUnitInventory;

@Repository
public interface IProductDetailUnitInventoryRepository extends JpaRepository<ProductDetailUnitInventory, Integer> {

}
