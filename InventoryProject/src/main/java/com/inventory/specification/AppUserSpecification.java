package com.inventory.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.inventory.db2.entities.AppUser;
import com.inventory.requestVM.AppUserRequest.UserFilterRequest;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class AppUserSpecification {
	@SuppressWarnings("deprecation")
	public static Specification<AppUser> buildWhere(String search, UserFilterRequest filterRequest) {
		Specification<AppUser> where = null;

		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomSpecificationAppUser firstName = new CustomSpecificationAppUser("firstName", search);
			CustomSpecificationAppUser lastName = new CustomSpecificationAppUser("lastName", search);
			CustomSpecificationAppUser userName = new CustomSpecificationAppUser("userName", search);
			CustomSpecificationAppUser email = new CustomSpecificationAppUser("email", search);
			where = Specification.where(firstName).or(lastName).or(userName).or(email);
		}
		
		//To do filter
		
		// if there is filter by createdDate
		if (filterRequest != null && filterRequest.getCreatedMaxDate() != null) {
			CustomSpecificationAppUser createdMaxDate = new CustomSpecificationAppUser("createdMaxDate", filterRequest.getCreatedMaxDate());
			if (where == null) {
				where = createdMaxDate;
			} else {
				where = where.and(createdMaxDate);
			}
		}
		
		// if there is filter by DOB
		if (filterRequest != null && filterRequest.getCreatedMinDate() != null) {
			CustomSpecificationAppUser createdMinDate = new CustomSpecificationAppUser("createdMinDate", filterRequest.getCreatedMinDate());
			if (where == null) {
				where = createdMinDate;
			} else {
				where = where.and(createdMinDate);
			}
		}
		
		// if there is filter by DOB
		if (filterRequest != null && filterRequest.getLockoutEnabled() != null) {
			CustomSpecificationAppUser lockoutEnabled = new CustomSpecificationAppUser("lockoutEnabled", filterRequest.getLockoutEnabled());
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
class CustomSpecificationAppUser implements Specification<AppUser> {

	@NonNull
	private String field;
	@NonNull
	private Object value;

	@Override
	public Predicate toPredicate(Root<AppUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if (field.equalsIgnoreCase("firstName")) {
			return criteriaBuilder.like(root.get("firstName"), "%" + value.toString() + "%");
		}

		if (field.equalsIgnoreCase("lastName")) {
			return criteriaBuilder.like(root.get("lastName"), "%" + value.toString() + "%");
		}
		
		if (field.equalsIgnoreCase("userName")) {
			return criteriaBuilder.like(root.get("userName"), "%" + value.toString() + "%");
		}
		
		if (field.equalsIgnoreCase("email")) {
			return criteriaBuilder.like(root.get("email"), "%" + value.toString() + "%");
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