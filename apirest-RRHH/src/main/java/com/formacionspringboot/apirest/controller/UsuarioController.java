package com.formacionspringboot.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspringboot.apirest.service.UsuarioServicio;


@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	private UsuarioServicio servicio;
	
	private boolean permiso=false;
	
	@GetMapping("/log")
	public boolean log(Long id){
		if (servicio.findById(id)!=null) this.permiso=true;
		else this.permiso=false;
		return this.permiso;
	}
}
