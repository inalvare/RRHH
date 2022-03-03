package com.formacionspringboot.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspringboot.apirest.entity.Departamento;
import com.formacionspringboot.apirest.entity.Departamento;
import com.formacionspringboot.apirest.service.DepartamentoService;

@RestController
@RequestMapping("/api")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService servicio;
	
	@GetMapping("/departamentos")
	public List<Departamento> index(){
		return servicio.findAll();
	}
	
	@GetMapping("/departamentos/{id}")
	public ResponseEntity<?>  findDepartamentoById(@PathVariable Long id) {
		Departamento departamento = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			departamento = servicio.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(departamento == null) {
			response.put("mensaje", "La departamento ID: " +id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Departamento>(departamento, HttpStatus.OK);
		
	}
	
	@PostMapping("/departamento/nuevo")
	public ResponseEntity<?> saveDepartamento(@RequestBody Departamento departamento) {
		 Departamento departamentoNueva= null;
		 Map<String, Object> response = new HashMap<>();
		 
		 try {
			 departamentoNueva = servicio.save(departamento);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		 response.put("mensaje", "La departamento ha sido creada con éxito!");
		 response.put("departamento", departamentoNueva);
		 
		 return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		 
	}
	
	@PutMapping("/departamento/{id}")
	public ResponseEntity<?> updateDepartamento(@RequestBody Departamento departamento, @PathVariable Long id) {
		Departamento departamentoActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(departamentoActual == null) {
			response.put("mensaje","Error: no se pudo editar, ela departamento ID: "+id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			departamentoActual.setNombre(departamento.getNombre());
			departamentoActual.setUbicacion(departamento.getUbicacion());
			
			servicio.save(departamentoActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un update a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		 response.put("mensaje", "La departamento ha sido actualizado con éxito!");
		 response.put("departamento", departamentoActual);
		 
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/departamentodelete/{id}")
	public ResponseEntity<?> deleteDepartamento(@PathVariable Long id) {
		
		Map<String, Object> response=new HashMap<>();
		
		Departamento departamentoActual= servicio.findById(id);
		
		response.put("departamento",departamentoActual);
		
		try {
			
			departamentoActual=servicio.delete(id);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el departamento");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return  new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El departamento se ha eliminado con exito");
		response.put("jefe",departamentoActual);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
