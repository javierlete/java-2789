package com.ipartek.formacion.amazonia.accesodatos;

import java.util.function.Function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaDao {
	private EntityManagerFactory entityManagerFactory;

	public JpaDao(String url, String nousado2, String nousado3) {
		entityManagerFactory = Persistence.createEntityManagerFactory(url);
	}

	protected <T> T enTransaccion(Function<EntityManager, T> codigo) {

		EntityTransaction transaction = null;
		EntityManager entityManager = null;

		try {
			entityManager = entityManagerFactory.createEntityManager();
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
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}
}
