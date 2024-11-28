package com.ipartek.almacen.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Consumer;

import com.ipartek.almacen.pojos.Categoria;

public abstract class DaoCategoriaJdbc extends DaoJdbc implements DaoCategoria {

	protected DaoCategoriaJdbc(String url, String user, String pass) {
		super(url, user, pass);
	}

	protected static Categoria filaACategoria(ResultSet rs) throws SQLException {
		var id = rs.getLong("id");
		var nombre = rs.getString("nombre");
		var descripcion = rs.getString("descripcion");
	
		return new Categoria(id, nombre, descripcion);
	}

	protected Categoria ejecutarConsultaUno(String sql, Consumer<PreparedStatement> codigo) {
		var i = ejecutarConsulta(sql, codigo).iterator();
		return i.hasNext() ? i.next() : null;
	}
	
	protected Iterable<Categoria> ejecutarConsulta(String sql, Consumer<PreparedStatement> codigo) {
		try (Connection con = getConexion(); PreparedStatement pst = con.prepareStatement(sql);) {
	
			codigo.accept(pst);
	
			try (ResultSet rs = pst.executeQuery()) {
				Categoria categoria = null;
	
				var categorias = new ArrayList<Categoria>();
	
				while (rs.next()) {
					categoria = filaACategoria(rs);
					categorias.add(categoria);
				}
	
				return categorias;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la consulta", e);
		}
	}
}