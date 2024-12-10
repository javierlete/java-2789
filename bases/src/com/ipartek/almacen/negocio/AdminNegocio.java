package com.ipartek.almacen.negocio;

import java.util.Map;

import com.ipartek.almacen.pojos.Producto;

public interface AdminNegocio extends UsuarioNegocio {
	Producto altaProducto(Producto producto);
	Producto modificarProducto(Producto producto);
	void borrarProducto(Long id);
	boolean validar(Producto producto);
	Map<String, String> errores(Producto producto);
}
