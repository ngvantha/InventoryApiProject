package com.inventory.requestVM.UnitRequest;


import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class CreateUnitRequest {
	
	private String name;
	
	private String unitDescription;
	
	private Boolean delStatus;
}
