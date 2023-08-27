package com.inventory.config.JWT;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inventory.config.GlobalExceptionHandler.ApiMessageResponse;
import com.inventory.db2.entities.AppUser;
import com.inventory.db2.repositories.IAppRoleRepository;
import com.inventory.db2.repositories.IAppUserRepository;
import com.inventory.requestVM.AppUserRequest.LoginRequest;
import com.inventory.responseVM.JwtResponse;

@Service
public class CustomAuthenticationService implements UserDetailsService{
	
	 @Autowired
	  AuthenticationManager authenticationManager;

	  @Autowired
	  IAppUserRepository userRepository;

	  @Autowired
	  IAppRoleRepository roleRepository;

	  @Autowired
	  PasswordEncoder encoder;

	  @Autowired
	  JwtTokenUtil jwtUtils;

	public AuthenticationModel loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User Not Found with username: " + username);
		}
		return AuthenticationModel.build(user);
	}
	
	public JwtResponse authenticateUser(LoginRequest loginRequest) {
	    Authentication authentication = authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassWord()));

		    SecurityContextHolder.getContext().setAuthentication(authentication);
		    String jwt = jwtUtils.generateJwtToken(authentication);
		    
		    AuthenticationModel userDetails = (AuthenticationModel) authentication.getPrincipal();    
		    List<String> roles = userDetails.getAuthorities().stream()
		        .map(item -> item.getAuthority())
		        .collect(Collectors.toList());
		    var result = new JwtResponse(jwt, 
	                userDetails.getId(), 
	                userDetails.getUsername(), 
	                userDetails.getEmail(), 
	                roles);
		    return result;
	}
	
	public ApiMessageResponse registerUser() {
		
		
		return new ApiMessageResponse("Succsess","");
	}
}
