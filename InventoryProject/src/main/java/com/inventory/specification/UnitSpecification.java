package com.inventory.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.inventory.db1.entities.Unit;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class UnitSpecification {
	@SuppressWarnings("deprecation")
	public static Specification<Unit> buildWhere(String search) {
		
		if (StringUtils.isEmpty(search)) {
			return null;
		}
		
		search = search.trim();
		
		CustomSpecification name = new CustomSpecification("name", search);

		return Specification.where(name);
	}
}
@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecification implements Specification<Unit> {

	@NonNull
	private String field;
	@NonNull
	private Object value;

	@Override
	public Predicate toPredicate(
			Root<Unit> root, 
			CriteriaQuery<?> query, 
			CriteriaBuilder criteriaBuilder) {

		if (field.equalsIgnoreCase("name")) {
			return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
		}

		return null;
	}
}
