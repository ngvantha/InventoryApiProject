package com.inventory.requestVM.UnitRequest;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.inventory.validation.ValidBoolean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppUserFilterRequest {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdMaxDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdMinDate;
	@ValidBoolean(message = "The delStatus must be true or false")
	private Boolean lockoutEnabled;
}
