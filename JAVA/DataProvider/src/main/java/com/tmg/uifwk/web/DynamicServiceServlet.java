package com.tmg.uifwk.web;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tmg.uifwk.codegenerator.ServiceGenerator;
import com.tmg.uifwk.orm.ObjectTree;
import com.tmg.uifwk.orm.ObjectVersion;
import com.tmg.uifwk.service.ObjectService;

@SuppressWarnings("serial")
public class DynamicServiceServlet extends CXFServlet {

	@Override
	protected void loadBus(ServletConfig config) {
		super.loadBus(config);
		Bus bus = this.getBus();
		BusFactory.setDefaultBus(bus);
		ServletContext servletContext = this.getServletContext();
		ApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		try {
			ObjectService objectService = springContext.getBean(
					"ObjectService", ObjectService.class);

			ServiceGenerator serviceGenerator = springContext.getBean(
					"ServiceGenerator", ServiceGenerator.class);

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

					String implClassName = implClass.getClass().getName();
					String serviceName = implClassName.substring(
							implClassName.lastIndexOf(".") + 1,
							implClassName.length());
					serviceName = serviceName.substring(0,
							serviceName.indexOf("Impl"));

					try {
						JaxWsServerFactoryBean serviceFactory = new JaxWsServerFactoryBean();

						serviceFactory.setBus(bus);
						serviceFactory.setAddress("/" + serviceName);

						serviceFactory.setServiceClass(implClass.getClass());
						serviceFactory.create();

					} catch (Exception e) {
						e.printStackTrace();
					}

					// serviceFactory.setAddress("/" + service);
				}

			}

			System.out.println();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}