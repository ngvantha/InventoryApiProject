package com.inventory.validation.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.inventory.db1.repositories.IProductRepository;
import com.inventory.db1.repositories.IUnitRepository;
import com.inventory.db2.repositories.IAppRoleRepository;
import com.inventory.service.IUnitService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductNameExistsValidator implements ConstraintValidator<ProductNameExists, String>{
	@Autowired
	private IProductRepository repository;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {

		if (StringUtils.isEmpty(name)) {
			return true;
		}

		return !repository.existsByProductName(name);
	}
	
}
