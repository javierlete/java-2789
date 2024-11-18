package com.ipartek.pojos;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Local {
	private Long id;
	private String nombre;
	private Persona responsable;

	private HashSet<Persona> personas = new HashSet<>();

	public Local(Long id, String nombre, Persona responsable) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.responsable = responsable;
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

	public Persona getResponsable() {
		return responsable;
	}

	public void setResponsable(Persona responsable) {
		this.responsable = responsable;
	}

	public Set<Persona> getPersonas() {
		return Collections.unmodifiableSet(personas);
	}
	
	public void entrarPersona(Persona persona) {
		if(persona == null) {
			throw new RuntimeException("No se admiten personas inexistentes");
		}
		
		personas.add(persona);
	}
	
	@Override
	public String toString() {
		return "Local [id=" + id + ", nombre=" + nombre + ", responsable=" + responsable + "]";
	}

}
