package com.ipartek.almacen.pruebas;

import java.math.BigDecimal;

import com.ipartek.almacen.fabrica.Fabrica;
import com.ipartek.almacen.pojos.Categoria;
import com.ipartek.almacen.pojos.Producto;

public class RelacionUnoAVariosJpaPrueba {
	public static void main(String[] args) {
		var daoCategoria = Fabrica.getDaoCategoria();

		Categoria cat1 = Categoria.builder().nombre("Cat1").build();
		Categoria cat2 = Categoria.builder().nombre("Cat2").build();

		daoCategoria.insertar(cat1);
		daoCategoria.insertar(cat2);

		var daoProducto = Fabrica.getDaoProducto();

		daoProducto.insertar(Producto.builder().nombre("Producto1").precio(new BigDecimal("1234.56")).categoria(cat1).build());
		daoProducto.insertar(Producto.builder().nombre("Producto2").precio(new BigDecimal("123.56")).categoria(cat1).build());
		daoProducto.insertar(Producto.builder().nombre("Producto3").precio(new BigDecimal("12.56")).categoria(Categoria.builder().id(2L).build()).build());
		
		daoProducto.obtenerTodos().forEach(System.out::println);
		
		System.out.println(daoProducto.obtenerPorId(2L));
		
		Categoria cat = daoCategoria.obtenerPorId(1L);
		System.out.println(cat);
		
		cat.getProductos().forEach(System.out::println);
	}
}
