package com.ipartek.almacen.fabrica;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import com.ipartek.almacen.accesodatos.DaoProducto;

public class Fabrica {
	private static DaoProducto daoProducto = null;
	
	static {
		try {
			Properties props = new Properties();
			props.load(Fabrica.class.getClassLoader().getResourceAsStream("almacen.properties"));
			
			var dao = props.getProperty("dao.implementacion");
			var url = props.getProperty("dao.url");
			var user = props.getProperty("dao.user");
			var pass = props.getProperty("dao.pass");
			
			daoProducto = (DaoProducto) Class.forName(dao).getConstructor(String.class, String.class, String.class).newInstance(url, user, pass);
		} catch (IOException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			throw new FabricaException("No se ha podido inicializar la fábrica", e);
		}
	}
	
	public static DaoProducto getDaoProducto() {
		return daoProducto;
	}
	
}
