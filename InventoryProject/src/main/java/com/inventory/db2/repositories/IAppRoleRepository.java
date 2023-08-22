package com.inventory.db2.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.inventory.db2.entities.AppRole;


public interface IAppRoleRepository extends JpaRepository<AppRole, UUID>,  JpaSpecificationExecutor<AppRole>  {

}
