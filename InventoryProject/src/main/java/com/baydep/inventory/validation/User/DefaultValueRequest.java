package com.baydep.inventory.validation.User;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.baydep.inventory.validation.ValidDateValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDateValidator.class)
public @interface DefaultValueRequest {
	String message() default "Invalid date format";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
