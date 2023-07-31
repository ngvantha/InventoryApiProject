package com.inventory.db1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.db1.entities.ProductDetail;

public interface IProductDetailRepository extends JpaRepository<ProductDetail, Integer>{

}
