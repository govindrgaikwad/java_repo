package com.tmg.uifwk.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tmg.uifwk.codegenerator.ServiceGenerator;
import com.tmg.uifwk.dao.DataDao;
import com.tmg.uifwk.orm.ObjectTree;
import com.tmg.uifwk.service.ObjectDataService;
import com.tmg.uifwk.service.ObjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/spring/uifwk-data-services.xml",
		"classpath:uifwk-test-orm-context.xml" })
public class ObjectDataServiceTest {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private ObjectDataService objectDataService;

	@Autowired
	private ObjectService objectService;

	@Autowired
	private ServiceGenerator serviceGenerator;

	@Test
	public void testGetData() {
		String version = "1.0.0";
		List<ObjectTree> treeObjects = this.objectService
				.getRootObjectsFromObjectTree(version);

		Map<String, String> generatedSources = serviceGenerator
				.generateServiceClasses(treeObjects.get(1), version);
		List<String> compileSequence = serviceGenerator.getCompileSequence(
				treeObjects.get(1), version);
		serviceGenerator.compileJavaFiles(generatedSources, compileSequence);

		Object planData = serviceGenerator
				.getInstance("com.tmg.equinox.services.v100.plancontainer.PlanData");

		DataDao dataDao = new DataDao();
		dataDao.setServiceGenerator(serviceGenerator);
		planData = dataDao.getData(planData, version, new Long(2));

		System.out.println();

	}
	
	
//	@Test
//	public void testGetRootData() {
//		String version = "1.0.0";
//		List<ObjectTree> treeObjects = this.objectService
//				.getRootObjectsFromObjectTree(version);
//
//		Map<String, String> generatedSources = serviceGenerator
//				.generateServiceClasses(treeObjects.get(1), version);
//		List<String> compileSequence = serviceGenerator.getCompileSequence(
//				treeObjects.get(1), version);
//		serviceGenerator.compileJavaFiles(generatedSources, compileSequence);
//
//		Object planContainer = serviceGenerator
//				.getInstance("com.tmg.equinox.services.v100.plancontainer.PlanContainer");
//
//		DataDao dataDao = new DataDao();
//		dataDao.setServiceGenerator(serviceGenerator);
//		planContainer = dataDao.getData(planContainer, version, new Long(1));
//
//		System.out.println();
//	}

}
