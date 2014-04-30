package com.tmg.uifwk.codegenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmg.uifwk.orm.Attribute;
import com.tmg.uifwk.orm.ObjectDefinition;
import com.tmg.uifwk.orm.ObjectRelation;
import com.tmg.uifwk.orm.ObjectTree;
import com.tmg.uifwk.service.ObjectService;

@Component(value = "ServiceGenerator")
public class ServiceGenerator {

	@Autowired
	private ObjectService objectService;

	private JavaFileManager fileManager;

	public Map<String, String> generateServiceClasses(ObjectTree rootObject,
			String version) {
		List<ObjectTree> allObjectsInTree = objectService.getObjectTree(
				rootObject.getRootObject().getName(), version);
		init();
		Map<String, String> generatedSources = new HashMap<String, String>();
		Map<String, String> dataObjectSources = generateDataObjects(
				allObjectsInTree, version);
		generatedSources.putAll(dataObjectSources);
		Map<String, String> serviceSources = generateInterfaceAndImpl(
				allObjectsInTree, version);
		generatedSources.putAll(serviceSources);

		/**** The following code should be uncommented for DEBUG purposes ONLY *****/
		generateJavaFiles(generatedSources);

		return generatedSources;
		// compileJavaFiles(generatedSources);

	}

	public List<String> getCompileSequence(ObjectTree rootTreeObject,
			String version) {

		List<ObjectTree> allObjectsInTree = objectService.getObjectTree(
				rootTreeObject.getRootObject().getName(), version);
		List<String> compileSequence = new ArrayList<String>();

		String baseString = "com.tmg.equinox.services.v";
		version = version.replace(".", "");

		ObjectDefinition rootObject = allObjectsInTree.get(0).getRootObject();
		String rootObjectName = rootObject.getName();

		String serviceName = baseString + version + "."
				+ rootObjectName.toLowerCase() + "." + rootObjectName
				+ "Service";
		compileSequence.add(serviceName);
		String serviceImpl = baseString + version + "."
				+ rootObjectName.toLowerCase() + "." + rootObjectName
				+ "ServiceImpl";
		compileSequence.add(serviceImpl);

		compileSequence.add(0,
				baseString + version + "." + rootObjectName.toLowerCase() + "."
						+ rootObjectName);

		getChildrenInList(compileSequence, rootObject.getObjectId(),
				allObjectsInTree);
		return compileSequence;
	}

	private void getChildrenInList(List<String> compileSequence, Long objectId,
			List<ObjectTree> allObjectsInTree) {
		String baseString = "com.tmg.equinox.services.v";
		String version = allObjectsInTree.get(0).getObjectVersion()
				.getVersionName();
		version = version.replace(".", "");
		ObjectDefinition rootObject = allObjectsInTree.get(0).getRootObject();
		String rootObjectName = rootObject.getName();

		for (ObjectTree objectTree : allObjectsInTree) {
			if (objectTree.getParentObject() != null
					&& objectTree.getParentObject().getObjectId() == objectId) {
				ObjectDefinition relatedObject = objectTree.getRelation()
						.getRelatedObject();
				String name = relatedObject.getName();
				compileSequence.add(0, baseString + version + "."
						+ rootObjectName.toLowerCase() + "." + name);
				getChildrenInList(compileSequence, relatedObject.getObjectId(),
						allObjectsInTree);
			}
		}

	}

