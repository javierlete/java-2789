package com.ipartek.almacen.accesodatos;

import com.ipartek.almacen.pojos.Usuario;

import jakarta.persistence.NoResultException;

public class DaoUsuarioJpa extends DaoJpa implements DaoUsuario
{
	public DaoUsuarioJpa(String nousado1, String nousado2, String nousado3) {
		super(nousado1, nousado2, nousado3);
	}

	@Override
	public Usuario obtenerPorEmail(String email) {
		try (var em = FABRICA_JPA.createEntityManager()) {
			var t = em.getTransaction();
			
			t.begin();
			
			var usuario = em.createQuery("from Usuario u join fetch u.rol where u.email = :email", Usuario.class).setParameter("email", email).getSingleResult();
			
			t.commit();
			
			return usuario;
		} catch(NoResultException e) {
			return null;
		}
	}

}
