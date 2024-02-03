package com.baydep.inventory.validation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.baydep.inventory.db1.repositories.IUnitRepository;
import com.baydep.inventory.db2.repositories.IAppRoleRepository;
import com.baydep.inventory.service.IUnitService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleNameExistsValidator implements ConstraintValidator<RoleNameExists, String>{
	@Autowired
	private IAppRoleRepository repository;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {

		if (StringUtils.isEmpty(name)) {
			return true;
		}

		return !repository.existsByName(name);
	}
	
}
