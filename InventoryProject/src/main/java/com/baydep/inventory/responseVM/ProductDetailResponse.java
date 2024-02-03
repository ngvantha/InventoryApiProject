package com.baydep.inventory.responseVM;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDetailResponse {
	
	private Integer id;
	
	private String productCode;
	
	private Boolean isStatus;
	
	private String productDetailDescription;
	
	//private Product product;
	//@JsonIgnore
	private List<ProductDetailUnitResponse> productDetailUnits;
}
