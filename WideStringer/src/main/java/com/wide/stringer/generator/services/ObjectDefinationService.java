package com.wide.stringer.generator.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wide.stringer.generator.dto.ObjectDefinationData;
import com.wide.stringer.generator.entity.ObjectDefination;
import com.wide.stringer.generator.exceptions.DataAccessException;
import com.wide.stringer.generator.exceptions.OperationResult;
import com.wide.stringer.generator.exceptions.ServiceOperationResult;
import com.wide.stringer.generator.repository.GenericRepository;

@Service("ObjectDefinationService")
public class ObjectDefinationService {

	private static Logger logger = Logger
			.getLogger(ObjectDefinationService.class);

	@Autowired
	private GenericRepository repository;

	@Transactional(readOnly = true)
	public List<ObjectDefinationData> getAllObjectDefination()
			throws DataAccessException {
		List<ObjectDefinationData> objectDefinationDataList = new ArrayList<ObjectDefinationData>();
		List<ObjectDefination> objectDefinationList = null;
		try {
			objectDefinationList = repository.findAllObjectDefination();
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

					objectDefinationDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return objectDefinationDataList;
	}

	@Transactional(readOnly = true)
	public ObjectDefinationData getObjectDefinationById(Integer objectIdId)
			throws DataAccessException {
		ObjectDefinationData data = new ObjectDefinationData();
		ObjectDefination newObjectDefination = null;
		try {
			newObjectDefination = repository
					.findObjectDefinationById(objectIdId);
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

			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return data;
	}

	@Transactional
	public ServiceOperationResult deleteObjectDefinationById(Integer objectIdId)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			ObjectDefination objectDefination = repository
					.findObjectDefinationById(objectIdId);
			if (objectDefination == null) {
				operationResult.setOperationResult(OperationResult.WARNING);
				messages.add("ObjectDefination Does Not Exist");
				logger.error("ObjectDefination Does Not Exist");
				throw new DataAccessException("ObjectDefination Does Not Exist");
			}
			repository.deleteData(objectDefination);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while deleting value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

	@Transactional(readOnly = true)
	public ObjectDefinationData getObjectDefinationByName(String name)
			throws DataAccessException {
		ObjectDefination newObjectDefination = null;
		ObjectDefinationData data = null;
		try {
			newObjectDefination = repository.findObjectDefinationByName(name);

			data = new ObjectDefinationData();
			
			data.setObjectId(newObjectDefination.getObjectId());

			data.setName(newObjectDefination.getName());

			data.setSchemaName(newObjectDefination.getSchemaName());

			data.setDataBaseName(newObjectDefination.getDataBaseName());

			data.setUserDefinedName(newObjectDefination.getUserDefinedName());

			data.setCamelCaseName(newObjectDefination.getCamelCaseName());

			data.setEmbaddable(newObjectDefination.getEmbaddable());

			data.setUpdated(newObjectDefination.getUpdated());

			data.setPrimaryKey(newObjectDefination.getPrimaryKey());

		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return data;
	}

	@Transactional(readOnly = true)
	public List<ObjectDefinationData> getObjectDefinationBySchemaName(
			String schemaName) throws DataAccessException {
		List<ObjectDefinationData> objectDefinationDataList = new ArrayList<ObjectDefinationData>();
		List<ObjectDefination> objectDefinationList = null;
		try {
			objectDefinationList = repository
					.findObjectDefinationBySchemaName(schemaName);
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

					objectDefinationDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return objectDefinationDataList;
	}

	@Transactional(readOnly = true)
	public List<ObjectDefinationData> getObjectDefinationByDataBaseName(
			String dataBaseName) throws DataAccessException {
		List<ObjectDefinationData> objectDefinationDataList = new ArrayList<ObjectDefinationData>();
		List<ObjectDefination> objectDefinationList = null;
		try {
			objectDefinationList = repository
					.findObjectDefinationByDataBaseName(dataBaseName);
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

					objectDefinationDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return objectDefinationDataList;
	}

	@Transactional(readOnly = true)
	public List<ObjectDefinationData> getObjectDefinationByUserDefinedName(
			String userDefinedName) throws DataAccessException {
		List<ObjectDefinationData> objectDefinationDataList = new ArrayList<ObjectDefinationData>();
		List<ObjectDefination> objectDefinationList = null;
		try {
			objectDefinationList = repository
					.findObjectDefinationByUserDefinedName(userDefinedName);
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

					objectDefinationDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return objectDefinationDataList;
	}

	@Transactional(readOnly = true)
	public List<ObjectDefinationData> getObjectDefinationByCamelCaseName(
			String camelCaseName) throws DataAccessException {
		List<ObjectDefinationData> objectDefinationDataList = new ArrayList<ObjectDefinationData>();
		List<ObjectDefination> objectDefinationList = null;
		try {
			objectDefinationList = repository
					.findObjectDefinationByCamelCaseName(camelCaseName);
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

					objectDefinationDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return objectDefinationDataList;
	}

	@Transactional(readOnly = true)
	public List<ObjectDefinationData> getObjectDefinationByEmbaddable(
			Boolean embaddable) throws DataAccessException {
		List<ObjectDefinationData> objectDefinationDataList = new ArrayList<ObjectDefinationData>();
		List<ObjectDefination> objectDefinationList = null;
		try {
			objectDefinationList = repository
					.findObjectDefinationByEmbaddable(embaddable);
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

					objectDefinationDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return objectDefinationDataList;
	}

	@Transactional(readOnly = true)
	public List<ObjectDefinationData> getObjectDefinationByUpdated(
			Boolean updated) throws DataAccessException {
		List<ObjectDefinationData> objectDefinationDataList = new ArrayList<ObjectDefinationData>();
		List<ObjectDefination> objectDefinationList = null;
		try {
			objectDefinationList = repository
					.findObjectDefinationByUpdated(updated);
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

					objectDefinationDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return objectDefinationDataList;
	}

	@Transactional(readOnly = true)
	public List<ObjectDefinationData> getObjectDefinationByprimaryKey(
			Boolean primaryKey) throws DataAccessException {
		List<ObjectDefinationData> objectDefinationDataList = new ArrayList<ObjectDefinationData>();
		List<ObjectDefination> objectDefinationList = null;
		try {
			objectDefinationList = repository
					.findObjectDefinationByprimaryKey(primaryKey);
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

					objectDefinationDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return objectDefinationDataList;
	}

	@Transactional
	public ServiceOperationResult saveObjectDefination(ObjectDefinationData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			ObjectDefination objectDefination = new ObjectDefination();
			objectDefination.setObjectId(data.getObjectId());
			objectDefination.setName(data.getName());
			objectDefination.setSchemaName(data.getSchemaName());
			objectDefination.setDataBaseName(data.getDataBaseName());
			objectDefination.setUserDefinedName(data.getUserDefinedName());
			objectDefination.setCamelCaseName(data.getCamelCaseName());
			objectDefination.setEmbaddable(data.getEmbaddable());
			objectDefination.setUpdated(data.getUpdated());
			objectDefination.setPrimaryKey(data.getPrimaryKey());

			repository.saveData(objectDefination);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while Saving Value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

	@Transactional
	public ServiceOperationResult updateObjectDefination(
			ObjectDefinationData data) throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			ObjectDefination objectDefination = null;
			if (data.getObjectId() != null && data.getObjectId() > 0) {
				objectDefination = repository.findObjectDefinationById(data
						.getObjectId());
				if (objectDefination == null) {
					operationResult.setOperationResult(OperationResult.WARNING);
					messages.add("ObjectDefination Does Not Exist");
					logger.error("ObjectDefination Does Not Exist");
					throw new DataAccessException(
							"ObjectDefination Does Not Exist");
				}
				objectDefination.setName(data.getName());
				objectDefination.setSchemaName(data.getSchemaName());
				objectDefination.setDataBaseName(data.getDataBaseName());
				objectDefination.setUserDefinedName(data.getUserDefinedName());
				objectDefination.setCamelCaseName(data.getCamelCaseName());
				objectDefination.setEmbaddable(data.getEmbaddable());
				objectDefination.setUpdated(data.getUpdated());
				objectDefination.setPrimaryKey(data.getPrimaryKey());
				repository.updateData(objectDefination);
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
	public ServiceOperationResult deleteObjectDefination(
			ObjectDefinationData data) throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			ObjectDefination objectDefination = null;
			if (data.getObjectId() != null && data.getObjectId() > 0) {
				objectDefination = repository.findObjectDefinationById(data
						.getObjectId());
			}
			if (objectDefination == null) {
				operationResult.setOperationResult(OperationResult.WARNING);
				messages.add("ObjectDefination Does Not Exist");
				logger.error("ObjectDefination Does Not Exist");
				throw new DataAccessException("ObjectDefination Does Not Exist");
			}
			repository.deleteData(objectDefination);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while Deleting Value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

}