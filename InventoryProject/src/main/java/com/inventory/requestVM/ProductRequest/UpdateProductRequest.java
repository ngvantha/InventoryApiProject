package com.inventory.requestVM.ProductRequest;

import com.inventory.validation.Product.ProductIdExists;
import com.inventory.validation.Product.ProductNameExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateProductRequest {
	
	@ProductIdExists(message = "Product id not exist already!")
	private Integer id;

	@ProductNameExists(message = "Product name exist already!")
	private String productName;
	
	private Integer viewCount;
	
	private String seoAlias;
	
	private Boolean isStatus;
	
	private Boolean delStatus;
}
