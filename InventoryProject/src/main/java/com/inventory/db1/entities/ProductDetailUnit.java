package com.inventory.db1.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "PRODUCT_DETAIL_UNITS", catalog = "WAREHOUSE")
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

//	@Column(name = "PRODUCT_DETAIL_ID")
//	@NonNull
//	private Integer PRODUCT_DETAIL_ID;
//
//	@Column(name = "UNIT_ID")
//	@NonNull
//	private Integer UNIT_ID;

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
	private Boolean ratioToUnit;

	@Column(name = "IS_STATUS", columnDefinition = "boolean default true")
	@NonNull
	private Boolean isStatus;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_DETAIL_ID")
	private ProductDetail productDetail;

	@ManyToOne
	@JoinColumn(name = "UNIT_ID")
	private Unit Unit;

	@OneToMany(mappedBy = "productDetailUnit")
	private List<ProductDetailUnitInventory> productDetailUnitInventories;

	public enum RatioType {
		Default, Percent
	}
}
