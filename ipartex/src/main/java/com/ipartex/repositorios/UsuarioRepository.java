package com.ipartex.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.ipartex.entidades.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Usuario findByEmail(String email);

}
