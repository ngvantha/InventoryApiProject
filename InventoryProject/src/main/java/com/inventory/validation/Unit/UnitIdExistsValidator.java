package com.inventory.validation.Unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.inventory.db1.repositories.IUnitRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UnitIdExistsValidator implements ConstraintValidator<UnitIdExists, Integer> {
	@Autowired
	private IUnitRepository repository;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {

		if (StringUtils.isEmpty(id)) {
			return true;
		}

		return repository.existsById(id);
	}
}
