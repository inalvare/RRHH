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

import com.formacionspringboot.apirest.entity.Jefe;
import com.formacionspringboot.apirest.entity.Jefe;
import com.formacionspringboot.apirest.service.JefeService;

@RestController
@RequestMapping("/api")
public class JefeController {
	
	@Autowired
	private JefeService servicio;
	
	@GetMapping("/jefes")
	public List<Jefe> index(){
		return servicio.findAll();
	}
	
	@GetMapping("/jefes/{id}")
	public ResponseEntity<?>  findJefeById(@PathVariable Long id) {
		Jefe jefe = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			jefe = servicio.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(jefe == null) {
			response.put("mensaje", "La jefe ID: " +id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Jefe>(jefe, HttpStatus.OK);
		
	}
	
	@PostMapping("/jefe/nuevo")
	public ResponseEntity<?> saveJefe(@RequestBody Jefe jefe) {
		 Jefe jefeNueva= null;
		 Map<String, Object> response = new HashMap<>();
		 
		 try {
			 jefeNueva = servicio.save(jefe);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		 response.put("mensaje", "La jefe ha sido creada con éxito!");
		 response.put("jefe", jefeNueva);
		 
		 return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		 
	}
	
	@PutMapping("/jefe/{id}")
	public ResponseEntity<?> updateJefe(@RequestBody Jefe jefe, @PathVariable Long id) {
		Jefe jefeActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(jefeActual == null) {
			response.put("mensaje","Error: no se pudo editar, ela jefe ID: "+id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			jefeActual.setDni(jefe.getDni());
			jefeActual.setNombre(jefe.getNombre());
			jefeActual.setSalario(jefe.getSalario());
			jefeActual.setTelefono(jefe.getTelefono());
			jefeActual.setDepartamento(jefe.getDepartamento());
			
			servicio.save(jefeActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un update a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		 response.put("mensaje", "La jefe ha sido actualizado con éxito!");
		 response.put("jefe", jefeActual);
		 
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/jefedelete/{id}")
	public ResponseEntity<?> deleteJefe(@PathVariable Long id) {
		Jefe jefeActual= servicio.findById(id);
		
		Map<String, Object> response=new HashMap<>();
		
		response.put("jefe",jefeActual);
		
		if(jefeActual==null) {
			response.put("mensaje", "Error: no se pudo eliminar");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		try {
			jefeActual=servicio.delete(id);
			
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el jefe");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El jefe se ha eliminado con exito");
		response.put("jefe",jefeActual);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
