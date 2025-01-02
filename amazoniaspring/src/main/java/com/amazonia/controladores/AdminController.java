package com.amazonia.controladores;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.amazonia.entidades.Producto;
import com.amazonia.servicios.AdminService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private String rutaRaiz;
	
	@Value("${rutas.imagenes}")
	private String rutaImagenes;
	
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
	public String datosFormularioProducto(MultipartFile fichero, @Valid Producto producto, BindingResult bindingResult) throws IOException {
		if(bindingResult.hasErrors()) {
			return "/admin/producto";
		}
		
		Producto productoRecibido;
		
		if(producto.getId() != null) {
			productoRecibido = adminService.modificarProducto(producto);
		} else {
			productoRecibido = adminService.anadirProducto(producto);
		}

		var ruta = rutaRaiz + rutaImagenes + productoRecibido.getId() + ".jpg"; // fichero.getOriginalFilename();

		fichero.transferTo(Paths.get(ruta));
		
		return "redirect:/admin/productos";
	}
	
	@GetMapping("/producto/borrar")
	public String borrarProducto(Long id) {
		adminService.borrarProducto(id);
		
		return "redirect:/admin/productos";
	}
}
