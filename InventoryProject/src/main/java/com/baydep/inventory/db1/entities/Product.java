package com.baydep.inventory.db1.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "PRODUCTS")
@NoArgsConstructor
@Data
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private Integer id;

	@Column(name = "PRODUCT_NAME", length = 255, unique = true)
	@NonNull
	private String productName;

	@Column(name = "VIEWCOUNT")
	@NonNull
	private Integer viewCount;

	@Column(name = "SEOALIAS", length = 255)
	@NonNull
	private String seoAlias;

	@Column(name = "IS_STATUS")
	@NonNull
	private Boolean isStatus;

	@Column(name = "DELETE_STATUS")
	@NonNull
	private Boolean delStatus;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY)
	private List<ProductDetail> productDetails;
}
