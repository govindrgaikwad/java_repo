package com.empresa.marco.services;

import java.util.List;

import com.empresa.marco.data.AttributeData;
import com.empresa.marco.exceptions.DataAccessException;

public interface AttributeService {

	public AttributeData getAttributeById(Integer attributeId)
			throws DataAccessException;

	public List<AttributeData> getAttributeByProjectVersion(Integer projectId,
			Integer projectVersionId) throws DataAccessException;

	public List<AttributeData> getAttributeByOjectId(Integer objectId,
			Integer projectId, Integer ProjectVersionId)
			throws DataAccessException;

	public AttributeData getAttributeByNameAndObjectId(String attributeName,
			Integer objectId, Integer projectId, Integer ProjectVersionId)
			throws DataAccessException;

	public Boolean saveAttribute(AttributeData attributeData)
			throws DataAccessException;

	public Boolean updateAttribute(AttributeData attributeData)
			throws DataAccessException;

	public Boolean deleteAttribute(AttributeData attributeData)
			throws DataAccessException;

	public Boolean deleteAttributeById(Integer attributeId)
			throws DataAccessException;

}
