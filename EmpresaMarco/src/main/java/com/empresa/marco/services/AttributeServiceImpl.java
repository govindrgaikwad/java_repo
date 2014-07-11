package com.empresa.marco.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.marco.data.AttributeData;
import com.empresa.marco.entity.Attribute;
import com.empresa.marco.exceptions.DataAccessException;
import com.empresa.marco.repository.GenericRepository;

@Service("AttributeService")
public class AttributeServiceImpl implements AttributeService {

	private static Logger logger = Logger.getLogger(AttributeService.class);

	@Autowired
	GenericRepository repository;

	@Override
	@Transactional(readOnly = true)
	public AttributeData getAttributeById(Integer attributeId)
			throws DataAccessException {

		AttributeData data = new AttributeData();
		Attribute newAttribute = null;
		try {
			newAttribute = repository.findAttributeById(attributeId);
			if (newAttribute != null) {
				data.setAttributeId(newAttribute.getAttributeId());

				if (newAttribute.getObjectDefination() != null)
					data.setObjectId(newAttribute.getObjectDefination()
							.getObjectId());

				if (newAttribute.getObjectDefination1() != null)
					data.setReferenceObjectId(newAttribute
							.getObjectDefination1().getObjectId());

				data.setName(newAttribute.getName());

				data.setUserDefinedName(newAttribute.getUserDefinedName());

				data.setCamelCaseName(newAttribute.getCamelCaseName());

				data.setReferenceName(newAttribute.getReferenceName());

				data.setDatatype(newAttribute.getDatatype());

				data.setJavaDataType(newAttribute.getJavaDataType());

				data.setPrimaryKey(newAttribute.getPrimaryKey());

				data.setForeignKey(newAttribute.getForeignKey());

				data.setEmbaddable(newAttribute.getEmbaddable());

				if (newAttribute.getProject() != null)
					data.setProjectId(newAttribute.getProject().getProjectId());

				if (newAttribute.getProjectVersion() != null)
					data.setProjectVersionId(newAttribute.getProjectVersion()
							.getProjectVersionId());

				data.setCreatedBy(newAttribute.getCreatedBy());

				data.setCreatedDate(newAttribute.getCreatedDate());

				data.setUpdatedBy(newAttribute.getUpdatedBy());

				data.setUpdatedDate(newAttribute.getUpdatedDate());

			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return data;

	}

	@Override
	@Transactional(readOnly = true)
	public List<AttributeData> getAttributeByProjectVersion(Integer projectId,
			Integer projectVersionId) throws DataAccessException {

		List<AttributeData> attributeDataList = new ArrayList<AttributeData>();
		List<Attribute> attributeList = null;
		try {
			attributeList = repository.findAttributeByProjectVersion(projectId,
					projectVersionId);
			if (attributeList != null && attributeList.size() > 0) {
				for (Attribute newAttribute : attributeList) {
					AttributeData data = new AttributeData();
					data.setAttributeId(newAttribute.getAttributeId());

					if (newAttribute.getObjectDefination() != null)
						data.setObjectId(newAttribute.getObjectDefination()
								.getObjectId());

					if (newAttribute.getObjectDefination1() != null)
						data.setReferenceObjectId(newAttribute
								.getObjectDefination1().getObjectId());

					data.setName(newAttribute.getName());

					data.setUserDefinedName(newAttribute.getUserDefinedName());

					data.setCamelCaseName(newAttribute.getCamelCaseName());

					data.setReferenceName(newAttribute.getReferenceName());

					data.setDatatype(newAttribute.getDatatype());

					data.setJavaDataType(newAttribute.getJavaDataType());

					data.setPrimaryKey(newAttribute.getPrimaryKey());

					data.setForeignKey(newAttribute.getForeignKey());

					data.setEmbaddable(newAttribute.getEmbaddable());

					if (newAttribute.getProject() != null)
						data.setProjectId(newAttribute.getProject()
								.getProjectId());

					if (newAttribute.getProjectVersion() != null)
						data.setProjectVersionId(newAttribute
								.getProjectVersion().getProjectVersionId());

					data.setCreatedBy(newAttribute.getCreatedBy());

					data.setCreatedDate(newAttribute.getCreatedDate());

					data.setUpdatedBy(newAttribute.getUpdatedBy());

					data.setUpdatedDate(newAttribute.getUpdatedDate());

					attributeDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return attributeDataList;

	}

	@Override
	@Transactional(readOnly = true)
	public List<AttributeData> getAttributeByOjectId(Integer objectId,
			Integer projectId, Integer ProjectVersionId)
			throws DataAccessException {

		List<AttributeData> attributeDataList = new ArrayList<AttributeData>();
		List<Attribute> attributeList = null;
		try {
			attributeList = repository.findAttributeByObjectId(objectId,
					projectId, ProjectVersionId);
			if (attributeList != null && attributeList.size() > 0) {
				for (Attribute newAttribute : attributeList) {
					AttributeData data = new AttributeData();
					data.setAttributeId(newAttribute.getAttributeId());

					if (newAttribute.getObjectDefination() != null)
						data.setObjectId(newAttribute.getObjectDefination()
								.getObjectId());

					if (newAttribute.getObjectDefination1() != null)
						data.setReferenceObjectId(newAttribute
								.getObjectDefination1().getObjectId());

					data.setName(newAttribute.getName());

					data.setUserDefinedName(newAttribute.getUserDefinedName());

					data.setCamelCaseName(newAttribute.getCamelCaseName());

					data.setReferenceName(newAttribute.getReferenceName());

					data.setDatatype(newAttribute.getDatatype());

					data.setJavaDataType(newAttribute.getJavaDataType());

					data.setPrimaryKey(newAttribute.getPrimaryKey());

					data.setForeignKey(newAttribute.getForeignKey());

					data.setEmbaddable(newAttribute.getEmbaddable());

					if (newAttribute.getProject() != null)
						data.setProjectId(newAttribute.getProject()
								.getProjectId());

					if (newAttribute.getProjectVersion() != null)
						data.setProjectVersionId(newAttribute
								.getProjectVersion().getProjectVersionId());

					data.setCreatedBy(newAttribute.getCreatedBy());

					data.setCreatedDate(newAttribute.getCreatedDate());

					data.setUpdatedBy(newAttribute.getUpdatedBy());

					data.setUpdatedDate(newAttribute.getUpdatedDate());

					attributeDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return attributeDataList;

	}

	@Override
	@Transactional(readOnly = true)
	public AttributeData getAttributeByNameAndObjectId(String attributeName,
			Integer objectId, Integer projectId, Integer projectVersionId)
			throws DataAccessException {

		AttributeData data = new AttributeData();
		Attribute newAttribute = null;
		try {
			newAttribute = repository.findAttributeByNameAndObjectId(
					attributeName, objectId, projectId, projectVersionId);
			if (newAttribute != null) {
				data.setAttributeId(newAttribute.getAttributeId());

				if (newAttribute.getObjectDefination() != null)
					data.setObjectId(newAttribute.getObjectDefination()
							.getObjectId());

				if (newAttribute.getObjectDefination1() != null)
					data.setReferenceObjectId(newAttribute
							.getObjectDefination1().getObjectId());

				data.setName(newAttribute.getName());

				data.setUserDefinedName(newAttribute.getUserDefinedName());

				data.setCamelCaseName(newAttribute.getCamelCaseName());

				data.setReferenceName(newAttribute.getReferenceName());

				data.setDatatype(newAttribute.getDatatype());

				data.setJavaDataType(newAttribute.getJavaDataType());

				data.setPrimaryKey(newAttribute.getPrimaryKey());

				data.setForeignKey(newAttribute.getForeignKey());

				data.setEmbaddable(newAttribute.getEmbaddable());

				if (newAttribute.getProject() != null)
					data.setProjectId(newAttribute.getProject().getProjectId());

				if (newAttribute.getProjectVersion() != null)
					data.setProjectVersionId(newAttribute.getProjectVersion()
							.getProjectVersionId());

				data.setCreatedBy(newAttribute.getCreatedBy());

				data.setCreatedDate(newAttribute.getCreatedDate());

				data.setUpdatedBy(newAttribute.getUpdatedBy());

				data.setUpdatedDate(newAttribute.getUpdatedDate());

			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return data;

	}

	@Override
	@Transactional
	public Boolean saveAttribute(AttributeData attributeData)
			throws DataAccessException {
		Boolean result = true;
		try {
			Attribute attribute = new Attribute();
			attribute.setAttributeId(attributeData.getAttributeId());
			if (attributeData.getObjectId() != null) {
				attribute.setObjectDefination(repository
						.findObjectDefinationById(attributeData.getObjectId()));
			}
			if (attributeData.getReferenceObjectId() != null) {
				attribute.setObjectDefination1(repository
						.findObjectDefinationById(attributeData
								.getReferenceObjectId()));
			}
			attribute.setName(attributeData.getName());
			attribute.setUserDefinedName(attributeData.getUserDefinedName());
			attribute.setCamelCaseName(attributeData.getCamelCaseName());
			attribute.setReferenceName(attributeData.getReferenceName());
			attribute.setDatatype(attributeData.getDatatype());
			attribute.setJavaDataType(attributeData.getJavaDataType());
			attribute.setPrimaryKey(attributeData.getPrimaryKey());
			attribute.setForeignKey(attributeData.getForeignKey());
			attribute.setEmbaddable(attributeData.getEmbaddable());
			if (attributeData.getProjectId() != null) {
				attribute.setProject(repository.findProjectById(attributeData
						.getProjectId()));
			}
			if (attributeData.getProjectVersionId() != null) {
				attribute.setProjectVersion(repository
						.findProjectVersionById(attributeData
								.getProjectVersionId()));
			}
			attribute.setCreatedBy(attributeData.getCreatedBy());
			attribute.setCreatedDate(attributeData.getCreatedDate());
			attribute.setUpdatedBy(attributeData.getUpdatedBy());
			attribute.setUpdatedDate(attributeData.getUpdatedDate());

			repository.saveData(attribute);
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;

	}

	@Override
	@Transactional
	public Boolean updateAttribute(AttributeData attributeData)
			throws DataAccessException {

		Boolean result = true;
		try {
			Attribute attribute = null;
			if (attributeData.getAttributeId() != null
					&& attributeData.getAttributeId() > 0) {
				attribute = repository.findAttributeById(attributeData
						.getAttributeId());
				if (attribute == null) {
					result = false;
					logger.error("Attribute Does Not Exist");
					throw new DataAccessException("Attribute Does Not Exist");
				}
				if (attributeData.getObjectId() != null) {
					attribute.setObjectDefination(repository
							.findObjectDefinationById(attributeData
									.getObjectId()));
				}
				if (attributeData.getReferenceObjectId() != null) {
					attribute.setObjectDefination1(repository
							.findObjectDefinationById(attributeData
									.getReferenceObjectId()));
				}
				attribute.setName(attributeData.getName());
				attribute
						.setUserDefinedName(attributeData.getUserDefinedName());
				attribute.setCamelCaseName(attributeData.getCamelCaseName());
				attribute.setReferenceName(attributeData.getReferenceName());
				attribute.setDatatype(attributeData.getDatatype());
				attribute.setJavaDataType(attributeData.getJavaDataType());
				attribute.setPrimaryKey(attributeData.getPrimaryKey());
				attribute.setForeignKey(attributeData.getForeignKey());
				attribute.setEmbaddable(attributeData.getEmbaddable());
				if (attributeData.getProjectId() != null) {
					attribute.setProject(repository
							.findProjectById(attributeData.getProjectId()));
				}
				if (attributeData.getProjectVersionId() != null) {
					attribute.setProjectVersion(repository
							.findProjectVersionById(attributeData
									.getProjectVersionId()));
				}
				attribute.setCreatedBy(attributeData.getCreatedBy());
				attribute.setCreatedDate(attributeData.getCreatedDate());
				attribute.setUpdatedBy(attributeData.getUpdatedBy());
				attribute.setUpdatedDate(attributeData.getUpdatedDate());
				repository.updateData(attribute);
			}
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;

	}

	@Override
	@Transactional
	public Boolean deleteAttribute(AttributeData attributeData)
			throws DataAccessException {

		Boolean result = true;
		try {
			Attribute attribute = null;
			if (attributeData.getAttributeId() != null
					&& attributeData.getAttributeId() > 0) {
				attribute = repository.findAttributeById(attributeData
						.getAttributeId());
			}
			if (attribute == null) {
				result = false;
				logger.error("Attribute Does Not Exist");
				throw new DataAccessException("Attribute Does Not Exist");
			}
			repository.deleteData(attribute);
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;

	}

	@Override
	@Transactional
	public Boolean deleteAttributeById(Integer attributeId)
			throws DataAccessException {
		Boolean result = true;
		try {
			Attribute attribute = repository.findAttributeById(attributeId);
			if (attribute == null) {
				result = false;
				logger.error("Attribute Does Not Exist");
				throw new DataAccessException("Attribute Does Not Exist");
			}
			repository.deleteData(attribute);
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;

	}

}
