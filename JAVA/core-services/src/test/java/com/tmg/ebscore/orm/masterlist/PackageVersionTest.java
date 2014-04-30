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

import com.tmg.ebscore.dto.packageversion.BenefitSelectionTierdataData;
import com.tmg.ebscore.dto.packageversion.PkgVerBig3COData;
import com.tmg.ebscore.dto.packageversion.ProductServiceData;
import com.tmg.ebscore.dto.packageversion.ShapeValueInstanceTreeData;
import com.tmg.ebscore.orm.DataAccessException;
import com.tmg.ebscore.service.PackageVersionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:core-data-services.xml",
		"classpath:ebscore-test-orm-context.xml" })
public class PackageVersionTest {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private PackageVersionService packageVersionService;

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
	// public void testgetMatrixMessage() throws DataAccessException {
	// PackageVersionContainer<MatrixShapeBig3COMessageXXrefData>
	// packageVersionContainer = packageVersionService
	// .getAllMessagesFromMatrixShapeBig3CO(69960, 0, 100);
	// Assert.assertEquals(24, packageVersionContainer.getData().size());
	//
	// System.out.println("Hello");
	// }

	// @Test
	// public void getPkgVerByPkgId() throws DataAccessException {
	//
	// PackageVersionContainer<PackageVersionData> containers =
	// packageVersionService
	// .getPkgVerByPkgId(318, 0, 100);
	// Assert.assertEquals(1, containers.getData().size());
	// System.out.println("getPkgVerByPkgId() Tested successfully");
	//
	// }
	//
	// @Test
	// public void testGetAllBeneitOptionById() throws DataAccessException {
	// PackageVersionContainer<PackageVersionBenefitOptionData>
	// packageVersionContainer = packageVersionService
	// .getAllBeneitOptionById(281, 0, 100);
	// Assert.assertEquals(1, packageVersionContainer.getData().size());
	// System.out.println("GetAllBeneitOptionById() Tested successfully");
	// }
	//
	// @Test
	// public void testGetCommonBenefitForEachPackageVersion()
	// throws DataAccessException {
	// PackageVersionContainer<PkgVerInstanceTreeData> packageVersionContainer =
	// packageVersionService
	// .getCommonBenefitForEachPackageVersion(281, 0, 100);
	// Assert.assertEquals(6, packageVersionContainer.getData().size());
	// System.out
	// .println("GetCommonBenefitForEachPackageVersion() Tested successfully");
	// }
	//
	// @Test
	// public void getCommonBenefitForEachPackageVersionByTemplateTree()
	// throws DataAccessException {
	//
	// List<PkgVerInstanceTreeData> list = packageVersionService
	// .getCommonBenefitForEachPackageVersionByTemplateTree(281, 112);
	// Assert.assertEquals(6, list.size());
	// System.out
	// .println("getCommonBenefitForEachPackageVersionByTemplateTree() Tested successfully");
	// }
	//
	// @Test
	// public void getPkgVerBig3COByPkgVerId() throws DataAccessException {
	//
	// PackageVersionContainer<PkgVerBig3COData> container =
	// packageVersionService
	// .getPkgVerBig3CoByPkgVerId(281, 0, 3000);
	// Assert.assertEquals(52, container.getData().size());
	// System.out.println("getPkgVerBig3COByPkgVerId() Tested successfully");
	// }
	//
	// @Test
	// public void getBenefitRuleMapperTest() throws DataAccessException {
	// List<Integer> intx = new ArrayList<Integer>();
	// intx.add(104);
	// intx.add(105);
	// intx.add(106);
	// intx.add(107);
	// packageVersionService.getBenefitRuleMapper(intx);
	// System.out.println("getBenefitRuleMapperTest() Tested successfully");
	// }
	//
	// // @Test
	// // public void getPackageVersionSummary() {
	// //
	// // PackageVersionContainer<PackageVersionSummaryData> container =
	// // packageVersionService
	// // .getPackageVersionSummary(0, 100);
	// // Assert.assertEquals(36, container.getData().size());
	// // System.out.println("getPackageVersionSummary() Tested successfully");
	// // }
	//
	// @Test
	// public void getPkgVerDetailsByPkgVerId() throws DataAccessException {
	// PackageVersionData data = packageVersionService
	// .getPkgVerDetailsByPkgVerId(281);
	// System.out.println(data.getVersionName());
	// System.out.println("getPkgVerDetailsByPkgVerId() Tested successfully");
	// }
	//
	// @Test
	// public void getAllShapeValueBig3COforDeductible()
	// throws DataAccessException {
	//
	// List<ShapeValueBig3COData> list = packageVersionService
	// .getAllShapeValueBig3COforDeductible(522);
	//
	// Assert.assertEquals(154, list.size());
	// System.out
	// .println("getAllShapeValueBig3COforDeductible() Tested successfully");
	// }
	//
	// @Test
	// public void getServicetypeForBenefitOption() throws DataAccessException {
	// List<BenefitOptionBig3COData> list = packageVersionService
	// .getServicetypeForBenefitOption(281, 300, true);
	//
	// Assert.assertEquals(52, list.size());
	// System.out
	// .println("getServicetypeForBenefitOption() Tested successfully");
	// }
	//
	// @Test
	// public void saveUpdatePackageVersion() {
	//
	// PackageVersionData packageVersionData = new PackageVersionData();
	// packageVersionData.setBenifitPackage1up(326);
	// packageVersionData.setCreateBy("AP");
	// packageVersionData.setCreateDt(new Date());
	// packageVersionData.seteBSInstance1up(1);
	// packageVersionData.setPkgVersion1up(330);
	// // packageVersionData.setReleased(false);
	// packageVersionData.setVersionDescription("AP");
	// packageVersionData.setVersionID("AP");
	// packageVersionData.setVersionName("AP");
	// packageVersionData.setWorkflowstate1up(1);
	//
	// packageVersionService.saveUpdatePackageVersion(packageVersionData);
	// }
	//
	// @Test
	// public void saveAndUpdatePkgVerBig3Co() throws DataAccessException {
	//
	// List<PkgVerBig3COData> list = new ArrayList<PkgVerBig3COData>();
	//
	// PkgVerBig3COData data = new PkgVerBig3COData();
	// data.seteBSInstance1up(1);
	// data.setPkgVerBig3CO1up(73157);
	// data.setEnable(true);
	// data.setCreateBy("AP");
	// data.setCreateDt(new Date());
	// data.setCommonBenefit1Up(522);
	// data.setIsCovered(true);
	// data.setPkgMasterListForBig3COId(45872);
	// data.setsETRMessageSelected(true);
	// data.setPkgVersionId(281);
	// data.setTreatAs100(true);
	// list.add(data);
	//
	// packageVersionService.saveAndUpdatePkgVerBig3Co(list);
	// System.out.println("saveAndUpdatePkgVerBig3Co() successfully tested");
	//
	// }
	//
	// @Test
	// public void savePackageVersionInstanceTree() throws DataAccessException {
	//
	// PkgVerInstanceTreeData pkgVersionData = new PkgVerInstanceTreeData();
	//
	// pkgVersionData.setPkgVersion1up(110);
	// pkgVersionData.setTemplateTree1up(76);
	// pkgVersionData.setCreateBy("Ranjit1");
	// pkgVersionData.setName("mmefit11");
	// pkgVersionData.setDescription("mfit11");
	// pkgVersionData.seteBSInstance1up(1);
	//
	// packageVersionService.copyCommonBenefitFormExistingCommonBenefit(
	// pkgVersionData, 160);
	// }
	//
	// @Test
	// public void changeWorkFlowStateOfPackageVersion()
	// throws DataAccessException {
	// packageVersionService.changeWorkFlowStateOfPackageVersion(26, 18);
	// }
	//
	// @Test
	// public void saveAllBenefitBasedOnCommonBenefit() throws
	// DataAccessException {
	//
	// List<BenefitSelectionTierdataData> list = new
	// ArrayList<BenefitSelectionTierdataData>();
	//
	// BenefitSelectionTierdataData data = new BenefitSelectionTierdataData();
	// // data.setBenefitSelectionTierdata1up(0);
	// data.setAllowedAmtShape(69);
	// data.setAllowedAmt(124);
	// data.setAllowedCtr(124);
	// data.setAllowedCtrShape(69);
	// data.setApplyCoinsurance(true);
	// data.setApplyCoinsuranceShape(69);
	// data.setPkgVerInstanceTree1up(171);
	// data.setCoinsuranceYesValShape(69);
	// data.setCoinsuranceYesVal(104);
	// data.setCoinsuranceNoVal(124);
	// data.setCopayShape(69);
	// data.setCopayVal(187);
	// data.setTierNo(4);
	// data.setPkgVerBig3CO1up(25811);
	// data.setUpdateBy("Snehal");
	// data.setCreateBy("TMG");
	// data.seteBSInstance1up(1);
	// list.add(data);
	// packageVersionService.saveBenefitBasedOnCommmomBenifit(list);
	// }
	// // @Test
	// // public void saveAllBenefitBasedOnCommonBenefit() throws
	// // DataAccessException {
	// //
	// // BenefitSelectionTierdataData data = new
	// BenefitSelectionTierdataData();
	// // // data.setBenefitSelectionTierdata1up(0);
	// // data.setAllowedAmtShape(69);
	// // data.setAllowedAmt(124);
	// // data.setAllowedCtr(124);
	// // data.setAllowedCtrShape(69);
	// // data.setApplyCoinsurance(true);
	// // data.setApplyCoinsuranceShape(69);
	// // data.setPkgVerInstanceTree1up(171);
	// // data.setCoinsuranceYesValShape(69);
	// // data.setCoinsuranceYesVal(104);
	// // data.setCoinsuranceNoVal(124);
	// // data.setCopayShape(69);
	// // data.setCopayVal(187);
	// // data.setTierNo(4);
	// // data.setPkgVerBig3CO1up(25811);
	// // data.setUpdateBy("Snehal");
	// // data.setCreateBy("TMG");
	// // data.seteBSInstance1up(1);
	// // list.add(data);
	// // packageVersionService.saveBenefitBasedOnCommmomBenifit(list);
	// // }
	//
	// // @Test
	// // public void testGetCommonBenefitForEachPackageVersion()
	// // throws DataAccessException {
	// //
	// // List<PkgVerInstanceTreeData> packageVersionContainer =
	// // packageVersionService
	// // .getCommonBenefitForEachPackageVersionByTemplateTree(313, 25);
	// // Assert.assertEquals(4, packageVersionContainer.size());
	// // }
	//
	// // @Test
	// // public void testUpdateAndDeleteServicetypeForBenefitOption()
	// // throws DataAccessException {
	// // List<BenefitOptionBig3COData> big3coDatas = new
	// // ArrayList<BenefitOptionBig3COData>();
	// // BenefitOptionBig3COData big3coData = new BenefitOptionBig3COData();
	// // big3coData.setBenefitOptionBig3CO1up(359);
	// // big3coData.setEnable(false);
	// // big3coData.setCoveredFlag(true);
	// // big3coData.setTreatAs100PctFlag(true);
	// // big3coData.setUpdateBy("Ranjit");
	// // big3coData.setPkgVerInstanceTree1up(571);
	// // big3coDatas.add(big3coData);
	// // packageVersionService
	// // .updateAndDeleteServiceTypeForBenefitOption(big3coDatas);
	// // }
	//
	// /*
	// * @Test public void testUpdateAndDeleteServicetypeForBenefitOption()
	// throws
	// * DataAccessException {
	// *
	// * List<BenefitOptionBig3COData> big3coDatas = new
	// * ArrayList<BenefitOptionBig3COData>();
	// *
	// * BenefitOptionBig3COData big3coData = new BenefitOptionBig3COData(); //
	// * big3coData.setBenefitOptionBig3CO1up(295);
	// * big3coData.setPkgVersion1up(229); big3coData.setPkgVerBig3CO1up(56403);
	// * big3coData.setBenefitOptionCoveredFlag(true);
	// * big3coData.setBenefitOptionTreatAs100PctFlag(true);
	// * big3coData.setBenefitOptionInstanceTree1up(449);
	// * big3coData.setCreateBy("aasfa"); big3coData.setUpdateBy("Raddam");
	// * big3coData.seteBSInstance1up(1);
	// * big3coData.setsETRMessageSelected(false);
	// *
	// * big3coDatas.add(big3coData);
	// *
	// * PackageVersionBenefitOptionData benefitOptionData = new
	// * PackageVersionBenefitOptionData();
	// *
	// * benefitOptionData.setPkgVerBenefitOption1up(342);
	// * benefitOptionData.setBenefitOptionName("Sn");
	// * benefitOptionData.setDescription("RRRRRR");
	// * benefitOptionData.setCreateBy("aaa");
	// * benefitOptionData.seteBSInstance1up(1);
	// * benefitOptionData.setUpdateBy("ssfs");
	// * benefitOptionData.setPkgVersion1up(229);
	// *
	// * packageVersionService.saveAndUpdateBenefitOption(benefitOptionData,
	// * big3coDatas, null);
	// *
	// * }
	// *
	// * @Test public void testsaveAndUpdateBenefitSelectionForBenefitOption()
	// * throws DataAccessException {
	// *
	// * PackageVersionBenefitOptionData benefitOptionData = new
	// * PackageVersionBenefitOptionData();
	// *
	// * benefitOptionData.setPkgVerBenefitOption1up(342);
	// * benefitOptionData.setBenefitOptionName("Sn");
	// * benefitOptionData.setDescription("RRRRRR");
	// * benefitOptionData.setCreateBy("aaa");
	// * benefitOptionData.seteBSInstance1up(1);
	// * benefitOptionData.setUpdateBy("ssfs");
	// * benefitOptionData.setPkgVersion1up(229);
	// *
	// * List<BenefitOptionTierdataData> benDatas = new
	// * ArrayList<BenefitOptionTierdataData>();
	// *
	// * BenefitOptionTierdataData data = new BenefitOptionTierdataData();
	// * data.setBenefitOptionTierdataData1up(1); data.seteBSInstance1up(1);
	// * data.setCreateBy("Snehal"); data.setUpdateBy("Sne"); data.setTierNo(1);
	// * data.setAllowedAmt(228); data.setAllowedAmtShape(957);
	// * data.setAllowedCtr(195); data.setAllowedCtrShape(958);
	// * data.setApplyCoinsurance(false); data.setApplyCoinsuranceShape(853);
	// * data.setCopayShape(1055); data.setCopayVal(65);
	// * data.setCoinsuranceNoVal(127); data.setCoinsuranceNoValShape(995);
	// * data.setCoinsuranceYesVal(21); data.setCoinsuranceYesValShape(854);
	// *
	// * benDatas.add(data);
	// *
	// * packageVersionService.saveAndUpdateBenefitSelectionForBenefitOption(88,
	// * benefitOptionData, benDatas); }
	// */
	// // @Test
	// // public void GetServicetypeForBenefitOption() throws
	// DataAccessException {
	// // List<BenefitOptionBig3COData> benefitOptionBig3CODatas =
	// // packageVersionService
	// // .getServicetypeForBenefitOption(229, 342, true);
	// //
	// // Assert.assertEquals(15, benefitOptionBig3CODatas.size());
	// // }
	//
	// // @Test
	// // public void testsaveAndUpdateBenefitSelectionForBenefitOption()
	// // throws DataAccessException {
	// //
	// // List<BenefitOptionTierdataData> benOptList = new
	// // ArrayList<BenefitOptionTierdataData>();
	// // BenefitOptionTierdataData benOption = new BenefitOptionTierdataData();
	// //
	// // benOption.setBenefitOptionTierdataData1up(29);
	// // benOption.seteBSInstance1up(1);
	// // benOption.setUpdateBy("Snehal");
	// // benOption.setAllowedAmt(193);
	// // benOption.setAllowedAmtShape(812);
	// // benOption.setAllowedCtr(195);
	// // benOption.setApplyCoinsurance(true);
	// // benOption.setApplyCoinsuranceShape(913);
	// // benOption.setCoinsuranceNoVal(127);
	// // benOption.setCoinsuranceNoValShape(915);
	// // benOption.setCopayShape(916);
	// // benOption.setCopayVal(41);
	// // benOption.setTierNo(1);
	// // benOption.setTreatAs100(true);
	// //
	// // benOption.setAllowedCtr(231);
	// // benOption.setAllowedCtrShape(813);
	// //
	// // benOption.setUpdateDt(new Date());
	// //
	// // List<BenefitOptionTierdataData> optionTierdataDatas = new
	// // ArrayList<BenefitOptionTierdataData>();
	// //
	// // optionTierdataDatas.add(benOption);
	// //
	// // PackageVersionBenefitOptionData benefitOptionData = new
	// // PackageVersionBenefitOptionData();
	// // benefitOptionData.setPkgVerBenefitOption1up(46);
	// // benefitOptionData.setBenefitOptionName("afaeg");
	// // benefitOptionData.setDescription("rbgwur");
	// // benefitOptionData.setUpdateBy("Snehal");
	// //
	// // packageVersionService.saveAndUpdateBenefitSelectionForBenefitOption(
	// // benefitOptionData, optionTierdataDatas);
	// // }

