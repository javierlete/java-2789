package com.ipartek.almacen.accesodatos;

import com.ipartek.almacen.pojos.Rol;

public class DaoRolJpa extends DaoJpa implements DaoRol {
	public DaoRolJpa(String nousado1, String nousado2, String nousado3) {
		super(nousado1, nousado2, nousado3);
	}

	@Override
	public Rol obtenerPorId(Long id) {
		return enTransaccion(em -> em.find(Rol.class, id));
	}



	@Override
	public Rol insertar(Rol rol) {
		return enTransaccion(em -> {
			em.persist(rol);
			return rol;
		});
	}

}
