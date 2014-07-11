package com.wide.stringer.generator.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

public class GenericRepositoryImpl implements GenericCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public <T> void saveData(T t) {

		try {
			entityManager.persist(t);
			entityManager.flush();
		} catch (PersistenceException e) {
			throw e;
		}

	}

	public <T> void updateData(T t) {

		try {
			entityManager.merge(t);
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
