package com.wide.stringer.generator.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wide.stringer.generator.dto.EmbaddableData;
import com.wide.stringer.generator.entity.Embaddable;
import com.wide.stringer.generator.exceptions.DataAccessException;
import com.wide.stringer.generator.exceptions.OperationResult;
import com.wide.stringer.generator.exceptions.ServiceOperationResult;
import com.wide.stringer.generator.repository.GenericRepository;

@Service("EmbaddableService")
public class EmbaddableService {

	private static Logger logger = Logger.getLogger(EmbaddableService.class);

	@Autowired
	private GenericRepository repository;

	@Transactional(readOnly = true)
	public List<EmbaddableData> getAllEmbaddable() throws DataAccessException {
		List<EmbaddableData> embaddableDataList = new ArrayList<EmbaddableData>();
		List<Embaddable> embaddableList = null;
		try {
			embaddableList = repository.findAllEmbaddable();
			if (embaddableList != null && embaddableList.size() > 0) {
				for (Embaddable newEmbaddable : embaddableList) {
					EmbaddableData data = new EmbaddableData();
					if (newEmbaddable.getObjectDefination() != null)
						data.setEmbaddableId(newEmbaddable
								.getObjectDefination().getObjectId());

					data.setName(newEmbaddable.getName());

					data.setCamelCaseName(newEmbaddable.getCamelCaseName());

					embaddableDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return embaddableDataList;
	}

	@Transactional(readOnly = true)
	public EmbaddableData getEmbaddableById(Integer embaddableIdId)
			throws DataAccessException {
		EmbaddableData data = new EmbaddableData();
		Embaddable newEmbaddable = null;
		try {
			newEmbaddable = repository.findEmbaddableById(embaddableIdId);
			if (newEmbaddable != null) {
				if (newEmbaddable.getObjectDefination() != null)
					data.setEmbaddableId(newEmbaddable.getObjectDefination()
							.getObjectId());

				data.setName(newEmbaddable.getName());

				data.setCamelCaseName(newEmbaddable.getCamelCaseName());

			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return data;
	}

	@Transactional
	public ServiceOperationResult deleteEmbaddableById(Integer embaddableIdId)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			Embaddable embaddable = repository
					.findEmbaddableById(embaddableIdId);
			if (embaddable == null) {
				operationResult.setOperationResult(OperationResult.WARNING);
				messages.add("Embaddable Does Not Exist");
				logger.error("Embaddable Does Not Exist");
				throw new DataAccessException("Embaddable Does Not Exist");
			}
			repository.deleteData(embaddable);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while deleting value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

	@Transactional(readOnly = true)
	public List<EmbaddableData> getEmbaddableByName(String name)
			throws DataAccessException {
		List<EmbaddableData> embaddableDataList = new ArrayList<EmbaddableData>();
		List<Embaddable> embaddableList = null;
		try {
			embaddableList = repository.findEmbaddableByName(name);
			if (embaddableList != null && embaddableList.size() > 0) {
				for (Embaddable newEmbaddable : embaddableList) {
					EmbaddableData data = new EmbaddableData();
					if (newEmbaddable.getObjectDefination() != null)
						data.setEmbaddableId(newEmbaddable
								.getObjectDefination().getObjectId());

					data.setName(newEmbaddable.getName());

					data.setCamelCaseName(newEmbaddable.getCamelCaseName());

					embaddableDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return embaddableDataList;
	}

	@Transactional(readOnly = true)
	public List<EmbaddableData> getEmbaddableByCamelCaseName(
			String camelCaseName) throws DataAccessException {
		List<EmbaddableData> embaddableDataList = new ArrayList<EmbaddableData>();
		List<Embaddable> embaddableList = null;
		try {
			embaddableList = repository
					.findEmbaddableByCamelCaseName(camelCaseName);
			if (embaddableList != null && embaddableList.size() > 0) {
				for (Embaddable newEmbaddable : embaddableList) {
					EmbaddableData data = new EmbaddableData();
					if (newEmbaddable.getObjectDefination() != null)
						data.setEmbaddableId(newEmbaddable
								.getObjectDefination().getObjectId());

					data.setName(newEmbaddable.getName());

					data.setCamelCaseName(newEmbaddable.getCamelCaseName());

					embaddableDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return embaddableDataList;
	}

	@Transactional
	public ServiceOperationResult saveEmbaddable(EmbaddableData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			Embaddable embaddable = new Embaddable();
			if (data.getEmbaddableId() != null) {
				embaddable.setObjectDefination(repository
						.findObjectDefinationById(data.getEmbaddableId()));
			}
			embaddable.setName(data.getName());
			embaddable.setCamelCaseName(data.getCamelCaseName());

			repository.saveData(embaddable);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while Saving Value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

	@Transactional
	public ServiceOperationResult updateEmbaddable(EmbaddableData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			Embaddable embaddable = null;
			if (data.getEmbaddableId() != null && data.getEmbaddableId() > 0) {
				embaddable = repository.findEmbaddableById(data
						.getEmbaddableId());
				if (embaddable == null) {
					operationResult.setOperationResult(OperationResult.WARNING);
					messages.add("Embaddable Does Not Exist");
					logger.error("Embaddable Does Not Exist");
					throw new DataAccessException("Embaddable Does Not Exist");
				}
				if (data.getEmbaddableId() != null) {
					embaddable.setObjectDefination(repository
							.findObjectDefinationById(data.getEmbaddableId()));
				}
				embaddable.setName(data.getName());
				embaddable.setCamelCaseName(data.getCamelCaseName());
				repository.updateData(embaddable);
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
	public ServiceOperationResult deleteEmbaddable(EmbaddableData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			Embaddable embaddable = null;
			if (data.getEmbaddableId() != null && data.getEmbaddableId() > 0) {
				embaddable = repository.findEmbaddableById(data
						.getEmbaddableId());
			}
			if (embaddable == null) {
				operationResult.setOperationResult(OperationResult.WARNING);
				messages.add("Embaddable Does Not Exist");
				logger.error("Embaddable Does Not Exist");
				throw new DataAccessException("Embaddable Does Not Exist");
			}
			repository.deleteData(embaddable);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while Deleting Value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

}