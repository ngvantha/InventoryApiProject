package com.inventory.db1.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "PRODUCT_DETAILS")
@NoArgsConstructor
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

	@Column(name = "PRODUCT_CODE", length = 255)
	@NonNull
	private String productCode;

	@Column(name = "IS_STATUS", columnDefinition = "boolean default true")
	@NonNull
	private Boolean isStatus;

	@Column(name = "PRODUCT_DETAIL_DESCRIPTION")
	@NonNull
	private String productDetailDescription;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	@JsonIgnore
	@JsonManagedReference
	private Product product;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productDetail", fetch = FetchType.LAZY)
	private List<ProductDetailUnit> productDetailUnits;
}
