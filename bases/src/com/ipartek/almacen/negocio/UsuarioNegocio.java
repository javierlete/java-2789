package com.ipartek.almacen.negocio;

import com.ipartek.almacen.pojos.Producto;

public interface UsuarioNegocio {
	Iterable<Producto> verProductos();
	Producto buscarProductoPorId(Long id);
	Iterable<Producto> buscarProductosPorNombre(String nombre);
}
