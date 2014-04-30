package com.tmg.ebscore.orm.masterlist;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import com.tmg.ebscore.dto.ebspackage.PackageContainer;
import com.tmg.ebscore.dto.ebspackage.PackageSummaryData;
import com.tmg.ebscore.dto.ebspackage.PkgConfigOptionData;
import com.tmg.ebscore.dto.ebspackage.PkgMasterListForBig3COData;
import com.tmg.ebscore.orm.DataAccessException;
import com.tmg.ebscore.service.PackageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:core-data-services.xml",
		"classpath:ebscore-test-orm-context.xml" })
public class PackageTest {
	@Autowired
	private ApplicationContext context;

	@Autowired
	private PackageService packageService;

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

	// @Test
	// public void getReleasedPckVer() throws DataAccessException {
	// int count = packageService.getNoOfReleasedPackageVersions(102);
	// System.out.println(count);
	// }
	//
	// @Test
	// public void getUnReleasedPckVer() throws DataAccessException {
	// int count = packageService.getNoOfUnReleasedPackageVersions(102);
	// System.out.println(count);
	// }
	//

	// @Test
	// public void getPackageDetails() throws DataAccessException {
	// BenifitPackageData benifitPackageData = packageService
	// .getPackageDetails(294);
	// System.out.println(benifitPackageData.getPackageId());
	// }
	//
	// @Test
	// public void testGetServiceTypeForPackage() throws DataAccessException {
	//
	// PackageContainer<PkgMasterListForBig3COData> container = packageService
	// .getServiceTypeForPackage(294, 0, 100);
	// Assert.assertEquals(52, container.getData().size());
	// }
	//
	// @Test
	// public void testGetPackageList() throws DataAccessException {
	//
	// PackageContainer<PackageSummaryData> packageContainer = packageService
	// .getPackageList(0, 200);
	// Assert.assertEquals(43, packageContainer.getData().size());
	//
	// }
	//
	// @Test
	// public void testGetAllBenefitOffering() throws DataAccessException {
	//
	// PackageContainer<PkgConfigOptionData> container = new
	// PackageContainer<PkgConfigOptionData>();
	// container = packageService.getAllBenefitOffering(294, 0, 100);
	//
	// Assert.assertEquals(22, container.getData().size());
	// }
	//
	// @Test
	// public void testSaveeServiceTypeForPackage() throws DataAccessException {
	//
	// List<PkgMasterListForBig3COData> big3coDatas = new
	// ArrayList<PkgMasterListForBig3COData>();
	// PkgMasterListForBig3COData big3coData = new PkgMasterListForBig3COData();
	// big3coData.setPkgMasterListForBig3COId(62669);
	// big3coData.setBenefitPackage1up(294);
	// big3coData.setCreateBy("apoorva");
	// big3coData.setMasterListServiceDefinitionId(14320);
	// big3coData.setBenefitRule1Up(159);
	// big3coData.seteBSInstance1up(1);
	// big3coDatas.add(big3coData);
	// packageService.saveServiceTypeForPackage(big3coDatas, 294);
	//
	// }
	//
	// @Test
	// public void testcreateNewPackage() throws DataAccessException {
	// BenifitPackageData benifitPackageData = new BenifitPackageData();
	// benifitPackageData.setPackageDescription("sw1");
	// benifitPackageData.setCreateBy("apoorva");
	// benifitPackageData.setPackageId("saha");
	// benifitPackageData.setPackageName("saahs");
	// benifitPackageData.seteBSInstance1up(1);
	// benifitPackageData.setPackageTemplate1up(123);
	// packageService.saveUpdatePackage(benifitPackageData);
	//
	// }
	//
	// @Test
	// public void testcopyFromExistingPackage() throws DataAccessException {
	// BenifitPackageData benifitPackageData = new BenifitPackageData();
	// benifitPackageData.setPackageDescription("rswi");
	// benifitPackageData.setCreateBy("apoorva");
	// benifitPackageData.setPackageId("apoorva");
	// benifitPackageData.setPackageName("apoorva");
	// benifitPackageData.setPackageTemplate1up(123);
	// benifitPackageData.seteBSInstance1up(1);
	// benifitPackageData.setBenefitPackage1up(306);
	// List<Integer> pkgVersionId = new ArrayList<Integer>();
	// pkgVersionId.add(293);
	// // pkgVersionId.add(302);
	// // packageService.copyFromExistingPackage(data, oldPackageId,
	// // pkgversionId, serviceType, commonBenefit, benefitOption)
	// packageService.copyFromExistingPackage(benifitPackageData,
	// pkgVersionId, true, true, true);
	// }
	//
	// @Test
	// public void testCreateBenefitOffering() throws DataAccessException {
	// PkgConfigOptionData data = new PkgConfigOptionData();
	// data.setAllowUse(true);
	// data.setClaimsCancelDate(new Date());
	// data.setClaimsEffectiveDate(new Date());
	// data.setCreateBy("apoorva");
	// data.setDescription("test");
	// data.setEffectiveDate(new Date());
	// data.setMarketSegment(124);
	// data.setName("test");
	// data.setWorkflowState(18);
	// data.seteBSInstance1up(1);
	// data.setPackageVersion(294);
	// data.setUpdateDt(new Date());
	// packageService.createBenefitOffering(data);
	// }
	//
	@Test
	public void testdeletePackage() throws DataAccessException {
		packageService.deletePackage(292);
	}

	// @Test
	// public void testDeleteBenefitOption() throws DataAccessException {
	// packageService.deleteBenefitOption(328);
	// }
	//
}