	public Object compileJavaFiles(Map<String, String> generatedSources,
			List<String> compileSequence) {

		Object result = null;

		String implClassName = null;

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		
		fileManager = new ClassFileManager(compiler.getStandardFileManager(
				null, null, null));
		ClassFileManager classFileManager = (ClassFileManager) fileManager;
		classFileManager.setClassLoadSequence(compileSequence);
		List<JavaFileObject> jfiles = new ArrayList<JavaFileObject>();

		for (String qualifiedClassName : generatedSources.keySet()) {
			if (qualifiedClassName.endsWith("Impl")) {
				implClassName = qualifiedClassName;
			}
			String javaCode = generatedSources.get(qualifiedClassName);
			jfiles.add(new JavaSourceFromString(qualifiedClassName, javaCode));
		}

		String calculatedClassPath = getClassPath();
		
		List<String> optionList = new ArrayList<String>();
		// set compiler's classpath to be same as the runtime's
		optionList.addAll(Arrays.asList("-classpath", calculatedClassPath));

		// We specify a task to the compiler. Compiler should use our file
		// manager and our list of "files".
		// Then we run the compilation with call()
		boolean compilationStatus = compiler.getTask(null, fileManager, null,
				optionList, null, jfiles).call();
		if (compilationStatus == false) {
			System.out.println("ERROR: compilation failed ");
		}

		try {
			Class<? extends Object> clazz = fileManager.getClassLoader(null)
					.loadClass(implClassName);
			result = clazz.newInstance();

			Method setServiceGenerator = clazz.getMethod("setServiceGenerator",
					ServiceGenerator.class);
			setServiceGenerator.invoke(result, this);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	private String getClassPath() {
		URL classLocationURL = getClass().getProtectionDomain().getCodeSource()
				.getLocation();
		File f = null;
		try {
			f = new File(classLocationURL.getFile());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		}
		String classLocation = f.getAbsolutePath();
//		classLocation = classLocation.substring(0,
//				classLocation.indexOf("lib"));
//		classLocation = classLocation + "classes/";

		String baseClassPath = System.getProperty("java.class.path");
		String calculatedClassPath = baseClassPath + ";" + classLocation;
		return calculatedClassPath;
	}

	private void generateJavaFiles(Map<String, String> generatedSources) {

		File baseFolder = new File("./gen");
		for (String key : generatedSources.keySet()) {

			String className = key.substring(key.lastIndexOf(".") + 1,
					key.length());
			String basePackage = key.substring(0, key.lastIndexOf("."));
			String otherFolders = basePackage.replace(".", "/");
			File dirs = new File(baseFolder, otherFolders);
			if (!dirs.exists()) {
				dirs.mkdirs();
			}

			String source = generatedSources.get(key);
			File javaFile = new File(dirs, className + ".java");

			try {
				FileOutputStream fos = new FileOutputStream(javaFile);
				fos.write(source.getBytes());
				fos.flush();
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	private Map<String, String> generateInterfaceAndImpl(
			List<ObjectTree> allObjectsInTree, String version) {

		Map<String, String> generatedClassesMap = new HashMap<String, String>();

		StringWriter sw = new StringWriter();

		VelocityContext context = new VelocityContext();
		String basePackage = "com.tmg.equinox.services";

		context.put("VersionString", version);
		version = version.replaceAll("\\.", "");
		context.put("BasePackage", basePackage);
		context.put("Version", version);

		List<ServiceMethod> serviceMethods = new ArrayList<ServiceMethod>();
		for (ObjectTree objectTree : allObjectsInTree) {

			ObjectDefinition me = null;
			if (objectTree.getParentObject() == null) {
				me = objectTree.getRootObject();
				String objectName = me.getName();
				String serviceName = objectName + "Service";
				context.put("ClassName", serviceName);

				context.put("PackageName", objectName.toLowerCase());
				context.put("Namespace", "http://equinox.tmg.com/services/v"
						+ version + "/" + objectName);
				ServiceMethod method = new ServiceMethod();
				method.setName("Get" + objectName);
				method.setReturnType(objectName);
				List<String> params = new ArrayList<String>();
				params.add(objectName + "ID");
				method.setParams(params);
				serviceMethods.add(method);
			} else {
				me = objectTree.getRelation().getRelatedObject();
				String objectName = me.getName();
				ServiceMethod method = new ServiceMethod();
				String methodName = "Get" + objectTree.getRelation().getRelationName();
				method.setName("Get" + methodName);
				method.setReturnType(objectName);
				List<String> params = new ArrayList<String>();
				params.addAll(getParents(me.getObjectId(), allObjectsInTree));
				params.add(objectName + "ID");
				method.setParams(params);

				serviceMethods.add(method);
			}
		}

		context.put("Methods", serviceMethods);
		try {
			Template template = Velocity
					.getTemplate("/templates/service-interface-template.vm");
			template.merge(context, sw);

			sw.flush();
			sw.close();

			String fullyQualifiedName = basePackage + ".v" + version + "."
					+ context.get("PackageName") + "."
					+ context.get("ClassName");
			generatedClassesMap.put(fullyQualifiedName, sw.toString());

			sw = new StringWriter();
			template = Velocity
					.getTemplate("/templates/service-impl-template.vm");
			template.merge(context, sw);

			sw.flush();
			sw.close();
			fullyQualifiedName = basePackage + ".v" + version + "."
					+ context.get("PackageName") + "."
					+ context.get("ClassName") + "Impl";
			generatedClassesMap.put(fullyQualifiedName, sw.toString());

		} catch (IOException e) {
			// TODO Log This
			e.printStackTrace();
		}

		return generatedClassesMap;
	}

	private Set<String> getParents(Long objectId,
			List<ObjectTree> allObjectsInTree) {

		Set<String> result = new HashSet<String>();

		for (ObjectTree objectTree : allObjectsInTree) {
			if (objectTree.getRelation() != null) {
				ObjectDefinition me = objectTree.getRelation()
						.getRelatedObject();
				if (me.getObjectId() == objectId) {
					ObjectDefinition parent = objectTree.getParentObject();
					result.addAll(getParents(parent.getObjectId(),
							allObjectsInTree));
					result.add(parent.getName() + "ID");
				}
			}
		}

		return result;
	}

	private Map<String, String> generateDataObjects(
			List<ObjectTree> allObjectsInTree, String version) {

		Map<String, String> generatedClassesMap = new HashMap<String, String>();

		for (ObjectTree objectTree : allObjectsInTree) {
			String className = null;
			List<Attribute> objectAttributes;

			ObjectDefinition me = null;

			if (objectTree.getParentObject() == null) {
				me = objectTree.getRootObject();
			} else {
				me = objectTree.getRelation().getRelatedObject();
			}
			className = me.getName();
			objectAttributes = objectService.getAttributes(me.getObjectId(),
					version);

			Map<String, String> generatedClass = generateClassFromTemplate(
					version, className, objectAttributes, allObjectsInTree);
			generatedClassesMap.putAll(generatedClass);

		}
		return generatedClassesMap;
	}

	private Map<String, String> generateClassFromTemplate(String version,
			String className, List<Attribute> attributes,
			List<ObjectTree> allObjectsInTree) {

		Map<String, String> result = new HashMap<String, String>();

		StringWriter sw = new StringWriter();

		VelocityContext context = new VelocityContext();
		String basePackage = "com.tmg.equinox.services";
		version = version.replaceAll("\\.", "");
		context.put("BasePackage", basePackage);
		context.put("Version", version);
		context.put("ClassName", className);
		context.put("Attributes", attributes);

		List<RelationShip> relationShips = new ArrayList<RelationShip>();
		for (ObjectTree objectTree : allObjectsInTree) {
			if (objectTree.getParentObject() != null) {
				if (objectTree.getParentObject().getName()
						.equalsIgnoreCase(className)) {
					ObjectRelation relation = objectTree.getRelation();
					String name = relation.getRelationName();
					String type = relation.getRelatedObject().getName();
					String cardinality = relation.getCardinality();
					RelationShip relationShip = new RelationShip(type, name,
							cardinality);
					relationShips.add(relationShip);
				}
			} else {
				String packageName = objectTree.getRootObject().getName();
				context.put("PackageName", packageName.toLowerCase());
				context.put("Namespace", "http://equinox.tmg.com/services/v"
						+ version + "/" + packageName);
			}
		}

		context.put("Relationships", relationShips);

		try {
			Template template = Velocity
					.getTemplate("/templates/class-template.vm");

			template.merge(context, sw);

			sw.flush();
			sw.close();
		} catch (IOException e) {
			// TODO Log This
			e.printStackTrace();
		}

		String fullyQualifiedName = basePackage + ".v" + version + "."
				+ context.get("PackageName") + "." + className;
		result.put(fullyQualifiedName, sw.toString());

		return result;
	}

	private void init() {
		Properties p = new Properties();

		// Cannot find template with this:
		p.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		p.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());

		Velocity.init(p);

	}

	public Class<? extends Object> getClassOf(String className)
			throws ClassNotFoundException {
		return fileManager.getClassLoader(null).loadClass(className);
	}

	public Object getInstance(String className) {
		Object result = null;
		try {
			Class<? extends Object> clazz = getClassOf(className);
			result = clazz.newInstance();
		} catch (Exception e) {
		}

		return result;
	}

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public JavaFileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(JavaFileManager fileManager) {
		this.fileManager = fileManager;
	}

}
