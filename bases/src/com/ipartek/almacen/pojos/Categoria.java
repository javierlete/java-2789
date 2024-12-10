package com.ipartek.almacen.pojos;

import java.util.LinkedHashSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Categoria {
	private Long id;
	private String nombre;
	private String descripcion;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Builder.Default
	private Iterable<Producto> productos = new LinkedHashSet<>();

	public Categoria(Long id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
}
