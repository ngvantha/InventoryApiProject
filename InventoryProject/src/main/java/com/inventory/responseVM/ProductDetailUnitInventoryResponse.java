package com.inventory.responseVM;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProductDetailUnitInventoryResponse {

	private Integer id;

	private Date inputDate;

	private Double unitPrice;

	private Double retailPrice;

	private Double wholesalePrice;

	private Double salePrice;

	private Double inputQuantity;

	private Double inventoryQuantity;

	private Boolean isStatus;

	private String productDetailBarcode;
	
	//private ProductDetailUnitResponse productDetailUnit;
}
