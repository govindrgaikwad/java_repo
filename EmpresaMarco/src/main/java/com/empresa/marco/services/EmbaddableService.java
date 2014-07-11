package com.empresa.marco.services;

import com.empresa.marco.data.EmbaddableData;
import com.empresa.marco.exceptions.DataAccessException;

public interface EmbaddableService {

	public EmbaddableData getEmbaddableById(Integer embaddableId)
			throws DataAccessException;

	public EmbaddableData getEmbaddableByOjectId(Integer objectId)
			throws DataAccessException;

	public Boolean saveEmbaddable(EmbaddableData embaddableData)
			throws DataAccessException;

	public Boolean updateEmbaddable(EmbaddableData embaddableData)
			throws DataAccessException;

	public Boolean deleteEmbaddable(EmbaddableData embaddableData)
			throws DataAccessException;

	public Boolean deleteEmbaddableById(Integer embaddableId)
			throws DataAccessException;

}
