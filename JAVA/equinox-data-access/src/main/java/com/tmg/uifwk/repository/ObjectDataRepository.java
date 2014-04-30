package com.tmg.uifwk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tmg.uifwk.orm.ObjectTree;
import com.tmg.uifwk.orm.StringDataValue;

public interface ObjectDataRepository extends JpaRepository<ObjectTree, Long> {

	@Query(value = "SELECT sval from "
			+ " StringDataValue sval "
			+ " join fetch sval.objectVersionAttributeMap as map "
			+ " join fetch map.attribute as attribute "
			+ " join fetch map.object as object "
			+ " where sval.objInstanceID = :objInstanceID AND map.objectVersion.versionName = :version")
	public List<StringDataValue> getStringDataElements(
			@Param("objInstanceID") Long objInstanceID,
			@Param("version") String version);

}
