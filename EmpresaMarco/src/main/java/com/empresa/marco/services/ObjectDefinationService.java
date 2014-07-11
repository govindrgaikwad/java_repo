package com.empresa.marco.services;

import java.util.List;

import com.empresa.marco.data.ObjectDefinationData;
import com.empresa.marco.exceptions.DataAccessException;

public interface ObjectDefinationService {

	public ObjectDefinationData getObjectDefinationById(Integer objectId)throws DataAccessException;

	public List<ObjectDefinationData> getAllObjectDefination(Integer projectId,
			Integer projectVersionId)throws DataAccessException;

	public ObjectDefinationData getObjectDefinationByName(String name,
			Integer projectId, Integer projectVersionId)throws DataAccessException;

	public List<ObjectDefinationData> getObjectDefinationByEmbaddable(
			Boolean embaddable, Integer projectId, Integer projectVersionId)throws DataAccessException;

	public Boolean saveObjectDefination(
			ObjectDefinationData objectDefinationData)throws DataAccessException;

	public Boolean updateObjectDefination(
			ObjectDefinationData objectDefinationData)throws DataAccessException;

	public Boolean deleteObjectDefination(
			ObjectDefinationData objectDefinationData)throws DataAccessException;

	public Boolean deleteObjectDefinationById(Integer objectId)throws DataAccessException;

}
