package com.baydep.inventory.requestVM.AppUserRequest;

import java.util.Date;

import com.baydep.inventory.validation.ValidBoolean;
import com.baydep.inventory.validation.ValidDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFilterRequest {

	@ValidDate(message = "Invalid date format. Please format yyyy-MM-dd. ", pattern = "yyyy-MM-dd")
	private Date createdMaxDate;
	@ValidDate(message = "Invalid date format. Please format yyyy-MM-dd. ", pattern = "yyyy-MM-dd")
	private Date createdMinDate;
	@ValidBoolean(message = "The delStatus must be true or false")
	private Boolean lockoutEnabled;
}
