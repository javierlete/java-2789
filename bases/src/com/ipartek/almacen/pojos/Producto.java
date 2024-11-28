package com.ipartek.almacen.pojos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Producto implements Serializable {
	
	private static final long serialVersionUID = -1343735207294068533L;

	private Long id;
	private String nombre;
	private BigDecimal precio;
	private LocalDate fechaCaducidad;
	
	private Categoria categoria;

	public Producto(Long id, String nombre, BigDecimal precio, LocalDate fechaCaducidad, Categoria categoria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.fechaCaducidad = fechaCaducidad;
		this.categoria = categoria;
	}
	
	public Producto(Long id, String nombre, BigDecimal precio, LocalDate fechaCaducidad) {
		this(id, nombre, precio, fechaCaducidad, null);
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

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, fechaCaducidad, id, nombre, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(fechaCaducidad, other.fechaCaducidad)
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(precio, other.precio);
	}

	@Override
	public String toString() {
		return String.format("Producto [id=%s, nombre=%s, precio=%s, fechaCaducidad=%s, categoria=%s]", id, nombre,
				precio, fechaCaducidad, categoria);
	}

}
