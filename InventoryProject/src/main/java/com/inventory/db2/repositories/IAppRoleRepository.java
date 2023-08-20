package com.inventory.db2.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.db2.entities.AppRoles;


public interface IAppRoleRepository extends JpaRepository<AppRoles, UUID> {

}
