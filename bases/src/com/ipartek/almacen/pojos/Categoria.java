package com.ipartek.almacen.pojos;

import java.util.Collection;
import java.util.LinkedHashSet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "categorias")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 50)
	// @Column(nullable = false, length = 50)
	private String nombre;
	
	@Lob
	@Size(max = 2000)
	private String descripcion;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Builder.Default

	@OneToMany(mappedBy = "categoria")
	private Collection<Producto> productos = new LinkedHashSet<>();
}
