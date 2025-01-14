package com.ipartex.servicios;

import com.ipartex.dtos.MensajeDTO;
import com.ipartex.entidades.Mensaje;

public interface AnonimoService {
	Iterable<Mensaje> listarMensajes();

	Iterable<MensajeDTO> listarMensajesDTO(String email);

	Mensaje detalleMensaje(Long id);

	MensajeDTO detalleMensajeDTO(Long id, String email);
}
