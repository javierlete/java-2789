package com.amazonia.entidades;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@SessionScope

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carrito {
	private static final BigDecimal IVA = new BigDecimal("0.21");

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

		if (resultados.size() > 0) {
			var linea = resultados.get(0);
			linea.setCantidad(linea.getCantidad() + 1);
		} else {
			lineas.add(Linea.builder().producto(producto).cantidad(1).build());
		}
	}

	public void quitarProducto(Producto producto) {
		var resultados = lineas.stream().filter(l -> l.producto.equals(producto)).toList();

		if (resultados.size() > 0) {
			var linea = resultados.get(0);

			if (linea.getCantidad() != 1) {
				linea.setCantidad(linea.getCantidad() - 1);
			} else {
				lineas.remove(linea);
			}
		}
	}

	public BigDecimal getTotal() {
		return lineas.stream().map(l -> l.getTotal())
				.reduce((totalParcial, totalAcumulado) -> totalAcumulado.add(totalParcial)).orElse(BigDecimal.ZERO);
	}

	public BigDecimal getIva() {
		return getTotal().multiply(IVA);
	}

	public BigDecimal getTotalConIva() {
		return getTotal().add(getIva());
	}
}
