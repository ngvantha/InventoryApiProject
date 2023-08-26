package com.inventory.db2.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.inventory.db2.entities.AppUser;

public interface IAppUserRepository extends JpaRepository<AppUser, UUID>, JpaSpecificationExecutor<AppUser> {
	public AppUser findByUserName(String name);
	
	public AppUser findByEmail(String email);

	public boolean existsByUserName(String name);
	
	public boolean existsByEmail(String email);
}
