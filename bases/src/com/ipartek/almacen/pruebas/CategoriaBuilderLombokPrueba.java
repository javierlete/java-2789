package com.ipartek.almacen.pruebas;

import com.ipartek.almacen.pojos.Categoria;

public class CategoriaBuilderLombokPrueba {
	public static void main(String[] args) {
		Categoria.CategoriaBuilder cb = Categoria.builder();
		
		cb.nombre("Cat1").id(2L);
		
		cb.descripcion("Otra");
		
		Categoria categoria = cb.build();
		
		System.out.println(categoria);
	}
}
