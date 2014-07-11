package com.wide.stringer.generator.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wide.stringer.generator.dto.MethodData;
import com.wide.stringer.generator.entity.Method;
import com.wide.stringer.generator.exceptions.DataAccessException;
import com.wide.stringer.generator.exceptions.OperationResult;
import com.wide.stringer.generator.exceptions.ServiceOperationResult;
import com.wide.stringer.generator.repository.GenericRepository;

@Service("MethodService")
public class MethodService {

	private static Logger logger = Logger.getLogger(MethodService.class);

	@Autowired
	private GenericRepository repository;

	@Transactional(readOnly = true)
	public List<MethodData> getAllMethod() throws DataAccessException {
		List<MethodData> methodDataList = new ArrayList<MethodData>();
		List<Method> methodList = null;
		try {
			methodList = repository.findAllMethod();
			if (methodList != null && methodList.size() > 0) {
				for (Method newMethod : methodList) {
					MethodData data = new MethodData();
					data.setMethodId(newMethod.getMethodId());

					if (newMethod.getObjectDefination() != null)
						data.setObjectId(newMethod.getObjectDefination()
								.getObjectId());

					data.setName(newMethod.getName());

					data.setCamelCaseName(newMethod.getCamelCaseName());

					data.setAccessSpecifier(newMethod.getAccessSpecifier());

					data.setReturnType(newMethod.getReturnType());

					data.setMethodType(newMethod.getMethodType());

					data.setSoapOperationName(newMethod.getSoapOperationName());

					data.setSoapAction(newMethod.getSoapAction());

					data.setExceptions(newMethod.getExceptions());

					methodDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return methodDataList;
	}

	@Transactional(readOnly = true)
	public MethodData getMethodById(Integer methodIdId)
			throws DataAccessException {
		MethodData data = new MethodData();
		Method newMethod = null;
		try {
			newMethod = repository.findMethodById(methodIdId);
			if (newMethod != null) {
				data.setMethodId(newMethod.getMethodId());

				if (newMethod.getObjectDefination() != null)
					data.setObjectId(newMethod.getObjectDefination()
							.getObjectId());

				data.setName(newMethod.getName());

				data.setCamelCaseName(newMethod.getCamelCaseName());

				data.setAccessSpecifier(newMethod.getAccessSpecifier());

				data.setReturnType(newMethod.getReturnType());

				data.setMethodType(newMethod.getMethodType());

				data.setSoapOperationName(newMethod.getSoapOperationName());

				data.setSoapAction(newMethod.getSoapAction());

				data.setExceptions(newMethod.getExceptions());

			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return data;
	}

	@Transactional
	public ServiceOperationResult deleteMethodById(Integer methodIdId)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			Method method = repository.findMethodById(methodIdId);
			if (method == null) {
				operationResult.setOperationResult(OperationResult.WARNING);
				messages.add("Method Does Not Exist");
				logger.error("Method Does Not Exist");
				throw new DataAccessException("Method Does Not Exist");
			}
			repository.deleteData(method);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while deleting value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

	@Transactional(readOnly = true)
	public List<MethodData> getMethodByObjectId(Integer objectId)
			throws DataAccessException {
		List<MethodData> methodDataList = new ArrayList<MethodData>();
		List<Method> methodList = null;
		try {
			methodList = repository.findMethodByObjectId(objectId);
			if (methodList != null && methodList.size() > 0) {
				for (Method newMethod : methodList) {
					MethodData data = new MethodData();
					data.setMethodId(newMethod.getMethodId());

					if (newMethod.getObjectDefination() != null)
						data.setObjectId(newMethod.getObjectDefination()
								.getObjectId());

					data.setName(newMethod.getName());

					data.setCamelCaseName(newMethod.getCamelCaseName());

					data.setAccessSpecifier(newMethod.getAccessSpecifier());

					data.setReturnType(newMethod.getReturnType());

					data.setMethodType(newMethod.getMethodType());

					data.setSoapOperationName(newMethod.getSoapOperationName());

					data.setSoapAction(newMethod.getSoapAction());

					data.setExceptions(newMethod.getExceptions());

					methodDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return methodDataList;
	}

	@Transactional(readOnly = true)
	public List<MethodData> getMethodByName(String name)
			throws DataAccessException {
		List<MethodData> methodDataList = new ArrayList<MethodData>();
		List<Method> methodList = null;
		try {
			methodList = repository.findMethodByName(name);
			if (methodList != null && methodList.size() > 0) {
				for (Method newMethod : methodList) {
					MethodData data = new MethodData();
					data.setMethodId(newMethod.getMethodId());

					if (newMethod.getObjectDefination() != null)
						data.setObjectId(newMethod.getObjectDefination()
								.getObjectId());

					data.setName(newMethod.getName());

					data.setCamelCaseName(newMethod.getCamelCaseName());

					data.setAccessSpecifier(newMethod.getAccessSpecifier());

					data.setReturnType(newMethod.getReturnType());

					data.setMethodType(newMethod.getMethodType());

					data.setSoapOperationName(newMethod.getSoapOperationName());

					data.setSoapAction(newMethod.getSoapAction());

					data.setExceptions(newMethod.getExceptions());

					methodDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return methodDataList;
	}

	@Transactional(readOnly = true)
	public List<MethodData> getMethodByCamelCaseName(String camelCaseName)
			throws DataAccessException {
		List<MethodData> methodDataList = new ArrayList<MethodData>();
		List<Method> methodList = null;
		try {
			methodList = repository.findMethodByCamelCaseName(camelCaseName);
			if (methodList != null && methodList.size() > 0) {
				for (Method newMethod : methodList) {
					MethodData data = new MethodData();
					data.setMethodId(newMethod.getMethodId());

					if (newMethod.getObjectDefination() != null)
						data.setObjectId(newMethod.getObjectDefination()
								.getObjectId());

					data.setName(newMethod.getName());

					data.setCamelCaseName(newMethod.getCamelCaseName());

					data.setAccessSpecifier(newMethod.getAccessSpecifier());

					data.setReturnType(newMethod.getReturnType());

					data.setMethodType(newMethod.getMethodType());

					data.setSoapOperationName(newMethod.getSoapOperationName());

					data.setSoapAction(newMethod.getSoapAction());

					data.setExceptions(newMethod.getExceptions());

					methodDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return methodDataList;
	}

	@Transactional(readOnly = true)
	public List<MethodData> getMethodByAccessSpecifier(String accessSpecifier)
			throws DataAccessException {
		List<MethodData> methodDataList = new ArrayList<MethodData>();
		List<Method> methodList = null;
		try {
			methodList = repository
					.findMethodByAccessSpecifier(accessSpecifier);
			if (methodList != null && methodList.size() > 0) {
				for (Method newMethod : methodList) {
					MethodData data = new MethodData();
					data.setMethodId(newMethod.getMethodId());

					if (newMethod.getObjectDefination() != null)
						data.setObjectId(newMethod.getObjectDefination()
								.getObjectId());

					data.setName(newMethod.getName());

					data.setCamelCaseName(newMethod.getCamelCaseName());

					data.setAccessSpecifier(newMethod.getAccessSpecifier());

					data.setReturnType(newMethod.getReturnType());

					data.setMethodType(newMethod.getMethodType());

					data.setSoapOperationName(newMethod.getSoapOperationName());

					data.setSoapAction(newMethod.getSoapAction());

					data.setExceptions(newMethod.getExceptions());

					methodDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return methodDataList;
	}

	@Transactional(readOnly = true)
	public List<MethodData> getMethodByReturnType(String returnType)
			throws DataAccessException {
		List<MethodData> methodDataList = new ArrayList<MethodData>();
		List<Method> methodList = null;
		try {
			methodList = repository.findMethodByReturnType(returnType);
			if (methodList != null && methodList.size() > 0) {
				for (Method newMethod : methodList) {
					MethodData data = new MethodData();
					data.setMethodId(newMethod.getMethodId());

					if (newMethod.getObjectDefination() != null)
						data.setObjectId(newMethod.getObjectDefination()
								.getObjectId());

					data.setName(newMethod.getName());

					data.setCamelCaseName(newMethod.getCamelCaseName());

					data.setAccessSpecifier(newMethod.getAccessSpecifier());

					data.setReturnType(newMethod.getReturnType());

					data.setMethodType(newMethod.getMethodType());

					data.setSoapOperationName(newMethod.getSoapOperationName());

					data.setSoapAction(newMethod.getSoapAction());

					data.setExceptions(newMethod.getExceptions());

					methodDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return methodDataList;
	}

	@Transactional(readOnly = true)
	public List<MethodData> getMethodByMethodType(String methodType)
			throws DataAccessException {
		List<MethodData> methodDataList = new ArrayList<MethodData>();
		List<Method> methodList = null;
		try {
			methodList = repository.findMethodByMethodType(methodType);
			if (methodList != null && methodList.size() > 0) {
				for (Method newMethod : methodList) {
					MethodData data = new MethodData();
					data.setMethodId(newMethod.getMethodId());

					if (newMethod.getObjectDefination() != null)
						data.setObjectId(newMethod.getObjectDefination()
								.getObjectId());

					data.setName(newMethod.getName());

					data.setCamelCaseName(newMethod.getCamelCaseName());

					data.setAccessSpecifier(newMethod.getAccessSpecifier());

					data.setReturnType(newMethod.getReturnType());

					data.setMethodType(newMethod.getMethodType());

					data.setSoapOperationName(newMethod.getSoapOperationName());

					data.setSoapAction(newMethod.getSoapAction());

					data.setExceptions(newMethod.getExceptions());

					methodDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return methodDataList;
	}

	@Transactional(readOnly = true)
	public List<MethodData> getMethodBySoapOperationName(
			String soapOperationName) throws DataAccessException {
		List<MethodData> methodDataList = new ArrayList<MethodData>();
		List<Method> methodList = null;
		try {
			methodList = repository
					.findMethodBySoapOperationName(soapOperationName);
			if (methodList != null && methodList.size() > 0) {
				for (Method newMethod : methodList) {
					MethodData data = new MethodData();
					data.setMethodId(newMethod.getMethodId());

					if (newMethod.getObjectDefination() != null)
						data.setObjectId(newMethod.getObjectDefination()
								.getObjectId());

					data.setName(newMethod.getName());

					data.setCamelCaseName(newMethod.getCamelCaseName());

					data.setAccessSpecifier(newMethod.getAccessSpecifier());

					data.setReturnType(newMethod.getReturnType());

					data.setMethodType(newMethod.getMethodType());

					data.setSoapOperationName(newMethod.getSoapOperationName());

					data.setSoapAction(newMethod.getSoapAction());

					data.setExceptions(newMethod.getExceptions());

					methodDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return methodDataList;
	}

	@Transactional(readOnly = true)
	public List<MethodData> getMethodBySoapAction(String soapAction)
			throws DataAccessException {
		List<MethodData> methodDataList = new ArrayList<MethodData>();
		List<Method> methodList = null;
		try {
			methodList = repository.findMethodBySoapAction(soapAction);
			if (methodList != null && methodList.size() > 0) {
				for (Method newMethod : methodList) {
					MethodData data = new MethodData();
					data.setMethodId(newMethod.getMethodId());

					if (newMethod.getObjectDefination() != null)
						data.setObjectId(newMethod.getObjectDefination()
								.getObjectId());

					data.setName(newMethod.getName());

					data.setCamelCaseName(newMethod.getCamelCaseName());

					data.setAccessSpecifier(newMethod.getAccessSpecifier());

					data.setReturnType(newMethod.getReturnType());

					data.setMethodType(newMethod.getMethodType());

					data.setSoapOperationName(newMethod.getSoapOperationName());

					data.setSoapAction(newMethod.getSoapAction());

					data.setExceptions(newMethod.getExceptions());

					methodDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return methodDataList;
	}

	@Transactional(readOnly = true)
	public List<MethodData> getMethodByExceptions(String exceptions)
			throws DataAccessException {
		List<MethodData> methodDataList = new ArrayList<MethodData>();
		List<Method> methodList = null;
		try {
			methodList = repository.findMethodByExceptions(exceptions);
			if (methodList != null && methodList.size() > 0) {
				for (Method newMethod : methodList) {
					MethodData data = new MethodData();
					data.setMethodId(newMethod.getMethodId());

					if (newMethod.getObjectDefination() != null)
						data.setObjectId(newMethod.getObjectDefination()
								.getObjectId());

					data.setName(newMethod.getName());

					data.setCamelCaseName(newMethod.getCamelCaseName());

					data.setAccessSpecifier(newMethod.getAccessSpecifier());

					data.setReturnType(newMethod.getReturnType());

					data.setMethodType(newMethod.getMethodType());

					data.setSoapOperationName(newMethod.getSoapOperationName());

					data.setSoapAction(newMethod.getSoapAction());

					data.setExceptions(newMethod.getExceptions());

					methodDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return methodDataList;
	}

	@Transactional
	public ServiceOperationResult saveMethod(MethodData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			Method method = new Method();
			method.setMethodId(data.getMethodId());
			if (data.getObjectId() != null) {
				method.setObjectDefination(repository
						.findObjectDefinationById(data.getObjectId()));
			}
			method.setName(data.getName());
			method.setCamelCaseName(data.getCamelCaseName());
			method.setAccessSpecifier(data.getAccessSpecifier());
			method.setReturnType(data.getReturnType());
			method.setMethodType(data.getMethodType());
			method.setSoapOperationName(data.getSoapOperationName());
			method.setSoapAction(data.getSoapAction());
			method.setExceptions(data.getExceptions());

			repository.saveData(method);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while Saving Value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

	@Transactional
	public ServiceOperationResult updateMethod(MethodData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			Method method = null;
			if (data.getMethodId() != null && data.getMethodId() > 0) {
				method = repository.findMethodById(data.getMethodId());
				if (method == null) {
					operationResult.setOperationResult(OperationResult.WARNING);
					messages.add("Method Does Not Exist");
					logger.error("Method Does Not Exist");
					throw new DataAccessException("Method Does Not Exist");
				}
				if (data.getObjectId() != null) {
					method.setObjectDefination(repository
							.findObjectDefinationById(data.getObjectId()));
				}
				method.setName(data.getName());
				method.setCamelCaseName(data.getCamelCaseName());
				method.setAccessSpecifier(data.getAccessSpecifier());
				method.setReturnType(data.getReturnType());
				method.setMethodType(data.getMethodType());
				method.setSoapOperationName(data.getSoapOperationName());
				method.setSoapAction(data.getSoapAction());
				method.setExceptions(data.getExceptions());
				repository.updateData(method);
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
	public ServiceOperationResult deleteMethod(MethodData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			Method method = null;
			if (data.getMethodId() != null && data.getMethodId() > 0) {
				method = repository.findMethodById(data.getMethodId());
			}
			if (method == null) {
				operationResult.setOperationResult(OperationResult.WARNING);
				messages.add("Method Does Not Exist");
				logger.error("Method Does Not Exist");
				throw new DataAccessException("Method Does Not Exist");
			}
			repository.deleteData(method);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while Deleting Value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

}