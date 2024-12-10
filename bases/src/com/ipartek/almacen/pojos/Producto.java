package com.ipartek.almacen.pojos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Producto implements Serializable {
	
	private static final long serialVersionUID = -1343735207294068533L;

	private Long id;
	private String nombre;
	private BigDecimal precio;
	private LocalDate fechaCaducidad;

	@EqualsAndHashCode.Exclude
	@Builder.Default
	private Map<String, String> errores = new HashMap<>();
	
	private Categoria categoria;

	public Producto(String id, String nombre, String precio, String fechaCaducidad) {
		setId(id);
		setNombre(nombre);
		setPrecio(precio);
		setFechaCaducidad(fechaCaducidad);
	}
	
	public Producto(Long id, String nombre, BigDecimal precio, LocalDate fechaCaducidad, Categoria categoria) {
		setId(id);
		setNombre(nombre);
		setPrecio(precio);
		setFechaCaducidad(fechaCaducidad);
		setCategoria(categoria);
	}
	
	public Producto(Long id, String nombre, BigDecimal precio, LocalDate fechaCaducidad) {
		this(id, nombre, precio, fechaCaducidad, null);
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setId(String sId) {
		try {
			setId(sId.isBlank() ? null: Long.parseLong(sId));
		} catch (NumberFormatException e) {
			errores.put("id", "El id debe ser un número");
		}
	}

	public void setNombre(String nombre) {
		if(nombre == null || nombre.isBlank()) {
			errores.put("nombre", "El nombre no puede estar en blanco y es obligatorio");
		}
		
		this.nombre = nombre;
	}

	public void setPrecio(BigDecimal precio) {
		if(precio == null || precio.compareTo(BigDecimal.ZERO) < 0) {
			errores.put("precio", "El precio no puede ser negativo y es obligatorio");
		}
		
		this.precio = precio;
	}

	public void setPrecio(String sPrecio) {
		try {
			setPrecio(new BigDecimal(sPrecio));
		} catch (NumberFormatException e) {
			errores.put("precio", "El precio es obligatorio");
		}
	}

	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		if(fechaCaducidad != null && fechaCaducidad.isBefore(LocalDate.now())) {
			errores.put("fechaCaducidad", "La fecha de caducidad no puede ser anterior a la actual");
		}
		
		this.fechaCaducidad = fechaCaducidad;
	}

	public void setFechaCaducidad(String sFecha) {
		try {
			setFechaCaducidad(sFecha.isBlank() ? null : LocalDate.parse(sFecha));
		} catch (DateTimeParseException e) {
			errores.put("fechaCaducidad", "La fecha no se ha podido convertir");
		}
		
	}

	public boolean isCorrecto() {
		return errores.size() == 0;
	}
}
