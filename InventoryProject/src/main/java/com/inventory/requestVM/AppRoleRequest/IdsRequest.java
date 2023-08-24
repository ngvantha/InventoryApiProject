package com.inventory.requestVM.AppRoleRequest;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public class IdsRequest {
	@NotBlank(message = "The UUID mustn't be null value")
	private List<UUID> ids;
}
