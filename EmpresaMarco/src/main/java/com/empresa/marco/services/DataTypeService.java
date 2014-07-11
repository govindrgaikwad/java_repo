package com.empresa.marco.services;

import com.empresa.marco.data.DataTypeData;
import com.empresa.marco.exceptions.DataAccessException;

public interface DataTypeService {

	public DataTypeData getDataTypeById(Integer dataTypeId)
			throws DataAccessException;

	public DataTypeData getDataTypeByConstantValue(Integer constantValue)
			throws DataAccessException;

	public DataTypeData getDataTypeBySQLType(String sqlType)
			throws DataAccessException;

	public DataTypeData getDataTypeByJavaType(String javaType)
			throws DataAccessException;

	public Boolean saveDataType(DataTypeData dataTypeData)
			throws DataAccessException;

	public Boolean updateDataType(DataTypeData dataTypeData)
			throws DataAccessException;

	public Boolean deleteDataType(DataTypeData dataTypeData)
			throws DataAccessException;

	public Boolean deleteDataTypeById(Integer dataTypeId)
			throws DataAccessException;

}
