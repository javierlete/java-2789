package com.ipartek.formacion.amazonia.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "facturas")
public class Factura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotBlank
	@Pattern(regexp = "\\d{4}/\\d{6}")
	@Column(columnDefinition = "CHAR(11)", unique = true)
	private String numeroFactura;

	@Builder.Default
	@NotNull
	@FutureOrPresent
	private LocalDate fecha = LocalDate.now();

	@ManyToOne
	private Cliente cliente;

	@NotNull
	@NotBlank
	@Pattern(regexp = "[XYZ\\d]\\d{7}[A-Z]")
	@Size(min = 9, max = 9)
	@Column(columnDefinition = "CHAR(9)")
	private String nif;

	@NotNull
	@NotBlank
	@Size(min = 2, max = 50)
	private String nombre;

	@OneToMany
	private Collection<Linea> lineas;

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor

	@Entity
	@Table(name = "factura-lineas")
	public static class Linea {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotNull
		@ManyToOne
		private Factura factura;
		
		@NotNull
		@ManyToOne
		private Producto producto;
		
		@NotNull
		@NotBlank
		@Size(min = 3, max = 50)
		private String nombre;
		
		@NotNull
		@Min(0)
		private BigDecimal precio;

		@NotNull
		@Min(0)
		private Integer cantidad;
	}
}
