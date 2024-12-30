package com.amazonia.servicios;

import com.amazonia.entidades.Carrito;
import com.amazonia.entidades.Factura;

public interface UsuarioService extends AnonimoService {

	Factura facturar(Carrito carrito);
	
}
