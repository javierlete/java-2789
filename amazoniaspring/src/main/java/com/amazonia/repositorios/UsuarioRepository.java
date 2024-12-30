package com.amazonia.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.amazonia.entidades.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	Usuario findByEmail(String email);
}
