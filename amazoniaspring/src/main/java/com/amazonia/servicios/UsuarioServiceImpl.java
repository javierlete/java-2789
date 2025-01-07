package com.amazonia.servicios;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazonia.entidades.Carrito;
import com.amazonia.entidades.Cliente;
import com.amazonia.entidades.Factura;
import com.amazonia.entidades.Usuario;
import com.amazonia.repositorios.FacturaLineaRepository;
import com.amazonia.repositorios.FacturaRepository;
import com.amazonia.repositorios.UsuarioRepository;

@Service
public class UsuarioServiceImpl extends AnonimoServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private FacturaRepository facturaRepo;
	
	@Autowired
	private FacturaLineaRepository facturaLineaRepo;
	
	@Transactional
	@Override
	public Factura facturar(Cliente cliente, Carrito carrito) {
		Factura.FacturaBuilder fb = Factura.builder();
		
		fb.numero(calcularNuevoNumeroFactura());
		fb.fecha(LocalDate.now());
		
		fb.cliente(cliente);
		
		fb.nif(cliente.getNif());
		fb.nombre(cliente.getNombre());
		
		var factura = fb.build();
		
		facturaRepo.save(factura);
		
		carrito.getLineas().stream().forEach(lineaCarrito -> {
			var lineaFactura = Factura.Linea.builder().factura(factura).nombre(lineaCarrito.getProducto().getNombre()).precio(lineaCarrito.getProducto().getPrecio()).cantidad(lineaCarrito.getCantidad()).producto(lineaCarrito.getProducto()).build();
			factura.getLineas().add(lineaFactura);
			
			facturaLineaRepo.save(lineaFactura);
		});
		
		return factura;
	}

	@Override
	public Usuario obtenerPorEmail(String email) {
		return usuarioRepo.findByEmail(email);
	}

	@Override
	public String obtenerUltimoNumeroFactura() {
		var factura = facturaRepo.findTopByOrderByNumeroDesc();
		
		if(factura == null) {
			return null;			
		}
		
		var numero = factura.getNumero();
		
		return numero;
	}

	@Override
	public String calcularNuevoNumeroFactura() {
		var anyoActual = LocalDate.now().getYear();

		String numeroFactura = obtenerUltimoNumeroFactura();
		
		if(numeroFactura == null) {
			return generarFactura(anyoActual, 1);
		}
		
		String[] partes = numeroFactura.split("/");
		
		var anyo = Integer.parseInt(partes[0]);
		var numero = Integer.parseInt(partes[1]);
		
		if(anyo == anyoActual) {
			return generarFactura(anyo, numero + 1);
		} else {
			return generarFactura(anyoActual, 1);
		}
	}

	private String generarFactura(int anyo, int numero) {
		return anyo + "/" + String.format("%06d", numero);
	}

	@Override
	public Factura obtenerFactura(String numero) {
		return facturaRepo.findByNumero(numero);
	}

}
