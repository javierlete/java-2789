package com.ipartex.servicios;

import com.ipartex.entidades.Mensaje;
import com.ipartex.entidades.Usuario;

public interface UsuarioService extends AnonimoService {
	Mensaje publicarMensaje(Mensaje mensaje);

	Usuario buscarPorEmail(String email);
}
