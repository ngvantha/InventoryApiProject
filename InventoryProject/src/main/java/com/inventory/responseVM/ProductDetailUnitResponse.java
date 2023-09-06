package com.inventory.responseVM;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDetailUnitResponse {
	
	private Integer id;

	private Boolean isMain;

	private Integer conversionratio;

	private RatioType ratioType;

	private Double ratioToUnit;

	private Boolean isStatus;

	private UnitResponse Unit;
	
	private List<ProductDetailUnitInventoryResponse> productDetailUnitInventories;

	public enum RatioType {
		Default, Percent
	}
}
