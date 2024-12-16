package com.ipartek.formacion.amazonia.accesodatos.jpa;

import com.ipartek.formacion.amazonia.accesodatos.FacturaDao;
import com.ipartek.formacion.amazonia.accesodatos.JpaDao;

public class FacturaJpaDao extends JpaDao implements FacturaDao {

	public FacturaJpaDao(String nousado1, String nousado2, String nousado3) {
		super(nousado1, nousado2, nousado3);
	}
}
