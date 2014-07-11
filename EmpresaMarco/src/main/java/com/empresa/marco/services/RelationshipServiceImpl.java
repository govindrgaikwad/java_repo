package com.empresa.marco.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.marco.data.RelationshipData;
import com.empresa.marco.entity.Relationship;
import com.empresa.marco.exceptions.DataAccessException;
import com.empresa.marco.repository.GenericRepository;

@Service("RelationshipService")
public class RelationshipServiceImpl implements RelationshipService {

	private static Logger logger = Logger
			.getLogger(RelationshipServiceImpl.class);

	@Autowired
	GenericRepository repository;

	@Override
	public RelationshipData getRelationshipById(Integer relationId)
			throws DataAccessException {
		RelationshipData data = new RelationshipData();
		Relationship newRelationship = null;
		try {
			newRelationship = repository.findRelationshipById(relationId);
			if (newRelationship != null) {
				data.setRelationshipId(newRelationship.getRelationshipId());

				if (newRelationship.getObjectDefination() != null)
					data.setParentObjectId(newRelationship
							.getObjectDefination().getObjectId());

				if (newRelationship.getObjectDefination1() != null)
					data.setChildObjectId(newRelationship
							.getObjectDefination1().getObjectId());

				if (newRelationship.getAttribute() != null)
					data.setParentAttributeId(newRelationship.getAttribute()
							.getAttributeId());

				if (newRelationship.getAttribute1() != null)
					data.setChildAttributeId(newRelationship.getAttribute1()
							.getAttributeId());

				data.setRelationName(newRelationship.getRelationName());

				data.setCamelcaseName(newRelationship.getCamelcaseName());

				data.setCardinality(newRelationship.getCardinality());

				if (newRelationship.getProject() != null)
					data.setProjectId(newRelationship.getProject()
							.getProjectId());

				if (newRelationship.getProjectVersion() != null)
					data.setProjectVersionId(newRelationship
							.getProjectVersion().getProjectVersionId());

				data.setCreatedBy(newRelationship.getCreatedBy());

				data.setCreatedDate(newRelationship.getCreatedDate());

				data.setUpdatedBy(newRelationship.getUpdatedBy());

				data.setUpdatedDate(newRelationship.getUpdatedDate());

			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return data;
	}

	@Override
	public List<RelationshipData> getAllRelationship(Integer projectId,
			Integer projectVersionId) throws DataAccessException {
		List<RelationshipData> relationshipDataList = new ArrayList<RelationshipData>();
		List<Relationship> relationshipList = null;
		try {
			relationshipList = repository.findAllRelationship(projectId,
					projectVersionId);
			if (relationshipList != null && relationshipList.size() > 0) {
				for (Relationship newRelationship : relationshipList) {
					RelationshipData data = new RelationshipData();
					data.setRelationshipId(newRelationship.getRelationshipId());

					if (newRelationship.getObjectDefination() != null)
						data.setParentObjectId(newRelationship
								.getObjectDefination().getObjectId());

					if (newRelationship.getObjectDefination1() != null)
						data.setChildObjectId(newRelationship
								.getObjectDefination1().getObjectId());

					if (newRelationship.getAttribute() != null)
						data.setParentAttributeId(newRelationship
								.getAttribute().getAttributeId());

					if (newRelationship.getAttribute1() != null)
						data.setChildAttributeId(newRelationship
								.getAttribute1().getAttributeId());

					data.setRelationName(newRelationship.getRelationName());

					data.setCamelcaseName(newRelationship.getCamelcaseName());

					data.setCardinality(newRelationship.getCardinality());

					if (newRelationship.getProject() != null)
						data.setProjectId(newRelationship.getProject()
								.getProjectId());

					if (newRelationship.getProjectVersion() != null)
						data.setProjectVersionId(newRelationship
								.getProjectVersion().getProjectVersionId());

					data.setCreatedBy(newRelationship.getCreatedBy());

					data.setCreatedDate(newRelationship.getCreatedDate());

					data.setUpdatedBy(newRelationship.getUpdatedBy());

					data.setUpdatedDate(newRelationship.getUpdatedDate());

					relationshipDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return relationshipDataList;
	}

	@Override
	public List<RelationshipData> getAllRelationshipByParentObjectId(
			Integer objectId, Integer projectId, Integer projectVersionId)
			throws DataAccessException {

		return null;
	}

	@Override
	@Transactional
	public Boolean saveRelationship(RelationshipData relationshipData)
			throws DataAccessException {
		Boolean result = true;
		try {
			Relationship relationship = new Relationship();
			relationship
					.setRelationshipId(relationshipData.getRelationshipId());
			if (relationshipData.getParentObjectId() != null) {
				relationship.setObjectDefination(repository
						.findObjectDefinationById(relationshipData
								.getParentObjectId()));
			}
			if (relationshipData.getChildObjectId() != null) {
				relationship.setObjectDefination1(repository
						.findObjectDefinationById(relationshipData
								.getChildObjectId()));
			}
			if (relationshipData.getParentAttributeId() != null) {
				relationship.setAttribute(repository
						.findAttributeById(relationshipData
								.getParentAttributeId()));
			}
			if (relationshipData.getChildAttributeId() != null) {
				relationship.setAttribute1(repository
						.findAttributeById(relationshipData
								.getChildAttributeId()));
			}
			relationship.setRelationName(relationshipData.getRelationName());
			relationship.setCamelcaseName(relationshipData.getCamelcaseName());
			relationship.setCardinality(relationshipData.getCardinality());
			if (relationshipData.getProjectId() != null) {
				relationship.setProject(repository
						.findProjectById(relationshipData.getProjectId()));
			}
			if (relationshipData.getProjectVersionId() != null) {
				relationship.setProjectVersion(repository
						.findProjectVersionById(relationshipData
								.getProjectVersionId()));
			}
			relationship.setCreatedBy(relationshipData.getCreatedBy());
			relationship.setCreatedDate(relationshipData.getCreatedDate());
			relationship.setUpdatedBy(relationshipData.getUpdatedBy());
			relationship.setUpdatedDate(relationshipData.getUpdatedDate());

			repository.saveData(relationship);
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;
	}

	@Override
	public Boolean upadateRelationship(RelationshipData relationshipData)
			throws DataAccessException {
		Boolean result = true;
		try {
			Relationship relationship = null;
			if (relationshipData.getRelationshipId() != null
					&& relationshipData.getRelationshipId() > 0) {
				relationship = repository.findRelationshipById(relationshipData
						.getRelationshipId());
				if (relationship == null) {
					result = false;
					logger.error("Relationship Does Not Exist");
					throw new DataAccessException("Relationship Does Not Exist");
				}
				if (relationshipData.getParentObjectId() != null) {
					relationship.setObjectDefination(repository
							.findObjectDefinationById(relationshipData
									.getParentObjectId()));
				}
				if (relationshipData.getChildObjectId() != null) {
					relationship.setObjectDefination1(repository
							.findObjectDefinationById(relationshipData
									.getChildObjectId()));
				}
				if (relationshipData.getParentAttributeId() != null) {
					relationship.setAttribute(repository
							.findAttributeById(relationshipData
									.getParentAttributeId()));
				}
				if (relationshipData.getChildAttributeId() != null) {
					relationship.setAttribute1(repository
							.findAttributeById(relationshipData
									.getChildAttributeId()));
				}
				relationship
						.setRelationName(relationshipData.getRelationName());
				relationship.setCamelcaseName(relationshipData
						.getCamelcaseName());
				relationship.setCardinality(relationshipData.getCardinality());
				if (relationshipData.getProjectId() != null) {
					relationship.setProject(repository
							.findProjectById(relationshipData.getProjectId()));
				}
				if (relationshipData.getProjectVersionId() != null) {
					relationship.setProjectVersion(repository
							.findProjectVersionById(relationshipData
									.getProjectVersionId()));
				}
				relationship.setCreatedBy(relationshipData.getCreatedBy());
				relationship.setCreatedDate(relationshipData.getCreatedDate());
				relationship.setUpdatedBy(relationshipData.getUpdatedBy());
				relationship.setUpdatedDate(relationshipData.getUpdatedDate());
				repository.updateData(relationship);
			}
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;
	}

	@Override
	public Boolean deleteRelationship(RelationshipData relationshipData)
			throws DataAccessException {
		Boolean result = true;
		try {
			Relationship relationship = null;
			if (relationshipData.getRelationshipId() != null
					&& relationshipData.getRelationshipId() > 0) {
				relationship = repository.findRelationshipById(relationshipData
						.getRelationshipId());
			}
			if (relationship == null) {
				result = false;
				logger.error("Relationship Does Not Exist");
				throw new DataAccessException("Relationship Does Not Exist");
			}
			repository.deleteData(relationship);
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;

	}

	@Override
	public Boolean deleteRelationshipById(Integer relationshipId)
			throws DataAccessException {
		Boolean result = true;
		try {
			Relationship relationship = repository
					.findRelationshipById(relationshipId);
			if (relationship == null) {
				result = false;
				logger.error("Relationship Does Not Exist");
				throw new DataAccessException("Relationship Does Not Exist");
			}
			repository.deleteData(relationship);
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;
	}

}
