package com.ipartek.formacion.amazonia.entidades;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carrito {
	private Collection<Linea> lineas;
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Linea {
		private Producto producto;
		private Integer cantidad;
	}
}
