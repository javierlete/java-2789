package com.ipartek.almacen.negocio;

import com.ipartek.almacen.accesodatos.DaoProducto;
import com.ipartek.almacen.fabrica.Fabrica;
import com.ipartek.almacen.pojos.Producto;

public class AdminNegocioImpl extends UsuarioNegocioImpl implements AdminNegocio {
	private static final DaoProducto DAO_PRODUCTO = Fabrica.getDaoProducto();

	@Override
	public Producto altaProducto(Producto producto) {
		if (producto.isCorrecto()) {
			return DAO_PRODUCTO.insertar(producto);
		} else {
			throw new NegocioException("El producto no es válido");
		}
	}

	@Override
	public Producto modificarProducto(Producto producto) {
		if (producto.isCorrecto()) {
			return DAO_PRODUCTO.modificar(producto);
		} else {
			throw new NegocioException("El producto no es válido");
		}
	}

	@Override
	public void borrarProducto(Long id) {
		DAO_PRODUCTO.borrar(id);
	}

}
