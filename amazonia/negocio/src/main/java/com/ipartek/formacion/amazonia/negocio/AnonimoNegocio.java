package com.ipartek.formacion.amazonia.negocio;

import java.util.Collection;

import com.ipartek.formacion.amazonia.entidades.Carrito;
import com.ipartek.formacion.amazonia.entidades.Producto;
import com.ipartek.formacion.amazonia.entidades.Usuario;

public interface AnonimoNegocio {
	default Usuario iniciarSesion(Usuario usuario) {
		throw new NegocioException("NO IMPLEMENTADO");
	}

	default Collection<Producto> listarProductos() {
		throw new NegocioException("NO IMPLEMENTADO");
	}

	default Producto detalleProducto(Long id) {
		throw new NegocioException("NO IMPLEMENTADO");
	}

	default Carrito anadirProductoCarrito(Producto producto) {
		throw new NegocioException("NO IMPLEMENTADO");
	}

	default Carrito cambiarCantidadProductoCarrito(Producto producto, int cantidad) {
		throw new NegocioException("NO IMPLEMENTADO");
	}

	default Carrito vaciarCarrito(Carrito carrito) {
		throw new NegocioException("NO IMPLEMENTADO");
	}

	default Producto detalleProducto(String url) {
		throw new NegocioException("NO IMPLEMENTADO");
	}
}
