package com.inventory.requestVM.AppUserRequest;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ChangePassWordRequest {
	
	//private UUID id;

	@NonNull
	private String userName;

	@NonNull
	private String email;
	
	@NonNull
	private String oldPassWord;
	
	@NonNull
	private String newPassWord;
	
	
}
