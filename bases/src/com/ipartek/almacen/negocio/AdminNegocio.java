package com.ipartek.almacen.negocio;

import com.ipartek.almacen.pojos.Producto;

public interface AdminNegocio extends UsuarioNegocio {
	Producto altaProducto(Producto producto);
	Producto modificarProducto(Producto producto);
	void borrarProducto(Long id);
}
