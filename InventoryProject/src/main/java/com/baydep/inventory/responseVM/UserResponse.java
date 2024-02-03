package com.baydep.inventory.responseVM;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.baydep.inventory.db2.entities.AppRole;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UserResponse {

	@NonNull
	private UUID id;

	@NonNull
	private String firstName;

	@NonNull
	private String lastName;

	@NonNull
	private Date DOB;

	private Date createdDate;

	@NonNull
	private String userName;

	private String normalizedUserName;

	@NonNull
	private String email;

	private String normalizedEmail;

	private Boolean emailConfirmed;

	private String securityStamp;

	private String concurrencyStamp;

	private String phoneNumber;

	private Boolean phoneNumberConfirmed;

	private Boolean twoFactorEnabled;
	
	private Date lockoutEnd;
	
	@NonNull
	private Boolean lockoutEnabled;
	
	@NonNull
	private String accessFailedCount;

	private List<RoleResponse> appRoles;
}
