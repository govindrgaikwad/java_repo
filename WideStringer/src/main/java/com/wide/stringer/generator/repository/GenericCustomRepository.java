package com.wide.stringer.generator.repository;

public interface GenericCustomRepository {

	public <T> void saveData(T t);

	public <T> void updateData(T t);

	public <T> void deleteData(T t);
	
}