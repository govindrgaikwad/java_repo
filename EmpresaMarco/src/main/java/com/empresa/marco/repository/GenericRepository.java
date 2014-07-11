package com.empresa.marco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.marco.entity.Attribute;
import com.empresa.marco.entity.DataType;
import com.empresa.marco.entity.Embaddable;
import com.empresa.marco.entity.ObjectDefination;
import com.empresa.marco.entity.Project;
import com.empresa.marco.entity.ProjectVersion;
import com.empresa.marco.entity.Relationship;

public interface GenericRepository extends
		JpaRepository<ObjectDefination, Long>, GenericCustomRepository {

	// **************Attribute Methods *****************//
	@Query(value = "SELECT list from Attribute list"
			+ " where list.attributeId = :id ")
	public Attribute findAttributeById(@Param("id") Integer attributeId);

	@Query(value = "SELECT list from Attribute list"
			+ " left join fetch list.project"
			+ " left join fetch list.projectVersion"
			+ " where list.project.projectId = :projectId"
			+ " And list.projectVersion.projectVersionId = :projectVersionId", countQuery = "SELECT count(list) from Attribute list"
			+ " where list.project.projectId = :projectId"
			+ " And list.projectVersion.projectVersionId = :projectVersionId")
	public List<Attribute> findAttributeByProjectVersion(
			@Param("projectId") Integer projectId,
			@Param("projectVersionId") Integer projectVersionId);

	@Query(value = "SELECT list from Attribute list"
			+ " left join fetch list.objectDefination"
			+ " left join fetch list.project"
			+ " left join fetch list.projectVersion"
			+ " where list.objectDefination.objectId = :objectId "
			+ " And list.project.projectId = :projectId"
			+ " And list.projectVersion.projectVersionId = :projectVersionId", countQuery = "SELECT count(list) from Attribute list"
			+ " where list.objectDefination.objectId = :objectId "
			+ " And list.project.projectId = :projectId"
			+ " And list.projectVersion.projectVersionId = :projectVersionId")
	public List<Attribute> findAttributeByObjectId(
			@Param("objectId") Integer objectId,
			@Param("projectId") Integer projectId,
			@Param("projectVersionId") Integer projectVersionId);

	@Query(value = "SELECT list from Attribute list"
			+ " left join fetch list.objectDefination"
			+ " left join fetch list.project"
			+ " left join fetch list.projectVersion"
			+ " where list.name = :name"
			+ " And list.objectDefination.objectId = :objectId "
			+ " And list.project.projectId = :projectId"
			+ " And list.projectVersion.projectVersionId = :projectVersionId", countQuery = "SELECT count(list) from Attribute list"
			+ " where list.name = :name"
			+ " And list.objectDefination.objectId = :objectId "
			+ " And list.project.projectId = :projectId"
			+ " And list.projectVersion.projectVersionId = :projectVersionId")
	public Attribute findAttributeByNameAndObjectId(@Param("name") String name,
			@Param("objectId") Integer objectId,
			@Param("projectId") Integer projectId,
			@Param("projectVersionId") Integer projectVersionId);

	// **************DataType Methods *****************//
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
	public DataType findDataTypeBySQLType(@Param("id") String sQLType);

	@Query(value = "SELECT list from DataType list"
			+ " where list.javaType = :id ", countQuery = "SELECT count(list) from DataType list"
			+ " where list.javaType = :id ")
	public DataType findDataTypeByJavaType(@Param("id") String javaType);

	// *************** Embaddable Methods ******************//

	@Query(value = "SELECT list from Embaddable list"
			+ " left join fetch list.project"
			+ " left join fetch list.projectVersion"
			+ " where list.project.projectId= :projectId"
			+ " And list.projectVersion.projectVersionId=:projectVersionId")
	public List<Embaddable> findAllEmbaddable(
			@Param("projectId") Integer projectId,
			@Param("projectVersionId") Integer projectVersionId);

	@Query(value = "SELECT list from Embaddable list"
			+ " where list.embaddableId = :id ")
	public Embaddable findEmbaddableById(@Param("id") Integer embaddableId);

	@Query(value = "SELECT list from Embaddable list"
			+ " left join fetch list.objectDefination"
			+ " where list.objectDefination.objectId = :id ", countQuery = "SELECT count(list) from Embaddable list"
			+ " where list.objectDefination.objectId  = :id ")
	public Embaddable findEmbaddableByObjectId(@Param("id") Integer objectId);

	// *************** Relationship Methods ******************//

	@Query(value = "SELECT list from Relationship list"
			+ " left join fetch list.project"
			+ " left join fetch list.projectVersion"
			+ " where list.project.projectId= :projectId"
			+ " And list.projectVersion.projectVersionId=:projectVersionId")
	public List<Relationship> findAllRelationship(
			@Param("projectId") Integer projectId,
			@Param("projectVersionId") Integer projectVersionId);

	@Query(value = "SELECT list from Relationship list"
			+ " where list.relationshipId = :id ")
	public Relationship findRelationshipById(@Param("id") Integer relationshipId);

	@Query(value = "SELECT list from Relationship list"
			+ " left join fetch list.objectDefination"
			+ " left join fetch list.project"
			+ " left join fetch list.projectVersion"
			+ " where list.objectDefination.objectId = :id "
			+ " And list.project.projectId= :projectId"
			+ " And list.projectVersion.projectVersionId = :projectVersionId", countQuery = "SELECT count(list) from Relationship list"
			+ " where list.objectDefination.objectId  = :id "
			+ " And list.project.projectId= :projectId"
			+ " And list.projectVersion.projectVersionId = :projectVersionId")
	public List<Relationship> findRelationshipByParentObjectId(
			@Param("id") Integer objectId,
			@Param("projectId") Integer projectId,
			@Param("projectVersionId") Integer projectVersionId);

	// *************ObjectDefination Methods ****************//

	@Query(value = "SELECT list from ObjectDefination list"
			+ " where list.objectId = :id ")
	public ObjectDefination findObjectDefinationById(Integer objectId);

	@Query(value = "SELECT list from ObjectDefination list"
			+ " left join fetch list.project"
			+ " left join fetch list.projectVersion"
			+ " where list.project.projectId = :projectId "
			+ " And list.projectVersion.projectVersionId = :projectVersionId", countQuery = "SELECT count(list) from ObjectDefination list"
			+ " where list.project.projectId  = :projectId "
			+ " And list.projectVersion.projectVersionId = :projectVersionId")
	public List<ObjectDefination> findAllObjectDefination(
			@Param("projectId") Integer projectId,
			@Param("projectVersionId") Integer projectVersionId);

	@Query(value = "SELECT list from ObjectDefination list"
			+ " left join fetch list.project"
			+ " left join fetch list.projectVersion"
			+ " where list.name = :name"
			+ " And list.project.projectId = :projectId "
			+ " And list.projectVersion.projectVersionId = :projectVersionId", countQuery = "SELECT count(list) from ObjectDefination list"
			+ " where list.name = :name"
			+ " And list.project.projectId = :projectId "
			+ " And list.projectVersion.projectVersionId = :projectVersionId")
	public ObjectDefination findObjectDefinationByName(
			@Param("name") String name, @Param("projectId") Integer projectId,
			@Param("projectVersionId") Integer projectVersionId);

	@Query(value = "SELECT list from ObjectDefination list"
			+ " left join fetch list.project"
			+ " left join fetch list.projectVersion"
			+ " where list.embaddable = :embaddable"
			+ " And list.project.projectId = :projectId "
			+ " And list.projectVersion.projectVersionId = :projectVersionId", countQuery = "SELECT count(list) from ObjectDefination list"
			+ " where list.embaddable = :embaddable"
			+ " And list.project.projectId = :projectId "
			+ " And list.projectVersion.projectVersionId = :projectVersionId")
	public List<ObjectDefination> findObjectDefinationByEmbaddable(
			@Param("embaddable") Boolean embaddable,
			@Param("projectId") Integer projectId,
			@Param("projectVersionId") Integer projectVersionId);

	// *********** Project Methods *****************//

	@Query(value = "SELECT list from Project list"
			+ " where list.projectId = :id ")
	public Project findProjectById(@Param("id") Integer projectId);

	@Query(value = "SELECT list from ProjectVersion list"
			+ " where list.projectVersionId = :id ")
	public ProjectVersion findProjectVersionById(
			@Param("id") Integer projectVersionId);
}