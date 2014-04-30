package com.tmg.ebscore.orm.masterlist;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tmg.ebscore.dto.masterlist.MasterListContainer;
import com.tmg.ebscore.dto.masterlist.MasterListMessageValData;
import com.tmg.ebscore.orm.DataAccessException;
import com.tmg.ebscore.service.MasterListService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:core-data-services.xml",
		"classpath:ebscore-test-orm-context.xml" })
public class MasterListTest {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private MasterListService masterListService;

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

	/*
	 * @Test public void testGetMaterListOnTemplate() throws DataAccessException
	 * {
	 * 
	 * MasterListContainer<MasterListData> masterListContainer =
	 * masterListService .getMasterList(0, 40, true, Level.TEMPLATE);
	 * Assert.assertEquals(13, masterListContainer.getData().size());
	 * 
	 * }
	 * 
	 * @Test public void testGetMaterListOnMasterList() throws
	 * DataAccessException {
	 * 
	 * MasterListContainer<MasterListData> masterListContainer =
	 * masterListService .getMasterList(0, 40, true, Level.MASTERLIST);
	 * Assert.assertEquals(23, masterListContainer.getData().size());
	 * 
	 * }
	 */

//	@Test
//	public void testGetMaterListStringVal() throws DataAccessException {
//
//		MasterListContainer<MasterListStringValData> masterListContainer = masterListService
//				.getMasterListStringVal(15, 0, 200);
//		Assert.assertEquals(5, masterListContainer.getData().size());
//
//	}

	
//	@Test
//	public void testGetMaterListServiceDefinitionVal()
//			throws DataAccessException {
//
//		MasterListContainer<MasterListServiceDefinitionData> masterListContainer = masterListService
//				.getMasterListServiceDefinition(31, 0, 1000);
//		Assert.assertEquals(762, masterListContainer.getData().size());
//
//	}

	/*
	 * @Test public void testGetMaterListMessageValIPMC() throws
	 * DataAccessException {
	 * 
	 * MasterListContainer<MasterListMessageValData> masterListContainer =
	 * masterListService .getMasterListMessageVal(20, "IPMC", 0, 40);
	 * Assert.assertEquals(27, masterListContainer.getData().size());
	 * 
	 * }
	 * 
	 * @Test public void testGetMaterListMessageValSERL() throws
	 * DataAccessException {
	 * 
	 * MasterListContainer<MasterListMessageValData> masterListContainer =
	 * masterListService .getMasterListMessageVal(20, "SERL", 0, 100);
	 * Assert.assertEquals(94, masterListContainer.getData().size());
	 * 
	 * }
	 * 
	 * @Test public void testGetMaterListMessageValSETR() throws
	 * DataAccessException {
	 * 
	 * MasterListContainer<MasterListMessageValData> masterListContainer =
	 * masterListService .getMasterListMessageVal(20, "SETR", 16, 100);
	 * Assert.assertEquals(8, masterListContainer.getData().size());
	 * 
	 * }
	 */

	// @Test
	// public void testGetMaterListMessageValSESP() throws DataAccessException {
	//
	// MasterListContainer<MasterListMessageValData> masterListContainer =
	// masterListService
	// .getMasterListMessageVal(20, "IPMC", 0, 50);
	// Assert.assertEquals(24, masterListContainer.getData().size());
	//
	// }

	@Test
	public void testGetMaterListMessageValIPMC() throws DataAccessException {

		MasterListContainer<MasterListMessageValData> masterListContainer = masterListService
				.getMasterListMessageVal(20, "IPMC", 0, 40);
		Assert.assertEquals(24, masterListContainer.getData().size());

	}

	// @Test
	// public void deleteMaterListMessageData() throws DataAccessException {
	//
	// List<Integer> id = new ArrayList<Integer>();
	// id.add(53);
	//
	// ServiceOperationResult result = masterListService
	// .deleteMasterListMessageVal(id);
	//
	// }
	// @Test
	// public void deleteMaterListMessageData() throws DataAccessException {
	//
	// List<Integer> id = new ArrayList<Integer>();
	// //id.add(180);
	// id.add(181);
	//
	// ServiceOperationResult result = masterListService
	// .deleteMasterListMessageVal(id);
	// System.out.println("Hello");
	//
	// }

	// @Test
	// public void deleteMaterListStringData() throws DataAccessException {
	//
	// List<Integer> id = new ArrayList<Integer>();
	// id.add(109);
	// id.add(1365);
	// //id.add(803);
	//
	// ServiceOperationResult result = masterListService
	// .deleteMasterListStringVal(id);
	// System.out.println("jello");
	// }

	// @Test
	// public void deleteMaterListIntData() throws DataAccessException {
	//
	// List<Integer> id = new ArrayList<Integer>();
	// id.add(239);
	// id.add(230);
	// id.add(226);
	//
	// ServiceOperationResult result = masterListService
	// .deleteMasterListIntVal(id);
	// System.out.println("Hello");
	// }

