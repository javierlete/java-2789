package com.amazonia.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.amazonia.entidades.Rol;

public interface RolRepository extends CrudRepository<Rol, Long> {
	Rol findByNombre(String nombre);
}
