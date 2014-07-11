package com.empresa.marco.repository;

public interface GenericCustomRepository {

	public <T> void saveData(T t);

	public <T> void updateData(T t);

	public <T> void deleteData(T t);
	
}