	// @Test
	// public void deleteMaterListServiceDefinitionData()
	// throws DataAccessException {
	//
	// List<Integer> id = new ArrayList<Integer>();
	// id.add(15090);
	// //id.add(234628);
	// //id.add(46456);
	//
	// ServiceOperationResult result = masterListService
	// .deleteMasterListServiceDefinition(id);
	// System.out.println("Hello");
	// }

	// @Test
	// public void deleteMaterListAccumulationData() throws DataAccessException
	// {
	//
	// List<Integer> id = new ArrayList<Integer>();
	// id.add(291);
	// //id.add(234628);
	// //id.add(46456);
	//
	// ServiceOperationResult result = masterListService
	// .deleteMasterListAccumulationVal(id);
	//
	// }

	// @Test
	// public void deleteMaterListLimitData() throws DataAccessException {
	//
	// List<Integer> id = new ArrayList<Integer>();
	// id.add(4076);
	//
	// ServiceOperationResult result = masterListService
	// .deleteMasterListLimitVal(id);
	//
	// }

	// @Test
	// public void saveAccumulationVal() throws DataAccessException {
	// List<MasterListAccumulationValData> accumulationValData = new
	// ArrayList<MasterListAccumulationValData>();
	// MasterListAccumulationValData data = new MasterListAccumulationValData();
	//
	// data.setAccumulation1up(348);
	// data.setCreateBy("avb");
	// data.setDescription("cmddd");
	// data.seteBSInstance1up(1);
	// AccumulationType type = new AccumulationType();
	// type.setId(6);
	// data.setType(type);
	// data.setUpdateBy("gh");
	// data.setNumber(55);
	// accumulationValData.add(data);
	// ServiceOperationResult result = masterListService
	// .saveUpdateMasterListAccumulationVal(accumulationValData);
	// }

	// @Test
	// public void saveMessageVal() throws DataAccessException {
	// List<MasterListMessageValData> messageValData = new
	// ArrayList<MasterListMessageValData>();
	//
	// MasterListMessageValData data = new MasterListMessageValData();
	//
	// data.setCreateBy("shw");
	//
	// data.seteBSInstance1up(1);
	// data.setId(8484);
	// data.setMasterList1up(20);
	// data.setMessageCategory("IPMC");
	// data.setMessageType("IPMC");
	// data.setMessageValue("ssh");
	// data.setUpdateBy("sad");
	//
	// messageValData.add(data);
	// ServiceOperationResult result = masterListService
	// .saveUpdateMasterListMessageVal(messageValData);
	//
	// }

	/*
	 * 
	 * 
	 * @Test public void testGetMaterListLimitVal() throws DataAccessException {
	 * 
	 * MasterListContainer<MasterListLimitValData> masterListContainer =
	 * masterListService .getMasterListLimitVal(23, 0, 1000);
	 * Assert.assertEquals(763, masterListContainer.getData().size());
	 * 
	 * }
	 */
	// @Test
	// public void testGetMaterListIntVal() throws DataAccessException {
	//
	// MasterListContainer<MasterListIntValData> masterListContainer =
	// masterListService
	// .getMasterListIntVal(6, 0, 200);
	// Assert.assertEquals(105, masterListContainer.getData().size());
	//
	// }

	/*
	 * @Test public void testGetMaterListLimitValById() throws
	 * DataAccessException {
	 * 
	 * MasterListLimitValData limitData = masterListService
	 * .getMasterListLimitValById(2726); assertEquals((Integer) 144,
	 * limitData.getTimePeriod());
	 * 
	 * }
	 */

	// @Test
	// public void saveStringVal() throws DataAccessException {
	//
	// List<MasterListStringValData> stringData = new
	// ArrayList<MasterListStringValData>();
	// MasterListStringValData data = new MasterListStringValData();
	//
	// data.setId(1458);
	// data.seteBSInstance1up(1);
	// data.setMasterList1up(34);
	// data.setAbbrev("dhcvsh");
	// data.setCreateBy("Snehalkl");
	// data.setUpdateBy("Ranjit");
	// data.setStringValue("sKolhe");
	//
	// stringData.add(data);
	//
	// ServiceOperationResult result = masterListService
	// .saveUpdateMasterListStringVal(stringData);
	//
	// }

	// @Test
	// public void saveServiceDefinitionVal() throws DataAccessException {
	//
	// List<MasterListServiceDefinitionData> serviceDefinitionData = new
	// ArrayList<MasterListServiceDefinitionData>();
	// MasterListServiceDefinitionData data = new
	// MasterListServiceDefinitionData();
	// data.setMasterListServiceDefinitionId(15111);
	// data.setMasterList1Up(31);
	// data.setCategory1(749);
	// data.setCategory2(847);
	// data.setCategory3(1182);
	// data.setPlaceOfService(736);
	// data.seteBSInstance1up(1);
	// data.setCreateBy("TMGrr");
	// data.setUpdateBy("Ranjit");
	//
	// serviceDefinitionData.add(data);
	//
	// ServiceOperationResult result = masterListService
	// .saveUpdateMasterListServiceDefinition(serviceDefinitionData);
	//
	// System.out.println("hh");
	//
	// }

