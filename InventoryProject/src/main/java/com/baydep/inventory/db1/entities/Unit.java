package com.baydep.inventory.db1.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "UNITS", catalog = "WAREHOUSE")
@NoArgsConstructor
@Data
public class Unit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UNIT_ID")
	@NonNull
	private Integer id;

	@Column(name = "UNIT_NAME", length = 255, nullable = false, unique = true)
	@NonNull
	private String name;

	@Column(name = "UNIT_DESCRIPTION", length = 255)
	@NonNull
	private String unitDescription;

	@Column(name = "DELETE_STATUS", columnDefinition = "boolean default false")
	@NonNull
	private Boolean delStatus;

	@OneToMany(mappedBy = "Unit", fetch = FetchType.LAZY)
	@JsonBackReference
	@JsonIgnore
	private List<ProductDetailUnit> productDetailUnites;
}
