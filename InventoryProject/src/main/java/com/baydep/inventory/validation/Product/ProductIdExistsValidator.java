package com.baydep.inventory.validation.Product;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.baydep.inventory.db1.repositories.IProductRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductIdExistsValidator implements ConstraintValidator<ProductIdExists, Integer>{
	@Autowired
	private IProductRepository repository;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {

		if (StringUtils.isEmpty(id)) {
			return true;
		}

		return repository.existsById(id);
	}
	
}
