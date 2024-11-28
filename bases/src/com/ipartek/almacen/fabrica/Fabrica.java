package com.ipartek.almacen.fabrica;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import com.ipartek.almacen.accesodatos.DaoCategoria;
import com.ipartek.almacen.accesodatos.DaoProducto;

public class Fabrica {
	private static DaoProducto daoProducto = null;
	private static DaoCategoria daoCategoria = null;
	
	static {
		try {
			Properties props = new Properties();
			props.load(Fabrica.class.getClassLoader().getResourceAsStream("almacen.properties"));
			
			var daoProductoClase = props.getProperty("dao.producto.implementacion");
			var daoCategoriaClase = props.getProperty("dao.categoria.implementacion");
			
			var url = props.getProperty("dao.url");
			var user = props.getProperty("dao.user");
			var pass = props.getProperty("dao.pass");
			
			daoProducto = (DaoProducto) Class.forName(daoProductoClase).getConstructor(String.class, String.class, String.class).newInstance(url, user, pass);
			daoCategoria = (DaoCategoria) Class.forName(daoCategoriaClase).getConstructor(String.class, String.class, String.class).newInstance(url, user, pass);
		} catch (IOException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			throw new FabricaException("No se ha podido inicializar la fábrica", e);
		}
	}
	
	public static DaoProducto getDaoProducto() {
		return daoProducto;
	}

	public static DaoCategoria getDaoCategoria() {
		return daoCategoria;
	}
	
}
