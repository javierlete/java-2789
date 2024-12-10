package com.ipartek.almacen.pojos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Producto implements Serializable {
	
	private static final long serialVersionUID = -1343735207294068533L;

	private Long id;
	
	@NotNull
	@NotBlank
	private String nombre;

	@NotNull
	@Min(0)
	private BigDecimal precio;
	
	@FutureOrPresent
	private LocalDate fechaCaducidad;

	private Categoria categoria;
}
