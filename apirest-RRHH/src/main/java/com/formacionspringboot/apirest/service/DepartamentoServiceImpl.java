package com.formacionspringboot.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspringboot.apirest.dao.DepartamentoDao;
import com.formacionspringboot.apirest.entity.Departamento;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{

	@Autowired
	DepartamentoDao AccesoDb;
	
	@Override
	public List<Departamento> findAll() {

		return (List<Departamento>) AccesoDb.findAll();
	}

	@Override
	public Departamento findById(Long id) {
		
		return AccesoDb.findById(id).orElse(null);
	}

	@Override
	public Departamento save(Departamento departamento) {
		
		return AccesoDb.save(departamento);
	}

	@Override
	public Departamento delete(Long id) {
		AccesoDb.deleteById(id);
		return null;
		
	}
}
