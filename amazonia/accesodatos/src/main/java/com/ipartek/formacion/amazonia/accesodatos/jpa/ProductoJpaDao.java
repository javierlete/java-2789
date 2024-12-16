package com.ipartek.formacion.amazonia.accesodatos.jpa;

import com.ipartek.formacion.amazonia.accesodatos.JpaDao;
import com.ipartek.formacion.amazonia.accesodatos.ProductoDao;

public class ProductoJpaDao extends JpaDao implements ProductoDao {

	public ProductoJpaDao(String nousado1, String nousado2, String nousado3) {
		super(nousado1, nousado2, nousado3);
	}
}
