package com.tmg.ebscore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tmg.ebscore.orm.ebspackage.AccountPkgConfigOptionXref;
import com.tmg.ebscore.orm.ebspackage.ExcelViewInfo;
import com.tmg.ebscore.orm.ebspackage.LimitShapeBenefitOptionOverride;
import com.tmg.ebscore.orm.ebspackage.MatrixShapeBig3COLimitXXref;
import com.tmg.ebscore.orm.ebspackage.MatrixShapeBig3COMessageXXref;
import com.tmg.ebscore.orm.ebspackage.MessageShapeBenefitOptionOverride;
import com.tmg.ebscore.orm.ebspackage.PkgConfigOptionBenefitOptionXref;
import com.tmg.ebscore.orm.ebspackage.PkgMasterListForBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBenefitOptionForBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueTreeInstance;
import com.tmg.ebscore.orm.masterlist.MasterListMessageVal;
import com.tmg.ebscore.orm.masterlist.MasterListStringVal;
import com.tmg.ebscore.orm.packageversion.BenefitOptionBig3CO;
import com.tmg.ebscore.orm.packageversion.BenefitOptionTierdata;
import com.tmg.ebscore.orm.packageversion.BenefitSelectionTierdata;
import com.tmg.ebscore.orm.packageversion.PkgVerBenefitOption;
import com.tmg.ebscore.orm.packageversion.PkgVerBig3CO;
import com.tmg.ebscore.orm.packageversion.PkgVerInstanceTree;
import com.tmg.ebscore.orm.packageversion.PkgVerProductBenefitOptionXref;
import com.tmg.ebscore.orm.packageversion.PkgVersion;
import com.tmg.ebscore.orm.packageversion.PkgVersionProductXref;
import com.tmg.ebscore.orm.packageversion.PkgVersionWorkflowState;
import com.tmg.ebscore.orm.template.MatrixShapeMessageXref;
import com.tmg.ebscore.orm.template.TreeShape;

