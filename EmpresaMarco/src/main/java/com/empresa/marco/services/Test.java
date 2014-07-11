package com.empresa.marco.services;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.empresa.marco.exceptions.DataAccessException;

public class Test {
	public static void main(String[] args) throws DataAccessException {
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"empresa-marco.xml");
		CollectorService collectorService = ac.getBean(CollectorService.class);
		collectorService.collectObjectDefination("UIFramework", null, null,
				new String[] { "TABLE" });

		collectorService.collectEmbaddable();
		collectorService.collectAttributes();
		collectorService.collectRelationship();
	}
}
