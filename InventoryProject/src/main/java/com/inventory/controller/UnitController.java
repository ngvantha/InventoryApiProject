package com.inventory.controller;

import java.util.List;

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

import com.inventory.requestVM.UnitRequest.CreateUnitRequest;
import com.inventory.requestVM.UnitRequest.UnitFilterRequest;
import com.inventory.requestVM.UnitRequest.UpdateUnitRequest;
import com.inventory.responseVM.UnitResponse;
import com.inventory.service.IUnitService;
import com.inventory.validation.Unit.UnitIdExists;
import com.inventory.validation.Unit.UnitIdsExists;
import com.inventory.validation.Unit.UnitNameExists;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "api/v1/units")
@Validated
@Log4j2
public class UnitController {
	@Autowired
	private IUnitService service;

	@GetMapping
	public ResponseEntity<Page<UnitResponse>> getAllUnites(Pageable pageable,
			@RequestParam(value = "search", required = false) String search, UnitFilterRequest filterRequest) {
		Page<UnitResponse> response = service.getAllUnit(pageable, search, filterRequest);
		log.info(response);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UnitResponse> getUnitById(@PathVariable(name = "id") int id) {
		UnitResponse response = service.getUnitByID(id);
		log.info(response);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/name/{name}")
	public ResponseEntity<UnitResponse> getUnitByName(@PathVariable(name = "name") String name) {
		UnitResponse response = service.getUnitByName(name);
		log.info(response);
		return ResponseEntity.ok(response);
	}

	@PostMapping()
	public ResponseEntity<?> createUnit(@RequestBody @Valid CreateUnitRequest request) {
		log.info(request);
		var result = service.createUnit(request);
		log.info(result);
		return ResponseEntity.ok(result);
	}

	@PutMapping()
	public ResponseEntity<UnitResponse> updateUnit(@RequestBody @Valid UpdateUnitRequest request) {
		log.info(request);
		var result = service.updateUnit(request);
		log.info(result);
		return ResponseEntity.ok(result);
	}

	@PutMapping(value = "/{id}/name/{name}")
	public ResponseEntity<?> updateNameOnlyUnit(@UnitIdExists @PathVariable(name = "id") int id,
			@UnitNameExists @PathVariable(name = "name") String name) {
		var result = service.updateNameOnlyUnit(id, name);
		log.info(result);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/Status/{id}")
	public ResponseEntity<?> deleteUnitWithStatus(@UnitIdExists @PathVariable(name = "id") int id) {
		var result = service.deleteUnitWithStatus(id);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUnit(@UnitIdExists @PathVariable(name = "id") int id) {
		var result = service.deleteUnit(id);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/MultipleDelete")
	public ResponseEntity<?> multipleDeleteUnit(@UnitIdsExists @RequestParam(name = "ids") List<Integer> ids) {
		var result = service.multipleDeleteUnit(ids);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/MultipleDeleteStatus")
	public ResponseEntity<?> multipleDeleteUnitWithStatus(
			@UnitIdsExists @RequestParam(name = "ids") List<Integer> ids) {
		var result = service.multipleDeleteUnitWithStatus(ids);
		return ResponseEntity.ok(result);
	}

}
