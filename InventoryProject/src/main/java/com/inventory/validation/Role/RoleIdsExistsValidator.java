package com.inventory.validation.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.inventory.db1.repositories.IUnitRepository;
import com.inventory.db2.repositories.IAppRoleRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleIdsExistsValidator implements ConstraintValidator<RoleIdsExists, List<UUID>> {
	@Autowired
	private IAppRoleRepository repository;

	@Override
	public boolean isValid(List<UUID> ids, ConstraintValidatorContext constraintValidatorContext) {

        if (ids == null || ids.isEmpty()) {
            return true; 
        }
        
        List<UUID> nonExistingIds = new ArrayList<>();
        for (UUID id : ids) {
            if (!repository.existsById(id)) {
                nonExistingIds.add(id);
            }
        }

        if (!nonExistingIds.isEmpty()) {
        	constraintValidatorContext.disableDefaultConstraintViolation();
        	constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "IDs " + nonExistingIds + " do not exist."
            ).addConstraintViolation();
            return false;
        }
        return true; 

	}
}
