package com.ipartek.almacen.accesodatos;

import java.util.Collection;

import com.ipartek.almacen.pojos.Categoria;

public class DaoCategoriaJpa extends DaoJpa implements DaoCategoria {
	
	public DaoCategoriaJpa(String nousado1, String nousado2, String nousado3) {
		super(nousado1, nousado2, nousado3);
	}

	@Override
	public Collection<Categoria> obtenerTodos() {
		var em = FABRICA_JPA.createEntityManager();
		var t = em.getTransaction();
		
		t.begin();
		
		var categorias = em.createQuery("from Categoria", Categoria.class).getResultList();
		
		t.commit();
		
		return categorias;
	}

	@Override
	public Categoria obtenerPorId(Long id) {
		var em = FABRICA_JPA.createEntityManager();
		var t = em.getTransaction();
		
		t.begin();
		
		var categoria = em.find(Categoria.class, id);
		
		t.commit();
		
		return categoria;
	}

	@Override
	public Categoria insertar(Categoria categoria) {
		var em = FABRICA_JPA.createEntityManager();
		var t = em.getTransaction();
		
		t.begin();
		
		em.persist(categoria);
		
		t.commit();
		
		return categoria;
	}

	@Override
	public Categoria modificar(Categoria categoria) {
		var em = FABRICA_JPA.createEntityManager();
		var t = em.getTransaction();
		
		t.begin();
		
		em.merge(categoria);
		
		t.commit();
		
		return categoria;
	}

	@Override
	public void borrar(Long id) {
		var em = FABRICA_JPA.createEntityManager();
		var t = em.getTransaction();
		
		t.begin();
		
		em.remove(em.find(Categoria.class, id));
		
		t.commit();
	}

	@Override
	public Categoria obtenerPorIdConProductos(Long id) {
		// TODO OBTENER CON LOS DATOS RELACIONADOS
		
		var em = FABRICA_JPA.createEntityManager();
		var t = em.getTransaction();
		
		t.begin();
		
		var categoria = em.find(Categoria.class, id);
		
		t.commit();
		
		return categoria;
	}

}
