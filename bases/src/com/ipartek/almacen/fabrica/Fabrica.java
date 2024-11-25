package com.ipartek.almacen.fabrica;

import java.io.IOException;
import java.util.Properties;

import com.ipartek.almacen.accesodatos.DaoProducto;
import com.ipartek.almacen.accesodatos.DaoProductoFicheroObjetos;
import com.ipartek.almacen.accesodatos.DaoProductoTreeMap;

public class Fabrica {
	private static DaoProducto daoProducto = null;
	
	static {
		try {
			Properties props = new Properties();
			props.load(Fabrica.class.getClassLoader().getResourceAsStream("almacen.properties"));
			
			var dao = props.getProperty("dao");
			var rutaFichero = props.getProperty("dao.fichero");
			
			daoProducto = switch(dao) {
			case "treemap"-> DaoProductoTreeMap.getInstancia();
			case "fichero" -> new DaoProductoFicheroObjetos(rutaFichero);
			default-> null;
			};
		} catch (IOException e) {
			throw new FabricaException("No se ha podido inicializar la fábrica", e);
		}
	}
	
	public static DaoProducto getDaoProducto() {
		return daoProducto;
	}
	
}
