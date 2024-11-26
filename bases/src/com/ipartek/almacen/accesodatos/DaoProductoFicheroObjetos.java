package com.ipartek.almacen.accesodatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.TreeMap;
import java.util.function.Supplier;

import com.ipartek.almacen.pojos.Producto;

public class DaoProductoFicheroObjetos extends DaoProductoTreeMap implements DaoProducto {
	private String rutaFichero;

	public DaoProductoFicheroObjetos(String rutaFichero) {
		this.rutaFichero = rutaFichero;

		if (!new File(rutaFichero).exists()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaFichero))) {
				oos.writeObject(new TreeMap<Long, Producto>());
			} catch (IOException e) {
				throw new AccesoDatosException("No se ha podido leer el fichero de objetos", e);
			}
		}
	}
	
	public DaoProductoFicheroObjetos(String url, String user, String pass) {
		this(url);
	}

	@Override
	public Iterable<Producto> obtenerTodos() {
		return lectura(() -> super.obtenerTodos());
	}

	@Override
	public Producto obtenerPorId(Long id) {
		return lectura(() -> super.obtenerPorId(id));
	}

	@Override
	public Producto insertar(Producto producto) {
		return lecturaEscritura(() -> super.insertar(producto));
	}

	@Override
	public Producto modificar(Producto producto) {
		return lecturaEscritura(() -> super.modificar(producto));
	}

	@Override
	public void borrar(Long id) {
		lecturaEscritura(() -> {
			super.borrar(id);
			return null;
		});
	}

	@Override
	public Iterable<Producto> obtenerPorNombreParcial(String nombre) {
		return lectura(() -> super.obtenerPorNombreParcial(nombre));
	}

	@Override
	public Iterable<Producto> obtenerPorPrecio(BigDecimal minimo, BigDecimal maximo) {
		return lectura(() -> super.obtenerPorPrecio(minimo, maximo));
	}

	@SuppressWarnings("unchecked")
	private <R> R lectura(Supplier<R> proveedor) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaFichero))) {
			super.productos = (TreeMap<Long, Producto>) ois.readObject();

			return proveedor.get();
		} catch (ClassNotFoundException | IOException e) {
			throw new AccesoDatosException("No se ha podido realizar la operación", e);
		}
	}

	private <R> R lecturaEscritura(Supplier<R> proveedor) {
		var respuesta = lectura(proveedor);

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaFichero))) {
			oos.writeObject(productos);

			return respuesta;
		} catch (IOException e) {
			throw new AccesoDatosException("No se ha podido realizar la operación", e);
		}
	}
}
