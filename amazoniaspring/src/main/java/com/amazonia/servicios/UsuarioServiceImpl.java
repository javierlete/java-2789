package com.amazonia.servicios;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonia.entidades.Carrito;
import com.amazonia.entidades.Cliente;
import com.amazonia.entidades.Factura;
import com.amazonia.entidades.Usuario;
import com.amazonia.repositorios.FacturaRepository;
import com.amazonia.repositorios.UsuarioRepository;

@Service
public class UsuarioServiceImpl extends AnonimoServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private FacturaRepository facturaRepo;
	
	@Override
	public Factura facturar(Cliente cliente, Carrito carrito) {
		Factura.FacturaBuilder fb = Factura.builder();
		
		fb.numero("2024/000001");
		fb.fecha(LocalDate.now());
		
		fb.nif(cliente.getNif());
		fb.nombre(cliente.getNombre());
		
		var factura = fb.build();
		
		carrito.getLineas().stream().forEach(lineaCarrito -> {
			var lineaFactura = Factura.Linea.builder().factura(factura).nombre(lineaCarrito.getProducto().getNombre()).precio(lineaCarrito.getProducto().getPrecio()).cantidad(lineaCarrito.getCantidad()).build();
			factura.getLineas().add(lineaFactura);
		});
		
		return facturaRepo.save(factura);
	}

	@Override
	public Usuario obtenerPorEmail(String email) {
		return usuarioRepo.findByEmail(email);
	}

}
