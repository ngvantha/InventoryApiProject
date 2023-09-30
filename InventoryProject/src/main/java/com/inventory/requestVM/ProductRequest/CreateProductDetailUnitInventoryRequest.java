package com.inventory.requestVM.ProductRequest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProductDetailUnitInventoryRequest {
	private Integer id;

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date inputDate;

	private Double unitPrice;

	private Double retailPrice;

	private Double wholesalePrice;

	private Double salePrice;

	private Double inputQuantity;

	private Double inventoryQuantity;

	private Boolean isStatus;

	private String productDetailBarcode;

}
