package com.inventory.db1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.db1.entities.Unit;

public interface IUnitRepository extends JpaRepository<Unit, Integer> {
//	public Unit findByName(String name);
//
//	public boolean existsByName(String name);
}