	// @Test
	// public void testGetBenefitselectionForCommonBenefit()
	// throws DataAccessException {
	// PackageVersionContainer<BenefitSelectionTierdataData> list =
	// packageVersionService
	// .getAllBenifitBasedOnCommonBenifitType(493, 0, 800);
	//
	// Assert.assertEquals(724, list.getData().size());
	//
	// System.out.println("Hello");
	// }

	/*
	 * @Test public void testgetMergedBenefitSelection() throws
	 * DataAccessException { List<BenefitSelectionTierdataData> list =
	 * packageVersionService .getMergedBenefitSelectionForProduct(493, 0, 1000);
	 * 
	 * // @Test // public void testgetMergedBenefitSelection() // throws
	 * DataAccessException { // List<BenefitSelectionTierdataData> list =
	 * packageVersionService // .getMergedBenefitSelectionForProduct(493, 0,
	 * 1000); // // Assert.assertEquals(727, list.size()); // //
	 * System.out.println("Hello"); // }
	 * 
	 * 
	 * // @Test // public void getAllLimitsFromMatrixShapeBig3CO() // throws
	 * DataAccessException { // List<MatrixShapeBig3COLimitXXrefData>
	 * listMatrixShapeBig3COLimit = // packageVersionService //
	 * .getAllLimitValForBenefitSelectionOnPkgVersion(19795, // 946); //
	 * Assert.assertEquals(14, listMatrixShapeBig3COLimit.size()); // }
	 * 
	 * 
	 * System.out.println("Hello"); }
	 */

