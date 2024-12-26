package com.ipartek.formacion.amazonia.entidades;

import java.math.BigDecimal;
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
		
		public BigDecimal getTotal() {
			return producto.getPrecio().multiply(new BigDecimal(cantidad));
		}
	}

	public void agregarProducto(Producto producto) {
		var resultados = lineas.stream().filter(l -> l.producto.equals(producto)).toList();

		if(resultados.size() > 0) {
			var linea = resultados.get(0);
			linea.setCantidad(linea.getCantidad() + 1);
		} else {
			lineas.add(Linea.builder().producto(producto).cantidad(1).build());
		}
	}
}