	// @Test
	// public void saveIntVal() throws DataAccessException {
	//
	// List<MasterListIntValData> intData = new
	// ArrayList<MasterListIntValData>();
	//
	// MasterListIntValData data = new MasterListIntValData();
	//
	// data.setId(351);
	// data.setMasterList1up(34);
	// data.seteBSInstance1up(1);
	// data.setIntAsDecimalValue(445243.32);
	// data.setAbbrev("mnctt");
	// data.setCreateBy("Rascxvt");
	// data.setUpdateBy("Ramnjit");
	// data.setAccumulation1up(9);
	//
	// intData.add(data);
	//
	// ServiceOperationResult result = masterListService
	// .saveUpdateMasterListIntVal(intData);
	//
	// }

	// @Test
	// public void saveLimitVal() throws DataAccessException {
	//
	// List<MasterListLimitValData> limitData = new
	// ArrayList<MasterListLimitValData>();
	//
	// MasterListLimitValData data = new MasterListLimitValData();
	//
	// data.setMasterListLimitValId(3524);
	// data.setMasterList1Up(23);
	// data.seteBSInstance1up(1);
	// data.setLimitName("abcdggd");
	// data.setLimitDescription("ravi");
	// data.setLimitCategory("gjfgdd");
	// data.setAccumulation1up(22);
	// data.setReinstatementAmount(2432);
	// data.setCoverageLevel(123);
	//
	// data.setTimePeriod(123);
	//
	// data.setBenefitAmtInDollarsFlag(true);
	// data.setBenefitAmtInPercentFlag(false);
	// data.setBenefitAmount(20.00);
	// data.setQuantitySetInBenefitFlag(true);
	// data.setAmountsSetInBenefitFlag(true);
	//
	// data.setQuantityQualifier(106);
	//
	// data.setCreateBy("Ranjit");
	// data.setUpdateBy("Ram");
	//
	// limitData.add(data);
	//
	// ServiceOperationResult result = masterListService
	// .saveUpdateMasterListLimitVal(limitData);
	//
	// }

	// @Test
	// public void testGetMaterListServiceDefinitionVal()
	// throws DataAccessException {
	//
	// MasterListContainer<MasterListServiceDefinitionData> masterListContainer
	// = masterListService
	// .getMasterListServiceDefinition(31, 0, 1000);
	// Assert.assertEquals(764, masterListContainer.getData().size());
	//
	// }

	// @Test
	// public void testGetMaterListMessageValIPMC() throws DataAccessException {
	//
	// MasterListContainer<MasterListMessageValData> masterListContainer =
	// masterListService
	// .getMasterListMessageVal(20, "IPMC", 0, 40);
	// Assert.assertEquals(23, masterListContainer.getData().size());
	//
	// }

	// @Test
	// public void testGetMaterListAccumulationVal() throws DataAccessException
	// {
	// List<Integer> intList = new ArrayList<Integer>();
	// intList.add(6);
	// intList.add(23);
	// MasterListContainer<MasterListAccumulationValData> masterListContainer =
	// masterListService
	// .getMasterListAccumulationVal(intList, 0, 400);
	// Assert.assertEquals(292, masterListContainer.getData().size());
	//
	// }

	// @Test
	// public void testGetMaterListIntVal() throws DataAccessException {
	//
	// MasterListContainer<MasterListIntValData> masterListContainer =
	// masterListService
	// .getMasterListIntVal(2, 0, 60);
	// Assert.assertEquals(45, masterListContainer.getData().size());
	//
	// }

	// @Test
	// public void testGetMaterListLimitVal() throws DataAccessException {
	//
	// MasterListContainer<MasterListLimitValData> masterListContainer =
	// masterListService
	// .getMasterListLimitVal(23, 0, 1000);
	// Assert.assertEquals(750, masterListContainer.getData().size());
	//
	// }

	// @Test
	// public void testGetMaterListServiceDefVal() throws DataAccessException {
	//
	// MasterListContainer<MasterListServiceDefinitionData> masterListContainer
	// = masterListService
	// .getMasterListServiceDefinition(31, 0, 800);
	// Assert.assertEquals(760, masterListContainer.getData().size());
	//
	// }
	//
	// @Test
	// public void testGetMaterListStringVal() throws DataAccessException {
	//
	// MasterListContainer<MasterListStringValData> masterListContainer =
	// masterListService
	// .getMasterListStringVal(28, 0, 100);
	// Assert.assertEquals(3, masterListContainer.getData().size());
	//
	// }

}
