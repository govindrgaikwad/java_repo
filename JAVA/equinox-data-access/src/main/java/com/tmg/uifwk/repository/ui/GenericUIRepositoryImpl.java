package com.tmg.uifwk.repository.ui;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

public class GenericUIRepositoryImpl implements GenericUICustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public <T> T saveData(T t) {

		try {
			entityManager.persist(t);
			// entityManager.flush();
			return t;
		} catch (PersistenceException e) {
			throw e;
		}

	}

	public <T> T updateData(T t) {

		try {
			entityManager.merge(t);
			// entityManager.flush();
			return t;
		} catch (PersistenceException e) {

			throw e;
		}

	}

	public <T> void deleteData(T t) {

		try {
			entityManager.remove(t);
		} catch (PersistenceException e) {

			throw e;
		}

	}
}
