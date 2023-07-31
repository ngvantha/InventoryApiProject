package com.inventory.db1.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "UNITS", catalog = "WAREHOUSE")
@Data
public class Unit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UNIT_ID")
	private Integer id;
	
	@Column(name = "UNIT_NAME", length = 255, nullable = false, unique = true)
	@NonNull
	private String unitName;
	
	@Column(name = "UNIT_DESCRIPTION", length = 255)
	@NonNull
	private String unitDescription;
	
	@Column(name = "DELETE_STATUS", columnDefinition = "boolean default false")
	@NonNull
	private Boolean delStatus;
	
	@OneToMany(mappedBy ="Unit")
	private List<ProductDetailUnit> productDetailUnites;
}
