package com.ipartex.rest;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ipartex.dtos.MensajeDTO;
import com.ipartex.entidades.Mensaje;
import com.ipartex.entidades.Usuario;
import com.ipartex.repositorios.MensajeRepository;
import com.ipartex.servicios.UsuarioService;

@RestController
@RequestMapping("/api")
public class IpartexRestController {
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private MensajeRepository mensajeRepository;
	
	@GetMapping("/mensajes")
	public Iterable<MensajeDTO> mensajes() {
		return mensajeRepository.listarMensajes("gandalf@email.net");
	}
	
	@GetMapping("/listar-mensajes")
	public Iterable<Mensaje> listarMensajes() {
		return usuarioService.listarMensajes();
	}
	
	@GetMapping("/buscar-por-email")
	public Usuario buscarPorEmail(String email) {
		return usuarioService.buscarPorEmail(email);
	}
	
	@GetMapping("/megusta")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void conmutarLeGusta(Long id, Principal principal) {
		System.out.println(id);
		System.out.println(principal);
		
		if(principal != null) {
			usuarioService.conmutarLeGusta(id, principal.getName());
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No estás logueado");
		}
	}
	
	@PostMapping("/publicar-mensaje")
	public Mensaje publicarMensaje(String texto, Principal principal) {
		if (texto == null || texto.isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No tenemos texto");
		}

		var usuario = usuarioService.buscarPorEmail(principal.getName());
		var mensaje = Mensaje.builder().usuario(usuario).texto(texto).build();

		return usuarioService.publicarMensaje(mensaje);
	}
	
	@PostMapping("/registrar-usuario")
	public Usuario registrarUsuario(@RequestBody Usuario usuario, String password) {
		usuario.setPassword(password);
		return usuarioService.registrarUsuario(usuario);
	}
}
