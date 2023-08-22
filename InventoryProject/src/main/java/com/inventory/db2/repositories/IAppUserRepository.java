package com.inventory.db2.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.inventory.db2.entities.AppUser;

public interface IAppUserRepository extends JpaRepository<AppUser, UUID>,JpaSpecificationExecutor<AppUser> {

}