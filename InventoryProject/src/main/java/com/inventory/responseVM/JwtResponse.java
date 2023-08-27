package com.inventory.responseVM;

import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponse {
	private String token;
	private UUID id;
	private String type = "Basic";
	private String userName;
	private String email;
	private List<String> roles;

	public JwtResponse(String token, UUID id, String username, String email, List<String> roles) {
		this.token = token;
		this.id = id;
		this.userName = username;
		this.email = email;
		this.roles = roles;
	}

}
