package com.ipartek.almacen.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ipartek.almacen.pojos.Categoria;
import com.ipartek.almacen.pojos.Producto;

public class DaoCategoriaSqlite extends DaoCategoriaJdbc implements DaoCategoria {

	private static final String SQL_SELECT = "SELECT * FROM categorias";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE id=?";
			
	private static final String SQL_SELECT_ID_PRODUCTOS = """
			SELECT c.id AS c_id, c.nombre AS c_nombre, c.descripcion AS c_descripcion, p.id AS p_id, p.nombre AS p_nombre, p.precio AS p_precio, p.fecha_caducidad AS p_fecha_caducidad
			FROM categorias c
			JOIN productos p ON c.id = p.id_categoria
			WHERE c.id=?
			""";

	public DaoCategoriaSqlite(String url, String user, String pass) {
		super(url, user, pass);
	}

	@Override
	public Iterable<Categoria> obtenerTodos() {
		return ejecutarConsulta(SQL_SELECT, pst -> {
		});
	}

	@Override
	public Categoria obtenerPorId(Long id) {
		return ejecutarConsultaUno(SQL_SELECT_ID, pst -> datosSentencia(pst, id));
	}

	@Override
	public Categoria obtenerPorIdConProductos(Long id) {
		try (Connection con = getConexion(); PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID_PRODUCTOS);) {

			pst.setLong(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				Categoria categoria = null;

				while (rs.next()) {
					if (categoria == null) {
						var nombre = rs.getString("c_nombre");
						var descripcion = rs.getString("c_descripcion");

						categoria = new Categoria(id, nombre, descripcion);
					}
					
					var idProducto = rs.getLong("p_id");
					var nombre = rs.getString("p_nombre");
					var precio = rs.getBigDecimal("p_precio");
					var fecha = rs.getDate("p_fecha_caducidad");
					var fechaCaducidad = fecha != null ? fecha.toLocalDate() : null;
					
					var producto = new Producto(idProducto, nombre, precio, fechaCaducidad, categoria);
					
					categoria.getProductos().add(producto);
				}

				return categoria;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la consulta", e);
		}
	}

}
