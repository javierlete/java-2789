package com.ipartek.almacen.pruebas;

import com.ipartek.almacen.fabrica.Fabrica;

public class DaoCategoriaSqlitePrueba {
	public static void main(String[] args) {
		var daoProducto = Fabrica.getDaoProducto();
		var daoCategoria = Fabrica.getDaoCategoria();
		
		daoCategoria.obtenerTodos().forEach(System.out::println);
		
		var categoria = daoCategoria.obtenerPorIdConProductos(1L);
		
		System.out.println(categoria);
		
		categoria.getProductos().forEach(System.out::println);

		categoria = daoCategoria.obtenerPorId(1L);
		categoria.setProductos(daoProducto.obtenerPorIdCategoria(categoria.getId()));
		
		System.out.println(categoria);
		
		categoria.getProductos().forEach(System.out::println);
	}
}
