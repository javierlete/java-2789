package com.ipartek.almacen.accesodatos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.almacen.pojos.Producto;

public class DaoProductoSqlite implements DaoProducto {
	private static final String SQL_SELECT = "SELECT id, nombre, precio, fecha_caducidad FROM productos";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE id=?";
	private static final String SQL_SELECT_NOMBRE = SQL_SELECT + " WHERE nombre LIKE ?";
	private static final String SQL_SELECT_PRECIO = SQL_SELECT + " WHERE precio BETWEEN ? AND ?";
	private static final String SQL_INSERT = "INSERT INTO productos (nombre, precio, fecha_caducidad) VALUES (?,?,?)";
	private static final String SQL_UPDATE = "UPDATE productos SET nombre=?, precio=?, fecha_caducidad=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM productos WHERE id=?";

	private String url;
	private String user;
	private String pass;

	public DaoProductoSqlite(String url, String user, String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
	}

	private Connection getConexion() {
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido conectar con la base de datos", e);
		}
	}

	@Override
	public Iterable<Producto> obtenerTodos() {
		try (Connection con = getConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT);
				ResultSet rs = pst.executeQuery()) {
			var productos = new ArrayList<Producto>();

			while (rs.next()) {
				var id = rs.getLong("id");
				var nombre = rs.getString("nombre");
				var precio = rs.getBigDecimal("precio");
				var fecha = rs.getDate("fecha_caducidad");
				var fechaCaducidad = fecha != null ? fecha.toLocalDate() : null;

				var producto = new Producto(id, nombre, precio, fechaCaducidad);

				productos.add(producto);
			}

			return productos;
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la consulta", e);
		}
	}

	@Override
	public Producto obtenerPorId(Long id) {
		try (Connection con = getConexion(); PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID);) {

			pst.setLong(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				Producto producto = null;

				if (rs.next()) {
					var nombre = rs.getString("nombre");
					var precio = rs.getBigDecimal("precio");
					var fecha = rs.getDate("fecha_caducidad");
					var fechaCaducidad = fecha != null ? fecha.toLocalDate() : null;

					producto = new Producto(id, nombre, precio, fechaCaducidad);
				}

				return producto;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la consulta", e);
		}
	}

	@Override
	public Producto insertar(Producto producto) {
		try (Connection con = getConexion(); PreparedStatement pst = con.prepareStatement(SQL_INSERT);) {

			pst.setString(1, producto.getNombre());
			pst.setBigDecimal(2, producto.getPrecio());
			pst.setDate(3, java.sql.Date.valueOf(producto.getFechaCaducidad()));

			pst.executeUpdate();

			return producto;
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la consulta", e);
		}
	}

	@Override
	public Producto modificar(Producto producto) {
		try (Connection con = getConexion(); PreparedStatement pst = con.prepareStatement(SQL_UPDATE);) {

			pst.setString(1, producto.getNombre());
			pst.setBigDecimal(2, producto.getPrecio());
			pst.setDate(3, java.sql.Date.valueOf(producto.getFechaCaducidad()));
			pst.setLong(4, producto.getId());

			int num = pst.executeUpdate();

			if (num == 0) {
				throw new AccesoDatosException("No se ha encontrado el producto a modificar");
			}

			if (num > 1) {
				throw new AccesoDatosException("¡Se ha modificado más de un producto!");
			}

			return producto;
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la consulta", e);
		}
	}

	@Override
	public void borrar(Long id) {
		try (Connection con = getConexion(); PreparedStatement pst = con.prepareStatement(SQL_DELETE);) {

			pst.setLong(1, id);

			int num = pst.executeUpdate();

			if (num == 0) {
				throw new AccesoDatosException("No se ha encontrado el producto a borrar");
			}

			if (num > 1) {
				throw new AccesoDatosException("¡Se ha borrado más de un producto!");
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la consulta", e);
		}
	}

	@Override
	public Iterable<Producto> obtenerPorNombreParcial(String nombreParcial) {
		try (Connection con = getConexion(); PreparedStatement pst = con.prepareStatement(SQL_SELECT_NOMBRE);) {

			pst.setString(1, "%" + nombreParcial + "%");

			try (ResultSet rs = pst.executeQuery()) {
				Producto producto = null;

				var productos = new ArrayList<Producto>();

				while (rs.next()) {
					var id = rs.getLong("id");
					var nombre = rs.getString("nombre");
					var precio = rs.getBigDecimal("precio");
					var fecha = rs.getDate("fecha_caducidad");
					var fechaCaducidad = fecha != null ? fecha.toLocalDate() : null;

					producto = new Producto(id, nombre, precio, fechaCaducidad);
					productos.add(producto);
				}

				return productos;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la consulta", e);
		}
	}

	@Override
	public Iterable<Producto> obtenerPorPrecio(BigDecimal minimo, BigDecimal maximo) {
		try (Connection con = getConexion(); PreparedStatement pst = con.prepareStatement(SQL_SELECT_PRECIO);) {

			pst.setBigDecimal(1, minimo);
			pst.setBigDecimal(2, maximo);

			try (ResultSet rs = pst.executeQuery()) {
				Producto producto = null;

				var productos = new ArrayList<Producto>();

				while (rs.next()) {
					var id = rs.getLong("id");
					var nombre = rs.getString("nombre");
					var precio = rs.getBigDecimal("precio");
					var fecha = rs.getDate("fecha_caducidad");
					var fechaCaducidad = fecha != null ? fecha.toLocalDate() : null;

					producto = new Producto(id, nombre, precio, fechaCaducidad);
					productos.add(producto);
				}

				return productos;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la consulta", e);
		}
	}

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver de SQLite", e);
		}
	}

}
