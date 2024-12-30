package com.amazonia.servicios;

import java.util.List;
import java.util.Map;

import com.amazonia.entidades.Producto;

public interface AdminService extends AnonimoService {

	Producto anadirProducto(Producto producto);

	Producto modificarProducto(Producto producto);

	void borrarProducto(Long id);

	void borrarProductos(List<Long> ids);

	Map<String, String> validarProducto(Producto producto);
	
}
