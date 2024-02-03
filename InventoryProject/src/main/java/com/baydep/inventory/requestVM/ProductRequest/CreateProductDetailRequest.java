package com.baydep.inventory.requestVM.ProductRequest;

import java.util.List;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProductDetailRequest {
	private Integer id;
	
	private String productCode;
	
	private Boolean isStatus;
	
	private String productDetailDescription;
	
	//private CreateProductRequest product;
	
	private List<CreateProductDetailUnitRequest> productDetailUnits;

}
