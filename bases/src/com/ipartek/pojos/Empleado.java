package com.ipartek.pojos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Empleado extends Persona {
	protected String dni;
	protected String nss;

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

	public abstract BigDecimal getSueldoMensual();
	
	@Override
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if (fechaNacimiento == null || getEdad(fechaNacimiento) < 18) {
			throw new IllegalArgumentException("No se aceptan empleados de menos de 18 años");
		}

		// super.setFechaNacimiento(fechaNacimiento);
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dni, nss);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(nss, other.nss);
	}

	@Override
	public String toString() {
		return String.format("Empleado [id=%s, nombre=%s, fechaNacimiento=%s, dni=%s, nss=%s]", id, nombre,
				fechaNacimiento, dni, nss);
	}
	

}
