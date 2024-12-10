package com.ipartek.almacen.pruebas;

import com.ipartek.almacen.fabrica.Fabrica;
import com.ipartek.almacen.pojos.Categoria;

public class DaoCategoriaJpaPrueba {
	public static void main(String[] args) {
		var daoCategoria = Fabrica.getDaoCategoria();
		
		daoCategoria.insertar(Categoria.builder().nombre("Cat1").build());
		daoCategoria.insertar(Categoria.builder().nombre("Cat2").build());
		
		daoCategoria.obtenerTodos().forEach(System.out::println);
		
		var categoria = daoCategoria.obtenerPorIdConProductos(1L);
		
		System.out.println(categoria);
		
		categoria = daoCategoria.obtenerPorId(1L);
		
		System.out.println(categoria);
		
	}
}
