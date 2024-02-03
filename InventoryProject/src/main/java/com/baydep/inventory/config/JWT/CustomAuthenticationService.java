package com.baydep.inventory.config.JWT;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baydep.inventory.db2.entities.AppUser;
import com.baydep.inventory.db2.repositories.IAppUserRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class CustomAuthenticationService implements UserDetailsService {

	@Autowired
	IAppUserRepository userRepository;

	public AuthenticationModel loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser userbyName = userRepository.findByUserName(username);
		AppUser userbyEmail = userRepository.findByEmail(username);
		if (userbyName == null && userbyEmail == null) {
			throw new UsernameNotFoundException("User Not Found with username or email: " + username);
		}
		AppUser user = (userbyName == null) ? userbyEmail : userbyName;
		return AuthenticationModel.build(user);
	}

}
