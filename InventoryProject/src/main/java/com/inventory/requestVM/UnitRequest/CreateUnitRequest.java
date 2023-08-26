package com.inventory.requestVM.UnitRequest;

import org.hibernate.validator.constraints.Length;

import com.inventory.validation.ValidBoolean;
import com.inventory.validation.Unit.UnitNameExists;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUnitRequest {

	@NotBlank(message = "The name mustn't be null filed")
	@Length(max = 255, message = "The name's length is max 255 characters")
	@UnitNameExists
	private String name;

	@NotBlank(message = "The unitDescription mustn't be null filed")
	@Length(max = 255, message = "The name's length is max 255 characters")
	private String unitDescription;

	// @Pattern(regexp="true|false", message = "The type must be true or false")
	@NotBlank(message = "The delStatus mustn't be null filed")
	@ValidBoolean(message = "The delStatus must be true or false")
	private Boolean delStatus;
}
