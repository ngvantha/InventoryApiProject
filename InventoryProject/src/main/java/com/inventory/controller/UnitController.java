package com.inventory.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.db1.entities.Unit;
import com.inventory.responseVM.UnitResponse;
import com.inventory.service.IUnitService;

@RestController
@RequestMapping(value = "api/v1/units")
public class UnitController {
	@Autowired
	private IUnitService service;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/{id}")
	public ResponseEntity<UnitResponse> getUnitId(@PathVariable(name = "id") int id) {
		// Tìm unit trong database bằng id
		Unit unit = service.getUnitByID(id);
		// Nếu không tìm thấy, trả về message lỗi 404 Not found
		if (unit == null)
			return ResponseEntity.notFound().build();
		UnitResponse response = modelMapper.map(unit, UnitResponse.class);
		// Nếu tìm thấy return 200 OK
		return ResponseEntity.ok(response);
	}

}
