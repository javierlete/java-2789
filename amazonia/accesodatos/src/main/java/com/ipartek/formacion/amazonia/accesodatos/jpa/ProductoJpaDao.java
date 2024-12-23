package com.ipartek.formacion.amazonia.accesodatos.jpa;

import java.util.Collection;

import com.ipartek.formacion.amazonia.accesodatos.JpaDao;
import com.ipartek.formacion.amazonia.accesodatos.ProductoDao;
import com.ipartek.formacion.amazonia.entidades.Producto;

import jakarta.persistence.NoResultException;

public class ProductoJpaDao extends JpaDao implements ProductoDao {

	public ProductoJpaDao(String nousado1, String nousado2, String nousado3) {
		super(nousado1, nousado2, nousado3);
	}

	@Override
	public Collection<Producto> obtenerTodos() {
		return enTransaccion(em -> em.createQuery("from Producto", Producto.class).getResultList());
	}

	@Override
	public Producto obtenerPorId(Long id) {
		return enTransaccion(em -> em.find(Producto.class, id));
	}

	@Override
	public Producto obtenerPorUrl(String url) {
		return enTransaccion(em -> {
			try {
				return em.createQuery("from Producto p where p.url=:url", Producto.class).setParameter("url", url).getSingleResult();
			} catch (NoResultException e) {
				return null;
			}
		});
	}

	@Override
	public Producto insertar(Producto producto) {
		return enTransaccion(em -> {
			em.persist(producto);
			return producto;
		});
	}

	@Override
	public Producto modificar(Producto producto) {
		return enTransaccion(em -> {
			em.merge(producto);
			return producto;
		});
	}

	@Override
	public void borrar(Long id) {
		enTransaccion(em -> {
			em.remove(em.find(Producto.class, id));
			return null;
		});
	}
	
	

}
