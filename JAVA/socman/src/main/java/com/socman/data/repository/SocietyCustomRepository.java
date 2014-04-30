package com.socman.data.repository;

public interface SocietyCustomRepository {

	public <T> T saveData(T t);

	public <T> T updateData(T t);

	public <T> void deleteData(T t);

}
