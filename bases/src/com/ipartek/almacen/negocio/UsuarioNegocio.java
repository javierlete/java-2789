package com.ipartek.almacen.negocio;

import java.util.Collection;

import com.ipartek.almacen.pojos.Categoria;
import com.ipartek.almacen.pojos.Producto;
import com.ipartek.almacen.pojos.Usuario;

public interface UsuarioNegocio {
	Iterable<Producto> verProductos();
	Producto buscarProductoPorId(Long id);
	Iterable<Producto> buscarProductosPorNombre(String nombre);
	Collection<Categoria> verCategorias();
	Usuario autenticar(Usuario usuario);
}
