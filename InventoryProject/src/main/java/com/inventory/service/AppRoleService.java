package com.inventory.service;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.db2.repositories.IAppRoleRepository;
import com.inventory.requestVM.AppRoleRequest.CreateRoleRequest;
import com.inventory.requestVM.AppRoleRequest.RoleFilterRequest;
import com.inventory.requestVM.AppRoleRequest.UpdateRoleRequest;
import com.inventory.responseVM.AppRoleResponse;

public class AppRoleService implements IAppRoleService {

	@Autowired
	private IAppRoleRepository repository;

	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public AppRoleResponse getRoleByID(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppRoleResponse getRoleByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createRole(CreateRoleRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String updateNameOnlyRole(UUID id, String newName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppRoleResponse updateRole(UpdateRoleRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteRole(UUID id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRoleWithStatus(UUID id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int multipleDeleteRole(List<UUID> ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int multipleDeleteRoleWithStatus(List<UUID> ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isRoleExistsByID(UUID id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRoleExistsByName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Page<AppRoleResponse> getAllRole(Pageable pageable, String search, RoleFilterRequest filterRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
