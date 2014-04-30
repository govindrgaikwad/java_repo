package com.tmg.ebscore.repository;

public interface PackageVersionCustomRepository {

	public <T> T saveData(T t);

	public <T> void updateData(T t);

	public <T> void deleteData(T t);
	
	public <T> void detachedEntity(T t);

}
