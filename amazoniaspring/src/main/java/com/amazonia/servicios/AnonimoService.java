package com.amazonia.servicios;

import org.springframework.validation.annotation.Validated;

import com.amazonia.entidades.Carrito;
import com.amazonia.entidades.Producto;
import com.amazonia.entidades.Usuario;
import com.amazonia.entidades.gruposvalidacion.UsuarioRegistro;

public interface AnonimoService {
	default Usuario iniciarSesion(Usuario usuario) {
		throw new NegocioException("NO IMPLEMENTADO");
	}

	default Iterable<Producto> listarProductos() {
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

	void registrarUsuario(@Validated(UsuarioRegistro.class) Usuario usuario);
}
