package com.inventory.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.responseVM.UnitResponse;
import com.inventory.service.IUnitService;

@RestController
@RequestMapping(value = "api/v1/units")
public class UnitController {
	@Autowired
	private IUnitService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<UnitResponse> getUnitId(@PathVariable(name = "id") int id) {
		UnitResponse response = service.getUnitByID(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<Page<UnitResponse>> getAllUnites(Pageable pageable) {
		Page<UnitResponse> response = service.getAllUnit(pageable);
		return ResponseEntity.ok(response);
	}

}
