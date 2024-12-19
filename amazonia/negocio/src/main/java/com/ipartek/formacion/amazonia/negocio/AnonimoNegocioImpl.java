package com.ipartek.formacion.amazonia.negocio;

import java.util.Collection;

import com.ipartek.formacion.amazonia.accesodatos.ProductoDao;
import com.ipartek.formacion.amazonia.accesodatos.UsuarioDao;
import com.ipartek.formacion.amazonia.entidades.Producto;
import com.ipartek.formacion.amazonia.entidades.Usuario;
import com.ipartek.formacion.amazonia.fabrica.Fabrica;

public class AnonimoNegocioImpl implements AnonimoNegocio {
	UsuarioDao usuarioDao = Fabrica.getUsuarioDao();
	ProductoDao productoDao = Fabrica.getProductoDao();
	
	@Override
	public Collection<Producto> listarProductos() {
		return productoDao.obtenerTodos();
	}

	@Override
	public Usuario iniciarSesion(Usuario usuario) {
		var usuarioCompleto = usuarioDao.obtenerPorEmail(usuario.getEmail());
		
		if(usuarioCompleto != null && verificarPassword(usuario.getPassword(), usuarioCompleto.getPassword())) {
			return usuarioCompleto;
		} else {
			return null;
		}
	}

	private boolean verificarPassword(String passwordRecibida, String passwordAlmacenada) {
		return passwordAlmacenada.equals(passwordRecibida);
	}

	@Override
	public Producto detalleProducto(Long id) {
		return productoDao.obtenerPorId(id);
	}

	
}
