package com.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.requestVM.ProductRequest.CreateProductRequest;
import com.inventory.requestVM.ProductRequest.ProductFilterRequest;
import com.inventory.responseVM.ProductResponse;
import com.inventory.service.IProductService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "api/v1/products")
@Validated
@Log4j2
public class ProductController {

	@Autowired
	private IProductService service;
	
	@GetMapping
	public ResponseEntity<Page<ProductResponse>> getAllProducts(Pageable pageable,
			@RequestParam(value = "search", required = false) String search, ProductFilterRequest filterRequest) {
		Page<ProductResponse> response = service.getAllProduct(pageable, search, filterRequest);
		log.info(response);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping()
	public ResponseEntity<?> createProduct(@RequestBody @Valid CreateProductRequest request) {
		log.info(request);
		var result = service.createProduct(request);
		log.info(result);
		return ResponseEntity.ok(result);
	}
	
}
