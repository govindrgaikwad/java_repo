package com.tmg.ebscore.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tmg.ebscore.orm.masterlist.MasterList;
import com.tmg.ebscore.orm.masterlist.MasterListAccumulationVal;
import com.tmg.ebscore.orm.masterlist.MasterListIntVal;
import com.tmg.ebscore.orm.masterlist.MasterListLimitVal;
import com.tmg.ebscore.orm.masterlist.MasterListMessageVal;
import com.tmg.ebscore.orm.masterlist.MasterListServiceDefinition;
import com.tmg.ebscore.orm.masterlist.MasterListStringVal;

public interface MasterListRepository extends JpaRepository<MasterList, Long>,
		MasterListCustomRepository {

	@Query(value = "SELECT list from MasterList list left join fetch "
			+ "list.masterListDataType "
			+ "where list.activeFlag = :showInactive "
			+ "and list.visibleAtMasterList = TRUE", countQuery = "SELECT count(list) from MasterList list "
			+ "where list.activeFlag = :showInactive and list.visibleAtMasterList = TRUE")
	public Page<MasterList> findMasterListAtMasterListLevel(
			@Param("showInactive") boolean showInactive, Pageable pageable);

	@Query(value = "SELECT list from MasterList list left join fetch "
			+ "list.masterListDataType "
			+ "where list.activeFlag = :showInactive "
			+ "and list.visibleAtTemplate = TRUE", countQuery = "SELECT count(list) from MasterList list "
			+ "where list.activeFlag = :showInactive and list.visibleAtTemplate = TRUE")
	public Page<MasterList> findMasterListAtTemplateLevel(
			@Param("showInactive") boolean showInactive, Pageable pageable);

	@Query(value = "SELECT list from MasterListAccumulationVal list "
			+ "left join fetch list.masterListAccumVal masterList "
			+ "left join fetch masterList.masterListDataType "
			+ "where list.masterListAccumVal.masterList1up in :id ", countQuery = "SELECT count(list) from MasterListAccumulationVal list "
			+ "where masterListAccumVal.masterList1up in :id")
	public Page<MasterListAccumulationVal> findAccumulationValMasterList(
			@Param("id") List<Integer> id, Pageable pageable);

	@Query(value = "SELECT list from MasterListIntVal list"
			+ " left join fetch list.masterListInt"
			+ " left join fetch list.accumulation"
			+ " where list.masterListInt.masterList1up = :id ", countQuery = "SELECT count(list) from MasterListIntVal list"
			+ " where list.masterListInt.masterList1up = :id ")
	public Page<MasterListIntVal> findIntValMasterList(@Param("id") int id,
			Pageable pageable);

	@Query(value = "SELECT list from MasterListLimitVal list "
			+ " left join fetch list.masterListLimit "
			+ "left join fetch list.masterListStringValCoverge "
			+ "left join fetch list.masterListStringValTimePeriod  "
			+ "left join fetch list.masterListStringValQuanQualifier "
			+ "left join fetch list.masterListAcumlationVal "
			+ "where list.masterListLimit.masterList1up = :id", countQuery = "SELECT count(list) from MasterListLimitVal list "
			+ "where list.masterListLimit.masterList1up = :id ")
	public Page<MasterListLimitVal> findMasterListLimitVal(@Param("id") int id,
			Pageable pageable);

	@Query(value = "SELECT list from MasterListMessageVal list "
			+ "left join fetch list.masterListMessage "
			+ "where list.masterListMessage.masterList1up = :id "
			+ "and list.messageType = :messageType", countQuery = "SELECT count(list) from MasterListMessageVal list "
			+ "where list.masterListMessage.masterList1up = :id "
			+ "and list.messageType = :messageType")
	public Page<MasterListMessageVal> findMessageValForMessageType(
			@Param("id") int id, @Param("messageType") String messageType,
			Pageable pageable);

	@Query(value = "SELECT list from MasterListMessageVal list "
			+ "left join fetch list.masterListMessage "
			+ "where list.masterListMessage.masterList1up = :id ", countQuery = "SELECT count(list) from MasterListMessageVal list "
			+ "where list.masterListMessage.masterList1up = :id ")
	public Page<MasterListMessageVal> findMessageVal(@Param("id") int id,
			Pageable pageable);

	@Query(value = "SELECT list from MasterListServiceDefinition list"
			+ " left join fetch list.masterListService"
			+ " left join fetch list.masterListStringValCat1"
			+ " left join fetch list.masterListStringValCat2"
			+ " left join fetch list.masterListStringValCat3"
			+ " left join fetch list.masterListStringValPostString"
			+ " where list.masterListService.masterList1up = :id ", countQuery = "SELECT count(list) from MasterListServiceDefinition list"
			+ " where list.masterListService.masterList1up = :id ")
	public Page<MasterListServiceDefinition> findMasterListServiceDefination(
			@Param("id") int id, Pageable pageable);

	@Query(value = "SELECT list from MasterListStringVal list"
			+ " left join fetch list.masterListString"
			+ " where list.masterListString.masterList1up = :id ", countQuery = "SELECT count(list) from MasterListStringVal list"
			+ " where list.masterListString.masterList1up = :id ")
	public Page<MasterListStringVal> findStringValMasterList(
			@Param("id") int id, Pageable pageable);

	// ********** Find By Id Methods ************** //

	@Query(value = "SELECT list from MasterList list left join fetch "
			+ "list.masterListDataType where list.masterList1up = :id ")
	public MasterList findMasterListById(@Param("id") int id);

	@Query(value = "SELECT list from MasterListAccumulationVal list "
			+ "left join fetch list.masterListAccumVal masterList "
			+ "left join fetch masterList.masterListDataType "
			+ "where list.accumulation1up = :id ")
	public MasterListAccumulationVal findAccumulationById(@Param("id") int id);

	@Query(value = "SELECT list from MasterListIntVal list "
			+ " left join fetch list.masterListInt "
			+ " left join fetch list.accumulation "
			+ "where list.masterListIntVal1up = :id ")
	public MasterListIntVal findMasterListIntValById(@Param("id") int id);

	@Query(value = "SELECT list from MasterListLimitVal list "
			+ "left join fetch list.masterListLimit "
			+ "left join fetch list.masterListStringValCoverge "
			+ "left join fetch list.masterListStringValTimePeriod  "
			+ "left join fetch list.masterListStringValQuanQualifier "
			+ "left join fetch list.masterListAcumlationVal "
			+ "where list.masterListLimitVal1up = :id")
	public MasterListLimitVal findMasterListLimitValById(@Param("id") int id);

	@Query(value = "SELECT list from MasterListMessageVal list "
			+ "left join fetch list.masterListMessage "
			+ "where list.masterListMessageVal1up = :id ")
	public MasterListMessageVal findMasterListMessageValListById(
			@Param("id") int id);

	@Query(value = "SELECT list from MasterListServiceDefinition list"
			+ " left join fetch list.masterListService"
			+ " left join fetch list.masterListStringValCat1"
			+ " left join fetch list.masterListStringValCat2"
			+ " left join fetch list.masterListStringValCat3"
			+ " left join fetch list.masterListStringValPostString"
			+ " where list.masterListServiceDefinition1up = :id ")
	public MasterListServiceDefinition findMasterListServiceDefinitionById(
			@Param("id") int id);

	@Query(value = "SELECT list from MasterListStringVal list"
			+ " left join fetch list.masterListString "
			+ "where list.masterListStringVal1up = :id ")
	public MasterListStringVal findMasterListStringValById(@Param("id") int id);

	// ******************************************************************************************//

	// ********** Delete By Id Methods ************** //

	@Modifying
	@Query(value = "Delete from MasterList list where list.masterList1up = :id ")
	public void deleteMasterListById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from MasterListAccumulationVal list "
			+ "where list.accumulation1up = :id ")
	public void deleteMasterListAccumulationById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from MasterListIntVal list "
			+ "where list.masterListIntVal1up = :id ")
	public void deleteMasterListIntValById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from MasterListLimitVal list "
			+ "where list.masterListLimitVal1up = :id")
	public void deleteMasterListLimitValById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from MasterListMessageVal list "
			+ "where list.masterListMessageVal1up = :id ")
	public void deleteMasterListMessageValById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from MasterListServiceDefinition list"
			+ " where list.masterListServiceDefinition1up = :id ")
	public void deleteMasterListServiceDefinitionById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from MasterListStringVal list"
			+ " where list.masterListStringVal1up = :id ")
	public void deleteMasterListStringValById(@Param("id") int id);

	@Query(value = "SELECT list from MasterListIntVal list "
			+ " left join fetch list.masterListInt"
			+ " left join fetch list.accumulation "
			+ "where list.masterListInt.masterList1up = :mastId")
	public List<MasterListIntVal> findMasterListIntValListByMastreListId(
			@Param("mastId") int mastId);

	@Query(value = "Select master from" + " MasterListStringVal master "
			+ "left join master.masterListString "
			+ "where master.masterListString.masterList1up = :id")
	public List<MasterListStringVal> findMasterListStringValListByMastreListId(
			@Param("id") int id);

	@Query(value = "Select list.masterListStringVal1up from MasterListStringVal list "
			+ "left join list.masterLimitCoverage list1 "
			+ "left join list.masterLimitPeriod list2 "
			+ "left join list.masterLimitQuantifier list3 "
			+ "left join list.masterCat1 list4 "
			+ "left join list.masterCat2 list5 "
			+ "left join list.masterCat3 list6 "
			+ "left join list.masterPost list7 "
			+ "left join list.templateString list8 "
			+ "left join list.templateAllowString list9 "
			+ "left join list.treeSwitchOption list10 "
			+ "left join list.configOptionVer list11 "
			+ "where list.masterListString.masterList1up = :masterList1up "
			+ "group by list.masterListStringVal1up having count(list1) > 0 "
			+ "or count(list2) > 0 or count(list3) > 0 or count(list4) > 0 or count(list5) > 0 "
			+ "or count(list6) > 0 or count(list7) > 0 or count(list8) > 0 or count(list9) > 0 "
			+ "or count(list10) > 0 or count(list11) > 0")
	public List<Integer> findAllUseAndUnUseStringValue(
			@Param("masterList1up") int masterList1up);

	@Query(value = "Select list from MasterListStringVal list "
			+ "left join fetch list.masterListString "
			+ "where list.masterListStringVal1up in :stringValId")
	public List<MasterListStringVal> findAllLockAndUnLockStringValue(
			@Param("stringValId") Set<Integer> stringValId);

	@Query(value = "Select list.masterListMessageVal1up from MasterListMessageVal list "
			+ "left join list.templateAllowMessage list1 "
			+ "where list.masterListMessage.masterList1up = :masterList1up "
			+ "and list.messageType = :messageType group by list.masterListMessageVal1up having count(list1) > 0 ")
	public List<Integer> findAllUseAndUnUseMessageValue(
			@Param("masterList1up") int masterList1up,
			@Param("messageType") String messageType);

	@Query(value = "Select list.accumulation1up from MasterListAccumulationVal list "
			+ "left join list.accumInt list1 "
			+ "left join list.masterLimitAccum list2 "
			+ "where list.masterListAccumVal.masterList1up in :masterList1up group by list.accumulation1up having count(list1) > 0 or count(list2) > 0")
	public List<Integer> findAllUseAndUnUseAccumulationValueByMasterList(
			@Param("masterList1up") List<Integer> masterList1up);

	@Query(value = "Select list.masterListIntVal1up from MasterListIntVal list "
			+ "left join list.templateAllowInt list1 "
			+ "where list.masterListInt.masterList1up= :masterList1up group by list.masterListIntVal1up having count(list1) > 0")
	public List<Integer> findAllUseAndUnUseIntValue(
			@Param("masterList1up") int masterList1up);

	@Query(value = "Select list.masterListLimitVal1up from MasterListLimitVal list "
			+ "left join list.templateAllowLimit list1 "
			+ " where list.masterListLimit.masterList1up =:masterList1up group by list.masterListLimitVal1up having count(list1) > 0")
	public List<Integer> findAllUseAndUnUseLimitValue(
			@Param("masterList1up") int masterList1up);

	@Query(value = "Select list.masterListServiceDefinition1up from MasterListServiceDefinition list "
			+ "left join list.templateAllowService list1 "
			+ "where list.masterListService.masterList1up = :masterList1up group by list.masterListServiceDefinition1up having count(list1) > 0 ")
	public List<Integer> findAllUseAndUnUseServiceDefValue(
			@Param("masterList1up") int masterList1up);

	@Query(value = "Select list.masterListMessageVal1up from MasterListMessageVal list "
			+ "left join list.templateAllowMessage list1 "
			+ "where list.masterListMessageVal1up in :id group by list.masterListMessageVal1up having count(list1) > 0 ")
	public List<Integer> findInUseForMessageValue(@Param("id") List<Integer> id);

	@Query(value = "Select list.masterListStringVal1up from MasterListStringVal list "
			+ "left join list.masterLimitCoverage list1 "
			+ "left join list.masterLimitPeriod list2 "
			+ "left join list.masterLimitQuantifier list3 "
			+ "left join list.masterCat1 list4 "
			+ "left join list.masterCat2 list5 "
			+ "left join list.masterCat3 list6 "
			+ "left join list.masterPost list7 "
			+ "left join list.templateString list8 "
			+ "left join list.templateAllowString list9 "
			+ "left join list.treeSwitchOption list10 "
			+ "left join list.configOptionVer list11 "
			+ "where list.masterListStringVal1up in :ids "
			+ "group by list.masterListStringVal1up having count(list1) > 0 "
			+ "or count(list2) > 0 or count(list3) > 0 or count(list4) > 0 or count(list5) > 0 "
			+ "or count(list6) > 0 or count(list7) > 0 or count(list8) > 0 or count(list9) > 0 "
			+ "or count(list10) > 0 or count(list11) > 0")
	public List<Integer> findInUseForStringValue(@Param("ids") List<Integer> ids);

	@Query(value = "Select list.masterListIntVal1up from MasterListIntVal list "
			+ "left join list.templateAllowInt list1 "
			+ "where list.masterListIntVal1up in :ids group by list.masterListIntVal1up having count(list1) > 0 ")
	public List<Integer> findAllUseAndUnUseIntValue(
			@Param("ids") List<Integer> ids);

	@Query(value = "Select list.masterListServiceDefinition1up from MasterListServiceDefinition list "
			+ "left join list.templateAllowService list1 "
			+ "where list.masterListServiceDefinition1up in :ids group by list.masterListServiceDefinition1up having count(list1) > 0 ")
	public List<Integer> findAllUseAndUnUseServiceDefValue(
			@Param("ids") List<Integer> ids);

	@Query(value = "Select list.accumulation1up from MasterListAccumulationVal list "
			+ "left join list.accumInt list1 "
			+ "left join list.masterLimitAccum list2 "
			+ "where list.accumulation1up in :ids group by list.accumulation1up having count(list1) > 0 or count(list2) > 0")
	public List<Integer> findAllUseAndUnUseAccumulationValue(
			@Param("ids") List<Integer> ids);

	@Query(value = "Select list.masterListLimitVal1up from MasterListLimitVal list "
			+ "left join list.templateAllowLimit list1 "
			+ " where list.masterListLimitVal1up in :ids group by list.masterListLimitVal1up having count(list1) > 0")
	public List<Integer> findAllUseAndUnUseLimitValue(
			@Param("ids") List<Integer> ids);

	@Query(value = "Select list from MasterListStringVal list "
			+ "left join fetch list.masterListString "
			+ "where list.masterListStringVal1up in :ids")
	public List<MasterListStringVal> findAllStringValMasterList(
			@Param("ids") List<Integer> ids);

	@Query(value = "Select list from MasterListIntVal list "
			+ "left join fetch list.masterListInt "
			+ "where list.masterListIntVal1up in :ids")
	public List<MasterListIntVal> findAllIntValMasterList(
			@Param("ids") List<Integer> ids);

	// ******************************************************************************************//

}
