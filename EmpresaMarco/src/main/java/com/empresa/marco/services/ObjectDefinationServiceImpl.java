package com.empresa.marco.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.marco.data.ObjectDefinationData;
import com.empresa.marco.entity.ObjectDefination;
import com.empresa.marco.exceptions.DataAccessException;
import com.empresa.marco.repository.GenericRepository;

@Service("ObjectDefinationService")
public class ObjectDefinationServiceImpl implements ObjectDefinationService {

	private static Logger logger = Logger
			.getLogger(ObjectDefinationServiceImpl.class);

	@Autowired
	GenericRepository repository;

	@Override
	public ObjectDefinationData getObjectDefinationById(Integer objectId)
			throws DataAccessException {

		ObjectDefinationData data = new ObjectDefinationData();
		ObjectDefination newObjectDefination = null;
		try {
			newObjectDefination = repository.findObjectDefinationById(objectId);
			if (newObjectDefination != null) {
				data.setObjectId(newObjectDefination.getObjectId());

				data.setName(newObjectDefination.getName());

				data.setSchemaName(newObjectDefination.getSchemaName());

				data.setDataBaseName(newObjectDefination.getDataBaseName());

				data.setUserDefinedName(newObjectDefination
						.getUserDefinedName());

				data.setCamelCaseName(newObjectDefination.getCamelCaseName());

				data.setEmbaddable(newObjectDefination.getEmbaddable());

				data.setUpdated(newObjectDefination.getUpdated());

				data.setPrimaryKey(newObjectDefination.getPrimaryKey());

				if (newObjectDefination.getProject() != null)
					data.setProjectId(newObjectDefination.getProject()
							.getProjectId());

				if (newObjectDefination.getProjectVersion() != null)
					data.setProjectVersionId(newObjectDefination
							.getProjectVersion().getProjectVersionId());

				data.setCreatedBy(newObjectDefination.getCreatedBy());

				data.setCreatedDate(newObjectDefination.getCreatedDate());

				data.setUpdatedBy(newObjectDefination.getUpdatedBy());

				data.setUpdatedDate(newObjectDefination.getUpdatedDate());

			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return data;

	}

	@Override
	public List<ObjectDefinationData> getAllObjectDefination(Integer projectId,
			Integer projectVersionId) throws DataAccessException {
		List<ObjectDefinationData> objectDefinationDataList = new ArrayList<ObjectDefinationData>();
		List<ObjectDefination> objectDefinationList = null;
		try {
			objectDefinationList = repository.findAllObjectDefination(1, 1);
			if (objectDefinationList != null && objectDefinationList.size() > 0) {
				for (ObjectDefination newObjectDefination : objectDefinationList) {
					ObjectDefinationData data = new ObjectDefinationData();
					data.setObjectId(newObjectDefination.getObjectId());

					data.setName(newObjectDefination.getName());

					data.setSchemaName(newObjectDefination.getSchemaName());

					data.setDataBaseName(newObjectDefination.getDataBaseName());

					data.setUserDefinedName(newObjectDefination
							.getUserDefinedName());

					data.setCamelCaseName(newObjectDefination
							.getCamelCaseName());

					data.setEmbaddable(newObjectDefination.getEmbaddable());

					data.setUpdated(newObjectDefination.getUpdated());

					data.setPrimaryKey(newObjectDefination.getPrimaryKey());

					if (newObjectDefination.getProject() != null)
						data.setProjectId(newObjectDefination.getProject()
								.getProjectId());

					if (newObjectDefination.getProjectVersion() != null)
						data.setProjectVersionId(newObjectDefination
								.getProjectVersion().getProjectVersionId());

					data.setCreatedBy(newObjectDefination.getCreatedBy());

					data.setCreatedDate(newObjectDefination.getCreatedDate());

					data.setUpdatedBy(newObjectDefination.getUpdatedBy());

					data.setUpdatedDate(newObjectDefination.getUpdatedDate());

					objectDefinationDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return objectDefinationDataList;
	}

	@Override
	public ObjectDefinationData getObjectDefinationByName(String name,
			Integer projectId, Integer projectVersionId)
			throws DataAccessException {
		ObjectDefinationData objectDefinationData = new ObjectDefinationData();
		ObjectDefination objectDefination = null;
		try {
			objectDefination = repository.findObjectDefinationByName(name,
					projectId, projectVersionId);
			if (objectDefination != null) {

				ObjectDefinationData data = new ObjectDefinationData();
				data.setObjectId(objectDefination.getObjectId());

				data.setName(objectDefination.getName());

				data.setSchemaName(objectDefination.getSchemaName());

				data.setDataBaseName(objectDefination.getDataBaseName());

				data.setUserDefinedName(objectDefination.getUserDefinedName());

				data.setCamelCaseName(objectDefination.getCamelCaseName());

				data.setEmbaddable(objectDefination.getEmbaddable());

				data.setUpdated(objectDefination.getUpdated());

				data.setPrimaryKey(objectDefination.getPrimaryKey());

				if (objectDefination.getProject() != null)
					data.setProjectId(objectDefination.getProject()
							.getProjectId());

				if (objectDefination.getProjectVersion() != null)
					data.setProjectVersionId(objectDefination
							.getProjectVersion().getProjectVersionId());

				data.setCreatedBy(objectDefination.getCreatedBy());

				data.setCreatedDate(objectDefination.getCreatedDate());

				data.setUpdatedBy(objectDefination.getUpdatedBy());

				data.setUpdatedDate(objectDefination.getUpdatedDate());

			}

		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return objectDefinationData;
	}

	@Override
	public List<ObjectDefinationData> getObjectDefinationByEmbaddable(
			Boolean embaddable, Integer projectId, Integer projectVersionId)
			throws DataAccessException {
		List<ObjectDefinationData> objectDefinationDataList = new ArrayList<ObjectDefinationData>();
		List<ObjectDefination> objectDefinationList = null;
		try {
			objectDefinationList = repository.findObjectDefinationByEmbaddable(
					embaddable, projectId, projectVersionId);
			if (objectDefinationList != null && objectDefinationList.size() > 0) {
				for (ObjectDefination newObjectDefination : objectDefinationList) {
					ObjectDefinationData data = new ObjectDefinationData();
					data.setObjectId(newObjectDefination.getObjectId());

					data.setName(newObjectDefination.getName());

					data.setSchemaName(newObjectDefination.getSchemaName());

					data.setDataBaseName(newObjectDefination.getDataBaseName());

					data.setUserDefinedName(newObjectDefination
							.getUserDefinedName());

					data.setCamelCaseName(newObjectDefination
							.getCamelCaseName());

					data.setEmbaddable(newObjectDefination.getEmbaddable());

					data.setUpdated(newObjectDefination.getUpdated());

					data.setPrimaryKey(newObjectDefination.getPrimaryKey());

					if (newObjectDefination.getProject() != null)
						data.setProjectId(newObjectDefination.getProject()
								.getProjectId());

					if (newObjectDefination.getProjectVersion() != null)
						data.setProjectVersionId(newObjectDefination
								.getProjectVersion().getProjectVersionId());

					data.setCreatedBy(newObjectDefination.getCreatedBy());

					data.setCreatedDate(newObjectDefination.getCreatedDate());

					data.setUpdatedBy(newObjectDefination.getUpdatedBy());

					data.setUpdatedDate(newObjectDefination.getUpdatedDate());

					objectDefinationDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return objectDefinationDataList;
	}

	@Override
	@Transactional
	public Boolean saveObjectDefination(
			ObjectDefinationData objectDefinationData)
			throws DataAccessException {
		Boolean result = true;
		try {
			ObjectDefination objectDefination = new ObjectDefination();
			objectDefination.setObjectId(objectDefinationData.getObjectId());
			objectDefination.setName(objectDefinationData.getName());
			objectDefination
					.setSchemaName(objectDefinationData.getSchemaName());
			objectDefination.setDataBaseName(objectDefinationData
					.getDataBaseName());
			objectDefination.setUserDefinedName(objectDefinationData
					.getUserDefinedName());
			objectDefination.setCamelCaseName(objectDefinationData
					.getCamelCaseName());
			objectDefination
					.setEmbaddable(objectDefinationData.getEmbaddable());
			objectDefination.setUpdated(objectDefinationData.getUpdated());
			objectDefination
					.setPrimaryKey(objectDefinationData.getPrimaryKey());
			if (objectDefinationData.getProjectId() != null) {
				objectDefination.setProject(repository
						.findProjectById(objectDefinationData.getProjectId()));
			}
			if (objectDefinationData.getProjectVersionId() != null) {
				objectDefination.setProjectVersion(repository
						.findProjectVersionById(objectDefinationData
								.getProjectVersionId()));
			}
			objectDefination.setCreatedBy(objectDefinationData.getCreatedBy());
			objectDefination.setCreatedDate(objectDefinationData
					.getCreatedDate());
			objectDefination.setUpdatedBy(objectDefinationData.getUpdatedBy());
			objectDefination.setUpdatedDate(objectDefinationData
					.getUpdatedDate());
			objectDefination.setProject(repository.findProjectById(1));
			objectDefination.setProjectVersion(repository
					.findProjectVersionById(1));

			repository.saveData(objectDefination);
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;
	}

	@Override
	public Boolean updateObjectDefination(
			ObjectDefinationData objectDefinationData)
			throws DataAccessException {
		Boolean result = true;
		try {
			ObjectDefination objectDefination = null;
			if (objectDefinationData.getObjectId() != null
					&& objectDefinationData.getObjectId() > 0) {
				objectDefination = repository
						.findObjectDefinationById(objectDefinationData
								.getObjectId());
				if (objectDefination == null) {
					result = false;
					logger.error("ObjectDefination Does Not Exist");
					throw new DataAccessException(
							"ObjectDefination Does Not Exist");
				}
				objectDefination.setName(objectDefinationData.getName());
				objectDefination.setSchemaName(objectDefinationData
						.getSchemaName());
				objectDefination.setDataBaseName(objectDefinationData
						.getDataBaseName());
				objectDefination.setUserDefinedName(objectDefinationData
						.getUserDefinedName());
				objectDefination.setCamelCaseName(objectDefinationData
						.getCamelCaseName());
				objectDefination.setEmbaddable(objectDefinationData
						.getEmbaddable());
				objectDefination.setUpdated(objectDefinationData.getUpdated());
				objectDefination.setPrimaryKey(objectDefinationData
						.getPrimaryKey());
				if (objectDefinationData.getProjectId() != null) {
					objectDefination.setProject(repository
							.findProjectById(objectDefinationData
									.getProjectId()));
				}
				if (objectDefinationData.getProjectVersionId() != null) {
					objectDefination.setProjectVersion(repository
							.findProjectVersionById(objectDefinationData
									.getProjectVersionId()));
				}
				objectDefination.setCreatedBy(objectDefinationData
						.getCreatedBy());
				objectDefination.setCreatedDate(objectDefinationData
						.getCreatedDate());
				objectDefination.setUpdatedBy(objectDefinationData
						.getUpdatedBy());
				objectDefination.setUpdatedDate(objectDefinationData
						.getUpdatedDate());
				repository.updateData(objectDefination);
			}
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;
	}

	@Override
	public Boolean deleteObjectDefination(
			ObjectDefinationData objectDefinationData)
			throws DataAccessException {
		Boolean result = true;
		try {
			ObjectDefination objectDefination = null;
			if (objectDefinationData.getObjectId() != null
					&& objectDefinationData.getObjectId() > 0) {
				objectDefination = repository
						.findObjectDefinationById(objectDefinationData
								.getObjectId());
			}
			if (objectDefination == null) {
				result = false;
				logger.error("ObjectDefination Does Not Exist");
				throw new DataAccessException("ObjectDefination Does Not Exist");
			}
			repository.deleteData(objectDefination);
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;
	}

	@Override
	public Boolean deleteObjectDefinationById(Integer objectId)
			throws DataAccessException {
		Boolean result = true;
		try {
			ObjectDefination objectDefination = repository
					.findObjectDefinationById(objectId);
			if (objectDefination == null) {
				result = false;
				logger.error("ObjectDefination Does Not Exist");
				throw new DataAccessException("ObjectDefination Does Not Exist");
			}
			repository.deleteData(objectDefination);
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;

	}

}
