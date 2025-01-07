package com.amazonia.servicios;

import com.amazonia.entidades.Carrito;
import com.amazonia.entidades.Cliente;
import com.amazonia.entidades.Factura;
import com.amazonia.entidades.Usuario;

public interface UsuarioService extends AnonimoService {

	Factura facturar(Cliente cliente, Carrito carrito);

	Usuario obtenerPorEmail(String email);
	
	String obtenerUltimoNumeroFactura();
	
	String calcularNuevoNumeroFactura();
}
