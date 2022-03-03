package com.formacionspringboot.apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspringboot.apirest.dao.UsuarioDao;
import com.formacionspringboot.apirest.entity.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioServicio{

	@Autowired
	UsuarioDao AccesoDb;

	@Override
	public Usuario findById(Long id) {
		
		return AccesoDb.findById(id).orElse(null);
	}
	
}
