package com.ipartek.almacen.accesodatos;

import java.math.BigDecimal;

import com.ipartek.almacen.pojos.Producto;

public class DaoProductoSqlite extends DaoProductoJdbc implements DaoProducto {
	private static final String SQL_SELECT = "SELECT id, nombre, precio, fecha_caducidad FROM productos";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE id=?";
	private static final String SQL_SELECT_NOMBRE = SQL_SELECT + " WHERE nombre LIKE ?";
	private static final String SQL_SELECT_PRECIO = SQL_SELECT + " WHERE precio BETWEEN ? AND ?";
	private static final String SQL_SELECT_CATEGORIA = SQL_SELECT + " WHERE id_categoria=?";
	
	private static final String SQL_INSERT = "INSERT INTO productos (nombre, precio, fecha_caducidad) VALUES (?,?,?)";
	private static final String SQL_UPDATE = "UPDATE productos SET nombre=?, precio=?, fecha_caducidad=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM productos WHERE id=?";

	
	public DaoProductoSqlite(String url, String user, String pass) {
		super(url, user, pass);
	}

	@Override
	public Iterable<Producto> obtenerTodos() {
		return ejecutarConsulta(SQL_SELECT, pst -> datosSentencia(pst));
	}

	@Override
	public Producto obtenerPorId(Long id) {
		return ejecutarConsultaUno(SQL_SELECT_ID, pst -> datosSentencia(pst, id));
	}

	@Override
	public Iterable<Producto> obtenerPorNombreParcial(String nombreParcial) {
		return ejecutarConsulta(SQL_SELECT_NOMBRE, pst -> datosSentencia(pst, "%" + nombreParcial + "%"));
	}

	@Override
	public Iterable<Producto> obtenerPorPrecio(BigDecimal minimo, BigDecimal maximo) {
		return ejecutarConsulta(SQL_SELECT_PRECIO, pst -> datosSentencia(pst, minimo, maximo));
	}

	@Override
	public Iterable<Producto> obtenerPorIdCategoria(Long id) {
		return ejecutarConsulta(SQL_SELECT_CATEGORIA, pst -> datosSentencia(pst, id));
	}

	@Override
	public Producto insertar(Producto producto) {
		ejecutarCambio(SQL_INSERT, pst -> productoAFila(producto, pst));

		return producto;
	}

	@Override
	public Producto modificar(Producto producto) {
		ejecutarCambio(SQL_UPDATE, pst -> productoAFila(producto, pst));

		return producto;
	}

	@Override
	public void borrar(Long id) {
		ejecutarCambio(SQL_DELETE, pst -> productoAFila(new Producto(id, null, null, null), pst));
	}

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver de SQLite", e);
		}
	}

}
