package com.ipartek.almacen.accesodatos;

import java.util.Collection;

public interface Dao<T> {
	default Collection<T> obtenerTodos() {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}

	default T obtenerPorId(Long id) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}

	default T insertar(T objeto) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}

	default T modificar(T objeto) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}

	default void borrar(Long id) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}
}
