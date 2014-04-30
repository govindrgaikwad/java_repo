package com.tmg.uifwk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmg.uifwk.orm.Attribute;
import com.tmg.uifwk.orm.ObjectTree;
import com.tmg.uifwk.orm.ObjectVersion;
import com.tmg.uifwk.repository.AttributeRepository;
import com.tmg.uifwk.repository.ObjectTreeRepository;

@Service("ObjectService")
public class ObjectService {
	@Autowired
	private ObjectTreeRepository objectTreeRepository;

	@Autowired
	private AttributeRepository attributeRepository;

	@Transactional(readOnly = true)
	public List<ObjectTree> getObjectTree(String rootObjectName, String version) {
		return objectTreeRepository.getObjectTree(rootObjectName, version);
	}

	@Transactional(readOnly = true)
	public List<Attribute> getAttributes(long objectId, String version) {
		return attributeRepository.getObjectAttributes(objectId, version);
	}

	@Transactional(readOnly = true)
	public List<ObjectTree> getObjectTreeByRootID(long rootObjectID,
			String version) {
		return objectTreeRepository
				.getObjectTreeByRootID(rootObjectID, version);
	}

	@Transactional(readOnly = true)
	public ObjectTree getObjectTreeForObject(String objectName, String version) {
		return objectTreeRepository.getObjectTreeForObject(objectName, version);
	}

	@Transactional(readOnly = true)
	public List<ObjectTree> getRootObjectsFromObjectTree(String version) {
		return objectTreeRepository.getRootObjectsFromObjectTree(version);
	}

	@Transactional(readOnly = true)
	public List<ObjectVersion> getAllVersions() {
		return objectTreeRepository.getAllVersions();
	}

	@Transactional(readOnly = true)
	public ObjectVersion getVersionByVersionName(String versionName) {
		return objectTreeRepository.getVersionByVersionName(versionName);
	}

}
