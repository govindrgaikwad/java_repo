package com.tmg.ebscore.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmg.ebscore.dto.Level;
import com.tmg.ebscore.dto.OperationResult;
import com.tmg.ebscore.dto.ServiceOperationResult;
import com.tmg.ebscore.dto.masterlist.AccumulationType;
import com.tmg.ebscore.dto.masterlist.MasterListAccumulationValData;
import com.tmg.ebscore.dto.masterlist.MasterListContainer;
import com.tmg.ebscore.dto.masterlist.MasterListData;
import com.tmg.ebscore.dto.masterlist.MasterListIntValData;
import com.tmg.ebscore.dto.masterlist.MasterListLimitValData;
import com.tmg.ebscore.dto.masterlist.MasterListMessageValData;
import com.tmg.ebscore.dto.masterlist.MasterListServiceDefinitionData;
import com.tmg.ebscore.dto.masterlist.MasterListStringValData;
import com.tmg.ebscore.orm.DataAccessException;
import com.tmg.ebscore.orm.IsLocked;
import com.tmg.ebscore.orm.masterlist.MasterList;
import com.tmg.ebscore.orm.masterlist.MasterListAccumulationVal;
import com.tmg.ebscore.orm.masterlist.MasterListIntVal;
import com.tmg.ebscore.orm.masterlist.MasterListLimitVal;
import com.tmg.ebscore.orm.masterlist.MasterListMessageVal;
import com.tmg.ebscore.orm.masterlist.MasterListServiceDefinition;
import com.tmg.ebscore.orm.masterlist.MasterListStringVal;
import com.tmg.ebscore.repository.MasterListRepository;

@Service("masterListService")
public class MasterListService {

	@Autowired
	private MasterListRepository masterListRepository;

	/**
	 * @param page
	 *            Corresponding Page Number for Pagination
	 * 
	 * @param maxResults
	 *            Maximum number of rows displayed in a page
	 * 
	 * @param showInactive
	 *            boolean parameter for visibility on corresponding Level (True
	 *            / False)
	 * 
	 * @param level
	 *            level as enum value from enum list {MASTERLIST, TEMPLATE,
	 *            PACKAGE, PACKAGEVERSION, BENEFITOFFERING}
	 * 
	 * @return {@link MasterListContainer} It returns the list of all
	 *         MasterListData objects, PagesCount ,TotalItems.
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             It returns the list of all MasterList objects which have
	 *             visible TRUE on matching Level type, PagesCount as total
	 *             number of pages,TotalItems as total number of items. Whenever
	 *             user click on Masterlist from homepage on UI, it shows the
	 *             list of MasterList data's Name,Type,Length,Abbreviation on
	 *             UI.Type is from MasterListDataType Table.
	 * 
	 */
	@Transactional(readOnly = true)
	public MasterListContainer<MasterListData> getMasterList(int page,
			int maxResults, boolean showInactive, Level level)
			throws DataAccessException {

		Page<MasterList> callResult = null;
		try {
			callResult = executeQueryFindAll(page, maxResults, showInactive,
					level);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}

		if (shouldExecuteSameQueryInLastPage(page, callResult)) {
			int lastPage = callResult.getTotalPages() - 1;
			try {
				callResult = executeQueryFindAll(lastPage, maxResults,
						showInactive, level);
			} catch (Exception e) {
				throw new DataAccessException(e);
			}
		}

		List<MasterListData> masterListDatas = new ArrayList<MasterListData>();

		for (MasterList masterList : callResult) {

			MasterListData masterListData = new MasterListData();

			masterListData.setId(masterList.getMasterList1up());

			if (masterList.getMasterListDataType() != null) {
				masterListData.setDataType(masterList.getMasterListDataType()
						.getName());
			}

			masterListData.setName(masterList.getName());

			masterListData.setAbbrev(masterList.getAbbrev());

			masterListData.setDescription(masterList.getDescription());

			masterListData.setAllowsNAFlag(masterList.getAllowsNAFlag());

			masterListData.setLength(masterList.getLength());

			masterListData.setPrecision(masterList.getPrecision());

			masterListData.setScale(masterList.getScale());

			masterListData.setActive(masterList.getActiveFlag());

			masterListData.setAccumulationAvailableFlag(masterList
					.getAccumulationAvailableFlag());

			masterListData.setVisibleAtTemplate(masterList
					.getVisibleAtTemplate());

			masterListData.setVisibleAtMasterList(masterList
					.getVisibleAtMasterList());

			masterListData.setHasExtensionForFacets(masterList
					.getHasExtensionForFacets());

			masterListData.setCreateBy(masterList.getCreateBy());

			masterListData.seteBSInstance1up(masterList.geteBSInstance1up());

			masterListData.setCreateDt(masterList.getCreateDt());

			masterListData.setUpdateBy(masterList.getUpdateBy());

			masterListData.setUpdateDt(masterList.getUpdateDt());

			masterListDatas.add(masterListData);

		}

		MasterListContainer<MasterListData> result = new MasterListContainer<MasterListData>();
		result.setData(masterListDatas);
		result.setPagesCount(callResult.getTotalPages());
		result.setTotalItems(callResult.getTotalElements());

		return result;
	}

	/**
	 * @param page
	 *            Corresponding Page Number
	 * 
	 * @param result
	 *            MasterList object
	 * 
	 * @return True or False
	 * 
	 *         <p>
	 *         It return true if same query is executed on last page else return
	 *         false
	 */
	private boolean shouldExecuteSameQueryInLastPage(int page,
			Page<MasterList> result) {
		return isUserAfterOrOnLastPage(page, result)
				&& hasDataInDataBase(result);
	}

	/**
	 * @param page
	 *            Corresponding Page Number
	 * 
	 * @param result
	 *            MasterList object
	 * 
	 * @return True or False
	 * 
	 *         <p>
	 *         It return true if user is after last page else return false
	 */
	private boolean isUserAfterOrOnLastPage(int page, Page<MasterList> result) {
		return page >= result.getTotalPages() - 1;
	}

	/**
	 * @param result
	 *            MasterList object
	 * 
	 * @return True or False
	 * 
	 *         <p>
	 *         It return true if database has more data else return false
	 * 
	 */
	private boolean hasDataInDataBase(Page<MasterList> result) {
		return result.getTotalElements() > 0;
	}

