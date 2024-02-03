package com.baydep.inventory.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.baydep.inventory.requestVM.AppUserRequest.ChangePassWordRequest;
import com.baydep.inventory.requestVM.AppUserRequest.CreateUserRequest;
import com.baydep.inventory.requestVM.AppUserRequest.UpdateUserRequest;
import com.baydep.inventory.requestVM.AppUserRequest.UserFilterRequest;
import com.baydep.inventory.responseVM.UserResponse;

public interface IAppUserService {

	public UserResponse getUserByID(UUID id);

	public UserResponse getUserByUserName(String name);

	public UUID createUser(CreateUserRequest request);

	public String updateUserNameOnlyUser(UUID id, String newUserName);

	public UserResponse updateUser(UpdateUserRequest request);

	public UUID deleteUser(UUID id);

	public UUID deleteUserWithStatus(UUID id);

	public int multipleDeleteUser(List<UUID> ids);

	public int multipleDeleteUserWithStatus(List<UUID> ids);

	public boolean isUserExistsByID(UUID id);

	public boolean isUserExistsByUserName(String userName);

	public Page<UserResponse> getAllUser(Pageable pageable, String search, UserFilterRequest filterRequest);
	
	public String updateEmailOnlyUser(UUID id, String newEmail);
	
	public String updatePasswordOnlyUser(UUID id, ChangePassWordRequest request) throws Exception;
	
	public boolean isUserExistsByEmail(String email);
	
	public UserResponse getUserByEmail(String email);

}
