package com.baydep.inventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/version")
public class VersionController {
	@GetMapping
	public ResponseEntity<String> version() {
		return ResponseEntity.ok("1.0.0.002");
	}
}
