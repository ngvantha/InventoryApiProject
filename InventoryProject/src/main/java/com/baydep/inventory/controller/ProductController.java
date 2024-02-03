package com.baydep.inventory.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baydep.inventory.config.GlobalExceptionHandler.ApiMessageResponse;
import com.baydep.inventory.db1.repositories.IUnitRepository;
import com.baydep.inventory.requestVM.ProductRequest.CreateProductDetailRequest;
import com.baydep.inventory.requestVM.ProductRequest.CreateProductDetailUnitInventoryRequest;
import com.baydep.inventory.requestVM.ProductRequest.CreateProductDetailUnitRequest;
import com.baydep.inventory.requestVM.ProductRequest.CreateProductRequest;
import com.baydep.inventory.requestVM.ProductRequest.ProductFilterRequest;
import com.baydep.inventory.requestVM.ProductRequest.UpdateProductDetailRequest;
import com.baydep.inventory.requestVM.ProductRequest.UpdateProductDetailUnitInventoryRequest;
import com.baydep.inventory.requestVM.ProductRequest.UpdateProductDetailUnitRequest;
import com.baydep.inventory.requestVM.ProductRequest.UpdateProductRequest;
import com.baydep.inventory.responseVM.ProductDetailResponse;
import com.baydep.inventory.responseVM.ProductDetailUnitInventoryResponse;
import com.baydep.inventory.responseVM.ProductDetailUnitResponse;
import com.baydep.inventory.responseVM.ProductResponse;
import com.baydep.inventory.responseVM.UserResponse;
import com.baydep.inventory.service.IProductService;
import com.baydep.inventory.service.IUnitService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "api/v1/products")
@Validated
@Log4j2
public class ProductController {

	@Autowired
	private IProductService service;
	
	@Autowired
	private IUnitService unitService;

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
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductResponse> getProducById(@PathVariable(name = "id") int id) {
		ProductResponse response = service.getProductByID(id);
		log.info(response);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/{id}/productdetails")
	public ResponseEntity<?> createProductDetail(@PathVariable(name = "id") int id,
			@RequestBody CreateProductDetailRequest request) {
		log.info(request);
		var result = service.createProductDetail(id, request);
		log.info(result);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/{id}/productdetails/addmore")
	public ResponseEntity<?> createProductDetails(@PathVariable(name = "id") int id,
			@RequestBody List<CreateProductDetailRequest> requests) {
		log.info(requests);
		service.createProductDetails(id, requests);
		return ResponseEntity.ok(new ApiMessageResponse("Success", "Successfully added new product details!!!"));
	}
	
	@GetMapping(value = "/productdetails/{id}")
	public ResponseEntity<ProductDetailResponse> getProducDetailById(@PathVariable(name = "id") int id) {
		ProductDetailResponse response = service.getProductDetailById(id);
		log.info(response);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/productdetails/{id}/productDetailUnits")
	public ResponseEntity<?> createProductDetailUnit(@PathVariable(name = "id") int id,
			@RequestBody CreateProductDetailUnitRequest request) {
		log.info(request);
		var result = service.createProductDetailUnit(id, request);
		log.info(result);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/productdetails/{id}/productDetailUnits/addmore")
	public ResponseEntity<?> createProductDetailUnits(@PathVariable(name = "id") int id,
			@RequestBody List<CreateProductDetailUnitRequest> requests) {
		log.info(requests);
		service.createProductDetailUnits(id, requests);
		return ResponseEntity.ok(new ApiMessageResponse("Success", "Successfully added new product detail units!!!"));

	}
	
	@GetMapping(value = "/productdetails/productDetailUnits/{id}")
	public ResponseEntity<ProductDetailUnitResponse> getProducDetailUnitById(@PathVariable(name = "id") int id) {
		ProductDetailUnitResponse response = service.getProductDetailUnitById(id);
		log.info(response);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/productdetails/productDetailUnits/{id}/productDetailUnitInventories")
	public ResponseEntity<?> createProductDetailUnitInventory(@PathVariable(name = "id") int id,
			@RequestBody CreateProductDetailUnitInventoryRequest request) {
		log.info(request);
		var result = service.createProductDetailUnitInventory(id, request);
		log.info(result);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/productdetails/productDetailUnits/{id}/productDetailUnitInventories/addmore")
	public ResponseEntity<?> createProductDetailUnitInventories(@PathVariable(name = "id") int id,
			@RequestBody List<CreateProductDetailUnitInventoryRequest> requests) {
		log.info(requests);
		service.createProductDetailUnitInventories(id, requests);
		return ResponseEntity.ok(new ApiMessageResponse("Success", "Successfully added new product detail unit inventories!!!"));
	}
	
	@GetMapping(value = "/productdetails/productDetailUnits/productDetailUnitInventories/{id}")
	public ResponseEntity<ProductDetailUnitInventoryResponse> getProducDetailUnitInventoryById(@PathVariable(name = "id") int id) {
		ProductDetailUnitInventoryResponse response = service.getProductDetailUnitInventoryById(id);
		log.info(response);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping()
	public ResponseEntity<?> UpdateProduct(@RequestBody @Valid UpdateProductRequest request) {
		log.info(request);
		var result = service.updateProduct(request);
		log.info(result);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/productdetails")
	public ResponseEntity<?> UpdateProductDetail(@RequestBody @Valid UpdateProductDetailRequest request) {
		log.info(request);
		var result = service.updateProductDetail(request);
		log.info(result);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/productdetails/productDetailUnits")
	public ResponseEntity<?> UpdateProductDetailUnit(@RequestBody @Valid UpdateProductDetailUnitRequest request) {
		log.info(request);
		boolean check =  unitService.isUnitExistsByID(request.getUnit().getId());
		if(!check) {
			return ResponseEntity.badRequest().body(new ApiMessageResponse("Update Product Detail Unit Faled!!!", "Unit id not exisits")); 
		}
		var result = service.updateProductDetailUnit(request);
		log.info(result);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/productdetails/productDetailUnits/productDetailUnitInventories")
	public ResponseEntity<?> UpdateProductDetailUnitInventory(@RequestBody @Valid UpdateProductDetailUnitInventoryRequest request) {
		log.info(request);
		var result = service.updateProductDetailUnitInventory(request);
		log.info(result);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") int id) {
		var result = service.deleteProduct(id);
		log.info(result);
		return ResponseEntity.ok(result);
	}
	
}
