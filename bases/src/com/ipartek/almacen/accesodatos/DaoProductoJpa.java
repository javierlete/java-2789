package com.ipartek.almacen.accesodatos;

import java.math.BigDecimal;

import com.ipartek.almacen.pojos.Producto;

public class DaoProductoJpa extends DaoJpa implements DaoProducto {
	
	public DaoProductoJpa(String nousado1, String nousado2, String nousado3) {
		super(nousado1, nousado2, nousado3);
	}

	@Override
	public Iterable<Producto> obtenerTodos() {
		var em = FABRICA_JPA.createEntityManager();
		var t = em.getTransaction();
		
		t.begin();
		
		var productos = em.createQuery("from Producto p join fetch p.categoria", Producto.class).getResultList();
		
		t.commit();
		
		return productos;
	}

	@Override
	public Producto obtenerPorId(Long id) {
		var em = FABRICA_JPA.createEntityManager();
		var t = em.getTransaction();
		
		t.begin();
		
		var producto = em.find(Producto.class, id);
		
		t.commit();
		
		return producto;
	}

	@Override
	public Producto insertar(Producto producto) {
		var em = FABRICA_JPA.createEntityManager();
		var t = em.getTransaction();
		
		t.begin();
		
		em.persist(producto);
		
		t.commit();
		
		return producto;
	}

	@Override
	public Producto modificar(Producto producto) {
		var em = FABRICA_JPA.createEntityManager();
		var t = em.getTransaction();
		
		t.begin();
		
		em.merge(producto);
		
		t.commit();
		
		return producto;
	}

	@Override
	public void borrar(Long id) {
		var em = FABRICA_JPA.createEntityManager();
		var t = em.getTransaction();
		
		t.begin();
		
		em.remove(em.find(Producto.class, id));
		
		t.commit();
	}

	@Override
	public Iterable<Producto> obtenerPorNombreParcial(String nombre) {
		var em = FABRICA_JPA.createEntityManager();
		var t = em.getTransaction();
		
		t.begin();
		
		var productos = em.createQuery("from Producto p where p.nombre like :nombre", Producto.class).setParameter("nombre", "%" + nombre + "%").getResultList();
		
		t.commit();
		
		return productos;
	}

	@Override
	public Iterable<Producto> obtenerPorPrecio(BigDecimal minimo, BigDecimal maximo) {
		var em = FABRICA_JPA.createEntityManager();
		var t = em.getTransaction();
		
		t.begin();
		
		var productos = em.createQuery("from Producto p where p.precio between :minimo and :maximo", Producto.class).setParameter("minimo", minimo).setParameter("maximo", maximo).getResultList();
		
		t.commit();
		
		return productos;
	}

}
