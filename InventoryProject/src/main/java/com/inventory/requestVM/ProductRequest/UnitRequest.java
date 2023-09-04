package com.inventory.requestVM.ProductRequest;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UnitRequest {
	private Integer id;
	private String name;
	private String unitDescription;
	private Boolean delStatus;
}
