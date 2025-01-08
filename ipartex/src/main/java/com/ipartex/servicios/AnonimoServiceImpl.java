package com.ipartex.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ipartex.entidades.Mensaje;
import com.ipartex.repositorios.MensajeRepository;

@Primary
@Service
public class AnonimoServiceImpl implements AnonimoService {

	@Autowired
	private MensajeRepository mensajeRepository;
	
	@Override
	public Iterable<Mensaje> listarMensajes() {
		return mensajeRepository.findMensajesByOrderByFechaDesc();
	}

}
