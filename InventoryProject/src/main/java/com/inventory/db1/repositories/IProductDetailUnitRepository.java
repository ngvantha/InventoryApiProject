package com.inventory.db1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.db1.entities.ProductDetailUnit;

public interface IProductDetailUnitRepository extends JpaRepository<ProductDetailUnit, Integer> {

}
