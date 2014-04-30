package com.tmg.uifwk.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tmg.uifwk.orm.Attribute;
import com.tmg.uifwk.orm.ObjectTree;
import com.tmg.uifwk.orm.ObjectVersion;
import com.tmg.uifwk.service.ObjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/uifwk-data-services.xml",
		"classpath:uifwk-test-orm-context.xml" })
public class DomainModelTest {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private ObjectService objectService;

	@Test
	public void testConnection() {
		DataSource ds = context.getBean("uifwkDS", DataSource.class);

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(conn);

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Test
	public void testGetObjectTree() {

		List<ObjectTree> treeObjects = this.objectService.getObjectTree("LimitData",
				"1.0.0");

		Assert.assertEquals(3, treeObjects.size());

	}

	@Test
	public void testGetObjectAttributes() {

		List<Attribute> attributes = this.objectService.getAttributes(2,
				"1.0.0");
		Assert.assertEquals(8, attributes.size());
	}
	
	@Test
	public void testGetObjectAttributes2() {

		List<Attribute> attributes = this.objectService.getAttributes(3,
				"1.0.0");
		
//		List<Attribute> attributes = this.objectService.getAttributes();
		Assert.assertEquals(5, attributes.size());
	}

	@Test
	public void testGetRootObjects() {

		List<ObjectTree> treeObjects = this.objectService
				.getRootObjectsFromObjectTree("1.0.0");
		Assert.assertEquals(2, treeObjects.size());
	}
	
	
	@Test
	public void testGetAllVersions() {

		List<ObjectVersion> versions = this.objectService
				.getAllVersions();
		Assert.assertEquals(3, versions.size());
	}

}
