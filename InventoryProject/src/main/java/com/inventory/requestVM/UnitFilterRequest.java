package com.inventory.requestVM;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnitFilterRequest {

	private Integer minId;

	private Integer maxId;
	
	private Boolean status;
}
