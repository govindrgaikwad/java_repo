package com.wide.stringer.generator.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wide.stringer.generator.dto.DataTypeData;
import com.wide.stringer.generator.entity.DataType;
import com.wide.stringer.generator.exceptions.DataAccessException;
import com.wide.stringer.generator.exceptions.OperationResult;
import com.wide.stringer.generator.exceptions.ServiceOperationResult;
import com.wide.stringer.generator.repository.GenericRepository;

@Service("DataTypeService")
public class DataTypeService {

	private static Logger logger = Logger.getLogger(DataTypeService.class);

	@Autowired
	private GenericRepository repository;

	@Transactional(readOnly = true)
	public List<DataTypeData> getAllDataType() throws DataAccessException {
		List<DataTypeData> dataTypeDataList = new ArrayList<DataTypeData>();
		List<DataType> dataTypeList = null;
		try {
			dataTypeList = repository.findAllDataType();
			if (dataTypeList != null && dataTypeList.size() > 0) {
				for (DataType newDataType : dataTypeList) {
					DataTypeData data = new DataTypeData();
					data.setDataTypeId(newDataType.getDataTypeId());

					data.setConstantValue(newDataType.getConstantValue());

					data.setSQLType(newDataType.getSQLType());

					data.setJavaType(newDataType.getJavaType());

					dataTypeDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return dataTypeDataList;
	}

	@Transactional(readOnly = true)
	public DataTypeData getDataTypeById(Integer dataTypeIdId)
			throws DataAccessException {
		DataTypeData data = new DataTypeData();
		DataType newDataType = null;
		try {
			newDataType = repository.findDataTypeById(dataTypeIdId);
			if (newDataType != null) {
				data.setDataTypeId(newDataType.getDataTypeId());

				data.setConstantValue(newDataType.getConstantValue());

				data.setSQLType(newDataType.getSQLType());

				data.setJavaType(newDataType.getJavaType());

			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return data;
	}

	@Transactional
	public ServiceOperationResult deleteDataTypeById(Integer dataTypeIdId)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			DataType dataType = repository.findDataTypeById(dataTypeIdId);
			if (dataType == null) {
				operationResult.setOperationResult(OperationResult.WARNING);
				messages.add("DataType Does Not Exist");
				logger.error("DataType Does Not Exist");
				throw new DataAccessException("DataType Does Not Exist");
			}
			repository.deleteData(dataType);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while deleting value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

	@Transactional(readOnly = true)
	public DataTypeData getDataTypeByConstantValue(Integer constantValue)
			throws DataAccessException {
		DataTypeData data = null;
		DataType dataType = null;
		try {
			dataType = repository.findDataTypeByConstantValue(constantValue);

			data = new DataTypeData();
			data.setDataTypeId(dataType.getDataTypeId());

			data.setConstantValue(dataType.getConstantValue());

			data.setSQLType(dataType.getSQLType());

			data.setJavaType(dataType.getJavaType());

		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return data;
	}

	@Transactional(readOnly = true)
	public List<DataTypeData> getDataTypeBySQLType(String sQLType)
			throws DataAccessException {
		List<DataTypeData> dataTypeDataList = new ArrayList<DataTypeData>();
		List<DataType> dataTypeList = null;
		try {
			dataTypeList = repository.findDataTypeBySQLType(sQLType);
			if (dataTypeList != null && dataTypeList.size() > 0) {
				for (DataType newDataType : dataTypeList) {
					DataTypeData data = new DataTypeData();
					data.setDataTypeId(newDataType.getDataTypeId());

					data.setConstantValue(newDataType.getConstantValue());

					data.setSQLType(newDataType.getSQLType());

					data.setJavaType(newDataType.getJavaType());

					dataTypeDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return dataTypeDataList;
	}

	@Transactional(readOnly = true)
	public List<DataTypeData> getDataTypeByJavaType(String javaType)
			throws DataAccessException {
		List<DataTypeData> dataTypeDataList = new ArrayList<DataTypeData>();
		List<DataType> dataTypeList = null;
		try {
			dataTypeList = repository.findDataTypeByJavaType(javaType);
			if (dataTypeList != null && dataTypeList.size() > 0) {
				for (DataType newDataType : dataTypeList) {
					DataTypeData data = new DataTypeData();
					data.setDataTypeId(newDataType.getDataTypeId());

					data.setConstantValue(newDataType.getConstantValue());

					data.setSQLType(newDataType.getSQLType());

					data.setJavaType(newDataType.getJavaType());

					dataTypeDataList.add(data);

				}
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return dataTypeDataList;
	}

	@Transactional
	public ServiceOperationResult saveDataType(DataTypeData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			DataType dataType = new DataType();
			dataType.setDataTypeId(data.getDataTypeId());
			dataType.setConstantValue(data.getConstantValue());
			dataType.setSQLType(data.getSQLType());
			dataType.setJavaType(data.getJavaType());

			repository.saveData(dataType);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while Saving Value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

	@Transactional
	public ServiceOperationResult updateDataType(DataTypeData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			DataType dataType = null;
			if (data.getDataTypeId() != null && data.getDataTypeId() > 0) {
				dataType = repository.findDataTypeById(data.getDataTypeId());
				if (dataType == null) {
					operationResult.setOperationResult(OperationResult.WARNING);
					messages.add("DataType Does Not Exist");
					logger.error("DataType Does Not Exist");
					throw new DataAccessException("DataType Does Not Exist");
				}
				dataType.setConstantValue(data.getConstantValue());
				dataType.setSQLType(data.getSQLType());
				dataType.setJavaType(data.getJavaType());
				repository.updateData(dataType);
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
	public ServiceOperationResult deleteDataType(DataTypeData data)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {
			DataType dataType = null;
			if (data.getDataTypeId() != null && data.getDataTypeId() > 0) {
				dataType = repository.findDataTypeById(data.getDataTypeId());
			}
			if (dataType == null) {
				operationResult.setOperationResult(OperationResult.WARNING);
				messages.add("DataType Does Not Exist");
				logger.error("DataType Does Not Exist");
				throw new DataAccessException("DataType Does Not Exist");
			}
			repository.deleteData(dataType);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error while Deleting Value");
			logger.error(e);
			throw new DataAccessException(e);
		}
		return operationResult;
	}

}