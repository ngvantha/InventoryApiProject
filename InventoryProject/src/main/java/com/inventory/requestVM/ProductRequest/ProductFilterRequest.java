package com.inventory.requestVM.ProductRequest;

import java.util.Date;

import com.inventory.validation.ValidBoolean;
import com.inventory.validation.ValidDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductFilterRequest {
	
	@ValidDate(message = "Invalid date format. Please format yyyy-MM-dd. ", pattern = "yyyy-MM-dd")
	private Date createdMaxDate;
	@ValidDate(message = "Invalid date format. Please format yyyy-MM-dd. ", pattern = "yyyy-MM-dd")
	private Date createdMinDate;
	@ValidBoolean(message = "The delStatus must be true or false")
	private Boolean lockoutEnabled;
}
