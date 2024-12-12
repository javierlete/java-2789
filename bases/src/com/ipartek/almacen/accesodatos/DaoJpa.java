package com.ipartek.almacen.accesodatos;

import java.util.function.Function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DaoJpa {
	public DaoJpa(String nousado1, String nousado2, String nousado3) {
		
	}
	
	protected static final EntityManagerFactory FABRICA_JPA = Persistence
			.createEntityManagerFactory("com.ipartek.almacen.pojos");

	protected <T> T enTransaccion(Function<EntityManager, T> codigo) {

		EntityTransaction transaction = null;

		try (EntityManager entityManager = FABRICA_JPA.createEntityManager();) {
			transaction = entityManager.getTransaction();

			transaction.begin();
			var resultado = codigo.apply(entityManager);
			transaction.commit();
			
			return resultado;
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			throw new AccesoDatosException("Error en la operación JPA", e);
		}
	}
}
