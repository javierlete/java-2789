package com.amazonia.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.amazonia.entidades.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {
	Producto findByUrl(String url);
}
