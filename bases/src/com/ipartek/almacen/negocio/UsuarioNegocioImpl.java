package com.ipartek.almacen.negocio;

import java.util.Collection;

import com.ipartek.almacen.accesodatos.AccesoDatosException;
import com.ipartek.almacen.accesodatos.DaoCategoria;
import com.ipartek.almacen.accesodatos.DaoProducto;
import com.ipartek.almacen.accesodatos.DaoRol;
import com.ipartek.almacen.accesodatos.DaoUsuario;
import com.ipartek.almacen.fabrica.Fabrica;
import com.ipartek.almacen.pojos.Categoria;
import com.ipartek.almacen.pojos.Producto;
import com.ipartek.almacen.pojos.Usuario;

import lombok.extern.java.Log;

@Log
public class UsuarioNegocioImpl implements UsuarioNegocio {
	
	private final DaoProducto daoProductos = Fabrica.getDaoProducto();
	private final DaoCategoria daoCategorias = Fabrica.getDaoCategoria();
	private final DaoUsuario daoUsuarios = Fabrica.getDaoUsuario();
	private final DaoRol daoRol = Fabrica.getDaoRol();
	
	@Override
	public Collection<Producto> verProductos() {
		return daoProductos.obtenerTodos();
	}

	@Override
	public Producto buscarProductoPorId(Long id) {
		try {
			log.info("Se ha pedido un producto por el id: " + id);
			return daoProductos.obtenerPorId(id);
		} catch (AccesoDatosException e) {
			throw new NegocioException("Error al buscar el producto por id " + id, e);
		}
	}

	@Override
	public Iterable<Producto> buscarProductosPorNombre(String nombre) {
		// daoBusquedas.insertar(new Busqueda(LocalDateTime.now(), nombre));
		log.info("BUSQUEDA: " + nombre);
		return daoProductos.obtenerPorNombreParcial(nombre);
	}

	@Override
	public Collection<Categoria> verCategorias() {
		return daoCategorias.obtenerTodos();
	}

	@Override
	public Usuario autenticar(Usuario usuario) {
		Usuario usuarioEmail = daoUsuarios.obtenerPorEmail(usuario.getEmail());
		
		if(usuarioEmail != null && validarPassword(usuario.getPassword(), usuarioEmail.getPassword())) {
			return usuarioEmail;
		}
		
		return null;
	}

	private boolean validarPassword(String usuarioPassword, String usuarioEmailPassword) {
		return usuarioEmailPassword.equals(usuarioPassword);
	}

	@Override
	public Usuario registrar(Usuario usuarioNuevo) {
		try {
			usuarioNuevo.setRol(daoRol.obtenerPorId(2L));
			return daoUsuarios.insertar(usuarioNuevo);
		} catch (Exception e) {
			throw new NegocioException("Ha habido un error en la inserción", e);
		}
	}
}
