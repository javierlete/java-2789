package com.ipartex.servicios;

import com.ipartex.entidades.Mensaje;
import com.ipartex.entidades.Usuario;

import jakarta.validation.Valid;

public interface UsuarioService extends AnonimoService {
	Mensaje publicarMensaje(Mensaje mensaje);
	Mensaje publicarMensaje(String email, String texto);

	Mensaje publicarRespuesta(Long id, String texto, String email);

	Usuario buscarPorEmail(String email);

	Usuario registrarUsuario(@Valid Usuario usuario);

	void conmutarLeGusta(Long id, String email);
}
