package com.ipartex.servicios;

import com.ipartex.entidades.Mensaje;

public interface UsuarioService extends AnonimoService {
	Mensaje publicarMensaje(Mensaje mensaje);
}
