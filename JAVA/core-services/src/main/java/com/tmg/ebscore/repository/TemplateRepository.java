package com.tmg.ebscore.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tmg.ebscore.orm.ebspackage.BenefitPackage;
import com.tmg.ebscore.orm.masterlist.MasterListIntVal;
import com.tmg.ebscore.orm.masterlist.MasterListLimitVal;
import com.tmg.ebscore.orm.masterlist.MasterListMessageVal;
import com.tmg.ebscore.orm.masterlist.MasterListServiceDefinition;
import com.tmg.ebscore.orm.masterlist.MasterListStringVal;
import com.tmg.ebscore.orm.packageversion.PkgVersion;
import com.tmg.ebscore.orm.packageversion.PkgVersionWorkflowState;
import com.tmg.ebscore.orm.template.IfYNTestAttribute;
import com.tmg.ebscore.orm.template.MatrixShapeLimitXref;
import com.tmg.ebscore.orm.template.MatrixShapeMessageXref;
import com.tmg.ebscore.orm.template.PackageTemplate;
import com.tmg.ebscore.orm.template.SetValueParam;
import com.tmg.ebscore.orm.template.ShapeValueDefault;
import com.tmg.ebscore.orm.template.SwitchCriteria;
import com.tmg.ebscore.orm.template.TemplateAllowsIntVal;
import com.tmg.ebscore.orm.template.TemplateAllowsLimitVal;
import com.tmg.ebscore.orm.template.TemplateAllowsMessageVal;
import com.tmg.ebscore.orm.template.TemplateAllowsServiceDefinition;
import com.tmg.ebscore.orm.template.TemplateAllowsStringVal;
import com.tmg.ebscore.orm.template.TemplateTree;
import com.tmg.ebscore.orm.template.TemplateWorkflowState;
import com.tmg.ebscore.orm.template.TreeConnector;
import com.tmg.ebscore.orm.template.TreeShape;
import com.tmg.ebscore.orm.template.TreeShapeType;

