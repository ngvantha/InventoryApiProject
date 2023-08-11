package com.inventory.validation.Unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.inventory.service.IUnitService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UnitNameExistsValidator implements ConstraintValidator<UnitNameExists, String>{
	@Autowired
	private IUnitService service;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {

		if (StringUtils.isEmpty(name)) {
			return true;
		}

		return !service.isUnitExistsByName(name);
	}
	
}
