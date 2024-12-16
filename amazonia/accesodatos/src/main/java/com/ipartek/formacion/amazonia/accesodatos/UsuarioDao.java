package com.ipartek.formacion.amazonia.accesodatos;

import com.ipartek.formacion.amazonia.entidades.Usuario;

public interface UsuarioDao extends Dao<Usuario> {
	Usuario obtenerPorEmail(String email);
}
