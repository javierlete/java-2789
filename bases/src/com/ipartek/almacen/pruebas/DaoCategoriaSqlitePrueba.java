package com.ipartek.almacen.pruebas;

import com.ipartek.almacen.fabrica.Fabrica;

public class DaoCategoriaSqlitePrueba {
	public static void main(String[] args) {
		var dao = Fabrica.getDaoCategoria();
		
		dao.obtenerTodos().forEach(System.out::println);
		
		System.out.println(dao.obtenerPorId(1L));
		
		var categoria = dao.obtenerPorIdConProductos(1L);
		
		System.out.println(categoria);
		
		categoria.getProductos().forEach(System.out::println);
	}
}