	// @Test
	// public void testSave() throws DataAccessException {
	//
	// List<BenefitSelectionTierdataData> list = new
	// ArrayList<BenefitSelectionTierdataData>();
	// BenefitSelectionTierdataData data = new BenefitSelectionTierdataData();
	// data.setPkgVerInstanceTree1up(493);
	// data.seteBSInstance1up(1);
	// data.setPkgVerBig3CO1up(71150);
	// data.setUpdateBy("Ranjit");
	// data.setApplyDeductible(true);
	// data.setApplyDeductibleShape(948);
	// list.add(data);
	//
	// ServiceOperationResult result = packageVersionService
	// .saveAndUpdateBenefitSelectionInShapeValueBig3Co(493, list);
	// System.out.println("Hello");
	//
	// }
	// @Test
	// public void testupdateDefaultValueOfBenefitSelectionForPackgeVersion()
	// throws DataAccessException {
	// BenefitSelectionTierdataData data = new BenefitSelectionTierdataData();
	//
	// data.setCoinsuranceYesVal1up(27);
	// data.setCoinsuranceYesValShape(950);
	// data.setUpdateBy("TMGShweta");
	// data.setUpdateDt(new Date());
	// packageVersionService
	// .updateDefaultValueOfBenefitSelectionForPackgeVersion(data, 493);
	//
	// data.setApplyDeductibleShape(40);
	// }

