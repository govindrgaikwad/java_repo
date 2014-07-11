package com.wide.stringer.generator.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wide.stringer.generator.dto.AttributeData;
import com.wide.stringer.generator.entity.Attribute;
import com.wide.stringer.generator.exceptions.DataAccessException;
import com.wide.stringer.generator.exceptions.OperationResult;
import com.wide.stringer.generator.exceptions.ServiceOperationResult;
import com.wide.stringer.generator.repository.GenericRepository;

@Service("AttributeService")
public class AttributeService {

	private static Logger logger = Logger.getLogger(AttributeService.class);

	@Autowired
	private GenericRepository repository;

	@Transactional(readOnly = true)
	public List<AttributeData> getAllAttribute() throws DataAccessException {
		List<AttributeData> attributeDataList = new ArrayList<AttributeData>();
		List<Attribute> attributeList = null;
		try {
			attributeList = repository.findAllAttribute();
			if (attributeList != null && attributeList.size() > 0) {
				for (Attribute newAttribute : attributeList) {
					AttributeData data = new AttributeData();
					data.setAttributeId(newAttribute.getAttributeId());

					if (newAttribute.getObjectDefination() != null)
						data.setObjectId(newAttribute.getObjectDefination()
								.getObjectId());

					if (newAttribute.getReferenceObjectDefination() != null)
						data.setReferenceObjectId(newAttribute
								.getReferenceObjectDefination().getObjectId());

					data.setName(newAttribute.getName());

					data.setUserDefinedName(newAttribute.getUserDefinedName());

					data.setCamelCaseName(newAttribute.getCamelCaseName());

					data.setDatatype(newAttribute.getDatatype());
					
					data.setReferenceObjectName(newAttribute
							.getReferenceObjectName());

					data.setReferenceName(newAttribute.getReferenceName());

					data.setJavaDataType(newAttribute.getJavaDataType());

					data.setPrimaryKey(newAttribute.getPrimaryKey());

					data.setForeignKey(newAttribute.getForeignKey());

					data.setEmbaddable(newAttribute.getEmbaddable());

					attributeDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return attributeDataList;
	}

	@Transactional(readOnly = true)
	public AttributeData getAttributeById(Integer attributeIdId)
			throws DataAccessException {
		AttributeData data = new AttributeData();
		Attribute newAttribute = null;
		try {
			newAttribute = repository.findAttributeById(attributeIdId);
			if (newAttribute != null) {
				data.setAttributeId(newAttribute.getAttributeId());

				if (newAttribute.getObjectDefination() != null)
					data.setObjectId(newAttribute.getObjectDefination()
							.getObjectId());

				if (newAttribute.getReferenceObjectDefination() != null)
					data.setReferenceObjectId(newAttribute
							.getReferenceObjectDefination().getObjectId());

				data.setName(newAttribute.getName());

				data.setUserDefinedName(newAttribute.getUserDefinedName());

				data.setCamelCaseName(newAttribute.getCamelCaseName());
				
				data.setReferenceObjectName(newAttribute
						.getReferenceObjectName());

				data.setReferenceName(newAttribute.getReferenceName());

				data.setDatatype(newAttribute.getDatatype());

				data.setJavaDataType(newAttribute.getJavaDataType());

				data.setPrimaryKey(newAttribute.getPrimaryKey());

				data.setForeignKey(newAttribute.getForeignKey());

				data.setEmbaddable(newAttribute.getEmbaddable());

			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return data;
	}

	@Transactional
	public ServiceOperationResult deleteAttributeById(Integer attributeIdId)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			Attribute attribute = repository.findAttributeById(attributeIdId);
			if (attribute == null) {
				operationResult.setOperationResult(OperationResult.WARNING);
				messages.add("Attribute Does Not Exist");
				logger.error("Attribute Does Not Exist");
				throw new DataAccessException("Attribute Does Not Exist");
			}
			repository.deleteData(attribute);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while deleting value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

	@Transactional(readOnly = true)
	public List<AttributeData> getAttributeByObjectId(Integer objectId)
			throws DataAccessException {
		List<AttributeData> attributeDataList = new ArrayList<AttributeData>();
		List<Attribute> attributeList = null;
		try {
			attributeList = repository.findAttributeByObjectId(objectId);
			if (attributeList != null && attributeList.size() > 0) {
				for (Attribute newAttribute : attributeList) {
					AttributeData data = new AttributeData();
					data.setAttributeId(newAttribute.getAttributeId());

					if (newAttribute.getObjectDefination() != null)
						data.setObjectId(newAttribute.getObjectDefination()
								.getObjectId());

					if (newAttribute.getReferenceObjectDefination() != null)
						data.setReferenceObjectId(newAttribute
								.getReferenceObjectDefination().getObjectId());

					data.setName(newAttribute.getName());

					data.setUserDefinedName(newAttribute.getUserDefinedName());

					data.setCamelCaseName(newAttribute.getCamelCaseName());
					 
					data.setReferenceObjectName(newAttribute
							.getReferenceObjectName());

					data.setReferenceName(newAttribute.getReferenceName());

					data.setDatatype(newAttribute.getDatatype());

					data.setJavaDataType(newAttribute.getJavaDataType());

					data.setPrimaryKey(newAttribute.getPrimaryKey());

					data.setForeignKey(newAttribute.getForeignKey());

					data.setEmbaddable(newAttribute.getEmbaddable());

					attributeDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return attributeDataList;
	}

	@Transactional(readOnly = true)
	public List<AttributeData> getAttributeByReferenceObjectId(
			Integer referenceObjectId) throws DataAccessException {
		List<AttributeData> attributeDataList = new ArrayList<AttributeData>();
		List<Attribute> attributeList = null;
		try {
			attributeList = repository
					.findAttributeByReferenceObjectId(referenceObjectId);
			if (attributeList != null && attributeList.size() > 0) {
				for (Attribute newAttribute : attributeList) {
					AttributeData data = new AttributeData();
					data.setAttributeId(newAttribute.getAttributeId());

					if (newAttribute.getObjectDefination() != null)
						data.setObjectId(newAttribute.getObjectDefination()
								.getObjectId());

					if (newAttribute.getReferenceObjectDefination() != null)
						data.setReferenceObjectId(newAttribute
								.getReferenceObjectDefination().getObjectId());

					data.setName(newAttribute.getName());

					data.setUserDefinedName(newAttribute.getUserDefinedName());

					data.setCamelCaseName(newAttribute.getCamelCaseName());

					data.setDatatype(newAttribute.getDatatype());

					data.setJavaDataType(newAttribute.getJavaDataType());

					data.setPrimaryKey(newAttribute.getPrimaryKey());

					data.setForeignKey(newAttribute.getForeignKey());

					data.setEmbaddable(newAttribute.getEmbaddable());

					attributeDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return attributeDataList;
	}

	@Transactional(readOnly = true)
	public List<AttributeData> getAttributeByName(String name)
			throws DataAccessException {
		List<AttributeData> attributeDataList = new ArrayList<AttributeData>();
		List<Attribute> attributeList = null;
		try {
			attributeList = repository.findAttributeByName(name);
			if (attributeList != null && attributeList.size() > 0) {
				for (Attribute newAttribute : attributeList) {
					AttributeData data = new AttributeData();
					data.setAttributeId(newAttribute.getAttributeId());

					if (newAttribute.getObjectDefination() != null)
						data.setObjectId(newAttribute.getObjectDefination()
								.getObjectId());

					if (newAttribute.getReferenceObjectDefination() != null)
						data.setReferenceObjectId(newAttribute
								.getReferenceObjectDefination().getObjectId());

					data.setName(newAttribute.getName());

					data.setUserDefinedName(newAttribute.getUserDefinedName());

					data.setCamelCaseName(newAttribute.getCamelCaseName());

					data.setDatatype(newAttribute.getDatatype());

					data.setJavaDataType(newAttribute.getJavaDataType());

					data.setPrimaryKey(newAttribute.getPrimaryKey());

					data.setForeignKey(newAttribute.getForeignKey());

					data.setEmbaddable(newAttribute.getEmbaddable());

					attributeDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return attributeDataList;
	}

	@Transactional(readOnly = true)
	public List<AttributeData> getAttributeByUserDefinedName(
			String userDefinedName) throws DataAccessException {
		List<AttributeData> attributeDataList = new ArrayList<AttributeData>();
		List<Attribute> attributeList = null;
		try {
			attributeList = repository
					.findAttributeByUserDefinedName(userDefinedName);
			if (attributeList != null && attributeList.size() > 0) {
				for (Attribute newAttribute : attributeList) {
					AttributeData data = new AttributeData();
					data.setAttributeId(newAttribute.getAttributeId());

					if (newAttribute.getObjectDefination() != null)
						data.setObjectId(newAttribute.getObjectDefination()
								.getObjectId());

					if (newAttribute.getReferenceObjectDefination() != null)
						data.setReferenceObjectId(newAttribute
								.getReferenceObjectDefination().getObjectId());

					data.setName(newAttribute.getName());

					data.setUserDefinedName(newAttribute.getUserDefinedName());

					data.setCamelCaseName(newAttribute.getCamelCaseName());

					data.setDatatype(newAttribute.getDatatype());

					data.setJavaDataType(newAttribute.getJavaDataType());

					data.setPrimaryKey(newAttribute.getPrimaryKey());

					data.setForeignKey(newAttribute.getForeignKey());

					data.setEmbaddable(newAttribute.getEmbaddable());

					attributeDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return attributeDataList;
	}

	@Transactional(readOnly = true)
	public List<AttributeData> getAttributeByCamelCaseName(String camelCaseName)
			throws DataAccessException {
		List<AttributeData> attributeDataList = new ArrayList<AttributeData>();
		List<Attribute> attributeList = null;
		try {
			attributeList = repository
					.findAttributeByCamelCaseName(camelCaseName);
			if (attributeList != null && attributeList.size() > 0) {
				for (Attribute newAttribute : attributeList) {
					AttributeData data = new AttributeData();
					data.setAttributeId(newAttribute.getAttributeId());

					if (newAttribute.getObjectDefination() != null)
						data.setObjectId(newAttribute.getObjectDefination()
								.getObjectId());

					if (newAttribute.getReferenceObjectDefination() != null)
						data.setReferenceObjectId(newAttribute
								.getReferenceObjectDefination().getObjectId());

					data.setName(newAttribute.getName());

					data.setUserDefinedName(newAttribute.getUserDefinedName());

					data.setCamelCaseName(newAttribute.getCamelCaseName());

					data.setDatatype(newAttribute.getDatatype());

					data.setJavaDataType(newAttribute.getJavaDataType());

					data.setPrimaryKey(newAttribute.getPrimaryKey());

					data.setForeignKey(newAttribute.getForeignKey());

					data.setEmbaddable(newAttribute.getEmbaddable());

					attributeDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return attributeDataList;
	}

	@Transactional(readOnly = true)
	public List<AttributeData> getAttributeByDatatype(String datatype)
			throws DataAccessException {
		List<AttributeData> attributeDataList = new ArrayList<AttributeData>();
		List<Attribute> attributeList = null;
		try {
			attributeList = repository.findAttributeByDatatype(datatype);
			if (attributeList != null && attributeList.size() > 0) {
				for (Attribute newAttribute : attributeList) {
					AttributeData data = new AttributeData();
					data.setAttributeId(newAttribute.getAttributeId());

					if (newAttribute.getObjectDefination() != null)
						data.setObjectId(newAttribute.getObjectDefination()
								.getObjectId());

					if (newAttribute.getReferenceObjectDefination() != null)
						data.setReferenceObjectId(newAttribute
								.getReferenceObjectDefination().getObjectId());

					data.setName(newAttribute.getName());

					data.setUserDefinedName(newAttribute.getUserDefinedName());

					data.setCamelCaseName(newAttribute.getCamelCaseName());

					data.setDatatype(newAttribute.getDatatype());

					data.setJavaDataType(newAttribute.getJavaDataType());

					data.setPrimaryKey(newAttribute.getPrimaryKey());

					data.setForeignKey(newAttribute.getForeignKey());

					data.setEmbaddable(newAttribute.getEmbaddable());

					attributeDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return attributeDataList;
	}

	@Transactional(readOnly = true)
	public List<AttributeData> getAttributeByJavaDataType(String javaDataType)
			throws DataAccessException {
		List<AttributeData> attributeDataList = new ArrayList<AttributeData>();
		List<Attribute> attributeList = null;
		try {
			attributeList = repository
					.findAttributeByJavaDataType(javaDataType);
			if (attributeList != null && attributeList.size() > 0) {
				for (Attribute newAttribute : attributeList) {
					AttributeData data = new AttributeData();
					data.setAttributeId(newAttribute.getAttributeId());

					if (newAttribute.getObjectDefination() != null)
						data.setObjectId(newAttribute.getObjectDefination()
								.getObjectId());

					if (newAttribute.getReferenceObjectDefination() != null)
						data.setReferenceObjectId(newAttribute
								.getReferenceObjectDefination().getObjectId());

					data.setName(newAttribute.getName());

					data.setUserDefinedName(newAttribute.getUserDefinedName());

					data.setCamelCaseName(newAttribute.getCamelCaseName());

					data.setDatatype(newAttribute.getDatatype());

					data.setJavaDataType(newAttribute.getJavaDataType());

					data.setPrimaryKey(newAttribute.getPrimaryKey());

					data.setForeignKey(newAttribute.getForeignKey());

					data.setEmbaddable(newAttribute.getEmbaddable());

					attributeDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return attributeDataList;
	}

	@Transactional(readOnly = true)
	public List<AttributeData> getAttributeByPrimaryKey(Boolean primaryKey)
			throws DataAccessException {
		List<AttributeData> attributeDataList = new ArrayList<AttributeData>();
		List<Attribute> attributeList = null;
		try {
			attributeList = repository.findAttributeByPrimaryKey(primaryKey);
			if (attributeList != null && attributeList.size() > 0) {
				for (Attribute newAttribute : attributeList) {
					AttributeData data = new AttributeData();
					data.setAttributeId(newAttribute.getAttributeId());

					if (newAttribute.getObjectDefination() != null)
						data.setObjectId(newAttribute.getObjectDefination()
								.getObjectId());

					if (newAttribute.getReferenceObjectDefination() != null)
						data.setReferenceObjectId(newAttribute
								.getReferenceObjectDefination().getObjectId());

					data.setName(newAttribute.getName());

					data.setUserDefinedName(newAttribute.getUserDefinedName());

					data.setCamelCaseName(newAttribute.getCamelCaseName());

					data.setDatatype(newAttribute.getDatatype());

					data.setJavaDataType(newAttribute.getJavaDataType());

					data.setPrimaryKey(newAttribute.getPrimaryKey());

					data.setForeignKey(newAttribute.getForeignKey());

					data.setEmbaddable(newAttribute.getEmbaddable());

					attributeDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return attributeDataList;
	}

	@Transactional(readOnly = true)
	public List<AttributeData> getAttributeByForeignKey(Boolean foreignKey)
			throws DataAccessException {
		List<AttributeData> attributeDataList = new ArrayList<AttributeData>();
		List<Attribute> attributeList = null;
		try {
			attributeList = repository.findAttributeByForeignKey(foreignKey);
			if (attributeList != null && attributeList.size() > 0) {
				for (Attribute newAttribute : attributeList) {
					AttributeData data = new AttributeData();
					data.setAttributeId(newAttribute.getAttributeId());

					if (newAttribute.getObjectDefination() != null)
						data.setObjectId(newAttribute.getObjectDefination()
								.getObjectId());

					if (newAttribute.getReferenceObjectDefination() != null)
						data.setReferenceObjectId(newAttribute
								.getReferenceObjectDefination().getObjectId());

					data.setName(newAttribute.getName());

					data.setUserDefinedName(newAttribute.getUserDefinedName());

					data.setCamelCaseName(newAttribute.getCamelCaseName());

					data.setDatatype(newAttribute.getDatatype());

					data.setJavaDataType(newAttribute.getJavaDataType());

					data.setPrimaryKey(newAttribute.getPrimaryKey());

					data.setForeignKey(newAttribute.getForeignKey());

					data.setEmbaddable(newAttribute.getEmbaddable());

					attributeDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return attributeDataList;
	}

	@Transactional(readOnly = true)
	public List<AttributeData> getAttributeByEmbaddable(Boolean embaddable)
			throws DataAccessException {
		List<AttributeData> attributeDataList = new ArrayList<AttributeData>();
		List<Attribute> attributeList = null;
		try {
			attributeList = repository.findAttributeByEmbaddable(embaddable);
			if (attributeList != null && attributeList.size() > 0) {
				for (Attribute newAttribute : attributeList) {
					AttributeData data = new AttributeData();
					data.setAttributeId(newAttribute.getAttributeId());

					if (newAttribute.getObjectDefination() != null)
						data.setObjectId(newAttribute.getObjectDefination()
								.getObjectId());

					if (newAttribute.getReferenceObjectDefination() != null)
						data.setReferenceObjectId(newAttribute
								.getReferenceObjectDefination().getObjectId());

					data.setName(newAttribute.getName());

					data.setUserDefinedName(newAttribute.getUserDefinedName());

					data.setCamelCaseName(newAttribute.getCamelCaseName());

					data.setDatatype(newAttribute.getDatatype());

					data.setJavaDataType(newAttribute.getJavaDataType());

					data.setPrimaryKey(newAttribute.getPrimaryKey());

					data.setForeignKey(newAttribute.getForeignKey());

					data.setEmbaddable(newAttribute.getEmbaddable());

					attributeDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return attributeDataList;
	}

	@Transactional(readOnly = true)
	public AttributeData getAttributeByNameAndObjectId(String name,
			Integer objectId) throws DataAccessException {
		AttributeData data = new AttributeData();
		Attribute newAttribute = null;
		try {
			newAttribute = repository.findAttributeByNameAndObjectId(name,
					objectId);
			if (newAttribute != null) {
				data.setAttributeId(newAttribute.getAttributeId());

				if (newAttribute.getObjectDefination() != null)
					data.setObjectId(newAttribute.getObjectDefination()
							.getObjectId());

				if (newAttribute.getReferenceObjectDefination() != null)
					data.setReferenceObjectId(newAttribute
							.getReferenceObjectDefination().getObjectId());

				data.setName(newAttribute.getName());

				data.setUserDefinedName(newAttribute.getUserDefinedName());

				data.setCamelCaseName(newAttribute.getCamelCaseName());

				data.setReferenceObjectName(newAttribute
						.getReferenceObjectName());

				data.setReferenceName(newAttribute.getReferenceName());

				data.setDatatype(newAttribute.getDatatype());

				data.setJavaDataType(newAttribute.getJavaDataType());

				data.setPrimaryKey(newAttribute.getPrimaryKey());

				data.setForeignKey(newAttribute.getForeignKey());

				data.setEmbaddable(newAttribute.getEmbaddable());

			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return data;
	}

	@Transactional
	public ServiceOperationResult saveAttribute(AttributeData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			Attribute attribute = new Attribute();
			attribute.setAttributeId(data.getAttributeId());
			if (data.getObjectId() != null) {
				attribute.setObjectDefination(repository
						.findObjectDefinationById(data.getObjectId()));
			}
			if (data.getReferenceObjectId() != null) {
				attribute.setReferenceObjectDefination(repository
						.findObjectDefinationById(data.getReferenceObjectId()));
			}
			attribute.setName(data.getName());
			attribute.setUserDefinedName(data.getUserDefinedName());
			attribute.setCamelCaseName(data.getCamelCaseName());
			attribute.setReferenceObjectName(data.getReferenceObjectName());
			attribute.setReferenceName(data.getReferenceName());
			attribute.setDatatype(data.getDatatype());
			attribute.setJavaDataType(data.getJavaDataType());
			attribute.setPrimaryKey(data.getPrimaryKey());
			attribute.setForeignKey(data.getForeignKey());
			attribute.setEmbaddable(data.getEmbaddable());

			repository.saveData(attribute);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while Saving Value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

	@Transactional
	public ServiceOperationResult updateAttribute(AttributeData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			Attribute attribute = null;
			if (data.getAttributeId() != null && data.getAttributeId() > 0) {
				attribute = repository.findAttributeById(data.getAttributeId());
				if (attribute == null) {
					operationResult.setOperationResult(OperationResult.WARNING);
					messages.add("Attribute Does Not Exist");
					logger.error("Attribute Does Not Exist");
					throw new DataAccessException("Attribute Does Not Exist");
				}
				if (data.getObjectId() != null) {
					attribute.setObjectDefination(repository
							.findObjectDefinationById(data.getObjectId()));
				}
				if (data.getReferenceObjectId() != null) {
					attribute.setReferenceObjectDefination(repository
							.findObjectDefinationById(data
									.getReferenceObjectId()));
				}
				attribute.setName(data.getName());
				attribute.setUserDefinedName(data.getUserDefinedName());
				attribute.setCamelCaseName(data.getCamelCaseName());
				attribute.setDatatype(data.getDatatype());
				attribute.setJavaDataType(data.getJavaDataType());
				attribute.setPrimaryKey(data.getPrimaryKey());
				attribute.setForeignKey(data.getForeignKey());
				attribute.setEmbaddable(data.getEmbaddable());
				repository.updateData(attribute);
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
	public ServiceOperationResult deleteAttribute(AttributeData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			Attribute attribute = null;
			if (data.getAttributeId() != null && data.getAttributeId() > 0) {
				attribute = repository.findAttributeById(data.getAttributeId());
			}
			if (attribute == null) {
				operationResult.setOperationResult(OperationResult.WARNING);
				messages.add("Attribute Does Not Exist");
				logger.error("Attribute Does Not Exist");
				throw new DataAccessException("Attribute Does Not Exist");
			}
			repository.deleteData(attribute);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while Deleting Value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

}