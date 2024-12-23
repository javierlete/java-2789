package com.ipartek.formacion.amazonia.accesodatos;

import java.util.List;

import com.ipartek.formacion.amazonia.entidades.Producto;

public interface ProductoDao extends Dao<Producto> {

	Producto obtenerPorUrl(String url);

	void borrar(List<Long> ids);

}
