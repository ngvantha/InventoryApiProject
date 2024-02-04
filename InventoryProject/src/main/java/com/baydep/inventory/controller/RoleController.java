package com.baydep.inventory.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baydep.inventory.requestVM.AppRoleRequest.CreateRoleRequest;
import com.baydep.inventory.requestVM.AppRoleRequest.RoleFilterRequest;
import com.baydep.inventory.requestVM.AppRoleRequest.UpdateRoleRequest;
import com.baydep.inventory.responseVM.RoleResponse;
import com.baydep.inventory.service.IAppRoleService;
import com.baydep.inventory.validation.Role.RoleIdExists;
import com.baydep.inventory.validation.Role.RoleIdsExists;
import com.baydep.inventory.validation.Role.RoleNameExists;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "api/v1/roles")
@Validated
@Log4j2
@CrossOrigin(origins = "*")
public class RoleController {
	@Autowired
	private IAppRoleService service;

	@GetMapping
	public ResponseEntity<Page<RoleResponse>> getAllRoles(Pageable pageable,
			@RequestParam(value = "search", required = false) String search, RoleFilterRequest filterRequest) {
		Page<RoleResponse> response = service.getAllRole(pageable, search, filterRequest);
		log.info(response);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<RoleResponse> getRoleById(@PathVariable(name = "id") UUID id) {
		RoleResponse response = service.getRoleByID(id);
		log.info(response);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/name/{name}")
	public ResponseEntity<RoleResponse> getRoleById(@PathVariable(name = "name") String name) {
		RoleResponse response = service.getRoleByName(name);
		log.info(response);
		return ResponseEntity.ok(response);
	}

	@PostMapping()
	public ResponseEntity<?> createRole(@RequestBody @Valid CreateRoleRequest request) {
		log.info(request);
		var result = service.createRole(request);
		log.info(result);
		return ResponseEntity.ok(result);
	}

	@PutMapping()
	public ResponseEntity<RoleResponse> updateRole(@RequestBody @Valid UpdateRoleRequest request) {
		log.info(request);
		var result = service.updateRole(request);
		log.info(result);
		return ResponseEntity.ok(result);
	}

	@PutMapping(value = "/{id}/name/{name}")
	public ResponseEntity<?> updateRoleOnlyName(@RoleIdExists @PathVariable(name = "id") UUID id,
			@RoleNameExists @PathVariable(name = "name") String name) {
		var result = service.updateRoleOnlyName(id, name);
		log.info(result);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/Status/{id}")
	public ResponseEntity<?> deleteRoleWithStatus(@RoleIdExists @PathVariable(name = "id") UUID id) {
		var result = service.deleteRoleWithStatus(id);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRole(@RoleIdExists @PathVariable(name = "id") UUID id) {
		var result = service.deleteRole(id);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/MultipleDelete")
	public ResponseEntity<?> multipleDeleteRole(@RoleIdsExists @RequestParam(name = "ids") List<UUID> ids) {
		var result = service.multipleDeleteRole(ids);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/MultipleDeleteStatus")
	public ResponseEntity<?> multipleDeleteRoleWithStatus(
			@RoleIdsExists @RequestParam(name = "ids") List<UUID> ids) {
		var result = service.multipleDeleteRoleWithStatus(ids);
		return ResponseEntity.ok(result);
	}
}
