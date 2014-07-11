package com.wide.stringer.generator.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wide.stringer.generator.dto.MethodParameterData;
import com.wide.stringer.generator.entity.MethodParameter;
import com.wide.stringer.generator.exceptions.DataAccessException;
import com.wide.stringer.generator.exceptions.OperationResult;
import com.wide.stringer.generator.exceptions.ServiceOperationResult;
import com.wide.stringer.generator.repository.GenericRepository;

@Service("MethodParameterService")
public class MethodParameterService {

	private static Logger logger = Logger
			.getLogger(MethodParameterService.class);

	@Autowired
	private GenericRepository repository;

	@Transactional(readOnly = true)
	public List<MethodParameterData> getAllMethodParameter()
			throws DataAccessException {
		List<MethodParameterData> methodParameterDataList = new ArrayList<MethodParameterData>();
		List<MethodParameter> methodParameterList = null;
		try {
			methodParameterList = repository.findAllMethodParameter();
			if (methodParameterList != null && methodParameterList.size() > 0) {
				for (MethodParameter newMethodParameter : methodParameterList) {
					MethodParameterData data = new MethodParameterData();
					data.setId(newMethodParameter.getId());

					if (newMethodParameter.getMethod() != null)
						data.setMethodId(newMethodParameter.getMethod()
								.getMethodId());

					if (newMethodParameter.getAttribute() != null)
						data.setAttributeId(newMethodParameter.getAttribute()
								.getAttributeId());

					methodParameterDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return methodParameterDataList;
	}

	@Transactional(readOnly = true)
	public MethodParameterData getMethodParameterById(Integer idId)
			throws DataAccessException {
		MethodParameterData data = new MethodParameterData();
		MethodParameter newMethodParameter = null;
		try {
			newMethodParameter = repository.findMethodParameterById(idId);
			if (newMethodParameter != null) {
				data.setId(newMethodParameter.getId());

				if (newMethodParameter.getMethod() != null)
					data.setMethodId(newMethodParameter.getMethod()
							.getMethodId());

				if (newMethodParameter.getAttribute() != null)
					data.setAttributeId(newMethodParameter.getAttribute()
							.getAttributeId());

			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return data;
	}

	@Transactional
	public ServiceOperationResult deleteMethodParameterById(Integer idId)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			MethodParameter methodParameter = repository
					.findMethodParameterById(idId);
			if (methodParameter == null) {
				operationResult.setOperationResult(OperationResult.WARNING);
				messages.add("MethodParameter Does Not Exist");
				logger.error("MethodParameter Does Not Exist");
				throw new DataAccessException("MethodParameter Does Not Exist");
			}
			repository.deleteData(methodParameter);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while deleting value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

	@Transactional(readOnly = true)
	public List<MethodParameterData> getMethodParameterByMethodId(
			Integer methodId) throws DataAccessException {
		List<MethodParameterData> methodParameterDataList = new ArrayList<MethodParameterData>();
		List<MethodParameter> methodParameterList = null;
		try {
			methodParameterList = repository
					.findMethodParameterByMethodId(methodId);
			if (methodParameterList != null && methodParameterList.size() > 0) {
				for (MethodParameter newMethodParameter : methodParameterList) {
					MethodParameterData data = new MethodParameterData();
					data.setId(newMethodParameter.getId());

					if (newMethodParameter.getMethod() != null)
						data.setMethodId(newMethodParameter.getMethod()
								.getMethodId());

					if (newMethodParameter.getAttribute() != null)
						data.setAttributeId(newMethodParameter.getAttribute()
								.getAttributeId());

					methodParameterDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return methodParameterDataList;
	}

	@Transactional(readOnly = true)
	public List<MethodParameterData> getMethodParameterByAttributeId(
			Integer attributeId) throws DataAccessException {
		List<MethodParameterData> methodParameterDataList = new ArrayList<MethodParameterData>();
		List<MethodParameter> methodParameterList = null;
		try {
			methodParameterList = repository
					.findMethodParameterByAttributeId(attributeId);
			if (methodParameterList != null && methodParameterList.size() > 0) {
				for (MethodParameter newMethodParameter : methodParameterList) {
					MethodParameterData data = new MethodParameterData();
					data.setId(newMethodParameter.getId());

					if (newMethodParameter.getMethod() != null)
						data.setMethodId(newMethodParameter.getMethod()
								.getMethodId());

					if (newMethodParameter.getAttribute() != null)
						data.setAttributeId(newMethodParameter.getAttribute()
								.getAttributeId());

					methodParameterDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return methodParameterDataList;
	}

	@Transactional
	public ServiceOperationResult saveMethodParameter(MethodParameterData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			MethodParameter methodParameter = new MethodParameter();
			methodParameter.setId(data.getId());
			if (data.getMethodId() != null) {
				methodParameter.setMethod(repository.findMethodById(data
						.getMethodId()));
			}
			if (data.getAttributeId() != null) {
				methodParameter.setAttribute(repository.findAttributeById(data
						.getAttributeId()));
			}

			repository.saveData(methodParameter);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while Saving Value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

	@Transactional
	public ServiceOperationResult updateMethodParameter(MethodParameterData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			MethodParameter methodParameter = null;
			if (data.getId() != null && data.getId() > 0) {
				methodParameter = repository.findMethodParameterById(data
						.getId());
				if (methodParameter == null) {
					operationResult.setOperationResult(OperationResult.WARNING);
					messages.add("MethodParameter Does Not Exist");
					logger.error("MethodParameter Does Not Exist");
					throw new DataAccessException(
							"MethodParameter Does Not Exist");
				}
				if (data.getMethodId() != null) {
					methodParameter.setMethod(repository.findMethodById(data
							.getMethodId()));
				}
				if (data.getAttributeId() != null) {
					methodParameter.setAttribute(repository
							.findAttributeById(data.getAttributeId()));
				}
				repository.updateData(methodParameter);
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
	public ServiceOperationResult deleteMethodParameter(MethodParameterData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			MethodParameter methodParameter = null;
			if (data.getId() != null && data.getId() > 0) {
				methodParameter = repository.findMethodParameterById(data
						.getId());
			}
			if (methodParameter == null) {
				operationResult.setOperationResult(OperationResult.WARNING);
				messages.add("MethodParameter Does Not Exist");
				logger.error("MethodParameter Does Not Exist");
				throw new DataAccessException("MethodParameter Does Not Exist");
			}
			repository.deleteData(methodParameter);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while Deleting Value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

}