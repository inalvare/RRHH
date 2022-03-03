package com.formacionspringboot.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspringboot.apirest.entity.Empleado;
import com.formacionspringboot.apirest.service.EmpleadoService;

@RestController
@RequestMapping("/api")
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService servicio;
	
	@GetMapping("/empleados")
	public List<Empleado> index(){
		return servicio.findAll();
	}
	
	@GetMapping("/empleados/{id}")
	public ResponseEntity<?>  findEmpleadoById(@PathVariable Long id) {
		Empleado empleado = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			empleado = servicio.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(empleado == null) {
			response.put("mensaje", "La empleado ID: " +id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
		
	}
	
	@PostMapping("/empleados/nueva")
	public ResponseEntity<?> saveEmpleado(@RequestBody Empleado empleado) {
		 Empleado empleadoNueva= null;
		 Map<String, Object> response = new HashMap<>();
		 
		 try {
			 empleadoNueva = servicio.save(empleado);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		 response.put("mensaje", "La empleado ha sido creada con éxito!");
		 response.put("empleado", empleadoNueva);
		 
		 return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		 
	}
	
	@PutMapping("/empleados/{id}")
	public ResponseEntity<?> updateEmpleado(@RequestBody Empleado empleado, @PathVariable Long id) {
		Empleado empleadoActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(empleadoActual == null) {
			response.put("mensaje","Error: no se pudo editar, ela empleado ID: "+id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			empleadoActual.setDNI(empleado.getDNI());
			empleadoActual.setNombre(empleado.getNombre());
			empleadoActual.setSalario(empleado.getSalario());
			empleadoActual.setTelefono(empleado.getTelefono());
			empleadoActual.setDepartamento(empleado.getDepartamento());
			
			servicio.save(empleadoActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar un update a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		 response.put("mensaje", "La empleado ha sido actualizado con éxito!");
		 response.put("empleado", empleadoActual);
		 
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
}
