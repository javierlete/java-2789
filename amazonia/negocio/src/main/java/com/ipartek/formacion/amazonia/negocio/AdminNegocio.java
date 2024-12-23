package com.ipartek.formacion.amazonia.negocio;

import java.util.List;
import java.util.Map;

import com.ipartek.formacion.amazonia.entidades.Producto;

public interface AdminNegocio extends AnonimoNegocio {

	Producto anadirProducto(Producto producto);

	Producto modificarProducto(Producto producto);

	void borrarProducto(Long id);

	void borrarProductos(List<Long> ids);

	Map<String, String> validarProducto(Producto producto);
	
}
