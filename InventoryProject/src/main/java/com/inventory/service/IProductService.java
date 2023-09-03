package com.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.requestVM.ProductRequest.CreateProductRequest;
import com.inventory.requestVM.ProductRequest.ProductFilterRequest;
import com.inventory.requestVM.ProductRequest.UpdateProductRequest;
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


}
