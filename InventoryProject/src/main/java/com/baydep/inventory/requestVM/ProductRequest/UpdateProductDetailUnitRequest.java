package com.baydep.inventory.requestVM.ProductRequest;


import com.baydep.inventory.validation.Product.ProductDetailUnitIdExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateProductDetailUnitRequest {
	@ProductDetailUnitIdExists(message = "Product detail unit id not exist already!")
	private Integer id;

	private Boolean isMain;

	private Integer conversionratio;

	private RatioType ratioType;

	private Integer ratioToUnit;

	private Boolean isStatus;
	
	private UnitRequest unit;

	public enum RatioType {
		Default, Percent
	}
}
