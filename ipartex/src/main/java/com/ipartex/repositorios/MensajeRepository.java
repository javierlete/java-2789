package com.ipartex.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartex.dtos.MensajeDTO;
import com.ipartex.entidades.Mensaje;
import com.ipartex.entidades.Usuario;

@RepositoryRestResource(collectionResourceRel = "mensajes", path = "mensajes")
public interface MensajeRepository extends CrudRepository<Mensaje, Long> {
	Iterable<Mensaje> findMensajesByOrderByFechaDesc();

	@Query("select m.id from Mensaje m join m.lesGusta u where m.id=:id and u.email=:email")
	Long comprobarMeGusta(@Param("id") Long id, @Param("email") String email);
	
	@Query("select new com.ipartex.dtos.MensajeDTO(m.id, m.usuario.nombre, m.usuario.usuarioIpartex, m.texto, (select case when count(u) > 0 then true else false end from Mensaje m2 join m.lesGusta u where m2.id = m.id and u.email = :email), size(m.lesGusta), m.fecha) from Mensaje m")
	Iterable<MensajeDTO> listarMensajes(@Param("email") String email);

	@Query("select new com.ipartex.dtos.MensajeDTO(m.id, m.usuario.nombre, m.usuario.usuarioIpartex, m.texto, :usuario member of m.lesGusta, size(m.lesGusta), m.fecha) from Mensaje m")
	Iterable<MensajeDTO> listarMensajes(@Param("usuario") Usuario usuario);
}
