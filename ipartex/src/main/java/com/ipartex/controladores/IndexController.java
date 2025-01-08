package com.ipartex.controladores;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ipartex.entidades.Mensaje;
import com.ipartex.servicios.AnonimoService;
import com.ipartex.servicios.UsuarioService;

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
}
