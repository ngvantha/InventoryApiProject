package com.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.config.GlobalExceptionHandler.NotFoundException;
import com.inventory.requestVM.ProductRequest.CreateProductDetailRequest;
import com.inventory.requestVM.ProductRequest.CreateProductDetailUnitInventoryRequest;
import com.inventory.requestVM.ProductRequest.CreateProductDetailUnitRequest;
import com.inventory.requestVM.ProductRequest.CreateProductRequest;
import com.inventory.requestVM.ProductRequest.ProductFilterRequest;
import com.inventory.requestVM.ProductRequest.UpdateProductDetailRequest;
import com.inventory.requestVM.ProductRequest.UpdateProductDetailUnitInventoryRequest;
import com.inventory.requestVM.ProductRequest.UpdateProductDetailUnitRequest;
import com.inventory.requestVM.ProductRequest.UpdateProductRequest;
import com.inventory.responseVM.ProductDetailResponse;
import com.inventory.responseVM.ProductDetailUnitInventoryResponse;
import com.inventory.responseVM.ProductDetailUnitResponse;
import com.inventory.responseVM.ProductResponse;

public interface IProductService {

	public ProductResponse getProductByID(Integer id);

	public ProductResponse getProductByName(String name);

	public int createProduct(CreateProductRequest request);

	public String updateProductNameOnlyProduct(Integer id, String newProductName);

	public ProductResponse updateProduct(UpdateProductRequest request);

	public int deleteProduct(Integer id);

	public int deleteProductWithStatus(Integer id);

	public int multipleDeleteProduct(List<Integer> ids);

	public int multipleDeleteProductWithStatus(List<Integer> ids);

	public boolean isProductExistsByID(Integer id);

	public boolean isProductExistsByProductName(String ProductName);

	public Page<ProductResponse> getAllProduct(Pageable pageable, String search, ProductFilterRequest filterRequest);

	public String updateEmailOnlyProduct(Integer id, String newEmail);

	public int createProductDetail(int prductId, CreateProductDetailRequest request);

	public void createProductDetails(int prductId, List<CreateProductDetailRequest> requests);

	public int createProductDetailUnit(int productDetailId, CreateProductDetailUnitRequest request);

	public void createProductDetailUnits(int productDetailId, List<CreateProductDetailUnitRequest> requests);

	public int createProductDetailUnitInventory(int productDetailUnitId,
			CreateProductDetailUnitInventoryRequest request);

	public void createProductDetailUnitInventories(int productDetailUnitId,
			List<CreateProductDetailUnitInventoryRequest> requests);
	
	public ProductDetailResponse getProductDetailById(Integer productDetailId);
	
	public ProductDetailUnitResponse getProductDetailUnitById(Integer productDetailUnitId);
	
	public ProductDetailUnitInventoryResponse getProductDetailUnitInventoryById(Integer productDetailUnitInventoryId);
	
	public ProductDetailResponse updateProductDetail(UpdateProductDetailRequest request);
	
	public ProductDetailUnitResponse updateProductDetailUnit(UpdateProductDetailUnitRequest request);
	
	public ProductDetailUnitInventoryResponse updateProductDetailUnitInventory(UpdateProductDetailUnitInventoryRequest request);

}
