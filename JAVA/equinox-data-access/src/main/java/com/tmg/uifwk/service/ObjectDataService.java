package com.tmg.uifwk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmg.uifwk.orm.StringDataValue;
import com.tmg.uifwk.repository.AttributeRepository;
import com.tmg.uifwk.repository.ObjectDataRepository;
import com.tmg.uifwk.repository.ObjectTreeRepository;

@Service("ObjectDataService")
public class ObjectDataService {
	@Autowired
	private ObjectTreeRepository objectTreeRepository;

	@Autowired
	private AttributeRepository attributeRepository;

	@Autowired
	private ObjectDataRepository objectDataRepository;

	@Autowired
	private ObjectService objectService;

	@Transactional(readOnly = true)
	public void getSectionData(long objectInstanceId, String version) {

		List<StringDataValue> stringValues = objectDataRepository
				.getStringDataElements(objectInstanceId, version);
		
		System.out.println(stringValues.get(0).getObjectVersionAttributeMap().getObject().getName());
//		Class.forName(className)
		System.out.println(stringValues.size());

		// return objectTreeRepository.getObjectTree(rootObjectName, version);
	}
}
