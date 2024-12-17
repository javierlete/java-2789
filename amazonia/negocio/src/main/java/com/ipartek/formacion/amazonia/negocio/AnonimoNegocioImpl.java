package com.ipartek.formacion.amazonia.negocio;

import com.ipartek.formacion.amazonia.accesodatos.UsuarioDao;
import com.ipartek.formacion.amazonia.entidades.Usuario;
import com.ipartek.formacion.amazonia.fabrica.Fabrica;

public class AnonimoNegocioImpl implements AnonimoNegocio {
	UsuarioDao usuarioDao = Fabrica.getUsuarioDao();
	
	@Override
	public Usuario iniciarSesion(Usuario usuario) {
		var usuarioCompleto = usuarioDao.obtenerPorEmail(usuario.getEmail());
		
		if(usuarioCompleto != null && verificarPassword(usuario.getPassword(), usuarioCompleto.getPassword())) {
			return usuarioCompleto;
		} else {
			return null;
		}
	}

	private boolean verificarPassword(String passwordRecibida, String passwordAlmacenada) {
		return passwordAlmacenada.equals(passwordRecibida);
	}

}
