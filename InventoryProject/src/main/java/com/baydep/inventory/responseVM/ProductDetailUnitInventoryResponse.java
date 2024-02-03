package com.baydep.inventory.responseVM;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDetailUnitInventoryResponse {

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
