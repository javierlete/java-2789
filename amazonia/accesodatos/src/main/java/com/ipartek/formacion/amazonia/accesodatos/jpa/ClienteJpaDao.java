package com.ipartek.formacion.amazonia.accesodatos.jpa;

import com.ipartek.formacion.amazonia.accesodatos.ClienteDao;
import com.ipartek.formacion.amazonia.accesodatos.JpaDao;

public class ClienteJpaDao extends JpaDao implements ClienteDao {

	public ClienteJpaDao(String nousado1, String nousado2, String nousado3) {
		super(nousado1, nousado2, nousado3);
	}
}
