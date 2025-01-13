package com.ipartex.controladores;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

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

	@Autowired
	private String rutaRaiz;

	@Value("${rutas.imagenes}")
	private String rutaImagenes;

	@GetMapping("/")
	public String index(Model modelo, Principal principal) {
		var mensajes = anonimoService.listarMensajes();

		if (principal != null) {
			modelo.addAttribute("usuarioLogueado", usuarioService.buscarPorEmail(principal.getName()));
		}

		modelo.addAttribute("mensajes", mensajes);
		modelo.addAttribute("raizImagenes", rutaImagenes);

		return "index";
	}

	@PostMapping("/publicar")
	public String publicarPost(String texto, Principal principal) {
		if (texto == null || texto.isBlank()) {
			return "index";
		}

		usuarioService.publicarMensaje(principal.getName(), texto);

		return "redirect:/";
	}

	@GetMapping("/registrarse")
	public String registrarse(Usuario usuario) {
		return "registro";
	}

	@PostMapping("/registrarse")
	public String registrarsePost(MultipartFile imagen, @Valid Usuario usuario, BindingResult bindingResult)
			throws IllegalStateException, IOException {
		if (bindingResult.hasErrors()) {
			return "registro";
		}

		var usuarioRegistrado = usuarioService.registrarUsuario(usuario);

		var ruta = rutaRaiz + rutaImagenes + usuarioRegistrado.getId() + ".jpg"; // fichero.getOriginalFilename();

		imagen.transferTo(Paths.get(ruta));

		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/megusta")
	public String megusta(Principal principal, Long id) {
		if (principal != null) {
			usuarioService.conmutarLeGusta(id, principal.getName());
		}

		return "redirect:/#m" + id;
	}

	@GetMapping("/responder")
	public String responder(Long id, Principal principal, Model modelo) {
		if (principal != null) {
			modelo.addAttribute("mensaje", anonimoService.detalleMensaje(id));
			modelo.addAttribute("usuarioLogueado", usuarioService.buscarPorEmail(principal.getName()));
			modelo.addAttribute("raizImagenes", rutaImagenes);
		}
		
		return "responder";
	}

	@PostMapping("/responder")
	public String responderPost(String texto, Long id, Principal principal) {
		if (texto == null || texto.isBlank()) {
			return "index";
		}

		usuarioService.publicarRespuesta(id, texto, principal.getName());
		
		return "redirect:/#m" + id;
	}
}
