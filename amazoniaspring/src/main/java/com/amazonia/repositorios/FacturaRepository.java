package com.amazonia.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.amazonia.entidades.Factura;

public interface FacturaRepository extends CrudRepository<Factura, Long> {

	Factura findTopByOrderByNumeroDesc();

	Factura findByNumero(String numero);
}
