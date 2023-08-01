package com.inventory.service;

import java.util.List;

import com.inventory.db1.entities.Unit;

public interface IUnitService {
	//public Page<Unit> getAllUnit(Pageable pageable, DepartmentFilter filter, String search);

	public Unit getUnitByID(int id);

	public Unit getUnitByName(String name);

	public void createUnit(Unit department);

	public void updateUnit(int id, String newName);

	public void updateUnit(Unit department);

	public void deleteUnit(int id);
	
	public void deleteUnit(List<Integer> ids);

	public boolean isUnitExistsByID(int id);

	public boolean isUnitExistsByName(String name);

}
