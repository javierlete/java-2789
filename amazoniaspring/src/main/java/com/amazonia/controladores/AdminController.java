package com.amazonia.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amazonia.entidades.Producto;
import com.amazonia.servicios.AdminService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/productos")
	public String listadoProductos(Model modelo) {
		modelo.addAttribute("productos", adminService.listarProductos());
		return "/admin/productos";
	}
	
	@GetMapping("/producto")
	public String mostrarFormularioProducto(Long id, Model modelo, Producto producto) {
		if(id != null) {
			modelo.addAttribute("producto", adminService.detalleProducto(id));
		}
		
		return "/admin/producto";
	}
	
	@PostMapping("/producto")
	public String datosFormularioProducto(@Valid Producto producto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "/admin/producto";
		}
		
		if(producto.getId() != null) {
			adminService.modificarProducto(producto);
		} else {
			adminService.anadirProducto(producto);
		}
		
		return "redirect:/admin/productos";
	}
	
	@GetMapping("/producto/borrar")
	public String borrarProducto(Long id) {
		adminService.borrarProducto(id);
		
		return "redirect:/admin/productos";
	}
}
