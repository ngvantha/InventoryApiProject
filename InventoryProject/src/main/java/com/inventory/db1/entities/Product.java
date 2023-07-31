package com.inventory.db1.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "PRODUCTS", catalog = "WAREHOUSE")
@Data
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private Integer PRODUCT_ID;
	@Column(name = "PRODUCT_NAME", length = 255, nullable = false, unique = true)
	@NonNull
	private String PRODUCT_NAME;
	@Column(name = "VIEWCOUNT")
	@NonNull
	private Integer VIEWCOUNT;
	@Column(name = "SEOALIAS", length = 255)
	@NonNull
	private String SEOALIAS;
	@Column(name = "IS_STATUS", columnDefinition = "boolean default true")
	@NonNull
	private Boolean IS_STATUS;
	@Column(name = "DELETE_STATUS", columnDefinition = "boolean default false")
	@NonNull
	private Boolean DELETE_STATUS;
	
	@OneToMany(mappedBy="producdetail")
	private List<ProductDetail> ProductDetailes;
}
