package com.inventory.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.inventory.db1.entities.Unit;
import com.inventory.requestVM.UnitFilterRequest;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class UnitSpecification {
	@SuppressWarnings("deprecation")
	public static Specification<Unit> buildWhere(String search, UnitFilterRequest filterRequest) {
		Specification<Unit> where = null;

		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomSpecification name = new CustomSpecification("name", search);
			CustomSpecification unitDescription = new CustomSpecification("unitDescription", search);
			where = Specification.where(name).or(unitDescription);
		}

		// if there is filter by min id
		if (filterRequest != null && filterRequest.getMinId() != null) {
			CustomSpecification minId = new CustomSpecification("minId", filterRequest.getMinId());
			if (where == null) {
				where = minId;
			} else {
				where = where.and(minId);
			}
		}

		// if there is filter by max id
		if (filterRequest != null && filterRequest.getMaxId() != null) {
			CustomSpecification maxId = new CustomSpecification("maxId", filterRequest.getMaxId());
			if (where == null) {
				where = maxId;
			} else {
				where = where.and(maxId);
			}
		}

		// if there is filter by status
		if (filterRequest != null && filterRequest.getStatus() != null) {
			CustomSpecification status = new CustomSpecification("delStatus", filterRequest.getStatus());
			if (where == null) {
				where = status;
			} else {
				where = where.and(status);
			}
		}

		return where;
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
	public Predicate toPredicate(Root<Unit> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if (field.equalsIgnoreCase("name")) {
			return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
		}

		if (field.equalsIgnoreCase("minId")) {
			return criteriaBuilder.greaterThanOrEqualTo(root.get("id"), value.toString());
		}

		if (field.equalsIgnoreCase("maxId")) {
			return criteriaBuilder.lessThanOrEqualTo(root.get("id"), value.toString());
		}

		if (field.equalsIgnoreCase("unitDescription")) {
			return criteriaBuilder.like(root.get("unitDescription"), "%" + value.toString() + "%");
		}
		
		if (field.equalsIgnoreCase("delStatus")) {
			return criteriaBuilder.equal(root.get("delStatus"), value);
		}

		return null;
	}
}
