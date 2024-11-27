package com.ipartek.almacen.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Consumer;

public abstract class DaoJdbc {

	private String url;
	private String user;
	private String pass;

	protected DaoJdbc(String url, String user, String pass) {
		super();
		this.url = url;
		this.user = user;
		this.pass = pass;
	}
	
	protected Connection getConexion() {
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido conectar con la base de datos", e);
		}
	}

	protected static void datosSentencia(PreparedStatement pst, Object... datos) {
		try {
			for (int i = 0; i < datos.length; i++) {
				pst.setObject(i + 1, datos[i]);
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se puede asociar el parámetro", e);
		}
	}

	protected void ejecutarCambio(String sql, Consumer<PreparedStatement> codigo) {
		try (Connection con = getConexion(); PreparedStatement pst = con.prepareStatement(sql);) {
	
			codigo.accept(pst);
	
			int num = pst.executeUpdate();
	
			if (num == 0) {
				throw new AccesoDatosException("No se ha encontrado el producto");
			}
	
			if (num > 1) {
				throw new AccesoDatosException("¡Se ha cambiado más de un producto!");
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la consulta", e);
		}
	}

}