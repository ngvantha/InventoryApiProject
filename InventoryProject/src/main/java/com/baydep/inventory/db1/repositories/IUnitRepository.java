package com.baydep.inventory.db1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.baydep.inventory.db1.entities.Unit;

public interface IUnitRepository extends JpaRepository<Unit, Integer>, JpaSpecificationExecutor<Unit> {

	public Unit findByName(String name);

	public boolean existsByName(String name);

}
