package com.tmg.uifwk.repository.ui;

public interface GenericUICustomRepository {
	public <T> T saveData(T t);

	public <T> T updateData(T t);

	public <T> void deleteData(T t);

}
