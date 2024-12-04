package com.ipartek.almacen.negocio;

import com.ipartek.almacen.accesodatos.DaoProducto;
import com.ipartek.almacen.fabrica.Fabrica;
import com.ipartek.almacen.pojos.Producto;

public class AdminNegocioImpl extends UsuarioNegocioImpl implements AdminNegocio {
	private static final DaoProducto DAO_PRODUCTO = Fabrica.getDaoProducto();
	
	@Override
	public Producto altaProducto(Producto producto) {
		return DAO_PRODUCTO.insertar(producto);
	}

	@Override
	public Producto modificarProducto(Producto producto) {
		return DAO_PRODUCTO.modificar(producto);
	}

	@Override
	public void borrarProducto(Long id) {
		DAO_PRODUCTO.borrar(id);
	}

}
