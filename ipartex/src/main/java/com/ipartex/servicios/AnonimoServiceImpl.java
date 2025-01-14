package com.ipartex.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ipartex.dtos.MensajeDTO;
import com.ipartex.entidades.Mensaje;
import com.ipartex.repositorios.MensajeRepository;
import com.ipartex.repositorios.UsuarioRepository;

@Primary
@Service
public class AnonimoServiceImpl implements AnonimoService {

	@Autowired
	private MensajeRepository mensajeRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Iterable<Mensaje> listarMensajes() {
		return mensajeRepository.findMensajesByRespuestaDeIsNullOrderByFechaDesc();
	}

	@Override
	public Iterable<MensajeDTO> listarMensajesDTO(String email) {
		var usuario = usuarioRepository.findByEmail(email);
		return mensajeRepository.listarMensajes(usuario);
	}

	@Override
	public Mensaje detalleMensaje(Long id) {
		return mensajeRepository.findById(id).orElseThrow();
	}

	@Override
	public MensajeDTO detalleMensajeDTO(Long id, String email) {
		var usuario = usuarioRepository.findByEmail(email);
		return mensajeRepository.buscarPorId(id, usuario);
	}

}
