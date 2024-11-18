package com.ipartek.pojos;

import java.time.LocalDate;
import java.time.Period;

/**
 * La clase persona representa una persona con nombre y fecha de nacimiento
 * 
 * @author Javier Lete
 */
public class Persona {
	// CONSTANTES
	/**
	 * Valor por defecto para el nombre cuando no se proporciona
	 */
	public static final String NOMBRE_POR_DEFECTO = "Anónimo";
	
	// VARIABLES DE INSTANCIA (private)
	private Long id;
	private String nombre;
	private LocalDate fechaNacimiento;

	// CONSTRUCTORES
	/**
	 * Pasarán los datos por validaciones
	 * @param id El id
	 * @param nombre El nombre completo
	 * @param fechaNacimiento La fecha de nacimiento
	 */
	public Persona(Long id, String nombre, LocalDate fechaNacimiento) {
		setId(id);
		setNombre(nombre);
		setFechaNacimiento(fechaNacimiento);
	}
	
	/**
	 * Al no introducir el id, se asume null
	 * @param nombre El nombre completo
	 * @param fechaNacimiento La fecha de nacimiento
	 */
	public Persona(String nombre, LocalDate fechaNacimiento) {
		this(null, nombre, fechaNacimiento);
	}
	
	/**
	 * Como no se recibe ningún argumento, se asume <code>null</code>
	 * para el id, el <code>NOMBRE_POR_DEFECTO</code> para el nombre y 
	 * la fecha actual para la fecha de nacimiento
	 */
	public Persona() {
		this(null, NOMBRE_POR_DEFECTO, LocalDate.now());
	}

	/**
	 * Crea una copia <em>superficial</em> de persona
	 * @param persona
	 */
	public Persona(Persona persona) {
		this(persona.getId(), persona.getNombre(), persona.getFechaNacimiento());
	}

	// MÉTODOS DE ACCESO (GETTERS Y SETTERS)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	/**
	 * Se comprobará que el nombre está rellenado y tiene sólo letras
	 * y espacios
	 * @param nombre
	 * @throws RuntimeException Cuando el nombre no cumple los requisitos
	 */
	public void setNombre(String nombre) {
		if (nombre == null || nombre.isBlank()) {
			throw new RuntimeException("El nombre debe estar rellenado");
		}

		if (!nombre.matches("^[\\p{L} ]+$")) {
			throw new RuntimeException("El nombre no es válido. Debe estar compuesto de letras y espacios");
		}

		this.nombre = nombre.trim();
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * La fecha se comprobará que exista y no sea nula
	 * @param fechaNacimiento La fecha de nacimiento
	 * @throws RuntimeException en el caso de que la fecha no cumpla los requisitos
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if (fechaNacimiento == null || fechaNacimiento.isAfter(LocalDate.now())) {
			throw new RuntimeException("No se aceptan fechas futuras y es obligatorio rellenar la fecha");
		}

		this.fechaNacimiento = fechaNacimiento;
	}

	// MÉTODOS DE INSTANCIA
	public int getEdad() {
		return getEdad(fechaNacimiento);
	}
	
	// MÉTODO ESTÁTICO (DE CLASE)
	public static int getEdad(LocalDate fechaNacimiento) {
		return Period.between(fechaNacimiento, LocalDate.now()).getYears();
	}
	
	// TOSTRING
	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + "]";
	}
}
