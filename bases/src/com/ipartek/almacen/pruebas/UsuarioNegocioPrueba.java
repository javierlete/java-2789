package com.ipartek.almacen.pruebas;

import com.ipartek.almacen.fabrica.Fabrica;

public class UsuarioNegocioPrueba {
	public static void main(String[] args) {
		var negocio = Fabrica.getUsuarioNegocio();
		
		negocio.verProductos().forEach(System.out::println);
		
		System.out.println(negocio.buscarProductoPorId(2L));
		
		negocio.buscarProductosPorNombre("por").forEach(System.out::println);
	}
}
