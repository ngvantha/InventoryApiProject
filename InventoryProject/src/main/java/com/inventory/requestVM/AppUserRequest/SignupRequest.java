package com.inventory.requestVM.AppUserRequest;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import com.inventory.responseVM.RoleResponse;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class SignupRequest {
	
	private String firstName;
	private String lastName;
	@CreationTimestamp
	private Date DOB;
	private Date createdDate = new Date();
	private String userName;
	//private String normalizedUserName;
	private String email;
	// private String normalizedEmail;
	// private Boolean emailConfirmed = false;
	private String passwordHash;
	// private String securityStamp = null;
	// private String concurrencyStamp = null;
	private String phoneNumber;
	// private Boolean phoneNumberConfirmed = false;
	// private Boolean twoFactorEnabled = false;
	// private Date lockoutEnd;
	// private Boolean lockoutEnabled = false;
	// private String accessFailedCount = "0";
	private List<RoleResponse> appRoles = null;
	
	

//	@PrePersist
//	@PreUpdate
//	void prePersist() {
//		if (getNormalizedUserName() == null) {
//			setNormalizedUserName(getUserName());
//		}
//	}
}
