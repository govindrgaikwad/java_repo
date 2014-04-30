package com.tmg.ebscore.orm.masterlist;

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

import com.tmg.ebscore.dto.ebspackage.BenifitPackageData;
import com.tmg.ebscore.dto.template.PkgVersionWorkflowStateData;
import com.tmg.ebscore.dto.template.TemplateAllowsIntValData;
import com.tmg.ebscore.dto.template.TemplateAllowsLimitValData;
import com.tmg.ebscore.dto.template.TemplateAllowsMessageValData;
import com.tmg.ebscore.dto.template.TemplateAllowsServiceDefinitionData;
import com.tmg.ebscore.dto.template.TemplateAllowsStringValData;
import com.tmg.ebscore.dto.template.TemplateContainer;
import com.tmg.ebscore.dto.template.TemplateSummaryData;
import com.tmg.ebscore.dto.template.TemplateTreeData;
import com.tmg.ebscore.dto.template.TemplateWorkFlowStateData;
import com.tmg.ebscore.orm.DataAccessException;
import com.tmg.ebscore.orm.template.TemplateWorkflowState;
import com.tmg.ebscore.service.TemplateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:core-data-services.xml",
		"classpath:ebscore-test-orm-context.xml" })
public class TemplateTest {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private TemplateService templateService;

