package com.inventory.responseVM;

import java.util.List;

import com.inventory.db1.entities.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDetailResponse {
	
	private Integer id;
	
	private String productCode;
	
	private Boolean isStatus;
	
	private String productDetailDescription;
	
	private Product product;
	
	private List<ProductDetailUnitResponse> productDetailUnites;
}
