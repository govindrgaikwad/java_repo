package com.tmg.ebscore.repository;

import java.util.List;

import com.tmg.ebscore.orm.IsLocked;

public interface TemplateCustomRepository {

	public <T> T saveData(T t);

	public <T> void updateData(T t);

	public <T> void deleteData(T t);

	public <T> void detachedEntity(T t);

	public List<Integer> findLockedValues(int masterListId, int tempId,
			IsLocked isLocked);
	
	public List<Integer> findLockedValuesForServiceDefinition(int masterListId, int tempId);

}
