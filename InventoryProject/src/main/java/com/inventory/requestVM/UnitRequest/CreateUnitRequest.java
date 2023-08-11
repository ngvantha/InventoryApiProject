package com.inventory.requestVM.UnitRequest;


import org.hibernate.validator.constraints.Length;

import com.inventory.validation.Unit.UnitNameExists;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class CreateUnitRequest {
	
	@NotBlank(message = "The name mustn't be null value")
	@Length(max = 255, message = "The name's length is max 255 characters")
	@UnitNameExists
	private String name;
	
	@Length(max = 255, message = "The name's length is max 255 characters")
	private String unitDescription;
	
	//@Pattern(regexp="true|false", message = "The type must be true or false")
	@AssertTrue(message = "The delStatus must be true(0) or false(1)")
	private Boolean delStatus;
}
