package com.ipartex.controladores;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ipartex.entidades.Mensaje;
import com.ipartex.entidades.Usuario;
import com.ipartex.servicios.AnonimoService;
import com.ipartex.servicios.UsuarioService;

import jakarta.validation.Valid;

@Controller
public class IndexController {
	@Autowired
	private AnonimoService anonimoService;

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/")
	public String index(Model modelo) {
		var mensajes = anonimoService.listarMensajes();
		
		modelo.addAttribute("mensajes", mensajes);
		
		return "index";
	}
	
	@PostMapping("/publicar")
	public String publicarPost(String texto, Principal principal) {
		if(texto == null || texto.isBlank()) {
			return "index";
		}
		
		var usuario = usuarioService.buscarPorEmail(principal.getName());
		var mensaje = Mensaje.builder().usuario(usuario).texto(texto).build(); 
		
		usuarioService.publicarMensaje(mensaje);
		
		return "redirect:/";
	}
	
	@GetMapping("/registrarse")
	public String registrarse(Usuario usuario) {
		return "registro";
	}
	
	@PostMapping("/registrarse")
	public String registrarsePost(@Valid Usuario usuario, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "registro";
		}
		
		usuarioService.registrarUsuario(usuario);
		
		return "redirect:/login";
	}
}
