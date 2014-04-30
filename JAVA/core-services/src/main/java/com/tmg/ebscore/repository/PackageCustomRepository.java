package com.tmg.ebscore.repository;

public interface PackageCustomRepository {

	public <T> T saveData(T t);

	public <T> void detachedEntity(T t);

	public <T> void updateData(T t);

	public <T> void deleteData(T t);

}
