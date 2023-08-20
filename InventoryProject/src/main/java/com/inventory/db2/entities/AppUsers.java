package com.inventory.db2.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.inventory.db1.entities.ProductDetailUnit;
import com.inventory.db1.entities.Unit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "AppUsers", catalog = "tShopSolution")
@NoArgsConstructor
@Data
public class AppUsers implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Id")
	@NonNull
	private UUID Id;

	@Column(name = "FirstName", length = 200, nullable = false)
	@NonNull
	private String FirstName;

	@Column(name = "LastName", length = 200, nullable = false)
	@NonNull
	private String LastName;

	@Column(name = "DOB")
	@NonNull
	private Date DOB;

	@Column(name = "CreatedDate")
	private Date CreatedDate;

	@Column(name = "UserName")
	@NonNull
	private String UserName;

	@Column(name = "NormalizedUserName")
	private String NormalizedUserName;

	@Column(name = "Email", length = 255, nullable = false, unique = true)
	@NonNull
	private String Email;

	@Column(name = "NormalizedEmail", length = 255, nullable = false, unique = true)
	private String NormalizedEmail;

	@Column(name = "EmailConfirmed")
	private Boolean EmailConfirmed;

	@Column(name = "PasswordHash")
	private String PasswordHash;

	@Column(name = "SecurityStamp")
	private String SecurityStamp;

	@Column(name = "ConcurrencyStamp")
	private String ConcurrencyStamp;

	@Column(name = "PhoneNumber")
	private String PhoneNumber;

	@Column(name = "PhoneNumberConfirmed")
	private Boolean PhoneNumberConfirmed;

	@Column(name = "TwoFactorEnabled")
	private Boolean TwoFactorEnabled;

	@Column(name = "LockoutEnd")
	private Date LockoutEnd;

	@Column(name = "LockoutEnabled")
	@NonNull
	private Boolean LockoutEnabled;

	@Column(name = "AccessFailedCount")
	@NonNull
	private String AccessFailedCount;

	@ManyToMany
	@JoinTable(name = "AppUserRoles", joinColumns = { @JoinColumn(name = "UserId") }, inverseJoinColumns = {
			@JoinColumn(name = "RoleId") })
	private List<AppRoles> appRoles;
}
