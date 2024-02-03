package com.baydep.inventory.validation.Unit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.baydep.inventory.db1.repositories.IUnitRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UnitIdsExistsValidator implements ConstraintValidator<UnitIdsExists, List<Integer>> {
	@Autowired
	private IUnitRepository repository;

	@Override
	public boolean isValid(List<Integer> ids, ConstraintValidatorContext constraintValidatorContext) {

        if (ids == null || ids.isEmpty()) {
            return true; 
        }
        
        List<Integer> nonExistingIds = new ArrayList<>();
        for (Integer id : ids) {
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
