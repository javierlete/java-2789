package com.ipartek.almacen.accesodatos;

import com.ipartek.almacen.pojos.Usuario;

public interface DaoUsuario extends Dao<Usuario> {
	Usuario obtenerPorEmail(String email);
}
