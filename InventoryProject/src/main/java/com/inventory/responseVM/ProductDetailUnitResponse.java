package com.inventory.responseVM;

import java.util.List;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDetailUnitResponse {
	
	private Integer id;

	private Boolean isMain;

	private Boolean conversionratio;

	private RatioType ratioType;

	private Boolean ratioToUnit;

	private Boolean isStatus;

	private ProductDetailResponse productDetail;

	private UnitResponse Unit;

	private List<ProductDetailUnitInventoryResponse> productDetailUnitInventories;

	public enum RatioType {
		Default, Percent
	}
}
