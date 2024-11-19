package com.ipartek.pojos;

import java.time.LocalDate;

public class Empleado extends Persona {
	private String dni;
	private String nss;

	public Empleado(Long id, String nombre, LocalDate fechaNacimiento, String dni, String nss) {
		super(id, nombre, fechaNacimiento);

		setDni(dni);
		setNss(nss);
	}

	public Empleado(String nombre, LocalDate fechaNacimiento, String dni, String nss) {
		this(null, nombre, fechaNacimiento, dni, nss);
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	@Override
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if (fechaNacimiento == null || getEdad(fechaNacimiento) < 18) {
			throw new IllegalArgumentException("No se aceptan empleados de menos de 18 años");
		}

		// super.setFechaNacimiento(fechaNacimiento);
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return String.format("Empleado [id=%s, nombre=%s, fechaNacimiento=%s, dni=%s, nss=%s]", id, nombre,
				fechaNacimiento, dni, nss);
	}
	

}
