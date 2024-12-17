package com.ipartek.formacion.amazonia.accesodatos.jpa;

import com.ipartek.formacion.amazonia.accesodatos.JpaDao;
import com.ipartek.formacion.amazonia.accesodatos.UsuarioDao;
import com.ipartek.formacion.amazonia.entidades.Usuario;

import jakarta.persistence.NoResultException;

public class UsuarioJpaDao extends JpaDao implements UsuarioDao {

	public UsuarioJpaDao(String nousado1, String nousado2, String nousado3) {
		super(nousado1, nousado2, nousado3);
	}

	@Override
	public Usuario obtenerPorEmail(String email) {
		return enTransaccion(em -> {
			try {
				var usuario = em.createQuery("from Usuario u where u.email = :email", Usuario.class)
						.setParameter("email", email).getSingleResult();
				return usuario;
			} catch (NoResultException e) {
				return null;
			}
		});
	}

}
