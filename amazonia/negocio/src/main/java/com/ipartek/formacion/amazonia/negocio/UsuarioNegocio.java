package com.ipartek.formacion.amazonia.negocio;

import com.ipartek.formacion.amazonia.entidades.Carrito;
import com.ipartek.formacion.amazonia.entidades.Factura;

public interface UsuarioNegocio extends AnonimoNegocio {

	Factura facturar(Carrito carrito);
	
}
