package com.ipartek.almacen.accesodatos;

import java.math.BigDecimal;
import java.util.Collection;

import com.ipartek.almacen.pojos.Producto;

public interface DaoProducto extends Dao<Producto> {
	Iterable<Producto> obtenerPorNombreParcial(String nombre);
	Iterable<Producto> obtenerPorPrecio(BigDecimal minimo, BigDecimal maximo);

	default Collection<Producto> obtenerPorIdCategoria(Long id) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}
}
