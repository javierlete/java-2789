package com.ipartek.almacen.accesodatos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.TreeMap;

import com.ipartek.almacen.pojos.Producto;

public class DaoProductoTreeMap implements DaoProducto {
	private TreeMap<Long, Producto> productos = new TreeMap<>();

	public DaoProductoTreeMap() {
		for (long i = 1; i <= 10; i++) {
			productos.put(i, new Producto(i, "Producto " + i, new BigDecimal(i * 10), LocalDate.of(2024, (int)i, (int)i * 2)));
		}
	}

	@Override
	public Iterable<Producto> obtenerTodos() {
		return productos.values();
	}

	@Override
	public Producto obtenerPorId(Long id) {
		return productos.get(id);
	}

	@Override
	public Producto insertar(Producto producto) {
		Long id = productos.size() > 0 ? productos.lastKey() + 1L : 1L;
		producto.setId(id);

		productos.put(id, producto);

		return producto;
	}

	@Override
	public Producto modificar(Producto producto) {
		productos.put(producto.getId(), producto);

		return producto;
	}

	@Override
	public void borrar(Long id) {
		productos.remove(id);
	}

	@Override
	public Iterable<Producto> obtenerPorNombreParcial(String nombre) {
		return productos.values().stream().filter(p -> p.getNombre().contains(nombre)).toList();
	}

	@Override
	public Iterable<Producto> obtenerPorPrecio(BigDecimal minimo, BigDecimal maximo) {
		return productos.values().stream()
				.filter(p -> p.getPrecio().compareTo(minimo) >= 0 && p.getPrecio().compareTo(maximo) <= 0).toList();
	}
}
