package com.tmg.uifwk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tmg.uifwk.orm.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {

	@Query(value = "SELECT attribute from " + "Attribute attribute "
			+ " join attribute.attributeMap as attributeMap "
			+ " where attributeMap.object.objectID = :OID AND "
			+ "attributeMap.objectVersion.versionName = :version")
	public List<Attribute> getObjectAttributes(@Param("OID") long OID,
			@Param("version") String version);	

}
