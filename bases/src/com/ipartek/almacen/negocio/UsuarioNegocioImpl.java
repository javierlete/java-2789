package com.ipartek.almacen.negocio;

import java.util.Collection;

import com.ipartek.almacen.accesodatos.AccesoDatosException;
import com.ipartek.almacen.accesodatos.DaoCategoria;
import com.ipartek.almacen.accesodatos.DaoProducto;
import com.ipartek.almacen.fabrica.Fabrica;
import com.ipartek.almacen.pojos.Categoria;
import com.ipartek.almacen.pojos.Producto;

import lombok.extern.java.Log;

@Log
public class UsuarioNegocioImpl implements UsuarioNegocio {
	
	private final DaoProducto daoProductos = Fabrica.getDaoProducto();
	private final DaoCategoria daoCategorias = Fabrica.getDaoCategoria();
	
	@Override
	public Collection<Producto> verProductos() {
		return daoProductos.obtenerTodos();
	}

	@Override
	public Producto buscarProductoPorId(Long id) {
		try {
			log.info("Se ha pedido un producto por el id: " + id);
			return daoProductos.obtenerPorId(id);
		} catch (AccesoDatosException e) {
			throw new NegocioException("Error al buscar el producto por id " + id, e);
		}
	}

	@Override
	public Iterable<Producto> buscarProductosPorNombre(String nombre) {
		// daoBusquedas.insertar(new Busqueda(LocalDateTime.now(), nombre));
		log.info("BUSQUEDA: " + nombre);
		return daoProductos.obtenerPorNombreParcial(nombre);
	}

	@Override
	public Collection<Categoria> verCategorias() {
		return daoCategorias.obtenerTodos();
	}
}
