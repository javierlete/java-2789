package com.ipartek.almacen.accesodatos;

import com.ipartek.almacen.pojos.Categoria;

public interface DaoCategoria extends Dao<Categoria> {
	Categoria obtenerPorIdConProductos(Long id);
}
