package com.amazonia.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.amazonia.entidades.Carrito;
import com.amazonia.servicios.AnonimoService;
import com.amazonia.servicios.UsuarioService;

@Controller
public class CarritoController {
	@Autowired
	private AnonimoService anonimoService;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private Carrito carrito;

	@ModelAttribute
	private void carrito(Model modelo) {
		modelo.addAttribute("carrito", carrito);
	}
	
	@GetMapping("/carrito")
	public String carrito(Long id) {
		if (id != null) {
			var producto = anonimoService.detalleProducto(id);

			carrito.agregarProducto(producto);
			
			return "redirect:/carrito";
		}

		return "carrito";
	}
	
	@GetMapping("/factura") 
	public String factura(Model modelo) {
		var factura = usuarioService.facturar(carrito);
		
		modelo.addAttribute("factura", factura);
		
		return "factura";
	}
}
