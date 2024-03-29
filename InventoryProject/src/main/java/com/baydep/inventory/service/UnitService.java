package com.baydep.inventory.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baydep.inventory.db1.entities.Unit;
import com.baydep.inventory.db1.repositories.IUnitRepository;
import com.baydep.inventory.requestVM.UnitRequest.CreateUnitRequest;
import com.baydep.inventory.requestVM.UnitRequest.UnitFilterRequest;
import com.baydep.inventory.requestVM.UnitRequest.UpdateUnitRequest;
import com.baydep.inventory.responseVM.UnitResponse;
import com.baydep.inventory.specification.UnitSpecification;

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
		if (result.getId() != null) {
			return result.getId();
		}
		return 0;
	}

	@Override
	public int multipleDeleteUnit(List<Integer> ids) {
		long countBeforDel = repository.count();
		repository.deleteAllById(ids);
		long countAfterDel = repository.count();
		if (countAfterDel == countBeforDel - ids.size()) {
			return 0;
		}
		return ids.size();
	}

	@Override
	public boolean isUnitExistsByID(int id) {
		return repository.existsById(id);
	}

	@Override
	public boolean isUnitExistsByName(String name) {
		return repository.existsByName(name);
	}

	public Page<UnitResponse> getAllUnit(Pageable pageable, String search, UnitFilterRequest filterRequest) {
		Specification<Unit> where = UnitSpecification.buildWhere(search, filterRequest);
		Page<Unit> entityPages = repository.findAll(where, pageable);
		// convert entities --> dtos
		List<UnitResponse> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<UnitResponse>>() {
		}.getType());
		Page<UnitResponse> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());
		return dtoPages;
	}

	@Override
	public String updateNameOnlyUnit(int id, String newName) {
		Optional<Unit> optionalUnit = repository.findById(id);
		if (optionalUnit.isPresent()) {
			Unit unit = optionalUnit.get();
			unit.setName(newName);
			var result = repository.save(unit);
			if (result.getName() != null) {
				return result.getName();
			}
			return null;
		}
		return null;
	}

	@Override
	public UnitResponse updateUnit(UpdateUnitRequest request) {
		Unit unit = modelMapper.map(request, Unit.class);
		var unitResult = repository.save(unit);
		UnitResponse result = modelMapper.map(unitResult, UnitResponse.class);
		return result;
	}

	@Override
	public int deleteUnit(int id) {
		repository.deleteById(id);
		Optional<Unit> optionalUnit = repository.findById(id);
		if (optionalUnit.isPresent()) {
			return optionalUnit.get().getId();
		}
		return 0;
	}

	@Override
	public int deleteUnitWithStatus(int id) {
		Optional<Unit> optionalUnit = repository.findById(id);
		if (optionalUnit.isPresent()) {
			Unit unit = optionalUnit.get();
			unit.setDelStatus(true);
			var result = repository.save(unit);
			if (result.getId() != null) {
				return result.getId();
			}
			return 0;
		}
		return 0;
	}

	@Override
	public int multipleDeleteUnitWithStatus(List<Integer> ids) {
		int count = 0;
		for (int id : ids) {
			if (repository.existsById(id)) {
				Optional<Unit> optionalUnit = repository.findById(id);
				if (optionalUnit.isPresent()) {
					Unit unit = optionalUnit.get();
					unit.setDelStatus(true);
					var result = repository.save(unit);
					if (result.getId() != null) {
						count++;
					}
				}
			}
		}
		if (ids.size() == count) {
			return count;
		}
		return 0;
	}

}
