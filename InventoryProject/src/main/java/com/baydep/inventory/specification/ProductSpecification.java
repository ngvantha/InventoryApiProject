package com.baydep.inventory.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.baydep.inventory.db1.entities.Product;
import com.baydep.inventory.requestVM.ProductRequest.ProductFilterRequest;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class ProductSpecification {
	@SuppressWarnings("deprecation")
	public static Specification<Product> buildWhere(String search, ProductFilterRequest filterRequest) {
		Specification<Product> where = null;

		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
//			CustomSpecificationProduct firstName = new CustomSpecificationProduct("firstName", search);
//			CustomSpecificationProduct lastName = new CustomSpecificationProduct("lastName", search);
//			CustomSpecificationProduct userName = new CustomSpecificationProduct("userName", search);
			CustomSpecificationProduct name = new CustomSpecificationProduct("name", search);
			//where = Specification.where(firstName).or(lastName).or(userName).or(email);
			where = Specification.where(name);
		}
		
		//To do filter
		
		// if there is filter by createdDate
		if (filterRequest != null && filterRequest.getCreatedMaxDate() != null) {
			CustomSpecificationProduct createdMaxDate = new CustomSpecificationProduct("createdMaxDate", filterRequest.getCreatedMaxDate());
			if (where == null) {
				where = createdMaxDate;
			} else {
				where = where.and(createdMaxDate);
			}
		}
		
		// if there is filter by DOB
		if (filterRequest != null && filterRequest.getCreatedMinDate() != null) {
			CustomSpecificationProduct createdMinDate = new CustomSpecificationProduct("createdMinDate", filterRequest.getCreatedMinDate());
			if (where == null) {
				where = createdMinDate;
			} else {
				where = where.and(createdMinDate);
			}
		}
		
		if (filterRequest != null && filterRequest.getLockoutEnabled() != null) {
			CustomSpecificationProduct lockoutEnabled = new CustomSpecificationProduct("lockoutEnabled", filterRequest.getLockoutEnabled());
			if (where == null) {
				where = lockoutEnabled;
			} else {
				where = where.and(lockoutEnabled);
			}
		}
		return where;
	}
}

@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecificationProduct implements Specification<Product> {

	@NonNull
	private String field;
	@NonNull
	private Object value;

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

//		if (field.equalsIgnoreCase("firstName")) {
//			return criteriaBuilder.like(root.get("firstName"), "%" + value.toString() + "%");
//		}
//
//		if (field.equalsIgnoreCase("lastName")) {
//			return criteriaBuilder.like(root.get("lastName"), "%" + value.toString() + "%");
//		}
//		
//		if (field.equalsIgnoreCase("userName")) {
//			return criteriaBuilder.like(root.get("userName"), "%" + value.toString() + "%");
//		}
		
		if (field.equalsIgnoreCase("name")) {
			return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
		}
		
		//To do
		if (field.equalsIgnoreCase("createdMaxDate")) {
			return criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"), value.toString());
		}
		
		if (field.equalsIgnoreCase("createdMinDate")) {
			return criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), value.toString());
		}
		
		if (field.equalsIgnoreCase("lockoutEnabled")) {
			return criteriaBuilder.equal(root.get("lockoutEnabled"), value);
		}
		
		return null;
	}
}