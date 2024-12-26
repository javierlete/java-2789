package com.ipartek.formacion.amazonia.accesodatos.jpa;

import com.ipartek.formacion.amazonia.accesodatos.FacturaDao;
import com.ipartek.formacion.amazonia.accesodatos.JpaDao;
import com.ipartek.formacion.amazonia.entidades.Factura;

public class FacturaJpaDao extends JpaDao implements FacturaDao {

	public FacturaJpaDao(String nousado1, String nousado2, String nousado3) {
		super(nousado1, nousado2, nousado3);
	}

	@Override
	public Factura insertar(Factura factura) {
		return enTransaccion(em -> {
			em.persist(factura);
			
			factura.getLineas().forEach(l -> em.persist(l));
			
			return factura;
		});
	}

}
