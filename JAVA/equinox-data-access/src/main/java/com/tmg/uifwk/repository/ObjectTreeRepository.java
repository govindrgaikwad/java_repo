package com.tmg.uifwk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tmg.uifwk.orm.ObjectTree;
import com.tmg.uifwk.orm.ObjectVersion;

public interface ObjectTreeRepository extends JpaRepository<ObjectTree, Long> {

	@Query(value = "SELECT tree from "
			+ " ObjectTree tree "
			+ " join fetch tree.rootObject as rootObject "
			+ " where rootObject.name = :rootObjectName AND tree.objectVersion.versionName = :version")
	public List<ObjectTree> getObjectTree(
			@Param("rootObjectName") String rootObjectName,
			@Param("version") String version);

	@Query(value = "SELECT tree from "
			+ " ObjectTree tree "
			+ " join fetch tree.rootObject as rootObject "
			+ " where rootObject.objectID = :rootObjectID AND tree.objectVersion.versionName = :version")
	public List<ObjectTree> getObjectTreeByRootID(
			@Param("rootObjectID") long rootObjectID,
			@Param("version") String version);

	@Query(value = "SELECT tree from "
			+ " ObjectTree tree "
			+ " join fetch tree.rootObject "
			+ " left outer join fetch tree.parentObject "
			+ " left outer join fetch tree.relation "
			+ " where tree.parentObject IS NULL AND tree.objectVersion.versionName = :version")
	public List<ObjectTree> getRootObjectsFromObjectTree(
			@Param("version") String version);

	@Query(value = "SELECT version from " + " ObjectVersion version ")
	public List<ObjectVersion> getAllVersions();

	@Query(value = "SELECT tree from "
			+ " ObjectTree tree "
			+ " join fetch tree.rootObject "
			+ " left outer join fetch tree.parentObject "
			+ " left outer join fetch tree.relation relation"
			+ " where relation.relatedObject.name = :objectName AND tree.objectVersion.versionName = :version")
	public ObjectTree getObjectTreeForObject(
			@Param("objectName") String objectName,
			@Param("version") String version);

	@Query(value = "SELECT version from ObjectVersion version "
			+ "WHERE version.versionName = :versionName")
	public ObjectVersion getVersionByVersionName(
			@Param("versionName") String versionName);

}
