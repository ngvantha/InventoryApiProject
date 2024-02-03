package com.baydep.inventory.requestVM.ProductRequest;

import java.util.List;

import com.baydep.inventory.db1.entities.Unit;
import com.baydep.inventory.requestVM.UnitRequest.CreateUnitRequest;
import com.baydep.inventory.responseVM.UnitResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProductDetailUnitRequest {
	private Integer id;

	private Boolean isMain;

	private Integer conversionratio;

	private RatioType ratioType;

	private Integer ratioToUnit;

	private Boolean isStatus;
	
	private Unit unit;

	private List<CreateProductDetailUnitInventoryRequest> productDetailUnitInventories;

	public enum RatioType {
		Default, Percent
	}
}
