package com.ipartek.pojos;

import java.util.LinkedHashSet;

public class Poligono {
	private Long id;
	private String nombre;

	private final LinkedHashSet<Punto> puntos = new LinkedHashSet<>();

	public Poligono(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LinkedHashSet<Punto> getPuntos() {
		return puntos;
	}

	@Override
	public String toString() {
		return String.format("Poligono [id=%s, nombre=%s, puntos=%s]", id, nombre, puntos);
	}
}
