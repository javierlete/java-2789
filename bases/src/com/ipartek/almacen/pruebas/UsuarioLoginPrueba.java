package com.ipartek.almacen.pruebas;

import com.ipartek.almacen.fabrica.Fabrica;
import com.ipartek.almacen.pojos.Usuario;

public class UsuarioLoginPrueba {
	public static void main(String[] args) {
		var negocio = Fabrica.getUsuarioNegocio();
		
		var usuario = negocio.autenticar(Usuario.builder().email("javier@email.net").password("javier").build());
		
		if(usuario != null) {
			System.out.println("Usuario autenticado");
			
			System.out.println(usuario);
		} else {
			System.out.println("Usuario no autenticado");
		}
	}
}
