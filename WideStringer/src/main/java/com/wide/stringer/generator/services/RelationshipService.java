package com.wide.stringer.generator.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wide.stringer.generator.dto.RelationshipData;
import com.wide.stringer.generator.entity.Relationship;
import com.wide.stringer.generator.exceptions.DataAccessException;
import com.wide.stringer.generator.exceptions.OperationResult;
import com.wide.stringer.generator.exceptions.ServiceOperationResult;
import com.wide.stringer.generator.repository.GenericRepository;

@Service("RelationshipService")
public class RelationshipService {

	private static Logger logger = Logger.getLogger(RelationshipService.class);

	@Autowired
	private GenericRepository repository;

	@Transactional(readOnly = true)
	public List<RelationshipData> getAllRelationship()
			throws DataAccessException {
		List<RelationshipData> relationshipDataList = new ArrayList<RelationshipData>();
		List<Relationship> relationshipList = null;
		try {
			relationshipList = repository.findAllRelationship();
			if (relationshipList != null && relationshipList.size() > 0) {
				for (Relationship newRelationship : relationshipList) {
					RelationshipData data = new RelationshipData();
					data.setRelationshipId(newRelationship.getRelationshipId());

					if (newRelationship.getParentObjectDefination() != null)
						data.setParentObjectId(newRelationship
								.getParentObjectDefination().getObjectId());

					if (newRelationship.getRelatedObjectDefination() != null)
						data.setRelatedObjectId(newRelationship
								.getRelatedObjectDefination().getObjectId());

					if (newRelationship.getParentAttribute() != null)
						data.setParentAttributeId(newRelationship
								.getParentAttribute().getAttributeId());

					if (newRelationship.getChildAttribute() != null)
						data.setChildAttributeId(newRelationship
								.getChildAttribute().getAttributeId());

					data.setRelationName(newRelationship.getRelationName());

					data.setCamelcaseName(newRelationship.getCamelcaseName());

					data.setCardinality(newRelationship.getCardinality());

					relationshipDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return relationshipDataList;
	}

	@Transactional(readOnly = true)
	public RelationshipData getRelationshipById(Integer relationshipIdId)
			throws DataAccessException {
		RelationshipData data = new RelationshipData();
		Relationship newRelationship = null;
		try {
			newRelationship = repository.findRelationshipById(relationshipIdId);
			if (newRelationship != null) {
				data.setRelationshipId(newRelationship.getRelationshipId());

				if (newRelationship.getParentObjectDefination() != null)
					data.setParentObjectId(newRelationship
							.getParentObjectDefination().getObjectId());

				if (newRelationship.getRelatedObjectDefination() != null)
					data.setRelatedObjectId(newRelationship
							.getRelatedObjectDefination().getObjectId());

				if (newRelationship.getParentAttribute() != null)
					data.setParentAttributeId(newRelationship
							.getParentAttribute().getAttributeId());

				if (newRelationship.getChildAttribute() != null)
					data.setChildAttributeId(newRelationship
							.getChildAttribute().getAttributeId());

				data.setRelationName(newRelationship.getRelationName());

				data.setCamelcaseName(newRelationship.getCamelcaseName());

				data.setCardinality(newRelationship.getCardinality());

			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return data;
	}

	@Transactional
	public ServiceOperationResult deleteRelationshipById(
			Integer relationshipIdId) throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			Relationship relationship = repository
					.findRelationshipById(relationshipIdId);
			if (relationship == null) {
				operationResult.setOperationResult(OperationResult.WARNING);
				messages.add("Relationship Does Not Exist");
				logger.error("Relationship Does Not Exist");
				throw new DataAccessException("Relationship Does Not Exist");
			}
			repository.deleteData(relationship);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while deleting value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

	@Transactional(readOnly = true)
	public List<RelationshipData> getRelationshipByParentObjectId(
			Integer parentObjectId) throws DataAccessException {
		List<RelationshipData> relationshipDataList = new ArrayList<RelationshipData>();
		List<Relationship> relationshipList = null;
		try {
			relationshipList = repository
					.findRelationshipByParentObjectId(parentObjectId);
			if (relationshipList != null && relationshipList.size() > 0) {
				for (Relationship newRelationship : relationshipList) {
					RelationshipData data = new RelationshipData();
					data.setRelationshipId(newRelationship.getRelationshipId());

					if (newRelationship.getParentObjectDefination() != null)
						data.setParentObjectId(newRelationship
								.getParentObjectDefination().getObjectId());

					if (newRelationship.getRelatedObjectDefination() != null)
						data.setRelatedObjectId(newRelationship
								.getRelatedObjectDefination().getObjectId());

					if (newRelationship.getParentAttribute() != null)
						data.setParentAttributeId(newRelationship
								.getParentAttribute().getAttributeId());

					if (newRelationship.getChildAttribute() != null)
						data.setChildAttributeId(newRelationship
								.getChildAttribute().getAttributeId());

					data.setRelationName(newRelationship.getRelationName());

					data.setCamelcaseName(newRelationship.getCamelcaseName());

					data.setCardinality(newRelationship.getCardinality());

					relationshipDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return relationshipDataList;
	}

	@Transactional(readOnly = true)
	public List<RelationshipData> getRelationshipByRelatedObjectId(
			Integer relatedObjectId) throws DataAccessException {
		List<RelationshipData> relationshipDataList = new ArrayList<RelationshipData>();
		List<Relationship> relationshipList = null;
		try {
			relationshipList = repository
					.findRelationshipByRelatedObjectId(relatedObjectId);
			if (relationshipList != null && relationshipList.size() > 0) {
				for (Relationship newRelationship : relationshipList) {
					RelationshipData data = new RelationshipData();
					data.setRelationshipId(newRelationship.getRelationshipId());

					if (newRelationship.getParentObjectDefination() != null)
						data.setParentObjectId(newRelationship
								.getParentObjectDefination().getObjectId());

					if (newRelationship.getRelatedObjectDefination() != null)
						data.setRelatedObjectId(newRelationship
								.getRelatedObjectDefination().getObjectId());

					if (newRelationship.getParentAttribute() != null)
						data.setParentAttributeId(newRelationship
								.getParentAttribute().getAttributeId());

					if (newRelationship.getChildAttribute() != null)
						data.setChildAttributeId(newRelationship
								.getChildAttribute().getAttributeId());

					data.setRelationName(newRelationship.getRelationName());

					data.setCamelcaseName(newRelationship.getCamelcaseName());

					data.setCardinality(newRelationship.getCardinality());

					relationshipDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return relationshipDataList;
	}

	@Transactional(readOnly = true)
	public List<RelationshipData> getRelationshipByParentAttributeId(
			Integer parentAttributeId) throws DataAccessException {
		List<RelationshipData> relationshipDataList = new ArrayList<RelationshipData>();
		List<Relationship> relationshipList = null;
		try {
			relationshipList = repository
					.findRelationshipByParentAttributeId(parentAttributeId);
			if (relationshipList != null && relationshipList.size() > 0) {
				for (Relationship newRelationship : relationshipList) {
					RelationshipData data = new RelationshipData();
					data.setRelationshipId(newRelationship.getRelationshipId());

					if (newRelationship.getParentObjectDefination() != null)
						data.setParentObjectId(newRelationship
								.getParentObjectDefination().getObjectId());

					if (newRelationship.getRelatedObjectDefination() != null)
						data.setRelatedObjectId(newRelationship
								.getRelatedObjectDefination().getObjectId());

					if (newRelationship.getParentAttribute() != null)
						data.setParentAttributeId(newRelationship
								.getParentAttribute().getAttributeId());

					if (newRelationship.getChildAttribute() != null)
						data.setChildAttributeId(newRelationship
								.getChildAttribute().getAttributeId());

					data.setRelationName(newRelationship.getRelationName());

					data.setCamelcaseName(newRelationship.getCamelcaseName());

					data.setCardinality(newRelationship.getCardinality());

					relationshipDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return relationshipDataList;
	}

	@Transactional(readOnly = true)
	public List<RelationshipData> getRelationshipByChildAttributeId(
			Integer childAttributeId) throws DataAccessException {
		List<RelationshipData> relationshipDataList = new ArrayList<RelationshipData>();
		List<Relationship> relationshipList = null;
		try {
			relationshipList = repository
					.findRelationshipByChildAttributeId(childAttributeId);
			if (relationshipList != null && relationshipList.size() > 0) {
				for (Relationship newRelationship : relationshipList) {
					RelationshipData data = new RelationshipData();
					data.setRelationshipId(newRelationship.getRelationshipId());

					if (newRelationship.getParentObjectDefination() != null)
						data.setParentObjectId(newRelationship
								.getParentObjectDefination().getObjectId());

					if (newRelationship.getRelatedObjectDefination() != null)
						data.setRelatedObjectId(newRelationship
								.getRelatedObjectDefination().getObjectId());

					if (newRelationship.getParentAttribute() != null)
						data.setParentAttributeId(newRelationship
								.getParentAttribute().getAttributeId());

					if (newRelationship.getChildAttribute() != null)
						data.setChildAttributeId(newRelationship
								.getChildAttribute().getAttributeId());

					data.setRelationName(newRelationship.getRelationName());

					data.setCamelcaseName(newRelationship.getCamelcaseName());

					data.setCardinality(newRelationship.getCardinality());

					relationshipDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return relationshipDataList;
	}

	@Transactional(readOnly = true)
	public List<RelationshipData> getRelationshipByRelationName(
			String relationName) throws DataAccessException {
		List<RelationshipData> relationshipDataList = new ArrayList<RelationshipData>();
		List<Relationship> relationshipList = null;
		try {
			relationshipList = repository
					.findRelationshipByRelationName(relationName);
			if (relationshipList != null && relationshipList.size() > 0) {
				for (Relationship newRelationship : relationshipList) {
					RelationshipData data = new RelationshipData();
					data.setRelationshipId(newRelationship.getRelationshipId());

					if (newRelationship.getParentObjectDefination() != null)
						data.setParentObjectId(newRelationship
								.getParentObjectDefination().getObjectId());

					if (newRelationship.getRelatedObjectDefination() != null)
						data.setRelatedObjectId(newRelationship
								.getRelatedObjectDefination().getObjectId());

					if (newRelationship.getParentAttribute() != null)
						data.setParentAttributeId(newRelationship
								.getParentAttribute().getAttributeId());

					if (newRelationship.getChildAttribute() != null)
						data.setChildAttributeId(newRelationship
								.getChildAttribute().getAttributeId());

					data.setRelationName(newRelationship.getRelationName());

					data.setCamelcaseName(newRelationship.getCamelcaseName());

					data.setCardinality(newRelationship.getCardinality());

					relationshipDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return relationshipDataList;
	}

	@Transactional(readOnly = true)
	public List<RelationshipData> getRelationshipByCamelcaseName(
			String camelcaseName) throws DataAccessException {
		List<RelationshipData> relationshipDataList = new ArrayList<RelationshipData>();
		List<Relationship> relationshipList = null;
		try {
			relationshipList = repository
					.findRelationshipByCamelcaseName(camelcaseName);
			if (relationshipList != null && relationshipList.size() > 0) {
				for (Relationship newRelationship : relationshipList) {
					RelationshipData data = new RelationshipData();
					data.setRelationshipId(newRelationship.getRelationshipId());

					if (newRelationship.getParentObjectDefination() != null)
						data.setParentObjectId(newRelationship
								.getParentObjectDefination().getObjectId());

					if (newRelationship.getRelatedObjectDefination() != null)
						data.setRelatedObjectId(newRelationship
								.getRelatedObjectDefination().getObjectId());

					if (newRelationship.getParentAttribute() != null)
						data.setParentAttributeId(newRelationship
								.getParentAttribute().getAttributeId());

					if (newRelationship.getChildAttribute() != null)
						data.setChildAttributeId(newRelationship
								.getChildAttribute().getAttributeId());

					data.setRelationName(newRelationship.getRelationName());

					data.setCamelcaseName(newRelationship.getCamelcaseName());

					data.setCardinality(newRelationship.getCardinality());

					relationshipDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return relationshipDataList;
	}

	@Transactional(readOnly = true)
	public List<RelationshipData> getRelationshipByCardinality(
			String cardinality) throws DataAccessException {
		List<RelationshipData> relationshipDataList = new ArrayList<RelationshipData>();
		List<Relationship> relationshipList = null;
		try {
			relationshipList = repository
					.findRelationshipByCardinality(cardinality);
			if (relationshipList != null && relationshipList.size() > 0) {
				for (Relationship newRelationship : relationshipList) {
					RelationshipData data = new RelationshipData();
					data.setRelationshipId(newRelationship.getRelationshipId());

					if (newRelationship.getParentObjectDefination() != null)
						data.setParentObjectId(newRelationship
								.getParentObjectDefination().getObjectId());

					if (newRelationship.getRelatedObjectDefination() != null)
						data.setRelatedObjectId(newRelationship
								.getRelatedObjectDefination().getObjectId());

					if (newRelationship.getParentAttribute() != null)
						data.setParentAttributeId(newRelationship
								.getParentAttribute().getAttributeId());

					if (newRelationship.getChildAttribute() != null)
						data.setChildAttributeId(newRelationship
								.getChildAttribute().getAttributeId());

					data.setRelationName(newRelationship.getRelationName());

					data.setCamelcaseName(newRelationship.getCamelcaseName());

					data.setCardinality(newRelationship.getCardinality());

					relationshipDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return relationshipDataList;
	}

	@Transactional
	public ServiceOperationResult saveRelationship(RelationshipData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			Relationship relationship = new Relationship();
			relationship.setRelationshipId(data.getRelationshipId());
			if (data.getParentObjectId() != null) {
				relationship.setParentObjectDefination(repository
						.findObjectDefinationById(data.getParentObjectId()));
			}
			if (data.getRelatedObjectId() != null) {
				relationship.setRelatedObjectDefination(repository
						.findObjectDefinationById(data.getRelatedObjectId()));
			}
			if (data.getParentAttributeId() != null) {
				relationship.setParentAttribute(repository
						.findAttributeById(data.getParentAttributeId()));
			}
			if (data.getChildAttributeId() != null) {
				relationship.setChildAttribute(repository
						.findAttributeById(data.getChildAttributeId()));
			}
			relationship.setRelationName(data.getRelationName());
			relationship.setCamelcaseName(data.getCamelcaseName());
			relationship.setCardinality(data.getCardinality());

			repository.saveData(relationship);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while Saving Value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

	@Transactional
	public ServiceOperationResult updateRelationship(RelationshipData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			Relationship relationship = null;
			if (data.getRelationshipId() != null
					&& data.getRelationshipId() > 0) {
				relationship = repository.findRelationshipById(data
						.getRelationshipId());
				if (relationship == null) {
					operationResult.setOperationResult(OperationResult.WARNING);
					messages.add("Relationship Does Not Exist");
					logger.error("Relationship Does Not Exist");
					throw new DataAccessException("Relationship Does Not Exist");
				}
				if (data.getParentObjectId() != null) {
					relationship
							.setParentObjectDefination(repository
									.findObjectDefinationById(data
											.getParentObjectId()));
				}
				if (data.getRelatedObjectId() != null) {
					relationship
							.setRelatedObjectDefination(repository
									.findObjectDefinationById(data
											.getRelatedObjectId()));
				}
				if (data.getParentAttributeId() != null) {
					relationship.setParentAttribute(repository
							.findAttributeById(data.getParentAttributeId()));
				}
				if (data.getChildAttributeId() != null) {
					relationship.setChildAttribute(repository
							.findAttributeById(data.getChildAttributeId()));
				}
				relationship.setRelationName(data.getRelationName());
				relationship.setCamelcaseName(data.getCamelcaseName());
				relationship.setCardinality(data.getCardinality());
				repository.updateData(relationship);
			}
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while Updating Value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

	@Transactional
	public ServiceOperationResult deleteRelationship(RelationshipData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			Relationship relationship = null;
			if (data.getRelationshipId() != null
					&& data.getRelationshipId() > 0) {
				relationship = repository.findRelationshipById(data
						.getRelationshipId());
			}
			if (relationship == null) {
				operationResult.setOperationResult(OperationResult.WARNING);
				messages.add("Relationship Does Not Exist");
				logger.error("Relationship Does Not Exist");
				throw new DataAccessException("Relationship Does Not Exist");
			}
			repository.deleteData(relationship);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while Deleting Value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

}