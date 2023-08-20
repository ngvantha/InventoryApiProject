package com.inventory.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.inventory.db2.entities.AppRoles;
import com.inventory.requestVM.UnitRequest.AppRoleFilterRequest;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class AppRoleSpecification {
	@SuppressWarnings("deprecation")
	public static Specification<AppRoles> buildWhere(String search, AppRoleFilterRequest filterRequest) {
		Specification<AppRoles> where = null;

		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomSpecificationAppRole name = new CustomSpecificationAppRole("name", search);
			CustomSpecificationAppRole appRoleDescription = new CustomSpecificationAppRole("description", search);
			where = Specification.where(name).or(appRoleDescription);
		}
		return where;
	}
}

@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecificationAppRole implements Specification<AppRoles> {

	@NonNull
	private String field;
	@NonNull
	private Object value;

	@Override
	public Predicate toPredicate(Root<AppRoles> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if (field.equalsIgnoreCase("name")) {
			return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
		}

		if (field.equalsIgnoreCase("description")) {
			return criteriaBuilder.like(root.get("description"), "%" + value.toString() + "%");
		}

		return null;
	}
}