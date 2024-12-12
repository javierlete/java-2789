package com.ipartek.almacen.accesodatos;

import java.math.BigDecimal;
import java.util.Collection;

import com.ipartek.almacen.pojos.Producto;

public class DaoProductoJpa extends DaoJpa implements DaoProducto {

	public DaoProductoJpa(String nousado1, String nousado2, String nousado3) {
		super(nousado1, nousado2, nousado3);
	}

	@Override
	public Collection<Producto> obtenerTodos() {
		return enTransaccion(
				em -> em.createQuery("from Producto p left join fetch p.categoria", Producto.class).getResultList());
	}

	@Override
	public Producto obtenerPorId(Long id) {
		return enTransaccion(em -> em.find(Producto.class, id));
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

	@Override
	public Iterable<Producto> obtenerPorNombreParcial(String nombre) {
		return enTransaccion(em -> em.createQuery("from Producto p where p.nombre like :nombre", Producto.class)
				.setParameter("nombre", "%" + nombre + "%").getResultList());
	}

	@Override
	public Iterable<Producto> obtenerPorPrecio(BigDecimal minimo, BigDecimal maximo) {
		return enTransaccion(
				em -> em.createQuery("from Producto p where p.precio between :minimo and :maximo", Producto.class)
						.setParameter("minimo", minimo).setParameter("maximo", maximo).getResultList());
	}
}
