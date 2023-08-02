package com.inventory.contrller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.db1.entities.Unit;
import com.inventory.responseVM.UnitResponse;
import com.inventory.service.UnitService;

@RestController
@RequestMapping(value = "api/v1/units")
public class UnitController {
	@Autowired
	private UnitService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UnitResponse> getUnitId(@PathVariable(name = "id") int id) {
		try {
			// Tìm unit trong database bằng id
	        Unit unit = service.getUnitByID(id);
	        // Nếu không tìm thấy, trả về message lỗi 404 Not found
	        if (unit == null)
	            return ResponseEntity.notFound().build();  // Tạm thời là vậy, thực tế người ta dùng AOP để bắt exception
	        UnitResponse response = new UnitResponse(unit.getId(),unit.getName(),unit.getUnitDescription(),unit.getDelStatus());
	        // Nếu tìm thấy return 200 OK
	        return ResponseEntity.ok(response);
			//return service.getUnitByID(id);
			//return null;
		}catch (Exception e) {
			// TODO: handle exception
			 return ResponseEntity.notFound().build(); 
		} 
		
		
	}
	
}
