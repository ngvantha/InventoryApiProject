package com.inventory.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.db1.entities.Unit;
import com.inventory.db2.entities.AppRole;
import com.inventory.db2.repositories.IAppRoleRepository;
import com.inventory.requestVM.AppRoleRequest.CreateRoleRequest;
import com.inventory.requestVM.AppRoleRequest.RoleFilterRequest;
import com.inventory.requestVM.AppRoleRequest.UpdateRoleRequest;
import com.inventory.responseVM.RoleResponse;
import com.inventory.responseVM.UnitResponse;
import com.inventory.specification.AppRoleSpecification;
import com.inventory.specification.UnitSpecification;

@Service
@Transactional
public class AppRoleService implements IAppRoleService {

	@Autowired
	private IAppRoleRepository repository;

	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public RoleResponse getRoleByID(UUID id) {
		AppRole role = repository.findById(id).get();
		RoleResponse result = modelMapper.map(role, RoleResponse.class);
		return result;
	}

	@Override
	public RoleResponse getRoleByName(String name) {
		AppRole role = repository.findByName(name);
		RoleResponse result = modelMapper.map(role, RoleResponse.class);
		return result;
	}

	@Override
	public UUID createRole(CreateRoleRequest request) {
		AppRole role = modelMapper.map(request, AppRole.class);
		role.setId(UUID.randomUUID());
		var result = repository.save(role);
		if (result.getId() != null) {
			return result.getId();
		}
		return null;
	}

	@Override
	public String updateRoleOnlyName(UUID id, String newName) {
		Optional<AppRole> optionalRole = repository.findById(id);
		if (optionalRole.isPresent()) {
			AppRole role = optionalRole.get();
			role.setName(newName);
			var result = repository.save(role);
			if (result.getName() != null) {
				return result.getName();
			}
			return null;
		}
		return null;
	}

	@Override
	public RoleResponse updateRole(UpdateRoleRequest request) {
		AppRole role = modelMapper.map(request, AppRole.class);
		var roleResult = repository.save(role);
		RoleResponse result = modelMapper.map(roleResult, RoleResponse.class);
		return result;
	}

	@Override
	public UUID deleteRole(UUID id) {
		repository.deleteById(id);
		Optional<AppRole> optionalRole = repository.findById(id);
		if (optionalRole.isPresent()) {
			return optionalRole.get().getId();
		}
		return null;
	}

	@Override
	public UUID deleteRoleWithStatus(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int multipleDeleteRole(List<UUID> ids) {
		long countBeforDel = repository.count();
		repository.deleteAllById(ids);
		long countAfterDel = repository.count();
		if (countAfterDel == countBeforDel - ids.size()) {
			return 0;
		}
		return ids.size();
	}

	@Override
	public int multipleDeleteRoleWithStatus(List<UUID> ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isRoleExistsByID(UUID id) {
		return repository.existsById(id);
	}

	@Override
	public boolean isRoleExistsByName(String name) {
		return repository.existsByName(name);
	}

	@Override
	public Page<RoleResponse> getAllRole(Pageable pageable, String search, RoleFilterRequest filterRequest) {
		Specification<AppRole> where = AppRoleSpecification.buildWhere(search, filterRequest);
		Page<AppRole> entityPages = repository.findAll(where, pageable);
		// convert entities --> dtos
		List<RoleResponse> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<RoleResponse>>() {
		}.getType());
		Page<RoleResponse> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());
		return dtoPages;
	}

}
