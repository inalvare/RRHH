package com.formacionspringboot.apirest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="empleado")
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long DNI;
	
	@Column(name="nombre", nullable = false, length=50)
	private String nombre;
	
	@Column(name="salario", nullable = false, length=50)
	private String salario;
	
	@Column(name="telefono", nullable = false, length=50)
	private String telefono;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="codDep")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Departamento departamento;

	public Long getDNI() {
		return DNI;
	}

	public void setDNI(Long dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento cliente) {
		this.departamento = cliente;
	}
	
	
}
