package com.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.db1.entities.Product;
import com.inventory.db1.entities.ProductDetail;
import com.inventory.db1.repositories.IProductRepository;
import com.inventory.db2.entities.AppRole;
import com.inventory.requestVM.ProductRequest.CreateProductRequest;
import com.inventory.requestVM.ProductRequest.ProductFilterRequest;
import com.inventory.requestVM.ProductRequest.UpdateProductRequest;
import com.inventory.responseVM.ProductDetailResponse;
import com.inventory.responseVM.ProductResponse;
import com.inventory.specification.ProductSpecification;


@Service
@Transactional
public class ProductService implements IProductService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IProductRepository productRepository;

	@Override
	public ProductResponse getProductByID(Integer id) {
		Product product = productRepository.findById(id).get();
		ProductResponse result = modelMapper.map(product, ProductResponse.class);
		return result;
	}

	@Override
	public ProductResponse getProductByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createProduct(CreateProductRequest request) {
		Product product = modelMapper.map(request, Product.class);
		var result = productRepository.save(product);
		if (result != null) {
			return result.getId();
		}
		return 0;
	}

	@Override
	public String updateProductNameOnlyProduct(Integer id, String newProductName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductResponse updateProduct(UpdateProductRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteProduct(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProductWithStatus(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int multipleDeleteProduct(List<Integer> ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int multipleDeleteProductWithStatus(List<Integer> ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isProductExistsByID(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isProductExistsByProductName(String ProductName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Page<ProductResponse> getAllProduct(Pageable pageable, String search, ProductFilterRequest filterRequest) {
		Specification<Product> where = ProductSpecification.buildWhere(search, filterRequest);
		Page<Product> entityPages = productRepository.findAll(where, pageable);
		// convert entities --> dtos
		List<ProductResponse> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<ProductResponse>>() {
		}.getType());
		Page<ProductResponse> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());
		return dtoPages;
	}

	@Override
	public String updateEmailOnlyProduct(Integer id, String newEmail) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
