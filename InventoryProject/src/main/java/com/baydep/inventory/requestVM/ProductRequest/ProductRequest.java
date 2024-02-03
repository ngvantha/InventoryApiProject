package com.baydep.inventory.requestVM.ProductRequest;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequest {
	private Integer id;

	private String productName;
	
	private Integer viewCount;
	
	private String seoAlias;
	
	private Boolean isStatus;
	
	private Boolean delStatus;
}
