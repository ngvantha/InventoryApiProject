package com.baydep.inventory.db2.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.baydep.inventory.db2.entities.AppUser;

@Repository
public interface IAppUserRepository extends JpaRepository<AppUser, UUID>, JpaSpecificationExecutor<AppUser> {
	public AppUser findByUserName(String name);
	
	public AppUser findByEmail(String email);

	public boolean existsByUserName(String name);
	
	public boolean existsByEmail(String email);
}