public interface PackageVersionRepository extends
		JpaRepository<PkgVersion, Long>, PackageVersionCustomRepository {

	@Query(value = "Select packageVersion from PkgVersion packageVersion "
			+ "left join fetch packageVersion.benefitPackage "
			+ "left join fetch packageVersion.pkgVersionWorkflowState "
			+ "where packageVersion.benefitPackage.benefitPackage1up = :pkgId", countQuery = "Select count(packageVersion) from PkgVersion packageVersion "
			+ "where packageVersion.benefitPackage.benefitPackage1up = :pkgId")
	public Page<PkgVersion> findPackageVersionByPkgId(
			@Param("pkgId") int pkgId, Pageable pageable);

	// public List<PkgVerBig3CO> findPkgVerBig3CoByPkgVerId(@Param("pkgVerId")
	// int pkgVerId);
	@Query(value = "Select list FROM AccountPkgConfigOptionXref list "
			+ "left join fetch list.pkgConfigOption")
	public List<AccountPkgConfigOptionXref> findAllAccountPkgConfigOptionXref();

	@Query(value = "SELECT commonBenefit from PkgVerInstanceTree commonBenefit "
			+ "left join fetch commonBenefit.pkgVersion "
			+ "left join fetch commonBenefit.templateTree "
			+ "where commonBenefit.pkgVersion.pkgVersion1up = :id", countQuery = "SELECT count(commonBenefit) from PkgVerInstanceTree commonBenefit "
			+ "left join commonBenefit.pkgVersion "
			+ "left join commonBenefit.templateTree "
			+ "where commonBenefit.pkgVersion.pkgVersion1up = :id")
	public Page<PkgVerInstanceTree> findCommonBenefitForEachPackageVersion(
			@Param("id") int id, Pageable pageable);

	@Query(value = "Select pvb3o from PkgVerBig3CO pvb3o "
			+ "left join fetch pvb3o.pkgMasterListForBig3CO list1 "
			+ "left join fetch  list1.masterListServiceDefinition list2 "
			+ "left join fetch  list2.masterListStringValCat1 "
			+ "left join fetch  list2.masterListStringValCat2 "
			+ "left join fetch  list2.masterListStringValCat3 "
			+ "left join fetch  list2.masterListStringValPostString "
			+ "left join fetch  list1.masterListStringVal "
			+ "left join fetch pvb3o.pkgVersion "
			+ "left join fetch pvb3o.pkgVerInstanceTree "
			+ "where pvb3o.pkgVersion.pkgVersion1up = :pkgVerId", countQuery = "Select count(pvb3o) from PkgVerBig3CO pvb3o "
			+ "where pvb3o.pkgVersion.pkgVersion1up = :pkgVerId")
	public Page<PkgVerBig3CO> findPkgVerBig3CoByPkgVerId(
			@Param("pkgVerId") int pkgVerId, Pageable pageable);

	@Query(value = "Select list from PkgMasterListForBig3CO list "
			+ "left join fetch list.benefitPackage "
			+ "left join fetch list.masterListServiceDefinition "
			+ "left join fetch list.masterListStringVal "
			+ "where list.benefitPackage.benefitPackage1up = :pkgId", countQuery = "Select count(list)"
			+ " from PkgMasterListForBig3CO list "
			+ "where list.benefitPackage.benefitPackage1up = :pkgId")
	public Page<PkgMasterListForBig3CO> findPkgMasterListBig3CoById(
			@Param("pkgId") int pkgId, Pageable pageable);

	@Query(value = "SELECT benefitOption from PkgVerBenefitOption benefitOption"
			+ " where benefitOption.pkgVersion.pkgVersion1up = :pkgVersionId", countQuery = "Select count(benefitOption)"
			+ " from PkgVerBenefitOption benefitOption "
			+ "where benefitOption.pkgVersion.pkgVersion1up = :pkgVersionId")
	public Page<PkgVerBenefitOption> findAllBeneitOptionById(
			@Param("pkgVersionId") int id, Pageable pageable);

	@Query(value = "SELECT distinct list from MasterListStringVal list "
			+ "left join fetch list.masterListString list1 "
			+ "where list1.name = 'Benefit Rule' and list.masterListStringVal1up in :id")
	public List<MasterListStringVal> findmasterListStringValforBenefitRule(
			@Param("id") List<Integer> id);

	// **************** Find BY Id Methods *********************//

	@Query(value = "SELECT list from BenefitSelectionTierdata list "
			+ "left join fetch list.pkgVerBig3CO "
			+ "where list.benefitSelectionTierdata1up = :id")
	public BenefitSelectionTierdata findBenefitSelectionTierdataById(
			@Param("id") int id);

	@Query(value = "SELECT list from PkgVerBenefitOption list "
			+ "left join fetch list.pkgVersion "
			+ "where list.pkgVerBenefitOption1up = :id")
	public PkgVerBenefitOption findPkgVerBenefitOptionById(@Param("id") int id);

	@Query(value = "SELECT list from PkgVerBig3CO list "
			+ "left join fetch list.pkgVersion "
			+ "left join fetch list.pkgMasterListForBig3CO "
			+ "left join fetch list.pkgVerInstanceTree "
			+ "where list.pkgVerBig3CO1up = :id")
	public PkgVerBig3CO findPkgVerBig3COById(@Param("id") int id);

	@Query(value = "SELECT list from PkgVerBig3CO list "
			+ "left join fetch list.pkgVersion "
			+ "left join fetch list.pkgMasterListForBig3CO "
			+ "left join fetch list.pkgVerInstanceTree "
			+ "where list.pkgVerBig3CO1up = :id")
	public List<PkgVerBig3CO> findPkgVersionBig3COById(@Param("id") int id);

	@Query(value = "SELECT list.pkgVerBig3CO1up from PkgVerBig3CO list "
			+ "where list.pkgVerBig3CO1up = :id")
	public List<Integer> findPkgVersionBig3Co1upById(@Param("id") int Id);

	@Query(value = "SELECT list from PkgVerInstanceTree list "
			+ "left join fetch list.pkgVersion list1 "
			+ "left join fetch list1.pkgVersionWorkflowState "
			+ "left join fetch list.templateTree "
			+ "where list.pkgVerInstanceTree1up = :id")
	public PkgVerInstanceTree findPkgVerInstanceTreeById(@Param("id") int id);

	@Query(value = "SELECT list from PkgVerProductBenefitOptionXref list "
			+ "left join fetch list.pkgVerProductBenefitOptionXrefId "
			+ "left join fetch list.pkgVerProductBenefitOptionXrefId.pkgVersion "
			+ "left join fetch list.pkgVerProductBenefitOptionXrefId.pkgConfigOption "
			+ "left join fetch list.pkgVerProductBenefitOptionXrefId.pkgVerBenefitOption "
			+ " where list.pkgVerProductBenefitOptionXrefId.pkgVersion.pkgVersion1up = :pkgVersion1up"
			+ " AND list.pkgVerProductBenefitOptionXrefId.pkgConfigOption.pkgConfigOption1up = :pkgConfigOption1up"
			+ " AND list.pkgVerProductBenefitOptionXrefId.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVerBenefitOption1up")
	public PkgVerProductBenefitOptionXref findPkgVerProductBenefitOptionXrefById(
			@Param("pkgVersion1up") int pkgVersion1up,
			@Param("pkgConfigOption1up") int pkgConfigOption1up,
			@Param("pkgVerBenefitOption1up") int pkgVerBenefitOption1up);

	@Query(value = "SELECT list from PkgVersion list "
			+ "left join fetch list.benefitPackage "
			+ "left join fetch list.pkgVersionWorkflowState "
			+ "where list.pkgVersion1up = :id")
	public PkgVersion findPackageVersionById(@Param("id") int id);

	@Query(value = "SELECT list from PkgVersionProductXref list "
			+ "left join fetch list.pkgVersionProductXrefId "
			+ "left join fetch list.pkgVersionProductXrefId.pkgVersion "
			+ "left join fetch list.pkgVersionProductXrefId.pkgConfigOption "
			+ " where list.pkgVersionProductXrefId.pkgVersion.pkgVersion1up = :pkgVersion1up"
			+ " AND list.pkgVersionProductXrefId.pkgConfigOption.pkgConfigOption1up = :pkgConfigOption1up")
	public PkgVersionProductXref findPkgVersionProductXrefById(
			@Param("pkgVersion1up") int pkgVersion1up,
			@Param("pkgConfigOption1up") int pkgConfigOption1up);

	@Query(value = "SELECT list from PkgVersionWorkflowState list "
			+ "left join fetch list.packageTemplate "
			+ "left join fetch list.pkgVerWorkFlow "
			+ "left join fetch list.configOptionVerState "
			+ "where list.pkgVersionWorkflowState1up = :id")
	public PkgVersionWorkflowState findPkgVersionWorkflowStateById(
			@Param("id") int id);

	// ******************************************************************************************//

	// **************** Delete BY Id Methods *********************//

	@Modifying
	@Query(value = "Delete from BenefitSelectionTierdata list "
			+ "where list.benefitSelectionTierdata1up = :id")
	public void deleteBenefitSelectionTierdataById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from PkgVerBenefitOption list "
			+ "where list.pkgVerBenefitOption1up = :id")
	public void deletePkgVerBenefitOptionById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from PkgVerBig3CO list "
			+ "where list.pkgVerBig3CO1up = :id")
	public void deletePkgVerBig3COById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from PkgVerInstanceTree list "
			+ "where list.pkgVerInstanceTree1up = :id")
	public void deletePkgVerInstanceTreeById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete  from PkgVerProductBenefitOptionXref list "
			+ " where list.pkgVerProductBenefitOptionXrefId.pkgVersion.pkgVersion1up = :pkgVersion1up"
			+ " AND list.pkgVerProductBenefitOptionXrefId.pkgConfigOption.pkgConfigOption1up = :pkgConfigOption1up"
			+ " AND list.pkgVerProductBenefitOptionXrefId.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVerBenefitOption1up")
	public void deletePkgVerProductBenefitOptionXrefById(
			@Param("pkgVersion1up") int pkgVersion1up,
			@Param("pkgConfigOption1up") int pkgConfigOption1up,
			@Param("pkgVerBenefitOption1up") int pkgVerBenefitOption1up);

	@Modifying
	@Query(value = "Delete from PkgVersion list "
			+ "where list.pkgVersion1up = :id")
	public void deletePackageVersionById(@Param("id") int id);

	@Modifying
	@Query(value = "Delete from PkgVersionProductXref list "
			+ " where list.pkgVersionProductXrefId.pkgVersion.pkgVersion1up = :pkgVersion1up"
			+ " AND list.pkgVersionProductXrefId.pkgConfigOption.pkgConfigOption1up = :pkgConfigOption1up")
	public void deletePkgVersionProductXrefById(
			@Param("pkgVersion1up") int pkgVersion1up,
			@Param("pkgConfigOption1up") int pkgConfigOption1up);

	@Query(value = "Select packageVersion from PkgVersion packageVersion "
			+ "left join fetch packageVersion.benefitPackage list1 "
			+ "left join fetch list1.packageTemplate "
			+ "left join fetch packageVersion.pkgVersionWorkflowState", countQuery = "Select count(packageVersion) from PkgVersion packageVersion")
	public Page<PkgVersion> findAllPackageVersion(Pageable pageable);

	@Query(value = "SELECT benSel from BenefitSelectionTierdata benSel"
			+ " left join fetch benSel.pkgVerInstanceTree tree"
			+ " left join fetch tree.pkgVersion"
			+ " left join fetch benSel.pkgVerBig3CO list1"
			+ " left join fetch list1.pkgMasterListForBig3CO list2"
			+ " left join fetch list2.masterListStringVal "
			+ " left join fetch list2.masterListServiceDefinition list3"
			+ " left join fetch list3.masterListStringValCat1"
			+ " left join fetch list3.masterListStringValCat2"
			+ " left join fetch list3.masterListStringValCat3"
			+ " left join fetch list3.masterListStringValPostString"
			+ " left join fetch benSel.applyCoinsuranceShape"
			+ " left join fetch benSel.coinsuranceYesValShape"
			+ " left join fetch benSel.coinsuranceYesVal"
			+ " left join fetch benSel.coinsuranceNoValShape"
			+ " left join fetch benSel.coinsuranceNoVal"
			+ " left join fetch benSel.copayShape"
			+ " left join fetch benSel.copayVal"
			+ " left join fetch benSel.allowedAmtShape"
			+ " left join fetch benSel.allowedCtrShape"
			+ " left join fetch benSel.allowedAmt"
			+ " left join fetch benSel.allowedCtr"
			+ " where benSel.pkgVerInstanceTree.pkgVerInstanceTree1up = :versionInstance", countQuery = "SELECT count(benSel) from BenefitSelectionTierdata benSel"
			+ " where benSel.pkgVerInstanceTree.pkgVerInstanceTree1up = :versionInstance")
	public Page<BenefitSelectionTierdata> findAllBenefitSelectionTierDataByCommonBenifit(
			@Param("versionInstance") int versionInstance, Pageable page);

	@Query(value = "Select pvb3o from PkgVerBig3CO pvb3o"
			+ " left join fetch pvb3o.pkgVersion"
			+ " where pvb3o.pkgVersion.pkgVersion1up = :version1up")
	public List<PkgVerBig3CO> findPkgVerBig3ListCoByPkgVerId(
			@Param("version1up") int version1up);

	@Query(value = "SELECT commonBenefit from PkgVerInstanceTree commonBenefit "
			+ "left join fetch commonBenefit.pkgVersion "
			+ "left join fetch commonBenefit.templateTree "
			+ "where commonBenefit.pkgVersion.pkgVersion1up = :pkgVersion1up "
			+ "and commonBenefit.templateTree.templateTree1up = :templateTree1up ")
	public List<PkgVerInstanceTree> findCommonBenefitForEachPackageVersionByTemplateTree(
			@Param("pkgVersion1up") int pkgVersion1up,
			@Param("templateTree1up") int templateTree1up);

	@Modifying
	@Query(value = "DELETE FROM BenefitOptionBig3CO bobig3"
			+ " where bobig3.pkgVerInstanceTree.pkgVerInstanceTree1up = :pkgVersionInstanceTreeId")
	public void deleteFromBenefitOptionBig3COByPkgVersionInstanceTreeId(
			@Param("pkgVersionInstanceTreeId") int PkgVersionInstanceTreeId);

	@Modifying
	@Query(value = "DELETE FROM ExcelViewInfo evInfo"
			+ " where evInfo.pkgVerInstanceTree.pkgVerInstanceTree1up = :PkgVersionInstanceTreeId")
	public void deleteFromExcelViewInfoByPkgVersionInstanceTreeId(
			@Param("PkgVersionInstanceTreeId") int PkgVersionInstanceTreeId);

	@Modifying
	@Query(value = "DELETE FROM BenefitOptionBig3CO bobig3"
			+ " where bobig3.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVersionBig3COId")
	public void deleteFromBenefitOptionBig3COByPkgVersionBig3COId(
			@Param("pkgVersionBig3COId") int PkgVersionBig3COId);

	@Modifying
	@Query(value = "DELETE FROM ShapeValueBenefitOptionForBig3CO svBenOptFrBig3Co"
			+ " where svBenOptFrBig3Co.shapeValueBenefitOptionForBig3COId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVersionBig3COId")
	public void deleteFromShapeValueBenefitOptionForBig3COByPkgVersionBig3COId(
			@Param("pkgVersionBig3COId") int PkgVersionBig3COId);

	@Modifying
	@Query(value = "DELETE FROM ShapeValueBig3CO svBig3Co"
			+ " where svBig3Co.shapeValueBig3COId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVersionBig3COId")
	public void deleteFromShapeValueBig3COByPkgVersionBig3COId(
			@Param("pkgVersionBig3COId") int PkgVersionBig3COId);

	@Modifying
	@Query(value = "DELETE FROM ShapeValueBenefitOptionForBig3CO svBenOpFrBig3Co"
			+ " where svBenOpFrBig3Co.shapeValueBenefitOptionForBig3COId.pkgVerInstanceTree.pkgVerInstanceTree1up = :PkgVersionInstanceTreeId")
	public void deleteFromShapeValueBenefitOptionForBig3COByPkgVersionInstanceTreeId(
			@Param("PkgVersionInstanceTreeId") int PkgVersionInstanceTreeId);

	@Modifying
	@Query(value = "DELETE FROM ShapeValueBig3CO svBig3Co"
			+ " where svBig3Co.shapeValueBig3COId.pkgVerInstanceTree.pkgVerInstanceTree1up = :PkgVersionInstanceTreeId ")
	public void deleteShapeValueBig3COByPkgVersionInstanceTreeId(
			@Param("PkgVersionInstanceTreeId") int PkgVersionInstanceTreeId);

	@Modifying
	@Query(value = "DELETE FROM ShapeValueTreeInstance svTreeInst"
			+ " where svTreeInst.shapeValueTreeInstanceId.pkgVerInstanceTree.pkgVerInstanceTree1up = :PkgVersionInstanceTreeId")
	public void deleteShapeValueTreeInstanceByPkgVersionInstanceTreeId(
			@Param("PkgVersionInstanceTreeId") int PkgVersionInstanceTreeId);

	@Query(value = "Select pvb3o from PkgVerBig3CO pvb3o "
			+ "left join fetch pvb3o.pkgMasterListForBig3CO list1 "
			+ "left join fetch  list1.masterListServiceDefinition list2 "
			+ "left join fetch  list2.masterListStringValCat1 "
			+ "left join fetch  list2.masterListStringValCat2 "
			+ "left join fetch  list2.masterListStringValCat3 "
			+ "left join fetch  list2.masterListStringValPostString "
			+ "left join fetch  list1.masterListStringVal "
			+ "left join fetch pvb3o.pkgVersion "
			+ "left join fetch pvb3o.pkgVerInstanceTree "
			+ "where pvb3o.pkgVersion.pkgVersion1up = :pkgVerId")
	public List<PkgVerBig3CO> findPkgVerBig3CoByPkgVerId(
			@Param("pkgVerId") int pkgVerID);

	@Query(value = "SELECT list from PkgVerInstanceTree list "
			+ "left join fetch list.pkgVersion "
			+ "left join fetch list.templateTree "
			+ "left join fetch list.pkgVerBenInstanceTree "
			+ "where list.pkgVersion.pkgVersion1up = :pkgVerId")
	public List<PkgVerInstanceTree> findPkgVerInstanceTreeByPkgVerId(
			@Param("pkgVerId") int pkgVerId);

	@Query(value = "SELECT list from PkgVerInstanceTree list "
			+ "left join fetch list.pkgVersion "
			+ "left join fetch list.templateTree "
			+ "where list.pkgVersion.pkgVersion1up = :pkgVerId")
	public List<PkgVerInstanceTree> findPkgVerInstTreeByPkgVerId(
			@Param("pkgVerId") int pkgVerId);

	@Query(value = "SELECT list from PkgVerBenefitOption list "
			+ "left join fetch list.pkgVersion "
			+ "where list.pkgVersion.pkgVersion1up = :id")
	public List<PkgVerBenefitOption> findPkgVerBenefitOptionByPkgVerId(
			@Param("id") int id);

	@Query(value = "SELECT list from BenefitSelectionTierdata list "
			+ "where list.pkgVerInstanceTree.pkgVerInstanceTree1up = :instId "
			+ "and list.pkgVerBig3CO.pkgVerBig3CO1up = :big3COId")
	public BenefitSelectionTierdata findBenefitSelectionTierdataByPkgVerId(
			@Param("instId") int instId, @Param("big3COId") int big3COId);

	@Query(value = "Select pvb3o from PkgVerBig3CO pvb3o "
			+ "left join fetch pvb3o.pkgMasterListForBig3CO list1 "
			+ "left join fetch  list1.masterListServiceDefinition list2 "
			+ "left join fetch  list2.masterListStringValCat1 "
			+ "left join fetch  list2.masterListStringValCat2 "
			+ "left join fetch  list2.masterListStringValCat3 "
			+ "left join fetch  list2.masterListStringValPostString "
			+ "left join fetch  list1.masterListStringVal "
			+ "left join fetch pvb3o.pkgVersion "
			+ "left join fetch pvb3o.pkgVerInstanceTree "
			+ "where pvb3o.pkgVerInstanceTree.pkgVerInstanceTree1up = :instTreeId "
			+ "and pvb3o.pkgVersion.pkgVersion1up = :pkgVersionId")
	public List<PkgVerBig3CO> findPkgVerBig3CoByInstancetreeId(
			@Param("instTreeId") int instTreeId,
			@Param("pkgVersionId") int pkgVersionId);

	// ******************************************************************************************//
	@Query(value = "SELECT list from PkgVersion list "
			+ "left join fetch list.benefitPackage "
			+ "left join fetch list.pkgVersionWorkflowState "
			+ "where list.pkgVersion1up = :id")
	public List<PkgVersion> findPackageVersionByListId(
			@Param("id") List<Integer> id);

	@Query(value = "SELECT big3co FROM PkgVerBig3CO big3co "
			+ " left join fetch big3co.pkgVersion list1 "
			+ " left join fetch list1.benefitPackage "
			+ " where list1.benefitPackage.benefitPackage1up = :pkgId")
	public List<PkgVerBig3CO> findPkgVerBig3COByPkgid(@Param("pkgId") int pkgId);

	@Query(value = "SELECT pVerInstTree FROM PkgVerInstanceTree pVerInstTree"
			+ " left join fetch pVerInstTree.pkgVersion list1"
			+ " left join fetch list1.benefitPackage"
			+ " where list1.benefitPackage.benefitPackage1up = :pkgId ")
	public List<PkgVerInstanceTree> findPkgVerInstanceTreeByPkgId(
			@Param("pkgId") int pkgId);

	@Query(value = "SELECT pkgVerBenOpt FROM PkgVerBenefitOption pkgVerBenOpt"
			+ " left join fetch pkgVerBenOpt.pkgVersion list1"
			+ " left join fetch list1.benefitPackage "
			+ " where list1.benefitPackage.benefitPackage1up = :pkgId")
	public List<PkgVerBenefitOption> findPkgVerBenefitOptionByPkgId(
			@Param("pkgId") int pkgId);

	@Query(value = "SELECT list FROM PkgVerInstanceTree list"
			+ " left join fetch list.shapeIntstanceTree"
			+ " where list.pkgVerInstanceTree1up = :oldInstanceTreeId")
	public PkgVerInstanceTree findSetShapeValueTreeInstanceByPkgVerInstanceTreeId(
			@Param("oldInstanceTreeId") int oldInstanceTreeId);

	@Query(value = "SELECT list from PkgVersion list "
			+ "left join fetch list.benefitPackage "
			+ "left join fetch list.pkgVersionWorkflowState "
			+ "left join fetch list.pkgBenefitOption "
			+ "left join fetch list.pkgBigCo "
			+ "left join fetch list.pkgVerTree "
			+ "left join fetch list.pkgProductOprtion "
			+ "left join fetch list.pkgProductXref "
			+ "left join fetch list.pkgVerBenOption "
			+ "left join fetch list.configOptionVer "
			+ "where list.pkgVersion1up = :id")
	public PkgVersion findPackageVersionSetById(@Param("id") int id);

	// ******************************Delete Queries*************************//
	// By pkgVerId

	@Modifying
	@Query(value = "Delete from PkgVersion ver "
			+ "where ver.pkgVersion1up = :pkgVerId")
	public void deletePackageVersionByPkgVerId(@Param("pkgVerId") int pkgVerId);

	@Modifying
	@Query(value = "Delete from PkgVerInstanceTree inst "
			+ "where inst.pkgVersion.pkgVersion1up = :pkgVerId")
	public void deletePkgVerInstanceTreeByPkgVerId(
			@Param("pkgVerId") int pkgVerId);

	@Modifying
	@Query(value = "Delete from PkgVerBenefitOption option "
			+ "where option.pkgVersion.pkgVersion1up = :pkgVerId")
	public void deletePkgVerBenefitOptionByPkgVerId(
			@Param("pkgVerId") int pkgVerId);

	@Modifying
	@Query(value = "Delete from PkgConfigOption config "
			+ "where config.pkgVersion.pkgVersion1up = :pkgVerId")
	public void deletePkgConfigOptionByPkgVerId(@Param("pkgVerId") int pkgVerId);

	@Modifying
	@Query(value = "Delete from BenefitOptionBig3CO ben "
			+ "where ben.pkgVersion.pkgVersion1up = :pkgVerId")
	public void deleteBenefitOptionBig3COByPkgVerId(
			@Param("pkgVerId") int pkgVerId);

	@Modifying
	@Query(value = "Delete from PkgVerBig3CO ver "
			+ "where ver.pkgVersion.pkgVersion1up = :pkgVerId")
	public void deletePkgVerBig3COByPkgVerId(@Param("pkgVerId") int pkgVerId);

	@Modifying
	@Query(value = "Delete from PackageVersionUIElementValue ui "
			+ "where ui.pkgVersion.pkgVersion1up = :pkgVerId")
	public void deletePackageVersionUIElementValueByPkgVerId(
			@Param("pkgVerId") int pkgVerId);

	// By pkgVerInstanceId

	@Modifying
	@Query(value = "Delete from PkgVerBig3CO big3Co where"
			+ " big3Co.pkgVerInstanceTree.pkgVerInstanceTree1up = :pkgVerInstanceId")
	public void deletePkgVerBig3COByPkgVerInstanceId(
			@Param("pkgVerInstanceId") int pkgVerInstanceId);

	@Modifying
	@Query(value = "Delete from BenefitSelectionTierdata tierData where"
			+ " tierData.pkgVerInstanceTree.pkgVerInstanceTree1up = :pkgVerInstanceId")
	public void deleteBenefitSelectionTierdataBypkgVerInstanceId(
			@Param("pkgVerInstanceId") int pkgVerInstanceId);

	@Modifying
	@Query(value = "Delete from ShapeValueTreeInstance shapeTree where"
			+ " shapeTree.shapeValueTreeInstanceId.pkgVerInstanceTree.pkgVerInstanceTree1up "
			+ "= :pkgVerInstanceId")
	public void deleteShapeValueTreeInstanceBypkgVerInstanceId(
			@Param("pkgVerInstanceId") int pkgVerInstanceId);

	@Modifying
	@Query(value = "Delete from ShapeValueBig3CO shapeTree where"
			+ " shapeTree.shapeValueBig3COId.pkgVerInstanceTree.pkgVerInstanceTree1up "
			+ "= :pkgVerInstanceId")
	public void deleteShapeValueBig3COBypkgVerInstanceId(
			@Param("pkgVerInstanceId") int pkgVerInstanceId);

	@Modifying
	@Query(value = "Delete from ShapeValueBenefitOptionForBig3CO shapeTree where"
			+ " shapeTree.shapeValueBenefitOptionForBig3COId.pkgVerInstanceTree.pkgVerInstanceTree1up "
			+ "= :pkgVerInstanceId")
	public void deleteShapeValueBenefitOptionForBig3COBypkgVerInstanceId(
			@Param("pkgVerInstanceId") int pkgVerInstanceId);

	@Modifying
	@Query(value = "Delete from ExcelViewInfo excel where"
			+ " excel.pkgVerInstanceTree.pkgVerInstanceTree1up "
			+ "= :pkgVerInstanceId")
	public void deleteExcelViewInfoBypkgVerInstanceId(
			@Param("pkgVerInstanceId") int pkgVerInstanceId);

	@Modifying
	@Query(value = "Delete from BenefitOptionBig3CO option where"
			+ " option.pkgVerInstanceTree.pkgVerInstanceTree1up "
			+ "= :pkgVerInstanceId")
	public void deleteBenefitOptionBig3COBypkgVerInstanceId(
			@Param("pkgVerInstanceId") int pkgVerInstanceId);

	// By pkgVerBig3CO1up

	@Modifying
	@Query(value = "Delete from BenefitSelectionTierdata tier where"
			+ " tier.pkgVerBig3CO.pkgVerBig3CO1up " + "= :pkgVerBig3CO1up")
	public void deleteBenefitSelectionTierdataBypkgVerBig3CoId(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "Delete from ShapeValueBig3CO shape where"
			+ " shape.shapeValueBig3COId.pkgVerBig3CO.pkgVerBig3CO1up "
			+ "= :pkgVerBig3CO1up")
	public void deleteShapeValueBig3COBypkgVerBig3CoId(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "Delete from ShapeValueBenefitOptionForBig3CO shape where"
			+ " shape.shapeValueBenefitOptionForBig3COId.pkgVerBig3CO.pkgVerBig3CO1up "
			+ "= :pkgVerBig3CO1up")
	public void deleteShapeValueBenefitOptionForBig3COBypkgVerBig3CoId(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "Delete from MatrixShapeBig3COXref matrixShape where"
			+ " matrixShape.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up "
			+ "= :pkgVerBig3CO1up")
	public void deleteMatrixShapeBig3COXrefBypkgVerBig3CoId(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "Delete from MatrixShapeBig3COMessageXXref matrixShape where"
			+ " matrixShape.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up "
			+ "= :pkgVerBig3CO1up")
	public void deleteMatrixShapeBig3COMessageXXrefBypkgVerBig3CoId(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "Delete from MessageShapeBenefitOptionOverride message where"
			+ " message.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up "
			+ "= :pkgVerBig3CO1up")
	public void deleteMessageShapeBenefitOptionOverrideBypkgVerBig3CoId(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "Delete from MatrixShapeBig3COLimitXXref matrixShape where"
			+ " matrixShape.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up "
			+ "= :pkgVerBig3CO1up")
	public void deleteMatrixShapeBig3COLimitXXrefBypkgVerBig3CoId(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "Delete from LimitShapeBenefitOptionOverride limit where"
			+ " limit.limitShapeBenefitOptionOverrideId.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up "
			+ "= :pkgVerBig3CO1up")
	public void deleteLimitShapeBenefitOptionOverrideBypkgVerBig3CoId(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "Delete from BenefitOptionBig3CO option where"
			+ " option.pkgVerBig3CO.pkgVerBig3CO1up " + "= :pkgVerBig3CO1up")
	public void deleteBenefitOptionBig3COBypkgVerBig3CoId(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	// By pkgVerBenefitOptionId

	@Modifying
	@Query(value = "Delete from ShapeValueBenefitOptionForBig3CO shape where "
			+ "shape.shapeValueBenefitOptionForBig3COId.pkgVerBenefitOption.pkgVerBenefitOption1up "
			+ "= :pkgVerBenefitOptionId")
	public void deleteShapeValueBenefitOptionForBig3COBypkgVerBenefitOptId(
			@Param("pkgVerBenefitOptionId") int pkgVerBenefitOptionId);

	@Modifying
	@Query(value = "Delete from PkgConfigOptionBenefitOptionXref config where "
			+ "config.pkgVerBenefitOption.pkgVerBenefitOption1up "
			+ "= :pkgVerBenefitOptionId")
	public void deletePkgConfigOptionBenefitOptionXrefBypkgVerBenefitOptId(
			@Param("pkgVerBenefitOptionId") int pkgVerBenefitOptionId);

	@Modifying
	@Query(value = "Delete from MessageShapeBenefitOptionOverride message where "
			+ "message.messageShapeBenefitOptionOverrideId.pkgVerBenefitOption.pkgVerBenefitOption1up "
			+ "= :pkgVerBenefitOptionId")
	public void deleteMessageShapeBenefitOptionOverrideBypkgVerBenefitOptId(
			@Param("pkgVerBenefitOptionId") int pkgVerBenefitOptionId);

	@Modifying
	@Query(value = "Delete from LimitShapeBenefitOptionOverride limit where "
			+ "limit.limitShapeBenefitOptionOverrideId.pkgVerBenefitOption.pkgVerBenefitOption1up "
			+ "= :pkgVerBenefitOptionId")
	public void deleteLimitShapeBenefitOptionOverrideBypkgVerBenefitOptId(
			@Param("pkgVerBenefitOptionId") int pkgVerBenefitOptionId);

	@Modifying
	@Query(value = "Delete from PkgVerProductBenefitOptionXref product where "
			+ "product.pkgVerProductBenefitOptionXrefId.pkgVerBenefitOption.pkgVerBenefitOption1up "
			+ "= :pkgVerBenefitOptionId")
	public void deletePkgVerProductBenefitOptionXrefBypkgVerBenefitOptId(
			@Param("pkgVerBenefitOptionId") int pkgVerBenefitOptionId);

	@Modifying
	@Query(value = "Delete from BenefitOptionBig3CO option where"
			+ " option.pkgVerBenefitOption.pkgVerBenefitOption1up "
			+ "= :pkgVerBenefitOptionId")
	public void deleteBenefitOptionBig3COBypkgVerBenefitOptId(
			@Param("pkgVerBenefitOptionId") int pkgVerBenefitOptionId);

	// By pkgConfigOption1up
	@Modifying
	@Query(value = "Delete from PkgVersionProductXref prod where "
			+ "prod.pkgVersionProductXrefId.pkgConfigOption.pkgConfigOption1up = :pkgConfigOption1up")
	public void deletePkgVersionProductXrefByConfigId(
			@Param("pkgConfigOption1up") int pkgConfigOption1up);

	@Modifying
	@Query(value = "Delete from PkgConfigOptionBenefitOptionXref config where "
			+ "config.pkgConfigOption.pkgConfigOption1up = :pkgConfigOption1up")
	public void deletePkgConfigOptionBenefitOptionXrefByConfigId(
			@Param("pkgConfigOption1up") int pkgConfigOption1up);

	@Modifying
	@Query(value = "Delete from PkgVerProductBenefitOptionXref prod where "
			+ "prod.pkgVerProductBenefitOptionXrefId.pkgConfigOption.pkgConfigOption1up "
			+ "= :pkgConfigOption1up")
	public void deletePkgVerProductBenefitOptionXrefByConfigId(
			@Param("pkgConfigOption1up") int pkgConfigOption1up);

	@Modifying
	@Query(value = "Delete from PkgConfigOption config "
			+ "where config.pkgConfigOption1up = :pkgConfigOptionId")
	public void deletePkgConfigOptionByByConfigId(
			@Param("pkgConfigOptionId") int pkgConfigOptionId);

	@Query(value = "SELECT benSel from BenefitOptionTierdata benSel"
			+ " left join fetch benSel.pkgVerInstanceTree tree"
			+ " left join fetch benSel.pkgVerBig3CO list1"
			+ " left join fetch list1.pkgMasterListForBig3CO list2"
			+ " left join fetch list2.masterListStringVal "
			+ " left join fetch list2.masterListServiceDefinition list3"
			+ " left join fetch list3.masterListStringValCat1 list4"
			+ " left join fetch list3.masterListStringValCat2"
			+ " left join fetch list3.masterListStringValCat3"
			+ " left join fetch list3.masterListStringValPostString"
			+ " left join fetch benSel.applyCoinsuranceShape"
			+ " left join fetch benSel.coinsuranceYesValShape"
			+ " left join fetch benSel.coinsuranceYesVal"
			+ " left join fetch benSel.coinsuranceNoValShape"
			+ " left join fetch benSel.coinsuranceNoVal"
			+ " left join fetch benSel.copayShape"
			+ " left join fetch benSel.copayVal"
			+ " left join fetch benSel.allowedAmtShape"
			+ " left join fetch benSel.allowedCtrShape"
			+ " left join fetch benSel.allowedAmt"
			+ " left join fetch benSel.allowedCtr"
			+ " left join fetch benSel.benefitOption"
			+ " where benSel.pkgVerInstanceTree.pkgVerInstanceTree1up = :versionInstance "
			+ " and benSel.benefitOption.pkgVerBenefitOption1up = :benfitOptionId"
			+ " and list4.stringValue != 'Default Values'", countQuery = "SELECT count(benSel) from BenefitOptionTierdata benSel"
			+ " where benSel.pkgVerInstanceTree.pkgVerInstanceTree1up = :versionInstance "
			+ " and benSel.benefitOption.pkgVerBenefitOption1up = :benfitOptionId"
			+ " and benSel.pkgVerBig3CO.pkgMasterListForBig3CO.masterListServiceDefinition.masterListStringValCat1.stringValue != 'Default Values'")
	public Page<BenefitOptionTierdata> findAllBenefitSelectionForBenefitOption(
			@Param("versionInstance") int versionInstance,
			@Param("benfitOptionId") int benfitOptionId, Pageable page);

	@Modifying
	@Query(value = "delete from BenefitOptionTierdata list"
			+ " Where list.benefitOptionTierdata1up = :id")
	public void deleteBenefitOptionTierdataById(@Param("id") int id);

	@Query(value = "SELECT list from BenefitOptionTierdata list"
			+ " Where list.benefitOptionTierdata1up = :id")
	public BenefitOptionTierdata findBenefitOptionTierdataById(
			@Param("id") int id);

	@Query(value = "SELECT list from BenefitOptionBig3CO list"
			+ " left join fetch list.pkgVersion"
			+ " left join fetch list.pkgVerBig3CO"
			+ " left join fetch list.pkgVerBenefitOption"
			+ " left join fetch list.pkgVerInstanceTree"
			+ " Where list.benefitOptionBig3CO1up = :id")
	public BenefitOptionBig3CO findBenefitOptionBig3COById(@Param("id") int id);

	@Query(value = "SELECT list from BenefitOptionBig3CO list"
			+ " left join fetch list.pkgVersion"
			+ " left join fetch list.pkgVerBig3CO"
			+ " left join fetch list.pkgVerBenefitOption"
			+ " left join fetch list.pkgVerInstanceTree"
			+ " Where list.pkgVerBenefitOption.pkgVerBenefitOption1up = :id")
	public List<BenefitOptionBig3CO> findBenefitOpBig3COByPkgVerBenOptId(
			@Param("id") int id);

	@Modifying
	@Query(value = "delete from BenefitOptionBig3CO list"
			+ " Where list.benefitOptionBig3CO1up = :id")
	public void deleteBenefitOptionBig3COById(@Param("id") int id);

	@Query(value = "Select list.pkgVerBig3CO1up from PkgVerBig3CO list "
			+ "left join list.pkgVerBenOption3Co list1 "
			+ "where list.pkgVersion.pkgVersion1up = :packageVersionId "
			+ "group by list.pkgVerBig3CO1up having count(list1) > 0 ")
	public List<Integer> findSetOfPkgVerBig3COUseAndUnUse(
			@Param("packageVersionId") int packageVersionId);

	@Query(value = "SELECT list from BenefitOptionBig3CO list "
			+ " left join fetch list.pkgVersion "
			+ " left join fetch list.pkgVerBig3CO "
			+ " left join fetch list.pkgVerBenefitOption "
			+ " left join fetch list.pkgVerInstanceTree "
			+ " where list.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVerBenefitOption1up ")
	public List<BenefitOptionBig3CO> findEnabledServicetypeforBenefitOption(
			@Param("pkgVerBenefitOption1up") int pkgVerBenefitOption1up);

	@Query(value = "Select list.pkgVerBenefitOption1up from PkgVerBenefitOption list "
			+ "left join list.pkgConfigXref list1 "
			+ "where list.pkgVersion.pkgVersion1up = :packageVersionId "
			+ "group by list.pkgVerBenefitOption1up having count(list1) > 0 ")
	public List<Integer> findSetOfPkgConfigOptionBenefitOptionXrefUseAndUnUse(
			@Param("packageVersionId") int packageVersionId);

	@Query(value = "Select list from PkgVerBig3CO list "
			+ "left join fetch list.pkgVersion "
			+ "left join fetch list.pkgMasterListForBig3CO list1 "
			+ "left join fetch list1.masterListServiceDefinition "
			+ "left join fetch list1.masterListStringVal "
			+ "left join fetch list.pkgVerInstanceTree "
			+ "where list.pkgVersion.pkgVersion1up = :pkgVersionId")
	public List<PkgVerBig3CO> findAllServicetypeforBenefitOption(
			@Param("pkgVersionId") int pkgVersionId);

	@Query(value = "Select list.pkgVerBig3CO.pkgVerBig3CO1up from BenefitOptionBig3CO list "
			+ "left join list.pkgVerBenefitOption "
			+ "left join list.pkgVerBig3CO "
			+ "where list.coveredFlag = true "
			+ "and list.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVersionBenefitOptionId")
	public List<Integer> findCoveredBenefitOption(
			@Param("pkgVersionBenefitOptionId") int pkgVersionBenefitOptionId);

	@Query(value = "Select list.pkgVerInstanceTree.pkgVerInstanceTree1up from BenefitOptionBig3CO list "
			+ "left join list.pkgVerBenefitOption "
			+ "left join list.pkgVerInstanceTree "
			+ "left join list.pkgVerBig3CO "
			+ "where list.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVersionBenefitOptionId "
			+ "and list.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVersionBig3COId "
			+ "and list.pkgVersion.pkgVersion1up = :pkgVersionId ")
	public Integer findInstanceTreeBenefitOption(
			@Param("pkgVersionBenefitOptionId") int pkgVersionBenefitOptionId,
			@Param("pkgVersionBig3COId") int pkgVersionBig3COId,
			@Param("pkgVersionId") int pkgVersionId);

	@Query(value = "Select list.pkgVerBig3CO.pkgVerBig3CO1up from BenefitOptionBig3CO list "
			+ "left join list.pkgVerBenefitOption "
			+ "left join list.pkgVerBig3CO "
			+ "where list.treatAs100PctFlag = true "
			+ "and list.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVersionBenefitOptionId")
	public List<Integer> findTreat100BenefitOption(
			@Param("pkgVersionBenefitOptionId") int pkgVersionBenefitOptionId);

	@Query(value = "Select list from BenefitOptionBig3CO list "
			+ "left join fetch list.pkgVerBenefitOption "
			+ "where list.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVersionBenefitOptionId")
	public List<BenefitOptionBig3CO> findAllBenefitOptionBig3COByBenefitOption(
			@Param("pkgVersionBenefitOptionId") int pkgVersionBenefitOptionId);

	@Query(value = "Select list from ExcelViewInfo list "
			+ "left join fetch list.treeShape "
			+ "where list.pkgVerInstanceTree.pkgVerInstanceTree1up = :instanceTree1Up")
	public List<ExcelViewInfo> findAllTreeShapeForCommonBenefit(
			@Param("instanceTree1Up") int instanceTree1Up);

	@Query(value = "SELECT list from ShapeValueTreeInstance list "
			+ "left join fetch list.masterListIntVal "
			+ "left join fetch list.masterListStringVal "
			+ "left join fetch list.shapeValueTreeInstanceId.pkgVerInstanceTree "
			+ "left join fetch list.shapeValueTreeInstanceId.treeShape "
			+ "where list.shapeValueTreeInstanceId.pkgVerInstanceTree.pkgVerInstanceTree1up = :instanceTree1Up")
	public List<ShapeValueTreeInstance> findInstanceTreesAllTreeShapeValue(
			@Param("instanceTree1Up") int instanceTree1Up);

	@Query(value = "Select list from ShapeValueBig3CO list "
			+ "left join fetch list.masterListIntVal "
			+ "left join fetch list.masterListStringVal "
			+ "left join fetch list.shapeValueBig3COId "
			+ "left join fetch list.shapeValueBig3COId.treeShape "
			+ "left join fetch list.shapeValueBig3COId.pkgVerInstanceTree "
			+ "left join fetch list.shapeValueBig3COId.pkgVerBig3CO "
			+ "where list.shapeValueBig3COId.pkgVerInstanceTree.pkgVerInstanceTree1up = :versionInstance "
			+ "And list.shapeValueBig3COId.treeShape.treeShape1up in :treeShapeIds "
			+ "And list.shapeValueBig3COId.pkgVerBig3CO.pkgVerBig3CO1up in :pkgVerBig3CoIds ")
	public List<ShapeValueBig3CO> findAllShapeValueforPkgVerBig3Co(
			@Param("versionInstance") int versionInstance,
			@Param("pkgVerBig3CoIds") List<Integer> pkgVerBig3CoIds,
			@Param("treeShapeIds") List<Integer> treeShapeIds);

	@Query(value = "Select list from ShapeValueBenefitOptionForBig3CO list "
			+ "left join fetch list.masterListIntVal "
			+ "left join fetch list.masterListStringVal "
			+ "left join fetch list.shapeValueBenefitOptionForBig3COId "
			+ "left join fetch list.shapeValueBenefitOptionForBig3COId.treeShape "
			+ "left join fetch list.shapeValueBenefitOptionForBig3COId.pkgVerInstanceTree "
			+ "left join fetch list.shapeValueBenefitOptionForBig3COId.pkgVerBig3CO "
			+ "left join fetch list.shapeValueBenefitOptionForBig3COId.pkgVerBenefitOption "
			+ "where list.shapeValueBenefitOptionForBig3COId.pkgVerInstanceTree.pkgVerInstanceTree1up = :versionInstance "
			+ "And list.shapeValueBenefitOptionForBig3COId.pkgVerBenefitOption.pkgVerBenefitOption1up = :benefitOptionId "
			+ "And list.shapeValueBenefitOptionForBig3COId.treeShape.treeShape1up in :treeShapeIds "
			+ "And list.shapeValueBenefitOptionForBig3COId.pkgVerBig3CO.pkgVerBig3CO1up in :pkgVerBig3CoIds ")
	public List<ShapeValueBenefitOptionForBig3CO> findBenefitOptionDeductibleValueforPkgVerBig3Co(
			@Param("versionInstance") int versionInstance,
			@Param("benefitOptionId") int benefitOptionId,
			@Param("pkgVerBig3CoIds") List<Integer> pkgVerBig3CoIds,
			@Param("treeShapeIds") List<Integer> treeShapeIds);

	@Query(value = "Select list from ShapeValueBenefitOptionForBig3CO list "
			+ "left join fetch list.masterListIntVal "
			+ "left join fetch list.masterListStringVal "
			+ "left join fetch list.shapeValueBenefitOptionForBig3COId "
			+ "left join fetch list.shapeValueBenefitOptionForBig3COId.treeShape "
			+ "left join fetch list.shapeValueBenefitOptionForBig3COId.pkgVerInstanceTree "
			+ "left join fetch list.shapeValueBenefitOptionForBig3COId.pkgVerBig3CO "
			+ "left join fetch list.shapeValueBenefitOptionForBig3COId.pkgVerBenefitOption "
			+ "where list.shapeValueBenefitOptionForBig3COId.pkgVerInstanceTree.pkgVerInstanceTree1up = :versionInstance "
			+ "And list.shapeValueBenefitOptionForBig3COId.pkgVerBenefitOption.pkgVerBenefitOption1up = :benefitOptionId "
			+ "And list.shapeValueBenefitOptionForBig3COId.treeShape.treeShape1up in :treeShapeIds "
			+ "And list.shapeValueBenefitOptionForBig3COId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVerBig3CoId ")
	public List<ShapeValueBenefitOptionForBig3CO> findBenefitOptionDefaultDeductibleValueforPkgVerBig3Co(
			@Param("versionInstance") int versionInstance,
			@Param("benefitOptionId") int benefitOptionId,
			@Param("pkgVerBig3CoId") int pkgVerBig3CoId,
			@Param("treeShapeIds") List<Integer> treeShapeIds);

	@Query(value = "Select list from MatrixShapeBig3COLimitXXref list "
			+ "left join fetch list.matrixShapeBig3COLimitXXrefId list1 "
			+ "left join fetch list1.masterListLimitVal "
			+ "left join fetch list1.matrixShapeBig3COXrefId list2 "
			+ "left join fetch list2.pkgVerBig3CO list3 "
			+ "left join fetch list2.treeShape "
			+ "Where list3.pkgVerBig3CO1up in :pkgVerBig3CoIds ")
	public List<MatrixShapeBig3COLimitXXref> findAllLimitsForPkgVerBig3Co(
			@Param("pkgVerBig3CoIds") List<Integer> pkgVerBig3CoIds);

	@Query(value = "Select list from MatrixShapeBig3COMessageXXref list "
			+ "left join fetch list.matrixShapeBig3COMessageXXrefId list1 "
			+ "left join fetch list1.masterListMessageVal "
			+ "left join fetch list1.matrixShapeBig3COXrefId list2 "
			+ "left join fetch list2.pkgVerBig3CO list3 "
			+ "left join fetch list2.treeShape "
			+ "Where list3.pkgVerBig3CO1up in :pkgVerBig3CoIds ")
	public List<MatrixShapeBig3COMessageXXref> findAllMessagessForPkgVerBig3Co(
			@Param("pkgVerBig3CoIds") List<Integer> pkgVerBig3CoIds);

	@Query(value = "Select list from PkgVerBig3CO list "
			+ "left join fetch list.pkgMasterListForBig3CO list1 "
			+ "left join fetch list1.masterListServiceDefinition "
			+ "Where list.pkgVerInstanceTree.pkgVerInstanceTree1up = :versionInstance "
			+ "And list1.masterListServiceDefinition.masterListStringValCat1.stringValue = 'Default Values'")
	public PkgVerBig3CO findDefaultPkgVerbig3Co(
			@Param("versionInstance") int versionInstance);

	@Query(value = "Select list from ShapeValueTreeInstance list "
			+ "left join fetch list.shapeValueTreeInstanceId "
			+ "left join fetch list.shapeValueTreeInstanceId.treeShape "
			+ "left join fetch list.shapeValueTreeInstanceId.pkgVerInstanceTree "
			+ "Where list.shapeValueTreeInstanceId.pkgVerInstanceTree.pkgVerInstanceTree1up = :versionInstance")
	public List<ShapeValueTreeInstance> findDefaultShapeInstanceTreeValue(
			@Param("versionInstance") int versionInstance);

	@Query(value = "Select list from MatrixShapeBig3COLimitXXref list "
			+ "left join fetch list.matrixShapeBig3COLimitXXrefId list1 "
			+ "left join fetch list1.masterListLimitVal "
			+ "left join fetch list1.matrixShapeBig3COXrefId list2 "
			+ "left join fetch list2.pkgVerBig3CO list3 "
			+ "left join fetch list2.treeShape "
			+ "Where list3.pkgVerBig3CO1up = :pkgVerBig3CO1up ")
	public List<MatrixShapeBig3COLimitXXref> findAllLimitsForDefaultPkgVerBig3Co(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Query(value = "Select list from MatrixShapeBig3COMessageXXref list "
			+ "left join fetch list.matrixShapeBig3COMessageXXrefId list1 "
			+ "left join fetch list1.masterListMessageVal "
			+ "left join fetch list1.matrixShapeBig3COXrefId list2 "
			+ "left join fetch list2.pkgVerBig3CO list3 "
			+ "left join fetch list2.treeShape "
			+ "Where list3.pkgVerBig3CO1up = :pkgVerBig3CO1up ")
	public List<MatrixShapeBig3COMessageXXref> findAllMessagessForDefaultPkgVerBig3Co(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Query(value = "Select list from MatrixShapeBig3COMessageXXref list "
			+ "left join fetch list.matrixShapeBig3COMessageXXrefId list1 "
			+ "left join fetch list1.masterListMessageVal "
			+ "left join fetch list1.matrixShapeBig3COXrefId list2 "
			+ "left join fetch list2.pkgVerBig3CO list3 "
			+ "left join fetch list2.treeShape list4 "
			+ "Where list3.pkgVerBig3CO1up = :pkgVerBig3CO1up and list4.treeShape1up = :treeShape1up")
	public List<MatrixShapeBig3COMessageXXref> findAllEnableMessageValForPkgVer(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up,
			@Param("treeShape1up") int treeShape1up);

	@Query(value = "Select list from MatrixShapeMessageXref list "
			+ "Where list.matrixShapeMessageXrefId.treeShape.treeShape1up = :treeShape1up "
			+ "And list.availableFlag = true")
	public List<MatrixShapeMessageXref> findAllMessageValByTreeShapeId(
			@Param("treeShape1up") int treeShape1up);

	@Query(value = "Select list from LimitShapeBenefitOptionOverride list "
			+ "left join fetch list.limitShapeBenefitOptionOverrideId list1 "
			+ "left join fetch list1.matrixShapeBig3COLimitXXrefId list2 "
			+ "left join fetch list1.pkgVerBenefitOption list6 "
			+ "left join fetch list2.matrixShapeBig3COXrefId list3 "
			+ "left join fetch list2.masterListLimitVal "
			+ "left join fetch list3.pkgVerBig3CO list4 "
			+ "left join fetch list3.treeShape list5 "
			+ "Where list6.pkgVerBenefitOption1up = :benefitOptionId "
			+ "AND list5.treeShape1up = :treeShapeId "
			+ "And list4.pkgVerBig3CO1up in :pkgVerBig3CoIds")
	public List<LimitShapeBenefitOptionOverride> findAllLimitsForBenefitOptionPkgVerBig3Co(
			@Param("benefitOptionId") int benefitOptionId,
			@Param("pkgVerBig3CoIds") List<Integer> pkgVerBig3CoIds,
			@Param("treeShapeId") int treeShapeId);

	@Query(value = "Select list from LimitShapeBenefitOptionOverride list "
			+ "left join fetch list.limitShapeBenefitOptionOverrideId list1 "
			+ "left join fetch list1.matrixShapeBig3COLimitXXrefId list2 "
			+ "left join fetch list1.pkgVerBenefitOption list6 "
			+ "left join fetch list2.matrixShapeBig3COXrefId list3 "
			+ "left join fetch list2.masterListLimitVal "
			+ "left join fetch list3.pkgVerBig3CO list4 "
			+ "left join fetch list3.treeShape list5 "
			+ "Where list6.pkgVerBenefitOption1up = :benefitOptionId "
			+ "AND list5.treeShape1up = :treeShapeId "
			+ "And list4.pkgVerBig3CO1up = :pkgVerBig3CoId")
	public List<LimitShapeBenefitOptionOverride> findDefaultLimitsForBenefitOptionPkgVerBig3Co(
			@Param("benefitOptionId") int benefitOptionId,
			@Param("pkgVerBig3CoId") int pkgVerBig3CoId,
			@Param("treeShapeId") int treeShapeId);

	@Query(value = "Select list from MessageShapeBenefitOptionOverride list "
			+ "left join fetch list.messageShapeBenefitOptionOverrideId list1 "
			+ "left join fetch list1.matrixShapeBig3COMessageXXrefId list2 "
			+ "left join fetch list1.pkgVerBenefitOption list6 "
			+ "left join fetch list2.matrixShapeBig3COXrefId list3 "
			+ "left join fetch list2.masterListMessageVal "
			+ "left join fetch list3.pkgVerBig3CO list4 "
			+ "left join fetch list3.treeShape list5 "
			+ "Where list6.pkgVerBenefitOption1up = :benefitOptionId "
			+ "AND list5.treeShape1up = :treeShapeId "
			+ "And list4.pkgVerBig3CO1up in :pkgVerBig3CoIds")
	public List<MessageShapeBenefitOptionOverride> findAllMessagessForBenefitOptionPkgVerBig3Co(
			@Param("benefitOptionId") int benefitOptionId,
			@Param("pkgVerBig3CoIds") List<Integer> pkgVerBig3CoIds,
			@Param("treeShapeId") int treeShapeId);

	@Query(value = "Select list from MessageShapeBenefitOptionOverride list "
			+ "left join fetch list.messageShapeBenefitOptionOverrideId list1 "
			+ "left join fetch list1.matrixShapeBig3COMessageXXrefId list2 "
			+ "left join fetch list1.pkgVerBenefitOption list6 "
			+ "left join fetch list2.matrixShapeBig3COXrefId list3 "
			+ "left join fetch list2.masterListMessageVal "
			+ "left join fetch list3.pkgVerBig3CO list4 "
			+ "left join fetch list3.treeShape list5 "
			+ "Where list6.pkgVerBenefitOption1up = :benefitOptionId "
			+ "AND list5.treeShape1up = :treeShapeId "
			+ "And list4.pkgVerBig3CO1up = :pkgVerBig3CoId")
	public List<MessageShapeBenefitOptionOverride> findDefaultMessagessForBenefitOptionPkgVerBig3Co(
			@Param("benefitOptionId") int benefitOptionId,
			@Param("pkgVerBig3CoId") int pkgVerBig3CoId,
			@Param("treeShapeId") int treeShapeId);

	@Modifying
	@Query(value = "Delete from MatrixShapeMessageXref list"
			+ " Where list.matrixShapeMessageXrefId.treeShape.treeShape1up = :treeShape1up"
			+ " AND list.matrixShapeMessageXrefId.masterListMessageVal.masterListMessageVal1up = :masterListMessageVal1up")
	public void deleteAllMatrixShapeMessage(
			@Param("treeShape1up") int treeShape1up,
			@Param("masterListMessageVal1up") int masterListMessageVal1up);

	@Query(value = "SELECT list from MessageShapeBenefitOptionOverride list "
			+ "left join fetch list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.masterListMessageVal "
			+ "left join fetch list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.treeShape "
			+ "Where list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.masterListMessageVal.masterListMessageVal1up = :masterListMessageVal1up")
	public MasterListMessageVal findMasterListMessageValById(
			@Param("masterListMessageVal1up") int masterListMessageVal1up);

	@Query(value = "SELECT list from MessageShapeBenefitOptionOverride list "
			+ "left join fetch list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.masterListMessageVal "
			+ "left join fetch list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.treeShape "
			+ "Where list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.treeShape.treeShape1up = :treeShape1up")
	public TreeShape findTreeShapeById(@Param("treeShape1up") int treeShape1up);

	@Query(value = "Select list from MessageShapeBenefitOptionOverride list "
			+ "left join fetch list.messageShapeBenefitOptionOverrideId list1 "
			+ "left join fetch list1.matrixShapeBig3COMessageXXrefId list2 "
			+ "left join fetch list1.pkgVerBenefitOption list6 "
			+ "left join fetch list2.matrixShapeBig3COXrefId list3 "
			+ "left join fetch list2.masterListMessageVal "
			+ "left join fetch list3.pkgVerBig3CO list4 "
			+ "left join fetch list3.treeShape list5 "
			+ "Where list5.treeShape1up = :treeShapeId "
			+ "And list4.pkgVerBig3CO1up = :pkgVerBig3CoId "
			+ "And list6.pkgVerBenefitOption1up = :pkgVerBenefitOptionId")
	public List<MessageShapeBenefitOptionOverride> findAllEnableMessageValForBenefitOption(
			@Param("pkgVerBig3CoId") int pkgVerBig3CoId,
			@Param("pkgVerBenefitOptionId") int pkgVerBenefitOptionId,
			@Param("treeShapeId") int treeShapeId);

	@Query(value = "Select list from LimitShapeBenefitOptionOverride list "
			+ "left join fetch list.limitShapeBenefitOptionOverrideId list1 "
			+ "left join fetch list1.matrixShapeBig3COLimitXXrefId list2 "
			+ "left join fetch list1.pkgVerBenefitOption list6 "
			+ "left join fetch list2.matrixShapeBig3COXrefId list3 "
			+ "left join fetch list2.masterListLimitVal "
			+ "left join fetch list3.pkgVerBig3CO list4 "
			+ "left join fetch list3.treeShape list5 "
			+ "Where list5.treeShape1up = :treeShapeId "
			+ "And list4.pkgVerBig3CO1up = :pkgVerBig3CoId "
			+ "And list6.pkgVerBenefitOption1up = :pkgVerBenefitOptionId")
	public List<LimitShapeBenefitOptionOverride> findAllEnableLimitValForBenefitOption(
			@Param("pkgVerBig3CoId") int pkgVerBig3CoId,
			@Param("pkgVerBenefitOptionId") int pkgVerBenefitOptionId,
			@Param("treeShapeId") int treeShapeId);

	@Query(value = "SELECT benSel from BenefitOptionTierdata benSel"
			+ " left join fetch benSel.pkgVerInstanceTree tree"
			+ " left join fetch benSel.pkgVerBig3CO list1"
			+ " left join fetch list1.pkgMasterListForBig3CO list2"
			+ " left join fetch list2.masterListStringVal "
			+ " left join fetch list2.masterListServiceDefinition list3"
			+ " left join fetch list3.masterListStringValCat1 list4"
			+ " left join fetch list3.masterListStringValCat2"
			+ " left join fetch list3.masterListStringValCat3"
			+ " left join fetch list3.masterListStringValPostString"
			+ " left join fetch benSel.applyCoinsuranceShape"
			+ " left join fetch benSel.coinsuranceYesValShape"
			+ " left join fetch benSel.coinsuranceYesVal"
			+ " left join fetch benSel.coinsuranceNoValShape"
			+ " left join fetch benSel.coinsuranceNoVal"
			+ " left join fetch benSel.copayShape"
			+ " left join fetch benSel.copayVal"
			+ " left join fetch benSel.allowedAmtShape"
			+ " left join fetch benSel.allowedCtrShape"
			+ " left join fetch benSel.allowedAmt"
			+ " left join fetch benSel.allowedCtr"
			+ " left join fetch benSel.benefitOption"
			+ " where benSel.pkgVerInstanceTree.pkgVerInstanceTree1up = :versionInstance"
			+ " and benSel.benefitOption.pkgVerBenefitOption1up = :benefitOptionId"
			+ " and list4.stringValue = 'Default Values'")
	public BenefitOptionTierdata findDefaultBenefitSelectionForBenefitOption(
			@Param("versionInstance") int versionInstance,
			@Param("benefitOptionId") int benefitOptionId);

	@Query(value = "SELECT list from PkgConfigOptionBenefitOptionXref list"
			+ " left join fetch list.pkgVerBenefitOption "
			+ " left join fetch list.pkgConfigOption"
			+ " where list.pkgConfigOption.pkgConfigOption1up = :productId")
	public List<PkgConfigOptionBenefitOptionXref> findBenefitOptionsForAProductByProductId(
			@Param("productId") int productId);

	@Query(value = "Select list from PkgVerBig3CO list "
			+ "left join fetch list.pkgVersion "
			+ "left join fetch list.pkgMasterListForBig3CO list1 "
			+ "left join fetch list1.masterListServiceDefinition "
			+ "left join fetch list1.masterListStringVal "
			+ "left join fetch list.pkgVerInstanceTree "
			+ "where list.pkgVersion.pkgVersion1up = :pkgVersionId")
	public List<PkgVerBig3CO> findAllServicetypeforPkgVersion(
			@Param("pkgVersionId") int pkgVersionId);

	@Query(value = "SELECT list from PkgMasterListForBig3CO list "
			+ "left join fetch list.benefitPackage "
			+ "left join fetch list.masterListServiceDefinition "
			+ "left join fetch list.masterListStringVal "
			+ "where list.benefitPackage.benefitPackage1up = :pkgId")
	public List<PkgMasterListForBig3CO> findAllServiceTypeForPkg(
			@Param("pkgId") int pkgId);

	@Query(value = "SELECT list FROM BenefitOptionBig3CO list"
			+ " left join fetch list.pkgVerBenefitOption"
			+ " left join fetch list.pkgVerBig3CO list2"
			+ " left join fetch list2.pkgMasterListForBig3CO list3"
			+ " left join fetch list3.masterListServiceDefinition list4 "
			+ " left join fetch list4.masterListStringValCat1 list5 "
			+ " where list.pkgVerBenefitOption.pkgVerBenefitOption1up in :benefitOptionIds"
			+ " AND list5.stringValue <> 'Default Values'")
	public List<BenefitOptionBig3CO> findListOfPkgVerBenefitOptionBig3CosById(
			@Param("benefitOptionIds") List<Integer> benefitOptionIds);

	@Query(value = "SELECT list FROM PkgConfigOptionBenefitOptionXref list"
			+ " left join fetch list.pkgConfigOption"
			+ " left join fetch list.pkgVerBenefitOption list1"
			+ " left join fetch list1.pkgVerBenOptionBig3CO"
			+ " where list.pkgVerBenefitOption.pkgVerBenefitOption1up = :benefitOptionId")
	public List<PkgConfigOptionBenefitOptionXref> findBenefitOfferingByBenefitOptionId(
			@Param("benefitOptionId") int benefitOptionId);

}
