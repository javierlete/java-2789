package com.ipartek.pojos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class EmpleadoPorHoras extends Empleado {
	private Integer horasMensuales;
	private BigDecimal precioHora;

	public EmpleadoPorHoras(Long id, String nombre, LocalDate fechaNacimiento, String dni, String nss,
			Integer horasMensuales, BigDecimal precioHora) {
		super(id, nombre, fechaNacimiento, dni, nss);

		setHorasMensuales(horasMensuales);
		setPrecioHora(precioHora);
	}

	public Integer getHorasMensuales() {
		return horasMensuales;
	}

	public void setHorasMensuales(Integer horasMensuales) {
		this.horasMensuales = horasMensuales;
	}

	public BigDecimal getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(BigDecimal precioHora) {
		this.precioHora = precioHora;
	}

	@Override
	public BigDecimal getSueldoMensual() {
		return precioHora.multiply(new BigDecimal(horasMensuales));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(horasMensuales, precioHora);
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
		EmpleadoPorHoras other = (EmpleadoPorHoras) obj;
		return Objects.equals(horasMensuales, other.horasMensuales) && Objects.equals(precioHora, other.precioHora);
	}

	@Override
	public String toString() {
		return String.format(
				"EmpleadoPorHoras [horasMensuales=%s, precioHora=%s, dni=%s, nss=%s, id=%s, nombre=%s, fechaNacimiento=%s]",
				horasMensuales, precioHora, dni, nss, id, nombre, fechaNacimiento);
	}

}
