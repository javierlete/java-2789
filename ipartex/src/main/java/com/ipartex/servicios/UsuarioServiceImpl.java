package com.ipartex.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartex.entidades.Mensaje;
import com.ipartex.repositorios.MensajeRepository;

@Service
public class UsuarioServiceImpl extends AnonimoServiceImpl implements UsuarioService {

	@Autowired
	private MensajeRepository mensajeRepository;
	
	@Override
	public Mensaje publicarMensaje(Mensaje mensaje) {
		return mensajeRepository.save(mensaje);
	}

}
