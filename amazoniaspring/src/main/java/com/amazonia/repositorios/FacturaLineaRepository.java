package com.amazonia.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.amazonia.entidades.Factura;

public interface FacturaLineaRepository extends CrudRepository<Factura.Linea, Long> {
}