	@Test
	public void testConnection() {
		DataSource ds = context.getBean("coreDS", DataSource.class);

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
	public void testGetTemplateList() throws DataAccessException {

		TemplateContainer<TemplateSummaryData> templateContainer = templateService
				.getTemplateList(0, 100);
		Assert.assertEquals(17, templateContainer.getData().size());

	}

	@Test
	public void testGetTemplateWorkFlowList() throws DataAccessException {

		TemplateContainer<TemplateWorkFlowStateData> templateContainer = templateService
				.getTemplateWorkFlowStateListAtTemplate(0, 20);
		Assert.assertEquals(6, templateContainer.getData().size());

	}

	@Test
	public void getNoOfPackage() throws DataAccessException {

		int result = 0;

		result = templateService.getNoOfPackages(123);

		System.out.println(result);
	}

	@Test
	public void getPackagesForTemplate() throws DataAccessException {
		TemplateContainer<BenifitPackageData> templateContainer = templateService
				.getPackagesForTemplate(123, 0, 100);

		Assert.assertEquals(29, templateContainer.getData().size());
	}

	@Test
	public void getUnreleasedPckVer() throws DataAccessException {
		int count = templateService.getNoOfUnreleasedPackageVersions(123);
		System.out.println(count);
	}

	@Test
	public void getReleasedPckVer1() throws DataAccessException {
		int count = templateService.getNoOfReleasedPackageVersions(17);
		System.out.println(count);
	}

	@Test
	public void testisTemplateLocked() throws DataAccessException {

		boolean a = templateService.isLockedTemplate(123);
		System.out.println(a);
	}

	@Test
	public void getBenefitPackageType() throws DataAccessException {
		List<String> type = templateService.getBenefitPackageType(16);
		for (String string : type) {
			System.out.println(string);
		}
	}

	@Test
	public void updateWorkflowStateTest() throws DataAccessException {
		templateService.changeWorkFlowState(4, 123);
	}

	@Test
	public void findTemplateDetails() throws DataAccessException {
		templateService.getTemplateDetail(123);

	}

	@Test
	public void findTemplateDetail() throws DataAccessException {
		TemplateWorkflowState data = templateService
				.getTemplateWorkFlowStateById(4);
		System.out.println(data);
	}

	@Test
	public void testGetTemplateAllowsIntValList() throws DataAccessException {

		TemplateContainer<TemplateAllowsIntValData> templateContainer = templateService
				.getIntValListForTemplate(34, 123, 0, 40);
		Assert.assertEquals(4, templateContainer.getData().size());
	}

	@Test
	public void testGetTemplateAllowsStringValList() throws DataAccessException {

		TemplateContainer<TemplateAllowsStringValData> templateContainer = templateService
				.getStringValListForTemplate(12, 123, 0, 200);
		Assert.assertEquals(64, templateContainer.getData().size());
	}

	@Test
	public void getWorkFlowStateForEachTemplate() throws DataAccessException {

		TemplateContainer<PkgVersionWorkflowStateData> templateContainer = templateService
				.getWorkFlowStateForEachTemplate(123, 0, 100);
		System.out.println(templateContainer.getData().size());
		Assert.assertEquals(6, templateContainer.getData().size());
	}

	@Test
	public void testGetTemplateAllowsServiceDifinitionList()
			throws DataAccessException {

		TemplateContainer<TemplateAllowsServiceDefinitionData> templateContainer = templateService
				.getAllowServiceDefinitionForTemplate(123, 0, 1000);
		Assert.assertEquals(763, templateContainer.getData().size());
	}

	@Test
	public void getServiceDefinitionValListforTemplateTest()
			throws DataAccessException {
		TemplateContainer<TemplateAllowsServiceDefinitionData> templateContainer = templateService
				.getServiceDefinitionValListForTemplate(31, 123, 0, 1000);
		Assert.assertEquals(764, templateContainer.getData().size());
	}

	@Test
	public void getLimitValList() throws DataAccessException {

		TemplateContainer<TemplateAllowsLimitValData> templateContainer = templateService
				.getLimitValListForTemplate(23, 123, 0, 2000);
		Assert.assertEquals(745, templateContainer.getData().size());
	}

	@Test
	public void testGetTemplateAllowsIntValListS() throws DataAccessException {

		TemplateContainer<TemplateAllowsIntValData> templateContainer = templateService
				.getAllowIntValForTemplate(123, 0, 200);
		Assert.assertEquals(42, templateContainer.getData().size());

	}

	public void testGetTemplateAllowsStringValListS()
			throws DataAccessException {
		TemplateContainer<TemplateAllowsStringValData> templateContainer = templateService
				.getAllowStringValForTemplate(123, 0, 200);
		Assert.assertEquals(12, templateContainer.getData().size());
	}

	@Test
	public void testGetTemplateAllowsLimitValList() throws DataAccessException {

		TemplateContainer<TemplateAllowsLimitValData> templateContainer = templateService
				.getAllowsLimitForTemplate(123, 0, 100);
		Assert.assertEquals(12, templateContainer.getData().size());
	}

	@Test
	public void testGetTemplateAllowsMessageValList()
			throws DataAccessException {

		TemplateContainer<TemplateAllowsMessageValData> templateContainer = templateService
				.getAllowMessageValForTemplate(123, 0, 200);
		Assert.assertEquals(11, templateContainer.getData().size());
	}

	@Test
	public void testGetTemplateAllowsServiceDifinitionLists()
			throws DataAccessException {

		TemplateContainer<TemplateAllowsServiceDefinitionData> templateContainer = templateService
				.getAllowServiceDefinitionForTemplate(123, 0, 1000);
		Assert.assertEquals(763, templateContainer.getData().size());
	}

	@Test
	public void getMessageValList() throws DataAccessException {

		TemplateContainer<TemplateAllowsMessageValData> templateContainer = templateService
				.getMessageValListForTemplate(20, 123, 0, 200);
		Assert.assertEquals(196, templateContainer.getData().size());
	}

	@Test
	public void getAllTemplateTreeList() throws DataAccessException {

		TemplateContainer<TemplateTreeData> templateContainer = templateService
				.getAllTemplateTreeForTemplate(123, 0, 10);
		Assert.assertEquals(2, templateContainer.getData().size());
	}

	// @Test
	// public void saveTemplateGeneralInfo() throws DataAccessException {
	// PackageTemplateData pkgData = new PackageTemplateData();
	//
	// pkgData.setBenefitPackageTypeAsString1up(136);
	// pkgData.setCreateBy("Ranjit");
	// pkgData.setTemplateID("Ranjit");
	// pkgData.setTemplateName("Ranjit");
	// pkgData.setUpdateBy("Ranjit");
	// pkgData.setDescription("final");
	// pkgData.seteBSInstance1up(1);
	// pkgData.setTemplateWorkflowState1up(5);
	//
	// ServiceOperationResult operationResult = templateService
	// .saveUpdateTemplate(pkgData);
	//
	// System.out.println(operationResult);
	// System.out.println("hi");
	//
	// }

	// @Test
	// public void copyTemplate() throws DataAccessException {
	//
	// PackageTemplateData pkgData = new PackageTemplateData();
	//
	// pkgData.setBenefitPackageTypeAsString1up(133);
	// pkgData.seteBSInstance1up(1);
	// pkgData.setTemplateID("opsd");
	// pkgData.setTemplateName("opsd");
	// pkgData.setDescription("opsd");
	// pkgData.setTemplateWorkflowState1up(3);
	// pkgData.setPackageTemplate1Up(123);
	// pkgData.setCreateBy("opsd");
	//
	// templateService.saveTemplateFromExistingTemplate(pkgData, true, true,
	// true);
	// }

	// @Test
	// public void saveAndUpdatePkgVerWorkflowStateForTemplate()
	// throws DataAccessException {
	// List<PkgVersionWorkflowStateData> workFlowStateData = new
	// ArrayList<PkgVersionWorkflowStateData>();
	// PkgVersionWorkflowStateData pkgVersionWorkflowStateData = new
	// PkgVersionWorkflowStateData();
	// pkgVersionWorkflowStateData.seteBSInstance1up(1);
	// pkgVersionWorkflowStateData.setPackageTemplate1Up(123);
	// pkgVersionWorkflowStateData.setName("POSd774");
	// pkgVersionWorkflowStateData.setDescription("posd774");
	// pkgVersionWorkflowStateData.setReleasedFlag(true);
	// pkgVersionWorkflowStateData.setDefaultValFlag(true);
	// pkgVersionWorkflowStateData.setDisplayOrder(6);
	// pkgVersionWorkflowStateData.setCreateBy("TMG");
	//
	// workFlowStateData.add(pkgVersionWorkflowStateData);
	//
	// templateService
	// .saveAndUpdatePkgVerWorkflowStateForTemplate(workFlowStateData);
	//
	// }

	// @Test
	// public void saveAllowMessageValById() throws DataAccessException {
	// List<TemplateAllowsMessageValData> allowMessageValList = new
	// ArrayList<TemplateAllowsMessageValData>();
	// TemplateAllowsMessageValData data = new TemplateAllowsMessageValData();
	// data.setMasterListMessageVal1up(20);
	// data.seteBSInstance1up(1);
	// data.setCreateBy("Apu");
	// allowMessageValList.add(data);
	// templateService.saveAndDeleteAllowMessageValForTemplate(
	// allowMessageValList, 143);
	// }

	// public void saveAllowIntValForTempl() throws DataAccessException {
	//
	// System.out.println("Updated.");
	//
	// List<TemplateAllowsIntValData> list = new
	// ArrayList<TemplateAllowsIntValData>();
	// TemplateAllowsIntValData data = new TemplateAllowsIntValData();
	// data.setMasterListIntVal1up(35);
	// data.seteBSInstance1up(1);
	// data.setCreateBy("Ranjit");
	// list.add(data);
	// System.out.println(templateService
	// .saveUpdateAndDeleteAllowIntValForTemplate(list, 123, 34));
	//
	// }

	// @Test
	// public void saveTemplateAllowServiceDefinitionTest()
	// throws DataAccessException {
	// List<TemplateAllowsServiceDefinitionData> list = new
	// ArrayList<TemplateAllowsServiceDefinitionData>();
	//
	// TemplateAllowsServiceDefinitionData data = new
	// TemplateAllowsServiceDefinitionData();
	//
	// data.setMasterListServiceDefinition1up(14290);
	// data.setPackageTemplate1Up(123);
	// data.setCreateBy("Ranjit");
	// data.seteBSInstance1up(1);
	//
	// TemplateAllowsServiceDefinitionData data2 = new
	// TemplateAllowsServiceDefinitionData();
	//
	// data2.setMasterListServiceDefinition1up(15105);
	// data2.setPackageTemplate1Up(123);
	// data2.setCreateBy("Ranjit");
	// data2.seteBSInstance1up(1);
	// list.add(data);
	// list.add(data2);
	//
	// templateService.saveAndDeleteAllowServiceDefinitionForTemplate(list,
	// 123);
	// System.out.println("hello");
	//
	// }

	// @Test
	// public void saveAllowLimitValForTempl() throws DataAccessException {
	// List<TemplateAllowsLimitValData> list = new
	// ArrayList<TemplateAllowsLimitValData>();
	// TemplateAllowsLimitValData data = new TemplateAllowsLimitValData();
	// data.setMasterListLimitVal1up(3490);
	// data.seteBSInstance1up(1);
	// data.setCreateBy("shweta");
	// // data.setPackageTemplate1Up(78);
	//
	// list.add(data);
	// System.out.println(templateService
	// .saveAndDeleteAllowLimitValForTemplate(list, 123));
	//
	// TemplateAllowsLimitValData data1 = new TemplateAllowsLimitValData();
	// data1.setMasterListLimitVal1up(3495);
	// data1.seteBSInstance1up(1);
	// data1.setCreateBy("shweta");
	// // data.setPackageTemplate1Up(78);
	//
	// list.add(data);
	// System.out.println(templateService
	// .saveAndDeleteAllowLimitValForTemplate(list, 123));
	// TemplateAllowsLimitValData data2 = new TemplateAllowsLimitValData();
	// data2.setMasterListLimitVal1up(3497);
	// data2.seteBSInstance1up(1);
	// data2.setCreateBy("shweta");
	// // data.setPackageTemplate1Up(78);
	//
	// list.add(data2);
	// System.out.println(templateService
	// .saveAndDeleteAllowLimitValForTemplate(list, 123));
	// TemplateAllowsLimitValData data3 = new TemplateAllowsLimitValData();
	// data3.setMasterListLimitVal1up(3500);
	// data3.seteBSInstance1up(1);
	// data3.setCreateBy("shweta");
	// // data.setPackageTemplate1Up(78);
	//
	// list.add(data3);
	// System.out.println(templateService
	// .saveAndDeleteAllowLimitValForTemplate(list, 123));
	// TemplateAllowsLimitValData data4 = new TemplateAllowsLimitValData();
	// data4.setMasterListLimitVal1up(3506);
	// data4.seteBSInstance1up(1);
	// data4.setCreateBy("shweta");
	// // data.setPackageTemplate1Up(78);
	//
	// list.add(data4);
	// System.out.println(templateService
	// .saveAndDeleteAllowLimitValForTemplate(list, 123));
	// TemplateAllowsLimitValData data5 = new TemplateAllowsLimitValData();
	// data5.setMasterListLimitVal1up(3515);
	// data5.seteBSInstance1up(1);
	// data5.setCreateBy("shweta");
	// // data.setPackageTemplate1Up(78);
	//
	// list.add(data5);
	// System.out.println(templateService
	// .saveAndDeleteAllowLimitValForTemplate(list, 123));
	//
	// }
	//
	// @Test
	// public void deleteWorkFlowStateTemplate() throws DataAccessException {
	//
	// List<Integer> ids = new ArrayList<>();
	//
	// ids.add(22);
	// ids.add(24);
	//
	// templateService.deleteWorkFlowStateTemplate(ids);
	//
	// }

	//
	// @Test
	// public void deleteTemplateTest() throws DataAccessException {
	// templateService.deleteTemplate(80);
	//
	// }
	//

}
