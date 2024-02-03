package com.baydep.inventory.validation.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.baydep.inventory.db1.repositories.IUnitRepository;
import com.baydep.inventory.db2.entities.AppUser;
import com.baydep.inventory.requestVM.AppUserRequest.SignupRequest;
import com.baydep.inventory.validation.ValidDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DefaultValueRequestValidator implements ConstraintValidator<DefaultValueRequest, String> {

	
	private String collum;
	@Override
    public void initialize(DefaultValueRequest constraintAnnotation) {
        
    }

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

		if (StringUtils.isEmpty(value)) {
			//SignupRequest entity = SignupRequest.class;
			
		}
		
		return true;
	}
}
