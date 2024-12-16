package com.ipartek.formacion.amazonia.fabrica;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import com.ipartek.formacion.amazonia.accesodatos.ClienteDao;
import com.ipartek.formacion.amazonia.accesodatos.FacturaDao;
import com.ipartek.formacion.amazonia.accesodatos.ProductoDao;
import com.ipartek.formacion.amazonia.accesodatos.UsuarioDao;

public class Fabrica {
	private static ClienteDao clienteDao = null;
	private static FacturaDao facturaDao = null;
	private static ProductoDao productoDao = null;
	private static UsuarioDao usuarioDao = null;

	static {
		try {
			Properties props = new Properties();
			props.load(Fabrica.class.getClassLoader().getResourceAsStream("amazonia.properties"));

			var clienteDaoClase = props.getProperty("dao.cliente.implementacion");
			var facturaDaoClase = props.getProperty("dao.factura.implementacion");
			var productoDaoClase = props.getProperty("dao.producto.implementacion");
			var usuarioDaoClase = props.getProperty("dao.usuario.implementacion");

			var url = props.getProperty("dao.url");
			var user = props.getProperty("dao.user");
			var pass = props.getProperty("dao.pass");

			clienteDao = (ClienteDao) Class.forName(clienteDaoClase)
					.getConstructor(String.class, String.class, String.class).newInstance(url, user, pass);
			facturaDao = (FacturaDao) Class.forName(facturaDaoClase)
					.getConstructor(String.class, String.class, String.class).newInstance(url, user, pass);
			productoDao = (ProductoDao) Class.forName(productoDaoClase)
					.getConstructor(String.class, String.class, String.class).newInstance(url, user, pass);
			usuarioDao = (UsuarioDao) Class.forName(usuarioDaoClase)
					.getConstructor(String.class, String.class, String.class).newInstance(url, user, pass);

		} catch (IOException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			throw new FabricaException("No se ha podido inicializar la fábrica", e);
		}
	}

	public static ClienteDao getClienteDao() {
		return clienteDao;
	}

	public static FacturaDao getFacturaDao() {
		return facturaDao;
	}
	
	public static ProductoDao getProductoDao() {
		return productoDao;
	}
	
	public static UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}
	
}
