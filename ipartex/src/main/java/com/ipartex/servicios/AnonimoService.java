package com.ipartex.servicios;

import com.ipartex.entidades.Mensaje;

public interface AnonimoService {
	Iterable<Mensaje> listarMensajes();

	Mensaje detalleMensaje(Long id);
}
