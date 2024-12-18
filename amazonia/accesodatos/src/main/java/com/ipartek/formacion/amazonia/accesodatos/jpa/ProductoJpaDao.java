package com.ipartek.formacion.amazonia.accesodatos.jpa;

import java.util.Collection;

import com.ipartek.formacion.amazonia.accesodatos.JpaDao;
import com.ipartek.formacion.amazonia.accesodatos.ProductoDao;
import com.ipartek.formacion.amazonia.entidades.Producto;

public class ProductoJpaDao extends JpaDao implements ProductoDao {

	public ProductoJpaDao(String nousado1, String nousado2, String nousado3) {
		super(nousado1, nousado2, nousado3);
	}

	@Override
	public Collection<Producto> obtenerTodos() {
		return enTransaccion(em -> em.createQuery("from Producto", Producto.class).getResultList());
	}
}
