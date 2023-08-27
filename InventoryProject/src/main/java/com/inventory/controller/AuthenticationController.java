package com.inventory.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.inventory.config.JWT.AuthenticationModel;
import com.inventory.config.JWT.CustomAuthenticationService;
import com.inventory.config.JWT.JwtTokenUtil;
import com.inventory.db2.entities.AppRole;
import com.inventory.db2.entities.AppUser;
import com.inventory.db2.repositories.IAppRoleRepository;
import com.inventory.db2.repositories.IAppUserRepository;
import com.inventory.requestVM.AppUserRequest.LoginRequest;
import com.inventory.requestVM.AppUserRequest.SignupRequest;
import com.inventory.responseVM.JwtResponse;

import jakarta.validation.Valid;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

	 @Autowired
	 private CustomAuthenticationService authenticationService;
	
	  @PostMapping("/signin")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		  var result = authenticationService.authenticateUser(loginRequest);
	    return ResponseEntity.ok(result);
	  }
	  
//	  @PostMapping("/signup")
//	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//	    if (userRepository.existsByUserName(signUpRequest.getUsername())) {
//	      return ResponseEntity
//	          .badRequest()
//	          .body(new ApiErrorResponse("Error","Username is already taken!"));
//	    }
//
//	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//	      return ResponseEntity
//	          .badRequest()
//	          .body(new ApiErrorResponse("Error"," Email is already in use!"));
//	    }
//
//	    // Create new user's account
//	    AppUser user = new AppUser(signUpRequest.getUsername(), 
//	               signUpRequest.getEmail(),
//	               encoder.encode(signUpRequest.getPassword()));
//
//	    Set<String> strRoles = signUpRequest.getRole();
//	    Set<AppRole> roles = new HashSet<>();
//
//	    if (strRoles == null) {
//	      AppRole userRole = roleRepository.findByName(ERole.ROLE_USER)
//	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//	      roles.add(userRole);
//	    } else {
//	      strRoles.forEach(role -> {
//	        switch (role) {
//	        case "admin":
//	          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//	          roles.add(adminRole);
//
//	          break;
//	        case "mod":
//	          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//	          roles.add(modRole);
//
//	          break;
//	        default:
//	          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//	          roles.add(userRole);
//	        }
//	      });
//	    }
//
//	    user.setRoles(roles);
//	    userRepository.save(user);
//
//	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//	  }
//	  
	  
}
