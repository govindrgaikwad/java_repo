package com.tmg.uifwk.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tmg.uifwk.service.ObjectService;

public abstract class BaseDataDao {

	protected static ApplicationContext context;

	protected static ObjectService objectService;

	public BaseDataDao() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(
					"classpath*:/spring/uifwk-data-services.xml");
		}
		if (objectService == null) {
			objectService = context.getBean("ObjectService",
					ObjectService.class);
		}
	}
}
