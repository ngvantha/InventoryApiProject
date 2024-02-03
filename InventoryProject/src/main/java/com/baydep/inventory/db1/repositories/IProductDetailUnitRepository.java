package com.baydep.inventory.db1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baydep.inventory.db1.entities.ProductDetailUnit;

@Repository
public interface IProductDetailUnitRepository extends JpaRepository<ProductDetailUnit, Integer> {

}