	// @Test
	// public void
	// testsaveAndUpdateBenefitSelectionInShapeValueBenefitOptionForBig3Co()
	// throws DataAccessException {
	//
	// List<BenefitSelectionTierdataData> datas = new
	// ArrayList<BenefitSelectionTierdataData>();
	// BenefitSelectionTierdataData data = new BenefitSelectionTierdataData();
	// data.setCoinsuranceYesVal1up(27);
	// data.setCoinsuranceYesValShape(950);
	// data.setPkgVerBig3CO1up(81868);
	// //data.setBenefitOption1up();
	// data.setUpdateBy("TMGRanjit");
	// data.setUpdateDt(new Date());
	// datas.add(data);
	// packageVersionService
	// .saveAndUpdateBenefitSelectionInShapeValueBenefitOptionForBig3Co(
	// 526, datas);
	//
	// data.setApplyDeductibleShape(40);
	// }

	/*
	 * @Test public void testGetAllServiceTypeForPKgVersion() throws
	 * DataAccessException { List<PkgVerBig3COData> list = packageVersionService
	 * .getAllServiceTypeForPKgVersion(249); System.out.println(list.size());
	 * Assert.assertEquals(2662, list.size()); }
	 */

	@Test
	public void testgetInstanceTreesAllTreeShapeValue()
			throws DataAccessException {
		List<ShapeValueInstanceTreeData> list = packageVersionService
				.getInstanceTreesAllTreeShapeValue(493);
		System.out.println("size of list is" + list.size());
	}

	/*
	 * @Test public void testServicesForAProduct() throws DataAccessException {
	 * 
	 * ProductServiceData data = packageVersionService
	 * .getAllBenefitsForAProduct(193, 0, 1000);
	 * 
	 * System.out.println("Hello"); }
	 */
}
