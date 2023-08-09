package com.inventory.service;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.db1.entities.Unit;
import com.inventory.requestVM.UnitRequest.CreateUnitRequest;
import com.inventory.requestVM.UnitRequest.UnitFilterRequest;
import com.inventory.responseVM.UnitResponse;

public interface IUnitService {
	//public Page<Unit> getAllUnit(Pageable pageable, DepartmentFilter filter, String search);

	public UnitResponse getUnitByID(int id);

	public UnitResponse getUnitByName(String name);

	public int createUnit(CreateUnitRequest request);

	public void updateUnit(int id, String newName);

	public void updateUnit(Unit department);

	public void deleteUnit(int id);
	
	public void deleteUnit(List<Integer> ids);

	public boolean isUnitExistsByID(int id);

	public boolean isUnitExistsByName(String name);
	
	public Page<UnitResponse> getAllUnit(Pageable pageable, String search, UnitFilterRequest filterRequest);

}
