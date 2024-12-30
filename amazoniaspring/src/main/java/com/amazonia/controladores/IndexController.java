package com.amazonia.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.amazonia.servicios.AnonimoService;

@Controller
public class IndexController {
	
	@Autowired
	private AnonimoService anonimoService;
	
	@GetMapping("/")
	public String index(Model modelo) {
		modelo.addAttribute("productos", anonimoService.listarProductos());
		return "index";
	}
	
	@GetMapping("/detalle")
	public String detallePorId(Long id, Model modelo) {
		modelo.addAttribute("producto", anonimoService.detalleProducto(id));
		return "detalle";
	}
	
	@GetMapping("/detalle/{url}")
	public String detallePorUrl(@PathVariable String url, Model modelo) {
		modelo.addAttribute("producto", anonimoService.detalleProducto(url));
		return "detalle";
	}
}
