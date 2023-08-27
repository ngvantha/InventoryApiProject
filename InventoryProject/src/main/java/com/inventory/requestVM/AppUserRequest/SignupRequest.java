package com.inventory.requestVM.AppUserRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupRequest {

	private String username;
	private String email;
	private String password;
	
}
