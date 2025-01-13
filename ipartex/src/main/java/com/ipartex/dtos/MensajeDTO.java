package com.ipartex.dtos;

import java.time.Duration;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MensajeDTO {
	private Long id;
	private Long idUsuario;
	private String nombreUsuario;
	private String nombreUsuarioIpartex;
	private String texto;
	private Boolean leGusta;
	private Integer numeroMeGustas;
	private Integer numeroRespuestas;
	
	@JsonIgnore
	private LocalDateTime fecha;
	
	@JsonIgnore
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
