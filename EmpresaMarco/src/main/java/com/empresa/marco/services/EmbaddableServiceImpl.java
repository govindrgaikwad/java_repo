package com.empresa.marco.services;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.marco.data.EmbaddableData;
import com.empresa.marco.entity.Embaddable;
import com.empresa.marco.exceptions.DataAccessException;
import com.empresa.marco.repository.GenericRepository;

@Service("EmbaddableService")
public class EmbaddableServiceImpl implements EmbaddableService {

	private static Logger logger = Logger
			.getLogger(EmbaddableServiceImpl.class);

	@Autowired
	GenericRepository repository;

	@Override
	@Transactional(readOnly = true)
	public EmbaddableData getEmbaddableById(Integer embaddableId)
			throws DataAccessException {

		EmbaddableData data = new EmbaddableData();
		Embaddable newEmbaddable = null;
		try {
			newEmbaddable = repository.findEmbaddableById(embaddableId);
			if (newEmbaddable != null) {
				data.setEmbaddableId(newEmbaddable.getEmbaddableId());

				if (newEmbaddable.getObjectDefination() != null)
					data.setObjectId(newEmbaddable.getObjectDefination()
							.getObjectId());

				data.setName(newEmbaddable.getName());

				data.setCamelCaseName(newEmbaddable.getCamelCaseName());

				if (newEmbaddable.getProject() != null)
					data.setProjectId(newEmbaddable.getProject().getProjectId());

				if (newEmbaddable.getProjectVersion() != null)
					data.setProjectVersionId(newEmbaddable.getProjectVersion()
							.getProjectVersionId());

				data.setCreatedBy(newEmbaddable.getCreatedBy());

				data.setCreatedDate(newEmbaddable.getCreatedDate());

				data.setUpdatedBy(newEmbaddable.getUpdatedBy());

				data.setUpdatedDate(newEmbaddable.getUpdatedDate());

			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return data;

	}

	@Override
	@Transactional(readOnly = true)
	public EmbaddableData getEmbaddableByOjectId(Integer objectId)
			throws DataAccessException {

		EmbaddableData data = new EmbaddableData();
		Embaddable newEmbaddable = null;
		try {
			newEmbaddable = repository.findEmbaddableByObjectId(objectId);
			if (newEmbaddable != null) {
				data.setEmbaddableId(newEmbaddable.getEmbaddableId());

				if (newEmbaddable.getObjectDefination() != null)
					data.setObjectId(newEmbaddable.getObjectDefination()
							.getObjectId());

				data.setName(newEmbaddable.getName());

				data.setCamelCaseName(newEmbaddable.getCamelCaseName());

				if (newEmbaddable.getProject() != null)
					data.setProjectId(newEmbaddable.getProject().getProjectId());

				if (newEmbaddable.getProjectVersion() != null)
					data.setProjectVersionId(newEmbaddable.getProjectVersion()
							.getProjectVersionId());

				data.setCreatedBy(newEmbaddable.getCreatedBy());

				data.setCreatedDate(newEmbaddable.getCreatedDate());

				data.setUpdatedBy(newEmbaddable.getUpdatedBy());

				data.setUpdatedDate(newEmbaddable.getUpdatedDate());

			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return data;

	}

	@Override
	@Transactional
	public Boolean saveEmbaddable(EmbaddableData embaddableData)
			throws DataAccessException {
		Boolean result = true;
		try {
			Embaddable embaddable = new Embaddable();
			embaddable.setEmbaddableId(embaddableData.getEmbaddableId());
			if (embaddableData.getObjectId() != null) {
				embaddable
						.setObjectDefination(repository
								.findObjectDefinationById(embaddableData
										.getObjectId()));
			}
			embaddable.setName(embaddableData.getName());
			embaddable.setCamelCaseName(embaddableData.getCamelCaseName());
			if (embaddableData.getProjectId() != null) {
				embaddable.setProject(repository.findProjectById(embaddableData
						.getProjectId()));
			}
			if (embaddableData.getProjectVersionId() != null) {
				embaddable.setProjectVersion(repository
						.findProjectVersionById(embaddableData
								.getProjectVersionId()));
			}
			embaddable.setCreatedBy(embaddableData.getCreatedBy());
			embaddable.setCreatedDate(embaddableData.getCreatedDate());
			embaddable.setUpdatedBy(embaddableData.getUpdatedBy());
			embaddable.setUpdatedDate(embaddableData.getUpdatedDate());

			repository.saveData(embaddable);
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;
	}

	@Override
	@Transactional
	public Boolean updateEmbaddable(EmbaddableData embaddableData)
			throws DataAccessException {
		Boolean result = true;
		try {
			Embaddable embaddable = null;
			if (embaddableData.getEmbaddableId() != null
					&& embaddableData.getEmbaddableId() > 0) {
				embaddable = repository.findEmbaddableById(embaddableData
						.getEmbaddableId());
				if (embaddable == null) {
					result = false;
					logger.error("Embaddable Does Not Exist");
					throw new DataAccessException("Embaddable Does Not Exist");
				}
				if (embaddableData.getObjectId() != null) {
					embaddable.setObjectDefination(repository
							.findObjectDefinationById(embaddableData
									.getObjectId()));
				}
				embaddable.setName(embaddableData.getName());
				embaddable.setCamelCaseName(embaddableData.getCamelCaseName());
				if (embaddableData.getProjectId() != null) {
					embaddable.setProject(repository
							.findProjectById(embaddableData.getProjectId()));
				}
				if (embaddableData.getProjectVersionId() != null) {
					embaddable.setProjectVersion(repository
							.findProjectVersionById(embaddableData
									.getProjectVersionId()));
				}
				embaddable.setCreatedBy(embaddableData.getCreatedBy());
				embaddable.setCreatedDate(embaddableData.getCreatedDate());
				embaddable.setUpdatedBy(embaddableData.getUpdatedBy());
				embaddable.setUpdatedDate(embaddableData.getUpdatedDate());
				repository.updateData(embaddable);
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
	public Boolean deleteEmbaddable(EmbaddableData embaddableData)
			throws DataAccessException {
		Boolean result = true;
		try {
			Embaddable embaddable = null;
			if (embaddableData.getEmbaddableId() != null
					&& embaddableData.getEmbaddableId() > 0) {
				embaddable = repository.findEmbaddableById(embaddableData
						.getEmbaddableId());
			}
			if (embaddable == null) {
				result = false;
				logger.error("Embaddable Does Not Exist");
				throw new DataAccessException("Embaddable Does Not Exist");
			}
			repository.deleteData(embaddable);
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;
	}

	@Override
	@Transactional
	public Boolean deleteEmbaddableById(Integer embaddableId)
			throws DataAccessException {
		Boolean result = true;
		try {
			Embaddable embaddable = repository.findEmbaddableById(embaddableId);
			if (embaddable == null) {
				result = false;
				logger.error("Embaddable Does Not Exist");
				throw new DataAccessException("Embaddable Does Not Exist");
			}
			repository.deleteData(embaddable);
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;

	}

}
