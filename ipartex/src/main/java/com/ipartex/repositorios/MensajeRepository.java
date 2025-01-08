package com.ipartex.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.ipartex.entidades.Mensaje;

public interface MensajeRepository extends CrudRepository<Mensaje, Long> {
	Iterable<Mensaje> findMensajesByOrderByFechaDesc(); 

}
