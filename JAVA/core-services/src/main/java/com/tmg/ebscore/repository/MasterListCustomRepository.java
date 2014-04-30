package com.tmg.ebscore.repository;

import java.util.List;

import com.tmg.ebscore.orm.IsLocked;

public interface MasterListCustomRepository {

	public <T> void saveData(T t);

	public <T> void updateData(T t);

	public <T> void deleteData(T t);

	public List<Integer> findLockedValues(int id, IsLocked isLocked);

}
