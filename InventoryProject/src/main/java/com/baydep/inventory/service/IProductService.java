package com.baydep.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.baydep.inventory.config.GlobalExceptionHandler.NotFoundException;
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

	public String updateProducNameOnlyProduct(Integer id, String newEmail);

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