public interface TemplateRepository extends
		JpaRepository<PackageTemplate, Long>, TemplateCustomRepository {

	@Query(value = "SELECT tempList from PackageTemplate tempList"
			+ " left join fetch tempList.benefitPackageTypeAsString"
			+ " left join fetch tempList.templateWorkflowState", countQuery = "SELECT count(tempList) from PackageTemplate tempList"
			+ " left join tempList.benefitPackageTypeAsString"
			+ " left join tempList.templateWorkflowState")
	public Page<PackageTemplate> findTemplateList(Pageable pageable);

	@Query(value = "SELECT workflowList from TemplateWorkflowState workflowList", countQuery = "SELECT count(workflowList) from TemplateWorkflowState workflowList")
	public Page<TemplateWorkflowState> findTemplateWorkFlowStateListAtTemplate(
			Pageable pageable);

	@Query(value = "SELECT count(package) from BenefitPackage package "
			+ "left join package.packageTemplate "
			+ "where package.packageTemplate.packageTemplate1up = :id")
	public int findNoOfPackageById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from PkgVersionWorkflowState list "
			+ "where list.pkgVersionWorkflowState1up = :id")
	public void deletePkgVersionWorkflowStateById(@Param("id") int id);

	@Query(value = "SELECT package.benefitPackage1up "
			+ "from BenefitPackage package where"
			+ " package.packageTemplate.packageTemplate1up = :id) ")
	public List<Integer> getBenefitPackageId(@Param("id") int id);

	@Query(value = "SELECT version.pkgVersionWorkflowState.pkgVersionWorkflowState1up from "
			+ "PkgVersion version "
			+ " where version.benefitPackage.benefitPackage1up = :pkgId")
	public List<Integer> getPkgVerWrkflowState(@Param("pkgId") int pkgId);

	@Query(value = "SELECT pkgWrkFlowState.releasedFlag from PkgVersionWorkflowState pkgWrkFlowState "
			+ "where pkgWrkFlowState.pkgVersionWorkflowState1up = :wrkFlowState")
	public boolean getIsReleasedFlag(@Param("wrkFlowState") int wrkFlowState);

	@Query(value = "SELECT count(version) from PkgVersion version "
			+ "left join version.benefitPackage "
			+ "where version.benefitPackage.benefitPackage1up = :id")
	public int getPackagesVersion(@Param("id") int id);

	@Query(value = "SELECT package from BenefitPackage package "
			+ "left join fetch package.packageTemplate "
			+ "where package.packageTemplate.packageTemplate1up = :id", countQuery = "SELECT count(package) from BenefitPackage package "
			+ "left join package.packageTemplate "
			+ "where package.packageTemplate.packageTemplate1up = :id")
	public Page<BenefitPackage> findPackagesForTemplate(@Param("id") int id,
			Pageable pageable);

	@Query(value = "Select master.stringValue from"
			+ " MasterListStringVal master left join master.masterListString "
			+ "where master.masterListString.masterList1up = :id")
	public List<String> findTemplateTypes(@Param("id") int id);

	@Query(value = "SELECT list from MasterListStringVal list "
			+ "where list.masterListStringVal1up = :id ")
	public MasterListStringVal findTemplateById(@Param("id") int id);

	@Query(value = "SELECT workflow from TemplateWorkflowState workflow "
			+ "where workflow.templateWorkflowState1up = :id")
	public TemplateWorkflowState findWorkFlowStateGetById(@Param("id") int id);

	@Query(value = "SELECT version.pkgVersion1up from PkgVersion version "

	+ " where version.benefitPackage.benefitPackage1up = :id")
	public List<Integer> getPckgVersion1upForPkgVersion(@Param("id") int id);

	@Query(value = "SELECT option.pkgVersion.pkgVersion1up from PkgConfigOption option")
	public List<Integer> getPckVersion1upForPkgConfigOption();

	@Query("SELECT list.masterListIntVal1up from MasterListIntVal list "
			+ " where list.masterListInt.masterList1up = :masterList1up")
	public List<Integer> findMasterListIntValForTemplate(
			@Param("masterList1up") int masterList1up);

	@Query(value = "SELECT intVal from TemplateAllowsIntVal intVal "
			+ "left join fetch intVal.templateAllowsIntValId.masterListIntVal "
			+ "left join fetch intVal.templateAllowsIntValId.packageTemplate "
			+ "where intVal.templateAllowsIntValId.packageTemplate.packageTemplate1up = :tempId")
	public List<TemplateAllowsIntVal> findAllowedIntValForTemplate(
			@Param("tempId") int tempId);

	@Query(value = "SELECT intVal.templateAllowsIntValId.masterListIntVal.masterListIntVal1up from TemplateAllowsIntVal intVal "
			+ "where intVal.templateAllowsIntValId.packageTemplate.packageTemplate1up = :tempId")
	public List<Integer> findAllowedOrNotIntValForTemplate(
			@Param("tempId") int tempId);

	@Query(value = "SELECT intVal.templateAllowsIntValId.masterListIntVal.masterListIntVal1up from TemplateAllowsIntVal intVal "
			+ "where intVal.templateAllowsIntValId.packageTemplate.packageTemplate1up = :tempId "
			+ "AND  intVal.defaultValFlag = true")
	public List<Integer> findDefaultIntValForTemplate(
			@Param("tempId") int tempId);

	@Query(value = "SELECT intVal from TemplateAllowsIntVal intVal "
			+ "left join fetch intVal.templateAllowsIntValId.packageTemplate "
			+ "where intVal.templateAllowsIntValId.packageTemplate.packageTemplate1up = :id", countQuery = "SELECT count(intVal) from TemplateAllowsIntVal intVal "
			+ "left join intVal.templateAllowsIntValId.packageTemplate "
			+ "where intVal.templateAllowsIntValId.packageTemplate.packageTemplate1up = :id")
	public Page<TemplateAllowsIntVal> findAllowedIntValForTemplate(
			@Param("id") int id, Pageable pageable);

	@Query(value = "SELECT stringVal from TemplateAllowsStringVal stringVal "
			+ "left join fetch stringVal.templateAllowsStringValId.masterListStringVal"
			+ " left join fetch stringVal.templateAllowsStringValId.packageTemplate"
			+ " where stringVal.templateAllowsStringValId.packageTemplate.packageTemplate1up = :tempId")
	public List<TemplateAllowsStringVal> findAllowedStringValForTemplate(
			@Param("tempId") int tempId);

	@Query(value = "SELECT stringVal.templateAllowsStringValId.masterListStringVal.masterListStringVal1up from TemplateAllowsStringVal stringVal "
			+ " where stringVal.templateAllowsStringValId.packageTemplate.packageTemplate1up = :tempId")
	public List<Integer> findAllowedOrNotStringValForTemplate(
			@Param("tempId") int tempId);

	@Query(value = "SELECT stringVal.templateAllowsStringValId.masterListStringVal.masterListStringVal1up from TemplateAllowsStringVal stringVal "
			+ " where stringVal.templateAllowsStringValId.packageTemplate.packageTemplate1up = :tempId "
			+ "AND  stringVal.defaultValFlag = true")
	public List<Integer> findDefaultStringValForTemplate(
			@Param("tempId") int tempId);

	@Query(value = "SELECT messageVal from TemplateAllowsMessageVal messageVal "
			+ "left join fetch messageVal.templateAllowsMessageValId "
			+ "where messageVal.templateAllowsMessageValId.packageTemplate.packageTemplate1up = :tempId")
	public List<TemplateAllowsMessageVal> findAllowedMessageValForTemplate(
			@Param("tempId") int tempId);

	@Query(value = "SELECT messageVal.templateAllowsMessageValId.masterListMessageVal.masterListMessageVal1up from TemplateAllowsMessageVal messageVal "
			+ "where messageVal.templateAllowsMessageValId.packageTemplate.packageTemplate1up = :tempId")
	public List<Integer> findAllowedOrNotMessageValForTemplate(
			@Param("tempId") int tempId);

	@Query(value = "SELECT stringVal from TemplateAllowsStringVal stringVal "
			+ "left join fetch stringVal.templateAllowsStringValId.packageTemplate "
			+ "where stringVal.templateAllowsStringValId.packageTemplate.packageTemplate1up = :id", countQuery = "SELECT count(stringVal) from TemplateAllowsStringVal stringVal "
			+ "left join stringVal.templateAllowsStringValId.packageTemplate "
			+ "where stringVal.templateAllowsStringValId.packageTemplate.packageTemplate1up = :id")
	public Page<TemplateAllowsStringVal> findAllowedStringValForTemplate(
			@Param("id") int id, Pageable pageable);

	@Query(value = "SELECT limitVal from TemplateAllowsLimitVal limitVal "
			+ "left join fetch limitVal.templateAllowsLimitValId "

			+ "where limitVal.templateAllowsLimitValId.packageTemplate.packageTemplate1up = :id", countQuery = "SELECT count(limitVal) from TemplateAllowsLimitVal limitVal "
			+ "left join limitVal.templateAllowsLimitValId.packageTemplate "
			+ "where limitVal.templateAllowsLimitValId.packageTemplate.packageTemplate1up = :id")
	public Page<TemplateAllowsLimitVal> findAllowedlimitValForTemplate(
			@Param("id") int id, Pageable pageable);

	@Query(value = "SELECT messageVal from TemplateAllowsMessageVal messageVal "
			+ "left join fetch messageVal.templateAllowsMessageValId.packageTemplate "
			+ "where messageVal.templateAllowsMessageValId.packageTemplate.packageTemplate1up = :id", countQuery = "SELECT count(messageVal) from TemplateAllowsMessageVal messageVal "
			+ "left join messageVal.templateAllowsMessageValId.packageTemplate "
			+ "where messageVal.templateAllowsMessageValId.packageTemplate.packageTemplate1up = :id")
	public Page<TemplateAllowsMessageVal> findAllowedMessageValForTemplate(
			@Param("id") int id, Pageable pageable);

	@Query(value = "SELECT list from MasterListStringVal list"
			+ " left join fetch list.masterListString"
			+ " where list.masterListString.masterList1up = :id ")
	public List<MasterListStringVal> findStringValMasterListForTemplate(
			@Param("id") int id);

	@Query(value = "SELECT list from TemplateAllowsServiceDefinition list "
			+ "left join fetch list.templateAllowsServiceDefinitionId.masterListServiceDefinition list1 "
			+ "left join fetch list1.masterListStringValCat1 cat1 "
			+ "left join fetch cat1.masterListString master1 "
			+ "left join fetch master1.masterListDataType "
			+ "left join fetch list1.masterListStringValCat2 cat2 "
			+ "left join fetch cat2.masterListString master2 "
			+ "left join fetch master2.masterListDataType "
			+ "left join fetch list1.masterListStringValCat3 cat3 "
			+ "left join fetch cat3.masterListString master3 "
			+ "left join fetch master3.masterListDataType "
			+ "left join fetch list1.masterListStringValPostString cat4 "
			+ "left join fetch cat4.masterListString master4 "
			+ "left join fetch master4.masterListDataType "
			+ "left join fetch list.templateAllowsServiceDefinitionId.packageTemplate list2 "
			+ "left join fetch list2.benefitPackageTypeAsString cat5 "
			+ "left join fetch cat5.masterListString master5 "
			+ "left join fetch master5.masterListDataType "
			+ "left join fetch list2.templateWorkflowState "
			+ "where list2.packageTemplate1up = :id", countQuery = "SELECT count(serviceDefinitionVal) from TemplateAllowsServiceDefinition serviceDefinitionVal "
			+ "where serviceDefinitionVal.templateAllowsServiceDefinitionId.packageTemplate.packageTemplate1up = :id")
	public Page<TemplateAllowsServiceDefinition> findAllowedServiceDefinitionForTemplate(
			@Param("id") int id, Pageable pageable);

	// ********** Find By Id Methods ************** //

	@Query(value = "SELECT list from IfYNTestAttribute list "
			+ " WHERE list.ifYNTestAttribute1up = :id")
	public IfYNTestAttribute findIfYNTestAttributeById(@Param("id") int id);

	@Query(value = "SELECT list from MatrixShapeLimitXref list "
			+ " left Join fetch list.matrixShapeLimitXrefId "
			+ " left join fetch list.matrixShapeLimitXrefId.treeShape"
			+ " left join fetch list.matrixShapeLimitXrefId.masterListLimitVal"
			+ " WHERE list.matrixShapeLimitXrefId.treeShape.treeShape1up = :treeShape1up"
			+ " AND list.matrixShapeLimitXrefId.masterListLimitVal.masterListLimitVal1up = :masterListLimitVal1up")
	public MatrixShapeLimitXref findMatrixShapeLimitXrefById(
			@Param("treeShape1up") int treeShape1up,
			@Param("masterListLimitVal1up") int masterListLimitVal1up);

	@Query(value = "SELECT list from MatrixShapeMessageXref list "
			+ " left Join fetch list.matrixShapeMessageXrefId "
			+ " left join fetch list.matrixShapeMessageXrefId.treeShape"
			+ " left join fetch list.matrixShapeMessageXrefId.masterListMessageVal"
			+ " WHERE list.matrixShapeMessageXrefId.treeShape.treeShape1up = :treeShape1up"
			+ " AND list.matrixShapeMessageXrefId.masterListMessageVal.masterListMessageVal1up = :masterListMessageVal1up")
	public MatrixShapeMessageXref findMatrixShapeMessageXrefById(
			@Param("treeShape1up") int treeShape1up,
			@Param("masterListMessageVal1up") int masterListMessageVal1up);

	@Query(value = "SELECT list from PackageTemplate list"
			+ " left join fetch list.benefitPackageTypeAsString"
			+ "	left join fetch list.templateTree"
			+ " left join fetch list.templateWorkflowState"
			+ " Where list.packageTemplate1up = :id")
	public PackageTemplate findPackageTemplateById(@Param("id") int id);

	@Query(value = "SELECT list from PackageTemplate list"
			+ " left join fetch list.benefitPackageTypeAsString"
			+ " left join fetch list.templateWorkflowState"
			+ " left join fetch list.templateBenefitPkg"
			+ " left join fetch list.templateTree"
			+ " left join fetch list.pkgVerWorkflow list1"
			+ " left join fetch list1.pkgVerWorkFlow"
			+ " left join fetch list1.configOptionVerState list2"
			+ " left join fetch list2.pkgConfigProductXref"
			+ " Where list.packageTemplate1up = :id")
	public PackageTemplate findPackageTemplateSetById(@Param("id") int id);

	@Query(value = "SELECT list from SetValueParam list"
			+ " left join fetch list.masterListSetValueParam"
			+ " Where list.setValueParam1up = :id")
	public SetValueParam findSetValueParamById(@Param("id") int id);

	@Query(value = "SELECT list from ShapeValueDefault list"
			+ " left join fetch list.templateTree"
			+ " left join fetch list.setValueValueAsInt"
			+ " left join fetch list.setValueValueAsString"
			+ " Where list.shapeValueDefault1up = :id")
	public ShapeValueDefault findShapeValueDefaultById(@Param("id") int id);

	@Query(value = "SELECT list from SwitchCriteria list"
			+ " left join fetch list.masterListSwitch"
			+ " Where list.switchCriteria1up = :id")
	public SwitchCriteria findSwitchCriteriaById(@Param("id") int id);

	@Query(value = "SELECT list from TemplateAllowsIntVal list"
			+ " left join fetch list.templateAllowsIntValId"
			+ " left join fetch list.templateAllowsIntValId.packageTemplate"
			+ " left join fetch list.templateAllowsIntValId.masterListIntVal"
			+ " Where list.templateAllowsIntValId.packageTemplate.packageTemplate1up = :packageTemplate1up"
			+ " AND list.templateAllowsIntValId.masterListIntVal.masterListIntVal1up = :masterListIntVal1up")
	public TemplateAllowsIntVal findTemplateAllowsIntValById(
			@Param("packageTemplate1up") int packageTemplate1up,
			@Param("masterListIntVal1up") int masterListIntVal1up);

	@Query(value = "SELECT list from TemplateAllowsLimitVal list"
			+ " left join fetch list.templateAllowsLimitValId"
			+ " left join fetch list.templateAllowsLimitValId.packageTemplate"
			+ " left join fetch list.templateAllowsLimitValId.masterListLimitVal"
			+ " Where list.templateAllowsLimitValId.packageTemplate.packageTemplate1up = :packageTemplate1up"
			+ " AND list.templateAllowsLimitValId.masterListLimitVal.masterListLimitVal1up = :masterListLimitVal1up")
	public TemplateAllowsLimitVal findTemplateAllowsLimitValById(
			@Param("packageTemplate1up") int packageTemplate1up,
			@Param("masterListLimitVal1up") int masterListLimitVal1up);

	@Query(value = "SELECT list from TemplateAllowsMessageVal list"
			+ " left join fetch list.templateAllowsMessageValId"
			+ " left join fetch list.templateAllowsMessageValId.packageTemplate"
			+ " left join fetch list.templateAllowsMessageValId.masterListMessageVal"
			+ " Where list.templateAllowsMessageValId.packageTemplate.packageTemplate1up = :packageTemplate1up"
			+ " AND list.templateAllowsMessageValId.masterListMessageVal.masterListMessageVal1up = :masterListMessageVal1up")
	public TemplateAllowsMessageVal findTemplateAllowsMessageValById(
			@Param("packageTemplate1up") int packageTemplate1up,
			@Param("masterListMessageVal1up") int masterListMessageVal1up);

	@Query(value = "SELECT list from TemplateAllowsServiceDefinition list"
			+ " left join fetch list.templateAllowsServiceDefinitionId"
			+ " left join fetch list.templateAllowsServiceDefinitionId.packageTemplate"
			+ " left join fetch list.templateAllowsServiceDefinitionId.masterListServiceDefinition"
			+ " Where list.templateAllowsServiceDefinitionId.packageTemplate.packageTemplate1up = :packageTemplate1up"
			+ " AND list.templateAllowsServiceDefinitionId.masterListServiceDefinition.masterListServiceDefinition1up = :masterListServiceDefinition1up")
	public TemplateAllowsServiceDefinition findTemplateAllowsServiceDefinitionById(
			@Param("packageTemplate1up") int packageTemplate1up,
			@Param("masterListServiceDefinition1up") int masterListServiceDefinition1up);

	@Query(value = "SELECT list from TemplateAllowsStringVal list"
			+ " left join fetch list.templateAllowsStringValId"
			+ " left join fetch list.templateAllowsStringValId.packageTemplate"
			+ " left join fetch list.templateAllowsStringValId.masterListStringVal"
			+ " Where list.templateAllowsStringValId.packageTemplate.packageTemplate1up = :packageTemplate1up"
			+ " AND list.templateAllowsStringValId.masterListStringVal.masterListStringVal1up = :masterListStringVal1up")
	public TemplateAllowsStringVal findTemplateAllowsStringValById(
			@Param("packageTemplate1up") int packageTemplate1up,
			@Param("masterListStringVal1up") int masterListStringVal1up);

	@Query(value = "SELECT list from TemplateTree list"
			+ " left join fetch list.packageTemplate"
			+ " Where list.templateTree1up = :id")
	public TemplateTree findTemplateTreeById(@Param("id") int id);

	@Query(value = "SELECT list from TemplateTree list"
			+ " left join fetch list.packageTemplate"
			+ " left join fetch list.shapeTree "
			+ " left join fetch list.pkgtempTree"
			+ " Where list.templateTree1up = :id")
	public TemplateTree findTemplateTreeSetById(@Param("id") int id);

	@Query(value = "SELECT list from TemplateWorkflowState list"
			+ " Where list.templateWorkflowState1up = :id")
	public TemplateWorkflowState findTemplateWorkflowStateById(
			@Param("id") int id);

	@Query(value = "SELECT list from TreeConnector list"
			+ " left join fetch list.templateTree"
			+ " left join fetch list.fromShape"
			+ " left join fetch list.toShape"
			+ " left join fetch list.switchOptionStringVal"
			+ " Where list.treeConnector1up = :id")
	public TreeConnector findTreeConnectorById(@Param("id") int id);

	@Query(value = "SELECT list from TreeShape list"
			+ " left join fetch list.templateTree"
			+ " left join fetch list.treeShapeType"
			+ " left join fetch list.ifYNTestAttribute"
			+ " left join fetch list.setValueParam"
			+ " left join fetch list.switchCriteria"
			+ " Where list.treeShape1up = :id")
	public TreeShape findTreeShapeById(@Param("id") int id);

	@Query(value = "SELECT list from TreeShape list"
			+ " left join fetch list.treeShapeBigXref"
			+ " Where list.treeShape1up = :id")
	public TreeShape findTreeShapeSetOfMatrixShapeBig3COXrefById(
			@Param("id") int id);

	@Query(value = "SELECT list from TreeShape list"
			+ " left join fetch list.treeShapeLimitXref"
			+ " left join fetch list.treeShapeMessageXref"
			+ " Where list.treeShape1up = :id")
	public TreeShape findTreeShapeSetOfMatrixShapeBig3COLimitAndMessageXXrefById(
			@Param("id") int id);

	@Query(value = "SELECT list from TreeShape list"
			+ " left join fetch list.masterShapeTree"
			+ " Where list.treeShape1up = :id")
	public TreeShape findTreeShapeSetOfMatrixShapeLimitXrefById(
			@Param("id") int id);

	@Query(value = "SELECT list from TreeShape list"
			+ " left join fetch list.masterMessageXrefTree"
			+ " Where list.treeShape1up = :id")
	public TreeShape findTreeShapeSetOfMatrixShapeMessageXrefById(
			@Param("id") int id);

	@Query(value = "SELECT list from TreeShapeType list"
			+ " Where list.treeShapeType1up = :id")
	public TreeShapeType findTreeShapeTypeById(@Param("id") int id);

	// ******************************************************************************************//

	// ********** Delete By Id Methods ************** //
	@Modifying
	@Query(value = "Delete from IfYNTestAttribute list "
			+ " WHERE list.ifYNTestAttribute1up = :id")
	public void deleteIfYNTestAttributeById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from MatrixShapeLimitXref list "
			+ " WHERE list.matrixShapeLimitXrefId.treeShape.treeShape1up = :treeShape1up"
			+ " AND list.matrixShapeLimitXrefId.masterListLimitVal.masterListLimitVal1up = :masterListLimitVal1up")
	public void deleteMatrixShapeLimitXrefById(
			@Param("treeShape1up") int treeShape1up,
			@Param("masterListLimitVal1up") int masterListLimitVal1up);

	@Modifying
	@Query(value = "Delete from MatrixShapeMessageXref list "
			+ " WHERE list.matrixShapeMessageXrefId.treeShape.treeShape1up = :treeShape1up"
			+ " AND list.matrixShapeMessageXrefId.masterListMessageVal.masterListMessageVal1up = :masterListMessageVal1up")
	public void deleteMatrixShapeMessageXrefById(
			@Param("treeShape1up") int treeShape1up,
			@Param("masterListMessageVal1up") int masterListMessageVal1up);

	@Modifying
	@Query(value = "Delete from PackageTemplate list"
			+ " Where list.packageTemplate1up = :id")
	public void deletePackageTemplateById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from SetValueParam list"
			+ " Where list.setValueParam1up = :id")
	public void deleteSetValueParamById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from ShapeValueDefault list"
			+ " Where list.shapeValueDefault1up = :id")
	public void deleteShapeValueDefaultById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from SwitchCriteria list"
			+ " Where list.switchCriteria1up = :id")
	public void deleteSwitchCriteriaById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from TemplateAllowsIntVal list"
			+ " Where list.templateAllowsIntValId.packageTemplate.packageTemplate1up = :packageTemplate1up"
			+ " AND list.templateAllowsIntValId.masterListIntVal.masterListIntVal1up = :masterListIntVal1up")
	public void deleteTemplateAllowsIntValById(
			@Param("packageTemplate1up") int packageTemplate1up,
			@Param("masterListIntVal1up") int masterListIntVal1up);

	@Modifying
	@Query(value = "Delete from TemplateAllowsLimitVal list"
			+ " Where list.templateAllowsLimitValId.packageTemplate.packageTemplate1up = :packageTemplate1up"
			+ " AND list.templateAllowsLimitValId.masterListLimitVal.masterListLimitVal1up = :masterListLimitVal1up")
	public void deleteTemplateAllowsLimitValById(
			@Param("packageTemplate1up") int packageTemplate1up,
			@Param("masterListLimitVal1up") int masterListLimitVal1up);

	@Modifying
	@Query(value = "Delete from TemplateAllowsMessageVal list"
			+ " Where list.templateAllowsMessageValId.packageTemplate.packageTemplate1up = :packageTemplate1up"
			+ " AND list.templateAllowsMessageValId.masterListMessageVal.masterListMessageVal1up = :masterListMessageVal1up")
	public void deleteTemplateAllowsMessageValById(
			@Param("packageTemplate1up") int packageTemplate1up,
			@Param("masterListMessageVal1up") int masterListMessageVal1up);

	@Modifying
	@Query(value = "Delete from TemplateAllowsServiceDefinition list"
			+ " Where list.templateAllowsServiceDefinitionId.packageTemplate.packageTemplate1up = :packageTemplate1up"
			+ " AND list.templateAllowsServiceDefinitionId.masterListServiceDefinition.masterListServiceDefinition1up = :masterListServiceDefinition1up")
	public void deleteTemplateAllowsServiceDefinitionById(
			@Param("packageTemplate1up") int packageTemplate1up,
			@Param("masterListServiceDefinition1up") int masterListServiceDefinition1up);

	@Modifying
	@Query(value = "Delete from TemplateAllowsStringVal list"
			+ " Where list.templateAllowsStringValId.packageTemplate.packageTemplate1up = :packageTemplate1up"
			+ " AND list.templateAllowsStringValId.masterListStringVal.masterListStringVal1up = :masterListStringVal1up")
	public void deleteTemplateAllowsStringValById(
			@Param("packageTemplate1up") int packageTemplate1up,
			@Param("masterListStringVal1up") int masterListStringVal1up);

	@Modifying
	@Query(value = "Delete from TemplateTree list"
			+ " Where list.templateTree1up = :id")
	public void deleteTemplateTreeById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from TemplateWorkflowState list"
			+ " Where list.templateWorkflowState1up = :id")
	public void deleteTemplateWorkflowStateById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from TreeConnector list"
			+ " Where list.treeConnector1up = :id")
	public void deleteTreeConnectorById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from TreeShape list"
			+ " Where list.treeShape1up = :id")
	public void deleteTreeShapeById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from TreeShapeType list"
			+ " Where list.treeShapeType1up = :id")
	public void deleteTreeShapeTypeById(@Param("id") int id);

	// ******************************************************************************************//

	@Query(value = "SELECT serviceDefinitionVal from TemplateAllowsServiceDefinition serviceDefinitionVal "
			+ "left join fetch serviceDefinitionVal.templateAllowsServiceDefinitionId list "
			+ "left join fetch list.masterListServiceDefinition "
			+ "left join fetch list.packageTemplate list2 "
			+ "where list2.packageTemplate1up = :tempId")
	public List<TemplateAllowsServiceDefinition> findAllowedServiceDefinitionListForTemplate(
			@Param("tempId") int tempId);

	@Query(value = "SELECT serviceDefinitionVal.templateAllowsServiceDefinitionId.masterListServiceDefinition.masterListServiceDefinition1up from TemplateAllowsServiceDefinition serviceDefinitionVal "
			+ "where serviceDefinitionVal.templateAllowsServiceDefinitionId.packageTemplate.packageTemplate1up = :tempId")
	public List<Integer> findAllowedOrNotServiceDefinitionListForTemplate(
			@Param("tempId") int tempId);

	@Modifying
	@Query(value = "DELETE from TemplateAllowsMessageVal messageVal where "
			+ " messageVal.templateAllowsMessageValId.masterListMessageVal.masterListMessageVal1up = :msgValId and"
			+ " messageVal.templateAllowsMessageValId.packageTemplate.packageTemplate1up = :tempId")
	public void deleteAllowMessageValById(@Param("msgValId") int msgValId,
			@Param("tempId") int tempId);

	@Query("SELECT limitVal from TemplateAllowsLimitVal limitVal "
			+ "left join fetch limitVal.templateAllowsLimitValId "
			+ "left join fetch limitVal.templateAllowsLimitValId.masterListLimitVal "
			+ "left join fetch limitVal.templateAllowsLimitValId.packageTemplate "
			+ "where limitVal.templateAllowsLimitValId.packageTemplate.packageTemplate1up = :tempId")
	public List<TemplateAllowsLimitVal> findAllowedLimitListValForTemplate(
			@Param("tempId") int tempId);

	@Query("SELECT limitVal.templateAllowsLimitValId.masterListLimitVal.masterListLimitVal1up from TemplateAllowsLimitVal limitVal "
			+ "where limitVal.templateAllowsLimitValId.packageTemplate.packageTemplate1up = :tempId")
	public List<Integer> findAllowedOrNotLimitListValForTemplate(
			@Param("tempId") int tempId);

	// selectQueries..................................................................................................

	@Query(value = "SELECT pco.pkgVersion.pkgVersion1up   FROM PkgConfigOption pco "
			+ "WHERE  pco.pkgVersionWorkflowState.pkgVersionWorkflowState1up = :id")
	public List<Integer> findPkgVersion1up(@Param("id") int id);

	@Query(value = "SELECT pco.pkgConfigOption1up FROM PkgConfigOption  pco  "
			+ "WHERE  pco.pkgVersionWorkflowState.pkgVersionWorkflowState1up = :id")
	public List<Integer> findPkgConfigOption1up(@Param("id") int id);

	// DeleteQueries...................................................................................................
	@Modifying
	@Query(value = "DELETE FROM BenefitOptionBig3CO bobig3 where bobig3.pkgVersion.pkgVersion1up =:pkgVersionId")
	public void deleteFromBenefitOptionBig3COBypkgVersionId(
			@Param("pkgVersionId") int pkgVersionId);

	@Modifying
	@Query(value = "DELETE FROM PkgConfigOption pco where pco.pkgVersion.pkgVersion1up =:pkgVersionId")
	public void deleteFromPkgConfigOptionByPkgVersionId(
			@Param("pkgVersionId") int pkgVersionId);

	@Modifying
	@Query(value = "DELETE FROM PkgConfigOptionBenefitOptionXref pcbox "
			+ "WHERE pcbox.pkgConfigOption.pkgConfigOption1up =:PkgConfigOptionId")
	public void deleteFromPkgConfigOptionBenefitOptionXrefByPkgConfigOptionId(
			@Param("PkgConfigOptionId") int PkgConfigOptionId);

	@Modifying
	@Query(value = "DELETE FROM PkgVersion pv "
			+ "WHERE pv.pkgVersionWorkflowState.pkgVersionWorkflowState1up =:PackageVersionWorkflowStateId")
	public void deleteFromPkgVersionByPkgVersionWorkFlowState(
			@Param("PackageVersionWorkflowStateId") int PackageVersionWorkflowStateId);

	@Modifying
	@Query(value = "DELETE FROM PkgConfigOption pco "
			+ "WHERE pco.pkgVersionWorkflowState.pkgVersionWorkflowState1up = :PackageVersionWorkflowStateId")
	public void deleteFromPkgVersionWorkflowState(
			@Param("PackageVersionWorkflowStateId") int PackageVersionWorkflowStateId);

	@Modifying
	@Query(value = "DELETE FROM PkgConfigOption pco "
			+ "WHERE pco.pkgVersionWorkflowState.pkgVersionWorkflowState1up = :PackageVersionWorkflowStateId")
	public void deletePkgConfigOptionByPkgVersionWorkflowStateId(
			@Param("PackageVersionWorkflowStateId") int PackageVersionWorkflowStateId);

	@Modifying
	@Query(value = "Delete from TemplateAllowsIntVal intVal "
			+ "where intVal.templateAllowsIntValId.packageTemplate.packageTemplate1up = :tempId and "
			+ "intVal.templateAllowsIntValId.masterListIntVal.masterListIntVal1up = :mstId ")
	public void deleteAllowIntValById(@Param("tempId") int tempId,
			@Param("mstId") int mstId);

	@Query(value = "SELECT list from PkgVersionWorkflowState list "
			+ "left join fetch list.packageTemplate "
			+ "where list.packageTemplate.packageTemplate1up =:tempId ")
	public List<PkgVersionWorkflowState> findAllWorkFlowStateById(
			@Param("tempId") int tempId);

	@Query(value = "SELECT list from BenefitPackage list "
			+ "left join fetch list.packageTemplate "
			+ "where list.packageTemplate.packageTemplate1up =:tempId ")
	public List<BenefitPackage> findAllBenefitPackagesById(
			@Param("tempId") int tempId);

	@Query(value = "SELECT list from PkgVersion list "
			+ "left join fetch list.benefitPackage "
			+ "where list.benefitPackage.benefitPackage1up =:benefitPackageId")
	public List<PkgVersion> findAllWorkFlowStateOfPkgVersionByPackageId(
			@Param("benefitPackageId") int benefitPackageId);

	@Query(value = "SELECT workFlowStateVal from PkgVersionWorkflowState workFlowStateVal "
			+ "left join fetch workFlowStateVal.packageTemplate "
			+ "where workFlowStateVal.packageTemplate.packageTemplate1up = :id", countQuery = "SELECT count(workFlowStateVal) from PkgVersionWorkflowState workFlowStateVal "
			+ "left join workFlowStateVal.packageTemplate "
			+ "where workFlowStateVal.packageTemplate.packageTemplate1up = :id")
	public Page<PkgVersionWorkflowState> findAllowedWorkFlowStatesForTemplate(
			@Param("id") int id, Pageable pageable);

	// ------------Delete Template Queries----------------------------//

	@Modifying
	@Query(value = "Delete from ExcelViewInfo list "
			+ "Where list.packageTemplate.packageTemplate1up = :templateId")
	public void deleteExcelViewInfoByTemplateId(
			@Param("templateId") int templateId);

	@Modifying
	@Query("Delete from TemplateAllowsIntVal list "
			+ "Where list.templateAllowsIntValId.packageTemplate.packageTemplate1up = :templateId")
	public void deleteTemplateAllowIntValByTemplateId(
			@Param("templateId") int templateId);

	@Modifying
	@Query("Delete from TemplateAllowsMessageVal list "
			+ "Where list.templateAllowsMessageValId.packageTemplate.packageTemplate1up = :templateId")
	public void deleteTemplateAllowMessageValByTemplateId(
			@Param("templateId") int templateId);

	@Modifying
	@Query("Delete from TemplateAllowsStringVal "
			+ "Where templateAllowsStringValId.packageTemplate.packageTemplate1up = :templateId")
	public void deleteTemplateAllowStringValByTemplateId(
			@Param("templateId") int templateId);

	@Modifying
	@Query("Delete from TemplateAllowsLimitVal list "
			+ "Where list.templateAllowsLimitValId.packageTemplate.packageTemplate1up = :templateId")
	public void deleteTemplateAllowLimitValByTemplateId(
			@Param("templateId") int templateId);

	@Modifying
	@Query("Delete from TemplateAllowsServiceDefinition list "
			+ "Where list.templateAllowsServiceDefinitionId.packageTemplate.packageTemplate1up = :templateId")
	public void deleteTemplateAllowServiceDefinitionByTemplateId(
			@Param("templateId") int templateId);

	@Modifying
	@Query("Delete from ShapeValueDefault list "
			+ "Where list.templateTree.templateTree1up = :templateTree1up")
	public void deleteShapeValueDefaultByTemplateTreeId(
			@Param("templateTree1up") int templateTree1up);

	@Modifying
	@Query("Delete from TreeConnector list "
			+ "Where list.templateTree.templateTree1up = :templateTree1up")
	public void deleteTreeConnectorByTemplateTreeId(
			@Param("templateTree1up") int templateTree1up);

	@Modifying
	@Query("Delete from ShapeValueBenefitOptionForBig3CO list "
			+ "Where list.shapeValueBenefitOptionForBig3COId.treeShape.treeShape1up = :treeShape1up")
	public void deleteShapeValueBenefitOptionForBig3COByTreeShapeId(
			@Param("treeShape1up") int treeShape1up);

	@Modifying
	@Query("Delete from MatrixShapeBig3COXref list "
			+ "Where list.matrixShapeBig3COXrefId.treeShape.treeShape1up = :treeShape1up")
	public void deleteMatrixShapeBig3COXrefByTreeShapeId(
			@Param("treeShape1up") int treeShape1up);

	@Modifying
	@Query("Delete from MatrixShapeBig3COLimitXXref list "
			+ "Where list.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.treeShape.treeShape1up = :treeShape1up")
	public void deleteMatrixShapeBig3COLimitXXrefByTreeShapeId(
			@Param("treeShape1up") int treeShape1up);

	@Modifying
	@Query("Delete from LimitShapeBenefitOptionOverride list "
			+ "Where list.limitShapeBenefitOptionOverrideId.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.treeShape.treeShape1up = :treeShape1up")
	public void deleteLimitShapeBenefitOptionOverrideByTreeShapeId(
			@Param("treeShape1up") int treeShape1up);

	@Modifying
	@Query("Delete from MessageShapeBenefitOptionOverride list "
			+ "Where list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.treeShape.treeShape1up = :treeShape1up")
	public void deleteMessageShapeBenefitOptionOverrideByTreeShapeId(
			@Param("treeShape1up") int treeShape1up);

	@Modifying
	@Query("Delete from MatrixShapeLimitXref list "
			+ "Where list.matrixShapeLimitXrefId.treeShape.treeShape1up = :treeShape1up")
	public void deleteMatrixShapeLimitXrefByTreeShapeId(
			@Param("treeShape1up") int treeShape1up);

	@Modifying
	@Query("Delete from MatrixShapeMessageXref list "
			+ "Where list.matrixShapeMessageXrefId.treeShape.treeShape1up = :treeShape1up")
	public void deleteMatrixShapeMessageXrefByTreeShapeId(
			@Param("treeShape1up") int treeShape1up);

	@Modifying
	@Query("Delete from MatrixShapeBig3COMessageXXref list "
			+ "Where list.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.treeShape.treeShape1up = :treeShape1up")
	public void deleteMatrixShapeBig3COMessageXXrefByTreeShapeId(
			@Param("treeShape1up") int treeShape1up);

	@Modifying
	@Query("Delete from ExcelViewInfo list "
			+ "Where list.treeShape.treeShape1up = :treeShape1up")
	public void deleteExcelViewInfoByTreeShapeId(
			@Param("treeShape1up") int treeShape1up);

	@Modifying
	@Query("Delete from ShapeValueDefault list "
			+ "Where list.treeShape.treeShape1up = :treeShape1up")
	public void deleteShapeValueDefaultByTreeShapeId(
			@Param("treeShape1up") int treeShape1up);

	@Modifying
	@Query("Delete from TreeConnector list "
			+ "Where list.fromShape.treeShape1up = :treeShape1up")
	public void deleteTreeConnectorByTreeShapeFromId(
			@Param("treeShape1up") int treeShape1up);

	@Modifying
	@Query("Delete from TreeConnector list "
			+ "Where list.toShape.treeShape1up = :treeShape1up")
	public void deleteTreeConnectorByTreeShapeToId(
			@Param("treeShape1up") int treeShape1up);

	@Modifying
	@Query("Delete from ShapeValueBig3CO list "
			+ "Where list.shapeValueBig3COId.treeShape.treeShape1up = :treeShape1up")
	public void deleteShapeValueBig3COByTreeShapeId(
			@Param("treeShape1up") int treeShape1up);

	@Modifying
	@Query("Delete from ShapeValueTreeInstance list "
			+ "Where list.shapeValueTreeInstanceId.treeShape.treeShape1up = :treeShape1up")
	public void deleteShapeValueInstanceTreeByTreeShapeId(
			@Param("treeShape1up") int treeShape1up);

	@Query(value = "SELECT list from TemplateTree list"
			+ " left join fetch list.packageTemplate"
			+ " Where list.packageTemplate.packageTemplate1up = :id")
	public List<TemplateTree> findAllowsTemplateTreeById(@Param("id") int id);

	@Query(value = "SELECT list from TreeConnector list"
			+ " left join fetch list.templateTree "
			+ " left join fetch list.fromShape "
			+ " left join fetch list.toShape "
			+ " left join fetch list.switchOptionStringVal "
			+ " Where list.templateTree.templateTree1up= :id")
	public List<TreeConnector> findTreeConnectorsById(@Param("id") int id);

	@Query(value = "SELECT list from ShapeValueDefault list"
			+ " left join fetch list.templateTree "
			+ " left join fetch list.treeShape "
			+ " left join fetch list.setValueValueAsInt "
			+ " left join fetch list.setValueValueAsString "
			+ " Where list.templateTree.templateTree1up= :id")
	public List<ShapeValueDefault> findTreeShapeValueDefaultById(
			@Param("id") int id);

	@Query(value = "SELECT list from TreeShape list"
			+ " left join fetch list.templateTree"
			+ " left join fetch list.treeShapeType"
			+ " left join fetch list.ifYNTestAttribute"
			+ " left join fetch list.setValueParam"
			+ " left join fetch list.switchCriteria"
			+ " Where list.templateTree.templateTree1up = :id")
	public List<TreeShape> findTreeShapesById(@Param("id") int id);

	@Query(value = "SELECT list from MatrixShapeMessageXref list "
			+ " left Join fetch list.matrixShapeMessageXrefId "
			+ " left join fetch list.matrixShapeMessageXrefId.treeShape"
			+ " left join fetch list.matrixShapeMessageXrefId.masterListMessageVal"
			+ " WHERE list.matrixShapeMessageXrefId.treeShape.treeShape1up = :treeShape1up")
	public List<MatrixShapeMessageXref> findMatrixShapeMessageXrefById(
			@Param("treeShape1up") int treeShape1up);

	@Query(value = "SELECT list from MatrixShapeLimitXref list "
			+ " left Join fetch list.matrixShapeLimitXrefId "
			+ " left join fetch list.matrixShapeLimitXrefId.treeShape"
			+ " left join fetch list.matrixShapeLimitXrefId.masterListLimitVal"
			+ " WHERE list.matrixShapeLimitXrefId.treeShape.treeShape1up = :treeShape1up")
	public List<MatrixShapeLimitXref> findMatrixShapeLimitXrefById(
			@Param("treeShape1up") int treeShape1up);

	@Query(value = "Select list from MasterListIntVal list "
			+ "left join fetch list.masterListInt "
			+ "left join fetch list.shapeInt list1 "
			+ "left join fetch list1.templateTree list2 "
			+ "left join fetch list2.packageTemplate "
			+ "where list.masterListInt.masterList1up = :masterList1up "
			+ "and list2.packageTemplate.packageTemplate1up = :template1up")
	public Set<MasterListIntVal> findSetOfShapeValueDefaultUseAndUnUseTemplateIntValue(
			@Param("masterList1up") int masterList1up,
			@Param("template1up") int template1up);

	@Query(value = "Select list from MasterListIntVal list "
			+ "left join fetch list.masterListInt "
			+ "left join fetch list.shapeIntstanceTree list1 "
			+ "left join fetch list1.shapeValueTreeInstanceId.treeShape list2 "
			+ "left join fetch list2.templateTree list3 "
			+ "left join fetch list3.packageTemplate "
			+ "where list.masterListInt.masterList1up = :masterList1up "
			+ "and list3.packageTemplate.packageTemplate1up = :template1up")
	public Set<MasterListIntVal> findSetOfShapeValueTreeInstanceUseAndUnUseTemplateIntValue(
			@Param("masterList1up") int masterList1up,
			@Param("template1up") int template1up);

	@Query(value = "Select list from MasterListIntVal list "
			+ "left join fetch list.masterListInt "
			+ "left join fetch list.shapeIntBig3Co list1 "
			+ "left join fetch list1.shapeValueBig3COId.treeShape list2 "
			+ "left join fetch list2.templateTree list3 "
			+ "left join fetch list3.packageTemplate "
			+ "where list.masterListInt.masterList1up = :masterList1up "
			+ "and list3.packageTemplate.packageTemplate1up = :template1up")
	public Set<MasterListIntVal> findSetOfShapeValueBig3COUseAndUnUseTemplateIntValue(
			@Param("masterList1up") int masterList1up,
			@Param("template1up") int template1up);

	@Query(value = "Select list from MasterListIntVal list "
			+ "left join fetch list.masterListInt "
			+ "left join fetch list.shapeValueIntBig3Co list1 "
			+ "left join fetch list1.shapeValueBenefitOptionForBig3COId.treeShape list2 "
			+ "left join fetch list2.templateTree list3 "
			+ "left join fetch list3.packageTemplate "
			+ "where list.masterListInt.masterList1up = :masterList1up "
			+ "and list3.packageTemplate.packageTemplate1up = :template1up")
	public Set<MasterListIntVal> findSetOfShapeValueBenefitOptionForBig3COUseAndUnUseTemplateIntValue(
			@Param("masterList1up") int masterList1up,
			@Param("template1up") int template1up);

	@Query(value = "Select list from MasterListStringVal list "
			+ "left join fetch list.masterListString "
			+ "left join fetch list.shapeString list1 "
			+ "left join fetch list1.templateTree list2 "
			+ "left join fetch list2.packageTemplate "
			+ "where list.masterListString.masterList1up = :masterList1up "
			+ "and list2.packageTemplate.packageTemplate1up = :template1up")
	public Set<MasterListStringVal> findSetOfShapeValueDefaultUseAndUnUseTemplateStringValue(
			@Param("masterList1up") int masterList1up,
			@Param("template1up") int template1up);

	@Query(value = "Select list from MasterListStringVal list "
			+ "left join fetch list.masterListString "
			+ "left join fetch list.shapeIntstanceTree list1 "
			+ "left join fetch list1.shapeValueTreeInstanceId.treeShape list2 "
			+ "left join fetch list2.templateTree list3 "
			+ "left join fetch list3.packageTemplate "
			+ "where list.masterListString.masterList1up = :masterList1up "
			+ "and list3.packageTemplate.packageTemplate1up = :template1up")
	public Set<MasterListStringVal> findSetOfShapeValueTreeInstanceUseAndUnUseTemplateStringValue(
			@Param("masterList1up") int masterList1up,
			@Param("template1up") int template1up);

	@Query(value = "Select list from MasterListStringVal list "
			+ "left join fetch list.masterListString "
			+ "left join fetch list.shapeStringBig3Co list1 "
			+ "left join fetch list1.shapeValueBig3COId.treeShape list2 "
			+ "left join fetch list2.templateTree list3 "
			+ "left join fetch list3.packageTemplate "
			+ "where list.masterListString.masterList1up = :masterList1up "
			+ "and list3.packageTemplate.packageTemplate1up = :template1up")
	public Set<MasterListStringVal> findSetOfShapeValueBig3COUseAndUnUseTemplateStringValue(
			@Param("masterList1up") int masterList1up,
			@Param("template1up") int template1up);

	@Query(value = "Select list from MasterListStringVal list "
			+ "left join fetch list.masterListString "
			+ "left join fetch list.shapeValueStringBig3Co list1 "
			+ "left join fetch list1.shapeValueBenefitOptionForBig3COId.treeShape list2 "
			+ "left join fetch list2.templateTree list3 "
			+ "left join fetch list3.packageTemplate "
			+ "where list.masterListString.masterList1up = :masterList1up "
			+ "and list3.packageTemplate.packageTemplate1up = :template1up")
	public Set<MasterListStringVal> findSetOfShapeValueBenefitOptionForBig3COUseAndUnUseTemplateStringValue(
			@Param("masterList1up") int masterList1up,
			@Param("template1up") int template1up);

	@Query(value = "Select list from MasterListStringVal list "
			+ "left join fetch list.masterListString "
			+ "left join fetch list.benPkgMasterList list1 "
			+ "left join fetch list1.benefitPackage list2 "
			+ "left join fetch list2.packageTemplate "
			+ "left join fetch list1.masterListStringVal "
			+ "where list.masterListString.masterList1up = :masterList1up "
			+ "and list2.packageTemplate.packageTemplate1up = :template1up")
	public Set<MasterListStringVal> findSetOfPkgMasterListForBig3COForNetworkUseAndUnUseTemplateStringValue(
			@Param("masterList1up") int masterList1up,
			@Param("template1up") int template1up);

	@Query(value = "Select list from MasterListServiceDefinition list "
			+ "left join fetch list.masterListService "
			+ "left join fetch list.benPkgMasterList list1 "
			+ "left join fetch list1.benefitPackage list2 "
			+ "left join fetch list2.packageTemplate "
			+ "left join fetch list1.masterListServiceDefinition "
			+ "where list2.packageTemplate.packageTemplate1up = :template1up")
	public Set<MasterListServiceDefinition> findSetOfPkgMasterListForBig3COForSeviceDefnUseAndUnUseTemplateStringValue(
			@Param("template1up") int template1up);

	@Query(value = "Select list from MasterListStringVal list "
			+ "left join fetch list.masterListString "
			+ "left join fetch list.masterCat1 "
			+ "left join fetch list.masterCat2 "
			+ "left join fetch list.masterCat3 "
			+ "left join fetch list.masterPost "
			+ "where list.masterListString.masterList1up = :masterList1up")
	public Set<MasterListStringVal> findSetOfPkgMasterListForBig3COForServiceUseAndUnUseTemplateStringValue(
			@Param("masterList1up") int masterList1up);

	@Query(value = "Select list from MasterListStringVal list "
			+ "left join fetch list.masterListString "
			+ "left join fetch list.treeSwitchOption list1 "
			+ "left join fetch list1.templateTree list2 "
			+ "left join fetch list2.packageTemplate "
			+ "where list.masterListString.masterList1up = :masterList1up "
			+ "and list2.packageTemplate.packageTemplate1up = :template1up")
	public Set<MasterListStringVal> findSetOfTreeConnectorUseAndUnUseTemplateStringValue(
			@Param("masterList1up") int masterList1up,
			@Param("template1up") int template1up);

	@Query(value = "Select list from MasterListStringVal list "
			+ "left join fetch list.masterListString "
			+ "left join fetch list.configOptionVer list1 "
			+ "left join fetch list1.pkgVersion list2 "
			+ "left join fetch list2.benefitPackage list3 "
			+ "left join fetch list3.packageTemplate "
			+ "where list.masterListString.masterList1up = :masterList1up "
			+ "and list3.packageTemplate.packageTemplate1up = :template1up")
	public Set<MasterListStringVal> findSetOfPkgConfigOptionUseAndUnUseTemplateStringValue(
			@Param("masterList1up") int masterList1up,
			@Param("template1up") int template1up);

	@Query(value = "Select list from MasterListLimitVal list "
			+ "left join fetch list.masterListLimit "
			+ "left join fetch list.masterShapeLimit list1 "
			+ "left join fetch list1.matrixShapeLimitXrefId.treeShape list2 "
			+ "left join fetch list2.templateTree list3 "
			+ "left join fetch list3.packageTemplate "
			+ "where list.masterListLimit.masterList1up = :masterList1up "
			+ "and list3.packageTemplate.packageTemplate1up = :template1up")
	public Set<MasterListLimitVal> findSetOfMatrixShapeLimitXrefUseAndUnUseTemplateLimitValue(
			@Param("masterList1up") int masterList1up,
			@Param("template1up") int template1up);

	@Query(value = "Select list from MasterListMessageVal list "
			+ "left join fetch list.masterListMessage "
			+ "left join fetch list.masterMessageXref list1 "
			+ "left join fetch list1.matrixShapeMessageXrefId.treeShape list2 "
			+ "left join fetch list2.templateTree list3 "
			+ "left join fetch list3.packageTemplate "
			+ "where list.masterListMessage.masterList1up = :masterList1up "
			+ "and list3.packageTemplate.packageTemplate1up = :template1up")
	public Set<MasterListMessageVal> findSetOfMatrixShapeMessageXrefUseAndUnUseTemplateMessageValue(
			@Param("masterList1up") int masterList1up,
			@Param("template1up") int template1up);

	@Query(value = "Select list from MasterListServiceDefinition list "
			+ "left join fetch list.masterListService "
			+ "left join fetch list.benPkgMasterList list1 "
			+ "left join fetch list1.benefitPackage list2 "
			+ "left join fetch list2.packageTemplate "
			+ "where list.masterListService.masterList1up = :masterList1up "
			+ "and list2.packageTemplate.packageTemplate1up = :template1up")
	public List<MasterListServiceDefinition> findSetOfPkgMasterListForBig3COUseAndUnUseTemplateServiceDefinition(
			@Param("masterList1up") int masterList1up,
			@Param("template1up") int template1up);

	@Query(value = "SELECT count(instanceTreeVal) from PkgVerInstanceTree instanceTreeVal "
			+ "left join instanceTreeVal.templateTree "
			+ "where instanceTreeVal.templateTree.templateTree1up = :id")
	public int findCommonUsedBenifit(@Param("id") int id);

	@Query(value = "SELECT list from TemplateTree list "
			+ "left join fetch list.packageTemplate "
			+ "where list.packageTemplate.packageTemplate1up = :id", countQuery = "SELECT count(list) from TemplateTree list "
			+ "where list.packageTemplate.packageTemplate1up = :id")
	public Page<TemplateTree> findAllowedTemplateTreeForTemplate(
			@Param("id") int id, Pageable pageable);

	@Query(value = "SELECT list.packageTemplate1up from PackageTemplate list "
			+ "left join list.templateBenefitPkg list1 "
			+ "where list.packageTemplate1up in :tempSummary group by list.packageTemplate1up having count(list1) > 0 ")
	public List<Integer> findSetOfBenefitPackageByTemplateId(
			@Param("tempSummary") List<Integer> tempSummary);

	@Query(value = "SELECT count(list1) from PackageTemplate list "
			+ "left join list.templateBenefitPkg list1 "
			+ "where list.packageTemplate1up = :tempId")
	public Integer findSetOfBenefitPackageForEachTemplate(
			@Param("tempId") int tempId);

	@Modifying
	@Query(value = "UPDATE TemplateAllowsStringVal list SET list.defaultValFlag = false "
			+ "Where list.templateAllowsStringValId.packageTemplate.packageTemplate1up = :tempId ")
	public void updateDefaultFlagForStringVal(@Param("tempId") int tempId);

	@Modifying
	@Query(value = "UPDATE TemplateAllowsIntVal list SET list.defaultValFlag = false "
			+ "Where list.templateAllowsIntValId.packageTemplate.packageTemplate1up = :tempId ")
	public void updateDefaultFlagForIntVal(@Param("tempId") int tempId);

	@Query(value = "SELECT count(list) from PkgVersion list "
			+ "where list.pkgVersionWorkflowState.pkgVersionWorkflowState1up = :id")
	public int findNoOfPackagesInThisState(@Param("id") int id);

	@Query(value = "Select list from PkgVersionWorkflowState list "
			+ "left join fetch list.pkgVerWorkFlow "
			+ "left join fetch list.configOptionVerState "
			+ "where list.pkgVersionWorkflowState1up = :id")
	public PkgVersionWorkflowState findSetOfPkgConfigOptionAndPkgVersionByWorkFlowStateId(
			@Param("id") int id);

	@Modifying
	@Query(value = "UPDATE PackageTemplate p SET p.templateWorkflowState.templateWorkflowState1up = :templateWorkFlowid "
			+ "WHERE p.packageTemplate1up = :packageTemplateid")
	public int updateWorkflowStateforPackage(
			@Param("templateWorkFlowid") int templateWorkFlowid,
			@Param("packageTemplateid") int packageTemplateid);

	@Query(value = "SELECT list from MatrixShapeLimitXref list "
			+ " left Join fetch list.matrixShapeLimitXrefId "
			+ " left join fetch list.matrixShapeLimitXrefId.treeShape"
			+ " left join fetch list.matrixShapeLimitXrefId.masterListLimitVal"
			+ " WHERE list.matrixShapeLimitXrefId.treeShape.treeShape1up = :treeShape1up and list.availableFlag =true")
	public List<MatrixShapeLimitXref> findActiveMatrixShapeLimitXrefById(
			@Param("treeShape1up") int treeShape1up);

	@Query(value = "SELECT list from ShapeValueDefault list"
			+ " left Join fetch list.treeShape"
			+ " left join fetch list.templateTree"
			+ " left join fetch list.setValueValueAsInt"
			+ " left join fetch list.setValueValueAsString"
			+ " WHERE list.templateTree.templateTree1up = :id")
	public List<ShapeValueDefault> findAllTreeShapesByTemplateTreeId(
			@Param("id") int id);

}
