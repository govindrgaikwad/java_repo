package com.empresa.marco.services;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.marco.data.DataTypeData;
import com.empresa.marco.entity.DataType;
import com.empresa.marco.exceptions.DataAccessException;
import com.empresa.marco.repository.GenericRepository;

@Service("DataTypeService")
public class DataTypeServiceImpl implements DataTypeService {

	private static Logger logger = Logger.getLogger(DataTypeServiceImpl.class);

	@Autowired
	GenericRepository repository;

	@Override
	@Transactional(readOnly = true)
	public DataTypeData getDataTypeById(Integer dataTypeId)
			throws DataAccessException {
		DataTypeData data = new DataTypeData();
		DataType newDataType = null;
		try {
			newDataType = repository.findDataTypeById(dataTypeId);
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

	@Override
	@Transactional(readOnly = true)
	public DataTypeData getDataTypeByConstantValue(Integer constantValue)
			throws DataAccessException {

		DataTypeData dataTypeData = new DataTypeData();
		DataType dataType = null;
		try {
			dataType = repository.findDataTypeByConstantValue(constantValue);
			if (dataType != null) {
				DataTypeData data = new DataTypeData();
				data.setDataTypeId(dataType.getDataTypeId());

				data.setConstantValue(dataType.getConstantValue());

				data.setSQLType(dataType.getSQLType());

				data.setJavaType(dataType.getJavaType());
			}

		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return dataTypeData;

	}

	@Override
	@Transactional(readOnly = true)
	public DataTypeData getDataTypeBySQLType(String sqlType)
			throws DataAccessException {

		DataTypeData dataTypeData = new DataTypeData();
		DataType dataType = null;
		try {
			dataType = repository.findDataTypeBySQLType(sqlType);
			if (dataType != null) {
				DataTypeData data = new DataTypeData();
				data.setDataTypeId(dataType.getDataTypeId());

				data.setConstantValue(dataType.getConstantValue());

				data.setSQLType(dataType.getSQLType());

				data.setJavaType(dataType.getJavaType());
			}

		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return dataTypeData;

	}

	@Override
	@Transactional(readOnly = true)
	public DataTypeData getDataTypeByJavaType(String javaType)
			throws DataAccessException {

		DataTypeData dataTypeData = new DataTypeData();
		DataType dataType = null;
		try {
			dataType = repository.findDataTypeByJavaType(javaType);
			if (dataType != null) {
				DataTypeData data = new DataTypeData();
				data.setDataTypeId(dataType.getDataTypeId());

				data.setConstantValue(dataType.getConstantValue());

				data.setSQLType(dataType.getSQLType());

				data.setJavaType(dataType.getJavaType());
			}

		} catch (PersistenceException e) {
			logger.error(e);
			throw new DataAccessException(e);
		}
		return dataTypeData;

	}

	@Override
	@Transactional
	public Boolean saveDataType(DataTypeData dataTypeData)
			throws DataAccessException {
		Boolean result = true;
		try {
			DataType dataType = new DataType();
			dataType.setDataTypeId(dataTypeData.getDataTypeId());
			dataType.setConstantValue(dataTypeData.getConstantValue());
			dataType.setSQLType(dataTypeData.getSQLType());
			dataType.setJavaType(dataTypeData.getJavaType());

			repository.saveData(dataType);
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;
	}

	@Override
	@Transactional
	public Boolean updateDataType(DataTypeData dataTypeData)
			throws DataAccessException {
		Boolean result = true;
		try {
			DataType dataType = null;
			if (dataTypeData.getDataTypeId() != null
					&& dataTypeData.getDataTypeId() > 0) {
				dataType = repository.findDataTypeById(dataTypeData
						.getDataTypeId());
				if (dataType == null) {
					result = false;
					logger.error("DataType Does Not Exist");
					throw new DataAccessException("DataType Does Not Exist");
				}
				dataType.setConstantValue(dataTypeData.getConstantValue());
				dataType.setSQLType(dataTypeData.getSQLType());
				dataType.setJavaType(dataTypeData.getJavaType());
				repository.updateData(dataType);
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
	public Boolean deleteDataType(DataTypeData dataTypeData)
			throws DataAccessException {

		Boolean result = true;
		try {
			DataType dataType = null;
			if (dataTypeData.getDataTypeId() != null
					&& dataTypeData.getDataTypeId() > 0) {
				dataType = repository.findDataTypeById(dataTypeData
						.getDataTypeId());
			}
			if (dataType == null) {
				result = false;
				logger.error("DataType Does Not Exist");
				throw new DataAccessException("DataType Does Not Exist");
			}
			repository.deleteData(dataType);
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;

	}

	@Override
	@Transactional
	public Boolean deleteDataTypeById(Integer dataTypeId)
			throws DataAccessException {
		Boolean result = true;
		try {
			DataType dataType = repository.findDataTypeById(dataTypeId);
			if (dataType == null) {
				result = false;
				logger.error("DataType Does Not Exist");
				throw new DataAccessException("DataType Does Not Exist");
			}
			repository.deleteData(dataType);
		} catch (PersistenceException e) {
			result = false;
			logger.error(e);
			throw new DataAccessException(e);
		}
		return result;

	}

}
