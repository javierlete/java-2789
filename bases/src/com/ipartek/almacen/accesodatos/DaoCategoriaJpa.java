package com.ipartek.almacen.accesodatos;

import java.util.Collection;

import com.ipartek.almacen.pojos.Categoria;

public class DaoCategoriaJpa extends DaoJpa implements DaoCategoria {

	public DaoCategoriaJpa(String nousado1, String nousado2, String nousado3) {
		super(nousado1, nousado2, nousado3);
	}

	@Override
	public Collection<Categoria> obtenerTodos() {
		return enTransaccion(em -> em.createQuery("from Categoria", Categoria.class).getResultList());
	}

	@Override
	public Categoria obtenerPorId(Long id) {
		return enTransaccion(em -> em.find(Categoria.class, id));
	}

	@Override
	public Categoria insertar(Categoria categoria) {
		return enTransaccion(em -> {
			em.persist(categoria);
			return categoria;
		});
	}

	@Override
	public Categoria modificar(Categoria categoria) {
		return enTransaccion(em -> {
			em.merge(categoria);
			return categoria;
		});
	}

	@Override
	public void borrar(Long id) {
		enTransaccion(em -> {
			em.remove(em.find(Categoria.class, id));
			return null;
		});
	}

	@Override
	public Categoria obtenerPorIdConProductos(Long id) {
		return enTransaccion(em -> em.find(Categoria.class, id));
	}

}
