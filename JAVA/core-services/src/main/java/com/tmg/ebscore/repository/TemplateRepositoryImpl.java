package com.tmg.ebscore.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.tmg.ebscore.dto.IsLockedData;
import com.tmg.ebscore.dto.IsLockedServiceDefinitionData;
import com.tmg.ebscore.orm.IsLocked;

public class TemplateRepositoryImpl implements TemplateCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public <T> T saveData(T t) {

		try {
			entityManager.persist(t);
			entityManager.flush();
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
	public <T> void deleteData(T t) {

		try {
			entityManager.remove(t);
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
	public List<Integer> findLockedValues(int masterListId, int tempId,
			IsLocked isLocked) {
		List<Integer> ids = new ArrayList<Integer>();

		Query query = entityManager
				.createNativeQuery(
						IsLocked.fromValue(IsLocked
								.getStoredProcedure(isLocked)),
						IsLockedData.class).setParameter(1, 1)
				.setParameter(2, tempId).setParameter(3, masterListId);

		@SuppressWarnings("unchecked")
		List<IsLockedData> results = query.getResultList();
		for (IsLockedData isLockedData : results) {
			if (isLockedData.getIsLocked() == true) {
				ids.add(isLockedData.getMasterListVal1up());
			}
		}

		return ids;
	}

	@Override
	public List<Integer> findLockedValuesForServiceDefinition(int masterListId,
			int tempId) {
		List<Integer> ids = new ArrayList<Integer>();

		Query query = entityManager
				.createNativeQuery(
						"{call usp_GetTemplateMasterListServiceDefinition(?,?,?)}",
						IsLockedServiceDefinitionData.class).setParameter(1, 1)
				.setParameter(2, tempId).setParameter(3, masterListId);

		@SuppressWarnings("unchecked")
		List<IsLockedServiceDefinitionData> results = query.getResultList();
		for (IsLockedServiceDefinitionData isLockedData : results) {
			if (isLockedData.getIsLocked() == true) {
				ids.add(isLockedData.getMasterListServiceDefinition1up());
			}
		}

		return ids;
	}
}
