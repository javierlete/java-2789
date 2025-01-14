package com.ipartex.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ipartex.dtos.MensajeDTO;
import com.ipartex.entidades.Mensaje;
import com.ipartex.entidades.Usuario;

public interface MensajeRepository extends CrudRepository<Mensaje, Long> {
	Iterable<Mensaje> findMensajesByOrderByFechaDesc();

	@Query("""
			select m.id 
			from Mensaje m 
			join m.lesGusta u 
			where m.id=:id and u.email=:email
			""")
	Long comprobarMeGusta(@Param("id") Long id, @Param("email") String email);
	
//	@Query("select new com.ipartex.dtos.MensajeDTO(m.id, m.usuario.id, m.usuario.nombre, m.usuario.usuarioIpartex, m.texto, (select case when count(u) > 0 then true else false end from Mensaje m2 join m.lesGusta u where m2.id = m.id and u.email = :email), size(m.lesGusta), m.fecha) from Mensaje m")
//	Iterable<MensajeDTO> listarMensajes(@Param("email") String email);

	@Query("""
			select new com.ipartex.dtos.MensajeDTO(
				m.id, 
				m.usuario.id, 
				m.usuario.nombre, 
				m.usuario.usuarioIpartex, 
				m.texto, 
				:usuario member of m.lesGusta, 
				size(m.lesGusta), 
				size(m.respuestas), 
				m.fecha
			) 
			from Mensaje m 
			where m.respuestaDe is null 
			order by m.fecha desc
			""")
	Iterable<MensajeDTO> listarMensajes(@Param("usuario") Usuario usuario);

	Iterable<Mensaje> findMensajesByRespuestaDeIsNullOrderByFechaDesc();

	@Query(""" 
			select new com.ipartex.dtos.MensajeDTO(
				m.id,
				m.usuario.id, 
				m.usuario.nombre, 
				m.usuario.usuarioIpartex, 
				m.texto, 
				:usuario member of m.lesGusta, 
				size(m.lesGusta), 
				size(m.respuestas), 
				m.fecha
			) from Mensaje m 
			where m.id = :id
			""")
	MensajeDTO buscarPorId(@Param("id") Long id, @Param("usuario") Usuario usuario);

	@Query("""
			select new com.ipartex.dtos.MensajeDTO(
				m.id, 
				m.usuario.id, 
				m.usuario.nombre, 
				m.usuario.usuarioIpartex, 
				m.texto, 
				:usuario member of m.lesGusta, 
				size(m.lesGusta), 
				size(m.respuestas), 
				m.fecha) 
			from Mensaje m 
			where
				m.respuestaDe.id = :id  
			order by m.fecha desc
			""")
	Iterable<MensajeDTO> buscarRespuestas(@Param("id") Long id, @Param("usuario") Usuario usuario);
}
