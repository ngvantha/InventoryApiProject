package com.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.db1.entities.Unit;
import com.inventory.db1.repositories.IUnitRepository;

import jakarta.persistence.EntityManagerFactory;


@Service
@Transactional
public class UnitService implements IUnitService{
	
	@Autowired
	private IUnitRepository repository;
	
	@Override
	public Unit getUnitByID(int id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
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

}
