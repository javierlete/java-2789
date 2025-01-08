package com.ipartex.servicios;

import com.ipartex.entidades.Mensaje;
import com.ipartex.entidades.Usuario;

import jakarta.validation.Valid;

public interface UsuarioService extends AnonimoService {
	Mensaje publicarMensaje(Mensaje mensaje);

	Usuario buscarPorEmail(String email);

	Usuario registrarUsuario(@Valid Usuario usuario);
}
