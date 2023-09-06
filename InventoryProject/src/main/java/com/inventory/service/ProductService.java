package com.inventory.service;

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

import com.inventory.config.GlobalExceptionHandler.NotFoundException;
import com.inventory.db1.entities.Product;
import com.inventory.db1.entities.ProductDetail;
import com.inventory.db1.entities.ProductDetailUnit;
import com.inventory.db1.entities.ProductDetailUnitInventory;
import com.inventory.db1.entities.Unit;
import com.inventory.db1.repositories.IProductDetailUnitInventoryRepository;
import com.inventory.db1.repositories.IProductDetailRepository;
import com.inventory.db1.repositories.IProductDetailUnitRepository;
import com.inventory.db1.repositories.IProductRepository;
import com.inventory.requestVM.ProductRequest.CreateProductDetailRequest;
import com.inventory.requestVM.ProductRequest.CreateProductDetailUnitInventoryRequest;
import com.inventory.requestVM.ProductRequest.CreateProductDetailUnitRequest;
import com.inventory.requestVM.ProductRequest.CreateProductRequest;
import com.inventory.requestVM.ProductRequest.ProductDetailRequest;
import com.inventory.requestVM.ProductRequest.ProductDetailUnitRequest;
import com.inventory.requestVM.ProductRequest.ProductFilterRequest;
import com.inventory.requestVM.ProductRequest.ProductRequest;
import com.inventory.requestVM.ProductRequest.UnitRequest;
import com.inventory.requestVM.ProductRequest.UpdateProductDetailRequest;
import com.inventory.requestVM.ProductRequest.UpdateProductDetailUnitInventoryRequest;
import com.inventory.requestVM.ProductRequest.UpdateProductDetailUnitRequest;
import com.inventory.requestVM.ProductRequest.UpdateProductRequest;
import com.inventory.responseVM.ProductDetailResponse;
import com.inventory.responseVM.ProductDetailUnitInventoryResponse;
import com.inventory.responseVM.ProductDetailUnitResponse;
import com.inventory.responseVM.ProductResponse;
import com.inventory.specification.ProductSpecification;

