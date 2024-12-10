package com.ipartek.almacen.accesodatos;

import java.util.function.Consumer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DaoJpa {
	public DaoJpa(String nousado1, String nousado2, String nousado3) {
		
	}
	
	protected static final EntityManagerFactory FABRICA_JPA = Persistence
			.createEntityManagerFactory("com.ipartek.almacen.pojos");

	protected void inTransaction(Consumer<EntityManager> work) {

		EntityTransaction transaction = null;

		try (EntityManager entityManager = FABRICA_JPA.createEntityManager();) {
			transaction = entityManager.getTransaction();

			transaction.begin();
			work.accept(entityManager);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			throw new AccesoDatosException("Error en la operación JPA", e);
		}
	}
}
