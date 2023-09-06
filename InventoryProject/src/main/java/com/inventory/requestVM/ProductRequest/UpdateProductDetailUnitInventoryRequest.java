package com.inventory.requestVM.ProductRequest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inventory.validation.Product.ProductDetailUnitInventoryIdExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateProductDetailUnitInventoryRequest {
	@ProductDetailUnitInventoryIdExists(message = "Product detail unit inventory id not exist already!")
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
