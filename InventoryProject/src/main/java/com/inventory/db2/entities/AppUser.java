package com.inventory.db2.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@CreationTimestamp
	private Date createdDate;

	@Column(name = "UserName")
	@NonNull
	private String userName;

	@Column(name = "NormalizedUserName")
	private String normalizedUserName = userName;

	@Column(name = "Email")
	@NonNull
	private String email;

	@Column(name = "NormalizedEmail")
	private String normalizedEmail = email;

	@Column(name = "EmailConfirmed", columnDefinition = "BIT DEFAULT 0")
	private Boolean emailConfirmed = false;

	@Column(name = "PasswordHash")
	private String passwordHash;

	@Column(name = "SecurityStamp")
	private String securityStamp;

	@Column(name = "ConcurrencyStamp")
	private String concurrencyStamp;

	@Column(name = "PhoneNumber")
	private String phoneNumber;

	@Column(name = "PhoneNumberConfirmed", columnDefinition = "BIT DEFAULT 0")
	private Boolean phoneNumberConfirmed = false;

	@Column(name = "TwoFactorEnabled", columnDefinition = "BIT DEFAULT 0")
	private Boolean twoFactorEnabled = false;

	@Column(name = "LockoutEnd")
	private Date lockoutEnd;

	@Column(name = "LockoutEnabled", columnDefinition = "BIT DEFAULT 0")
	private Boolean lockoutEnabled = false;

	@Column(name = "AccessFailedCount", insertable = true, columnDefinition = "integer default 0")
	private Integer accessFailedCount = 0;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "AppUserRoles", joinColumns = { @JoinColumn(name = "UserId") }, inverseJoinColumns = {
			@JoinColumn(name = "RoleId") })
	private List<AppRole> appRoles;

}
