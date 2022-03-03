package com.formacionspringboot.apirest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspringboot.apirest.entity.Departamento;

@Repository
public interface DepartamentoDao extends CrudRepository<Departamento, Long>{

}
