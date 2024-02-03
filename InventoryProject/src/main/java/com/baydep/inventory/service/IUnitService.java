package com.baydep.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.baydep.inventory.db1.entities.Unit;
import com.baydep.inventory.requestVM.UnitRequest.CreateUnitRequest;
import com.baydep.inventory.requestVM.UnitRequest.UnitFilterRequest;
import com.baydep.inventory.requestVM.UnitRequest.UpdateUnitRequest;
import com.baydep.inventory.responseVM.UnitResponse;

public interface IUnitService {

	public UnitResponse getUnitByID(int id);

	public UnitResponse getUnitByName(String name);

	public int createUnit(CreateUnitRequest request);

	public String updateNameOnlyUnit(int id, String newName);

	public UnitResponse updateUnit(UpdateUnitRequest request);

	public int deleteUnit(int id);

	public int deleteUnitWithStatus(int id);

	public int multipleDeleteUnit(List<Integer> ids);

	public int multipleDeleteUnitWithStatus(List<Integer> ids);

	public boolean isUnitExistsByID(int id);

	public boolean isUnitExistsByName(String name);

	public Page<UnitResponse> getAllUnit(Pageable pageable, String search, UnitFilterRequest filterRequest);

}
