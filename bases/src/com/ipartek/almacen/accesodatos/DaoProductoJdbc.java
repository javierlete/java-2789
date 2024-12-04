package com.ipartek.almacen.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Consumer;

import com.ipartek.almacen.pojos.Producto;

public abstract class DaoProductoJdbc extends DaoJdbc implements DaoProducto {

	protected DaoProductoJdbc(String url, String user, String pass) {
		super(url, user, pass);
	}

	protected static Producto filaAProducto(ResultSet rs) throws SQLException {
		Producto producto;
	
		var id = rs.getLong("id");
		var nombre = rs.getString("nombre");
		var precio = rs.getBigDecimal("precio");
		var fecha = rs.getDate("fecha_caducidad");
		var fechaCaducidad = fecha != null ? fecha.toLocalDate() : null;
	
		producto = new Producto(id, nombre, precio, fechaCaducidad);
		return producto;
	}

	protected static void productoAFila(Producto producto, PreparedStatement pst) {
		try {
			if (producto.getNombre() != null) {
				pst.setString(1, producto.getNombre());
				pst.setBigDecimal(2, producto.getPrecio());
				
				var fecha = producto.getFechaCaducidad() == null ? null : java.sql.Date.valueOf(producto.getFechaCaducidad());
				pst.setDate(3, fecha);
			}
	
			if (producto.getNombre() != null && producto.getId() != null) {
				pst.setLong(4, producto.getId());
			}
	
			if (producto.getNombre() == null && producto.getId() != null) {
				pst.setLong(1, producto.getId());
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se han asociado bien los datos a la consulta", e);
		}
	}

	protected Producto ejecutarConsultaUno(String sql, Consumer<PreparedStatement> codigo) {
		var i = ejecutarConsulta(sql, codigo).iterator();
		return i.hasNext() ? i.next() : null;
	}
	
	protected Iterable<Producto> ejecutarConsulta(String sql, Consumer<PreparedStatement> codigo) {
		try (Connection con = getConexion(); PreparedStatement pst = con.prepareStatement(sql);) {
	
			codigo.accept(pst);
	
			try (ResultSet rs = pst.executeQuery()) {
				Producto producto = null;
	
				var productos = new ArrayList<Producto>();
	
				while (rs.next()) {
					producto = filaAProducto(rs);
					productos.add(producto);
				}
	
				return productos;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error en la consulta", e);
		}
	}
}