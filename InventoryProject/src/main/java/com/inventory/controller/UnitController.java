package com.inventory.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.requestVM.UnitRequest.CreateUnitRequest;
import com.inventory.requestVM.UnitRequest.UnitFilterRequest;
import com.inventory.requestVM.UnitRequest.UpdateUnitRequest;
import com.inventory.responseVM.UnitResponse;
import com.inventory.service.IUnitService;
import com.inventory.validation.Unit.UnitIdExists;
import com.inventory.validation.Unit.UnitNameExists;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "api/v1/units")
@Validated
public class UnitController {
	@Autowired
	private IUnitService service;

	@GetMapping
	public ResponseEntity<Page<UnitResponse>> getAllUnites(Pageable pageable,
			@RequestParam(value = "search", required = false) String search, UnitFilterRequest filterRequest) {
		Page<UnitResponse> response = service.getAllUnit(pageable, search, filterRequest);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UnitResponse> getUnitId(@PathVariable(name = "id") int id) {
		UnitResponse response = service.getUnitByID(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<UnitResponse> getUnitId(@PathVariable(name = "name") String name) {
		UnitResponse response = service.getUnitByName(name);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping()
	public ResponseEntity<?> createUnit(@RequestBody @Valid CreateUnitRequest request) {
		var result = service.createUnit(request);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping()
	public ResponseEntity<UnitResponse> updateUnit(@RequestBody @Valid UpdateUnitRequest request) {
		var result = service.updateUnit(request);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping(value ="/{id}/name/{name}")
	public ResponseEntity<?> updateNameOnlyUnit(@UnitIdExists @PathVariable(name = "id") int id,@UnitNameExists @PathVariable(name="name") String name) {
		var result = service.updateNameOnlyUnit(id, name);
		return ResponseEntity.ok(result);
	}

}
