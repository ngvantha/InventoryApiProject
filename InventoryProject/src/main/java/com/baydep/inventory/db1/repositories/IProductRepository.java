package com.baydep.inventory.db1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.baydep.inventory.db1.entities.Product;

public interface IProductRepository extends JpaRepository<Product, Integer> , JpaSpecificationExecutor<Product>{

	public boolean existsByProductName(String name);

}
