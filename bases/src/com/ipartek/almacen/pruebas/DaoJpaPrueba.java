package com.ipartek.almacen.pruebas;

import java.math.BigDecimal;

import com.ipartek.almacen.accesodatos.DaoProducto;
import com.ipartek.almacen.accesodatos.DaoRol;
import com.ipartek.almacen.accesodatos.DaoUsuario;
import com.ipartek.almacen.fabrica.Fabrica;
import com.ipartek.almacen.pojos.Producto;
import com.ipartek.almacen.pojos.Rol;
import com.ipartek.almacen.pojos.Usuario;

public class DaoJpaPrueba {
	private static  Rol rol1 = Rol.builder().nombre("ADMIN").descripcion("Administración").build();
	private static  Rol rol2 = Rol.builder().nombre("USER").descripcion("Usuarios").build();
	
	private static  Usuario usuario1 = Usuario.builder().email("javier@email.net").password("javier")
			.nombre("Javier").build();
	private static  Usuario usuario2 = Usuario.builder().email("pepe@email.net").password("pepe")
			.nombre("Pepe").build();

	private static  Producto producto1 = Producto.builder().nombre("Portátil")
			.precio(new BigDecimal("1234.12")).build();
	private static  Producto producto2 = Producto.builder().nombre("Monitor")
			.precio(new BigDecimal("123.12")).build();
	private static  Producto producto3 = Producto.builder().nombre("Ratón")
			.precio(new BigDecimal("12.12")).build();

	private static final DaoUsuario daoUsuario = Fabrica.getDaoUsuario();
	private static final DaoRol daoRol = Fabrica.getDaoRol();
	private static final DaoProducto daoProducto = Fabrica.getDaoProducto();

	public static void main(String[] args) {
		rol1 = daoRol.insertar(rol1);
		rol2 = daoRol.insertar(rol2);
		
		usuario1.setRol(rol1);
		usuario2.setRol(rol2);
		
		daoUsuario.insertar(usuario1);
		daoUsuario.insertar(usuario2);
		
		daoProducto.insertar(producto1);
		daoProducto.insertar(producto2);
		daoProducto.insertar(producto3);		
	}
}
