package com.tmg.ebscore.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

public class PackageRepositoryImpl implements PackageCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public <T> T saveData(T t) {

		try {
			System.out.println("gdjsdfjkfgkhdghk");
			entityManager.persist(t);
			entityManager.flush();
			System.out.println("");
			return t;
		} catch (PersistenceException e) {
			throw e;
		}

	}

	@Override
	public <T> void updateData(T t) {

		try {
			entityManager.merge(t);
		} catch (PersistenceException e) {

			throw e;
		}

	}

	@Override
	public <T> void detachedEntity(T t) {

		try {
			entityManager.detach(t);
		} catch (PersistenceException e) {

			throw e;
		}

	}

	@Override
	public <T> void deleteData(T t) {

		try {
			entityManager.remove(t);
		} catch (PersistenceException e) {

			throw e;
		}

	}

}
