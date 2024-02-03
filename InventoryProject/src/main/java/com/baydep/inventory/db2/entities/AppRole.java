package com.baydep.inventory.db2.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "AppRoles")
@NoArgsConstructor
@Data
public class AppRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Id", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NonNull
	private UUID id;

	@Column(name = "Description", length = 200)
	@NonNull
	private String description;

	@Column(name = "Name")
	private String name;

	@Column(name = "NormalizedName")
	private String normalizedName;

	@Column(name = "ConcurrencyStamp")
	private String concurrencyStamp;

	@ManyToMany(mappedBy = "appRoles",fetch = FetchType.LAZY)
	private List<AppUser> appUsers;

}
