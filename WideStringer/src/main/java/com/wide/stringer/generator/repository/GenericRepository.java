package com.wide.stringer.generator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wide.stringer.generator.entity.Attribute;
import com.wide.stringer.generator.entity.DataType;
import com.wide.stringer.generator.entity.Embaddable;
import com.wide.stringer.generator.entity.Method;
import com.wide.stringer.generator.entity.MethodParameter;
import com.wide.stringer.generator.entity.ObjectDefination;
import com.wide.stringer.generator.entity.Relationship;

public interface GenericRepository extends
		JpaRepository<ObjectDefination, Long>, GenericCustomRepository {

	// ********** Attribute Methods***********//

	@Query(value = "SELECT list from Attribute list")
	public List<Attribute> findAllAttribute();

	@Query(value = "SELECT list from Attribute list"
			+ " where list.attributeId = :id ")
	public Attribute findAttributeById(@Param("id") Integer attributeId);

	@Query(value = "SELECT list from Attribute list"
			+ " left join fetch list.objectDefination"
			+ " where list.objectDefination.objectId = :id ", countQuery = "SELECT count(list) from Attribute list"
			+ " where list.objectDefination.objectId  = :id ")
	public List<Attribute> findAttributeByObjectId(@Param("id") Integer objectId);

	@Query(value = "SELECT list from Attribute list"
			+ " left join fetch list.referenceObjectDefination"
			+ " where list.referenceObjectDefination.objectId = :id ", countQuery = "SELECT count(list) from Attribute list"
			+ " where list.referenceObjectDefination.objectId  = :id ")
	public List<Attribute> findAttributeByReferenceObjectId(
			@Param("id") Integer objectId);

	@Query(value = "SELECT list from Attribute list"
			+ " where list.name = :id ", countQuery = "SELECT count(list) from Attribute list"
			+ " where list.name = :id ")
	public List<Attribute> findAttributeByName(@Param("id") String name);

	@Query(value = "SELECT list from Attribute list"
			+ " where list.userDefinedName = :id ", countQuery = "SELECT count(list) from Attribute list"
			+ " where list.userDefinedName = :id ")
	public List<Attribute> findAttributeByUserDefinedName(
			@Param("id") String userDefinedName);

	@Query(value = "SELECT list from Attribute list"
			+ " where list.camelCaseName = :id ", countQuery = "SELECT count(list) from Attribute list"
			+ " where list.camelCaseName = :id ")
	public List<Attribute> findAttributeByCamelCaseName(
			@Param("id") String camelCaseName);

	@Query(value = "SELECT list from Attribute list"
			+ " where list.datatype = :id ", countQuery = "SELECT count(list) from Attribute list"
			+ " where list.datatype = :id ")
	public List<Attribute> findAttributeByDatatype(@Param("id") String datatype);

	@Query(value = "SELECT list from Attribute list"
			+ " where list.javaDataType = :id ", countQuery = "SELECT count(list) from Attribute list"
			+ " where list.javaDataType = :id ")
	public List<Attribute> findAttributeByJavaDataType(
			@Param("id") String javaDataType);

	@Query(value = "SELECT list from Attribute list"
			+ " where list.primaryKey = :id ", countQuery = "SELECT count(list) from Attribute list"
			+ " where list.primaryKey = :id ")
	public List<Attribute> findAttributeByPrimaryKey(
			@Param("id") Boolean primaryKey);

	@Query(value = "SELECT list from Attribute list"
			+ " where list.foreignKey = :id ", countQuery = "SELECT count(list) from Attribute list"
			+ " where list.foreignKey = :id ")
	public List<Attribute> findAttributeByForeignKey(
			@Param("id") Boolean foreignKey);

	@Query(value = "SELECT list from Attribute list"
			+ " where list.embaddable = :id ", countQuery = "SELECT count(list) from Attribute list"
			+ " where list.embaddable = :id ")
	public List<Attribute> findAttributeByEmbaddable(
			@Param("id") Boolean embaddable);

	@Query(value = "SELECT list from Attribute list"
			+ " left join fetch list.objectDefination"
			+ " where list.name = :name and list.objectDefination.objectId = :id ")
	public Attribute findAttributeByNameAndObjectId(@Param("name") String name,
			@Param("id") Integer objectId);

	// **********************************End***************************************//

	// ******************** DataType Methods *********************************//

	@Query(value = "SELECT list from DataType list")
	public List<DataType> findAllDataType();

	@Query(value = "SELECT list from DataType list"
			+ " where list.dataTypeId = :id ")
	public DataType findDataTypeById(@Param("id") Integer dataTypeId);

	@Query(value = "SELECT list from DataType list"
			+ " where list.constantValue = :id ", countQuery = "SELECT count(list) from DataType list"
			+ " where list.constantValue = :id ")
	public DataType findDataTypeByConstantValue(
			@Param("id") Integer constantValue);

	@Query(value = "SELECT list from DataType list"
			+ " where list.sQLType = :id ", countQuery = "SELECT count(list) from DataType list"
			+ " where list.sQLType = :id ")
	public List<DataType> findDataTypeBySQLType(@Param("id") String sQLType);

	@Query(value = "SELECT list from DataType list"
			+ " where list.javaType = :id ", countQuery = "SELECT count(list) from DataType list"
			+ " where list.javaType = :id ")
	public List<DataType> findDataTypeByJavaType(@Param("id") String javaType);

	// **********************************End***************************************//

	// ******************** Embaddable Methods****************************//

	@Query(value = "SELECT list from Embaddable list")
	public List<Embaddable> findAllEmbaddable();

	@Query(value = "SELECT list from Embaddable list"
			+ " where list.embaddableId = :id ")
	public Embaddable findEmbaddableById(@Param("id") Integer embaddableId);
	
	@Query(value = "SELECT list from Embaddable list"
			+ " left join fetch list.objectDefination"
			+ " where list.objectDefination.objectId = :id ")
	public Embaddable findEmbaddableByObjectId(@Param("id") Integer objectId);

	@Query(value = "SELECT list from Embaddable list"
			+ " where list.name = :id ", countQuery = "SELECT count(list) from Embaddable list"
			+ " where list.name = :id ")
	public List<Embaddable> findEmbaddableByName(@Param("id") String name);

	@Query(value = "SELECT list from Embaddable list"
			+ " where list.camelCaseName = :id ", countQuery = "SELECT count(list) from Embaddable list"
			+ " where list.camelCaseName = :id ")
	public List<Embaddable> findEmbaddableByCamelCaseName(
			@Param("id") String camelCaseName);

	// **********************************End***************************************//

	// ******************** Method Methods *********************************//

	@Query(value = "SELECT list from Method list")
	public List<Method> findAllMethod();

	@Query(value = "SELECT list from Method list"
			+ " where list.methodId = :id ")
	public Method findMethodById(@Param("id") Integer methodId);

	@Query(value = "SELECT list from Method list"
			+ " left join fetch list.objectDefination"
			+ " where list.objectDefination.objectId = :id ", countQuery = "SELECT count(list) from Method list"
			+ " where list.objectDefination.objectId  = :id ")
	public List<Method> findMethodByObjectId(@Param("id") Integer objectId);

	@Query(value = "SELECT list from Method list" + " where list.name = :id ", countQuery = "SELECT count(list) from Method list"
			+ " where list.name = :id ")
	public List<Method> findMethodByName(@Param("id") String name);

	@Query(value = "SELECT list from Method list"
			+ " where list.camelCaseName = :id ", countQuery = "SELECT count(list) from Method list"
			+ " where list.camelCaseName = :id ")
	public List<Method> findMethodByCamelCaseName(
			@Param("id") String camelCaseName);

	@Query(value = "SELECT list from Method list"
			+ " where list.accessSpecifier = :id ", countQuery = "SELECT count(list) from Method list"
			+ " where list.accessSpecifier = :id ")
	public List<Method> findMethodByAccessSpecifier(
			@Param("id") String accessSpecifier);

	@Query(value = "SELECT list from Method list"
			+ " where list.returnType = :id ", countQuery = "SELECT count(list) from Method list"
			+ " where list.returnType = :id ")
	public List<Method> findMethodByReturnType(@Param("id") String returnType);

	@Query(value = "SELECT list from Method list"
			+ " where list.methodType = :id ", countQuery = "SELECT count(list) from Method list"
			+ " where list.methodType = :id ")
	public List<Method> findMethodByMethodType(@Param("id") String methodType);

	@Query(value = "SELECT list from Method list"
			+ " where list.soapOperationName = :id ", countQuery = "SELECT count(list) from Method list"
			+ " where list.soapOperationName = :id ")
	public List<Method> findMethodBySoapOperationName(
			@Param("id") String soapOperationName);

	@Query(value = "SELECT list from Method list"
			+ " where list.soapAction = :id ", countQuery = "SELECT count(list) from Method list"
			+ " where list.soapAction = :id ")
	public List<Method> findMethodBySoapAction(@Param("id") String soapAction);

	@Query(value = "SELECT list from Method list"
			+ " where list.exceptions = :id ", countQuery = "SELECT count(list) from Method list"
			+ " where list.exceptions = :id ")
	public List<Method> findMethodByExceptions(@Param("id") String exceptions);

	// **********************************End***************************************//

	// ******************** MethodParameter Methods************************//

	@Query(value = "SELECT list from MethodParameter list")
	public List<MethodParameter> findAllMethodParameter();

	@Query(value = "SELECT list from MethodParameter list"
			+ " where list.id = :id ")
	public MethodParameter findMethodParameterById(@Param("id") Integer id);

	@Query(value = "SELECT list from MethodParameter list"
			+ " left join fetch list.method"
			+ " where list.method.methodId = :id ", countQuery = "SELECT count(list) from MethodParameter list"
			+ " where list.method.methodId  = :id ")
	public List<MethodParameter> findMethodParameterByMethodId(
			@Param("id") Integer methodId);

	@Query(value = "SELECT list from MethodParameter list"
			+ " left join fetch list.attribute"
			+ " where list.attribute.attributeId = :id ", countQuery = "SELECT count(list) from MethodParameter list"
			+ " where list.attribute.attributeId  = :id ")
	public List<MethodParameter> findMethodParameterByAttributeId(
			@Param("id") Integer attributeId);

	// **********************************End***************************************//

	// ******************** ObjectDefination Methods**********************//

	@Query(value = "SELECT list from ObjectDefination list")
	public List<ObjectDefination> findAllObjectDefination();

	@Query(value = "SELECT list from ObjectDefination list"
			+ " where list.objectId = :id ")
	public ObjectDefination findObjectDefinationById(
			@Param("id") Integer objectId);

	@Query(value = "SELECT list from ObjectDefination list"
			+ " where list.name = :id ", countQuery = "SELECT count(list) from ObjectDefination list"
			+ " where list.name = :id ")
	public ObjectDefination findObjectDefinationByName(@Param("id") String name);

	@Query(value = "SELECT list from ObjectDefination list"
			+ " where list.schemaName = :id ", countQuery = "SELECT count(list) from ObjectDefination list"
			+ " where list.schemaName = :id ")
	public List<ObjectDefination> findObjectDefinationBySchemaName(
			@Param("id") String schemaName);

	@Query(value = "SELECT list from ObjectDefination list"
			+ " where list.dataBaseName = :id ", countQuery = "SELECT count(list) from ObjectDefination list"
			+ " where list.dataBaseName = :id ")
	public List<ObjectDefination> findObjectDefinationByDataBaseName(
			@Param("id") String dataBaseName);

	@Query(value = "SELECT list from ObjectDefination list"
			+ " where list.userDefinedName = :id ", countQuery = "SELECT count(list) from ObjectDefination list"
			+ " where list.userDefinedName = :id ")
	public List<ObjectDefination> findObjectDefinationByUserDefinedName(
			@Param("id") String userDefinedName);

	@Query(value = "SELECT list from ObjectDefination list"
			+ " where list.camelCaseName = :id ", countQuery = "SELECT count(list) from ObjectDefination list"
			+ " where list.camelCaseName = :id ")
	public List<ObjectDefination> findObjectDefinationByCamelCaseName(
			@Param("id") String camelCaseName);

	@Query(value = "SELECT list from ObjectDefination list"
			+ " where list.embaddable = :id ", countQuery = "SELECT count(list) from ObjectDefination list"
			+ " where list.embaddable = :id ")
	public List<ObjectDefination> findObjectDefinationByEmbaddable(
			@Param("id") Boolean embaddable);

	@Query(value = "SELECT list from ObjectDefination list"
			+ " where list.updated = :id ", countQuery = "SELECT count(list) from ObjectDefination list"
			+ " where list.updated = :id ")
	public List<ObjectDefination> findObjectDefinationByUpdated(
			@Param("id") Boolean updated);

	@Query(value = "SELECT list from ObjectDefination list"
			+ " where list.primaryKey = :id ", countQuery = "SELECT count(list) from ObjectDefination list"
			+ " where list.primaryKey = :id ")
	public List<ObjectDefination> findObjectDefinationByprimaryKey(
			@Param("id") Boolean primaryKey);

	// **********************************End***************************************//

	// ******************** Relationship Methods***********************//

	@Query(value = "SELECT list from Relationship list")
	public List<Relationship> findAllRelationship();

	@Query(value = "SELECT list from Relationship list"
			+ " where list.relationshipId = :id ")
	public Relationship findRelationshipById(@Param("id") Integer relationshipId);

	@Query(value = "SELECT list from Relationship list"
			+ " left join fetch list.parentObjectDefination"
			+ " where list.parentObjectDefination.objectId = :id ", countQuery = "SELECT count(list) from Relationship list"
			+ " where list.parentObjectDefination.objectId  = :id ")
	public List<Relationship> findRelationshipByParentObjectId(
			@Param("id") Integer objectId);

	@Query(value = "SELECT list from Relationship list"
			+ " left join fetch list.relatedObjectDefination"
			+ " where list.relatedObjectDefination.objectId = :id ", countQuery = "SELECT count(list) from Relationship list"
			+ " where list.relatedObjectDefination.objectId  = :id ")
	public List<Relationship> findRelationshipByRelatedObjectId(
			@Param("id") Integer objectId);

	@Query(value = "SELECT list from Relationship list"
			+ " left join fetch list.parentAttribute"
			+ " where list.parentAttribute.attributeId = :id ", countQuery = "SELECT count(list) from Relationship list"
			+ " where list.parentAttribute.attributeId  = :id ")
	public List<Relationship> findRelationshipByParentAttributeId(
			@Param("id") Integer attributeId);

	@Query(value = "SELECT list from Relationship list"
			+ " left join fetch list.childAttribute"
			+ " where list.childAttribute.attributeId = :id ", countQuery = "SELECT count(list) from Relationship list"
			+ " where list.childAttribute.attributeId  = :id ")
	public List<Relationship> findRelationshipByChildAttributeId(
			@Param("id") Integer attributeId);

	@Query(value = "SELECT list from Relationship list"
			+ " where list.relationName = :id ", countQuery = "SELECT count(list) from Relationship list"
			+ " where list.relationName = :id ")
	public List<Relationship> findRelationshipByRelationName(
			@Param("id") String relationName);

	@Query(value = "SELECT list from Relationship list"
			+ " where list.camelcaseName = :id ", countQuery = "SELECT count(list) from Relationship list"
			+ " where list.camelcaseName = :id ")
	public List<Relationship> findRelationshipByCamelcaseName(
			@Param("id") String camelcaseName);

	@Query(value = "SELECT list from Relationship list"
			+ " where list.cardinality = :id ", countQuery = "SELECT count(list) from Relationship list"
			+ " where list.cardinality = :id ")
	public List<Relationship> findRelationshipByCardinality(
			@Param("id") String cardinality);

	// **********************************End***************************************//

}