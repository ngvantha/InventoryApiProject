package com.inventory.db1.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "PRODUCT_DETAILS", catalog = "WAREHOUSE")
@Data
public class ProductDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_DETAIL_ID")
	private Integer id;
	
//	@Column(name = "PRODUCT_ID", nullable = false)
//	@NonNull
//	private Integer PRODUCT_ID;
	
	@Column(name = "PRODUCT_CODE", length = 255)
	@NonNull
	private String productCode;
	
	@Column(name = "IS_STATUS", columnDefinition = "boolean default true")
	@NonNull
	private Boolean isStatus;
	
	@Column(name = "PRODUCT_DETAIL_DESCRIPTION")
	@NonNull
	private String productDetailDescription;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	
	@OneToMany(mappedBy ="productDetail")
	private List<ProductDetailUnit> productDetailUnites;
}
