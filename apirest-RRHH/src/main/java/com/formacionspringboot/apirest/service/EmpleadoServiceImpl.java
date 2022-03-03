package com.formacionspringboot.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspringboot.apirest.dao.EmpleadoDao;
import com.formacionspringboot.apirest.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

	@Autowired
	EmpleadoDao AccesoDb;
	
	@Override
	public List<Empleado> findAll() {

		return (List<Empleado>) AccesoDb.findAll();
	}

	@Override
	public Empleado findById(Long id) {
		
		return AccesoDb.findById(id).orElse(null);
	}

	@Override
	public Empleado save(Empleado empleado) {
		
		return AccesoDb.save(empleado);
	}

	@Override
	public Empleado delete(Long id) {
		AccesoDb.deleteById(id);
		return null;
		
	}
}
