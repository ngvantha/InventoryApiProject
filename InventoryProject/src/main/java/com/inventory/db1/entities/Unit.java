package com.inventory.db1.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.Builder;
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
	
	@OneToMany(mappedBy ="Unit")
	private List<ProductDetailUnit> productDetailUnites;
}
