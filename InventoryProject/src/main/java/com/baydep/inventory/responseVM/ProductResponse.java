package com.baydep.inventory.responseVM;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
public class ProductResponse  implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String productName;
	
	private Integer viewCount;
	
	private String seoAlias;
	
	private Boolean isStatus;
	
	private Boolean delStatus;
	
	//@JsonIgnore
	private List<ProductDetailResponse> productDetails;
	

}
