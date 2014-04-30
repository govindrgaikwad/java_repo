package com.tmg.ebscore.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.tmg.ebscore.dto.IsLockedData;
import com.tmg.ebscore.orm.IsLocked;

public class MasterListRepositoryImpl implements MasterListCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public <T> void saveData(T t) {

		try {
			entityManager.persist(t);
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
	public <T> void deleteData(T t) {

		try {
			entityManager.remove(t);
		} catch (PersistenceException e) {

			throw e;
		}

	}

	@Override
	public List<Integer> findLockedValues(int id, IsLocked isLocked) {

		List<Integer> ids = new ArrayList<Integer>();

		Query query = entityManager
				.createNativeQuery(
						IsLocked.fromValue(IsLocked
								.getStoredProcedure(isLocked)),
						IsLockedData.class).setParameter(1, 1)
				.setParameter(2, id);

		@SuppressWarnings("unchecked")
		List<IsLockedData> results = query.getResultList();
		for (IsLockedData isLockedData : results) {
			if (isLockedData.getIsLocked() == true) {
				ids.add(isLockedData.getMasterListVal1up());
			}
		}

		return ids;
	}

}
