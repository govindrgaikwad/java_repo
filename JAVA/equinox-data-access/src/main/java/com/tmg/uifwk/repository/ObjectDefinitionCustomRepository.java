package com.tmg.uifwk.repository;

public interface ObjectDefinitionCustomRepository {
	
	public <T> void saveData(T t);

	public <T> void updateData(T t);

	public <T> void deleteData(T t);
}
