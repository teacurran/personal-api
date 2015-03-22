package com.wirelust.personalapi.producers;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * Date: 09-03-2015
 *
 * @Author T. Curran
 */
public class EntityManagerProducer {

	@PersistenceContext
	private EntityManager entityManager;

	@Produces
	public EntityManager create() {
		return this.entityManager;
	}

	public void close(@Disposes EntityManager em) {
		if (em != null && em.isOpen()) {
			em.close();
		}
	}

}
