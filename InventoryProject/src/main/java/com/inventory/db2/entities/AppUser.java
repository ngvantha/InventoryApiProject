package com.inventory.db2.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "AppUsers")
@NoArgsConstructor
@Data
public class AppUser implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Id", unique = true)
	@NonNull
	private UUID id;

	@Column(name = "FirstName", length = 200)
	@NonNull
	private String firstName;

	@Column(name = "LastName", length = 200)
	@NonNull
	private String lastName;

	@Column(name = "DOB")
	@NonNull
	private Date DOB;

	@Column(name = "CreatedDate")
	private Date createdDate;

	@Column(name = "UserName")
	@NonNull
	private String userName;

	@Column(name = "NormalizedUserName")
	private String normalizedUserName;

	@Column(name = "Email")
	@NonNull
	private String email;

	@Column(name = "NormalizedEmail")
	private String normalizedEmail;

	@Column(name = "EmailConfirmed")
	private Boolean emailConfirmed;

	@Column(name = "PasswordHash")
	private String passwordHash;

	@Column(name = "SecurityStamp")
	private String securityStamp;

	@Column(name = "ConcurrencyStamp")
	private String concurrencyStamp;

	@Column(name = "PhoneNumber")
	private String phoneNumber;

	@Column(name = "PhoneNumberConfirmed")
	private Boolean phoneNumberConfirmed;

	@Column(name = "TwoFactorEnabled")
	private Boolean twoFactorEnabled;

	@Column(name = "LockoutEnd")
	private Date lockoutEnd;

	@Column(name = "LockoutEnabled")
	@NonNull
	private Boolean lockoutEnabled;

	@Column(name = "AccessFailedCount")
	@NonNull
	private String accessFailedCount;

	@ManyToMany
	@JoinTable(name = "AppUserRoles", joinColumns = { @JoinColumn(name = "UserId") }, inverseJoinColumns = {
			@JoinColumn(name = "RoleId") })
	private List<AppRole> appRoles;
}
