package com.ipartex.entidades;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "mensajes")
public class Mensaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 500)
	private String texto;
	
	@NotNull
	@ManyToOne
	private Usuario usuario;
	
	@ManyToMany
	private Collection<Usuario> lesGusta;
	
	@NotNull
	@Builder.Default
	private LocalDateTime fecha = LocalDateTime.now();

	public Duration getTiempoVida() {
		return Duration.between(fecha, LocalDateTime.now());
	}
	
	public String getTiempoVidaTexto() {
		Duration tiempoVida = getTiempoVida();
		
		if(tiempoVida.toDaysPart() > 0) {
			return String.format("%dd", tiempoVida.toDaysPart());
		} else if(tiempoVida.toHoursPart() > 0) {
			return String.format("%dh", tiempoVida.toHoursPart());
		} else if(tiempoVida.toMinutesPart() > 0) {
			return String.format("%dm", tiempoVida.toMinutesPart());
		} else {
			return String.format("%ds", tiempoVida.toSecondsPart());
		}
	}
}
