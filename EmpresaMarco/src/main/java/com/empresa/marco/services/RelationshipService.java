package com.empresa.marco.services;

import java.util.List;

import com.empresa.marco.data.RelationshipData;
import com.empresa.marco.exceptions.DataAccessException;

public interface RelationshipService {

	public RelationshipData getRelationshipById(Integer relationId)
			throws DataAccessException;

	public List<RelationshipData> getAllRelationship(Integer projectId,
			Integer projectVersionId) throws DataAccessException;

	public List<RelationshipData> getAllRelationshipByParentObjectId(
			Integer objectId, Integer projectId, Integer projectVersionId)
			throws DataAccessException;

	public Boolean saveRelationship(RelationshipData relationshipData)
			throws DataAccessException;

	public Boolean upadateRelationship(RelationshipData relationshipData)
			throws DataAccessException;

	public Boolean deleteRelationship(RelationshipData relationshipData)
			throws DataAccessException;

	public Boolean deleteRelationshipById(Integer relationshipId)
			throws DataAccessException;

}
