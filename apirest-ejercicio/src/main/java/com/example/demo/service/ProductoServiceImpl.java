package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ProductoDao;
import com.example.demo.entity.Producto;

public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private ProductoDao productoDao;
	
	@Override
	public List<Producto> findAll(){
		return (List<Producto>) productoDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Producto findById(Long id){
		return productoDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Producto save(Producto producto){
		return productoDao.save(producto);
	}

	@Override
	public Producto delete(Long id) {
		return null;
	}

}
