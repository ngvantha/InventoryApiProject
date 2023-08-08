package com.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.requestVM.UnitFilterRequest;
import com.inventory.responseVM.UnitResponse;
import com.inventory.service.IUnitService;

@RestController
@RequestMapping(value = "api/v1/units")
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

}
