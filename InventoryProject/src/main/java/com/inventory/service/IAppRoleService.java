package com.inventory.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.requestVM.AppRoleRequest.RoleFilterRequest;
import com.inventory.requestVM.AppRoleRequest.CreateRoleRequest;
import com.inventory.requestVM.AppRoleRequest.UpdateRoleRequest;
import com.inventory.responseVM.RoleResponse;

public interface IAppRoleService {

	public RoleResponse getRoleByID(UUID id);

	public RoleResponse getRoleByName(String name);

	public UUID createRole(CreateRoleRequest request);

	public String updateNameOnlyRole(UUID id, String newName);

	public RoleResponse updateRole(UpdateRoleRequest request);

	public UUID deleteRole(UUID id);

	public UUID deleteRoleWithStatus(UUID id);

	public int multipleDeleteRole(List<UUID> ids);

	public int multipleDeleteRoleWithStatus(List<UUID> ids);

	public boolean isRoleExistsByID(UUID id);

	public boolean isRoleExistsByName(String name);

	public Page<RoleResponse> getAllRole(Pageable pageable, String search, RoleFilterRequest filterRequest);

}
