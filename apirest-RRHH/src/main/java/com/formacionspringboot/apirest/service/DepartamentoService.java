package com.formacionspringboot.apirest.service;

import java.util.List;

import com.formacionspringboot.apirest.entity.Departamento;

public interface DepartamentoService {

	public List<Departamento> findAll();
	
	public Departamento findById(Long id);
	
	public Departamento save(Departamento departamento);
	
	public Departamento delete(Long id);
}
