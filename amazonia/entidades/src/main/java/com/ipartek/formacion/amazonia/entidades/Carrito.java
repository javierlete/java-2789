package com.ipartek.formacion.amazonia.entidades;

import java.util.Collection;
import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carrito {
	@Builder.Default
	private Collection<Linea> lineas = new HashSet<Linea>();
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Linea {
		private Producto producto;
		private Integer cantidad;
	}

	public void agregarProducto(Producto producto) {
		lineas.add(Linea.builder().producto(producto).cantidad(1).build());
	}
}
