package com.baydep.inventory.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baydep.inventory.config.GlobalExceptionHandler.ApiMessageResponse;
import com.baydep.inventory.config.JWT.AuthenticationModel;
import com.baydep.inventory.config.JWT.CustomAuthenticationService;
import com.baydep.inventory.config.JWT.JwtTokenUtil;
import com.baydep.inventory.db2.entities.AppRole;
import com.baydep.inventory.db2.entities.AppUser;
import com.baydep.inventory.db2.repositories.IAppRoleRepository;
import com.baydep.inventory.db2.repositories.IAppUserRepository;
import com.baydep.inventory.requestVM.AppUserRequest.LoginRequest;
import com.baydep.inventory.requestVM.AppUserRequest.SignupRequest;
import com.baydep.inventory.responseVM.JwtResponse;
import com.baydep.inventory.responseVM.RoleResponse;

import jakarta.validation.Valid;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenUtil jwtUtils;

	@Autowired
	IAppUserRepository userRepository;

	@Autowired
	IAppRoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassWord()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		AuthenticationModel userDetails = (AuthenticationModel) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		var result = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
				roles);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/signup")
	public ResponseEntity<ApiMessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUserName(signUpRequest.getUserName())) {
			return ResponseEntity.badRequest().body(new ApiMessageResponse("Error", "Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new ApiMessageResponse("Error", "Email is already in use!"));
		}
		// Create new user's account
		var pass = signUpRequest.getPasswordHash();
		signUpRequest.setPasswordHash(passwordEncoder.encode(pass));
		AppUser user = modelMapper.map(signUpRequest, AppUser.class);
		List<RoleResponse> roles = signUpRequest.getAppRoles();
		List<AppRole> approles = new ArrayList<>();
		for (RoleResponse roleResponse : roles) {
			AppRole mapRole = modelMapper.map(roleResponse, AppRole.class);
			AppRole role = roleRepository.findById(mapRole.getId()).get();
			if (role != null) {
				approles.add(role);
			}
		}
		user.setAppRoles(approles);
		var result = userRepository.save(user);
		if (result.getId() != null) {
			return ResponseEntity.ok(new ApiMessageResponse("Succsess", "User registered successfully!"));
		}
		return ResponseEntity.badRequest().body(new ApiMessageResponse("Failed!", "User registered successfully!"));
	}

}
