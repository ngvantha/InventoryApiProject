package com.inventory.responseVM;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Data
@NoArgsConstructor
public class UserResponse {
	
	@NonNull
	private UUID id;
	
	@NonNull
	private String description;

	private String name;

	private String normalizedName;

	private String concurrencyStamp;
}
