package com.amazonia.servicios;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonia.entidades.Carrito;
import com.amazonia.entidades.Factura;
import com.amazonia.repositorios.FacturaRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private FacturaRepository facturaRepo;
	
	@Override
	public Factura facturar(Carrito carrito) {
		Factura.FacturaBuilder fb = Factura.builder();
		
		fb.numero("2024/000001");
		fb.fecha(LocalDate.now());
		
		fb.nif("12345678Z");
		fb.nombre("Ejemplo Ejemplez");
		
		var factura = fb.build();
		
		carrito.getLineas().stream().forEach(lineaCarrito -> {
			var lineaFactura = Factura.Linea.builder().factura(factura).nombre(lineaCarrito.getProducto().getNombre()).precio(lineaCarrito.getProducto().getPrecio()).cantidad(lineaCarrito.getCantidad()).build();
			factura.getLineas().add(lineaFactura);
		});
		
		return facturaRepo.save(factura);
	}

}
