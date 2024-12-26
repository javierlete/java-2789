package com.ipartek.formacion.amazonia.negocio;

import java.time.LocalDate;

import com.ipartek.formacion.amazonia.accesodatos.FacturaDao;
import com.ipartek.formacion.amazonia.entidades.Carrito;
import com.ipartek.formacion.amazonia.entidades.Factura;
import com.ipartek.formacion.amazonia.fabrica.Fabrica;

public class UsuarioNegocioImpl extends AnonimoNegocioImpl implements UsuarioNegocio {

	private static final FacturaDao FACTURA_DAO = Fabrica.getFacturaDao();
	
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
		
		FACTURA_DAO.insertar(factura);
		
		return factura;
	}

}
