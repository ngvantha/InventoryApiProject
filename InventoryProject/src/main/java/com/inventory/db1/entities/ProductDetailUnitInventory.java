package com.inventory.db1.entities;

import java.io.Serializable;
import java.util.Date;

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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "PRODUCT_DETAIL_UNIT_INVENTORIES")
@NoArgsConstructor
@Data
public class ProductDetailUnitInventory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_DETAIL_UNIT_INVENTORIE_ID")
	private Integer id;

	@Column(name = "INPUT_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	@NonNull
	private Date inputDate;

	@Column(name = "UNIT_PRICE")
	@NonNull
	private Double unitPrice;

	@Column(name = "RETAIL_PRICE")
	@NonNull
	private Double retailPrice;

	@Column(name = "WHOLESALE_PRICE")
	@NonNull
	private Double wholesalePrice;

	@Column(name = "SALE_PRICE")
	@NonNull
	private Double salePrice;

	@Column(name = "INPUT_QUANTITY")
	@NonNull
	private Double inputQuantity;

	@Column(name = "INVENTORY_QUANTITY")
	@NonNull
	private Double inventoryQuantity;

	@Column(name = "IS_STATUS", columnDefinition = "boolean default true")
	@NonNull
	private Boolean isStatus;

	@Column(name = "PRODUCT_DETAIL_BARCODE", length = 255)
	@NonNull
	private String productDetailBarcode;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_DETAIL_UNIT_ID", nullable = false)
	private ProductDetailUnit productDetailUnit;
}
