package com.baydep.inventory.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baydep.inventory.requestVM.AppUserRequest.CreateUserRequest;
import com.baydep.inventory.requestVM.AppUserRequest.UpdateUserRequest;
import com.baydep.inventory.requestVM.AppUserRequest.UserFilterRequest;
import com.baydep.inventory.responseVM.UserResponse;
import com.baydep.inventory.service.IAppUserService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "api/v1/users")
@Validated
@Log4j2
public class UserController {
	@Autowired
	private IAppUserService service;

	@GetMapping
	public ResponseEntity<Page<UserResponse>> getAllUseres(Pageable pageable,
			@RequestParam(value = "search", required = false) String search, UserFilterRequest filterRequest) {
		Page<UserResponse> response = service.getAllUser(pageable, search, filterRequest);
		log.info(response);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResponse> getUserId(@PathVariable(name = "id") UUID id) {
		UserResponse response = service.getUserByID(id);
		log.info(response);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/username/{username}")
	public ResponseEntity<UserResponse> getUserId(@PathVariable(name = "username") String username) {
		UserResponse response = service.getUserByUserName(username);
		log.info(response);
		return ResponseEntity.ok(response);
	}

	@PostMapping()
	public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserRequest request) {
		log.info(request);
		var result = service.createUser(request);
		log.info(result);
		return ResponseEntity.ok(result);
	}

	@PutMapping()
	public ResponseEntity<UserResponse> updateUser(@RequestBody @Valid UpdateUserRequest request) {
		log.info(request);
		var result = service.updateUser(request);
		log.info(result);
		return ResponseEntity.ok(result);
	}

	@PutMapping(value = "/{id}/username/{username}")
	public ResponseEntity<?> updateNameOnlyUser( @PathVariable(name = "id") UUID id,
			 @PathVariable(name = "username") String username) {
		var result = service.updateUserNameOnlyUser(id, username);
		log.info(result);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/Status/{id}")
	public ResponseEntity<?> deleteUserWithStatus(@PathVariable(name = "id") UUID id) {
		var result = service.deleteUserWithStatus(id);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(name = "id") UUID id) {
		var result = service.deleteUser(id);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/MultipleDelete")
	//to do doi @RequestParam thanh @RequestBody
	public ResponseEntity<?> multipleDeleteUser( @RequestParam(name = "ids") List<UUID> ids) {
		var result = service.multipleDeleteUser(ids);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/MultipleDeleteStatus")
	public ResponseEntity<?> multipleDeleteUserWithStatus(
			 @RequestParam(name = "ids") List<UUID> ids) {
		var result = service.multipleDeleteUserWithStatus(ids);
		return ResponseEntity.ok(result);
	}

}