	/**
	 * @param page
	 *            Corresponding Page Number
	 * 
	 * @param maxResults
	 *            Maximum number of rows displayed in a page
	 * 
	 * @param showInactive
	 *            boolean parameter for visibility on corresponding Level (True
	 *            / False)
	 * 
	 * @param level
	 *            level as enum value from enum list {MASTERLIST, TEMPLATE,
	 *            PACKAGE, PACKAGEVERSION, BENEFITOFFERING}
	 * 
	 * @return {@link MasterListContainer} It returns the list of all
	 *         MasterListData objects, PagesCount ,TotalItems.
	 * 
	 */
	public Page<MasterList> executeQueryFindAll(int page, int maxResults,
			boolean showInactive, Level level) throws DataAccessException {
		final PageRequest pageRequest = new PageRequest(page, maxResults,
				new Sort(Sort.Direction.ASC, "name"));
		try {
			if (level == Level.MASTERLIST) {
				return masterListRepository.findMasterListAtMasterListLevel(
						showInactive, pageRequest);
			}
			if (level == Level.TEMPLATE) {
				return masterListRepository.findMasterListAtTemplateLevel(
						showInactive, pageRequest);
			}
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		return null;
	}

	/**
	 * @param id
	 *            Primary Key of MasterList table which is Foreign key in
	 *            MasterListStringVal table
	 * 
	 * @param page
	 *            Corresponding Page Number for Pagination
	 * 
	 * @param maxResults
	 *            Maximum number of rows displayed in a page
	 * 
	 * @return {@link MasterListContainer} It returns the list of all
	 *         MasterListStringValData objects , PagesCount, TotalItems.
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             Whenever user click on String type from Masterlist on UI, it
	 *             shows the list of MasterListStringVal data's Value,
	 *             Abbreviation, In Use and Locked fields on UI.
	 */
	@Transactional(readOnly = true)
	public MasterListContainer<MasterListStringValData> getMasterListStringVal(
			int id, int page, int maxResults) throws DataAccessException {

		List<Integer> stringInUseList = new ArrayList<Integer>();

		Page<MasterListStringVal> pageStringVal = null;
		try {
			pageStringVal = masterListRepository.findStringValMasterList(id,
					new PageRequest(page, maxResults));

		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		stringInUseList = masterListRepository
				.findAllUseAndUnUseStringValue(id);

		List<Integer> lockedDatas = masterListRepository.findLockedValues(id,
				IsLocked.STRING);

		List<MasterListStringValData> masterListStringValDatas = new ArrayList<MasterListStringValData>();

		for (MasterListStringVal masterListStringVal : pageStringVal) {

			MasterListStringValData masterListStringValData = new MasterListStringValData();

			masterListStringValData
					.setMasterListStringVal1up(masterListStringVal
							.getMasterListStringVal1up());

			if (masterListStringVal.getMasterListString() != null) {
				masterListStringValData.setMasterList1up(masterListStringVal
						.getMasterListString().getMasterList1up());
			}
			if (stringInUseList.contains(masterListStringVal
					.getMasterListStringVal1up())) {
				masterListStringValData.setInUse(true);

			} else {
				masterListStringValData.setInUse(false);

			}

			if (lockedDatas.contains(masterListStringVal
					.getMasterListStringVal1up())) {
				masterListStringValData.setIsLocked(true);
			} else {
				masterListStringValData.setIsLocked(false);

			}

			masterListStringValData.setStringValue(masterListStringVal
					.getStringValue());

			masterListStringValData.setAbbrev(masterListStringVal.getAbbrev());

			masterListStringValData.setCreateBy(masterListStringVal
					.getCreateBy());

			masterListStringValData.seteBSInstance1up(masterListStringVal
					.geteBSInstance1up());

			masterListStringValData.setCreateDt(masterListStringVal
					.getCreateDt());

			masterListStringValData.setUpdateBy(masterListStringValData
					.getUpdateBy());

			masterListStringValData.setUpdateDt(masterListStringVal
					.getUpdateDt());

			masterListStringValDatas.add(masterListStringValData);

		}

		MasterListContainer<MasterListStringValData> result = new MasterListContainer<MasterListStringValData>();
		result.setData(masterListStringValDatas);
		result.setPagesCount(pageStringVal.getTotalPages());
		result.setTotalItems(pageStringVal.getTotalElements());

		return result;

	}

	@Transactional(readOnly = true)
	public List<MasterListStringValData> getAllMasterListStringVal(
			List<Integer> ids) throws DataAccessException {

		List<MasterListStringVal> pageStringVal = null;
		try {
			pageStringVal = masterListRepository
					.findAllStringValMasterList(ids);

		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<MasterListStringValData> masterListStringValDatas = new ArrayList<MasterListStringValData>();

		for (MasterListStringVal masterListStringVal : pageStringVal) {

			MasterListStringValData masterListStringValData = new MasterListStringValData();

			masterListStringValData
					.setMasterListStringVal1up(masterListStringVal
							.getMasterListStringVal1up());

			if (masterListStringVal.getMasterListString() != null) {
				masterListStringValData.setMasterList1up(masterListStringVal
						.getMasterListString().getMasterList1up());
			}

			masterListStringValData.setStringValue(masterListStringVal
					.getStringValue());

			masterListStringValData.setAbbrev(masterListStringVal.getAbbrev());

			masterListStringValData.setCreateBy(masterListStringVal
					.getCreateBy());

			masterListStringValData.seteBSInstance1up(masterListStringVal
					.geteBSInstance1up());

			masterListStringValData.setCreateDt(masterListStringVal
					.getCreateDt());

			masterListStringValData.setUpdateBy(masterListStringValData
					.getUpdateBy());

			masterListStringValData.setUpdateDt(masterListStringVal
					.getUpdateDt());

			masterListStringValDatas.add(masterListStringValData);

		}

		return masterListStringValDatas;

	}

	@Transactional(readOnly = true)
	public List<MasterListIntValData> getAllMasterListIntVal(List<Integer> ids)
			throws DataAccessException {

		List<MasterListIntVal> pageIntVal = null;
		try {
			pageIntVal = masterListRepository.findAllIntValMasterList(ids);

		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<MasterListIntValData> masterListIntValDatas = new ArrayList<MasterListIntValData>();

		for (MasterListIntVal masterListIntVal : pageIntVal) {

			MasterListIntValData masterListIntValData = new MasterListIntValData();

			masterListIntValData.setMasterListIntVal1up(masterListIntVal
					.getMasterListIntVal1up());

			if (masterListIntVal.getMasterListInt() != null) {
				masterListIntValData.setMasterList1up(masterListIntVal
						.getMasterListInt().getMasterList1up());
			}

			masterListIntValData.setAbbrev(masterListIntVal.getAbbrev());

			masterListIntValData.setIntAsDecimalValue(masterListIntVal
					.getIntAsDecimalValue());

			masterListIntValData.setCreateBy(masterListIntVal.getCreateBy());

			masterListIntValData.seteBSInstance1up(masterListIntVal
					.geteBSInstance1up());

			masterListIntValData.setCreateDt(masterListIntVal.getCreateDt());

			masterListIntValData.setUpdateBy(masterListIntVal.getUpdateBy());

			masterListIntValData.setUpdateDt(masterListIntVal.getUpdateDt());

			if (masterListIntVal.getAccumulation() != null) {
				masterListIntValData.setAccumulation1up(masterListIntVal
						.getAccumulation().getAccumulation1up());
			}

			masterListIntValDatas.add(masterListIntValData);

		}

		return masterListIntValDatas;

	}

	/**
	 * @param id
	 *            Primary Key of MasterList table which is Foreign key in
	 *            MasterListServiceDefinition table
	 * @param page
	 *            Corresponding Page Number for Pagination
	 * 
	 * @param maxResults
	 *            Maximum number of rows displayed in a page
	 * 
	 * @return {@link MasterListContainer} It returns the list of all
	 *         MasterListServiceDefinitionData objects , PagesCount, TotalItems.
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             Whenever user click on ServiceDefinition type from Masterlist
	 *             on UI, it shows the list of MasterListServiceDefinition
	 *             data's In Use and Locked fields and category1, category2,
	 *             category3 , place of service from MasterListStringVal on UI.
	 * 
	 */
	@Transactional(readOnly = true)
	public MasterListContainer<MasterListServiceDefinitionData> getMasterListServiceDefinition(
			int id, int page, int maxResults) throws DataAccessException {

		Page<MasterListServiceDefinition> pageServiceDefinition = null;
		try {
			pageServiceDefinition = masterListRepository
					.findMasterListServiceDefination(id, new PageRequest(page,
							maxResults, new Sort(Sort.Direction.ASC,
									"masterListStringValCat1")));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<Integer> serviceDefValInUseList = masterListRepository
				.findAllUseAndUnUseServiceDefValue(id);

		List<Integer> lockedDatas = masterListRepository.findLockedValues(id,
				IsLocked.SERVICEDEFINITION);

		List<MasterListServiceDefinitionData> masterListServiceDefinitionDatas = new ArrayList<MasterListServiceDefinitionData>();

		for (MasterListServiceDefinition masterListServiceDefinition : pageServiceDefinition) {

			MasterListServiceDefinitionData masterListServiceDefinitionData = new MasterListServiceDefinitionData();

			masterListServiceDefinitionData
					.setMasterListServiceDefinitionId(masterListServiceDefinition
							.getMasterListServiceDefinition1up());

			if (serviceDefValInUseList.contains(masterListServiceDefinition
					.getMasterListServiceDefinition1up())) {
				masterListServiceDefinitionData.setInUse(true);
			} else {
				masterListServiceDefinitionData.setInUse(false);

			}

			if (lockedDatas.contains(masterListServiceDefinition
					.getMasterListServiceDefinition1up())) {
				masterListServiceDefinitionData.setIsLocked(true);

			} else {
				masterListServiceDefinitionData.setIsLocked(false);

			}

			if (masterListServiceDefinition.getMasterListService() != null) {
				masterListServiceDefinitionData
						.setMasterList1Up(masterListServiceDefinition
								.getMasterListService().getMasterList1up());
			}

			if (masterListServiceDefinition.getMasterListStringValCat1() != null) {
				MasterListStringValData category1 = new MasterListStringValData();
				category1.setMasterListStringVal1up(masterListServiceDefinition
						.getMasterListStringValCat1()
						.getMasterListStringVal1up());
				category1.setStringValue(masterListServiceDefinition
						.getMasterListStringValCat1().getStringValue());
				category1.setAbbrev(masterListServiceDefinition
						.getMasterListStringValCat1().getAbbrev());

				masterListServiceDefinitionData.setCategory1(category1);

			}

			if (masterListServiceDefinition.getMasterListStringValCat2() != null) {

				MasterListStringValData category2 = new MasterListStringValData();
				category2.setMasterListStringVal1up(masterListServiceDefinition
						.getMasterListStringValCat2()
						.getMasterListStringVal1up());
				category2.setStringValue(masterListServiceDefinition
						.getMasterListStringValCat2().getStringValue());
				category2.setAbbrev(masterListServiceDefinition
						.getMasterListStringValCat2().getAbbrev());
				masterListServiceDefinitionData.setCategory2(category2);
			}

			if (masterListServiceDefinition.getMasterListStringValCat3() != null) {

				MasterListStringValData category3 = new MasterListStringValData();
				category3.setMasterListStringVal1up(masterListServiceDefinition
						.getMasterListStringValCat3()
						.getMasterListStringVal1up());
				category3.setStringValue(masterListServiceDefinition
						.getMasterListStringValCat3().getStringValue());
				category3.setAbbrev(masterListServiceDefinition
						.getMasterListStringValCat3().getAbbrev());
				masterListServiceDefinitionData.setCategory3(category3);
			}

			if (masterListServiceDefinition.getMasterListStringValPostString() != null) {

				MasterListStringValData placeOfService = new MasterListStringValData();
				placeOfService
						.setMasterListStringVal1up(masterListServiceDefinition
								.getMasterListStringValPostString()
								.getMasterListStringVal1up());
				placeOfService.setStringValue(masterListServiceDefinition
						.getMasterListStringValPostString().getStringValue());
				placeOfService.setAbbrev(masterListServiceDefinition
						.getMasterListStringValPostString().getAbbrev());
				masterListServiceDefinitionData
						.setPlaceOfService(placeOfService);
			}

			masterListServiceDefinitionData
					.setCreateBy(masterListServiceDefinition.getCreateBy());

			masterListServiceDefinitionData
					.seteBSInstance1up(masterListServiceDefinition
							.geteBSInstance1up());

			masterListServiceDefinitionData
					.setCreateDt(masterListServiceDefinition.getCreateDt());

			masterListServiceDefinitionData
					.setUpdateBy(masterListServiceDefinition.getUpdateBy());

			masterListServiceDefinitionData
					.setUpdateDt(masterListServiceDefinition.getUpdateDt());

			masterListServiceDefinitionDatas
					.add(masterListServiceDefinitionData);

		}

		MasterListContainer<MasterListServiceDefinitionData> result = new MasterListContainer<MasterListServiceDefinitionData>();
		result.setData(masterListServiceDefinitionDatas);
		result.setPagesCount(pageServiceDefinition.getTotalPages());
		result.setTotalItems(pageServiceDefinition.getTotalElements());

		return result;

	}

	/**
	 * @param id
	 *            Primary Key of MasterList table which is Foreign key in
	 *            MasterListIntVal table
	 * 
	 * @param page
	 *            corresponding page no.
	 * 
	 * @param maxResults
	 *            Maximum no. rows displayed in a page.
	 * 
	 * @return {@link MasterListContainer} It returns the list of all
	 *         MasterListIntValData objects, PagesCount, TotalItems.
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             Whenever user click on Dollar, Percentage,Non-Integer Int
	 *             type from Masterlist on UI, it shows the list of
	 *             MasterListIntVal data's In Use and Locked fields and Value,
	 *             Abbreviation on UI.
	 * 
	 */
	@Transactional(readOnly = true)
	public MasterListContainer<MasterListIntValData> getMasterListIntVal(
			int id, int page, int maxResults) throws DataAccessException {
		Page<MasterListIntVal> pageIntVal = null;
		try {
			pageIntVal = masterListRepository.findIntValMasterList(id,
					new PageRequest(page, maxResults, new Sort(
							Sort.Direction.ASC, "intAsDecimalValue")));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<MasterListIntValData> masterListIntValDatas = new ArrayList<MasterListIntValData>();
		List<Integer> intValInUseList = masterListRepository
				.findAllUseAndUnUseIntValue(id);
		List<Integer> lockedDatas = masterListRepository.findLockedValues(id,
				IsLocked.INT);
		for (MasterListIntVal masterListIntVal : pageIntVal) {

			MasterListIntValData masterListIntValData = new MasterListIntValData();

			masterListIntValData.setMasterListIntVal1up(masterListIntVal
					.getMasterListIntVal1up());

			if (masterListIntVal.getMasterListInt() != null) {
				masterListIntValData.setMasterList1up(masterListIntVal
						.getMasterListInt().getMasterList1up());
			}

			if (intValInUseList.contains(masterListIntVal
					.getMasterListIntVal1up())) {
				masterListIntValData.setInUse(true);
			} else {
				masterListIntValData.setInUse(false);

			}

			if (lockedDatas.contains(masterListIntVal.getMasterListIntVal1up())) {
				masterListIntValData.setIsLocked(true);
			} else {
				masterListIntValData.setIsLocked(false);

			}

			masterListIntValData.setAbbrev(masterListIntVal.getAbbrev());

			masterListIntValData.setIntAsDecimalValue(masterListIntVal
					.getIntAsDecimalValue());

			masterListIntValData.setCreateBy(masterListIntVal.getCreateBy());

			masterListIntValData.seteBSInstance1up(masterListIntVal
					.geteBSInstance1up());

			masterListIntValData.setCreateDt(masterListIntVal.getCreateDt());

			masterListIntValData.setUpdateBy(masterListIntVal.getUpdateBy());

			masterListIntValData.setUpdateDt(masterListIntVal.getUpdateDt());

			if (masterListIntVal.getAccumulation() != null) {
				masterListIntValData.setAccumulation1up(masterListIntVal
						.getAccumulation().getAccumulation1up());
			}

			masterListIntValDatas.add(masterListIntValData);

		}
		MasterListContainer<MasterListIntValData> result = new MasterListContainer<MasterListIntValData>();
		result.setData(masterListIntValDatas);
		result.setPagesCount(pageIntVal.getTotalPages());
		result.setTotalItems(pageIntVal.getTotalElements());

		return result;

	}

	/**
	 * @param id
	 *            Primary Key of MasterList table which is Foreign key in
	 *            MasterListMessageVal table
	 * @param mseeageType
	 *            Message type is from { IPMC, SERL, SETR, SESP }
	 * @param page
	 *            Corresponding Page Number for Pagination
	 * @param maxResults
	 *            Maximum number of rows displayed in a page
	 * 
	 * @return {@link MasterListContainer} It returns the list of all
	 *         MasterListMessageValData objects , PagesCount, TotalItems.
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             Whenever user click on Message Matrix type from Masterlist on
	 *             UI, it shows the list of MasterListMessageVal data's
	 *             MessageCategory, MessageValue, In Use and Locked fields on
	 *             UI.
	 * 
	 */
	@Transactional(readOnly = true)
	public MasterListContainer<MasterListMessageValData> getMasterListMessageVal(
			int id, String messageType, int page, int maxResults)
			throws DataAccessException {

		Page<MasterListMessageVal> pageMessageVal = null;
		try {
			pageMessageVal = masterListRepository.findMessageValForMessageType(
					id, messageType, new PageRequest(page, maxResults));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}
		List<Integer> messageInUseList = masterListRepository
				.findAllUseAndUnUseMessageValue(id, messageType);

		List<Integer> lockedDatas = masterListRepository.findLockedValues(id,
				IsLocked.MESSAGE);

		List<MasterListMessageValData> masterListMessageValDatas = new ArrayList<MasterListMessageValData>();

		for (MasterListMessageVal masterListMessageVal : pageMessageVal) {

			MasterListMessageValData masterListMessageValData = new MasterListMessageValData();

			masterListMessageValData
					.setMasterListMessageVal1up(masterListMessageVal
							.getMasterListMessageVal1up());

			if (masterListMessageVal.getMasterListMessage() != null) {
				masterListMessageValData.setMasterList1up(masterListMessageVal
						.getMasterListMessage().getMasterList1up());
			}
			if (messageInUseList.contains(masterListMessageVal
					.getMasterListMessageVal1up())) {
				masterListMessageValData.setInUse(true);
			} else {
				masterListMessageValData.setInUse(false);
			}
			if (lockedDatas.contains(masterListMessageVal
					.getMasterListMessageVal1up())) {
				masterListMessageValData.setIsLocked(true);
			} else {
				masterListMessageValData.setIsLocked(false);

			}

			masterListMessageValData.setMessageCategory(masterListMessageVal
					.getMessageCategory());

			masterListMessageValData.setMessageType(masterListMessageVal
					.getMessageType());

			masterListMessageValData.setMessageValue(masterListMessageVal
					.getMessageValue());

			masterListMessageValData.setCreateBy(masterListMessageVal
					.getCreateBy());

			masterListMessageValData.setCreateDt(masterListMessageVal
					.getCreateDt());

			masterListMessageValData.seteBSInstance1up(masterListMessageVal
					.geteBSInstance1up());

			masterListMessageValData.setUpdateBy(masterListMessageVal
					.getUpdateBy());

			masterListMessageValData.setUpdateDt(masterListMessageVal
					.getUpdateDt());

			masterListMessageValDatas.add(masterListMessageValData);

		}

		MasterListContainer<MasterListMessageValData> result = new MasterListContainer<MasterListMessageValData>();
		result.setData(masterListMessageValDatas);
		result.setPagesCount(pageMessageVal.getTotalPages());
		result.setTotalItems(pageMessageVal.getTotalElements());

		return result;

	}

	/**
	 * @param id
	 *            Primary Key of MasterList table which is Foreign key in
	 *            MasterListAccumulationVal table.
	 * @param page
	 *            corresponding page no.
	 * 
	 * @param maxResults
	 *            Maximum number of rows displayed in a page.
	 * 
	 * @return {@link MasterListContainer} It returns the list of all
	 *         MasterListAccumulationValData objects, PagesCount, TotalItems.
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             Whenever we click on Accumulations from the list of
	 *             MasterLists, it shows fields in Use, Locked, Accumulation
	 *             Number, Description and Type. Type can be Limits or
	 *             Deductible.
	 * 
	 */

	@Transactional(readOnly = true)
	public MasterListContainer<MasterListAccumulationValData> getMasterListAccumulationVal(
			List<Integer> id, int page, int maxResults)
			throws DataAccessException {

		Page<MasterListAccumulationVal> pageAccumulationVal = null;
		try {
			pageAccumulationVal = masterListRepository
					.findAccumulationValMasterList(id, new PageRequest(page,
							maxResults));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}
		List<Integer> accumInUseList = masterListRepository
				.findAllUseAndUnUseAccumulationValueByMasterList(id);

		List<Integer> lockedDatas = masterListRepository.findLockedValues(
				id.get(0), IsLocked.ACCUMULATION);

		List<MasterListAccumulationValData> masterListAccumulationValDatas = new ArrayList<MasterListAccumulationValData>();

		for (MasterListAccumulationVal masterListAccumulationVal : pageAccumulationVal) {

			MasterListAccumulationValData masterListAccumulationValData = new MasterListAccumulationValData();

			AccumulationType accType = new AccumulationType();

			if (masterListAccumulationVal.getMasterListAccumVal() != null) {

				accType.setId(masterListAccumulationVal.getMasterListAccumVal()
						.getMasterList1up());

				accType.setName(masterListAccumulationVal
						.getMasterListAccumVal().getName());

				masterListAccumulationValData.setType(accType);
			}

			if (accumInUseList.contains(masterListAccumulationVal
					.getAccumulation1up())) {
				masterListAccumulationValData.setInUse(true);
			} else {
				masterListAccumulationValData.setInUse(false);

			}

			if (lockedDatas.contains(masterListAccumulationVal
					.getAccumulation1up())) {
				masterListAccumulationValData.setIsLocked(true);
			} else {
				masterListAccumulationValData.setIsLocked(false);

			}

			masterListAccumulationValData
					.setAccumulation1up(masterListAccumulationVal
							.getAccumulation1up());

			masterListAccumulationValData
					.setDescription(masterListAccumulationVal.getDescription());

			masterListAccumulationValData.setNumber(masterListAccumulationVal
					.getNumber());

			masterListAccumulationValData.setCreateBy(masterListAccumulationVal
					.getCreateBy());

			masterListAccumulationValData
					.seteBSInstance1up(masterListAccumulationVal
							.geteBSInstance1up());

			masterListAccumulationValData.setCreateDt(masterListAccumulationVal
					.getCreateDt());

			masterListAccumulationValData.setUpdateBy(masterListAccumulationVal
					.getUpdateBy());

			masterListAccumulationValData.setUpdateDt(masterListAccumulationVal
					.getUpdateDt());

			masterListAccumulationValDatas.add(masterListAccumulationValData);

		}

		MasterListContainer<MasterListAccumulationValData> result = new MasterListContainer<MasterListAccumulationValData>();
		result.setData(masterListAccumulationValDatas);
		result.setPagesCount(pageAccumulationVal.getTotalPages());
		result.setTotalItems(pageAccumulationVal.getTotalElements());

		return result;

	}

	/**
	 * @param id
	 *            Primary Key of MasterList table which is Foreign key in
	 *            MasterListLimitVal table.
	 * 
	 * @param page
	 *            corresponding page no.
	 * 
	 * @param maxResults
	 *            Maximum number of rows displayed in a page.
	 * 
	 * @return {@link MasterListContainer} It returns the list of all
	 *         MasterListLimitValData objects, PagesCount, TotalItems.
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             Whenever user click on Limit Matrix type from Masterlist on
	 *             UI, it shows the list of MasterListLimitVal data's In Use,
	 *             Locked, Name, Description, Category, Coverage level, Time
	 *             Period, Benefit, Quantity, Quantity qualifier fields on UI.
	 */
	@Transactional(readOnly = true)
	public MasterListContainer<MasterListLimitValData> getMasterListLimitVal(
			int id, int page, int maxResults) throws DataAccessException {
		Page<MasterListLimitVal> pageLimitVal = null;
		try {
			pageLimitVal = masterListRepository.findMasterListLimitVal(id,
					new PageRequest(page, maxResults, new Sort(
							Sort.Direction.ASC, "limitCategory")));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<Integer> masterListLimitValList = masterListRepository
				.findAllUseAndUnUseLimitValue(id);

		List<Integer> lockedDatas = masterListRepository.findLockedValues(id,
				IsLocked.LIMIT);

		List<MasterListLimitValData> masterListLimitValDatas = new ArrayList<MasterListLimitValData>();

		for (MasterListLimitVal masterListLimitVal : pageLimitVal) {

			MasterListLimitValData masterListLimitValData = new MasterListLimitValData();

			masterListLimitValData.setMasterListLimitValId(masterListLimitVal
					.getMasterListLimitVal1up());

			masterListLimitValData.setMasterList1Up(masterListLimitVal
					.getMasterListLimit().getMasterList1up());

			masterListLimitValData.setLimitName(masterListLimitVal
					.getLimitName());

			masterListLimitValData.setLimitDescription(masterListLimitVal
					.getLimitDescription());

			masterListLimitValData.setLimitCategory(masterListLimitVal
					.getLimitCategory());

			if (masterListLimitValList.contains(masterListLimitVal
					.getMasterListLimitVal1up())) {
				masterListLimitValData.setInUse(true);
			} else {
				masterListLimitValData.setInUse(false);

			}

			if (lockedDatas.contains(masterListLimitVal
					.getMasterListLimitVal1up())) {
				masterListLimitValData.setIsLocked(true);
			} else {
				masterListLimitValData.setIsLocked(false);

			}

			if (masterListLimitVal.getMasterListStringValCoverge() != null) {
				masterListLimitValData.setCoverageLevel(masterListLimitVal
						.getMasterListStringValCoverge()
						.getMasterListStringVal1up());
			}

			if (masterListLimitVal.getMasterListStringValTimePeriod() != null) {
				masterListLimitValData.setTimePeriod(masterListLimitVal
						.getMasterListStringValTimePeriod()
						.getMasterListStringVal1up());
			}

			masterListLimitValData.setQuantityTo(masterListLimitVal
					.getQuantityTo());

			masterListLimitValData.setQuantityFrom(masterListLimitVal
					.getQuantityFrom());

			if (masterListLimitVal.getMasterListStringValQuanQualifier() != null) {
				masterListLimitValData.setQuantityQualifier(masterListLimitVal
						.getMasterListStringValQuanQualifier()
						.getMasterListStringVal1up());
			}

			masterListLimitValData
					.setBenefitAmtInDollarsFlag(masterListLimitVal
							.getBenefitAmtInDollarsFlag());

			masterListLimitValData
					.setBenefitAmtInPercentFlag(masterListLimitVal
							.getBenefitAmtInPercentFlag());

			masterListLimitValData.seteBSInstance1up(masterListLimitVal
					.geteBSInstance1up());

			masterListLimitValData
					.setCreateBy(masterListLimitVal.getCreateBy());

			masterListLimitValData
					.setCreateDt(masterListLimitVal.getCreateDt());

			masterListLimitValData
					.setUpdateBy(masterListLimitVal.getUpdateBy());

			masterListLimitValData
					.setUpdateDt(masterListLimitVal.getUpdateDt());

			masterListLimitValData.setBenefitAmount(masterListLimitVal
					.getBenefitAmount());

			masterListLimitValData.setReinstatementAmount(masterListLimitVal
					.getReinstatementAmount());

			masterListLimitValData
					.setAmountsSetInBenefitFlag(masterListLimitVal
							.getAmountsSetInBenefitFlag());

			masterListLimitValData.setSequenceOrder(masterListLimitVal
					.getSequenceOrder());

			masterListLimitValData
					.setQuantitySetInBenefitFlag(masterListLimitVal
							.getQuantitySetInBenefitFlag());

			if (masterListLimitVal.getMasterListAcumlationVal() != null) {
				masterListLimitValData.setAccumulation1up(masterListLimitVal
						.getMasterListAcumlationVal().getAccumulation1up());
			}

			masterListLimitValDatas.add(masterListLimitValData);

		}
		MasterListContainer<MasterListLimitValData> result = new MasterListContainer<MasterListLimitValData>();
		result.setData(masterListLimitValDatas);
		result.setData(masterListLimitValDatas);
		result.setPagesCount(pageLimitVal.getTotalPages());
		result.setTotalItems(pageLimitVal.getTotalElements());

		return result;

	}

	/**
	 * @param id
	 *            Primary Key of MasterListLimitVal table
	 * 
	 * @return{@link MasterListLimitValData} It returns the
	 *               MasterListLimitValData object
	 * 
	 * @throws DataAccessException
	 *             <p>
	 *             Whenever user click on any limit data from the list of Limit
	 *             Matrix it display all details regarding the specific limit
	 *             data.
	 */
	@Transactional(readOnly = true)
	public MasterListLimitValData getMasterListLimitValById(int id)
			throws DataAccessException {
		MasterListLimitVal limitVal = null;
		try {
			limitVal = masterListRepository.findMasterListLimitValById(id);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		MasterListLimitValData masterListLimitValData = new MasterListLimitValData();

		masterListLimitValData.setMasterListLimitValId(limitVal
				.getMasterListLimitVal1up());

		masterListLimitValData.setMasterList1Up(limitVal.getMasterListLimit()
				.getMasterList1up());

		masterListLimitValData.setLimitName(limitVal.getLimitName());

		masterListLimitValData.setLimitDescription(limitVal
				.getLimitDescription());

		masterListLimitValData.setLimitCategory(limitVal.getLimitCategory());

		if (limitVal.getMasterListStringValCoverge() != null) {
			masterListLimitValData.setCoverageLevel(limitVal
					.getMasterListStringValCoverge()
					.getMasterListStringVal1up());
		}

		if (limitVal.getMasterListStringValTimePeriod() != null) {
			masterListLimitValData.setTimePeriod(limitVal
					.getMasterListStringValTimePeriod()
					.getMasterListStringVal1up());
		}

		masterListLimitValData.setQuantityTo(limitVal.getQuantityTo());

		masterListLimitValData.setQuantityFrom(limitVal.getQuantityFrom());

		if (limitVal.getMasterListStringValQuanQualifier() != null) {
			masterListLimitValData.setQuantityQualifier(limitVal
					.getMasterListStringValQuanQualifier()
					.getMasterListStringVal1up());
		}

		masterListLimitValData.setBenefitAmtInDollarsFlag(limitVal
				.getBenefitAmtInDollarsFlag());

		masterListLimitValData.setBenefitAmtInPercentFlag(limitVal
				.getBenefitAmtInPercentFlag());

		masterListLimitValData.seteBSInstance1up(limitVal.geteBSInstance1up());

		masterListLimitValData.setCreateBy(limitVal.getCreateBy());

		masterListLimitValData.setCreateDt(limitVal.getCreateDt());

		masterListLimitValData.setUpdateBy(limitVal.getUpdateBy());

		masterListLimitValData.setUpdateDt(limitVal.getUpdateDt());

		masterListLimitValData.setBenefitAmount(limitVal.getBenefitAmount());

		masterListLimitValData.setReinstatementAmount(limitVal
				.getReinstatementAmount());

		masterListLimitValData.setAmountsSetInBenefitFlag(limitVal
				.getAmountsSetInBenefitFlag());

		masterListLimitValData.setSequenceOrder(limitVal.getSequenceOrder());

		masterListLimitValData.setQuantitySetInBenefitFlag(limitVal
				.getQuantitySetInBenefitFlag());

		if (limitVal.getMasterListAcumlationVal() != null) {
			masterListLimitValData.setAccumulation1up(limitVal
					.getMasterListAcumlationVal().getAccumulation1up());
		}

		return masterListLimitValData;

	}

	/**
	 * @param intValList
	 *            List Of MasterListIntValData Objects
	 * 
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method insert MasterListIntVal object into
	 *             MasterListIntVal table. It returns SUCCESS if all objects
	 *             inserted successfully. It returns WARNING if at least one
	 *             unsuccessful. Otherwise it throws exception
	 */
	@Transactional
	public ServiceOperationResult saveUpdateMasterListIntVal(
			List<MasterListIntValData> intValDataList)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();

		if (intValDataList == null) {
			throw new DataAccessException();
		}

		try {
			for (MasterListIntValData data : intValDataList) {

				MasterListIntVal temp = null;

				if (data.getMasterListIntVal1up() != null
						&& data.getMasterListIntVal1up() > 0) {
					temp = masterListRepository.findMasterListIntValById(data
							.getMasterListIntVal1up());
					temp.setUpdateBy(data.getUpdateBy());
					temp.setUpdateDt(new Date());
				} else {
					temp = new MasterListIntVal();
					temp.setCreateBy(data.getCreateBy());
					temp.setCreateDt(new Date());
				}

				if (data.getMasterList1up() != null) {
					temp.setMasterListInt(masterListRepository
							.findMasterListById(data.getMasterList1up()));
				}
				temp.setIntAsDecimalValue(data.getIntAsDecimalValue());
				temp.setAbbrev(data.getAbbrev());

				if (data.getAccumulation1up() != null) {
					temp.setAccumulation(masterListRepository
							.findAccumulationById(data.getAccumulation1up()));
				}
				temp.seteBSInstance1up(data.geteBSInstance1up());
				if (data.getMasterListIntVal1up() != null
						&& data.getMasterListIntVal1up() > 0) {
					masterListRepository.updateData(temp);
				} else {
					masterListRepository.saveData(temp);
				}
			}

		} catch (PersistenceException e) {

			throw new DataAccessException(e.getMessage());

		}

		return operationResult;
	}

	/**
	 * @param stringData
	 *            List of MasterListStringVal Objects
	 * 
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method insert MasterListStringVal object into
	 *             MasterListStringVal table. It returns SUCCESS if all objects
	 *             inserted successfully. It returns WARNING if at least one
	 *             unsuccessful. Otherwise it throws exception.
	 */
	@Transactional
	public ServiceOperationResult saveUpdateMasterListStringVal(
			List<MasterListStringValData> stringDataList)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();

		if (stringDataList == null) {
			throw new DataAccessException();
		}

		try {
			for (MasterListStringValData data : stringDataList) {
				MasterListStringVal temp = null;
				if (data.getMasterListStringVal1up() != null
						&& data.getMasterListStringVal1up() > 0) {
					temp = masterListRepository
							.findMasterListStringValById(data
									.getMasterListStringVal1up());
					temp.setUpdateBy(data.getUpdateBy());
					temp.setUpdateDt(new Date());

				} else {
					temp = new MasterListStringVal();
					temp.setCreateBy(data.getCreateBy());
					temp.setCreateDt(new Date());
				}

				if (data.getMasterList1up() != null) {
					temp.setMasterListString(masterListRepository
							.findMasterListById(data.getMasterList1up()));
				}

				temp.setStringValue(data.getStringValue());
				temp.seteBSInstance1up(data.geteBSInstance1up());
				temp.setAbbrev(data.getAbbrev());
				if (data.getMasterListStringVal1up() != null
						&& data.getMasterListStringVal1up() > 0) {
					masterListRepository.updateData(temp);
				} else {
					masterListRepository.saveData(temp);
				}
			}

		} catch (PersistenceException e) {

			throw new DataAccessException(e.getMessage());

		}

		return operationResult;
	}

	/**
	 * @param limitValList
	 *            List Of MasterListLimitValData Objects
	 * 
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method insert MasterListLimitVal object into
	 *             MasterListLimitVal table. It returns SUCCESS if all objects
	 *             inserted successfully. It returns WARNING if at least one
	 *             unsuccessful. Otherwise it throws exception.
	 * 
	 */
	@Transactional
	public ServiceOperationResult saveUpdateMasterListLimitVal(
			List<MasterListLimitValData> limitValDataList)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();

		if (limitValDataList == null) {
			throw new DataAccessException();
		}

		try {
			for (MasterListLimitValData data : limitValDataList) {

				MasterListLimitVal lval = null;

				if (data.getMasterListLimitValId() != null
						&& data.getMasterListLimitValId() > 0) {
					lval = masterListRepository.findMasterListLimitValById(data
							.getMasterListLimitValId());
					lval.setUpdateBy(data.getUpdateBy());
					lval.setUpdateDt(new Date());
				} else {
					lval = new MasterListLimitVal();
					lval.setCreateBy(data.getCreateBy());
					lval.setCreateDt(new Date());
				}

				if (data.getMasterList1Up() != null) {
					lval.setMasterListLimit(masterListRepository
							.findMasterListById(data.getMasterList1Up()));
				}

				lval.seteBSInstance1up(data.geteBSInstance1up());

				lval.setLimitCategory(data.getLimitCategory());
				lval.setLimitName(data.getLimitName());
				lval.setLimitDescription(data.getLimitDescription());

				if (data.getCoverageLevel() != null) {
					lval.setMasterListStringValCoverge(masterListRepository
							.findMasterListStringValById(data
									.getCoverageLevel()));
				}

				if (data.getTimePeriod() != null) {
					lval.setMasterListStringValTimePeriod(masterListRepository
							.findMasterListStringValById(data.getTimePeriod()));
				}

				lval.setBenefitAmtInDollarsFlag(data
						.getBenefitAmtInDollarsFlag());
				lval.setBenefitAmtInPercentFlag(data
						.getBenefitAmtInPercentFlag());
				lval.setBenefitAmount(data.getBenefitAmount());
				lval.setReinstatementAmount(data.getReinstatementAmount());
				lval.setAmountsSetInBenefitFlag(data
						.getAmountsSetInBenefitFlag());
				lval.setQuantityFrom(data.getQuantityFrom());
				lval.setQuantityTo(data.getQuantityTo());

				if (data.getQuantityQualifier() != null) {
					lval.setMasterListStringValQuanQualifier(masterListRepository
							.findMasterListStringValById(data
									.getQuantityQualifier()));
				}

				lval.setSequenceOrder(data.getSequenceOrder());

				lval.setQuantitySetInBenefitFlag(data
						.getQuantitySetInBenefitFlag());

				if (data.getAccumulation1up() != null) {
					lval.setMasterListAcumlationVal(masterListRepository
							.findAccumulationById(data.getAccumulation1up()));
				}

				if (data.getMasterListLimitValId() != null
						&& data.getMasterListLimitValId() > 0) {
					masterListRepository.updateData(lval);
				} else {
					masterListRepository.saveData(lval);
				}

			}

		} catch (PersistenceException e) {

			throw new DataAccessException(e.getMessage());
		}

		return operationResult;
	}

	/**
	 * @param serviceDefinationList
	 *            List of MasterListServiceDefination Objects
	 * 
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method insert MasterListServiceDefination object into
	 *             MasterListServiceDefination table. It returns SUCCESS if all
	 *             objects inserted successfully. It returns WARNING if at least
	 *             one unsuccessful. Otherwise it throws exception.
	 */

	@Transactional
	public ServiceOperationResult saveUpdateMasterListServiceDefinition(
			List<MasterListServiceDefinitionData> serviceDefinitionList)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();

		if (serviceDefinitionList == null) {
			throw new DataAccessException();
		}

		try {
			for (MasterListServiceDefinitionData data : serviceDefinitionList) {
				MasterListServiceDefinition temp = null;

				if (data.getMasterListServiceDefinitionId() != null
						&& data.getMasterListServiceDefinitionId() > 0) {

					temp = masterListRepository
							.findMasterListServiceDefinitionById(data
									.getMasterListServiceDefinitionId());
					temp.setUpdateBy(data.getUpdateBy());
					temp.setUpdateDt(new Date());
				} else {
					temp = new MasterListServiceDefinition();
					temp.setCreateBy(data.getCreateBy());
					temp.setCreateDt(new Date());
				}

				if (data.getMasterList1Up() != null) {
					MasterList mdata = masterListRepository
							.findMasterListById(data.getMasterList1Up());
					temp.setMasterListService(mdata);
				}
				if (data.getCategory1().getMasterListStringVal1up() != null) {
					MasterListStringVal mstring1 = masterListRepository
							.findMasterListStringValById(data.getCategory1()
									.getMasterListStringVal1up());
					temp.setMasterListStringValCat1(mstring1);
				}
				if (data.getCategory2().getMasterListStringVal1up() != null) {
					MasterListStringVal mstring2 = masterListRepository
							.findMasterListStringValById(data.getCategory2()
									.getMasterListStringVal1up());
					temp.setMasterListStringValCat2(mstring2);
				}
				if (data.getCategory3().getMasterListStringVal1up() != null) {
					MasterListStringVal mstring3 = masterListRepository
							.findMasterListStringValById(data.getCategory3()
									.getMasterListStringVal1up());
					temp.setMasterListStringValCat3(mstring3);
				}
				if (data.getPlaceOfService().getMasterListStringVal1up() != null) {
					MasterListStringVal mstring4 = masterListRepository
							.findMasterListStringValById(data
									.getPlaceOfService()
									.getMasterListStringVal1up());
					temp.setMasterListStringValPostString(mstring4);
				}

				temp.seteBSInstance1up(data.geteBSInstance1up());
				if (data.getMasterListServiceDefinitionId() != null
						&& data.getMasterListServiceDefinitionId() > 0) {
					masterListRepository.updateData(temp);
				} else {
					masterListRepository.saveData(temp);
				}
			}

		} catch (PersistenceException e) {

			throw new DataAccessException(e.getMessage());
		}

		return operationResult;
	}

	/**
	 * @param accumulationvalList
	 *            List of MasterListAccumulationVal Objects
	 * 
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method insert MasterListAccumulationVal object into
	 *             MasterListAccumulationVal table. It returns SUCCESS if all
	 *             objects inserted successfully. It returns WARNING if at least
	 *             one unsuccessful. Otherwise it throws exception.
	 * */

	@Transactional
	public ServiceOperationResult saveUpdateMasterListAccumulationVal(
			List<MasterListAccumulationValData> accumulationvalDataList)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();

		if (accumulationvalDataList == null) {
			throw new DataAccessException();
		}

		try {
			for (MasterListAccumulationValData data : accumulationvalDataList) {
				MasterListAccumulationVal temp = null;

				if (data.getAccumulation1up() != null
						&& data.getAccumulation1up() > 0) {
					temp = masterListRepository.findAccumulationById(data
							.getAccumulation1up());
					temp.setUpdateBy(data.getUpdateBy());
					temp.setUpdateDt(new Date());

				} else {
					temp = new MasterListAccumulationVal();
					temp.setCreateBy(data.getCreateBy());
					temp.setCreateDt(new Date());

				}
				if (data.getType().getId() != null) {
					MasterList mdata = masterListRepository
							.findMasterListById(data.getType().getId());
					temp.setMasterListAccumVal(mdata);

				}

				temp.setNumber(data.getNumber());
				temp.setDescription(data.getDescription());
				temp.seteBSInstance1up(data.geteBSInstance1up());

				if (data.getAccumulation1up() != null
						&& data.getAccumulation1up() > 0) {
					masterListRepository.updateData(temp);

				} else {
					masterListRepository.saveData(temp);
				}
			}

		} catch (PersistenceException e) {
			throw new DataAccessException(e.getMessage());

		}

		return operationResult;

	}

	/**
	 * @param messagevalList
	 *            List of MasterListMessageVal Objects
	 * 
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method insert MasterListMessageVal object into
	 *             MasterListMessageVal table. It returns SUCCESS if all objects
	 *             inserted successfully. It returns WARNING if at least one
	 *             unsuccessful. Otherwise it throws exception.
	 */

	@Transactional
	public ServiceOperationResult saveUpdateMasterListMessageVal(
			List<MasterListMessageValData> messagevalDataList)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();

		if (messagevalDataList == null) {
			throw new DataAccessException();
		}

		try {
			for (MasterListMessageValData data : messagevalDataList) {
				MasterListMessageVal temp = null;
				if (data.getMasterListMessageVal1up() != null
						&& data.getMasterListMessageVal1up() > 0) {
					temp = masterListRepository
							.findMasterListMessageValListById(data
									.getMasterListMessageVal1up());
					temp.setUpdateBy(data.getUpdateBy());
					temp.setUpdateDt(new Date());

				} else {
					temp = new MasterListMessageVal();
					temp.setCreateBy(data.getCreateBy());
					temp.setCreateDt(new Date());

				}

				temp.setMessageCategory(data.getMessageCategory());
				temp.setMessageType(data.getMessageType());
				temp.setMessageValue(data.getMessageValue());
				if (data.getMasterList1up() != null) {
					MasterList mdata = masterListRepository
							.findMasterListById(data.getMasterList1up());
					temp.setMasterListMessage(mdata);
				}
				temp.seteBSInstance1up(data.geteBSInstance1up());

				if (data.getMasterListMessageVal1up() != null
						&& data.getMasterListMessageVal1up() > 0) {
					masterListRepository.updateData(temp);

				} else {
					masterListRepository.saveData(temp);
				}
			}

		} catch (PersistenceException e) {
			throw new DataAccessException(e.getMessage());

		}

		return operationResult;

	}

	/**
	 * @param ids
	 *            List of primary keys of corresponding MasterListMessageVal
	 * 
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method delete MasterListMessageVal object from
	 *             MasterListMessageVal table. It returns SUCCESS if all objects
	 *             deleted successfully. It returns WARNING if at least one
	 *             unsuccessful. Otherwise it throws exception.
	 */
	@Transactional
	public ServiceOperationResult deleteMasterListMessageVal(List<Integer> ids)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		List<Integer> deleteList = new ArrayList<Integer>();
		try {

			deleteList = masterListRepository.findInUseForMessageValue(ids);

			if (deleteList.size() > 0) {

				operationResult.setOperationResult(OperationResult.FAILURE);
				messages.add("Error deleting value for MasterListMessageVal");
				return operationResult;

			} else {
				for (Integer id : ids) {
					if (deleteList.contains(id) == false) {
						masterListRepository.deleteMasterListMessageValById(id);
					}
				}

			}

		} catch (PersistenceException e) {
			throw new DataAccessException(e.getMessage());
		}

		return operationResult;
	}

	/**
	 * @param ids
	 *            List of primary keys of corresponding MasterListStringVal
	 * 
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method delete MasterListStringVal object from
	 *             MasterListStringVal table. It returns SUCCESS if all objects
	 *             deleted successfully. It returns WARNING if at least one
	 *             unsuccessful. Otherwise it throws exception.
	 */
	@Transactional
	public ServiceOperationResult deleteMasterListStringVal(List<Integer> ids)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		List<Integer> deleteList = new ArrayList<Integer>();

		try {
			deleteList = masterListRepository.findInUseForStringValue(ids);
			if (deleteList.size() > 0) {

				operationResult.setOperationResult(OperationResult.FAILURE);
				messages.add("Error deleting value for MasterListStringVal");
				return operationResult;

			} else {
				for (Integer id : ids) {
					if (deleteList.contains(id) == false) {
						masterListRepository.deleteMasterListStringValById(id);
					}
				}
			}

		} catch (PersistenceException e) {

			throw new DataAccessException(e.getMessage());
		}

		return operationResult;
	}

