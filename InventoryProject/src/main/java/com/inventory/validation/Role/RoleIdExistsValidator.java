package com.inventory.validation.Role;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.inventory.db1.repositories.IUnitRepository;
import com.inventory.db2.repositories.IAppRoleRepository;
import com.inventory.service.IUnitService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleIdExistsValidator implements ConstraintValidator<RoleIdExists, UUID>{
	@Autowired
	private IAppRoleRepository repository;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(UUID id, ConstraintValidatorContext constraintValidatorContext) {

		if (StringUtils.isEmpty(id)) {
			return true;
		}

		return !repository.existsById(id);
	}
	
}
