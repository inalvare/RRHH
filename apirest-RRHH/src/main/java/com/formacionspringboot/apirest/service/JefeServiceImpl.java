package com.formacionspringboot.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspringboot.apirest.dao.JefeDao;
import com.formacionspringboot.apirest.entity.Jefe;

@Service
public class JefeServiceImpl implements JefeService{

	@Autowired
	JefeDao AccesoDb;
	
	@Override
	public List<Jefe> findAll() {

		return (List<Jefe>) AccesoDb.findAll();
	}

	@Override
	public Jefe findById(Long id) {
		
		return AccesoDb.findById(id).orElse(null);
	}

	@Override
	public Jefe save(Jefe jefe) {
		
		return AccesoDb.save(jefe);
	}

	@Override
	public Jefe delete(Long id) {
		AccesoDb.deleteById(id);
		return null;
		
	}
}
