package com.tmg.ebscore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tmg.ebscore.orm.ebspackage.AccountPkgConfigOptionXref;
import com.tmg.ebscore.orm.ebspackage.AdminContentDesign;
import com.tmg.ebscore.orm.ebspackage.BenefitPackage;
import com.tmg.ebscore.orm.ebspackage.ExcelViewInfo;
import com.tmg.ebscore.orm.ebspackage.LimitShapeBenefitOptionOverride;
import com.tmg.ebscore.orm.ebspackage.MatrixShapeBig3COLimitXXref;
import com.tmg.ebscore.orm.ebspackage.MatrixShapeBig3COMessageXXref;
import com.tmg.ebscore.orm.ebspackage.MatrixShapeBig3COXref;
import com.tmg.ebscore.orm.ebspackage.MessageShapeBenefitOptionOverride;
import com.tmg.ebscore.orm.ebspackage.PkgConfigOption;
import com.tmg.ebscore.orm.ebspackage.PkgConfigOptionBenefitOptionXref;
import com.tmg.ebscore.orm.ebspackage.PkgConfigOptionFamily;
import com.tmg.ebscore.orm.ebspackage.PkgMasterListForBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBenefitOptionForBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueTreeInstance;
import com.tmg.ebscore.orm.ebspackage.UIElement;
import com.tmg.ebscore.orm.packageversion.PkgVerBig3CO;

