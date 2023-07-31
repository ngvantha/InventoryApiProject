package com.inventory.db1.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
	private Integer UNIT_ID;
	
	@Column(name = "UNIT_NAME", length = 255, nullable = false, unique = true)
	@NonNull
	private String UNIT_NAME;
	
	@Column(name = "UNIT_DESCRIPTION", length = 255)
	@NonNull
	private String UNIT_DESCRIPTION;
	
	@Column(name = "DELETE_STATUS", columnDefinition = "boolean default false")
	@NonNull
	private Boolean DELETE_STATUS;
	
	@OneToMany(mappedBy ="Unit")
	private List<ProductDetailUnit> productDetailUnites;
}
