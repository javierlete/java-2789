package com.ipartex.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ipartex.entidades.Mensaje;
import com.ipartex.entidades.Usuario;
import com.ipartex.repositorios.MensajeRepository;
import com.ipartex.repositorios.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class UsuarioServiceImpl extends AnonimoServiceImpl implements UsuarioService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MensajeRepository mensajeRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Mensaje publicarMensaje(Mensaje mensaje) {
		return mensajeRepository.save(mensaje);
	}

	@Override
	public Usuario buscarPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public Usuario registrarUsuario(@Valid Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

		return usuarioRepository.save(usuario);
	}

	@Override
	public void conmutarLeGusta(Long id, String email) {
		System.out.println("ID: " + id);
		System.out.println("EMAIL: " + email);

		var usuario = usuarioRepository.findByEmail(email);

		System.out.println(usuario);

		if (mensajeRepository.comprobarMeGusta(id, email) == null) {
			
			var mensaje = mensajeRepository.findById(id).orElse(null);

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(mensaje);
			System.out.println();
			System.out.println();
			System.out.println();
			
			mensaje.getLesGusta().add(usuario);

			mensajeRepository.save(mensaje);
		} else {
			var mensaje = mensajeRepository.findById(id).orElse(null);
			
			mensaje.getLesGusta().remove(usuario);

			mensajeRepository.save(mensaje);
		}
	}

}
