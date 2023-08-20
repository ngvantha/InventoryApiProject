package com.inventory.db2.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "AppRoles", catalog = "tShopSolution")
@NoArgsConstructor
@Data
public class AppRoles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Id")
	@NonNull
	private UUID Id;

	@Column(name = "Description", length = 200)
	@NonNull
	private String Description;

	@Column(name = "Name")
	private String Name;

	@Column(name = "NormalizedName")
	private String NormalizedName;

	@Column(name = "ConcurrencyStamp")
	private String ConcurrencyStamp;

	@ManyToMany(mappedBy = "appRoles")
	private List<AppUsers> appUsers;

}
