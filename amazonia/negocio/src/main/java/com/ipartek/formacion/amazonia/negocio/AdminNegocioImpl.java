package com.ipartek.formacion.amazonia.negocio;

import com.ipartek.formacion.amazonia.accesodatos.ProductoDao;
import com.ipartek.formacion.amazonia.entidades.Producto;
import com.ipartek.formacion.amazonia.fabrica.Fabrica;

public class AdminNegocioImpl extends AnonimoNegocioImpl implements AdminNegocio {
	private static final ProductoDao PRODUCTO_DAO = Fabrica.getProductoDao();
	
	@Override
	public Producto anadirProducto(Producto producto) {
		return PRODUCTO_DAO.insertar(producto);
	}

	@Override
	public Producto modificarProducto(Producto producto) {
		return PRODUCTO_DAO.modificar(producto);
	}

}
