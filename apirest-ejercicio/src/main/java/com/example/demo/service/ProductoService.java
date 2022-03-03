package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Producto;

public interface ProductoService {
	
	public List<Producto> findAll();
	
	public Producto findById(Long id);
	
	public Producto delete(Long id);
	
	public Producto save(Producto producto);
}
