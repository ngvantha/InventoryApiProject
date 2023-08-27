package com.inventory.requestVM.AppRoleRequest;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import com.inventory.validation.Role.RoleNameExists;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateRoleRequest {
	
	//@NotBlank(message = "The UUID mustn't be null value")
	//@Length(max = 255, message = "The UUID's length is max 255 characters")
	@NotNull(message = "The UUID mustn't be null value")
	private UUID id;
	
	@NotBlank(message = "The description mustn't be null value")
	@Length(max = 255, message = "The description's length is max 255 characters")
	private String description;

	@NotBlank(message = "The name mustn't be null value")
	@Length(max = 255, message = "The name's length is max 255 characters")
	@RoleNameExists
	private String name;

	@NotBlank(message = "The normalizedName mustn't be null value")
	@Length(max = 255, message = "The normalizedName's length is max 255 characters")
	private String normalizedName;

	//@NotBlank(message = "The concurrencyStamp mustn't be null value")
	@Length(max = 255, message = "The concurrencyStamp's length is max 255 characters")
	private String concurrencyStamp;
}
