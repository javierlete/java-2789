package com.ipartek.almacen.pruebas;

import java.math.BigDecimal;

import com.ipartek.almacen.fabrica.Fabrica;
import com.ipartek.almacen.pojos.Producto;

public class DaoProductoJpaPrueba {
	public static void main(String[] args) {
		var dao = Fabrica.getDaoProducto();

		dao.insertar(Producto.builder().nombre("Producto1").precio(new BigDecimal("1234.56")).build());
		dao.insertar(Producto.builder().nombre("Producto2").precio(new BigDecimal("123.56")).build());
		dao.insertar(Producto.builder().nombre("Producto3").precio(new BigDecimal("12.56")).build());

		dao.obtenerTodos().forEach(System.out::println);

		dao.obtenerPorNombreParcial("ucto2").forEach(System.out::println);

		dao.obtenerPorPrecio(new BigDecimal("100"), new BigDecimal("10000")).forEach(System.out::println);
	}
}
