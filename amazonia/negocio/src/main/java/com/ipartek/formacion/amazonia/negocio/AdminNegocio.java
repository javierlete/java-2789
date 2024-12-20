package com.ipartek.formacion.amazonia.negocio;

import com.ipartek.formacion.amazonia.entidades.Producto;

public interface AdminNegocio extends AnonimoNegocio {

	Producto anadirProducto(Producto producto);

	Producto modificarProducto(Producto producto);
	
}
