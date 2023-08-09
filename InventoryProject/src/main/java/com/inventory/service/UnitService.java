package com.inventory.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.db1.entities.Unit;
import com.inventory.db1.repositories.IUnitRepository;
import com.inventory.requestVM.UnitRequest.CreateUnitRequest;
import com.inventory.requestVM.UnitRequest.UnitFilterRequest;
import com.inventory.responseVM.UnitResponse;
import com.inventory.specification.UnitSpecification;

@Service
@Transactional
public class UnitService implements IUnitService {

	@Autowired
	private IUnitRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UnitResponse getUnitByID(int id) {
		Unit unit = repository.findById(id).get();
		UnitResponse result = modelMapper.map(unit, UnitResponse.class);
		return result;
	}

	@Override
	public UnitResponse getUnitByName(String name) {
		Unit unit = repository.findByName(name);
		UnitResponse result = modelMapper.map(unit, UnitResponse.class);
		return result;
	}

	@Override
	public int createUnit(CreateUnitRequest request) {
		Unit unit = modelMapper.map(request, Unit.class);
		var result = repository.save(unit);
		if(result.getId()!= null) {
			return result.getId();
		}
		return 0;
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

	public Page<UnitResponse> getAllUnit(Pageable pageable, String search, UnitFilterRequest filterRequest) {
		// TODO Auto-generated method stub
		Specification<Unit> where = UnitSpecification.buildWhere(search, filterRequest);
		Page<Unit> entityPages = repository.findAll(where, pageable);
		// convert entities --> dtos
		List<UnitResponse> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<UnitResponse>>() {
		}.getType());
		Page<UnitResponse> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());
		return dtoPages;
	}

}
