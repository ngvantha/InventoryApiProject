package com.baydep.inventory.requestVM.AppUserRequest;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class LoginRequest {

	@NonNull
	private String userName;
	
	@NonNull
	private String passWord;
}
