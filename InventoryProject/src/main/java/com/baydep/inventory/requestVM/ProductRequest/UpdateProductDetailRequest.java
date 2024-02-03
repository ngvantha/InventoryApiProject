package com.baydep.inventory.requestVM.ProductRequest;

import com.baydep.inventory.validation.Product.ProductDetailIdExists;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class UpdateProductDetailRequest {
	@ProductDetailIdExists(message = "Product detail id not exist already!")
	private Integer id;
	
	private String productCode;
	
	private Boolean isStatus;
	
	private String productDetailDescription;
}
