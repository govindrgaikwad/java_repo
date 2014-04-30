package com.tmg.uifwk.test.ui;

import java.io.StringWriter;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmg.equinox.view.formdesign.UIElementRowModel;
import com.tmg.uifwk.orm.ui.UIElement;
import com.tmg.uifwk.service.ui.ImportExportDataService;
import com.tmg.uifwk.service.ui.UIDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/spring/uifwk-data-services.xml",
		"classpath:uifwk-test-orm-context.xml" })
public class UIServiceTest {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private UIDataService UIDataService;

	@Autowired
	private ImportExportDataService ImportExportDataService;

	// @Test
	// public void testGetUIElementNonDeep() {
	// //
	// UIElement rootElement = this.UIDataService.getUIElement(1, true);
	//
	// System.out.println(rootElement);
	//
	// // Assert.assertEquals(1, textBoxElements.size());
	//
	// }
	//
	// @Test
	// public void testGetUIElementListForFormDesignVersion() throws Exception {
	//
	// List<UIElementRowModel> rootElement = this.UIDataService
	// .getUIElementListForFormDesignVersion(1, 1);
	// ObjectMapper mapper = new ObjectMapper();
	// StringWriter sw = new StringWriter();
	// mapper.writeValue(sw, rootElement);
	// System.out.println(sw.toString());
	//
	// for (int i = 0; i < 20; i++) {
	// this.UIDataService.getUIElementListForFormDesignVersion(1, 1);
	// }
	//
	// }
	//
//	@Test
//	public void testGetChangedUIElementListForFormDesignVersion()
//			throws Exception {
//		ImportExportDataService.getExportToExcelSheet();
//	}

	@Test
	public void testExportFromExcelAndImportToDB() throws Exception {

		ImportExportDataService.getImportFromExcelSheet();

	}

}
