package com.baydep.inventory.service;

import java.util.ArrayList;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baydep.inventory.db2.entities.AppRole;
import com.baydep.inventory.db2.entities.AppUser;
import com.baydep.inventory.db2.repositories.IAppRoleRepository;
import com.baydep.inventory.db2.repositories.IAppUserRepository;
import com.baydep.inventory.requestVM.AppUserRequest.ChangePassWordRequest;
import com.baydep.inventory.requestVM.AppUserRequest.CreateUserRequest;
import com.baydep.inventory.requestVM.AppUserRequest.UpdateUserRequest;
import com.baydep.inventory.requestVM.AppUserRequest.UserFilterRequest;
import com.baydep.inventory.responseVM.RoleResponse;
import com.baydep.inventory.responseVM.UserResponse;
import com.baydep.inventory.specification.AppUserSpecification;

@Service
@Transactional
public class AppUserService implements IAppUserService {

	@Autowired
	private IAppUserRepository repository;

	@Autowired
	private IAppRoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserResponse getUserByID(UUID id) {
		AppUser user = repository.findById(id).get();
		UserResponse result = modelMapper.map(user, UserResponse.class);
		return result;
	}

	@Override
	public UserResponse getUserByUserName(String name) {
		AppUser user = repository.findByUserName(name);
		UserResponse result = modelMapper.map(user, UserResponse.class);
		return result;
	}

	@Override
	public UUID createUser(CreateUserRequest request) {
		var pass = request.getPasswordHash();
		request.setPasswordHash(passwordEncoder.encode(pass));
		AppUser user = modelMapper.map(request, AppUser.class);
		List<RoleResponse> roles = request.getAppRoles();
		List<AppRole> approles = new ArrayList<>();
		for (RoleResponse roleResponse : roles) {
			AppRole mapRole = modelMapper.map(roleResponse, AppRole.class);
			AppRole role = roleRepository.findById(mapRole.getId()).get();
			if (role != null) {
				approles.add(role);
			}
		}
		user.setAppRoles(approles);
		var result = repository.save(user);
		if (result.getId() != null) {
			return result.getId();
		}
		return null;
	}

	@Override
	public String updateUserNameOnlyUser(UUID id, String newUserName) {
		Optional<AppUser> optionalUser = repository.findById(id);
		if (optionalUser.isPresent()) {
			AppUser user = optionalUser.get();
			user.setUserName(newUserName);
			var result = repository.save(user);
			if (result.getUserName() != null) {
				return result.getUserName();
			}
			return null;
		}
		return null;
	}

	@Override
	public UserResponse updateUser(UpdateUserRequest request) {
		AppUser user = modelMapper.map(request, AppUser.class);
		List<RoleResponse> roles = request.getAppRoles();
		AppUser entity = repository.findById(user.getId()).get();
		/// xoa toan bo role hien co di
		for (AppRole approle : entity.getAppRoles()) {
			entity.getAppRoles().remove(approle);
			repository.save(entity);
		}
		// them nhung role moi da duoc cap nhat
		List<AppRole> approles = new ArrayList<>();
		for (RoleResponse roleResponse : roles) {
			AppRole mapRole = modelMapper.map(roleResponse, AppRole.class);
			AppRole role = roleRepository.findById(mapRole.getId()).get();
			if (role != null) {
				approles.add(role);
			}
		}
		user.setAppRoles(approles);
		var result = repository.save(user);
		if (result != null) {
			return modelMapper.map(result, UserResponse.class);
		}
		return null;
	}

	@Override
	public UUID deleteUser(UUID id) {
		repository.deleteById(id);
		Optional<AppUser> optionalUser = repository.findById(id);
		if (optionalUser.isPresent()) {
			return optionalUser.get().getId();
		}
		return null;
	}

	@Override
	public UUID deleteUserWithStatus(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int multipleDeleteUser(List<UUID> ids) {
		long countBeforDel = repository.count();
		repository.deleteAllById(ids);
		long countAfterDel = repository.count();
		if (countAfterDel == countBeforDel - ids.size()) {
			return 0;
		}
		return ids.size();
	}

	@Override
	public int multipleDeleteUserWithStatus(List<UUID> ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUserExistsByID(UUID id) {
		return repository.existsById(id);
	}

	@Override
	public boolean isUserExistsByUserName(String userName) {
		return repository.existsByUserName(userName);
	}

	@Override
	public Page<UserResponse> getAllUser(Pageable pageable, String search, UserFilterRequest filterRequest) {
		Specification<AppUser> where = AppUserSpecification.buildWhere(search, filterRequest);
		Page<AppUser> entityPages = repository.findAll(where, pageable);
		// convert entities --> dtos
		List<UserResponse> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<UserResponse>>() {
		}.getType());
		Page<UserResponse> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());
		return dtoPages;
	}

	@Override
	public String updateEmailOnlyUser(UUID id, String newEmail) {
		Optional<AppUser> optionalUser = repository.findById(id);
		if (optionalUser.isPresent()) {
			AppUser user = optionalUser.get();
			user.setUserName(newEmail);
			var result = repository.save(user);
			if (result.getUserName() != null) {
				return result.getUserName();
			}
			return null;
		}
		return null;
	}

	@Override
	public String updatePasswordOnlyUser(UUID id, ChangePassWordRequest request) throws Exception {
		Optional<AppUser> optionalUser = repository.findById(id);
		if (optionalUser.isPresent()) {
			AppUser user = optionalUser.get();
			if (passwordEncoder.encode(request.getOldPassWord()) != passwordEncoder.encode(user.getPasswordHash())) {
				throw new Exception("old pass word not IsVaild !!!");
			}
			user.setPasswordHash(passwordEncoder.encode(request.getNewPassWord()));
			var result = repository.save(user);
			if (result.getUserName() != null) {
				return result.getUserName();
			}
			return null;
		}
		return null;
	}

	@Override
	public boolean isUserExistsByEmail(String email) {
		return repository.existsByEmail(email);
	}

	@Override
	public UserResponse getUserByEmail(String email) {
		AppUser user = repository.findByEmail(email);
		UserResponse result = modelMapper.map(user, UserResponse.class);
		return result;
	}

}
