package com.baydep.inventory.db2.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.baydep.inventory.db2.entities.AppRole;

@Repository
public interface IAppRoleRepository extends JpaRepository<AppRole, UUID>,  JpaSpecificationExecutor<AppRole>  {

	public AppRole findByName(String name);
	
	public boolean existsByName(String name);
}
