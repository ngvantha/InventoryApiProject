package com.baydep.inventory.requestVM.ProductRequest;

import com.baydep.inventory.validation.Unit.UnitIdExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnitRequest {
	@UnitIdExists(message = "Unit id not exist already!")
	private Integer id;
	private String name;
	private String unitDescription;
	private Boolean delStatus;
}
