package com.tmg.uifwk.test;

import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmg.uifwk.codegenerator.ServiceGenerator;
import com.tmg.uifwk.orm.ObjectTree;
import com.tmg.uifwk.orm.ObjectVersion;
import com.tmg.uifwk.service.ObjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/spring/uifwk-data-services.xml",
		"classpath:uifwk-test-orm-context.xml" })
public class ServiceGeneratorTest {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private ObjectService objectService;

	@Autowired
	private ServiceGenerator serviceGenerator;

	@Test
	public void testGetRootObjects() throws Exception {
		List<ObjectVersion> versions = objectService.getAllVersions();

		for (ObjectVersion objectVersion : versions) {
			String version = objectVersion.getVersionName();
			List<ObjectTree> rootTreeObjects = objectService
					.getRootObjectsFromObjectTree(version);

			for (ObjectTree rootObject : rootTreeObjects) {

				Map<String, String> generatedSources = serviceGenerator
						.generateServiceClasses(rootObject, version);
				List<String> compileSequence = serviceGenerator
						.getCompileSequence(rootObject, version);

				Object implClass = serviceGenerator.compileJavaFiles(
						generatedSources, compileSequence);
				;
				String implClassName = implClass.getClass().getName();
				String serviceName = implClassName.substring(
						implClassName.lastIndexOf(".") + 1,
						implClassName.length());
				serviceName = serviceName.substring(0,
						serviceName.indexOf("Impl"));
				System.out.println(serviceName);
			}

		}
	}

	@Test
	public void testGenerateService() {

		String version = "1.0";
		List<ObjectTree> rootTreeObjects = objectService
				.getRootObjectsFromObjectTree(version);

		for (ObjectTree rootObject : rootTreeObjects) {
			Map<String, String> generatedSources = serviceGenerator
					.generateServiceClasses(rootObject, version);
			List<String> compileSequence = serviceGenerator.getCompileSequence(
					rootObject, version);

			Object implClass = serviceGenerator.compileJavaFiles(
					generatedSources, compileSequence);

			String implClassName = implClass.getClass().getName();
			String serviceName = implClassName.substring(
					implClassName.lastIndexOf(".") + 1, implClassName.length());
			serviceName = serviceName.substring(0, serviceName.indexOf("Impl"));
			System.out.println(serviceName);
		}
	}

	@Test
	public void testGetAdminRootObject() throws Exception {
		ObjectVersion objectVersion = objectService
				.getVersionByVersionName("1.0.Anand");

		String version = objectVersion.getVersionName();
		List<ObjectTree> rootTreeObjects = objectService
				.getRootObjectsFromObjectTree(version);

		for (ObjectTree rootObject : rootTreeObjects) {

			Map<String, String> generatedSources = serviceGenerator
					.generateServiceClasses(rootObject, version);
			List<String> compileSequence = serviceGenerator.getCompileSequence(
					rootObject, version);

			Object implClass = serviceGenerator.compileJavaFiles(
					generatedSources, compileSequence);
			;
			String implClassName = implClass.getClass().getName();
			if (implClassName
					.equals("com.tmg.equinox.services.v10Anand.admin.AdminServiceImpl")) {
				Object rootObjectInstance = serviceGenerator
						.getInstance("com.tmg.equinox.services.v10Anand.admin.Admin");
				Method init = rootObjectInstance.getClass().getDeclaredMethod(
						"initialize", boolean.class);
				init.invoke(rootObjectInstance, true);
				ObjectMapper mapper = new ObjectMapper();
				StringWriter sw = new StringWriter();
				mapper.writeValue(sw, rootObjectInstance);
				System.out.println(sw.toString());

			}
			String serviceName = implClassName.substring(
					implClassName.lastIndexOf(".") + 1, implClassName.length());
			serviceName = serviceName.substring(0, serviceName.indexOf("Impl"));
			System.out.println(serviceName);
		}

	}

}