	/**
	 * @param ids
	 *            List of primary keys of corresponding MasterListIntVal
	 * 
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method delete MasterListIntVal object from
	 *             MasterListIntVal table. It returns SUCCESS if all objects
	 *             deleted successfully. It returns WARNING if at least one
	 *             unsuccessful. Otherwise it throws exception.
	 */
	@Transactional
	public ServiceOperationResult deleteMasterListIntVal(List<Integer> ids)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		List<Integer> deleteList = new ArrayList<Integer>();
		try {

			deleteList = masterListRepository.findAllUseAndUnUseIntValue(ids);
			if (deleteList.size() > 0) {

				operationResult.setOperationResult(OperationResult.FAILURE);
				messages.add("Error deleting value for MasterListIntVal");
				return operationResult;

			} else {

				for (Integer id : ids) {
					if (deleteList.contains(id) == false) {
						masterListRepository.deleteMasterListIntValById(id);
					}
				}

			}

		} catch (PersistenceException e) {

			throw new DataAccessException(e.getMessage());
		}

		return operationResult;
	}

	/**
	 * @param ids
	 *            List of primary keys of corresponding
	 *            MasterListServiceDefinition
	 * 
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method delete MasterListServiceDefinition object from
	 *             MasterListServiceDefinition table. It returns SUCCESS if all
	 *             objects deleted successfully. It returns WARNING if at least
	 *             one unsuccessful. Otherwise it throws exception.
	 */
	@Transactional
	public ServiceOperationResult deleteMasterListServiceDefinition(
			List<Integer> ids) throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		List<Integer> deleteList = new ArrayList<Integer>();

		try {
			deleteList = masterListRepository
					.findAllUseAndUnUseServiceDefValue(ids);

			if (deleteList.size() > 0) {

				operationResult.setOperationResult(OperationResult.FAILURE);
				messages.add("Error deleting value for MasterListServiceDefinition");
				return operationResult;

			} else {

				for (Integer id : ids) {
					if (deleteList.contains(id) == false) {
						masterListRepository
								.deleteMasterListServiceDefinitionById(id);
					}
				}

			}

		} catch (PersistenceException e) {

			throw new DataAccessException(e.getMessage());
		}

		return operationResult;
	}

	/**
	 * @param ids
	 *            List of primary keys of corresponding
	 *            MasterListAccumulationVal
	 * 
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method delete MasterListAccumulationVal object from
	 *             MasterListAccumulationVal table. It returns SUCCESS if all
	 *             objects deleted successfully. It returns WARNING if at least
	 *             one unsuccessful. Otherwise it throws exception.
	 */
	@Transactional
	public ServiceOperationResult deleteMasterListAccumulationVal(
			List<Integer> ids) throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		List<Integer> deleteList = new ArrayList<Integer>();
		try {

			deleteList = masterListRepository
					.findAllUseAndUnUseAccumulationValue(ids);

			if (deleteList.size() > 0) {

				operationResult.setOperationResult(OperationResult.FAILURE);
				messages.add("Error deleting value for MasterListAccumulationVal");
				return operationResult;

			} else {

				for (Integer id : ids) {
					if (deleteList.contains(id) == false) {
						masterListRepository
								.deleteMasterListAccumulationById(id);
					}
				}

			}

		} catch (PersistenceException e) {

			throw new DataAccessException(e.getMessage());
		}

		return operationResult;
	}

	/**
	 * @param ids
	 *            List of primary keys of corresponding MasterListLimitVal
	 * 
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method delete MasterListLimitVal object from
	 *             MasterListLimitVal table. It returns SUCCESS if all objects
	 *             deleted successfully. It returns WARNING if at least one
	 *             unsuccessful. Otherwise it throws exception.
	 */
	@Transactional
	public ServiceOperationResult deleteMasterListLimitVal(List<Integer> ids)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		List<Integer> deleteList = new ArrayList<Integer>();
		try {
			deleteList = masterListRepository.findAllUseAndUnUseLimitValue(ids);

			if (deleteList.size() > 0) {

				operationResult.setOperationResult(OperationResult.FAILURE);
				messages.add("Error deleting value for MasterListLimitVal");
				return operationResult;

			} else {

				for (Integer id : ids) {
					if (deleteList.contains(id) == false) {
						masterListRepository.deleteMasterListLimitValById(id);
					}
				}

			}

		} catch (PersistenceException e) {

			throw new DataAccessException(e.getMessage());
		}

		return operationResult;
	}

}
