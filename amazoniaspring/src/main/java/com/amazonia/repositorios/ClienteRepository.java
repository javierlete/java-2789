package com.amazonia.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.amazonia.entidades.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
