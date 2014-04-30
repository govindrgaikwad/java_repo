package com.socman.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.socman.data.service.SocietyDataService;
import com.socman.view.dto.SocietyView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring-jpa.xml",
		"classpath*:socman-test-orm-context.xml" })
public class SimpleTest {

	@Autowired
	private SocietyDataService societyDataService;

	@Test
	public void testgetSociety() {

		SocietyView society = societyDataService.getSocietyData(1);
		System.out.println(society.getSocietyName());

	}

}