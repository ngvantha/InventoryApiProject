package com.inventory.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.db1.entities.Unit;
import com.inventory.db1.repositories.IUnitRepository;
import com.inventory.responseVM.UnitResponse;

@Service
@Transactional
public class UnitService implements IUnitService {

	@Autowired
	private IUnitRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UnitResponse getUnitByID(int id) {
		// TODO Auto-generated method stub
		Unit unit = repository.findById(id).get();
		UnitResponse result = modelMapper.map(unit, UnitResponse.class);
		return result;
	}

	@Override
	public Unit getUnitByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createUnit(Unit department) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUnit(int id, String newName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUnit(Unit department) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUnit(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUnit(List<Integer> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isUnitExistsByID(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUnitExistsByName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Page<UnitResponse> getAllUnit(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Unit> entityPages = repository.findAll(pageable);
		// convert entities --> dtos
		List<UnitResponse> dtos = 
				modelMapper.map(entityPages.getContent(), new TypeToken<List<UnitResponse>>() {}.getType());
		Page<UnitResponse> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());
		return dtoPages;
	}

}