public interface PackageRepository extends JpaRepository<BenefitPackage, Long>,
		PackageCustomRepository {

	@Modifying
	@Query(value = "DELETE FROM PkgMasterListForBig3CO mbob3co  WHERE "
			+ "mbob3co.benefitPackage.benefitPackage1up  = :id")
	public void deleteFromPkgMasterListForBig3CO(@Param("id") int id);

	@Query(value = "SELECT count(version) from PkgVersion version "
			+ "left join version.benefitPackage "
			+ "where version.benefitPackage.benefitPackage1up = :id")
	public int findNoOfPackageVersionsById(@Param("id") int id);

	@Query(value = "SELECT packageList from BenefitPackage packageList"
			+ " left join fetch packageList.packageTemplate", countQuery = "SELECT count(packageList) from BenefitPackage packageList"
			+ " left join packageList.packageTemplate")
	public Page<BenefitPackage> findPackageList(Pageable pageable);

	// ********** Find By Id Methods ************** //

	@Query(value = "SELECT list from AdminContentDesign list"
			+ " left join fetch list.packageTemplate"
			+ " Where list.adminContentDesign1up = :id")
	public AdminContentDesign findAdminContentDesignById(@Param("id") int id);

	@Query(value = "SELECT list from BenefitPackage list "
			+ "left join fetch  list.packageTemplate "
			+ "where list.benefitPackage1up=:id")
	public BenefitPackage findBenefitPackageById(@Param("id") int id);

	@Query(value = "SELECT list from ExcelViewInfo list "
			+ "left join fetch  list.treeShape "
			+ "left join fetch  list.pkgVerInstanceTree "
			+ "left join fetch  list.packageTemplate "
			+ "left join fetch  list.masterList "
			+ "left join fetch  list.parentColumn "
			+ "where list.column1up=:id")
	public ExcelViewInfo findExcelViewInfoById(@Param("id") int id);

	@Query(value = "SELECT list from LimitShapeBenefitOptionOverride list "
			// +
			// "left join fetch list.limitShapeBenefitOptionOverrideId.matrixShapeBig3COLimitXXrefId "
			// +
			// "left join fetch list.limitShapeBenefitOptionOverrideId.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.treeShape "
			// +
			// "left join fetch list.limitShapeBenefitOptionOverrideId.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO "
			// +
			// "left join fetch list.limitShapeBenefitOptionOverrideId.pkgVerBenefitOption "
			// +
			// "left join fetch list.limitShapeBenefitOptionOverrideId.matrixShapeBig3COLimitXXrefId.masterListLimitVal "
			+ "WHERE list.limitShapeBenefitOptionOverrideId.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.treeShape.treeShape1up = :treeShape1up "
			+ "AND list.limitShapeBenefitOptionOverrideId.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVerBig3CO1up "
			+ "AND list.limitShapeBenefitOptionOverrideId.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVerBenefitOption1up "
			+ "AND list.limitShapeBenefitOptionOverrideId.matrixShapeBig3COLimitXXrefId.masterListLimitVal.masterListLimitVal1up = :masterListLimitVal1up")
	public LimitShapeBenefitOptionOverride findLimitShapeBenefitOptionOverrideById(
			@Param("treeShape1up") int treeShape1up,
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up,
			@Param("pkgVerBenefitOption1up") int pkgVerBenefitOption1up,
			@Param("masterListLimitVal1up") int masterListLimitVal1up);

	@Query(value = "SELECT list from MatrixShapeBig3COLimitXXref list "
			/*
			 * + "left join fetch  list.matrixShapeBig3COLimitXXrefId " +
			 * "left join fetch  list.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId "
			 * +
			 * "left join fetch  list.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.treeShape "
			 * +
			 * "left join fetch  list.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO "
			 * +
			 * "left join fetch  list.matrixShapeBig3COLimitXXrefId.masterListLimitVal "
			 */
			+ "WHERE list.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.treeShape.treeShape1up = :treeShape1up "
			+ "AND list.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVerBig3CO1up "
			+ "AND list.matrixShapeBig3COLimitXXrefId.masterListLimitVal.masterListLimitVal1up = :masterListLimitVal1up")
	public MatrixShapeBig3COLimitXXref findMatrixShapeBig3COLimitXXrefById(
			@Param("treeShape1up") int treeShape1up,
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up,
			@Param("masterListLimitVal1up") int masterListLimitVal1up);

	@Query(value = "SELECT list from MatrixShapeBig3COMessageXXref list "
			+ "left join fetch  list.matrixShapeBig3COMessageXXrefId "
			+ "left join fetch  list.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.treeShape "
			+ "left join fetch  list.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO "
			+ "left join fetch  list.matrixShapeBig3COMessageXXrefId.masterListMessageVal "
			+ "WHERE list.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.treeShape.treeShape1up = :treeShape1up "
			+ "AND list.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVerBig3CO1up "
			+ "AND list.matrixShapeBig3COMessageXXrefId.masterListMessageVal.masterListMessageVal1up = :masterListMessageVal1up")
	public MatrixShapeBig3COMessageXXref findMatrixShapeBig3COMessageXXrefById(
			@Param("treeShape1up") int treeShape1up,
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up,
			@Param("masterListMessageVal1up") int masterListMessageVal1up);

	@Query(value = "SELECT list from MatrixShapeBig3COXref list "
			+ "left join fetch  list.matrixShapeBig3COXrefId "
			+ "left join fetch  list.matrixShapeBig3COXrefId.treeShape "
			+ "left join fetch  list.matrixShapeBig3COXrefId.pkgVerBig3CO list2 "
			+ "WHERE list.matrixShapeBig3COXrefId.treeShape.treeShape1up = :treeShape1up "
			+ "AND list.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVerBig3CO1up")
	public MatrixShapeBig3COXref findMatrixShapeBig3COXrefById(
			@Param("treeShape1up") int treeShape1up,
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Query(value = "SELECT list from MessageShapeBenefitOptionOverride list "
			// +
			// "left join fetch  list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId "
			// +
			// "left join fetch  list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.treeShape "
			// +
			// "left join fetch  list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO "
			// +
			// "left join fetch  list.messageShapeBenefitOptionOverrideId.pkgVerBenefitOption "
			// +
			// "left join fetch  list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.masterListMessageVal "
			+ "WHERE list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.treeShape.treeShape1up = :treeShape1up "
			+ "AND list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVerBig3CO1up "
			+ "AND list.messageShapeBenefitOptionOverrideId.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVerBenefitOption1up "
			+ "AND list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.masterListMessageVal.masterListMessageVal1up = :masterListMessageVal1up")
	public MessageShapeBenefitOptionOverride findMessageShapeBenefitOptionOverrideById(
			@Param("treeShape1up") int treeShape1up,
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up,
			@Param("pkgVerBenefitOption1up") int pkgVerBenefitOption1up,
			@Param("masterListMessageVal1up") int masterListMessageVal1up);

	@Query(value = "SELECT list from PkgConfigOption list "
			+ "left join fetch  list.pkgConfigOptionFamily "
			+ "left join fetch  list.pkgVersion list1 "
			+ "left join fetch  list1.pkgVerTree "
			+ "left join fetch  list.pkgVersionWorkflowState "
			+ "left join fetch  list.masterListStringVal "
			+ "where list.pkgConfigOption1up = :id")
	public PkgConfigOption findPkgConfigOptionById(@Param("id") int id);

	@Query(value = "SELECT list from PkgConfigOptionBenefitOptionXref list "
			+ "left join fetch  list.pkgConfigOption "
			+ "left join fetch  list.pkgVerBenefitOption "
			+ "where list.pkgConfigOptionBenefitOptionXref1up=:id")
	public PkgConfigOptionBenefitOptionXref findPkgConfigOptionBenefitOptionXrefById(
			@Param("id") int id);

	@Query(value = "SELECT list from PkgConfigOptionFamily list "
			+ "left join fetch  list.benefitPackage "
			+ "where list.pkgConfigOptionFamily1up = :id")
	public PkgConfigOptionFamily findPkgConfigOptionFamilyById(
			@Param("id") int id);

	@Query(value = "SELECT list from PkgMasterListForBig3CO list "
			+ "left join fetch  list.benefitPackage "
			+ "left join fetch  list.masterListServiceDefinition "
			+ "left join fetch  list.masterListStringVal "
			+ "where list.pkgMasterListForBig3CO1up = :id")
	public PkgMasterListForBig3CO findPkgMasterListForBig3COById(
			@Param("id") int id);

	@Query(value = "SELECT list from ShapeValueBenefitOptionForBig3CO list "
			+ "left join fetch  list.masterListIntVal "
			+ "left join fetch  list.masterListStringVal "
			+ "left join fetch  list.shapeValueBenefitOptionForBig3COId "
			+ "left join fetch  list.shapeValueBenefitOptionForBig3COId.treeShape "
			+ "left join fetch  list.shapeValueBenefitOptionForBig3COId.pkgVerInstanceTree "
			+ "left join fetch  list.shapeValueBenefitOptionForBig3COId.pkgVerBig3CO "
			+ "left join fetch  list.shapeValueBenefitOptionForBig3COId.pkgVerBenefitOption "
			+ "WHERE list.shapeValueBenefitOptionForBig3COId.treeShape.treeShape1up = :treeShape1up "
			+ "AND list.shapeValueBenefitOptionForBig3COId.pkgVerInstanceTree.pkgVerInstanceTree1up = :pkgVerInstanceTree1up "
			+ "AND list.shapeValueBenefitOptionForBig3COId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVerBig3CO1up "
			+ "AND list.shapeValueBenefitOptionForBig3COId.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVerBenefitOption1up")
	public ShapeValueBenefitOptionForBig3CO findShapeValueBenefitOptionForBig3COById(
			@Param("treeShape1up") int treeShape1up,
			@Param("pkgVerInstanceTree1up") int pkgVerInstanceTree1up,
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up,
			@Param("pkgVerBenefitOption1up") int pkgVerBenefitOption1up);

	@Query(value = "SELECT list from ShapeValueBig3CO list "
			+ "left join fetch  list.shapeValueBig3COId "
			+ "left join fetch  list.masterListIntVal "
			+ "left join fetch  list.masterListStringVal "
			+ "left join fetch  list.shapeValueBig3COId.treeShape "
			+ "left join fetch  list.shapeValueBig3COId.pkgVerInstanceTree "
			+ "left join fetch  list.shapeValueBig3COId.pkgVerBig3CO "
			+ "WHERE list.shapeValueBig3COId.treeShape.treeShape1up = :treeShape1up "
			+ "AND list.shapeValueBig3COId.pkgVerInstanceTree.pkgVerInstanceTree1up = :pkgVerInstanceTree1up "
			+ "AND list.shapeValueBig3COId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVerBig3CO1up")
	public ShapeValueBig3CO findShapeValueBig3COById(
			@Param("treeShape1up") int treeShape1up,
			@Param("pkgVerInstanceTree1up") int pkgVerInstanceTree1up,
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Query(value = "SELECT list from ShapeValueTreeInstance list "
			+ "left join fetch  list.shapeValueTreeInstanceId "
			+ "left join fetch  list.masterListIntVal "
			+ "left join fetch  list.masterListStringVal "
			+ "left join fetch  list.shapeValueTreeInstanceId.treeShape "
			+ "left join fetch  list.shapeValueTreeInstanceId.pkgVerInstanceTree "
			+ "WHERE list.shapeValueTreeInstanceId.treeShape.treeShape1up = :treeShape1up "
			+ "AND list.shapeValueTreeInstanceId.pkgVerInstanceTree.pkgVerInstanceTree1up = :pkgVerInstanceTree1up")
	public ShapeValueTreeInstance findShapeValueTreeInstanceById(
			@Param("treeShape1up") int treeShape1up,
			@Param("pkgVerInstanceTree1up") int pkgVerInstanceTree1up);

	@Query(value = "SELECT list from UIElement list "
			+ "left join fetch  list.uIElement "
			+ "left join fetch  list.adminContentDesign "
			+ "where list.uIElement1up = :id")
	public UIElement findUIElementById(@Param("id") int id);

	// ********** End ************** //

	// ********** Delete By Id Methods ************** //

	@Modifying
	@Query(value = "delete from AdminContentDesign list"
			+ " Where list.adminContentDesign1up = :id")
	public void deleteAdminContentDesignById(@Param("id") int id);

	@Modifying
	@Query(value = "delete from BenefitPackage list "
			+ "where list.benefitPackage1up=:id")
	public void deleteBenefitPackageById(@Param("id") int id);

	@Modifying
	@Query(value = "delete from ExcelViewInfo list "
			+ "where list.column1up=:id")
	public void deleteExcelViewInfoById(@Param("id") int id);

	@Modifying
	@Query(value = "delete from LimitShapeBenefitOptionOverride list "
			+ "WHERE list.limitShapeBenefitOptionOverrideId.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.treeShape.treeShape1up = :treeShape1up "
			+ "AND list.limitShapeBenefitOptionOverrideId.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVerBig3CO1up "
			+ "AND list.limitShapeBenefitOptionOverrideId.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVerBenefitOption1up "
			+ "AND list.limitShapeBenefitOptionOverrideId.matrixShapeBig3COLimitXXrefId.masterListLimitVal.masterListLimitVal1up = :masterListLimitVal1up")
	public void deleteLimitShapeBenefitOptionOverrideById(
			@Param("treeShape1up") int treeShape1up,
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up,
			@Param("pkgVerBenefitOption1up") int pkgVerBenefitOption1up,
			@Param("masterListLimitVal1up") int masterListLimitVal1up);

	@Modifying
	@Query(value = "delete from MatrixShapeBig3COLimitXXref list "
			+ "WHERE list.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.treeShape.treeShape1up = :treeShape1up "
			+ "AND list.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVerBig3CO1up "
			+ "AND list.matrixShapeBig3COLimitXXrefId.masterListLimitVal.masterListLimitVal1up = :masterListLimitVal1up")
	public void deleteMatrixShapeBig3COLimitXXrefById(
			@Param("treeShape1up") int treeShape1up,
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up,
			@Param("masterListLimitVal1up") int masterListLimitVal1up);

	@Modifying
	@Query(value = "delete from MatrixShapeBig3COMessageXXref list "
			+ "WHERE list.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.treeShape.treeShape1up = :treeShape1up "
			+ "AND list.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVerBig3CO1up "
			+ "AND list.matrixShapeBig3COMessageXXrefId.masterListMessageVal.masterListMessageVal1up = :masterListMessageVal1up")
	public void deleteMatrixShapeBig3COMessageXXrefById(
			@Param("treeShape1up") int treeShape1up,
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up,
			@Param("masterListMessageVal1up") int masterListMessageVal1up);

	@Modifying
	@Query(value = "delete from MatrixShapeBig3COXref list "
			+ "WHERE list.matrixShapeBig3COXrefId.treeShape.treeShape1up = :treeShape1up "
			+ "AND list.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVerBig3CO1up")
	public void deleteMatrixShapeBig3COXrefById(
			@Param("treeShape1up") int treeShape1up,
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "delete from MessageShapeBenefitOptionOverride list "
			+ "WHERE list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.treeShape.treeShape1up = :treeShape1up "
			+ "AND list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVerBig3CO1up "
			+ "AND list.messageShapeBenefitOptionOverrideId.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVerBenefitOption1up "
			+ "AND list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.masterListMessageVal.masterListMessageVal1up = :masterListMessageVal1up")
	public void deleteMessageShapeBenefitOptionOverrideById(
			@Param("treeShape1up") int treeShape1up,
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up,
			@Param("pkgVerBenefitOption1up") int pkgVerBenefitOption1up,
			@Param("masterListMessageVal1up") int masterListMessageVal1up);

	@Modifying
	@Query(value = "delete from PkgConfigOption list "
			+ "where list.pkgConfigOption1up = :id")
	public void deletePkgConfigOptionById(@Param("id") int id);

	@Modifying
	@Query(value = "delete from PkgConfigOptionBenefitOptionXref list "
			+ "where list.pkgConfigOptionBenefitOptionXref1up=:id")
	public void deletePkgConfigOptionBenefitOptionXrefById(@Param("id") int id);

	@Modifying
	@Query(value = "delete from PkgConfigOptionFamily list "
			+ "where list.pkgConfigOptionFamily1up = :id")
	public void deletePkgConfigOptionFamilyById(@Param("id") int id);

	@Modifying
	@Query(value = "delete from PkgMasterListForBig3CO list "
			+ "where list.pkgMasterListForBig3CO1up = :id")
	public void deletePkgMasterListForBig3COById(@Param("id") int id);

	@Modifying
	@Query(value = "delete from ShapeValueBenefitOptionForBig3CO list "
			+ "WHERE list.shapeValueBenefitOptionForBig3COId.treeShape.treeShape1up = :treeShape1up "
			+ "AND list.shapeValueBenefitOptionForBig3COId.pkgVerInstanceTree.pkgVerInstanceTree1up = :pkgVerInstanceTree1up "
			+ "AND list.shapeValueBenefitOptionForBig3COId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVerBig3CO1up "
			+ "AND list.shapeValueBenefitOptionForBig3COId.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVerBenefitOption1up")
	public void deleteShapeValueBenefitOptionForBig3COById(
			@Param("treeShape1up") int treeShape1up,
			@Param("pkgVerInstanceTree1up") int pkgVerInstanceTree1up,
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up,
			@Param("pkgVerBenefitOption1up") int pkgVerBenefitOption1up);

	@Modifying
	@Query(value = "delete from ShapeValueBig3CO list "
			+ "WHERE list.shapeValueBig3COId.treeShape.treeShape1up = :treeShape1up "
			+ "AND list.shapeValueBig3COId.pkgVerInstanceTree.pkgVerInstanceTree1up = :pkgVerInstanceTree1up "
			+ "AND list.shapeValueBig3COId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVerBig3CO1up")
	public void deleteShapeValueBig3COById(
			@Param("treeShape1up") int treeShape1up,
			@Param("pkgVerInstanceTree1up") int pkgVerInstanceTree1up,
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "delete from ShapeValueTreeInstance list "
			+ "WHERE list.shapeValueTreeInstanceId.treeShape.treeShape1up = :treeShape1up "
			+ "AND list.shapeValueTreeInstanceId.pkgVerInstanceTree.pkgVerInstanceTree1up = :pkgVerInstanceTree1up")
	public void deleteShapeValueTreeInstanceById(
			@Param("treeShape1up") int treeShape1up,
			@Param("pkgVerInstanceTree1up") int pkgVerInstanceTree1up);

	@Modifying
	@Query(value = "delete from UIElement list "
			+ "where list.uIElement1up = :id")
	public void deleteUIElementById(@Param("id") int id);

	@Query(value = "SELECT list from BenefitPackage list "
			+ "left join fetch list.benPkgMasterList "
			+ "where list.benefitPackage1up = :id")
	public BenefitPackage findAllPkgMasterListForBig3COByPackageId(
			@Param("id") int id);

	@Query(value = "SELECT list from PkgMasterListForBig3CO list "
			+ "left join fetch list.pkgBigCoI "
			+ "where list.pkgMasterListForBig3CO1up = :id")
	public PkgMasterListForBig3CO findAllPkgVerBig3COByPkgMasterListForBig3COId(
			@Param("id") int id);

	@Query(value = "SELECT list from PkgVerBig3CO list "
			+ "left join fetch list.pkgVerBenOption3Co"
			+ " where list.pkgVerBig3CO1up = :id")
	public PkgVerBig3CO findAllBenefitOptionBig3COBypkgVerBig3COID(
			@Param("id") int id);

	@Query(value = "SELECT list from PkgVerBig3CO list "
			+ "left join fetch list.treeShapeBigLimitXref"
			+ " where list.pkgVerBig3CO1up = :id")
	public PkgVerBig3CO findAllMatrixShapeBig3COXrefBypkgVerBig3COID(
			@Param("id") int id);

	@Query(value = "SELECT list from PkgMasterListForBig3CO list "
			+ "left join fetch  list.benefitPackage "
			+ "left join fetch  list.masterListServiceDefinition list1 "
			+ "left join fetch  list1.masterListStringValCat1 "
			+ "left join fetch  list1.masterListStringValCat2 "
			+ "left join fetch  list1.masterListStringValCat3 "
			+ "left join fetch  list1.masterListStringValPostString "
			+ "left join fetch  list.masterListStringVal "
			+ "where list.benefitPackage.benefitPackage1up = :pkgId", countQuery = "SELECT count(list) from PkgMasterListForBig3CO list "
			+ "where list.benefitPackage.benefitPackage1up = :pkgId")
	public Page<PkgMasterListForBig3CO> findPkgMasterListForBig3COByPkgId(
			@Param("pkgId") int pkgId, Pageable pageable);

	// @Query(value = "DELETE from PkgNotice")
	// public void deletePkgNoticeByPackageId();

	@Query(value = "SELECT list from BenefitPackage list "
			+ "left join fetch list.benPkgMasterList list1 "
			+ "left join fetch list1.pkgBigCoI "
			+ "left join fetch list.pkgBenefitPkg "
			+ "left join fetch list.benPkgFamily list1 "
			+ "left join fetch list1.configOptionFamily "
			+ "where list.benefitPackage1up = :packageId")
	public BenefitPackage findBenefitPackageSetById(
			@Param("packageId") int packageId);

	@Modifying
	@Query(value = "DELETE FROM PkgConfigOptionBenefitOptionXref pcbox "
			+ "WHERE pcbox.pkgConfigOption.pkgConfigOption1up =:PkgConfigOptionId")
	public void deletePkgConfigOptionBenefitOptionXrefByPkgConfigOptionId(
			@Param("PkgConfigOptionId") int PkgConfigOptionId);

	@Modifying
	@Query(value = "DELETE FROM PkgVersionProductXref pcbox "
			+ "WHERE pcbox.pkgVersionProductXrefId.pkgConfigOption.pkgConfigOption1up =:PkgConfigOptionId")
	public void deletePkgVersionProductXrefByPkgConfigOptionId(
			@Param("PkgConfigOptionId") int PkgConfigOptionId);

	@Modifying
	@Query(value = "DELETE FROM PkgConfigOption pcbox "
			+ "WHERE pcbox.pkgConfigOptionFamily.pkgConfigOptionFamily1up =:PkgConfigOptionFamilyId")
	public void deletePkgConfigOptionByPkgConfigOptionFamilyId(
			@Param("PkgConfigOptionFamilyId") int PkgConfigOptionFamilyId);

	@Modifying
	@Query(value = "DELETE FROM PkgConfigOptionFamily pcbox "
			+ "WHERE pcbox.benefitPackage.benefitPackage1up =:packageId")
	public void deletePkgConfigOptionFamilyByBenefitPackageId(
			@Param("packageId") int packageId);

	@Modifying
	@Query(value = "DELETE FROM BenefitOptionBig3CO list "
			+ "WHERE list.pkgVerBig3CO.pkgVerBig3CO1up =:pkgVerBig3CO1up")
	public void deleteBenefitOptionBig3COByPkgVerBig3COId(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "DELETE FROM ShapeValueBenefitOptionForBig3CO list "
			+ "WHERE list.shapeValueBenefitOptionForBig3COId.pkgVerBig3CO.pkgVerBig3CO1up =:pkgVerBig3CO1up")
	public void deleteShapeValueBenefitOptionForBig3COByPkgVerBig3COId(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "DELETE FROM ShapeValueBig3CO list "
			+ "WHERE list.shapeValueBig3COId.pkgVerBig3CO.pkgVerBig3CO1up =:pkgVerBig3CO1up")
	public void deleteShapeValueBig3COByPkgVerBig3COId(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "DELETE FROM LimitShapeBenefitOptionOverride list "
			+ "WHERE list.limitShapeBenefitOptionOverrideId.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up =:pkgVerBig3CO1up")
	public void deleteLimitShapeBenefitOptionOverrideByPkgVerBig3COId(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "DELETE FROM MessageShapeBenefitOptionOverride list "
			+ "WHERE list.messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up =:pkgVerBig3CO1up")
	public void deleteMessageShapeBenefitOptionOverrideByPkgVerBig3COId(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "DELETE FROM MatrixShapeBig3COMessageXXref list "
			+ "WHERE list.matrixShapeBig3COMessageXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up =:pkgVerBig3CO1up")
	public void deleteMatrixShapeBig3COMessageXXrefByPkgVerBig3COId(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "DELETE FROM MatrixShapeBig3COLimitXXref list "
			+ "WHERE list.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up =:pkgVerBig3CO1up")
	public void deleteMatrixShapeBig3COLimitXXrefByPkgVerBig3COId(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "DELETE FROM MatrixShapeBig3COXref list "
			+ "WHERE list.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up =:pkgVerBig3CO1up")
	public void deleteMatrixShapeBig3COXrefByPkgVerBig3COId(
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Modifying
	@Query(value = "DELETE FROM PkgVerBig3CO list "
			+ "WHERE list.pkgMasterListForBig3CO.pkgMasterListForBig3CO1up =:pkgMasterListForBig3CO1up")
	public void deletePkgVerBig3COBypkgMasterListForBig3COId(
			@Param("pkgMasterListForBig3CO1up") int pkgMasterListForBig3CO1up);

	@Modifying
	@Query(value = "DELETE FROM PkgMasterListForBig3CO list "
			+ "WHERE list.benefitPackage.benefitPackage1up =:benefitPackage1up")
	public void deletepkgMasterListForBig3COByBenefitPackageId(
			@Param("benefitPackage1up") int benefitPackage1up);

	@Query(value = "Select list FROM AccountPkgConfigOptionXref list "
			+ "left join fetch list.pkgConfigOption")
	public List<AccountPkgConfigOptionXref> findAllAccountPkgConfigOptionXref();

	@Query(value = "SELECT list from PkgConfigOption list "
			+ "left join fetch  list.pkgConfigOptionFamily "
			+ "left join fetch  list.pkgVersionWorkflowState "
			+ "left join fetch  list.masterListStringVal "
			+ "left join fetch  list.pkgVersion "
			+ "where list.pkgVersion.pkgVersion1up = :id", countQuery = "SELECT count(list) from PkgConfigOption list "
			+ "where list.pkgVersion.pkgVersion1up = :id")
	public Page<PkgConfigOption> findAllPkgConfigOptionByPkgVerId(
			@Param("id") int id, Pageable Page);

	@Query(value = "SELECT list from PkgConfigOptionBenefitOptionXref list "
			+ "left join fetch  list.pkgConfigOption "
			+ "left join fetch  list.pkgVerBenefitOption "
			+ "where list.pkgConfigOption.pkgConfigOption1up in :listConfig")
	public List<PkgConfigOptionBenefitOptionXref> findAllPkgConfigOptionBenefitOptionXrefBypkgConfigOptionId(
			@Param("listConfig") List<Integer> listConfig);

	@Query(value = "SELECT list from PkgConfigOptionBenefitOptionXref list "
			+ "left join fetch  list.pkgConfigOption "
			+ "left join fetch  list.pkgVerBenefitOption "
			+ "where list.pkgConfigOption.pkgConfigOption1up =:pkgConfigOption1up")
	public List<PkgConfigOptionBenefitOptionXref> findPkgConfigOptionBenefitOptionXrefByConfigId(
			@Param("pkgConfigOption1up") int pkgConfigOption1up);

	@Modifying
	@Query("DELETE from BenefitOptionBig3CO list"
			+ " where list.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVerBenefitOption1up")
	public void deleteBenefitOptionBig3CoByConfigId(
			@Param("pkgVerBenefitOption1up") int pkgVerBenefitOption1up);

	@Modifying
	@Query("DELETE from PkgConfigOptionBenefitOptionXref list"
			+ " where list.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVerBenefitOption1up")
	public void deletePkgConfigOptionBenefitOptionXrefByConfigId(
			@Param("pkgVerBenefitOption1up") int pkgVerBenefitOption1up);

	@Modifying
	@Query("DELETE from ShapeValueBenefitOptionForBig3CO list"
			+ " where list.shapeValueBenefitOptionForBig3COId.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVerBenefitOption1up")
	public void deleteShapeValueBenefitOptionForBig3COByConfigId(
			@Param("pkgVerBenefitOption1up") int pkgVerBenefitOption1up);

	@Modifying
	@Query("DELETE from LimitShapeBenefitOptionOverride list"
			+ " where list.limitShapeBenefitOptionOverrideId.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVerBenefitOption1up")
	public void deleteLimitShapeBenefitOptionOverrideByConfigId(
			@Param("pkgVerBenefitOption1up") int pkgVerBenefitOption1up);

	@Modifying
	@Query("DELETE from MessageShapeBenefitOptionOverride list"
			+ " where list.messageShapeBenefitOptionOverrideId.pkgVerBenefitOption.pkgVerBenefitOption1up = :pkgVerBenefitOption1up")
	public void deleteMessageShapeBenefitOptionOverrideByConfigId(
			@Param("pkgVerBenefitOption1up") int pkgVerBenefitOption1up);

	@Query(value = "SELECT list from PkgMasterListForBig3CO list "
			+ "left join fetch list.benefitPackage "
			+ "left join fetch list.masterListServiceDefinition "
			+ "left join fetch list.masterListStringVal "
			+ "where list.benefitPackage.benefitPackage1up = :packageID")
	public List<PkgMasterListForBig3CO> findAllServiceTypeForAPackage(
			@Param("packageID") int packageID);

	// @Query(value =
	// "Select NEW com.tmg.ebscore.dto.ebspackage.Big3COData(list.pkgMasterListForBig3CO1up,count(list)) from PkgMasterListForBig3CO list "
	// + "left join list.benefitPackage "
	// + "left join list.pkgBigCoI "
	// +
	// "where list.benefitPackage.benefitPackage1up = :packageId group by list.pkgMasterListForBig3CO1up having count(list) > 0")
	// public List<Big3COData> findSetOfPkgVerBig3COUseAndUnUse(
	// @Param("packageId") int packageId);

	@Query(value = "Select list.pkgMasterListForBig3CO1up from PkgMasterListForBig3CO list "
			+ "left join list.pkgBigCoI pkgBigCoI "
			+ "where list.benefitPackage.benefitPackage1up = :packageId group by list.pkgMasterListForBig3CO1up having count(pkgBigCoI) > 0 ")
	public List<Integer> findSetOfPkgVerBig3COUseAndUnUse(
			@Param("packageId") int packageId);

	@Query(value = "SELECT list from MatrixShapeBig3COLimitXXref list " +
			"left join fetch list.matrixShapeBig3COLimitXXrefId.masterListLimitVal "
			+ "WHERE list.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.treeShape.treeShape1up = :treeShape1up "
			+ "AND list.matrixShapeBig3COLimitXXrefId.matrixShapeBig3COXrefId.pkgVerBig3CO.pkgVerBig3CO1up = :pkgVerBig3CO1up")
	public List<MatrixShapeBig3COLimitXXref> findAllEnableLimitValForPkgVer(
			@Param("treeShape1up") int treeShape1up,
			@Param("pkgVerBig3CO1up") int pkgVerBig3CO1up);

	@Query(value = "SELECT list from PkgConfigOptionFamily list "
			+ "left join fetch list.benefitPackage "
			+ "where list.benefitPackage.benefitPackage1up = :pkgId")
	public List<PkgConfigOptionFamily> findAllConfigOptionFamilyByPackageId(
			@Param("pkgId") int pkgId);

}
