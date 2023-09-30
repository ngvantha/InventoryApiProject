package com.inventory.requestVM.ProductRequest;

import java.util.List;

import com.inventory.db1.entities.Unit;
import com.inventory.requestVM.UnitRequest.CreateUnitRequest;
import com.inventory.responseVM.UnitResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDetailUnitRequest {
	private Integer id;

	private Boolean isMain;

	private Integer conversionratio;

	private RatioType ratioType;

	private Integer ratioToUnit;

	private Boolean isStatus;
	
	private Unit unit;

	//private List<CreateProductDetailUnitInventoryRequest> productDetailUnitInventories;

	public enum RatioType {
		Default, Percent
	}
}
