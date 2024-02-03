package com.baydep.inventory.db1.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "PRODUCT_DETAIL_UNITS")
@NoArgsConstructor
@Data
public class ProductDetailUnit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_DETAIL_UNIT_ID")
	private Integer id;

	@Column(name = "IS_MAIN")
	@NonNull
	private Boolean isMain;

	@Column(name = "CONVERSIONRATIO")
	@NonNull
	private Boolean conversionratio;

	@Column(name = "RATIO_TYPE")
	@Enumerated(EnumType.STRING)
	@NonNull
	private RatioType ratioType;

	@Column(name = "RATIO_TO_UNIT")
	@NonNull
	private Integer ratioToUnit;

	@Column(name = "IS_STATUS", columnDefinition = "boolean default true")
	@NonNull
	private Boolean isStatus;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_DETAIL_ID", nullable = false)
	private ProductDetail productDetail;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "UNIT_ID", nullable = false)
	private Unit Unit;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productDetailUnit", fetch = FetchType.LAZY)
	private List<ProductDetailUnitInventory> productDetailUnitInventories;

	public enum RatioType {
		Default, Percent
	}
}
