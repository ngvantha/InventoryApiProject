package com.baydep.inventory.requestVM.ProductRequest;

import java.util.List;

import com.baydep.inventory.validation.Product.ProductNameExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProductRequest {
	private Integer id;

	@ProductNameExists(message = "Product name exist already!")
	private String productName;
	
	private Integer viewCount;
	
	private String seoAlias;
	
	private Boolean isStatus;
	
	private Boolean delStatus;
	
	private List<CreateProductDetailRequest> productDetails;

}
