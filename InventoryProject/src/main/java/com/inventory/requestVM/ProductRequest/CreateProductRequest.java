package com.inventory.requestVM.ProductRequest;

import java.util.List;

import com.inventory.responseVM.ProductDetailResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProductRequest {
	private Integer id;

	private String name;
	
	private Integer viewCount;
	
	private String seoAlias;
	
	private Boolean isStatus;
	
	private Boolean delStatus;
	
	private List<ProductDetailResponse> ProductDetailes;

}
