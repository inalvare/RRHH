package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Producto;
import com.example.demo.service.ProductoService;

@RestController
@RequestMapping("/api")
public class ProductoRestController {
	
	@Autowired
	private ProductoService servicio;
	
	@GetMapping({"/producots","/todos"})
	public List<Producto> index(){
		return servicio.findAll();
	}
	
	@GetMapping("productos/{id}")
	public Producto findProductoById(@PathVariable Long id) {
		return servicio.findById(id);
	}
	
	
	@PostMapping("/producto")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto saveProducto(@RequestBody Producto producto) {
		return servicio.save(producto);
	}
	
	
	@PutMapping("producto/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto updateProducto(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoUpdate=servicio.findById(id);
		
		productoUpdate.setTipo(producto.getTipo());
		productoUpdate.setCantidad(producto.getCantidad());
		productoUpdate.setPrecio(producto.getPrecio());
		productoUpdate.setMarca(producto.getMarca());
		productoUpdate.setDescripcion(producto.getDescripcion());
		productoUpdate.setCreatedAt(producto.getCreatedAt());
		
		return servicio.save(productoUpdate);
	}
	
	@DeleteMapping("/producto/{id}")
	public void deleteProducto(@PathVariable Long id) {
		servicio.delete(id);
	}
}
