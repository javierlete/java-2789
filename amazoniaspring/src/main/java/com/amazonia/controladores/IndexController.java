package com.amazonia.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonia.servicios.AnonimoService;

@Controller
public class IndexController {
	
	@Autowired
	private AnonimoService anonimoService;
	
	@GetMapping("/")
	@ResponseBody
	public String index() {
		return anonimoService.listarProductos().toString();
	}
}
