package com.inventory.config.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inventory.db2.entities.AppUser;
import com.inventory.db2.repositories.IAppUserRepository;

@Service
public class CustomAuthenticationService implements UserDetailsService{
	
	@Autowired
	private IAppUserRepository repository;

	public AuthenticationModel loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = repository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User Not Found with username: " + username);
		}
		return AuthenticationModel.build(user);
	}
}
