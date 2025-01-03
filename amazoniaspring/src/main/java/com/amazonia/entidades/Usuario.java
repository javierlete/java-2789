package com.amazonia.entidades;

import com.amazonia.entidades.gruposvalidacion.UsuarioRegistro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(groups = UsuarioRegistro.class)
	@NotBlank(groups = UsuarioRegistro.class)
	@Email(groups = UsuarioRegistro.class)
	@Size(max = 50, groups = UsuarioRegistro.class)
	private String email;
	
	@NotNull(groups = UsuarioRegistro.class)
	@NotBlank(groups = UsuarioRegistro.class)
	// @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z\\d ]).{8,}$", message = "debe tener 8 caracteres o más, incluir al menos un dígito, una letra minúscula, una letra mayúscula y un símbolo", groups = UsuarioRegistro.class)
	@Column(length = 100)
	private String password;
	
	@NotNull(groups = UsuarioRegistro.class)
	@NotBlank(groups = UsuarioRegistro.class)
	@Size(max = 50, groups = UsuarioRegistro.class)
	private String nombre;
	
	@NotNull
	@ManyToOne
	private Rol rol;
	
	@OneToOne
	private Cliente cliente;
	
	public boolean isAdmin() {
		return "ADMIN".equals(rol.getNombre());
	}
}