@Service
@Transactional
public class ProductService implements IProductService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private IProductDetailRepository productDetailRepository;

	@Autowired
	private IProductDetailUnitRepository productDetailUnitRepository;

	@Autowired
	private IProductDetailUnitInventoryRepository productDetailUnitInventoryRepository;

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
	@Transactional
	public int createProduct(CreateProductRequest request) {
		ProductRequest productInfor = modelMapper.map(request, ProductRequest.class);
		Product product = modelMapper.map(productInfor, Product.class);
		// Lưu sản phẩm vào cơ sở dữ liệu.
		Product savedProduct = productRepository.save(product);
		// Lưu danh sách chi tiết sản phẩm và các đơn vị chi tiết sản phẩm liên quan.

		for (CreateProductDetailRequest productDetailRq : request.getProductDetails()) {
			ProductDetailRequest productDetailInfor = modelMapper.map(productDetailRq, ProductDetailRequest.class);
			ProductDetail productDetail = modelMapper.map(productDetailInfor, ProductDetail.class);
			productDetail.setProduct(savedProduct);
			ProductDetail savedProductDetail = productDetailRepository.save(productDetail);

			for (CreateProductDetailUnitRequest productDetailUnitRq : productDetailRq.getProductDetailUnits()) {
				ProductDetailUnitRequest productDetailUnitInfor = modelMapper.map(productDetailUnitRq,
						ProductDetailUnitRequest.class);
				ProductDetailUnit productDetailUnit = modelMapper.map(productDetailUnitInfor, ProductDetailUnit.class);
				UnitRequest unitInfor = modelMapper.map(productDetailUnitRq.getUnit(), UnitRequest.class);
				Unit unit = modelMapper.map(unitInfor, Unit.class);
				productDetailUnit.setProductDetail(savedProductDetail);
				productDetailUnit.setUnit(unit);
				ProductDetailUnit savedProductDetailUnit = productDetailUnitRepository.save(productDetailUnit);
				for (CreateProductDetailUnitInventoryRequest productDetailUnitInventoryRq : productDetailUnitRq
						.getProductDetailUnitInventories()) {
					ProductDetailUnitInventory productDetailUnitInventory = modelMapper
							.map(productDetailUnitInventoryRq, ProductDetailUnitInventory.class);
					productDetailUnitInventory.setProductDetailUnit(savedProductDetailUnit);
					productDetailUnitInventoryRepository.save(productDetailUnitInventory);
				}
			}
		}

		if (savedProduct != null) {
			return savedProduct.getId();
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
		Product product = modelMapper.map(request, Product.class);
		// Product entity = productRepository.findById(request.getId()).get();
		var productResult = productRepository.save(product);
		ProductResponse result = modelMapper.map(productResult, ProductResponse.class);
		return result;
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

	@Override
	public int createProductDetail(int productId, CreateProductDetailRequest request) {
		Product product = productRepository.findById(productId).get();
		ProductDetailRequest productDetailInfor = modelMapper.map(request, ProductDetailRequest.class);
		ProductDetail productDetail = modelMapper.map(productDetailInfor, ProductDetail.class);
		productDetail.setProduct(product);
		ProductDetail savedProductDetail = productDetailRepository.save(productDetail);
		for (CreateProductDetailUnitRequest productDetailUnitRq : request.getProductDetailUnits()) {
			ProductDetailUnitRequest productDetailUnitInfor = modelMapper.map(productDetailUnitRq,
					ProductDetailUnitRequest.class);
			ProductDetailUnit productDetailUnit = modelMapper.map(productDetailUnitInfor, ProductDetailUnit.class);
			UnitRequest unitInfor = modelMapper.map(productDetailUnitRq.getUnit(), UnitRequest.class);
			Unit unit = modelMapper.map(unitInfor, Unit.class);
			productDetailUnit.setProductDetail(savedProductDetail);
			productDetailUnit.setUnit(unit);
			ProductDetailUnit savedProductDetailUnit = productDetailUnitRepository.save(productDetailUnit);
			for (CreateProductDetailUnitInventoryRequest productDetailUnitInventoryRq : productDetailUnitRq
					.getProductDetailUnitInventories()) {
				ProductDetailUnitInventory productDetailUnitInventory = modelMapper.map(productDetailUnitInventoryRq,
						ProductDetailUnitInventory.class);
				productDetailUnitInventory.setProductDetailUnit(savedProductDetailUnit);
				productDetailUnitInventoryRepository.save(productDetailUnitInventory);
			}
		}
		if (savedProductDetail != null) {
			return savedProductDetail.getId();
		}
		return 0;
	}

	@Override
	public void createProductDetails(int productId, List<CreateProductDetailRequest> requests) {
		Product product = productRepository.findById(productId).get();
		for (CreateProductDetailRequest productDetailRq : requests) {
			ProductDetailRequest productDetailInfor = modelMapper.map(productDetailRq, ProductDetailRequest.class);
			ProductDetail productDetail = modelMapper.map(productDetailInfor, ProductDetail.class);
			productDetail.setProduct(product);
			ProductDetail savedProductDetail = productDetailRepository.save(productDetail);
			for (CreateProductDetailUnitRequest productDetailUnitRq : productDetailRq.getProductDetailUnits()) {
				ProductDetailUnitRequest productDetailUnitInfor = modelMapper.map(productDetailUnitRq,
						ProductDetailUnitRequest.class);
				ProductDetailUnit productDetailUnit = modelMapper.map(productDetailUnitInfor, ProductDetailUnit.class);
				UnitRequest unitInfor = modelMapper.map(productDetailUnitRq.getUnit(), UnitRequest.class);
				Unit unit = modelMapper.map(unitInfor, Unit.class);
				productDetailUnit.setProductDetail(savedProductDetail);
				productDetailUnit.setUnit(unit);
				ProductDetailUnit savedProductDetailUnit = productDetailUnitRepository.save(productDetailUnit);
				for (CreateProductDetailUnitInventoryRequest productDetailUnitInventoryRq : productDetailUnitRq
						.getProductDetailUnitInventories()) {
					ProductDetailUnitInventory productDetailUnitInventory = modelMapper
							.map(productDetailUnitInventoryRq, ProductDetailUnitInventory.class);
					productDetailUnitInventory.setProductDetailUnit(savedProductDetailUnit);
					productDetailUnitInventoryRepository.save(productDetailUnitInventory);
				}
			}
		}
	}

	@Override
	public int createProductDetailUnit(int productDetailId, CreateProductDetailUnitRequest request) {
		ProductDetail productDetail = productDetailRepository.findById(productDetailId).get();
		ProductDetailUnitRequest productDetailUnitInfor = modelMapper.map(request, ProductDetailUnitRequest.class);
		ProductDetailUnit productDetailUnit = modelMapper.map(productDetailUnitInfor, ProductDetailUnit.class);
		UnitRequest unitInfor = modelMapper.map(request.getUnit(), UnitRequest.class);
		Unit unit = modelMapper.map(unitInfor, Unit.class);
		productDetailUnit.setProductDetail(productDetail);
		productDetailUnit.setUnit(unit);
		ProductDetailUnit savedProductDetailUnit = productDetailUnitRepository.save(productDetailUnit);
		for (CreateProductDetailUnitInventoryRequest productDetailUnitInventoryRq : request
				.getProductDetailUnitInventories()) {
			ProductDetailUnitInventory productDetailUnitInventory = modelMapper.map(productDetailUnitInventoryRq,
					ProductDetailUnitInventory.class);
			productDetailUnitInventory.setProductDetailUnit(savedProductDetailUnit);
			productDetailUnitInventoryRepository.save(productDetailUnitInventory);
		}
		if (savedProductDetailUnit != null) {
			return savedProductDetailUnit.getId();
		}
		return 0;
	}

	@Override
	public void createProductDetailUnits(int productDetailId, List<CreateProductDetailUnitRequest> requests) {
		ProductDetail productDetail = productDetailRepository.findById(productDetailId).get();
		for (CreateProductDetailUnitRequest productDetailUnitRq : requests) {
			ProductDetailUnitRequest productDetailUnitInfor = modelMapper.map(productDetailUnitRq,
					ProductDetailUnitRequest.class);

			ProductDetailUnit productDetailUnit = modelMapper.map(productDetailUnitInfor, ProductDetailUnit.class);
			UnitRequest unitInfor = modelMapper.map(productDetailUnitRq.getUnit(), UnitRequest.class);
			Unit unit = modelMapper.map(unitInfor, Unit.class);
			productDetailUnit.setProductDetail(productDetail);
			productDetailUnit.setUnit(unit);
			ProductDetailUnit savedProductDetailUnit = productDetailUnitRepository.save(productDetailUnit);
			
			for (CreateProductDetailUnitInventoryRequest productDetailUnitInventoryRq : productDetailUnitRq
					.getProductDetailUnitInventories()) {
				ProductDetailUnitInventory productDetailUnitInventory = modelMapper.map(productDetailUnitInventoryRq,
						ProductDetailUnitInventory.class);
				productDetailUnitInventory.setProductDetailUnit(savedProductDetailUnit);
				productDetailUnitInventoryRepository.save(productDetailUnitInventory);
			}
		}
	}

	@Override
	public int createProductDetailUnitInventory(int productDetailUnitId,
			CreateProductDetailUnitInventoryRequest request) {
		ProductDetailUnit productDetailUnit = productDetailUnitRepository.findById(productDetailUnitId).get();

		ProductDetailUnitInventory productDetailUnitInventory = modelMapper.map(request,
				ProductDetailUnitInventory.class);
		productDetailUnitInventory.setProductDetailUnit(productDetailUnit);
		ProductDetailUnitInventory savedProductDetailUnitInventory = productDetailUnitInventoryRepository
				.save(productDetailUnitInventory);
		if (savedProductDetailUnitInventory != null) {
			return savedProductDetailUnitInventory.getId();
		}
		return 0;
	}

	@Override
	public void createProductDetailUnitInventories(int productDetailUnitId,
			List<CreateProductDetailUnitInventoryRequest> requests) {
		ProductDetailUnit productDetailUnit = productDetailUnitRepository.findById(productDetailUnitId).get();
		for (CreateProductDetailUnitInventoryRequest productDetailUnitInventoryRq : requests) {
			ProductDetailUnitInventory productDetailUnitInventory = modelMapper.map(productDetailUnitInventoryRq,
					ProductDetailUnitInventory.class);
			productDetailUnitInventory.setProductDetailUnit(productDetailUnit);
			productDetailUnitInventoryRepository.save(productDetailUnitInventory);
		}
	}

	@Override
	public ProductDetailResponse getProductDetailById(Integer productDetailId) {
		ProductDetail productDetail = productDetailRepository.findById(productDetailId).get();
		ProductDetailResponse result = modelMapper.map(productDetail, ProductDetailResponse.class);
		return result;
	}

	@Override
	public ProductDetailUnitResponse getProductDetailUnitById(Integer productDetailUnitId) {
		ProductDetailUnit productDetailUnit = productDetailUnitRepository.findById(productDetailUnitId).get();
		ProductDetailUnitResponse result = modelMapper.map(productDetailUnit, ProductDetailUnitResponse.class);
		return result;
	}

	@Override
	public ProductDetailUnitInventoryResponse getProductDetailUnitInventoryById(Integer productDetailUnitInventoryId) {
		ProductDetailUnitInventory productDetailUnitInventory = productDetailUnitInventoryRepository.findById(productDetailUnitInventoryId).get();
		ProductDetailUnitInventoryResponse result = modelMapper.map(productDetailUnitInventory, ProductDetailUnitInventoryResponse.class);
		return result;
	}
	
	@Override
	public ProductDetailResponse updateProductDetail(UpdateProductDetailRequest request) {
		ProductDetail productDetail = modelMapper.map(request, ProductDetail.class);
		var productDetailResult = productDetailRepository.save(productDetail);
		ProductDetailResponse result = modelMapper.map(productDetailResult, ProductDetailResponse.class);
		return result;
	}

	@Override
	public ProductDetailUnitResponse updateProductDetailUnit(UpdateProductDetailUnitRequest request) {
		ProductDetailUnit productDetailUnit = modelMapper.map(request, ProductDetailUnit.class);
		var productDetailUnitResult = productDetailUnitRepository.save(productDetailUnit);
		ProductDetailUnitResponse result = modelMapper.map(productDetailUnitResult, ProductDetailUnitResponse.class);
		return result;
	}

	@Override
	public ProductDetailUnitInventoryResponse updateProductDetailUnitInventory(UpdateProductDetailUnitInventoryRequest request) {
		ProductDetailUnitInventory productDetailUnitInventory = modelMapper.map(request, ProductDetailUnitInventory.class);
		var productDetailUnitInventoryResult = productDetailUnitInventoryRepository.save(productDetailUnitInventory);
		ProductDetailUnitInventoryResponse result = modelMapper.map(productDetailUnitInventoryResult, ProductDetailUnitInventoryResponse.class);
		return result;
	}

}
