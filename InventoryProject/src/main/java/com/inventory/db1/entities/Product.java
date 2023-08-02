package com.inventory.db1.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "PRODUCTS", catalog = "WAREHOUSE")
@NoArgsConstructor
@Data
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private Integer id;
	@Column(name = "PRODUCT_NAME", length = 255, nullable = false, unique = true)
	@NonNull
	private String name;
	@Column(name = "VIEWCOUNT")
	@NonNull
	private Integer viewCount;
	@Column(name = "SEOALIAS", length = 255)
	@NonNull
	private String seoAlias;
	@Column(name = "IS_STATUS", columnDefinition = "boolean default true")
	@NonNull
	private Boolean isStatus;
	@Column(name = "DELETE_STATUS", columnDefinition = "boolean default false")
	@NonNull
	private Boolean delStatus;
	
	@OneToMany(mappedBy="product")
	private List<ProductDetail> ProductDetailes;
}
