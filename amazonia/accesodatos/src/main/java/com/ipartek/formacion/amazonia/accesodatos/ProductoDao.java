package com.ipartek.formacion.amazonia.accesodatos;

import com.ipartek.formacion.amazonia.entidades.Producto;

public interface ProductoDao extends Dao<Producto> {

	Producto obtenerPorUrl(String url);

}
