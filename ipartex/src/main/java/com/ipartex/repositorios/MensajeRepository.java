package com.ipartex.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ipartex.entidades.Mensaje;

public interface MensajeRepository extends CrudRepository<Mensaje, Long> {
	Iterable<Mensaje> findMensajesByOrderByFechaDesc(); 

	@Query("select m.id from Mensaje m join m.lesGusta u where m.id=:id and u.email=:email")
	Long comprobarMeGusta(@Param("id") Long id, @Param("email") String email);
}
