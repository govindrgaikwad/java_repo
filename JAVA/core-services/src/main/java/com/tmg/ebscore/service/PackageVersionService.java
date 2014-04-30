package com.tmg.ebscore.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmg.ebscore.dto.OperationResult;
import com.tmg.ebscore.dto.ServiceOperationResult;
import com.tmg.ebscore.dto.masterlist.MasterListIntValData;
import com.tmg.ebscore.dto.masterlist.MasterListLimitValData;
import com.tmg.ebscore.dto.masterlist.MasterListMessageValData;
import com.tmg.ebscore.dto.masterlist.MasterListServiceDefinitionData;
import com.tmg.ebscore.dto.masterlist.MasterListStringValData;
import com.tmg.ebscore.dto.packageversion.BenefitOptionBig3COData;
import com.tmg.ebscore.dto.packageversion.BenefitSelectionTierdataData;
import com.tmg.ebscore.dto.packageversion.DeductibleData;
import com.tmg.ebscore.dto.packageversion.MatrixShapeBig3COLimitXXrefData;
import com.tmg.ebscore.dto.packageversion.MatrixShapeBig3COMessageXXrefData;
import com.tmg.ebscore.dto.packageversion.NetworkServiceData;
import com.tmg.ebscore.dto.packageversion.PackageVersionBenefitOptionData;
import com.tmg.ebscore.dto.packageversion.PackageVersionContainer;
import com.tmg.ebscore.dto.packageversion.PackageVersionData;
import com.tmg.ebscore.dto.packageversion.PackageVersionSummaryData;
import com.tmg.ebscore.dto.packageversion.PkgVerBig3COData;
import com.tmg.ebscore.dto.packageversion.PkgVerInstanceTreeData;
import com.tmg.ebscore.dto.packageversion.ProductServiceData;
import com.tmg.ebscore.dto.packageversion.ServiceData;
import com.tmg.ebscore.dto.packageversion.ShapeValueInstanceTreeData;
import com.tmg.ebscore.dto.packageversion.TierData;
import com.tmg.ebscore.orm.DataAccessException;
import com.tmg.ebscore.orm.ebspackage.AccountPkgConfigOptionXref;
import com.tmg.ebscore.orm.ebspackage.ExcelViewInfo;
import com.tmg.ebscore.orm.ebspackage.LimitShapeBenefitOptionOverride;
import com.tmg.ebscore.orm.ebspackage.MatrixShapeBig3COLimitXXref;
import com.tmg.ebscore.orm.ebspackage.MatrixShapeBig3COMessageXXref;
import com.tmg.ebscore.orm.ebspackage.MatrixShapeBig3COMessageXXrefId;
import com.tmg.ebscore.orm.ebspackage.MatrixShapeBig3COXrefId;
import com.tmg.ebscore.orm.ebspackage.MessageShapeBenefitOptionOverride;
import com.tmg.ebscore.orm.ebspackage.MessageShapeBenefitOptionOverrideId;
import com.tmg.ebscore.orm.ebspackage.PkgConfigOption;
import com.tmg.ebscore.orm.ebspackage.PkgConfigOptionBenefitOptionXref;
import com.tmg.ebscore.orm.ebspackage.PkgMasterListForBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBenefitOptionForBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBenefitOptionForBig3COId;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBig3CO;
import com.tmg.ebscore.orm.ebspackage.ShapeValueBig3COId;
import com.tmg.ebscore.orm.ebspackage.ShapeValueTreeInstance;
import com.tmg.ebscore.orm.ebspackage.ShapeValueTreeInstanceId;
import com.tmg.ebscore.orm.masterlist.MasterListStringVal;
import com.tmg.ebscore.orm.packageversion.BenefitOptionBig3CO;
import com.tmg.ebscore.orm.packageversion.BenefitOptionTierdata;
import com.tmg.ebscore.orm.packageversion.BenefitSelectionTierdata;
import com.tmg.ebscore.orm.packageversion.PkgVerBenefitOption;
import com.tmg.ebscore.orm.packageversion.PkgVerBig3CO;
import com.tmg.ebscore.orm.packageversion.PkgVerInstanceTree;
import com.tmg.ebscore.orm.packageversion.PkgVersion;
import com.tmg.ebscore.orm.template.MatrixShapeLimitXref;
import com.tmg.ebscore.orm.template.MatrixShapeMessageXref;
import com.tmg.ebscore.repository.MasterListRepository;
import com.tmg.ebscore.repository.PackageRepository;
import com.tmg.ebscore.repository.PackageVersionRepository;
import com.tmg.ebscore.repository.TemplateRepository;

@Service("packageVersionService")
public class PackageVersionService {

	@Autowired
	private PackageVersionRepository packageVersionRepository;

	@Autowired
	private PackageRepository packageRepository;

	@Autowired
	private MasterListRepository masterListRepository;

	@Autowired
	private TemplateRepository templateRepository;

	/**
	 * @param packageVersionData
	 * @return
	 */
	@Transactional
	public ServiceOperationResult saveUpdatePackageVersion(
			PackageVersionData packageVersionData) {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		try {

			PkgVersion pkgVersion = null;
			if (packageVersionData.getPkgVersion1up() != null
					&& packageVersionData.getPkgVersion1up() > 0) {
				pkgVersion = packageVersionRepository
						.findPackageVersionById(packageVersionData
								.getPkgVersion1up());
				pkgVersion.setUpdateBy(packageVersionData.getUpdateBy());
				pkgVersion.setUpdateDt(new Date());
			} else {
				pkgVersion = new PkgVersion();
				pkgVersion.setCreateBy(packageVersionData.getCreateBy());
				pkgVersion.setCreateDt(new Date());
			}
			if (packageVersionData.getBenifitPackage1up() != null) {
				pkgVersion.setBenefitPackage(packageRepository
						.findBenefitPackageById(packageVersionData
								.getBenifitPackage1up()));

			}
			pkgVersion.setVersionNumber(packageVersionData.getVersionID());
			pkgVersion.setName(packageVersionData.getVersionName());
			pkgVersion
					.seteBSInstance1up(packageVersionData.geteBSInstance1up());
			pkgVersion.setDescription(packageVersionData
					.getVersionDescription());
			if (packageVersionData.getWorkflowstate1up() != null) {
				pkgVersion.setPkgVersionWorkflowState(packageVersionRepository
						.findPkgVersionWorkflowStateById(packageVersionData
								.getWorkflowstate1up()));
			}

			if (packageVersionData.getPkgVersion1up() != null
					&& packageVersionData.getPkgVersion1up() > 0) {
				packageVersionRepository.updateData(pkgVersion);
			} else {
				packageVersionRepository.saveData(pkgVersion);
			}
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error saving value for PackageVersion : "
					+ packageVersionData.getVersionID());
		}
		return operationResult;
	}

	/**
	 * @param pkgId
	 *            Primary key of BenefitPackage Table Which is Foreign Key in
	 *            PackageVersion Table
	 * @param page
	 *            Corresponding Page Number
	 * @param maxResults
	 *            Maximum number of rows displayed in a page
	 * @return {@link PackageVersionContainer} It returns the list of all
	 *         PackageVersionData objects, PagesCount ,TotalItems.
	 *         <p>
	 *         This method gives List of PackageVersionData related to a
	 *         particular BenefitPackage
	 */
	@Transactional
	public PackageVersionContainer<PackageVersionData> getPkgVerByPkgId(
			int pkgId, int page, int maxResults) throws DataAccessException {

		Page<PkgVersion> pageVersionData = null;
		try {
			pageVersionData = packageVersionRepository
					.findPackageVersionByPkgId(pkgId, new PageRequest(page,
							maxResults));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<PackageVersionData> packageVersionDatas = new ArrayList<PackageVersionData>();

		for (PkgVersion pkgVersion : pageVersionData) {

			PackageVersionData packageVersionData = new PackageVersionData();

			packageVersionData.setVersionID(pkgVersion.getVersionNumber());
			packageVersionData.setVersionName(pkgVersion.getName());
			if (pkgVersion.getPkgVersionWorkflowState() != null)
				packageVersionData.setWorkflowstate1up(pkgVersion
						.getPkgVersionWorkflowState()
						.getPkgVersionWorkflowState1up());
			packageVersionData.setCreateBy(pkgVersion.getCreateBy());
			packageVersionData.setCreateDt(pkgVersion.getCreateDt());
			packageVersionData.setUpdateBy(pkgVersion.getUpdateBy());
			packageVersionData.setUpdateDt(pkgVersion.getUpdateDt());

			packageVersionDatas.add(packageVersionData);

		}

		PackageVersionContainer<PackageVersionData> result = new PackageVersionContainer<PackageVersionData>();
		result.setData(packageVersionDatas);
		result.setPagesCount(pageVersionData.getTotalPages());
		result.setTotalItems(pageVersionData.getTotalElements());

		return result;
	}

	/**
	 * @param pkgVersionId
	 *            primary key of PkgVersion table
	 * @return {@link PackageVersionContainer} It returns the list of all
	 *         PackageVersionData objects, PagesCount ,TotalItems.
	 * @see This method returns all benefit options by pkgVersionId
	 * 
	 */
	@Transactional(readOnly = true)
	public PackageVersionContainer<PackageVersionBenefitOptionData> getAllBeneitOptionById(
			int pkgVersionId, int page, int maxResults)
			throws DataAccessException {
		Page<PkgVerBenefitOption> pkgVerBenefitOptions = packageVersionRepository
				.findAllBeneitOptionById(pkgVersionId, new PageRequest(page,
						maxResults));

		List<Integer> benefitOptionInUseList = packageVersionRepository
				.findSetOfPkgConfigOptionBenefitOptionXrefUseAndUnUse(pkgVersionId);

		List<PackageVersionBenefitOptionData> packageVersionBenefitOptionDatas = new ArrayList<PackageVersionBenefitOptionData>();

		for (PkgVerBenefitOption pkgVerBenefitOption : pkgVerBenefitOptions) {
			PackageVersionBenefitOptionData data = new PackageVersionBenefitOptionData();

			if (benefitOptionInUseList != null) {
				data.setInUse(benefitOptionInUseList
						.contains(pkgVerBenefitOption
								.getPkgVerBenefitOption1up()));
			} else {
				data.setInUse(false);
			}
			System.out.println(benefitOptionInUseList
					.contains(pkgVerBenefitOption.getPkgVerBenefitOption1up()));

			data.setCreateBy(pkgVerBenefitOption.getCreateBy());
			data.setCreateDt(pkgVerBenefitOption.getCreateDt());
			data.setDescription(pkgVerBenefitOption.getDescription());
			data.seteBSInstance1up(pkgVerBenefitOption.geteBSInstance1up());

			data.setPkgVerBenefitOption1up(pkgVerBenefitOption
					.getPkgVerBenefitOption1up());
			if (pkgVerBenefitOption.getPkgVersion() != null) {
				data.setPkgVersion1up(pkgVerBenefitOption.getPkgVersion()
						.getPkgVersion1up());
			}

			data.setUpdateBy(pkgVerBenefitOption.getUpdateBy());
			data.setUpdateDt(pkgVerBenefitOption.getUpdateDt());
			data.setBenefitOptionName(pkgVerBenefitOption.getName());
			packageVersionBenefitOptionDatas.add(data);
		}

		PackageVersionContainer<PackageVersionBenefitOptionData> result = new PackageVersionContainer<PackageVersionBenefitOptionData>();
		result.setData(packageVersionBenefitOptionDatas);

		return result;
	}

	// public void createBeneitOption(List<Integer> )
	/**
	 * @param pkgVersion1up
	 *            Primary Key Of PkgVersion Table Which is Foreign Key in
	 *            PkgVerInstanceTree Table
	 * 
	 * @param page
	 *            Corresponding Page Number for Pagination
	 * @param maxResults
	 *            Maximum number of rows displayed in a page
	 * 
	 * @return {@link PackageVersionContainer} It returns the list of all
	 *         PkgVerInstanceTreeData objects , PagesCount, TotalItems.
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This Method will Return All Common Benefits Related to a
	 *             Package Version.
	 */
	@Transactional(readOnly = true)
	public PackageVersionContainer<PkgVerInstanceTreeData> getCommonBenefitForEachPackageVersion(
			int pkgVersion1up, int page, int maxResults)
			throws DataAccessException {

		Page<PkgVerInstanceTree> instanceTreeList = null;
		try {
			instanceTreeList = packageVersionRepository
					.findCommonBenefitForEachPackageVersion(pkgVersion1up,
							new PageRequest(page, maxResults, new Sort(
									Sort.Direction.ASC, "name")));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<PkgVerInstanceTreeData> instanceTreeDataList = new ArrayList<PkgVerInstanceTreeData>();

		for (PkgVerInstanceTree instanceTree : instanceTreeList) {

			PkgVerInstanceTreeData instanceTreeData = new PkgVerInstanceTreeData();

			instanceTreeData.setPkgVerInstanceTree1up(instanceTree
					.getPkgVerInstanceTree1up());

			if (instanceTree.getPkgVersion().getPkgVersion1up() != null) {
				instanceTreeData.setPkgVersion1up(instanceTree.getPkgVersion()
						.getPkgVersion1up());
			}

			if (instanceTree.getTemplateTree() != null) {
				instanceTreeData.setTemplateTree1up(instanceTree
						.getTemplateTree().getTemplateTree1up());
			}

			instanceTreeData.setName(instanceTree.getName());
			instanceTreeData.setDescription(instanceTree.getDescription());

			instanceTreeData
					.seteBSInstance1up(instanceTree.geteBSInstance1up());
			instanceTreeData.setCreateBy(instanceTree.getCreateBy());
			instanceTreeData.setCreateDt(instanceTree.getCreateDt());
			instanceTreeData.setUpdateBy(instanceTree.getUpdateBy());
			instanceTreeData.setUpdateDt(instanceTree.getUpdateDt());

			instanceTreeDataList.add(instanceTreeData);

		}

		PackageVersionContainer<PkgVerInstanceTreeData> result = new PackageVersionContainer<PkgVerInstanceTreeData>();
		result.setData(instanceTreeDataList);
		result.setPagesCount(instanceTreeList.getTotalPages());
		result.setTotalItems(instanceTreeList.getTotalElements());
		return result;

	}

	/**
	 * @param pkgId
	 *            Primary key of BenefitPackage which is FK in
	 *            PkgMasterListForBig3CO
	 * @param page
	 *            Corresponding Page Number for Pagination
	 * @param maxResults
	 *            Maximum number of rows displayed in a page
	 * @return It returns PkgMasterListForBig3COData objects , PagesCount,
	 *         TotalItems.
	 *         <p>
	 *         This method returns the servicetype related to a package.
	 */
	@Transactional(readOnly = true)
	public PackageVersionContainer<PkgVerBig3COData> getPkgVerBig3CoByPkgVerId(
			int pkgVerId, int page, int maxResults) throws DataAccessException {

		Page<PkgVerBig3CO> page2 = null;
		List<PkgVerBig3COData> big3coDatas = new ArrayList<PkgVerBig3COData>();

		try {
			page2 = packageVersionRepository.findPkgVerBig3CoByPkgVerId(
					pkgVerId, new PageRequest(page, maxResults));

			List<Integer> serviceTypeInUseList = packageVersionRepository
					.findSetOfPkgVerBig3COUseAndUnUse(pkgVerId);

			for (PkgVerBig3CO pkgVerBig3CO : page2) {

				PkgVerBig3COData forBig3COData = new PkgVerBig3COData();
				// Set properties from pkgVerBig3CO
				if (pkgVerBig3CO.getPkgVerInstanceTree() != null) {
					forBig3COData
							.setCommonBenefit1Up(pkgVerBig3CO
									.getPkgVerInstanceTree()
									.getPkgVerInstanceTree1up());
				}

				forBig3COData.setCreateBy(pkgVerBig3CO.getCreateBy());
				forBig3COData.setCreateDt(new Date());
				forBig3COData.seteBSInstance1up(pkgVerBig3CO
						.geteBSInstance1up());
				forBig3COData.setIsCovered(pkgVerBig3CO.getCoveredFlag());
				forBig3COData
						.setTreatAs100(pkgVerBig3CO.getTreatAs100PctFlag());
				if (pkgVerBig3CO.getPkgVersion() != null) {
					if (pkgVerBig3CO.getPkgVersion().getBenefitPackage() != null) {
						forBig3COData.setBenefitRule1Up(pkgVerBig3CO
								.getPkgVersion().getBenefitPackage()
								.getBenefitPackage1up());
					}
				}
				if (pkgVerBig3CO.getPkgMasterListForBig3CO() != null) {
					if (pkgVerBig3CO.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition() != null) {
						if (pkgVerBig3CO.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat1() != null) {

							forBig3COData.setCategory1(pkgVerBig3CO
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat1()
									.getStringValue());

						}
					}

				}

				if (pkgVerBig3CO.getPkgMasterListForBig3CO() != null) {
					if (pkgVerBig3CO.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition() != null) {
						if (pkgVerBig3CO.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat2() != null) {

							forBig3COData.setCategory1(pkgVerBig3CO
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat2()
									.getStringValue());

						}
					}

				}

				if (pkgVerBig3CO.getPkgMasterListForBig3CO() != null) {
					if (pkgVerBig3CO.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition() != null) {
						if (pkgVerBig3CO.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat3() != null) {

							forBig3COData.setCategory1(pkgVerBig3CO
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat3()
									.getStringValue());

						}
					}

				}

				if (pkgVerBig3CO.getPkgMasterListForBig3CO() != null) {
					if (pkgVerBig3CO.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition() != null) {
						if (pkgVerBig3CO.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValPostString() != null) {

							forBig3COData.setCategory1(pkgVerBig3CO
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValPostString()
									.getStringValue());

						}
					}
				}

				if (serviceTypeInUseList != null) {
					forBig3COData.setInUse(serviceTypeInUseList
							.contains(pkgVerBig3CO.getPkgVerBig3CO1up()));
				} else {
					forBig3COData.setInUse(false);
				}
				System.out.println(serviceTypeInUseList.contains(pkgVerBig3CO
						.getPkgVerBig3CO1up()));
				// Adding Object to list
				big3coDatas.add(forBig3COData);

			}
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		PackageVersionContainer<PkgVerBig3COData> container = new PackageVersionContainer<PkgVerBig3COData>();
		container.setData(big3coDatas);
		container.setPagesCount(page2.getTotalPages());
		container.setTotalItems(page2.getTotalElements());

		return container;

	}

	public List<Integer> getEnabledPkgVerBig3CO(int pkgVerId, int pkgId,
			int page, int maxResults) {
		List<Integer> pkgMasterBig3COIdFromPkgMaster = new ArrayList<Integer>();
		List<Integer> pkgMasterBig3COIdFromPkgVer = new ArrayList<Integer>();

		Page<PkgMasterListForBig3CO> page2 = packageRepository
				.findPkgMasterListForBig3COByPkgId(pkgId, new PageRequest(page,
						maxResults));
		for (PkgMasterListForBig3CO pkgMasterListForBig3CO : page2) {
			pkgMasterBig3COIdFromPkgMaster.add(pkgMasterListForBig3CO
					.getPkgMasterListForBig3CO1up());
		}

		Page<PkgVerBig3CO> page3 = packageVersionRepository
				.findPkgVerBig3CoByPkgVerId(pkgVerId, new PageRequest(page,
						maxResults));
		for (PkgVerBig3CO pkgVerBig3CO : page3) {
			pkgMasterBig3COIdFromPkgVer
					.add(pkgVerBig3CO.getPkgMasterListForBig3CO()
							.getPkgMasterListForBig3CO1up());
		}

		List<Integer> enabledPkgVerId = new ArrayList<Integer>(
				pkgMasterBig3COIdFromPkgMaster);
		enabledPkgVerId.retainAll(pkgMasterBig3COIdFromPkgVer);

		return enabledPkgVerId;
	}

	/**
	 * @param masterListStringVal1up
	 *            List of masterListStringVal1up Ids
	 * 
	 * @return List<MasterListStringValData>
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method will return the List Of StringVal Objects
	 */
	@Transactional(readOnly = true)
	public List<MasterListStringValData> getBenefitRuleMapper(
			List<Integer> masterListStringVal1up) throws DataAccessException {

		List<MasterListStringVal> stringValList = null;
		try {
			stringValList = packageVersionRepository
					.findmasterListStringValforBenefitRule(masterListStringVal1up);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<MasterListStringValData> stringDataList = new ArrayList<MasterListStringValData>();

		for (MasterListStringVal stringVal : stringValList) {

			MasterListStringValData benefitRuleData = new MasterListStringValData();

			benefitRuleData.setAbbrev(stringVal.getAbbrev());
			benefitRuleData.setStringValue(stringVal.getStringValue());
			benefitRuleData.setMasterListStringVal1up(stringVal
					.getMasterListStringVal1up());
			stringDataList.add(benefitRuleData);
		}

		return stringDataList;

	}

	/**
	 * @param page
	 *            = Corresponding Page Number for Pagination
	 * @param maxSize
	 *            = Maximum number of rows displayed in a page
	 * @return = It returns the list of all {@link PackageVersionData} objects ,
	 *         PagesCount, TotalItems.
	 */
	@Transactional(readOnly = true)
	public PackageVersionContainer<PackageVersionSummaryData> getPackageVersionSummary(
			int page, int maxSize) {

		Page<PkgVersion> versionList = null;

		try {

			versionList = packageVersionRepository
					.findAllPackageVersion(new PageRequest(page, maxSize));

		} catch (PersistenceException e) {
			throw new PersistenceException();
		}

		List<PackageVersionSummaryData> versionDataList = new ArrayList<PackageVersionSummaryData>();

		for (PkgVersion pkgver : versionList) {
			PackageVersionSummaryData data = new PackageVersionSummaryData();

			data.setPkgVersion1up(pkgver.getPkgVersion1up());
			data.setCreateBy(pkgver.getCreateBy());
			data.setCreateDt(new Date());
			data.seteBSInstance1up(pkgver.geteBSInstance1up());
			data.setUpdateBy(pkgver.getUpdateBy());
			data.setUpdateDt(new Date());
			data.setVersionDescription(pkgver.getDescription());
			data.setVersionID(pkgver.getVersionNumber());
			data.setVersionName(pkgver.getName());

			if (pkgver.getPkgVersionWorkflowState() != null) {

				data.setReleased(pkgver.getPkgVersionWorkflowState()
						.getReleasedFlag());
			}

			if (pkgver.getBenefitPackage().getId() != null) {

				data.setPackageId(pkgver.getBenefitPackage().getId());
			}

			if (pkgver.getBenefitPackage().getName() != null) {

				data.setPackageName(pkgver.getBenefitPackage().getName());
			}

			if (pkgver.getBenefitPackage().getPackageTemplate().getId() != null) {
				data.setTemplateId(pkgver.getBenefitPackage()
						.getPackageTemplate().getId());
			}
			if (pkgver.getBenefitPackage().getPackageTemplate().getName() != null) {
				data.setTemplateName(pkgver.getBenefitPackage()
						.getPackageTemplate().getName());
			}

			versionDataList.add(data);
		}

		PackageVersionContainer<PackageVersionSummaryData> result = new PackageVersionContainer<PackageVersionSummaryData>();
		result.setData(versionDataList);
		result.setPagesCount(versionList.getTotalPages());
		result.setTotalItems(versionList.getTotalElements());

		return result;
	}

	/**
	 * @param big3coDatas
	 *            List of PkgVerBig3COData
	 * @return It returns ServiceOperationResult
	 * @throws DataAccessException
	 *             <p>
	 *             This method saves the service type for a particular PkgVer.
	 */
	@Transactional
	public ServiceOperationResult saveAndUpdatePkgVerBig3Co(
			List<PkgVerBig3COData> big3coDatas) throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		PkgVerBig3CO pkgVerBig3CO = new PkgVerBig3CO();

		for (PkgVerBig3COData data : big3coDatas) {
			if (data.getEnable() == true) {

				if (data.getIsCovered() == true) {
					pkgVerBig3CO.seteBSInstance1up(data.geteBSInstance1up());
					pkgVerBig3CO.setCoveredFlag(data.getIsCovered());

					try {
						pkgVerBig3CO
								.setPkgVerInstanceTree(packageVersionRepository
										.findPkgVerInstanceTreeById(data
												.getCommonBenefit1Up()));
						pkgVerBig3CO
								.setPkgMasterListForBig3CO(packageRepository
										.findPkgMasterListForBig3COById(data
												.getPkgMasterListForBig3COId()));
						pkgVerBig3CO
								.setPkgVersion(packageVersionRepository
										.findPackageVersionById(data
												.getPkgVersionId()));
					} catch (PersistenceException e) {
						throw new DataAccessException(e);
					}

					pkgVerBig3CO.setTreatAs100PctFlag(data.getTreatAs100());
					pkgVerBig3CO.setCreateBy(data.getCreateBy());
					pkgVerBig3CO.setCreateDt(new Date());
					pkgVerBig3CO.setsETRMessageSelected(data
							.getsETRMessageSelected());

				} else {
					operationResult.setOperationResult(OperationResult.WARNING);
					messages.add("Error saving value for PackageVersionBig3Co : ");
				}
			} else {
				operationResult.setOperationResult(OperationResult.WARNING);
				messages.add("Error saving value for PackageVersionBig3Co : ");
			}

			packageVersionRepository.saveData(pkgVerBig3CO);

		}

		return operationResult;
	}

	/**
	 * @param versionInstance
	 *            = pkgVersionInstanceTree1up
	 * @param page
	 *            Corresponding Page Number
	 * @param maxResults
	 *            Maximum number of rows displayed in a page
	 * @return = {@link PackageVersionContainer} It returns the list of all
	 *         PackageVersionData objects, PagesCount ,TotalItems.
	 * @throws = DataAcessException
	 */
	@Transactional(readOnly = true)
	public PackageVersionContainer<BenefitSelectionTierdataData> getAllBenifitBasedOnCommonBenifitType(
			int versionInstance, int page, int maxSize)
			throws DataAccessException {

		Page<BenefitSelectionTierdata> benSelList = null;
		List<ExcelViewInfo> excelViewInfos = null;

		List<Integer> treeShapeIds = new ArrayList<Integer>();
		List<Integer> pkgVerBig3CoIds = new ArrayList<Integer>();
		List<ShapeValueBig3CO> shapeValueBig3COs = new ArrayList<ShapeValueBig3CO>();
		List<ShapeValueTreeInstance> shapeValueTreeInstances = new ArrayList<ShapeValueTreeInstance>();
		List<MatrixShapeBig3COLimitXXref> big3coLimitXXrefs = new ArrayList<MatrixShapeBig3COLimitXXref>();
		List<MatrixShapeBig3COMessageXXref> big3coMessageXXrefs = new ArrayList<MatrixShapeBig3COMessageXXref>();
		try {

			benSelList = packageVersionRepository
					.findAllBenefitSelectionTierDataByCommonBenifit(
							versionInstance, new PageRequest(page, maxSize));

			for (BenefitSelectionTierdata selectionTierdata : benSelList) {
				pkgVerBig3CoIds.add(selectionTierdata.getPkgVerBig3CO()
						.getPkgVerBig3CO1up());
			}

			excelViewInfos = packageVersionRepository
					.findAllTreeShapeForCommonBenefit(versionInstance);

			for (ExcelViewInfo excelViewInfo : excelViewInfos) {
				treeShapeIds
						.add(excelViewInfo.getTreeShape().getTreeShape1up());
			}

			shapeValueBig3COs = packageVersionRepository
					.findAllShapeValueforPkgVerBig3Co(versionInstance,
							pkgVerBig3CoIds, treeShapeIds);
			if (shapeValueBig3COs == null) {
				shapeValueTreeInstances = packageVersionRepository
						.findDefaultShapeInstanceTreeValue(versionInstance);
			}

			big3coLimitXXrefs = packageVersionRepository
					.findAllLimitsForPkgVerBig3Co(pkgVerBig3CoIds);

			big3coMessageXXrefs = packageVersionRepository
					.findAllMessagessForPkgVerBig3Co(pkgVerBig3CoIds);

		} catch (PersistenceException e) {
			throw new PersistenceException();
		}

		int limitTreeShape = 0;
		int messageTreeShape = 0;

		for (ExcelViewInfo excelViewInfo : excelViewInfos) {
			if (excelViewInfo.getHeader().equals("Limit(Covered)")) {
				limitTreeShape = excelViewInfo.getTreeShape().getTreeShape1up();

			}
			if (excelViewInfo.getHeader().equals("Message(Covered)")) {
				messageTreeShape = excelViewInfo.getTreeShape()
						.getTreeShape1up();

			}
		}

		List<BenefitSelectionTierdataData> benSelDataList = new ArrayList<BenefitSelectionTierdataData>();

		for (BenefitSelectionTierdata benefitSelectionTierdata : benSelList) {

			List<DeductibleData> deductibleDatas = new ArrayList<DeductibleData>();
			List<MatrixShapeBig3COLimitXXrefData> big3coLimitXXrefDatas = this
					.getAllEnableLimitValForBenefitForEachPkgVersion(
							benefitSelectionTierdata.getPkgVerBig3CO()
									.getPkgVerBig3CO1up(), limitTreeShape);

			List<MatrixShapeBig3COMessageXXrefData> big3coMessageXXrefDatas = this
					.getAllEnableMessageValForBenefitForEachPkgVersion(
							benefitSelectionTierdata.getPkgVerBig3CO()
									.getPkgVerBig3CO1up(), messageTreeShape);

			BenefitSelectionTierdataData data = new BenefitSelectionTierdataData();

			// data.setId(benefitSelectionTierdata
			// .getBenefitSelectionTierdata1up());

			if (big3coMessageXXrefDatas != null) {

				data.setMessageData(big3coMessageXXrefDatas);

			}
			if (big3coLimitXXrefDatas != null) {

				data.setLimitData(big3coLimitXXrefDatas);

			}

			if (benefitSelectionTierdata.getPkgVerBig3CO() != null) {
				data.setPkgVerBig3CO1up(benefitSelectionTierdata
						.getPkgVerBig3CO().getPkgVerBig3CO1up());
			}

			// if (benefitSelectionTierdata.getPkgVerInstanceTree() != null) {
			//
			// PkgVerInstanceTreeData commonBenefit = new
			// PkgVerInstanceTreeData();
			// commonBenefit.setPkgVerInstanceTree1up(benefitSelectionTierdata
			// .getPkgVerInstanceTree().getPkgVerInstanceTree1up());
			// commonBenefit.setName(benefitSelectionTierdata
			// .getPkgVerInstanceTree().getName());
			// commonBenefit.setDescription(benefitSelectionTierdata
			// .getPkgVerInstanceTree().getDescription());
			// data.setPkgVerInstanceTreeData(commonBenefit);
			//
			// }

			if (benefitSelectionTierdata.getPkgVerBig3CO() != null) {
				if (benefitSelectionTierdata.getPkgVerBig3CO()
						.getPkgMasterListForBig3CO() != null) {
					if (benefitSelectionTierdata.getPkgVerBig3CO()
							.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition() != null) {

						MasterListServiceDefinitionData definitionData = new MasterListServiceDefinitionData();

						if (benefitSelectionTierdata.getPkgVerBig3CO()
								.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat1() != null) {
							MasterListStringValData category1 = new MasterListStringValData();
							category1
									.setMasterListStringVal1up(benefitSelectionTierdata
											.getPkgVerBig3CO()
											.getPkgMasterListForBig3CO()
											.getMasterListServiceDefinition()
											.getMasterListStringValCat1()
											.getMasterListStringVal1up());
							category1.setStringValue(benefitSelectionTierdata
									.getPkgVerBig3CO()
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat1()
									.getStringValue());
							category1.setAbbrev(benefitSelectionTierdata
									.getPkgVerBig3CO()
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat1().getAbbrev());
							definitionData.setCategory1(category1);

						}

						if (benefitSelectionTierdata.getPkgVerBig3CO()
								.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat2() != null) {
							MasterListStringValData category2 = new MasterListStringValData();
							category2
									.setMasterListStringVal1up(benefitSelectionTierdata
											.getPkgVerBig3CO()
											.getPkgMasterListForBig3CO()
											.getMasterListServiceDefinition()
											.getMasterListStringValCat2()
											.getMasterListStringVal1up());
							category2.setStringValue(benefitSelectionTierdata
									.getPkgVerBig3CO()
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat2()
									.getStringValue());
							category2.setAbbrev(benefitSelectionTierdata
									.getPkgVerBig3CO()
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat2().getAbbrev());
							definitionData.setCategory2(category2);
						}

						if (benefitSelectionTierdata.getPkgVerBig3CO()
								.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat3() != null) {
							MasterListStringValData category3 = new MasterListStringValData();
							category3
									.setMasterListStringVal1up(benefitSelectionTierdata
											.getPkgVerBig3CO()
											.getPkgMasterListForBig3CO()
											.getMasterListServiceDefinition()
											.getMasterListStringValCat3()
											.getMasterListStringVal1up());
							category3.setStringValue(benefitSelectionTierdata
									.getPkgVerBig3CO()
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat3()
									.getStringValue());
							category3.setAbbrev(benefitSelectionTierdata
									.getPkgVerBig3CO()
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat3().getAbbrev());
							definitionData.setCategory3(category3);
						}

						if (benefitSelectionTierdata.getPkgVerBig3CO()
								.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValPostString() != null) {
							MasterListStringValData placeOfService = new MasterListStringValData();
							placeOfService
									.setMasterListStringVal1up(benefitSelectionTierdata
											.getPkgVerBig3CO()
											.getPkgMasterListForBig3CO()
											.getMasterListServiceDefinition()
											.getMasterListStringValPostString()
											.getMasterListStringVal1up());
							placeOfService
									.setStringValue(benefitSelectionTierdata
											.getPkgVerBig3CO()
											.getPkgMasterListForBig3CO()
											.getMasterListServiceDefinition()
											.getMasterListStringValPostString()
											.getStringValue());
							placeOfService.setAbbrev(benefitSelectionTierdata
									.getPkgVerBig3CO()
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValPostString()
									.getAbbrev());
							definitionData.setPlaceOfService(placeOfService);
						}

						data.setMasterListServiceDefinitionData(definitionData);

					}
				}

			}

			if (benefitSelectionTierdata.getPkgVerBig3CO() != null) {
				if (benefitSelectionTierdata.getPkgVerBig3CO()
						.getPkgMasterListForBig3CO() != null) {
					if (benefitSelectionTierdata.getPkgVerBig3CO()
							.getPkgMasterListForBig3CO()
							.getMasterListStringVal() != null) {

						MasterListStringValData benefitRule = new MasterListStringValData();
						benefitRule
								.setMasterListStringVal1up(benefitSelectionTierdata
										.getPkgVerBig3CO()
										.getPkgMasterListForBig3CO()
										.getMasterListStringVal()
										.getMasterListStringVal1up());

						benefitRule.setStringValue(benefitSelectionTierdata
								.getPkgVerBig3CO().getPkgMasterListForBig3CO()
								.getMasterListStringVal().getStringValue());

						benefitRule.setAbbrev(benefitSelectionTierdata
								.getPkgVerBig3CO().getPkgMasterListForBig3CO()
								.getMasterListStringVal().getAbbrev());

						data.setMasterListStringValData(benefitRule);

					}
				}
			}

			if (benefitSelectionTierdata.getPkgVerBig3CO() != null) {
				data.setIsCovered(benefitSelectionTierdata.getPkgVerBig3CO()
						.getCoveredFlag());
			}
			if (benefitSelectionTierdata.getPkgVerBig3CO()
					.getTreatAs100PctFlag() != null) {

				data.setTreatAs100(benefitSelectionTierdata.getPkgVerBig3CO()
						.getTreatAs100PctFlag());

			}

			if (shapeValueBig3COs != null) {

				for (ShapeValueBig3CO shapeValueBig3CO : shapeValueBig3COs) {

					if (benefitSelectionTierdata.getPkgVerBig3CO()
							.getPkgVerBig3CO1up() == shapeValueBig3CO
							.getShapeValueBig3COId().getPkgVerBig3CO()
							.getPkgVerBig3CO1up()) {

						setCoInsurenceAndDeductibleDetails(excelViewInfos,
								deductibleDatas, data, shapeValueBig3CO);

					}

				}
			} else {
				if (shapeValueTreeInstances != null) {
					for (ShapeValueTreeInstance shapeValueTreeInstance : shapeValueTreeInstances) {

						for (ExcelViewInfo excelViewInfo : excelViewInfos) {
							if (excelViewInfo.getHeader().equals(
									"Apply Deductible")
									&& shapeValueTreeInstance
											.getShapeValueTreeInstanceId()
											.getTreeShape().getTreeShape1up() == excelViewInfo
											.getTreeShape().getTreeShape1up()) {

								data.setApplyDeductible(shapeValueTreeInstance
										.getIfYNTestAnswerYesFlag());

							}

							if (excelViewInfo.getColumnType().equals(
									"ApplyDedYes")
									&& shapeValueTreeInstance
											.getShapeValueTreeInstanceId()
											.getTreeShape().getTreeShape1up() == excelViewInfo
											.getTreeShape().getTreeShape1up()) {

								DeductibleData deductibleData = new DeductibleData();

								deductibleData.setName(excelViewInfo
										.getHeader());

								MasterListIntValData indDudect = new MasterListIntValData();
								indDudect
										.setMasterListIntVal1up(shapeValueTreeInstance
												.getMasterListIntVal()
												.getMasterListIntVal1up());
								indDudect
										.setIntAsDecimalValue(shapeValueTreeInstance
												.getMasterListIntVal()
												.getIntAsDecimalValue());

								indDudect.setAbbrev(shapeValueTreeInstance
										.getMasterListIntVal().getAbbrev());

								deductibleData.setValue(indDudect);

								deductibleDatas.add(deductibleData);

							}

							// if (excelViewInfo.getHeader().equals("FAM")
							// && shapeValueTreeInstance
							// .getShapeValueTreeInstanceId()
							// .getTreeShape().getTreeShape1up() ==
							// excelViewInfo
							// .getTreeShape().getTreeShape1up()) {
							//
							// MasterListIntValData famDudect = new
							// MasterListIntValData();
							// famDudect
							// .setMasterListIntVal1up(shapeValueTreeInstance
							// .getMasterListIntVal()
							// .getMasterListIntVal1up());
							// famDudect
							// .setIntAsDecimalValue(shapeValueTreeInstance
							// .getMasterListIntVal()
							// .getIntAsDecimalValue());
							//
							// famDudect.setAbbrev(shapeValueTreeInstance
							// .getMasterListIntVal().getAbbrev());
							//
							// data.setApplyFamDeductible(famDudect);
							//
							// }

							if (excelViewInfo.getHeader().equals(
									"Apply Deductible-No")
									&& shapeValueTreeInstance
											.getShapeValueTreeInstanceId()
											.getTreeShape().getTreeShape1up() == excelViewInfo
											.getTreeShape().getTreeShape1up()) {

								MasterListStringValData noDudect = new MasterListStringValData();
								noDudect.setMasterListStringVal1up(shapeValueTreeInstance
										.getMasterListStringVal()
										.getMasterListStringVal1up());
								noDudect.setStringValue(shapeValueTreeInstance
										.getMasterListStringVal()
										.getStringValue());

								noDudect.setAbbrev(shapeValueTreeInstance
										.getMasterListStringVal().getAbbrev());

								data.setApplyDeductibleNo(noDudect);

							}
							if (excelViewInfo.getHeader().equals(
									"Apply Coinsurance")
									&& shapeValueTreeInstance
											.getShapeValueTreeInstanceId()
											.getTreeShape().getTreeShape1up() == excelViewInfo
											.getTreeShape().getTreeShape1up()) {

								data.setApplyCoinsurance(shapeValueTreeInstance
										.getIfYNTestAnswerYesFlag());

							}

							if (excelViewInfo.getHeader().equals(
									"Apply Coinsurance-Yes")
									&& shapeValueTreeInstance
											.getShapeValueTreeInstanceId()
											.getTreeShape().getTreeShape1up() == excelViewInfo
											.getTreeShape().getTreeShape1up()) {

								MasterListIntValData yesCoInsure = new MasterListIntValData();
								yesCoInsure
										.setMasterListIntVal1up(shapeValueTreeInstance
												.getMasterListIntVal()
												.getMasterListIntVal1up());
								yesCoInsure
										.setIntAsDecimalValue(shapeValueTreeInstance
												.getMasterListIntVal()
												.getIntAsDecimalValue());

								yesCoInsure.setAbbrev(shapeValueTreeInstance
										.getMasterListIntVal().getAbbrev());

								data.setCoinsuranceYesValData(yesCoInsure);

							}

							if (excelViewInfo.getHeader().equals(
									"Apply Coinsurance-No")
									&& shapeValueTreeInstance
											.getShapeValueTreeInstanceId()
											.getTreeShape().getTreeShape1up() == excelViewInfo
											.getTreeShape().getTreeShape1up()) {

								MasterListStringValData noCoInsure = new MasterListStringValData();
								noCoInsure
										.setMasterListStringVal1up(shapeValueTreeInstance
												.getMasterListStringVal()
												.getMasterListStringVal1up());
								noCoInsure
										.setStringValue(shapeValueTreeInstance
												.getMasterListStringVal()
												.getStringValue());

								noCoInsure.setAbbrev(shapeValueTreeInstance
										.getMasterListStringVal().getAbbrev());

								data.setCoinsuranceNoValData(noCoInsure);

							}
							if (excelViewInfo.getHeader().equals("Copay")
									&& shapeValueTreeInstance
											.getShapeValueTreeInstanceId()
											.getTreeShape().getTreeShape1up() == excelViewInfo
											.getTreeShape().getTreeShape1up()) {

								MasterListIntValData coPayVal = new MasterListIntValData();
								coPayVal.setMasterListIntVal1up(shapeValueTreeInstance
										.getMasterListIntVal()
										.getMasterListIntVal1up());

								coPayVal.setIntAsDecimalValue(shapeValueTreeInstance
										.getMasterListIntVal()
										.getIntAsDecimalValue());

								coPayVal.setAbbrev(shapeValueTreeInstance
										.getMasterListIntVal().getAbbrev());
								data.setCopayValData(coPayVal);

							}

							if (excelViewInfo.getHeader().equals("Allowed Amt")
									&& shapeValueTreeInstance
											.getShapeValueTreeInstanceId()
											.getTreeShape().getTreeShape1up() == excelViewInfo
											.getTreeShape().getTreeShape1up()) {
								MasterListIntValData allowedAmount = new MasterListIntValData();
								allowedAmount
										.setMasterListIntVal1up(shapeValueTreeInstance
												.getMasterListIntVal()
												.getMasterListIntVal1up());
								allowedAmount
										.setIntAsDecimalValue(shapeValueTreeInstance
												.getMasterListIntVal()
												.getIntAsDecimalValue());
								allowedAmount.setAbbrev(shapeValueTreeInstance
										.getMasterListIntVal().getAbbrev());
								data.setAllowedAmountData(allowedAmount);

							}
							if (excelViewInfo.getHeader().equals("Allowed Ctr")
									&& shapeValueTreeInstance
											.getShapeValueTreeInstanceId()
											.getTreeShape().getTreeShape1up() == excelViewInfo
											.getTreeShape().getTreeShape1up()) {
								MasterListIntValData allowedCtrl = new MasterListIntValData();
								allowedCtrl
										.setMasterListIntVal1up(shapeValueTreeInstance
												.getMasterListIntVal()
												.getMasterListIntVal1up());
								allowedCtrl
										.setIntAsDecimalValue(shapeValueTreeInstance
												.getMasterListIntVal()
												.getIntAsDecimalValue());
								allowedCtrl.setAbbrev(shapeValueTreeInstance
										.getMasterListIntVal().getAbbrev());
								data.setAllowedCtrData(allowedCtrl);

							}

						}

					}
				}

			}
			data.setTierNo(benefitSelectionTierdata.getTierNo());
			int limitCount = 0;
			for (MatrixShapeBig3COLimitXXref big3coLimitXXref : big3coLimitXXrefs) {

				if (benefitSelectionTierdata.getPkgVerBig3CO()
						.getPkgVerBig3CO1up() == big3coLimitXXref
						.getMatrixShapeBig3COLimitXXrefId()
						.getMatrixShapeBig3COXrefId().getPkgVerBig3CO()
						.getPkgVerBig3CO1up()) {
					limitCount++;

				}

			}
			data.setLimitCount(limitCount);
			int messageCount = 0;
			for (MatrixShapeBig3COMessageXXref big3coMessageXXref : big3coMessageXXrefs) {

				if (benefitSelectionTierdata.getPkgVerBig3CO()
						.getPkgVerBig3CO1up() == big3coMessageXXref
						.getMatrixShapeBig3COMessageXXrefId()
						.getMatrixShapeBig3COXrefId().getPkgVerBig3CO()
						.getPkgVerBig3CO1up()) {
					messageCount++;

				}

			}

			data.setMessageCount(messageCount);
			data.setDeductibleData(deductibleDatas);

			// data.setCreateBy(benefitSelectionTierdata.getCreateBy());
			//
			// data.setCreateDt(benefitSelectionTierdata.getCreateDt());
			//
			// data.setUpdateBy(benefitSelectionTierdata.getUpdateBy());
			//
			// data.setUpdateDt(benefitSelectionTierdata.getUpdateDt());
			//
			// data.seteBSInstance1up(benefitSelectionTierdata.geteBSInstance1up());

			benSelDataList.add(data);

		}

		PackageVersionContainer<BenefitSelectionTierdataData> result = new PackageVersionContainer<BenefitSelectionTierdataData>();

		result.setData(benSelDataList);
		result.setPagesCount(benSelList.getTotalPages());
		result.setTotalItems(benSelList.getTotalElements());

		return result;
	}

	@Transactional(readOnly = true)
	public BenefitSelectionTierdataData getDefaultBenifitBasedOnPkgVerBenefitSlection(
			int versionInstance) throws DataAccessException {
		List<ExcelViewInfo> allShapeexcelViewInfos = null;
		PkgVerBig3CO defaultPkgVerBig3Co = new PkgVerBig3CO();
		List<ShapeValueTreeInstance> defaultInstances = new ArrayList<ShapeValueTreeInstance>();
		List<MatrixShapeBig3COLimitXXref> big3coLimitXXrefs = new ArrayList<MatrixShapeBig3COLimitXXref>();
		List<MatrixShapeBig3COMessageXXref> big3coMessageXXrefs = new ArrayList<MatrixShapeBig3COMessageXXref>();
		try {

			defaultPkgVerBig3Co = packageVersionRepository
					.findDefaultPkgVerbig3Co(versionInstance);

			defaultInstances = packageVersionRepository
					.findDefaultShapeInstanceTreeValue(versionInstance);

			allShapeexcelViewInfos = packageVersionRepository
					.findAllTreeShapeForCommonBenefit(versionInstance);

			big3coLimitXXrefs = packageVersionRepository
					.findAllLimitsForDefaultPkgVerBig3Co(defaultPkgVerBig3Co
							.getPkgVerBig3CO1up());

			big3coMessageXXrefs = packageVersionRepository
					.findAllMessagessForDefaultPkgVerBig3Co(defaultPkgVerBig3Co
							.getPkgVerBig3CO1up());

		} catch (PersistenceException e) {
			throw new PersistenceException();
		}

		BenefitSelectionTierdataData benefitSelectionTierdata = new BenefitSelectionTierdataData();

		List<DeductibleData> deductibleDatas = new ArrayList<DeductibleData>();
		benefitSelectionTierdata.setPkgVerBig3CO1up(defaultPkgVerBig3Co
				.getPkgVerBig3CO1up());
		int limitTreeShape = 0;
		int messageTreeShape = 0;
		for (ExcelViewInfo excelViewInfo : allShapeexcelViewInfos) {
			if (excelViewInfo.getHeader().equals("Limit(Covered)")) {
				limitTreeShape = excelViewInfo.getTreeShape().getTreeShape1up();

			}
			if (excelViewInfo.getHeader().equals("Message(Covered)")) {
				messageTreeShape = excelViewInfo.getTreeShape()
						.getTreeShape1up();

			}
		}
		List<MatrixShapeBig3COLimitXXrefData> big3coLimitXXrefDatas = this
				.getAllEnableLimitValForBenefitForEachPkgVersion(
						defaultPkgVerBig3Co.getPkgVerBig3CO1up(),
						limitTreeShape);

		List<MatrixShapeBig3COMessageXXrefData> big3coMessageXXrefDatas = this
				.getAllEnableMessageValForBenefitForEachPkgVersion(
						defaultPkgVerBig3Co.getPkgVerBig3CO1up(),
						messageTreeShape);

		if (big3coMessageXXrefDatas != null) {

			benefitSelectionTierdata.setMessageData(big3coMessageXXrefDatas);

		}
		if (big3coLimitXXrefDatas != null) {

			benefitSelectionTierdata.setLimitData(big3coLimitXXrefDatas);

		}

		PkgVerInstanceTreeData commonBenefitDefault = new PkgVerInstanceTreeData();
		commonBenefitDefault.setPkgVerInstanceTree1up(defaultPkgVerBig3Co
				.getPkgVerInstanceTree().getPkgVerInstanceTree1up());
		commonBenefitDefault.setName(defaultPkgVerBig3Co
				.getPkgVerInstanceTree().getName());
		commonBenefitDefault.setDescription(defaultPkgVerBig3Co
				.getPkgVerInstanceTree().getDescription());
		benefitSelectionTierdata
				.setPkgVerInstanceTreeData(commonBenefitDefault);

		MasterListServiceDefinitionData defaultDefinitionData = new MasterListServiceDefinitionData();
		if (defaultPkgVerBig3Co.getPkgMasterListForBig3CO() != null) {
			if (defaultPkgVerBig3Co.getPkgMasterListForBig3CO()
					.getMasterListServiceDefinition() != null) {
				if (defaultPkgVerBig3Co.getPkgMasterListForBig3CO()
						.getMasterListServiceDefinition()
						.getMasterListStringValCat1() != null) {
					MasterListStringValData defaultcategory1 = new MasterListStringValData();
					defaultcategory1
							.setMasterListStringVal1up(defaultPkgVerBig3Co
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat1()
									.getMasterListStringVal1up());
					defaultcategory1.setStringValue(defaultPkgVerBig3Co
							.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition()
							.getMasterListStringValCat1().getStringValue());
					defaultcategory1.setAbbrev(defaultPkgVerBig3Co
							.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition()
							.getMasterListStringValCat1().getAbbrev());
					defaultDefinitionData.setCategory1(defaultcategory1);

				}

				if (defaultPkgVerBig3Co.getPkgMasterListForBig3CO()
						.getMasterListServiceDefinition()
						.getMasterListStringValCat2() != null) {
					MasterListStringValData defaultcategory2 = new MasterListStringValData();
					defaultcategory2
							.setMasterListStringVal1up(defaultPkgVerBig3Co
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat2()
									.getMasterListStringVal1up());
					defaultcategory2.setStringValue(defaultPkgVerBig3Co
							.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition()
							.getMasterListStringValCat2().getStringValue());
					defaultcategory2.setAbbrev(defaultPkgVerBig3Co
							.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition()
							.getMasterListStringValCat2().getAbbrev());
					defaultDefinitionData.setCategory1(defaultcategory2);

				}

				if (defaultPkgVerBig3Co.getPkgMasterListForBig3CO()
						.getMasterListServiceDefinition()
						.getMasterListStringValCat3() != null) {
					MasterListStringValData defaultcategory3 = new MasterListStringValData();
					defaultcategory3
							.setMasterListStringVal1up(defaultPkgVerBig3Co
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat3()
									.getMasterListStringVal1up());
					defaultcategory3.setStringValue(defaultPkgVerBig3Co
							.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition()
							.getMasterListStringValCat3().getStringValue());
					defaultcategory3.setAbbrev(defaultPkgVerBig3Co
							.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition()
							.getMasterListStringValCat3().getAbbrev());
					defaultDefinitionData.setCategory1(defaultcategory3);

				}

				if (defaultPkgVerBig3Co.getPkgMasterListForBig3CO()
						.getMasterListServiceDefinition()
						.getMasterListStringValPostString() != null) {
					MasterListStringValData defaultPos = new MasterListStringValData();
					defaultPos.setMasterListStringVal1up(defaultPkgVerBig3Co
							.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition()
							.getMasterListStringValPostString()
							.getMasterListStringVal1up());
					defaultPos.setStringValue(defaultPkgVerBig3Co
							.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition()
							.getMasterListStringValPostString()
							.getStringValue());
					defaultPos.setAbbrev(defaultPkgVerBig3Co
							.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition()
							.getMasterListStringValPostString().getAbbrev());
					defaultDefinitionData.setCategory1(defaultPos);

				}

				benefitSelectionTierdata
						.setMasterListServiceDefinitionData(defaultDefinitionData);
			}
		}

		if (defaultPkgVerBig3Co.getPkgMasterListForBig3CO() != null) {
			if (defaultPkgVerBig3Co.getPkgMasterListForBig3CO()
					.getMasterListStringVal() != null) {

				MasterListStringValData benefitRule = new MasterListStringValData();
				benefitRule.setMasterListStringVal1up(defaultPkgVerBig3Co
						.getPkgMasterListForBig3CO().getMasterListStringVal()
						.getMasterListStringVal1up());

				benefitRule.setStringValue(defaultPkgVerBig3Co
						.getPkgMasterListForBig3CO().getMasterListStringVal()
						.getStringValue());

				benefitRule.setAbbrev(defaultPkgVerBig3Co
						.getPkgMasterListForBig3CO().getMasterListStringVal()
						.getAbbrev());

				benefitSelectionTierdata
						.setMasterListStringValData(benefitRule);

			}
		}

		benefitSelectionTierdata.setIsCovered(defaultPkgVerBig3Co
				.getCoveredFlag());

		benefitSelectionTierdata.setTreatAs100(defaultPkgVerBig3Co
				.getTreatAs100PctFlag());
		for (ShapeValueTreeInstance defaultInstance : defaultInstances) {
			for (ExcelViewInfo excelViewInfo : allShapeexcelViewInfos) {
				if (excelViewInfo.getHeader().equals("Apply Deductible")
						&& defaultInstance.getShapeValueTreeInstanceId()
								.getTreeShape().getTreeShape1up() == excelViewInfo
								.getTreeShape().getTreeShape1up()) {

					benefitSelectionTierdata.setApplyDeductible(defaultInstance
							.getIfYNTestAnswerYesFlag());

				}

				if (excelViewInfo.getColumnType().equals("ApplyDedYes")
						&& defaultInstance.getShapeValueTreeInstanceId()
								.getTreeShape().getTreeShape1up() == excelViewInfo
								.getTreeShape().getTreeShape1up()) {
					DeductibleData deductibleData = new DeductibleData();

					deductibleData.setName(excelViewInfo.getHeader());

					MasterListIntValData indDudect = new MasterListIntValData();
					indDudect.setMasterListIntVal1up(defaultInstance
							.getMasterListIntVal().getMasterListIntVal1up());
					indDudect.setIntAsDecimalValue(defaultInstance
							.getMasterListIntVal().getIntAsDecimalValue());

					indDudect.setAbbrev(defaultInstance.getMasterListIntVal()
							.getAbbrev());

					deductibleData.setValue(indDudect);

					deductibleDatas.add(deductibleData);

				}

				// if (excelViewInfo.getHeader().equals("FAM")
				// && defaultInstance.getShapeValueTreeInstanceId()
				// .getTreeShape().getTreeShape1up() == excelViewInfo
				// .getTreeShape().getTreeShape1up()) {
				//
				// MasterListIntValData famDudect = new MasterListIntValData();
				// famDudect.setMasterListIntVal1up(defaultInstance
				// .getMasterListIntVal().getMasterListIntVal1up());
				// famDudect.setIntAsDecimalValue(defaultInstance
				// .getMasterListIntVal().getIntAsDecimalValue());
				//
				// famDudect.setAbbrev(defaultInstance.getMasterListIntVal()
				// .getAbbrev());
				//
				// benefitSelectionTierdata.setApplyFamDeductible(famDudect);
				//
				// }

				if (excelViewInfo.getHeader().equals("Apply Deductible-No")
						&& defaultInstance.getShapeValueTreeInstanceId()
								.getTreeShape().getTreeShape1up() == excelViewInfo
								.getTreeShape().getTreeShape1up()) {

					MasterListStringValData noDudect = new MasterListStringValData();
					noDudect.setMasterListStringVal1up(defaultInstance
							.getMasterListStringVal()
							.getMasterListStringVal1up());
					noDudect.setStringValue(defaultInstance
							.getMasterListStringVal().getStringValue());

					noDudect.setAbbrev(defaultInstance.getMasterListStringVal()
							.getAbbrev());

					benefitSelectionTierdata.setApplyDeductibleNo(noDudect);

				}

				if (excelViewInfo.getHeader().equals("Apply Coinsurance")
						&& defaultInstance.getShapeValueTreeInstanceId()
								.getTreeShape().getTreeShape1up() == excelViewInfo
								.getTreeShape().getTreeShape1up()) {

					benefitSelectionTierdata
							.setApplyCoinsurance(defaultInstance
									.getIfYNTestAnswerYesFlag());

				}

				if (excelViewInfo.getHeader().equals("Apply Coinsurance-Yes")
						&& defaultInstance.getShapeValueTreeInstanceId()
								.getTreeShape().getTreeShape1up() == excelViewInfo
								.getTreeShape().getTreeShape1up()) {

					MasterListIntValData yesCoInsure = new MasterListIntValData();
					yesCoInsure.setMasterListIntVal1up(defaultInstance
							.getMasterListIntVal().getMasterListIntVal1up());
					yesCoInsure.setIntAsDecimalValue(defaultInstance
							.getMasterListIntVal().getIntAsDecimalValue());

					yesCoInsure.setAbbrev(defaultInstance.getMasterListIntVal()
							.getAbbrev());

					benefitSelectionTierdata
							.setCoinsuranceYesValData(yesCoInsure);

				}

				if (excelViewInfo.getHeader().equals("Apply Coinsurance-No")
						&& defaultInstance.getShapeValueTreeInstanceId()
								.getTreeShape().getTreeShape1up() == excelViewInfo
								.getTreeShape().getTreeShape1up()) {

					MasterListStringValData noCoInsure = new MasterListStringValData();
					noCoInsure.setMasterListStringVal1up(defaultInstance
							.getMasterListStringVal()
							.getMasterListStringVal1up());
					noCoInsure.setStringValue(defaultInstance
							.getMasterListStringVal().getStringValue());

					noCoInsure.setAbbrev(defaultInstance
							.getMasterListStringVal().getAbbrev());

					benefitSelectionTierdata
							.setCoinsuranceNoValData(noCoInsure);

				}
				if (excelViewInfo.getHeader().equals("Copay")
						&& defaultInstance.getShapeValueTreeInstanceId()
								.getTreeShape().getTreeShape1up() == excelViewInfo
								.getTreeShape().getTreeShape1up()) {

					MasterListIntValData coPayVal = new MasterListIntValData();
					coPayVal.setMasterListIntVal1up(defaultInstance
							.getMasterListIntVal().getMasterListIntVal1up());

					coPayVal.setIntAsDecimalValue(defaultInstance
							.getMasterListIntVal().getIntAsDecimalValue());

					coPayVal.setAbbrev(defaultInstance.getMasterListIntVal()
							.getAbbrev());
					benefitSelectionTierdata.setCopayValData(coPayVal);

				}

				if (excelViewInfo.getHeader().equals("Allowed Amt")
						&& defaultInstance.getShapeValueTreeInstanceId()
								.getTreeShape().getTreeShape1up() == excelViewInfo
								.getTreeShape().getTreeShape1up()) {
					MasterListIntValData allowedAmount = new MasterListIntValData();
					allowedAmount.setMasterListIntVal1up(defaultInstance
							.getMasterListIntVal().getMasterListIntVal1up());
					allowedAmount.setIntAsDecimalValue(defaultInstance
							.getMasterListIntVal().getIntAsDecimalValue());
					allowedAmount.setAbbrev(defaultInstance
							.getMasterListIntVal().getAbbrev());
					benefitSelectionTierdata
							.setAllowedAmountData(allowedAmount);

				}
				if (excelViewInfo.getHeader().equals("Allowed Ctr")
						&& defaultInstance.getShapeValueTreeInstanceId()
								.getTreeShape().getTreeShape1up() == excelViewInfo
								.getTreeShape().getTreeShape1up()) {
					MasterListIntValData allowedCtrl = new MasterListIntValData();
					allowedCtrl.setMasterListIntVal1up(defaultInstance
							.getMasterListIntVal().getMasterListIntVal1up());
					allowedCtrl.setIntAsDecimalValue(defaultInstance
							.getMasterListIntVal().getIntAsDecimalValue());
					allowedCtrl.setAbbrev(defaultInstance.getMasterListIntVal()
							.getAbbrev());
					benefitSelectionTierdata.setAllowedCtrData(allowedCtrl);

				}

			}

		}

		int limitCount = 0;
		for (MatrixShapeBig3COLimitXXref big3coLimitXXref : big3coLimitXXrefs) {

			if (defaultPkgVerBig3Co.getPkgVerBig3CO1up() == big3coLimitXXref
					.getMatrixShapeBig3COLimitXXrefId()
					.getMatrixShapeBig3COXrefId().getPkgVerBig3CO()
					.getPkgVerBig3CO1up()) {
				limitCount++;

			}

		}
		benefitSelectionTierdata.setLimitCount(limitCount);
		int messageCount = 0;
		for (MatrixShapeBig3COMessageXXref big3coMessageXXref : big3coMessageXXrefs) {

			if (defaultPkgVerBig3Co.getPkgVerBig3CO1up() == big3coMessageXXref
					.getMatrixShapeBig3COMessageXXrefId()
					.getMatrixShapeBig3COXrefId().getPkgVerBig3CO()
					.getPkgVerBig3CO1up()) {
				messageCount++;

			}

		}

		benefitSelectionTierdata.setMessageCount(messageCount);

		benefitSelectionTierdata.setDeductibleData(deductibleDatas);

		benefitSelectionTierdata.seteBSInstance1up(defaultPkgVerBig3Co
				.geteBSInstance1up());

		return benefitSelectionTierdata;

	}

	@Transactional(readOnly = true)
	public PackageVersionContainer<BenefitSelectionTierdataData> getBenefitSelectionForBenefitOption(
			int versionInstance, int benfitOptionId, int page, int maxSize)
			throws DataAccessException {

		Page<BenefitOptionTierdata> benSelList = null;
		List<ExcelViewInfo> excelViewInfos = new ArrayList<ExcelViewInfo>();
		List<Integer> treeShapeIds = new ArrayList<Integer>();
		List<Integer> pkgVerBig3CoIds = new ArrayList<Integer>();
		List<ShapeValueBenefitOptionForBig3CO> shapeValueBenefitOptionForBig3COs = null;
		List<ShapeValueBig3CO> shapeValueBig3COs = new ArrayList<ShapeValueBig3CO>();
		List<LimitShapeBenefitOptionOverride> big3coLimitXXrefs = new ArrayList<LimitShapeBenefitOptionOverride>();
		List<MessageShapeBenefitOptionOverride> big3coMessageXXrefs = new ArrayList<MessageShapeBenefitOptionOverride>();

		try {

			benSelList = packageVersionRepository
					.findAllBenefitSelectionForBenefitOption(versionInstance,
							benfitOptionId, new PageRequest(page, maxSize));

			if (benSelList != null && benSelList.getContent().size() > 0) {
				for (BenefitOptionTierdata optionTierdata : benSelList) {
					pkgVerBig3CoIds.add(optionTierdata.getPkgVerBig3CO()
							.getPkgVerBig3CO1up());
				}
			}

			excelViewInfos = packageVersionRepository
					.findAllTreeShapeForCommonBenefit(versionInstance);
			if (excelViewInfos != null) {
				for (ExcelViewInfo excelViewInfo : excelViewInfos) {
					treeShapeIds.add(excelViewInfo.getTreeShape()
							.getTreeShape1up());
				}
			}

			if (pkgVerBig3CoIds != null && pkgVerBig3CoIds.size() > 0) {
				if (treeShapeIds != null && treeShapeIds.size() > 0) {
					shapeValueBenefitOptionForBig3COs = packageVersionRepository
							.findBenefitOptionDeductibleValueforPkgVerBig3Co(
									versionInstance, benfitOptionId,
									pkgVerBig3CoIds, treeShapeIds);
					if (shapeValueBenefitOptionForBig3COs == null) {
						shapeValueBig3COs = packageVersionRepository
								.findAllShapeValueforPkgVerBig3Co(
										versionInstance, pkgVerBig3CoIds,
										treeShapeIds);
					}

				}
			}

			if (pkgVerBig3CoIds != null && pkgVerBig3CoIds.size() > 0) {
				if (excelViewInfos != null) {
					for (ExcelViewInfo excelViewInfo : excelViewInfos) {
						if (excelViewInfo.getHeader().equals("Limit(Covered)")) {
							big3coLimitXXrefs = packageVersionRepository
									.findAllLimitsForBenefitOptionPkgVerBig3Co(
											benfitOptionId, pkgVerBig3CoIds,
											excelViewInfo.getTreeShape()
													.getTreeShape1up());
						}

					}
				}
			}
			if (pkgVerBig3CoIds != null && pkgVerBig3CoIds.size() > 0) {
				if (excelViewInfos != null) {
					for (ExcelViewInfo excelViewInfo : excelViewInfos) {
						if (excelViewInfo.getHeader()
								.equals("Message(Covered)")) {
							big3coMessageXXrefs = packageVersionRepository
									.findAllMessagessForBenefitOptionPkgVerBig3Co(
											benfitOptionId, pkgVerBig3CoIds,
											excelViewInfo.getTreeShape()
													.getTreeShape1up());
						}
					}
				}
			}

		} catch (PersistenceException e) {
			throw new PersistenceException();
		}

		int limitTreeShape = 0;
		int messageTreeShape = 0;
		if (excelViewInfos != null) {
			for (ExcelViewInfo excelViewInfo : excelViewInfos) {
				if (excelViewInfo.getHeader().equals("Limit(Covered)")) {
					limitTreeShape = excelViewInfo.getTreeShape()
							.getTreeShape1up();

				}
				if (excelViewInfo.getHeader().equals("Message(Covered)")) {
					messageTreeShape = excelViewInfo.getTreeShape()
							.getTreeShape1up();

				}
			}
		}
		List<BenefitSelectionTierdataData> benSelDataList = new ArrayList<BenefitSelectionTierdataData>();

		for (BenefitOptionTierdata benefitOptionTierdata : benSelList) {

			List<DeductibleData> deductibleDatas = new ArrayList<DeductibleData>();

			BenefitSelectionTierdataData data = new BenefitSelectionTierdataData();

			// data.setBenefitOption1up(benefitOptionTierdata.getBenefitOption()
			// .getPkgVerBenefitOption1up());
			List<MatrixShapeBig3COLimitXXrefData> big3coLimitXXrefDatas = null;
			List<MatrixShapeBig3COMessageXXrefData> big3coMessageXXrefDatas = null;

			if (benefitOptionTierdata.getPkgVerBig3CO() != null) {
				big3coLimitXXrefDatas = this
						.getAllEnableLimitValForBenefitForEachBenefitOption(
								benefitOptionTierdata.getPkgVerBig3CO()
										.getPkgVerBig3CO1up(),
								benefitOptionTierdata.getBenefitOption()
										.getPkgVerBenefitOption1up(),
								limitTreeShape);
			}

			if (benefitOptionTierdata.getPkgVerBig3CO() != null) {
				big3coMessageXXrefDatas = this
						.getAllEnableMessageValForBenefitForEachBenefitOption(
								benefitOptionTierdata.getPkgVerBig3CO()
										.getPkgVerBig3CO1up(),
								benefitOptionTierdata.getBenefitOption()
										.getPkgVerBenefitOption1up(),
								messageTreeShape);
			}

			// data.setId(benefitOptionTierdata.getBenefitOptionTierdata1up());

			if (big3coMessageXXrefDatas != null) {

				data.setMessageData(big3coMessageXXrefDatas);

			}
			if (big3coLimitXXrefDatas != null) {

				data.setLimitData(big3coLimitXXrefDatas);

			}

			if (benefitOptionTierdata.getPkgVerBig3CO() != null) {
				data.setIsCovered(benefitOptionTierdata.getPkgVerBig3CO()
						.getCoveredFlag());
			}
			if (benefitOptionTierdata.getPkgVerBig3CO() != null) {
				if (benefitOptionTierdata.getPkgVerBig3CO()
						.getPkgMasterListForBig3CO() != null) {
					if (benefitOptionTierdata.getPkgVerBig3CO()
							.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition() != null) {
						MasterListServiceDefinitionData definitionData = new MasterListServiceDefinitionData();

						if (benefitOptionTierdata.getPkgVerBig3CO()
								.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat1() != null) {
							MasterListStringValData category1 = new MasterListStringValData();
							category1
									.setMasterListStringVal1up(benefitOptionTierdata
											.getPkgVerBig3CO()
											.getPkgMasterListForBig3CO()
											.getMasterListServiceDefinition()
											.getMasterListStringValCat1()
											.getMasterListStringVal1up());
							category1.setStringValue(benefitOptionTierdata
									.getPkgVerBig3CO()
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat1()
									.getStringValue());
							category1.setAbbrev(benefitOptionTierdata
									.getPkgVerBig3CO()
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat1().getAbbrev());
							definitionData.setCategory1(category1);

						}

						if (benefitOptionTierdata.getPkgVerBig3CO()
								.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat2() != null) {
							MasterListStringValData category2 = new MasterListStringValData();
							category2
									.setMasterListStringVal1up(benefitOptionTierdata
											.getPkgVerBig3CO()
											.getPkgMasterListForBig3CO()
											.getMasterListServiceDefinition()
											.getMasterListStringValCat2()
											.getMasterListStringVal1up());
							category2.setStringValue(benefitOptionTierdata
									.getPkgVerBig3CO()
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat2()
									.getStringValue());
							category2.setAbbrev(benefitOptionTierdata
									.getPkgVerBig3CO()
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat2().getAbbrev());
							definitionData.setCategory2(category2);
						}

						if (benefitOptionTierdata.getPkgVerBig3CO()
								.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat3() != null) {
							MasterListStringValData category3 = new MasterListStringValData();
							category3
									.setMasterListStringVal1up(benefitOptionTierdata
											.getPkgVerBig3CO()
											.getPkgMasterListForBig3CO()
											.getMasterListServiceDefinition()
											.getMasterListStringValCat3()
											.getMasterListStringVal1up());
							category3.setStringValue(benefitOptionTierdata
									.getPkgVerBig3CO()
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat3()
									.getStringValue());
							category3.setAbbrev(benefitOptionTierdata
									.getPkgVerBig3CO()
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValCat3().getAbbrev());
							definitionData.setCategory3(category3);
						}

						if (benefitOptionTierdata.getPkgVerBig3CO()
								.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValPostString() != null) {
							MasterListStringValData placeOfService = new MasterListStringValData();
							placeOfService
									.setMasterListStringVal1up(benefitOptionTierdata
											.getPkgVerBig3CO()
											.getPkgMasterListForBig3CO()
											.getMasterListServiceDefinition()
											.getMasterListStringValPostString()
											.getMasterListStringVal1up());
							placeOfService.setStringValue(benefitOptionTierdata
									.getPkgVerBig3CO()
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValPostString()
									.getStringValue());
							placeOfService.setAbbrev(benefitOptionTierdata
									.getPkgVerBig3CO()
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListStringValPostString()
									.getAbbrev());
							definitionData.setPlaceOfService(placeOfService);
						}

						data.setMasterListServiceDefinitionData(definitionData);

					}

				}
			}

			if (shapeValueBenefitOptionForBig3COs != null) {
				for (ShapeValueBenefitOptionForBig3CO shapeValueBenefitOptionForBig3CO : shapeValueBenefitOptionForBig3COs) {

					if (benefitOptionTierdata.getPkgVerBig3CO()
							.getPkgVerBig3CO1up() == shapeValueBenefitOptionForBig3CO
							.getShapeValueBenefitOptionForBig3COId()
							.getPkgVerBig3CO().getPkgVerBig3CO1up()) {

						for (ExcelViewInfo excelViewInfo : excelViewInfos) {
							if (excelViewInfo.getHeader().equals(
									"Apply Deductible")
									&& shapeValueBenefitOptionForBig3CO
											.getShapeValueBenefitOptionForBig3COId()
											.getTreeShape().getTreeShape1up() == excelViewInfo
											.getTreeShape().getTreeShape1up()) {

								data.setApplyDeductible(shapeValueBenefitOptionForBig3CO
										.getIfYNTestAnswerYesFlag());

							}

							if (excelViewInfo.getColumnType().equals(
									"ApplyDedYes")
									&& shapeValueBenefitOptionForBig3CO
											.getShapeValueBenefitOptionForBig3COId()
											.getTreeShape().getTreeShape1up() == excelViewInfo
											.getTreeShape().getTreeShape1up()) {
								DeductibleData deductibleData = new DeductibleData();

								deductibleData.setName(excelViewInfo
										.getHeader());
								MasterListIntValData indDudect = new MasterListIntValData();
								indDudect
										.setMasterListIntVal1up(shapeValueBenefitOptionForBig3CO
												.getMasterListIntVal()
												.getMasterListIntVal1up());
								indDudect
										.setIntAsDecimalValue(shapeValueBenefitOptionForBig3CO
												.getMasterListIntVal()
												.getIntAsDecimalValue());

								indDudect
										.setAbbrev(shapeValueBenefitOptionForBig3CO
												.getMasterListIntVal()
												.getAbbrev());

								deductibleData.setValue(indDudect);

								deductibleDatas.add(deductibleData);

							}

							// if (excelViewInfo.getHeader().equals("FAM")
							// && shapeValueBenefitOptionForBig3CO
							// .getShapeValueBenefitOptionForBig3COId()
							// .getTreeShape().getTreeShape1up() ==
							// excelViewInfo
							// .getTreeShape().getTreeShape1up()) {
							//
							// MasterListIntValData famDudect = new
							// MasterListIntValData();
							// famDudect
							// .setMasterListIntVal1up(shapeValueBenefitOptionForBig3CO
							// .getMasterListIntVal()
							// .getMasterListIntVal1up());
							// famDudect
							// .setIntAsDecimalValue(shapeValueBenefitOptionForBig3CO
							// .getMasterListIntVal()
							// .getIntAsDecimalValue());
							//
							// famDudect
							// .setAbbrev(shapeValueBenefitOptionForBig3CO
							// .getMasterListIntVal()
							// .getAbbrev());
							//
							// data.setApplyFamDeductible(famDudect);
							//
							// }

							if (excelViewInfo.getHeader().equals(
									"Apply Deductible-No")
									&& shapeValueBenefitOptionForBig3CO
											.getShapeValueBenefitOptionForBig3COId()
											.getTreeShape().getTreeShape1up() == excelViewInfo
											.getTreeShape().getTreeShape1up()) {

								MasterListStringValData noDudect = new MasterListStringValData();
								noDudect.setMasterListStringVal1up(shapeValueBenefitOptionForBig3CO
										.getMasterListStringVal()
										.getMasterListStringVal1up());
								noDudect.setStringValue(shapeValueBenefitOptionForBig3CO
										.getMasterListStringVal()
										.getStringValue());

								noDudect.setAbbrev(shapeValueBenefitOptionForBig3CO
										.getMasterListStringVal().getAbbrev());

								data.setApplyDeductibleNo(noDudect);

							}

						}

					}

				}
				if (benefitOptionTierdata.getAllowedAmt() != null) {
					if (benefitOptionTierdata.getAllowedAmt()
							.getMasterListIntVal1up() != null) {
						MasterListIntValData allowedAmount = new MasterListIntValData();
						allowedAmount
								.setMasterListIntVal1up(benefitOptionTierdata
										.getAllowedAmt()
										.getMasterListIntVal1up());
						allowedAmount
								.setIntAsDecimalValue(benefitOptionTierdata
										.getAllowedAmt().getIntAsDecimalValue());
						allowedAmount.setAbbrev(benefitOptionTierdata
								.getAllowedAmt().getAbbrev());
						data.setAllowedAmountData(allowedAmount);
					}

				}
				if (benefitOptionTierdata.getAllowedCtr() != null) {
					if (benefitOptionTierdata.getAllowedCtr()
							.getMasterListIntVal1up() != null) {
						MasterListIntValData allowedCtrl = new MasterListIntValData();
						allowedCtrl
								.setMasterListIntVal1up(benefitOptionTierdata
										.getAllowedCtr()
										.getMasterListIntVal1up());
						allowedCtrl.setIntAsDecimalValue(benefitOptionTierdata
								.getAllowedAmt().getIntAsDecimalValue());
						allowedCtrl.setAbbrev(benefitOptionTierdata
								.getAllowedAmt().getAbbrev());
						data.setAllowedCtrData(allowedCtrl);

					}

				}

				if (benefitOptionTierdata.getApplyCoinsurance() != null) {
					data.setApplyCoinsurance(benefitOptionTierdata
							.getApplyCoinsurance());

				}
				if (benefitOptionTierdata.getCoinsuranceNoVal() != null) {

					MasterListStringValData coInsurenceNoVal = new MasterListStringValData();
					coInsurenceNoVal
							.setMasterListStringVal1up(benefitOptionTierdata
									.getCoinsuranceNoVal()
									.getMasterListStringVal1up());

					coInsurenceNoVal.setStringValue(benefitOptionTierdata
							.getCoinsuranceNoVal().getStringValue());

					coInsurenceNoVal.setAbbrev(benefitOptionTierdata
							.getCoinsuranceNoVal().getAbbrev());

					data.setCoinsuranceNoValData(coInsurenceNoVal);

				}
				if (benefitOptionTierdata.getCoinsuranceYesVal() != null) {

					MasterListIntValData coInsurenceYesVal = new MasterListIntValData();
					coInsurenceYesVal
							.setMasterListIntVal1up(benefitOptionTierdata
									.getCoinsuranceYesVal()
									.getMasterListIntVal1up());

					coInsurenceYesVal
							.setIntAsDecimalValue(benefitOptionTierdata
									.getCoinsuranceYesVal()
									.getIntAsDecimalValue());

					coInsurenceYesVal.setAbbrev(benefitOptionTierdata
							.getCoinsuranceYesVal().getAbbrev());

					data.setCoinsuranceYesValData(coInsurenceYesVal);

				}

				if (benefitOptionTierdata.getCopayVal() != null) {

					MasterListIntValData coPayVal = new MasterListIntValData();
					coPayVal.setMasterListIntVal1up(benefitOptionTierdata
							.getCopayVal().getMasterListIntVal1up());

					coPayVal.setIntAsDecimalValue(benefitOptionTierdata
							.getCopayVal().getIntAsDecimalValue());

					coPayVal.setAbbrev(benefitOptionTierdata.getCopayVal()
							.getAbbrev());
					data.setCopayValData(coPayVal);
				}

			} else {
				for (ShapeValueBig3CO shapeValueBig3CO : shapeValueBig3COs) {

					if (benefitOptionTierdata.getPkgVerBig3CO()
							.getPkgVerBig3CO1up() == shapeValueBig3CO
							.getShapeValueBig3COId().getPkgVerBig3CO()
							.getPkgVerBig3CO1up()) {

						setCoInsurenceAndDeductibleDetails(excelViewInfos,
								deductibleDatas, data, shapeValueBig3CO);

					}

				}

			}

			if (benefitOptionTierdata.getPkgVerBig3CO() != null) {
				if (benefitOptionTierdata.getPkgVerBig3CO()
						.getPkgMasterListForBig3CO() != null) {
					if (benefitOptionTierdata.getPkgVerBig3CO()
							.getPkgMasterListForBig3CO()
							.getMasterListStringVal() != null) {

						MasterListStringValData benefitRule = new MasterListStringValData();
						benefitRule
								.setMasterListStringVal1up(benefitOptionTierdata
										.getPkgVerBig3CO()
										.getPkgMasterListForBig3CO()
										.getMasterListStringVal()
										.getMasterListStringVal1up());

						benefitRule.setStringValue(benefitOptionTierdata
								.getPkgVerBig3CO().getPkgMasterListForBig3CO()
								.getMasterListStringVal().getStringValue());

						benefitRule.setAbbrev(benefitOptionTierdata
								.getPkgVerBig3CO().getPkgMasterListForBig3CO()
								.getMasterListStringVal().getAbbrev());

						data.setMasterListStringValData(benefitRule);

					}
				}
			}

			int limitCount = 0;
			for (LimitShapeBenefitOptionOverride big3coLimitXXref : big3coLimitXXrefs) {

				if (benefitOptionTierdata.getPkgVerBig3CO()
						.getPkgVerBig3CO1up() == big3coLimitXXref
						.getLimitShapeBenefitOptionOverrideId()
						.getMatrixShapeBig3COLimitXXrefId()
						.getMatrixShapeBig3COXrefId().getPkgVerBig3CO()
						.getPkgVerBig3CO1up()) {
					limitCount++;

				}

			}
			data.setLimitCount(limitCount);
			int messageCount = 0;
			for (MessageShapeBenefitOptionOverride big3coMessageXXref : big3coMessageXXrefs) {

				if (benefitOptionTierdata.getPkgVerBig3CO()
						.getPkgVerBig3CO1up() == big3coMessageXXref
						.getMessageShapeBenefitOptionOverrideId()
						.getMatrixShapeBig3COMessageXXrefId()
						.getMatrixShapeBig3COXrefId().getPkgVerBig3CO()
						.getPkgVerBig3CO1up()) {
					messageCount++;

				}

			}

			data.setMessageCount(messageCount);

			// data.setCreateBy(benefitOptionTierdata.getCreateBy());
			//
			// data.setCreateDt(benefitOptionTierdata.getCreateDt());
			//
			// data.setUpdateBy(benefitOptionTierdata.getUpdateBy());
			//
			// data.setUpdateDt(benefitOptionTierdata.getUpdateDt());
			//
			// data.seteBSInstance1up(benefitOptionTierdata.geteBSInstance1up());

			if (benefitOptionTierdata.getPkgVerBig3CO() != null) {
				data.setIsCovered(benefitOptionTierdata.getPkgVerBig3CO()
						.getCoveredFlag());
			}
			if (benefitOptionTierdata.getPkgVerBig3CO().getTreatAs100PctFlag() != null) {

				data.setTreatAs100(benefitOptionTierdata.getPkgVerBig3CO()
						.getTreatAs100PctFlag());

			}
			data.setTierNo(benefitOptionTierdata.getTierNo());
			if (benefitOptionTierdata.getPkgVerBig3CO() != null) {
				data.setPkgVerBig3CO1up(benefitOptionTierdata.getPkgVerBig3CO()
						.getPkgVerBig3CO1up());
			}

			// if (benefitOptionTierdata.getPkgVerInstanceTree() != null) {
			//
			// PkgVerInstanceTreeData commonBenefit = new
			// PkgVerInstanceTreeData();
			// commonBenefit.setPkgVerInstanceTree1up(benefitOptionTierdata
			// .getPkgVerInstanceTree().getPkgVerInstanceTree1up());
			// commonBenefit.setName(benefitOptionTierdata
			// .getPkgVerInstanceTree().getName());
			// commonBenefit.setDescription(benefitOptionTierdata
			// .getPkgVerInstanceTree().getDescription());
			// data.setPkgVerInstanceTreeData(commonBenefit);
			//
			// }

			data.setDeductibleData(deductibleDatas);

			data.setTierNo(benefitOptionTierdata.getTierNo());

			benSelDataList.add(data);
		}

		PackageVersionContainer<BenefitSelectionTierdataData> result = new PackageVersionContainer<BenefitSelectionTierdataData>();

		result.setData(benSelDataList);
		result.setPagesCount(benSelList.getTotalPages());
		result.setTotalItems(benSelList.getTotalElements());

		return result;

	}

	private void setCoInsurenceAndDeductibleDetails(
			List<ExcelViewInfo> excelViewInfos,
			List<DeductibleData> deductibleDatas,
			BenefitSelectionTierdataData data, ShapeValueBig3CO shapeValueBig3CO) {
		for (ExcelViewInfo excelViewInfo : excelViewInfos) {
			if (excelViewInfo.getHeader().equals("Apply Deductible")
					&& shapeValueBig3CO.getShapeValueBig3COId().getTreeShape()
							.getTreeShape1up() == excelViewInfo.getTreeShape()
							.getTreeShape1up()) {

				data.setApplyDeductible(shapeValueBig3CO
						.getIfYNTestAnswerYesFlag());

			}

			if (excelViewInfo.getColumnType().equals("ApplyDedYes")
					&& shapeValueBig3CO.getShapeValueBig3COId().getTreeShape()
							.getTreeShape1up() == excelViewInfo.getTreeShape()
							.getTreeShape1up()) {

				DeductibleData deductibleData = new DeductibleData();

				deductibleData.setName(excelViewInfo.getHeader());

				MasterListIntValData indDudect = new MasterListIntValData();
				indDudect.setMasterListIntVal1up(shapeValueBig3CO
						.getMasterListIntVal().getMasterListIntVal1up());
				indDudect.setIntAsDecimalValue(shapeValueBig3CO
						.getMasterListIntVal().getIntAsDecimalValue());

				indDudect.setAbbrev(shapeValueBig3CO.getMasterListIntVal()
						.getAbbrev());

				deductibleData.setValue(indDudect);

				deductibleDatas.add(deductibleData);

			}

			// if (excelViewInfo.getHeader().equals("FAM")
			// && shapeValueBig3CO.getShapeValueBig3COId()
			// .getTreeShape().getTreeShape1up() ==
			// excelViewInfo
			// .getTreeShape().getTreeShape1up()) {
			//
			// MasterListIntValData famDudect = new
			// MasterListIntValData();
			// famDudect
			// .setMasterListIntVal1up(shapeValueBig3CO
			// .getMasterListIntVal()
			// .getMasterListIntVal1up());
			// famDudect.setIntAsDecimalValue(shapeValueBig3CO
			// .getMasterListIntVal()
			// .getIntAsDecimalValue());
			//
			// famDudect.setAbbrev(shapeValueBig3CO
			// .getMasterListIntVal().getAbbrev());
			//
			// data.setApplyFamDeductible(famDudect);
			//
			// }

			if (excelViewInfo.getHeader().equals("Apply Deductible-No")
					&& shapeValueBig3CO.getShapeValueBig3COId().getTreeShape()
							.getTreeShape1up() == excelViewInfo.getTreeShape()
							.getTreeShape1up()) {

				MasterListStringValData noDudect = new MasterListStringValData();
				noDudect.setMasterListStringVal1up(shapeValueBig3CO
						.getMasterListStringVal().getMasterListStringVal1up());
				noDudect.setStringValue(shapeValueBig3CO
						.getMasterListStringVal().getStringValue());

				noDudect.setAbbrev(shapeValueBig3CO.getMasterListStringVal()
						.getAbbrev());

				data.setApplyDeductibleNo(noDudect);

			}

			if (excelViewInfo.getHeader().equals("Apply Coinsurance")
					&& shapeValueBig3CO.getShapeValueBig3COId().getTreeShape()
							.getTreeShape1up() == excelViewInfo.getTreeShape()
							.getTreeShape1up()) {

				data.setApplyCoinsurance(shapeValueBig3CO
						.getIfYNTestAnswerYesFlag());

			}

			if (excelViewInfo.getHeader().equals("Apply Coinsurance-Yes")
					&& shapeValueBig3CO.getShapeValueBig3COId().getTreeShape()
							.getTreeShape1up() == excelViewInfo.getTreeShape()
							.getTreeShape1up()) {

				MasterListIntValData yesCoInsure = new MasterListIntValData();
				yesCoInsure.setMasterListIntVal1up(shapeValueBig3CO
						.getMasterListIntVal().getMasterListIntVal1up());
				yesCoInsure.setIntAsDecimalValue(shapeValueBig3CO
						.getMasterListIntVal().getIntAsDecimalValue());

				yesCoInsure.setAbbrev(shapeValueBig3CO.getMasterListIntVal()
						.getAbbrev());

				data.setCoinsuranceYesValData(yesCoInsure);

			}

			if (excelViewInfo.getHeader().equals("Apply Coinsurance-No")
					&& shapeValueBig3CO.getShapeValueBig3COId().getTreeShape()
							.getTreeShape1up() == excelViewInfo.getTreeShape()
							.getTreeShape1up()) {

				MasterListStringValData noCoInsure = new MasterListStringValData();
				noCoInsure.setMasterListStringVal1up(shapeValueBig3CO
						.getMasterListStringVal().getMasterListStringVal1up());
				noCoInsure.setStringValue(shapeValueBig3CO
						.getMasterListStringVal().getStringValue());

				noCoInsure.setAbbrev(shapeValueBig3CO.getMasterListStringVal()
						.getAbbrev());

				data.setCoinsuranceNoValData(noCoInsure);

			}
			if (excelViewInfo.getHeader().equals("Copay")
					&& shapeValueBig3CO.getShapeValueBig3COId().getTreeShape()
							.getTreeShape1up() == excelViewInfo.getTreeShape()
							.getTreeShape1up()) {

				MasterListIntValData coPayVal = new MasterListIntValData();
				coPayVal.setMasterListIntVal1up(shapeValueBig3CO
						.getMasterListIntVal().getMasterListIntVal1up());

				coPayVal.setIntAsDecimalValue(shapeValueBig3CO
						.getMasterListIntVal().getIntAsDecimalValue());

				coPayVal.setAbbrev(shapeValueBig3CO.getMasterListIntVal()
						.getAbbrev());
				data.setCopayValData(coPayVal);

			}

			if (excelViewInfo.getHeader().equals("Allowed Amt")
					&& shapeValueBig3CO.getShapeValueBig3COId().getTreeShape()
							.getTreeShape1up() == excelViewInfo.getTreeShape()
							.getTreeShape1up()) {
				MasterListIntValData allowedAmount = new MasterListIntValData();
				allowedAmount.setMasterListIntVal1up(shapeValueBig3CO
						.getMasterListIntVal().getMasterListIntVal1up());
				allowedAmount.setIntAsDecimalValue(shapeValueBig3CO
						.getMasterListIntVal().getIntAsDecimalValue());
				allowedAmount.setAbbrev(shapeValueBig3CO.getMasterListIntVal()
						.getAbbrev());
				data.setAllowedAmountData(allowedAmount);

			}
			if (excelViewInfo.getHeader().equals("Allowed Ctr")
					&& shapeValueBig3CO.getShapeValueBig3COId().getTreeShape()
							.getTreeShape1up() == excelViewInfo.getTreeShape()
							.getTreeShape1up()) {
				MasterListIntValData allowedCtrl = new MasterListIntValData();
				allowedCtrl.setMasterListIntVal1up(shapeValueBig3CO
						.getMasterListIntVal().getMasterListIntVal1up());
				allowedCtrl.setIntAsDecimalValue(shapeValueBig3CO
						.getMasterListIntVal().getIntAsDecimalValue());
				allowedCtrl.setAbbrev(shapeValueBig3CO.getMasterListIntVal()
						.getAbbrev());
				data.setAllowedCtrData(allowedCtrl);

			}

		}
	}

	@Transactional(readOnly = true)
	public BenefitSelectionTierdataData getDefaultBenefitSelectionForBenefitOption(
			int versionInstance, int benefitOptionId)
			throws DataAccessException {

		BenefitOptionTierdata defaultData = null;
		List<ExcelViewInfo> excelViewInfos = new ArrayList<ExcelViewInfo>();
		List<Integer> treeShapeIds = new ArrayList<Integer>();
		List<ShapeValueBenefitOptionForBig3CO> shapeValueBenefitOptionForBig3COs = null;
		List<ShapeValueTreeInstance> defaultInstances = new ArrayList<ShapeValueTreeInstance>();
		List<LimitShapeBenefitOptionOverride> big3coLimitXXrefs = new ArrayList<LimitShapeBenefitOptionOverride>();
		List<MessageShapeBenefitOptionOverride> big3coMessageXXrefs = new ArrayList<MessageShapeBenefitOptionOverride>();
		try {

			defaultData = packageVersionRepository
					.findDefaultBenefitSelectionForBenefitOption(
							versionInstance, benefitOptionId);

			excelViewInfos = packageVersionRepository
					.findAllTreeShapeForCommonBenefit(versionInstance);
			if (excelViewInfos != null) {
				for (ExcelViewInfo excelViewInfo : excelViewInfos) {
					treeShapeIds.add(excelViewInfo.getTreeShape()
							.getTreeShape1up());
				}
			}

			if (treeShapeIds != null && treeShapeIds.size() > 0) {
				shapeValueBenefitOptionForBig3COs = packageVersionRepository
						.findBenefitOptionDefaultDeductibleValueforPkgVerBig3Co(
								versionInstance, benefitOptionId,
								defaultData.getPkgVerBig3CO()
										.getPkgVerBig3CO1up(), treeShapeIds);
				if (shapeValueBenefitOptionForBig3COs == null) {
					defaultInstances = packageVersionRepository
							.findDefaultShapeInstanceTreeValue(versionInstance);
				}
			}

			if (excelViewInfos != null) {
				for (ExcelViewInfo excelViewInfo : excelViewInfos) {
					if (excelViewInfo.getHeader().equals("Limit(Covered)")) {
						big3coLimitXXrefs = packageVersionRepository
								.findDefaultLimitsForBenefitOptionPkgVerBig3Co(
										benefitOptionId, defaultData
												.getPkgVerBig3CO()
												.getPkgVerBig3CO1up(),
										excelViewInfo.getTreeShape()
												.getTreeShape1up());
					}

				}
			}

			if (excelViewInfos != null) {
				for (ExcelViewInfo excelViewInfo : excelViewInfos) {
					if (excelViewInfo.getHeader().equals("Message(Covered)")) {
						big3coMessageXXrefs = packageVersionRepository
								.findDefaultMessagessForBenefitOptionPkgVerBig3Co(
										benefitOptionId, defaultData
												.getPkgVerBig3CO()
												.getPkgVerBig3CO1up(),
										excelViewInfo.getTreeShape()
												.getTreeShape1up());
					}
				}
			}

		} catch (PersistenceException e) {
			throw new PersistenceException();
		}

		int limitTreeShape = 0;
		int messageTreeShape = 0;

		for (ExcelViewInfo excelViewInfo : excelViewInfos) {
			if (excelViewInfo.getHeader().equals("Limit(Covered)")) {
				limitTreeShape = excelViewInfo.getTreeShape().getTreeShape1up();

			}
			if (excelViewInfo.getHeader().equals("Message(Covered)")) {
				messageTreeShape = excelViewInfo.getTreeShape()
						.getTreeShape1up();

			}
		}

		BenefitSelectionTierdataData data = new BenefitSelectionTierdataData();

		// data.setBenefitOption1up(defaultData.getBenefitOption()
		// .getPkgVerBenefitOption1up());
		List<DeductibleData> deductibleDatas = new ArrayList<DeductibleData>();
		List<MatrixShapeBig3COLimitXXrefData> big3coLimitXXrefDatas = null;
		List<MatrixShapeBig3COMessageXXrefData> big3coMessageXXrefDatas = null;

		if (defaultData.getPkgVerBig3CO() != null) {
			big3coLimitXXrefDatas = this
					.getAllEnableLimitValForBenefitForEachBenefitOption(
							defaultData.getPkgVerBig3CO().getPkgVerBig3CO1up(),
							defaultData.getBenefitOption()
									.getPkgVerBenefitOption1up(),
							limitTreeShape);
		}

		if (defaultData.getPkgVerBig3CO() != null) {
			big3coMessageXXrefDatas = this
					.getAllEnableMessageValForBenefitForEachBenefitOption(
							defaultData.getPkgVerBig3CO().getPkgVerBig3CO1up(),
							defaultData.getBenefitOption()
									.getPkgVerBenefitOption1up(),
							messageTreeShape);
		}

		// data.setId(defaultData.getBenefitOptionTierdata1up());

		if (big3coMessageXXrefDatas != null) {

			data.setMessageData(big3coMessageXXrefDatas);

		}
		if (big3coLimitXXrefDatas != null) {

			data.setLimitData(big3coLimitXXrefDatas);

		}

		if (defaultData.getPkgVerBig3CO() != null) {
			data.setIsCovered(defaultData.getPkgVerBig3CO().getCoveredFlag());
		}
		if (defaultData.getPkgVerBig3CO() != null) {
			if (defaultData.getPkgVerBig3CO().getPkgMasterListForBig3CO() != null) {
				if (defaultData.getPkgVerBig3CO().getPkgMasterListForBig3CO()
						.getMasterListServiceDefinition() != null) {
					MasterListServiceDefinitionData definitionData = new MasterListServiceDefinitionData();

					if (defaultData.getPkgVerBig3CO()
							.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition()
							.getMasterListStringValCat1() != null) {
						MasterListStringValData category1 = new MasterListStringValData();
						category1.setMasterListStringVal1up(defaultData
								.getPkgVerBig3CO().getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat1()
								.getMasterListStringVal1up());
						category1.setStringValue(defaultData.getPkgVerBig3CO()
								.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat1().getStringValue());
						category1.setAbbrev(defaultData.getPkgVerBig3CO()
								.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat1().getAbbrev());
						definitionData.setCategory1(category1);

					}

					if (defaultData.getPkgVerBig3CO()
							.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition()
							.getMasterListStringValCat2() != null) {
						MasterListStringValData category2 = new MasterListStringValData();
						category2.setMasterListStringVal1up(defaultData
								.getPkgVerBig3CO().getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat2()
								.getMasterListStringVal1up());
						category2.setStringValue(defaultData.getPkgVerBig3CO()
								.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat2().getStringValue());
						category2.setAbbrev(defaultData.getPkgVerBig3CO()
								.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat2().getAbbrev());
						definitionData.setCategory2(category2);
					}

					if (defaultData.getPkgVerBig3CO()
							.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition()
							.getMasterListStringValCat3() != null) {
						MasterListStringValData category3 = new MasterListStringValData();
						category3.setMasterListStringVal1up(defaultData
								.getPkgVerBig3CO().getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat3()
								.getMasterListStringVal1up());
						category3.setStringValue(defaultData.getPkgVerBig3CO()
								.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat3().getStringValue());
						category3.setAbbrev(defaultData.getPkgVerBig3CO()
								.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValCat3().getAbbrev());
						definitionData.setCategory3(category3);
					}

					if (defaultData.getPkgVerBig3CO()
							.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition()
							.getMasterListStringValPostString() != null) {
						MasterListStringValData placeOfService = new MasterListStringValData();
						placeOfService.setMasterListStringVal1up(defaultData
								.getPkgVerBig3CO().getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValPostString()
								.getMasterListStringVal1up());
						placeOfService.setStringValue(defaultData
								.getPkgVerBig3CO().getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListStringValPostString()
								.getStringValue());
						placeOfService
								.setAbbrev(defaultData.getPkgVerBig3CO()
										.getPkgMasterListForBig3CO()
										.getMasterListServiceDefinition()
										.getMasterListStringValPostString()
										.getAbbrev());
						definitionData.setPlaceOfService(placeOfService);
					}

					data.setMasterListServiceDefinitionData(definitionData);

				}

			}
		}

		if (shapeValueBenefitOptionForBig3COs != null) {
			for (ShapeValueBenefitOptionForBig3CO shapeValueBenefitOptionForBig3CO : shapeValueBenefitOptionForBig3COs) {

				if (defaultData.getPkgVerBig3CO().getPkgVerBig3CO1up() == shapeValueBenefitOptionForBig3CO
						.getShapeValueBenefitOptionForBig3COId()
						.getPkgVerBig3CO().getPkgVerBig3CO1up()) {

					for (ExcelViewInfo excelViewInfo : excelViewInfos) {
						if (excelViewInfo.getHeader()
								.equals("Apply Deductible")
								&& shapeValueBenefitOptionForBig3CO
										.getShapeValueBenefitOptionForBig3COId()
										.getTreeShape().getTreeShape1up() == excelViewInfo
										.getTreeShape().getTreeShape1up()) {

							data.setApplyDeductible(shapeValueBenefitOptionForBig3CO
									.getIfYNTestAnswerYesFlag());

						}

						if (excelViewInfo.getColumnType().equals("ApplyDedYes")
								&& shapeValueBenefitOptionForBig3CO
										.getShapeValueBenefitOptionForBig3COId()
										.getTreeShape().getTreeShape1up() == excelViewInfo
										.getTreeShape().getTreeShape1up()) {

							DeductibleData deductibleData = new DeductibleData();

							deductibleData.setName(excelViewInfo.getHeader());

							MasterListIntValData indDudect = new MasterListIntValData();
							indDudect
									.setMasterListIntVal1up(shapeValueBenefitOptionForBig3CO
											.getMasterListIntVal()
											.getMasterListIntVal1up());
							indDudect
									.setIntAsDecimalValue(shapeValueBenefitOptionForBig3CO
											.getMasterListIntVal()
											.getIntAsDecimalValue());

							indDudect
									.setAbbrev(shapeValueBenefitOptionForBig3CO
											.getMasterListIntVal().getAbbrev());

							deductibleData.setValue(indDudect);

							deductibleDatas.add(deductibleData);

						}

						// if (excelViewInfo.getHeader().equals("FAM")
						// && shapeValueBenefitOptionForBig3CO
						// .getShapeValueBenefitOptionForBig3COId()
						// .getTreeShape().getTreeShape1up() == excelViewInfo
						// .getTreeShape().getTreeShape1up()) {
						//
						// MasterListIntValData famDudect = new
						// MasterListIntValData();
						// famDudect
						// .setMasterListIntVal1up(shapeValueBenefitOptionForBig3CO
						// .getMasterListIntVal()
						// .getMasterListIntVal1up());
						// famDudect
						// .setIntAsDecimalValue(shapeValueBenefitOptionForBig3CO
						// .getMasterListIntVal()
						// .getIntAsDecimalValue());
						//
						// famDudect
						// .setAbbrev(shapeValueBenefitOptionForBig3CO
						// .getMasterListIntVal().getAbbrev());
						//
						// data.setApplyFamDeductible(famDudect);
						//
						// }

						if (excelViewInfo.getHeader().equals(
								"Apply Deductible-No")
								&& shapeValueBenefitOptionForBig3CO
										.getShapeValueBenefitOptionForBig3COId()
										.getTreeShape().getTreeShape1up() == excelViewInfo
										.getTreeShape().getTreeShape1up()) {

							MasterListStringValData noDudect = new MasterListStringValData();
							noDudect.setMasterListStringVal1up(shapeValueBenefitOptionForBig3CO
									.getMasterListStringVal()
									.getMasterListStringVal1up());
							noDudect.setStringValue(shapeValueBenefitOptionForBig3CO
									.getMasterListStringVal().getStringValue());

							noDudect.setAbbrev(shapeValueBenefitOptionForBig3CO
									.getMasterListStringVal().getAbbrev());

							data.setApplyDeductibleNo(noDudect);

						}

					}

				}

			}
			if (defaultData.getAllowedAmt() != null) {
				if (defaultData.getAllowedAmt().getMasterListIntVal1up() != null) {
					MasterListIntValData allowedAmount = new MasterListIntValData();
					allowedAmount.setMasterListIntVal1up(defaultData
							.getAllowedAmt().getMasterListIntVal1up());
					allowedAmount.setIntAsDecimalValue(defaultData
							.getAllowedAmt().getIntAsDecimalValue());
					allowedAmount.setAbbrev(defaultData.getAllowedAmt()
							.getAbbrev());
					data.setAllowedAmountData(allowedAmount);
				}

			}
			if (defaultData.getAllowedCtr() != null) {
				if (defaultData.getAllowedCtr().getMasterListIntVal1up() != null) {
					MasterListIntValData allowedCtrl = new MasterListIntValData();
					allowedCtrl.setMasterListIntVal1up(defaultData
							.getAllowedCtr().getMasterListIntVal1up());
					allowedCtrl.setIntAsDecimalValue(defaultData
							.getAllowedAmt().getIntAsDecimalValue());
					allowedCtrl.setAbbrev(defaultData.getAllowedAmt()
							.getAbbrev());
					data.setAllowedCtrData(allowedCtrl);

				}

			}

			if (defaultData.getApplyCoinsurance() != null) {
				data.setApplyCoinsurance(defaultData.getApplyCoinsurance());

			}
			if (defaultData.getCoinsuranceNoVal() != null) {

				MasterListStringValData coInsurenceNoVal = new MasterListStringValData();
				coInsurenceNoVal.setMasterListStringVal1up(defaultData
						.getCoinsuranceNoVal().getMasterListStringVal1up());

				coInsurenceNoVal.setStringValue(defaultData
						.getCoinsuranceNoVal().getStringValue());

				coInsurenceNoVal.setAbbrev(defaultData.getCoinsuranceNoVal()
						.getAbbrev());

				data.setCoinsuranceNoValData(coInsurenceNoVal);

			}
			if (defaultData.getCoinsuranceYesVal() != null) {

				MasterListIntValData coInsurenceYesVal = new MasterListIntValData();
				coInsurenceYesVal.setMasterListIntVal1up(defaultData
						.getCoinsuranceYesVal().getMasterListIntVal1up());

				coInsurenceYesVal.setIntAsDecimalValue(defaultData
						.getCoinsuranceYesVal().getIntAsDecimalValue());

				coInsurenceYesVal.setAbbrev(defaultData.getCoinsuranceYesVal()
						.getAbbrev());

				data.setCoinsuranceYesValData(coInsurenceYesVal);

			}

			if (defaultData.getCopayVal() != null) {

				MasterListIntValData coPayVal = new MasterListIntValData();
				coPayVal.setMasterListIntVal1up(defaultData.getCopayVal()
						.getMasterListIntVal1up());

				coPayVal.setIntAsDecimalValue(defaultData.getCopayVal()
						.getIntAsDecimalValue());

				coPayVal.setAbbrev(defaultData.getCopayVal().getAbbrev());
				data.setCopayValData(coPayVal);
			}
		} else {
			for (ShapeValueTreeInstance defaultInstance : defaultInstances) {
				for (ExcelViewInfo excelViewInfo : excelViewInfos) {
					if (excelViewInfo.getHeader().equals("Apply Deductible")
							&& defaultInstance.getShapeValueTreeInstanceId()
									.getTreeShape().getTreeShape1up() == excelViewInfo
									.getTreeShape().getTreeShape1up()) {

						data.setApplyDeductible(defaultInstance
								.getIfYNTestAnswerYesFlag());

					}

					if (excelViewInfo.getColumnType().equals("ApplyDedYes")
							&& defaultInstance.getShapeValueTreeInstanceId()
									.getTreeShape().getTreeShape1up() == excelViewInfo
									.getTreeShape().getTreeShape1up()) {

						DeductibleData deductibleData = new DeductibleData();

						deductibleData.setName(excelViewInfo.getHeader());

						MasterListIntValData indDudect = new MasterListIntValData();
						indDudect
								.setMasterListIntVal1up(defaultInstance
										.getMasterListIntVal()
										.getMasterListIntVal1up());
						indDudect.setIntAsDecimalValue(defaultInstance
								.getMasterListIntVal().getIntAsDecimalValue());

						indDudect.setAbbrev(defaultInstance
								.getMasterListIntVal().getAbbrev());

						deductibleData.setValue(indDudect);

						deductibleDatas.add(deductibleData);

					}

					// if (excelViewInfo.getHeader().equals("FAM")
					// && defaultInstance.getShapeValueTreeInstanceId()
					// .getTreeShape().getTreeShape1up() == excelViewInfo
					// .getTreeShape().getTreeShape1up()) {
					//
					// MasterListIntValData famDudect = new
					// MasterListIntValData();
					// famDudect
					// .setMasterListIntVal1up(defaultInstance
					// .getMasterListIntVal()
					// .getMasterListIntVal1up());
					// famDudect.setIntAsDecimalValue(defaultInstance
					// .getMasterListIntVal().getIntAsDecimalValue());
					//
					// famDudect.setAbbrev(defaultInstance
					// .getMasterListIntVal().getAbbrev());
					//
					// data.setApplyFamDeductible(famDudect);
					//
					// }

					if (excelViewInfo.getHeader().equals("Apply Deductible-No")
							&& defaultInstance.getShapeValueTreeInstanceId()
									.getTreeShape().getTreeShape1up() == excelViewInfo
									.getTreeShape().getTreeShape1up()) {

						MasterListStringValData noDudect = new MasterListStringValData();
						noDudect.setMasterListStringVal1up(defaultInstance
								.getMasterListStringVal()
								.getMasterListStringVal1up());
						noDudect.setStringValue(defaultInstance
								.getMasterListStringVal().getStringValue());

						noDudect.setAbbrev(defaultInstance
								.getMasterListStringVal().getAbbrev());

						data.setApplyDeductibleNo(noDudect);

					}

					if (excelViewInfo.getHeader().equals("Apply Coinsurance")
							&& defaultInstance.getShapeValueTreeInstanceId()
									.getTreeShape().getTreeShape1up() == excelViewInfo
									.getTreeShape().getTreeShape1up()) {

						data.setApplyCoinsurance(defaultInstance
								.getIfYNTestAnswerYesFlag());

					}

					if (excelViewInfo.getHeader().equals(
							"Apply Coinsurance-Yes")
							&& defaultInstance.getShapeValueTreeInstanceId()
									.getTreeShape().getTreeShape1up() == excelViewInfo
									.getTreeShape().getTreeShape1up()) {

						MasterListIntValData yesCoInsure = new MasterListIntValData();
						yesCoInsure
								.setMasterListIntVal1up(defaultInstance
										.getMasterListIntVal()
										.getMasterListIntVal1up());
						yesCoInsure.setIntAsDecimalValue(defaultInstance
								.getMasterListIntVal().getIntAsDecimalValue());

						yesCoInsure.setAbbrev(defaultInstance
								.getMasterListIntVal().getAbbrev());

						data.setCoinsuranceYesValData(yesCoInsure);

					}

					if (excelViewInfo.getHeader()
							.equals("Apply Coinsurance-No")
							&& defaultInstance.getShapeValueTreeInstanceId()
									.getTreeShape().getTreeShape1up() == excelViewInfo
									.getTreeShape().getTreeShape1up()) {

						MasterListStringValData noCoInsure = new MasterListStringValData();
						noCoInsure.setMasterListStringVal1up(defaultInstance
								.getMasterListStringVal()
								.getMasterListStringVal1up());
						noCoInsure.setStringValue(defaultInstance
								.getMasterListStringVal().getStringValue());

						noCoInsure.setAbbrev(defaultInstance
								.getMasterListStringVal().getAbbrev());

						data.setCoinsuranceNoValData(noCoInsure);

					}
					if (excelViewInfo.getHeader().equals("Copay")
							&& defaultInstance.getShapeValueTreeInstanceId()
									.getTreeShape().getTreeShape1up() == excelViewInfo
									.getTreeShape().getTreeShape1up()) {

						MasterListIntValData coPayVal = new MasterListIntValData();
						coPayVal.setMasterListIntVal1up(defaultInstance
								.getMasterListIntVal().getMasterListIntVal1up());

						coPayVal.setIntAsDecimalValue(defaultInstance
								.getMasterListIntVal().getIntAsDecimalValue());

						coPayVal.setAbbrev(defaultInstance
								.getMasterListIntVal().getAbbrev());
						data.setCopayValData(coPayVal);

					}

					if (excelViewInfo.getHeader().equals("Allowed Amt")
							&& defaultInstance.getShapeValueTreeInstanceId()
									.getTreeShape().getTreeShape1up() == excelViewInfo
									.getTreeShape().getTreeShape1up()) {
						MasterListIntValData allowedAmount = new MasterListIntValData();
						allowedAmount
								.setMasterListIntVal1up(defaultInstance
										.getMasterListIntVal()
										.getMasterListIntVal1up());
						allowedAmount.setIntAsDecimalValue(defaultInstance
								.getMasterListIntVal().getIntAsDecimalValue());
						allowedAmount.setAbbrev(defaultInstance
								.getMasterListIntVal().getAbbrev());
						data.setAllowedAmountData(allowedAmount);

					}
					if (excelViewInfo.getHeader().equals("Allowed Ctr")
							&& defaultInstance.getShapeValueTreeInstanceId()
									.getTreeShape().getTreeShape1up() == excelViewInfo
									.getTreeShape().getTreeShape1up()) {
						MasterListIntValData allowedCtrl = new MasterListIntValData();
						allowedCtrl
								.setMasterListIntVal1up(defaultInstance
										.getMasterListIntVal()
										.getMasterListIntVal1up());
						allowedCtrl.setIntAsDecimalValue(defaultInstance
								.getMasterListIntVal().getIntAsDecimalValue());
						allowedCtrl.setAbbrev(defaultInstance
								.getMasterListIntVal().getAbbrev());
						data.setAllowedCtrData(allowedCtrl);

					}

				}

			}
		}

		if (defaultData.getApplyCoinsurance() != null) {
			data.setApplyCoinsurance(defaultData.getApplyCoinsurance());

		}

		if (defaultData.getPkgVerBig3CO() != null) {
			if (defaultData.getPkgVerBig3CO().getPkgMasterListForBig3CO() != null) {
				if (defaultData.getPkgVerBig3CO().getPkgMasterListForBig3CO()
						.getMasterListStringVal() != null) {

					MasterListStringValData benefitRule = new MasterListStringValData();
					benefitRule.setMasterListStringVal1up(defaultData
							.getPkgVerBig3CO().getPkgMasterListForBig3CO()
							.getMasterListStringVal()
							.getMasterListStringVal1up());

					benefitRule.setStringValue(defaultData.getPkgVerBig3CO()
							.getPkgMasterListForBig3CO()
							.getMasterListStringVal().getStringValue());

					benefitRule.setAbbrev(defaultData.getPkgVerBig3CO()
							.getPkgMasterListForBig3CO()
							.getMasterListStringVal().getAbbrev());

					data.setMasterListStringValData(benefitRule);

				}
			}
		}

		int limitCount = 0;
		for (LimitShapeBenefitOptionOverride big3coLimitXXref : big3coLimitXXrefs) {

			if (defaultData.getPkgVerBig3CO().getPkgVerBig3CO1up() == big3coLimitXXref
					.getLimitShapeBenefitOptionOverrideId()
					.getMatrixShapeBig3COLimitXXrefId()
					.getMatrixShapeBig3COXrefId().getPkgVerBig3CO()
					.getPkgVerBig3CO1up()) {
				limitCount++;

			}

		}
		data.setLimitCount(limitCount);
		int messageCount = 0;
		for (MessageShapeBenefitOptionOverride big3coMessageXXref : big3coMessageXXrefs) {

			if (defaultData.getPkgVerBig3CO().getPkgVerBig3CO1up() == big3coMessageXXref
					.getMessageShapeBenefitOptionOverrideId()
					.getMatrixShapeBig3COMessageXXrefId()
					.getMatrixShapeBig3COXrefId().getPkgVerBig3CO()
					.getPkgVerBig3CO1up()) {
				messageCount++;

			}

		}

		data.setMessageCount(messageCount);

		data.setDeductibleData(deductibleDatas);

		// data.setCreateBy(defaultData.getCreateBy());
		//
		// data.setCreateDt(defaultData.getCreateDt());
		//
		// data.setUpdateBy(defaultData.getUpdateBy());
		//
		// data.setUpdateDt(defaultData.getUpdateDt());
		//
		// data.seteBSInstance1up(defaultData.geteBSInstance1up());

		if (defaultData.getPkgVerBig3CO() != null) {
			data.setIsCovered(defaultData.getPkgVerBig3CO().getCoveredFlag());
		}
		if (defaultData.getPkgVerBig3CO().getTreatAs100PctFlag() != null) {

			data.setTreatAs100(defaultData.getPkgVerBig3CO()
					.getTreatAs100PctFlag());

		}
		data.setTierNo(defaultData.getTierNo());
		if (defaultData.getPkgVerBig3CO() != null) {
			data.setPkgVerBig3CO1up(defaultData.getPkgVerBig3CO()
					.getPkgVerBig3CO1up());
		}

		// if (defaultData.getPkgVerInstanceTree() != null) {
		//
		// PkgVerInstanceTreeData commonBenefit = new PkgVerInstanceTreeData();
		// commonBenefit.setPkgVerInstanceTree1up(defaultData
		// .getPkgVerInstanceTree().getPkgVerInstanceTree1up());
		// commonBenefit
		// .setName(defaultData.getPkgVerInstanceTree().getName());
		// commonBenefit.setDescription(defaultData.getPkgVerInstanceTree()
		// .getDescription());
		// data.setPkgVerInstanceTreeData(commonBenefit);
		//
		// }

		data.setTierNo(defaultData.getTierNo());
		return data;

	}

	/**
	 * @param benefitSelectionTierDataList
	 *            List of BenefitSelectionTierdataData
	 * @return ServiceOperationResult
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             It will save
	 */
	@Transactional
	public ServiceOperationResult saveBenefitBasedOnCommmomBenifit(
			List<BenefitSelectionTierdataData> benefitSelectionTierDataList)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		if (benefitSelectionTierDataList == null) {
			throw new DataAccessException();
		}

		try {

			for (BenefitSelectionTierdataData data : benefitSelectionTierDataList) {
				BenefitSelectionTierdata temp = null;
				PkgVerInstanceTree tree = new PkgVerInstanceTree();
				if (data.getPkgVerInstanceTreeData().getPkgVerInstanceTree1up() != null) {
					tree = packageVersionRepository
							.findPkgVerInstanceTreeById(data
									.getPkgVerInstanceTree1up());
				}
				if (tree.getPkgVersion() != null) {
					if (tree.getPkgVersion().getPkgVersionWorkflowState() != null) {
						if (tree.getPkgVersion().getPkgVersionWorkflowState()
								.getReleasedFlag() != null) {
							if (tree.getPkgVersion()
									.getPkgVersionWorkflowState()
									.getReleasedFlag() != true) {

								if (data.getId() != null && data.getId() > 0) {

									temp = packageVersionRepository
											.findBenefitSelectionTierdataById(data
													.getId());
									temp.setUpdateBy(data.getUpdateBy());
									temp.setUpdateDt(new Date());

								} else {
									temp = new BenefitSelectionTierdata();
									temp.setCreateBy(data.getCreateBy());
									temp.setCreateDt(new Date());
								}

								if (data.getPkgVerInstanceTreeData()
										.getPkgVerInstanceTree1up() != null) {
									temp.setPkgVerInstanceTree(packageVersionRepository.findPkgVerInstanceTreeById(data
											.getPkgVerInstanceTree1up()));
								}

								if (data.getPkgVerBig3CO1up() != null) {
									temp.setPkgVerBig3CO(packageVersionRepository
											.findPkgVerBig3COById(data
													.getPkgVerBig3CO1up()));
								}

								if (data.getApplyCoinsuranceShape() != null) {
									temp.setApplyCoinsuranceShape(templateRepository.findTreeShapeById(data
											.getApplyCoinsuranceShape()));
								}

								temp.setApplyCoinsurance(data
										.getApplyCoinsurance());

								if (data.getCoinsuranceNoValData() != null) {
									temp.setCoinsuranceNoVal(masterListRepository
											.findMasterListStringValById(data
													.getCoinsuranceNoVal1up()));
								}

								if (data.getCoinsuranceYesValData() != null) {
									temp.setCoinsuranceYesVal(masterListRepository
											.findMasterListIntValById(data
													.getCoinsuranceYesVal1up()));
								}

								if (data.getCoinsuranceNoValShape() != null) {
									temp.setCoinsuranceNoValShape(templateRepository.findTreeShapeById(data
											.getCoinsuranceNoValShape()));
								}

								if (data.getCoinsuranceYesValShape() != null) {
									temp.setCoinsuranceYesValShape(templateRepository.findTreeShapeById(data
											.getCoinsuranceYesValShape()));
								}

								if (data.getCopayShape() != null) {
									temp.setCopayShape(templateRepository
											.findTreeShapeById(data
													.getCopayShape()));
								}

								if (data.getCopayValData() != null) {
									temp.setCopayVal(masterListRepository
											.findMasterListIntValById(data
													.getCopayVal1up()));
								}

								if (data.getAllowedAmountData() != null) {
									temp.setAllowedAmt(masterListRepository
											.findMasterListIntValById(data
													.getAllowedAmount1up()));
								}

								if (data.getAllowedCtrData() != null) {
									temp.setAllowedCtr(masterListRepository
											.findMasterListIntValById(data
													.getAllowedCtr1up()));
								}

								if (data.getAllowedAmtShape() != null) {
									temp.setAllowedAmtShape(templateRepository
											.findTreeShapeById(data
													.getAllowedAmtShape()));
								}

								if (data.getAllowedCtrShape() != null) {
									temp.setAllowedCtrShape(templateRepository
											.findTreeShapeById(data
													.getAllowedCtrShape()));
								}
								temp.seteBSInstance1up(data.geteBSInstance1up());
								temp.setTierNo(data.getTierNo());
								if (data.getId() != null && data.getId() > 0) {
									packageVersionRepository.updateData(temp);
								} else {
									packageVersionRepository.saveData(temp);
								}
							} else {
								operationResult
										.setOperationResult(OperationResult.FAILURE);
								messages.add("Error Saving Benefit Selection");
								return operationResult;
							}

						}
					}
				}
			}
		} catch (PersistenceException e) {
			throw new DataAccessException(e.getMessage());
		}

		return operationResult;

	}

	public int saveTierNumberForBenefitSelection(List<TierData> tierDatas)
			throws DataAccessException {

		Integer tierNo = tierDatas.size();
		return tierNo;
	}

	/**
	 * @param pkgVerId
	 *            PK of PackageVersion
	 * @return PackageVersion Object
	 * @throws DataAccessException
	 *             <p>
	 *             This method gives the details about a packageVersion
	 */
	@Transactional
	public PackageVersionData getPkgVerDetailsByPkgVerId(int pkgVerId)
			throws DataAccessException {

		PkgVersion pkgVersion;
		try {
			pkgVersion = packageVersionRepository
					.findPackageVersionById(pkgVerId);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}
		PackageVersionData data = new PackageVersionData();

		if (pkgVersion.getBenefitPackage() != null) {
			data.setBenifitPackage1up(pkgVersion.getBenefitPackage()
					.getBenefitPackage1up());
		}
		data.setCreateBy(pkgVersion.getCreateBy());
		data.setUpdateDt(new Date());
		data.seteBSInstance1up(pkgVersion.geteBSInstance1up());
		data.setVersionID(pkgVersion.getVersionNumber());
		data.setVersionName(pkgVersion.getName());
		data.setVersionDescription(pkgVersion.getDescription());

		if (pkgVersion.getPkgVersionWorkflowState() != null) {
			data.setWorkflowstate1up(pkgVersion.getPkgVersionWorkflowState()
					.getPkgVersionWorkflowState1up());
		}
		return data;
	}

	/**
	 * @param pkgVersionId
	 *            PkgVersion1Up
	 * @param workflowStateId
	 *            PkgVersionWorkflowState1up
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * @throws DataAccessException
	 *             <p>
	 *             This method update workFlowState of packageVersion. It
	 *             returns WARNING if at least one unsuccessful. Otherwise it
	 *             throws exception
	 */
	@Transactional
	public ServiceOperationResult changeWorkFlowStateOfPackageVersion(
			int pkgVersionId, int workflowStateId) throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();

		List<String> messages = new ArrayList<String>();

		PkgVersion pkgVersion = null;
		try {
			pkgVersion = packageVersionRepository
					.findPackageVersionById(pkgVersionId);
			pkgVersion.setPkgVersionWorkflowState(packageVersionRepository
					.findPkgVersionWorkflowStateById(workflowStateId));
			// pkgVersion.setUpdateBy(coreCommonAttributes.getUpdateBy());
			pkgVersion.setUpdateDt(new Date());

			templateRepository.updateData(pkgVersion);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error changing the workflow state");

		}
		return operationResult;
	}

	/**
	 * @param treeData
	 *            Object of PkgVerInstanceTreeData
	 * 
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This Method Will Save a newly Created CommonBenefit
	 */
	@Transactional
	public ServiceOperationResult saveNewCommonBenefitForPkgVersion(
			PkgVerInstanceTreeData treeData) throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		if (treeData == null) {
			throw new DataAccessException();
		}

		try {

			PkgVerInstanceTree instanceTree = new PkgVerInstanceTree();

			if (treeData.getPkgVersion1up() != null) {
				instanceTree.setPkgVersion(packageVersionRepository
						.findPackageVersionById(treeData.getPkgVersion1up()));
			}

			if (treeData.getTemplateTree1up() != null) {
				instanceTree.setTemplateTree(templateRepository
						.findTemplateTreeById(treeData.getTemplateTree1up()));
			}

			instanceTree.setName(treeData.getName());
			instanceTree.setDescription(treeData.getDescription());
			instanceTree.seteBSInstance1up(treeData.geteBSInstance1up());
			instanceTree.setCreateBy(treeData.getCreateBy());
			instanceTree.setCreateDt(new Date());

			packageVersionRepository.saveData(instanceTree);

		} catch (PersistenceException e) {

			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error saving value for MasterListIntVal");
		}

		return operationResult;
	}

	/**
	 * @param treeData
	 *            Object of PkgVerInstanceTreeData
	 * 
	 * @param oldInstanceTreeId
	 *            Primary Key of PkgVerInstanceTree from which we have to copy
	 * 
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This Method Will create a replica of existing one
	 */
	@Transactional
	public ServiceOperationResult copyCommonBenefitFormExistingCommonBenefit(
			PkgVerInstanceTreeData treeData, int oldInstanceTreeId)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		PkgVerInstanceTree instance = new PkgVerInstanceTree();

		if (treeData == null) {
			throw new DataAccessException();
		}

		try {
			instance = packageVersionRepository
					.findSetShapeValueTreeInstanceByPkgVerInstanceTreeId(oldInstanceTreeId);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		try {

			PkgVerInstanceTree instanceTree = new PkgVerInstanceTree();

			if (treeData.getPkgVersion1up() != null) {
				instanceTree.setPkgVersion(packageVersionRepository
						.findPackageVersionById(treeData.getPkgVersion1up()));
			}

			if (treeData.getTemplateTree1up() != null) {
				instanceTree.setTemplateTree(templateRepository
						.findTemplateTreeById(treeData.getTemplateTree1up()));
			}

			instanceTree.setName(treeData.getName());
			instanceTree.setDescription(treeData.getDescription());
			instanceTree.seteBSInstance1up(treeData.geteBSInstance1up());
			instanceTree.setCreateBy(treeData.getCreateBy());
			instanceTree.setCreateDt(new Date());

			Set<ShapeValueTreeInstance> treeInstances = instance
					.getShapeIntstanceTree();
			for (ShapeValueTreeInstance shapeValueTreeInstance : treeInstances) {
				packageVersionRepository.detachedEntity(shapeValueTreeInstance);
				shapeValueTreeInstance.getShapeValueTreeInstanceId()
						.setPkgVerInstanceTree(instanceTree);

			}
			instance.setShapeIntstanceTree(treeInstances);
			packageVersionRepository.saveData(instanceTree);

		} catch (PersistenceException e) {

			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error saving value for MasterListIntVal");
		}

		return operationResult;
	}

	/**
	 * @param pkgVersion1up
	 *            Primary key of PkgVersion foreign key in PkgVerInstanceTree
	 * 
	 * @param templateTree1up
	 *            Primary key of TemplateTree foreign key in PkgVerInstanceTree
	 * 
	 * @return List PkgVerInstanceTreeData object
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method will return list of Common Benefit for a Package
	 *             Version When respective Template Tree is selected
	 */
	@Transactional(readOnly = true)
	public List<PkgVerInstanceTreeData> getCommonBenefitForEachPackageVersionByTemplateTree(
			int pkgVersion1up, int templateTree1up) throws DataAccessException {

		List<PkgVerInstanceTree> instanceTreeList = null;
		try {
			instanceTreeList = packageVersionRepository
					.findCommonBenefitForEachPackageVersionByTemplateTree(
							pkgVersion1up, templateTree1up);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<PkgVerInstanceTreeData> instanceTreeDataList = new ArrayList<PkgVerInstanceTreeData>();

		for (PkgVerInstanceTree instanceTree : instanceTreeList) {

			PkgVerInstanceTreeData instanceTreeData = new PkgVerInstanceTreeData();

			instanceTreeData.setPkgVerInstanceTree1up(instanceTree
					.getPkgVerInstanceTree1up());

			if (instanceTree.getPkgVersion().getPkgVersion1up() != null) {
				instanceTreeData.setPkgVersion1up(instanceTree.getPkgVersion()
						.getPkgVersion1up());
			}

			if (instanceTree.getTemplateTree() != null) {
				instanceTreeData.setTemplateTree1up(instanceTree
						.getTemplateTree().getTemplateTree1up());
			}

			instanceTreeData.setName(instanceTree.getName());
			instanceTreeData.setDescription(instanceTree.getDescription());

			instanceTreeData
					.seteBSInstance1up(instanceTree.geteBSInstance1up());
			instanceTreeData.setCreateBy(instanceTree.getCreateBy());
			instanceTreeData.setCreateDt(instanceTree.getCreateDt());
			instanceTreeData.setUpdateBy(instanceTree.getUpdateBy());
			instanceTreeData.setUpdateDt(instanceTree.getUpdateDt());

			instanceTreeDataList.add(instanceTreeData);

		}

		return instanceTreeDataList;

	}

	/**
	 * @param data
	 *            this takes PackageVersionData object
	 * @return ServiceOperationResult
	 * @throws DataAccessException
	 *             <p>
	 *             This method creates a new package version.
	 */
	@Transactional
	public ServiceOperationResult createNewPackageVersion(
			PackageVersionData data) throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		PkgVersion version = new PkgVersion();

		try {
			version.setBenefitPackage(packageRepository
					.findBenefitPackageById(data.getBenifitPackage1up()));
			version.setPkgVersionWorkflowState(packageVersionRepository
					.findPkgVersionWorkflowStateById(data.getWorkflowstate1up()));

		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error creating new PackageVersion  ");
		}
		version.setCreateBy(data.getCreateBy());
		version.setCreateDt(new Date());
		version.setDescription(data.getVersionDescription());
		version.seteBSInstance1up(data.geteBSInstance1up());
		version.setName(data.getVersionName());
		version.setVersionNumber(data.getVersionID());

		packageRepository.saveData(version);
		return operationResult;
	}

	/**
	 * @param data
	 *            This method takes PackageVersionData Object
	 * @return It returns ServiceOperationResult
	 * @throws DataAccessException
	 *             <p>
	 *             This method is used to create PkgVersion from existing
	 *             PkgVersion
	 */
	@Transactional
	public ServiceOperationResult createNewPackageVersionFromExistingVersion(
			PackageVersionData data) throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		PkgVersion version = new PkgVersion();
		int pkgVerID = data.getPkgVersion1up();

		PkgVersion existingPkgVersion = null;
		try {
			existingPkgVersion = packageVersionRepository
					.findPackageVersionById(pkgVerID);

			// Copy from DTO
			version.setVersionNumber(data.getVersionID());
			version.setName(data.getVersionName());
			version.setDescription(data.getVersionDescription());
			version.setPkgVersionWorkflowState(packageVersionRepository
					.findPkgVersionWorkflowStateById(data.getWorkflowstate1up()));
			// Copy from Existing
			if (existingPkgVersion.getBenefitPackage() != null) {
				version.setBenefitPackage(existingPkgVersion
						.getBenefitPackage());
			}
			version.setCancelDate(existingPkgVersion.getCancelDate());
			version.setCreateBy(existingPkgVersion.getCreateBy());
			version.setCreateDt(new Date());
			version.seteBSInstance1up(existingPkgVersion.geteBSInstance1up());
			version.setEffectiveDate(existingPkgVersion.getEffectiveDate());

			;
			packageVersionRepository.saveData(version);
			System.out.println(version.getPkgVersion1up());

			if (data.getServiceTypeList() == true) {

				List<PkgVerBig3CO> big3cos = packageVersionRepository
						.findPkgVerBig3CoByPkgVerId(pkgVerID);

				for (PkgVerBig3CO pkgVerBig3CO : big3cos) {

					PkgVerBig3CO newPkgVerBig3CO = new PkgVerBig3CO();

					newPkgVerBig3CO.setCreateBy(data.getCreateBy());
					newPkgVerBig3CO.setCreateDt(new Date());
					newPkgVerBig3CO.seteBSInstance1up(pkgVerBig3CO
							.geteBSInstance1up());
					newPkgVerBig3CO.setPkgVersion(version);
					newPkgVerBig3CO.setPkgMasterListForBig3CO(pkgVerBig3CO
							.getPkgMasterListForBig3CO());
					newPkgVerBig3CO.setCoveredFlag(pkgVerBig3CO
							.getCoveredFlag());
					newPkgVerBig3CO.setTreatAs100PctFlag(pkgVerBig3CO
							.getTreatAs100PctFlag());
					newPkgVerBig3CO.setsETRMessageSelected(pkgVerBig3CO
							.getsETRMessageSelected());

					// packageVersionRepository.detachEntity(pkgVerBig3CO);
					// pkgVerBig3CO.setPkgVersion(version);
					// pkgVerBig3CO.setPkgVerBig3CO1up(null);

					packageVersionRepository.saveData(newPkgVerBig3CO);

				}
			}
			if (data.getCommonBenefit() == true) {

				List<PkgVerInstanceTree> instanceTrees = packageVersionRepository
						.findPkgVerInstanceTreeByPkgVerId(pkgVerID);
				for (PkgVerInstanceTree pkgVerInstanceTree : instanceTrees) {

					PkgVerInstanceTree newInstanceTree = new PkgVerInstanceTree();

					newInstanceTree.seteBSInstance1up(pkgVerInstanceTree
							.geteBSInstance1up());
					newInstanceTree.setPkgVersion(version);
					newInstanceTree.setTemplateTree(pkgVerInstanceTree
							.getTemplateTree());
					newInstanceTree.setName(pkgVerInstanceTree.getName());
					newInstanceTree.setDescription(pkgVerInstanceTree
							.getDescription());
					newInstanceTree.setCreateBy(data.getCreateBy());
					newInstanceTree.setCreateDt(new Date());
					packageVersionRepository.saveData(newInstanceTree);
				}

			}

			if (data.getBenefitOption() == true) {

				List<PkgVerBenefitOption> benefitOptions = packageVersionRepository
						.findPkgVerBenefitOptionByPkgVerId(pkgVerID);

				for (PkgVerBenefitOption pkgVerBenefitOption : benefitOptions) {

					PkgVerBenefitOption newBenefitOption = new PkgVerBenefitOption();

					newBenefitOption.seteBSInstance1up(pkgVerBenefitOption
							.geteBSInstance1up());
					newBenefitOption.setPkgVersion(version);
					newBenefitOption.setName(pkgVerBenefitOption.getName());
					newBenefitOption.setDescription(pkgVerBenefitOption
							.getDescription());
					newBenefitOption.setCreateBy(data.getCreateBy());
					newBenefitOption.setCreateDt(new Date());

					// packageVersionRepository.detachEntity(pkgVerBenefitOption);
					//
					// pkgVerBenefitOption.setPkgVersion(version);
					// pkgVerBenefitOption.setPkgVerBenefitOption1up(null);

					packageVersionRepository.saveData(newBenefitOption);
				}
			}

			if (data.getBenefitSelection() && data.getCommonBenefit()
					&& data.getServiceTypeList() == true) {

				Set<BenefitSelectionTierdata> tierdatas = new HashSet<BenefitSelectionTierdata>();

				List<PkgVerInstanceTree> exsistingInstanceTrees = packageVersionRepository
						.findPkgVerInstanceTreeByPkgVerId(pkgVerID);
				List<PkgVerInstanceTree> newInstanceTrees = packageVersionRepository
						.findPkgVerInstanceTreeByPkgVerId(version
								.getPkgVersion1up());

				for (PkgVerInstanceTree pkgVerInstanceTree : exsistingInstanceTrees) {

					tierdatas = pkgVerInstanceTree.getPkgVerBenInstanceTree();

					for (BenefitSelectionTierdata selectionTierdata : tierdatas) {

						BenefitSelectionTierdata newBenefitSelectionTierdata = new BenefitSelectionTierdata();

						for (PkgVerInstanceTree instanceTree : newInstanceTrees) {

							List<PkgVerBig3CO> newBig3cos = packageVersionRepository
									.findPkgVerBig3CoByInstancetreeId(
											instanceTree
													.getPkgVerInstanceTree1up(),
											version.getPkgVersion1up());
							for (PkgVerBig3CO big3CO2 : newBig3cos) {

								newBenefitSelectionTierdata
										.setPkgVerBig3CO(big3CO2);

								newBenefitSelectionTierdata
										.setPkgVerInstanceTree(instanceTree);

								newBenefitSelectionTierdata
										.seteBSInstance1up(selectionTierdata
												.geteBSInstance1up());
								newBenefitSelectionTierdata
										.setAllowedAmt(selectionTierdata
												.getAllowedAmt());
								newBenefitSelectionTierdata
										.setAllowedAmtShape(selectionTierdata
												.getAllowedAmtShape());
								newBenefitSelectionTierdata
										.setAllowedCtr(selectionTierdata
												.getAllowedCtr());
								newBenefitSelectionTierdata
										.setAllowedCtrShape(selectionTierdata
												.getAllowedCtrShape());
								newBenefitSelectionTierdata
										.setApplyCoinsurance(selectionTierdata
												.getApplyCoinsurance());
								newBenefitSelectionTierdata
										.setApplyCoinsuranceShape(selectionTierdata
												.getApplyCoinsuranceShape());
								newBenefitSelectionTierdata
										.setCoinsuranceNoVal(selectionTierdata
												.getCoinsuranceNoVal());
								newBenefitSelectionTierdata
										.setCoinsuranceNoValShape(selectionTierdata
												.getCoinsuranceNoValShape());
								newBenefitSelectionTierdata
										.setCoinsuranceYesVal(selectionTierdata
												.getCoinsuranceYesVal());
								newBenefitSelectionTierdata
										.setCoinsuranceYesValShape(selectionTierdata
												.getCoinsuranceYesValShape());
								newBenefitSelectionTierdata
										.setCopayShape(selectionTierdata
												.getCopayShape());
								newBenefitSelectionTierdata
										.setCopayVal(selectionTierdata
												.getCopayVal());
								newBenefitSelectionTierdata.setCreateBy(data
										.getCreateBy());
								newBenefitSelectionTierdata
										.setCreateDt(new Date());
								newBenefitSelectionTierdata
										.setTierNo(selectionTierdata
												.getTierNo());

								packageVersionRepository
										.saveData(newBenefitSelectionTierdata);
							}
						}
					}
				}

			} else {
				operationResult.setOperationResult(OperationResult.WARNING);
				messages.add("Error creating new PackageVersion  ");
			}
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error creating new PackageVersion  ");
		}

		return operationResult;

	}

	/*
	 * @Transactional public ServiceOperationResult createNewBenefitOption(
	 * PackageVersionBenefitOptionData data, List<BenefitOptionBig3COData>
	 * listBenefitOptBig3CO, List<BenefitOptionTierdataData>
	 * optionTierdataDatas) throws DataAccessException { ServiceOperationResult
	 * operationResult = new ServiceOperationResult(); List<String> messages =
	 * new ArrayList<String>();
	 * 
	 * try { saveAndUpdateBenefitOption(data, listBenefitOptBig3CO,
	 * optionTierdataDatas); } catch (DataAccessException e) {
	 * 
	 * e.printStackTrace(); } List<BenefitOptionBig3CO> list1 = null; try { for
	 * (BenefitOptionBig3COData big3coData : listBenefitOptBig3CO) { list1 =
	 * packageVersionRepository .findBenefitOpBig3COByPkgVerBenOptId(big3coData
	 * .getPkgVerBenefitOption1up()); }
	 * 
	 * } catch (PersistenceException e) {
	 * 
	 * throw new DataAccessException(e); } List<Integer> currentList = new
	 * ArrayList<Integer>(); List<Integer> existingList = new
	 * ArrayList<Integer>(); if (listBenefitOptBig3CO != null) { for
	 * (BenefitOptionBig3COData big3coData : listBenefitOptBig3CO) {
	 * currentList.add(big3coData.getPkgVerBig3CO1up());
	 * 
	 * } } if (list1 != null) { for (BenefitOptionBig3CO optionBig3CO : list1) {
	 * if (optionBig3CO.getBenefitOptionBig3CO1up() != null) {
	 * existingList.add(optionBig3CO.getBenefitOptionBig3CO1up()); } } }
	 * 
	 * List<Integer> common = new ArrayList<Integer>(existingList);
	 * common.retainAll(currentList); if (common != null) {
	 * existingList.removeAll(common); currentList.removeAll(common); }
	 * 
	 * if (existingList != null) { for (Integer existing : existingList) {
	 * 
	 * try { packageVersionRepository .deleteBenefitOptionBig3COById(existing);
	 * 
	 * } catch (PersistenceException e) { throw new DataAccessException(e); }
	 * 
	 * } }
	 * 
	 * List<BenefitOptionBig3CO> toSaveList = new
	 * ArrayList<BenefitOptionBig3CO>(); if (currentList != null) { for
	 * (BenefitOptionBig3COData benefitOptionBig3COData : listBenefitOptBig3CO)
	 * { if (currentList.contains(benefitOptionBig3COData
	 * .getBenefitOptionBig3CO1up())) { BenefitOptionBig3CO toSave = new
	 * BenefitOptionBig3CO();
	 * 
	 * try { if (benefitOptionBig3COData.getBenefitOptionBig3CO1up() != null) {
	 * toSave.setBenefitOptionBig3CO1up(benefitOptionBig3COData
	 * .getBenefitOptionBig3CO1up()); } } catch (PersistenceException e) {
	 * 
	 * operationResult .setOperationResult(OperationResult.WARNING);
	 * messages.add("Error saving value for BenefitOptionBig3CO"); }
	 * 
	 * toSave.setCreateBy(benefitOptionBig3COData.getCreateBy());
	 * toSave.setCreateDt(new Date());
	 * toSave.seteBSInstance1up(benefitOptionBig3COData .geteBSInstance1up());
	 * toSave.setPkgVerBenefitOption(packageVersionRepository
	 * .findPkgVerBenefitOptionById(benefitOptionBig3COData
	 * .getPkgVerBenefitOption1up()));
	 * toSave.setPkgVerBig3CO(packageVersionRepository
	 * .findPkgVerBig3COById(benefitOptionBig3COData .getPkgVerBig3CO1up()));
	 * toSave.setPkgVerInstanceTree(packageVersionRepository
	 * .findPkgVerInstanceTreeById(benefitOptionBig3COData
	 * .getPkgVerInstanceTree1up()));
	 * toSave.setPkgVersion(packageVersionRepository
	 * .findPackageVersionById(benefitOptionBig3COData .getPkgVersion1up()));
	 * toSave.setTreatAs100PctFlag(benefitOptionBig3COData
	 * .getPkgVersionTreaAs100PctFlag());
	 * toSave.setsETRMessageSelected(benefitOptionBig3COData
	 * .getsETRMessageSelected());
	 * 
	 * toSave.setCoveredFlag(benefitOptionBig3COData
	 * .getPkgVersionCoveredFlag()); toSaveList.add(toSave); for
	 * (BenefitOptionBig3CO benefitOptionBig3CO : toSaveList) { try {
	 * packageVersionRepository .saveData(benefitOptionBig3CO);
	 * 
	 * } catch (PersistenceException e) { operationResult
	 * .setOperationResult(OperationResult.WARNING);
	 * messages.add("Error saving value for BenefitOptionBig3CO"); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } }
	 * 
	 * return operationResult; }
	 */
	/**
	 * @param pkgVerId
	 *            This method takes PK of PkgVersion
	 * @return ServiceOperationResult
	 * @throws DataAccessException
	 *             <p>
	 *             This method implements cascade delete for a packageVersion
	 */
	@Transactional
	public ServiceOperationResult deletePackageVersion(int pkgVerId)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		try {
			PkgVersion pkgVersion = packageVersionRepository
					.findPackageVersionSetById(pkgVerId);

			// Delete by pkgVerId
			packageVersionRepository
					.deleteBenefitOptionBig3COByPkgVerId(pkgVerId);

			// Delete by pkgConfigOption1up
			Set<PkgConfigOption> configOptions = pkgVersion
					.getConfigOptionVer();
			List<AccountPkgConfigOptionXref> accountPkgConfigOptionXrefs = packageVersionRepository
					.findAllAccountPkgConfigOptionXref();
			List<Integer> accountPkgIdList = new ArrayList<Integer>();
			for (AccountPkgConfigOptionXref accountPkgConfigOptionXref : accountPkgConfigOptionXrefs) {
				accountPkgIdList.add(accountPkgConfigOptionXref
						.getPkgConfigOption().getPkgConfigOption1up());

			}
			for (PkgConfigOption pkgConfigOption : configOptions) {

				packageVersionRepository
						.deletePkgVerProductBenefitOptionXrefByConfigId(pkgConfigOption
								.getPkgConfigOption1up());

				packageVersionRepository
						.deletePkgConfigOptionBenefitOptionXrefByConfigId(pkgConfigOption
								.getPkgConfigOption1up());

				packageVersionRepository
						.deletePkgVersionProductXrefByConfigId(pkgConfigOption
								.getPkgConfigOption1up());

				if (accountPkgIdList.contains(pkgConfigOption
						.getPkgConfigOption1up())) {
					operationResult.setOperationResult(OperationResult.FAILURE);
					messages.add("Error deleting value for Package Version");
					return operationResult;
				}
			}
			packageVersionRepository.deletePkgConfigOptionByPkgVerId(pkgVerId);

			// Delete by pkgVerBenefitOption1up
			Set<PkgVerBenefitOption> pkgVerBenefitOptions = pkgVersion
					.getPkgBenefitOption();
			for (PkgVerBenefitOption pkgVerBenefitOption : pkgVerBenefitOptions) {

				int pkgVerBenefitOptionId = pkgVerBenefitOption
						.getPkgVerBenefitOption1up();
				packageVersionRepository
						.deleteBenefitOptionBig3COBypkgVerBenefitOptId(pkgVerBenefitOptionId);
				packageVersionRepository
						.deletePkgVerProductBenefitOptionXrefBypkgVerBenefitOptId(pkgVerBenefitOptionId);
				packageVersionRepository
						.deleteLimitShapeBenefitOptionOverrideBypkgVerBenefitOptId(pkgVerBenefitOptionId);
				packageVersionRepository
						.deleteMessageShapeBenefitOptionOverrideBypkgVerBenefitOptId(pkgVerBenefitOptionId);
				packageVersionRepository
						.deletePkgConfigOptionBenefitOptionXrefBypkgVerBenefitOptId(pkgVerBenefitOptionId);
				packageVersionRepository
						.deleteShapeValueBenefitOptionForBig3COBypkgVerBenefitOptId(pkgVerBenefitOptionId);
			}
			packageVersionRepository
					.deletePkgVerBenefitOptionByPkgVerId(pkgVerId);

			// Delete by pkgVerBig3CO1up
			Set<PkgVerBig3CO> pkgVerBig3COs = pkgVersion.getPkgBigCo();
			for (PkgVerBig3CO pkgVerBig3CO : pkgVerBig3COs) {
				int pkgVerBig3CO1up = pkgVerBig3CO.getPkgVerBig3CO1up();
				packageVersionRepository
						.deleteBenefitOptionBig3COBypkgVerBig3CoId(pkgVerBig3CO1up);

				packageVersionRepository
						.deleteLimitShapeBenefitOptionOverrideBypkgVerBig3CoId(pkgVerBig3CO1up);

				packageVersionRepository
						.deleteMatrixShapeBig3COLimitXXrefBypkgVerBig3CoId(pkgVerBig3CO1up);

				packageVersionRepository
						.deleteMessageShapeBenefitOptionOverrideBypkgVerBig3CoId(pkgVerBig3CO1up);

				packageVersionRepository
						.deleteMatrixShapeBig3COMessageXXrefBypkgVerBig3CoId(pkgVerBig3CO1up);

				packageVersionRepository
						.deleteMatrixShapeBig3COXrefBypkgVerBig3CoId(pkgVerBig3CO1up);

				packageVersionRepository
						.deleteShapeValueBenefitOptionForBig3COBypkgVerBig3CoId(pkgVerBig3CO1up);

				packageVersionRepository
						.deleteShapeValueBig3COBypkgVerBig3CoId(pkgVerBig3CO1up);

				packageVersionRepository
						.deleteBenefitSelectionTierdataBypkgVerBig3CoId(pkgVerBig3CO1up);
			}
			packageVersionRepository.deletePkgVerBig3COByPkgVerId(pkgVerId);

			// Delete by pkgVerInstanceId
			Set<PkgVerInstanceTree> pkgVerInstanceTrees = pkgVersion
					.getPkgVerTree();
			for (PkgVerInstanceTree pkgVerInstanceTree : pkgVerInstanceTrees) {

				int pkgVerInstanceId = pkgVerInstanceTree
						.getPkgVerInstanceTree1up();
				packageVersionRepository
						.deleteBenefitOptionBig3COBypkgVerInstanceId(pkgVerInstanceId);

				packageVersionRepository
						.deleteExcelViewInfoBypkgVerInstanceId(pkgVerInstanceId);

				packageVersionRepository
						.deleteShapeValueBenefitOptionForBig3COBypkgVerInstanceId(pkgVerInstanceId);

				packageVersionRepository
						.deleteShapeValueBig3COBypkgVerInstanceId(pkgVerInstanceId);

				packageVersionRepository
						.deleteShapeValueTreeInstanceBypkgVerInstanceId(pkgVerInstanceId);

				packageVersionRepository
						.deleteBenefitSelectionTierdataBypkgVerInstanceId(pkgVerInstanceId);

				Set<PkgVerBig3CO> big3COs = pkgVerInstanceTree
						.getPkgInstanceTree();
				for (PkgVerBig3CO pkgVerBig3CO : big3COs) {
					int pkgVerBig3CO1up = pkgVerBig3CO.getPkgVerBig3CO1up();
					packageVersionRepository
							.deleteBenefitOptionBig3COBypkgVerBig3CoId(pkgVerBig3CO1up);

					packageVersionRepository
							.deleteLimitShapeBenefitOptionOverrideBypkgVerBig3CoId(pkgVerBig3CO1up);

					packageVersionRepository
							.deleteMatrixShapeBig3COLimitXXrefBypkgVerBig3CoId(pkgVerBig3CO1up);

					packageVersionRepository
							.deleteMessageShapeBenefitOptionOverrideBypkgVerBig3CoId(pkgVerBig3CO1up);

					packageVersionRepository
							.deleteMatrixShapeBig3COMessageXXrefBypkgVerBig3CoId(pkgVerBig3CO1up);

					packageVersionRepository
							.deleteMatrixShapeBig3COXrefBypkgVerBig3CoId(pkgVerBig3CO1up);

					packageVersionRepository
							.deleteShapeValueBenefitOptionForBig3COBypkgVerBig3CoId(pkgVerBig3CO1up);

					packageVersionRepository
							.deleteShapeValueBig3COBypkgVerBig3CoId(pkgVerBig3CO1up);

					packageVersionRepository
							.deleteBenefitSelectionTierdataBypkgVerBig3CoId(pkgVerBig3CO1up);
				}
				packageVersionRepository
						.deletePkgVerBig3COByPkgVerInstanceId(pkgVerInstanceId);
			}
			packageVersionRepository
					.deletePkgVerInstanceTreeByPkgVerId(pkgVerId);

			packageVersionRepository
					.deletePackageVersionUIElementValueByPkgVerId(pkgVerId);

			packageVersionRepository.deletePackageVersionByPkgVerId(pkgVerId);
			System.out.println("packageVersion deleted...");
		} catch (PersistenceException e) {
			throw new DataAccessException(e.getMessage());
		}
		return operationResult;
	}

	@Transactional
	public ServiceOperationResult deleteCommonBenefit(int pkgVerInstTreeId)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		try {
			PkgVerInstanceTree pkgVerInstanceTree = packageVersionRepository
					.findPkgVerInstanceTreeById(pkgVerInstTreeId);

			Set<PkgVerBig3CO> big3cos = pkgVerInstanceTree.getPkgInstanceTree();
			for (PkgVerBig3CO pkgVerBig3CO : big3cos) {
				int pkgVerBig3CO1up = pkgVerBig3CO.getPkgVerBig3CO1up();

				packageVersionRepository
						.deleteBenefitOptionBig3COBypkgVerBig3CoId(pkgVerBig3CO1up);

				packageVersionRepository
						.deleteLimitShapeBenefitOptionOverrideBypkgVerBig3CoId(pkgVerBig3CO1up);

				packageVersionRepository
						.deleteMatrixShapeBig3COLimitXXrefBypkgVerBig3CoId(pkgVerBig3CO1up);

				packageVersionRepository
						.deleteMessageShapeBenefitOptionOverrideBypkgVerBig3CoId(pkgVerBig3CO1up);

				packageVersionRepository
						.deleteMatrixShapeBig3COMessageXXrefBypkgVerBig3CoId(pkgVerBig3CO1up);

				packageVersionRepository
						.deleteMatrixShapeBig3COXrefBypkgVerBig3CoId(pkgVerBig3CO1up);

				packageVersionRepository
						.deleteShapeValueBenefitOptionForBig3COBypkgVerBig3CoId(pkgVerBig3CO1up);

				packageVersionRepository
						.deleteShapeValueBig3COBypkgVerBig3CoId(pkgVerBig3CO1up);
			}
			packageVersionRepository
					.deletePkgVerBig3COByPkgVerInstanceId(pkgVerInstTreeId);

			packageVersionRepository
					.deleteShapeValueBig3COBypkgVerInstanceId(pkgVerInstTreeId);

			packageVersionRepository
					.deleteShapeValueTreeInstanceBypkgVerInstanceId(pkgVerInstTreeId);

			packageVersionRepository
					.deleteShapeValueBenefitOptionForBig3COBypkgVerInstanceId(pkgVerInstTreeId);

			packageVersionRepository
					.deleteExcelViewInfoBypkgVerInstanceId(pkgVerInstTreeId);

			packageVersionRepository
					.deleteBenefitOptionBig3COBypkgVerInstanceId(pkgVerInstTreeId);

			packageVersionRepository
					.deletePkgVerInstanceTreeById(pkgVerInstTreeId);
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error deleting CommonBenefit  ");
		}

		System.out.println("Done .....");

		return operationResult;

	}

	@Transactional
	public ServiceOperationResult deleteBenefitOffering(int pkgConfigOptionId)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		try {
			packageVersionRepository
					.deletePkgVersionProductXrefByConfigId(pkgConfigOptionId);

			packageVersionRepository
					.deletePkgVerProductBenefitOptionXrefByConfigId(pkgConfigOptionId);

			packageVersionRepository
					.deletePkgConfigOptionBenefitOptionXrefByConfigId(pkgConfigOptionId);

			packageVersionRepository
					.deletePkgConfigOptionByByConfigId(pkgConfigOptionId);
			System.out.println("Done");
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error deleting CommonBenefit  ");
		}

		return operationResult;
	}

	/**
	 * @param benefitOptionData
	 *            PackageVersionBenefitOptionData Object
	 * 
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method Will Save And Update BenefitOption
	 */
	@Transactional
	public ServiceOperationResult saveAndUpdateBenefitOption(
			PackageVersionBenefitOptionData benefitOptionData,
			List<BenefitOptionBig3COData> big3coDatas,
			List<BenefitSelectionTierdataData> optionTierdataDatas)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		PkgVerBenefitOption benefitOption = null;
		int benefitOptionId = 0;
		try {

			if (benefitOptionData.getPkgVerBenefitOption1up() != null
					&& benefitOptionData.getPkgVerBenefitOption1up() > 0) {
				benefitOption = packageVersionRepository
						.findPkgVerBenefitOptionById(benefitOptionData
								.getPkgVerBenefitOption1up());
				benefitOption.setUpdateBy(benefitOptionData.getUpdateBy());
				benefitOption.setUpdateDt(new Date());
			} else {
				benefitOption = new PkgVerBenefitOption();
				benefitOption.setCreateBy(benefitOptionData.getCreateBy());
				benefitOption.setCreateDt(new Date());
				benefitOption.setPkgVersion(packageVersionRepository
						.findPackageVersionById(benefitOptionData
								.getPkgVersion1up()));
				benefitOption.seteBSInstance1up(benefitOptionData
						.geteBSInstance1up());

			}

			benefitOption.setName(benefitOptionData.getBenefitOptionName());
			benefitOption.setDescription(benefitOptionData.getDescription());

			if (benefitOptionData.getPkgVerBenefitOption1up() != null
					&& benefitOptionData.getPkgVerBenefitOption1up() > 0) {
				packageVersionRepository.updateData(benefitOption);
				benefitOptionId = benefitOption.getPkgVerBenefitOption1up();
			} else {
				packageVersionRepository.saveData(benefitOption);
				benefitOptionId = benefitOption.getPkgVerBenefitOption1up();
			}

			if (big3coDatas != null) {
				this.saveUpdateAndDeleteServiceTypeBenefitOption(
						benefitOptionId, benefitOptionData, big3coDatas);
			}
			if (optionTierdataDatas != null) {
				this.saveAndUpdateBenefitSelectionForBenefitOption(
						benefitOptionId, benefitOptionData, optionTierdataDatas);
			}

		} catch (PersistenceException e) {

			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error saving value for Service Type");

		}

		return operationResult;

	}

	/**
	 * @param big3coDatas
	 *            List of BenefitOptionBig3COData
	 * 
	 * @return ServiceOperationResult
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method updates the value of the service type for a
	 *             particular BenefitOption if enable true. If enable is false
	 *             the it will remove from database.
	 */
	@Transactional
	public ServiceOperationResult saveUpdateAndDeleteServiceTypeBenefitOption(
			Integer benefitOptionId,
			PackageVersionBenefitOptionData benefitOptionData,
			List<BenefitOptionBig3COData> big3coDatas)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		List<BenefitOptionBig3CO> benefitOptionBig3COs = null;
		List<Integer> existringList = new ArrayList<Integer>();
		List<Integer> currentList = new ArrayList<Integer>();

		try {

			List<PkgConfigOptionBenefitOptionXref> configOptionsXref = new ArrayList<PkgConfigOptionBenefitOptionXref>();
			configOptionsXref = packageVersionRepository
					.findBenefitOfferingByBenefitOptionId(benefitOptionId);

			List<PkgVerBenefitOption> benefitOptions = new ArrayList<PkgVerBenefitOption>();
			for (PkgConfigOptionBenefitOptionXref configOptionXref : configOptionsXref) {
				if (!configOptionXref.getPkgVerBenefitOption()
						.getPkgVerBenefitOption1up().equals(benefitOptionId))
					benefitOptions.add(configOptionXref
							.getPkgVerBenefitOption());
			}

			List<Integer> benefitOptionIds = new ArrayList<Integer>();

			for (PkgVerBenefitOption benefitOption : benefitOptions) {
				benefitOptionIds.add(benefitOption.getPkgVerBenefitOption1up());
			}

			List<BenefitOptionBig3CO> benefitOptionsBig3COs = new ArrayList<BenefitOptionBig3CO>();
			benefitOptionsBig3COs = packageVersionRepository
					.findListOfPkgVerBenefitOptionBig3CosById(benefitOptionIds);

			if (benefitOptionsBig3COs != null
					&& benefitOptionsBig3COs.size() > 0) {
				for (BenefitOptionBig3CO benefitOption1Big3CO : benefitOptionsBig3COs) {
					int count = 0;
					for (BenefitOptionBig3COData benefitOption2Big3CO : big3coDatas) {
						if (benefitOption1Big3CO.getPkgVerBig3CO()
								.getPkgVerBig3CO1up() == benefitOption2Big3CO
								.getPkgVerBig3CO1up()
								&& benefitOption1Big3CO.getPkgVerInstanceTree()
										.getPkgVerInstanceTree1up() == benefitOption2Big3CO
										.getPkgVerInstanceTree1up()) {
							count++;
						}

					}

					if (count > 1) {
						throw new DataAccessException(
								"Duplicate Service Type Found in Benefit Options");
					}

				}
			}

			benefitOptionBig3COs = packageVersionRepository
					.findAllBenefitOptionBig3COByBenefitOption(benefitOptionId);

			if (benefitOptionBig3COs != null) {
				for (BenefitOptionBig3CO optionBig3CO : benefitOptionBig3COs) {
					existringList.add(optionBig3CO.getBenefitOptionBig3CO1up());
				}

			}

			if (big3coDatas != null) {

				for (BenefitOptionBig3COData optionBig3COData : big3coDatas) {

					if (optionBig3COData.getBenefitOptionBig3CO1up() != null) {
						currentList.add(optionBig3COData
								.getBenefitOptionBig3CO1up());
					}

				}
				List<Integer> commonIdList = new ArrayList<Integer>();
				if (existringList != null) {

					commonIdList = new ArrayList<Integer>(existringList);
					if (currentList != null) {
						commonIdList.retainAll(currentList);
						existringList.removeAll(commonIdList);
					}
				}

				if (existringList != null) {
					for (Integer id : existringList) {

						packageVersionRepository
								.deleteBenefitOptionBig3COById(id);
					}
				}

				for (BenefitOptionBig3COData data : big3coDatas) {
					BenefitOptionBig3CO big3co = new BenefitOptionBig3CO();

					if (data.getBenefitOptionBig3CO1up() != null
							&& data.getBenefitOptionBig3CO1up() > 0) {

						big3co = packageVersionRepository
								.findBenefitOptionBig3COById(data
										.getBenefitOptionBig3CO1up());
						big3co.setUpdateBy(data.getUpdateBy());
						big3co.setUpdateDt(new Date());
						big3co.setCoveredFlag(data
								.getBenefitOptionCoveredFlag());

						big3co.setTreatAs100PctFlag(data
								.getBenefitOptionTreatAs100PctFlag());

						big3co.setPkgVerInstanceTree(packageVersionRepository
								.findPkgVerInstanceTreeById(data
										.getBenefitOptionInstanceTree1up()));
						packageVersionRepository.updateData(big3co);
					} else {
						big3co = new BenefitOptionBig3CO();
						big3co.setCreateBy(data.getCreateBy());
						big3co.setCreateDt(new Date());
						big3co.setCoveredFlag(data
								.getBenefitOptionCoveredFlag());
						big3co.setPkgVersion(packageVersionRepository
								.findPackageVersionById(data.getPkgVersion1up()));
						big3co.setPkgVerBig3CO(packageVersionRepository
								.findPkgVerBig3COById(data.getPkgVerBig3CO1up()));
						big3co.seteBSInstance1up(data.geteBSInstance1up());
						big3co.setsETRMessageSelected(data
								.getsETRMessageSelected());
						big3co.setPkgVerBenefitOption(packageVersionRepository
								.findPkgVerBenefitOptionById(benefitOptionId));

						big3co.setTreatAs100PctFlag(data
								.getBenefitOptionTreatAs100PctFlag());

						big3co.setPkgVerInstanceTree(packageVersionRepository
								.findPkgVerInstanceTreeById(data
										.getBenefitOptionInstanceTree1up()));
						packageVersionRepository.saveData(big3co);
					}

				}

			}

		} catch (PersistenceException e) {

			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error saving value for Service Type");

		}

		return operationResult;
	}

	/**
	 * @param benefitOptionData
	 *            - PackageVersionBenefitOptionData object
	 * @param optionTierdataDatas
	 *            - object of list of BenefitOptionTierdataData
	 * @return ServiceOperationResult
	 * @throws DataAccessException
	 */
	@Transactional
	public ServiceOperationResult saveAndUpdateBenefitSelectionForBenefitOption(
			int benefitOptionId,
			PackageVersionBenefitOptionData benefitOptionData,
			List<BenefitSelectionTierdataData> optionTierdataDatas)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		try {

			if (optionTierdataDatas != null) {
				List<BenefitOptionTierdata> benOptionList = new ArrayList<BenefitOptionTierdata>();
				for (BenefitSelectionTierdataData optionTierdataData : optionTierdataDatas) {

					BenefitOptionTierdata benOption = null;

					if (optionTierdataData.getId() != null
							&& optionTierdataData.getId() > 0) {

						benOption = packageVersionRepository
								.findBenefitOptionTierdataById(optionTierdataData
										.getId());

						benOption.setUpdateBy(optionTierdataData.getUpdateBy());
						benOption.setUpdateDt(new Date());
					} else {
						benOption = new BenefitOptionTierdata();
						benOption.setCreateBy(optionTierdataData.getCreateBy());
						benOption.setUpdateDt(new Date());
					}

					if (optionTierdataData.getAllowedAmountData()
							.getMasterListIntVal1up() != null) {
						benOption.setAllowedAmt(masterListRepository
								.findMasterListIntValById(optionTierdataData
										.getAllowedAmount1up()));
					}
					if (optionTierdataData.getAllowedCtrData()
							.getMasterListIntVal1up() != null) {
						benOption.setAllowedCtr(masterListRepository
								.findMasterListIntValById(optionTierdataData
										.getAllowedCtr1up()));
					}

					if (optionTierdataData.getAllowedAmtShape() != null) {
						benOption.setAllowedAmtShape(templateRepository
								.findTreeShapeById(optionTierdataData
										.getAllowedAmtShape()));
					}

					if (optionTierdataData.getAllowedCtrShape() != null) {
						benOption.setAllowedCtrShape(templateRepository
								.findTreeShapeById(optionTierdataData
										.getAllowedCtrShape()));
					}

					if (optionTierdataData.getApplyCoinsuranceShape() != null) {
						benOption.setApplyCoinsuranceShape(templateRepository
								.findTreeShapeById(optionTierdataData
										.getApplyCoinsuranceShape()));
					}

					benOption.setBenefitOption(packageVersionRepository
							.findPkgVerBenefitOptionById(benefitOptionId));

					if (optionTierdataData.getCoinsuranceNoValData()
							.getMasterListStringVal1up() != null) {
						benOption.setCoinsuranceNoVal(masterListRepository
								.findMasterListStringValById(optionTierdataData
										.getCoinsuranceNoVal1up()));
					}

					if (optionTierdataData.getCoinsuranceYesValData()
							.getMasterListIntVal1up() != null) {
						benOption.setCoinsuranceYesVal(masterListRepository
								.findMasterListIntValById(optionTierdataData
										.getCoinsuranceYesVal1up()));
					}

					if (optionTierdataData.getCoinsuranceNoValShape() != null) {
						benOption.setCoinsuranceNoValShape(templateRepository
								.findTreeShapeById(optionTierdataData
										.getCoinsuranceNoValShape()));
					}
					if (optionTierdataData.getCoinsuranceYesValShape() != null) {
						benOption.setCoinsuranceYesValShape(templateRepository
								.findTreeShapeById(optionTierdataData
										.getCoinsuranceYesValShape()));

					}
					if (optionTierdataData.getCopayShape() != null) {
						benOption.setCopayShape(templateRepository
								.findTreeShapeById(optionTierdataData
										.getCopayShape()));
					}
					if (optionTierdataData.getCopayValData()
							.getMasterListIntVal1up() != null) {
						benOption.setCopayVal(masterListRepository
								.findMasterListIntValById(optionTierdataData
										.getCopayVal1up()));
					}
					if (optionTierdataData.getPkgVerInstanceTreeData()
							.getPkgVerInstanceTree1up() != null) {
						benOption
								.setPkgVerInstanceTree(packageVersionRepository
										.findPkgVerInstanceTreeById(optionTierdataData
												.getPkgVerInstanceTree1up()));
					}
					if (optionTierdataData.getPkgVerBig3CO1up() != null) {
						benOption
								.setPkgVerBig3CO(packageRepository
										.findAllBenefitOptionBig3COBypkgVerBig3COID(optionTierdataData
												.getPkgVerBig3CO1up()));
					}
					benOption.seteBSInstance1up(optionTierdataData
							.geteBSInstance1up());

					benOption.setApplyCoinsurance(optionTierdataData
							.getApplyCoinsurance());
					benOption.setTierNo(optionTierdataData.getTierNo());

					benOptionList.add(benOption);
				}

				for (BenefitOptionTierdata benOpt : benOptionList) {

					if (benOpt.getBenefitOptionTierdata1up() != null
							&& benOpt.getBenefitOptionTierdata1up() > 0) {
						packageVersionRepository.updateData(benOpt);
					} else {
						packageVersionRepository.saveData(benOpt);
					}
				}
			}

		} catch (PersistenceException e) {

			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error saving value for Service Type");

		}

		return operationResult;
	}

	/**
	 * @param pkgVersionId
	 *            Primary key of Package Version
	 * 
	 * @param pkgVersionBenefitOptionId
	 *            Primary key of PkgVerBenefitOption foreign key in
	 *            BenefitOptionBig3CO
	 * 
	 * @param showAll
	 *            Boolean Value
	 * 
	 * @return List BenefitOptionBig3COData object
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method will return list of ServiceType for Benefit
	 *             Option
	 */
	@Transactional(readOnly = true)
	public List<BenefitOptionBig3COData> getServicetypeForBenefitOption(
			int pkgVersionId, int pkgVersionBenefitOptionId, boolean showAll)
			throws DataAccessException {

		List<BenefitOptionBig3CO> benefitOptionBig3COs = packageVersionRepository
				.findEnabledServicetypeforBenefitOption(pkgVersionBenefitOptionId);

		List<Integer> benIntegers = new ArrayList<Integer>();
		for (BenefitOptionBig3CO big3co : benefitOptionBig3COs) {

			benIntegers.add(big3co.getPkgVerBig3CO().getPkgVerBig3CO1up());

		}

		List<PkgVerBig3CO> pkgVerBig3COs = packageVersionRepository
				.findAllServicetypeforBenefitOption(pkgVersionId);

		List<Integer> benefitOptionCoveredList = packageVersionRepository
				.findCoveredBenefitOption(pkgVersionBenefitOptionId);

		List<Integer> benefitOptionTreat100List = packageVersionRepository
				.findTreat100BenefitOption(pkgVersionBenefitOptionId);

		List<BenefitOptionBig3COData> packageVersionDatas = new ArrayList<BenefitOptionBig3COData>();
		if (showAll != true) {
			for (PkgVerBig3CO pkgVerBig3CO : pkgVerBig3COs) {

				BenefitOptionBig3COData big3CoData = new BenefitOptionBig3COData();

				if (benIntegers.contains(pkgVerBig3CO.getPkgVerBig3CO1up())) {

					big3CoData.setEnable(true);

					big3CoData.setPkgVerBig3CO1up(pkgVerBig3CO
							.getPkgVerBig3CO1up());

					if (pkgVerBig3CO.getPkgMasterListForBig3CO() != null) {
						big3CoData.setPkgMasterBig3CO1up(pkgVerBig3CO
								.getPkgMasterListForBig3CO()
								.getPkgMasterListForBig3CO1up());
					}

					if (pkgVerBig3CO.getPkgMasterListForBig3CO() != null) {
						if (pkgVerBig3CO.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition() != null) {
							big3CoData.setMasterServiceDefn1up(pkgVerBig3CO
									.getPkgMasterListForBig3CO()
									.getMasterListServiceDefinition()
									.getMasterListServiceDefinition1up());
						}
					}

					if (pkgVerBig3CO.getPkgMasterListForBig3CO() != null) {
						if (pkgVerBig3CO.getPkgMasterListForBig3CO()
								.getMasterListStringVal() != null) {
							big3CoData.setMasterStringVal1up(pkgVerBig3CO
									.getPkgMasterListForBig3CO()
									.getMasterListStringVal()
									.getMasterListStringVal1up());
						}
					}

					big3CoData.setPkgVersionCoveredFlag(pkgVerBig3CO
							.getCoveredFlag());

					big3CoData.setPkgVersionTreaAs100PctFlag(pkgVerBig3CO
							.getTreatAs100PctFlag());

					if (pkgVerBig3CO.getPkgVerInstanceTree() != null) {
						big3CoData.setPkgVerInstanceTree1up(pkgVerBig3CO
								.getPkgVerInstanceTree()
								.getPkgVerInstanceTree1up());
					}

					big3CoData
							.setBenefitOptionCoveredFlag(benefitOptionCoveredList
									.contains(pkgVerBig3CO.getPkgVerBig3CO1up()));

					big3CoData
							.setBenefitOptionTreatAs100PctFlag(benefitOptionTreat100List
									.contains(pkgVerBig3CO.getPkgVerBig3CO1up()));

					big3CoData
							.setBenefitOptionInstanceTree1up(packageVersionRepository
									.findInstanceTreeBenefitOption(
											pkgVersionBenefitOptionId,
											pkgVerBig3CO.getPkgVerBig3CO1up(),
											pkgVersionId));

					big3CoData.setCreateBy(pkgVerBig3CO.getCreateBy());
					big3CoData.setCreateDt(pkgVerBig3CO.getCreateDt());
					big3CoData.setUpdateBy(pkgVerBig3CO.getUpdateBy());
					big3CoData.setUpdateDt(pkgVerBig3CO.getUpdateDt());
					big3CoData.seteBSInstance1up(pkgVerBig3CO
							.geteBSInstance1up());
					packageVersionDatas.add(big3CoData);

				}

			}

		} else {
			for (PkgVerBig3CO verBig3CO : pkgVerBig3COs) {

				BenefitOptionBig3COData big3CoData = new BenefitOptionBig3COData();

				if (benIntegers.contains(verBig3CO.getPkgVerBig3CO1up()) == true) {

					big3CoData.setEnable(true);

					big3CoData
							.setBenefitOptionCoveredFlag(benefitOptionCoveredList
									.contains(verBig3CO.getPkgVerBig3CO1up()));

					System.out.println(benefitOptionCoveredList
							.contains(verBig3CO.getPkgVerBig3CO1up()));

					big3CoData
							.setBenefitOptionInstanceTree1up(packageVersionRepository
									.findInstanceTreeBenefitOption(
											pkgVersionBenefitOptionId,
											verBig3CO.getPkgVerBig3CO1up(),
											pkgVersionId));

					big3CoData
							.setBenefitOptionTreatAs100PctFlag(benefitOptionTreat100List
									.contains(verBig3CO.getPkgVerBig3CO1up()));

				} else {
					big3CoData.setEnable(false);

					big3CoData.setBenefitOptionCoveredFlag(false);
					big3CoData.setBenefitOptionTreatAs100PctFlag(false);
					big3CoData.setBenefitOptionInstanceTree1up(null);

				}

				big3CoData.setPkgVerBig3CO1up(verBig3CO.getPkgVerBig3CO1up());

				if (verBig3CO.getPkgMasterListForBig3CO() != null) {
					big3CoData.setPkgMasterBig3CO1up(verBig3CO
							.getPkgMasterListForBig3CO()
							.getPkgMasterListForBig3CO1up());
				}

				if (verBig3CO.getPkgMasterListForBig3CO() != null) {
					if (verBig3CO.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition() != null) {
						big3CoData.setMasterServiceDefn1up(verBig3CO
								.getPkgMasterListForBig3CO()
								.getMasterListServiceDefinition()
								.getMasterListServiceDefinition1up());
					}
				}

				if (verBig3CO.getPkgMasterListForBig3CO() != null) {
					if (verBig3CO.getPkgMasterListForBig3CO()
							.getMasterListStringVal() != null) {
						big3CoData.setMasterStringVal1up(verBig3CO
								.getPkgMasterListForBig3CO()
								.getMasterListStringVal()
								.getMasterListStringVal1up());

					}
				}

				big3CoData.setPkgVersionTreaAs100PctFlag(verBig3CO
						.getTreatAs100PctFlag());

				big3CoData.setPkgVersionCoveredFlag(verBig3CO.getCoveredFlag());

				if (verBig3CO.getPkgVerInstanceTree() != null) {
					big3CoData
							.setPkgVerInstanceTree1up(verBig3CO
									.getPkgVerInstanceTree()
									.getPkgVerInstanceTree1up());
				}

				big3CoData.setsETRMessageSelected(verBig3CO
						.getsETRMessageSelected());

				big3CoData.setCreateBy(verBig3CO.getCreateBy());
				big3CoData.setCreateDt(verBig3CO.getCreateDt());
				big3CoData.setUpdateBy(verBig3CO.getUpdateBy());
				big3CoData.setUpdateDt(verBig3CO.getUpdateDt());
				big3CoData.seteBSInstance1up(verBig3CO.geteBSInstance1up());
				packageVersionDatas.add(big3CoData);

			}

		}
		return packageVersionDatas;
	}

	@Transactional(readOnly = true)
	public ProductServiceData getAllBenefitsForAProduct(int productId,
			int page, int maxSize) throws DataAccessException {

		List<BenefitSelectionTierdataData> mergedBenefitsNetworkData = new ArrayList<BenefitSelectionTierdataData>();
		Set<PkgVerInstanceTree> instanceTrees = new HashSet<PkgVerInstanceTree>();
		List<PkgConfigOptionBenefitOptionXref> verBenefitOptions = new ArrayList<PkgConfigOptionBenefitOptionXref>();
		PkgConfigOption product = new PkgConfigOption();
		List<Integer> benefitOptionIds = new ArrayList<Integer>();

		try {
			product = packageRepository.findPkgConfigOptionById(productId);

			instanceTrees = product.getPkgVersion().getPkgVerTree();

			verBenefitOptions = packageVersionRepository
					.findBenefitOptionsForAProductByProductId(productId);

		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		for (PkgConfigOptionBenefitOptionXref benefitOption : verBenefitOptions) {
			benefitOptionIds.add(benefitOption.getPkgVerBenefitOption()
					.getPkgVerBenefitOption1up());
		}

		ProductServiceData productData = new ProductServiceData();
		List<NetworkServiceData> networkDatas = new ArrayList<NetworkServiceData>();
		BenefitSelectionTierdataData defaultData = new BenefitSelectionTierdataData();
		productData.setProductId(productId);
		for (PkgVerInstanceTree instanceTree : instanceTrees) {

			NetworkServiceData networkData = new NetworkServiceData();

			PkgVerInstanceTreeData instanceTreeData = new PkgVerInstanceTreeData();
			instanceTreeData.setPkgVerInstanceTree1up(instanceTree
					.getPkgVerInstanceTree1up());
			instanceTreeData.setName(instanceTree.getName());
			instanceTreeData.setDescription(instanceTree.getDescription());

			networkData.setPkgVerInstanceTreeData(instanceTreeData);
			System.out.println(benefitOptionIds.get(0));

			defaultData = this.getDefaultBenefitSelectionForBenefitOption(
					instanceTree.getPkgVerInstanceTree1up(),
					benefitOptionIds.get(0));

			networkData.setDefaultData(defaultData);

			ServiceData serviceData = new ServiceData();

			mergedBenefitsNetworkData = this
					.getMergedBenefitSelectionForANetwork(
							instanceTree.getPkgVerInstanceTree1up(),
							benefitOptionIds, page, maxSize);
			serviceData.setNetworkBenefits(mergedBenefitsNetworkData);
			networkData.setServiceData(serviceData);
			networkDatas.add(networkData);

		}
		productData.setNetworkBenefit(networkDatas);

		return productData;
	}

	@Transactional(readOnly = true)
	public List<BenefitSelectionTierdataData> getMergedBenefitSelectionForANetwork(
			int instanceTreeId, List<Integer> benefitOptionIds, int page,
			int maxSize) throws DataAccessException {

		Set<BenefitSelectionTierdataData> mergedSetBenefits = new HashSet<BenefitSelectionTierdataData>();
		List<BenefitSelectionTierdataData> benefitOptionTierdataDatas = new ArrayList<BenefitSelectionTierdataData>();
		List<BenefitSelectionTierdataData> benefitSelectionTierdataDatas = new ArrayList<BenefitSelectionTierdataData>();

		for (Integer benefitOptionId : benefitOptionIds) {
			benefitOptionTierdataDatas = this
					.getBenefitSelectionForBenefitOption(instanceTreeId,
							benefitOptionId, page, maxSize).getData();
			mergedSetBenefits.addAll(benefitOptionTierdataDatas);

		}

		benefitSelectionTierdataDatas = this
				.getAllBenifitBasedOnCommonBenifitType(instanceTreeId, page,
						maxSize).getData();

		List<BenefitSelectionTierdataData> removedFinalBenefitSelection = new ArrayList<BenefitSelectionTierdataData>(
				benefitSelectionTierdataDatas);

		for (BenefitSelectionTierdataData mergedBenefitData : mergedSetBenefits) {
			for (BenefitSelectionTierdataData benefitSelectionTierdataData : benefitSelectionTierdataDatas) {
				if (mergedBenefitData.getPkgVerBig3CO1up() == benefitSelectionTierdataData
						.getPkgVerBig3CO1up()
						&& mergedBenefitData.getTierNo() == benefitSelectionTierdataData
								.getTierNo()) {
					removedFinalBenefitSelection
							.remove(benefitSelectionTierdataData);
				}

			}
		}

		mergedSetBenefits.addAll(removedFinalBenefitSelection);
		List<BenefitSelectionTierdataData> mergedBenefits = new ArrayList<BenefitSelectionTierdataData>(
				mergedSetBenefits);
		return mergedBenefits;
	}

	@Transactional(readOnly = true)
	public List<MatrixShapeBig3COMessageXXrefData> getAllMessageValForBenefitSelectionOnPkgVersion(
			int benSelTierData1up, int treeShape1up) throws DataAccessException {
		List<MatrixShapeBig3COMessageXXref> matrixBig3coMessageVal = null;
		List<MatrixShapeMessageXref> matrixShapeMessage = null;
		BenefitSelectionTierdata benefitSelectionTierdata = new BenefitSelectionTierdata();

		try {
			matrixShapeMessage = packageVersionRepository
					.findAllMessageValByTreeShapeId(treeShape1up);

			benefitSelectionTierdata = packageVersionRepository
					.findBenefitSelectionTierdataById(benSelTierData1up);

			matrixBig3coMessageVal = packageVersionRepository
					.findAllEnableMessageValForPkgVer(benefitSelectionTierdata
							.getPkgVerBig3CO().getPkgVerBig3CO1up(),
							treeShape1up);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<Integer> messageValIds = new ArrayList<Integer>();

		for (MatrixShapeBig3COMessageXXref messageBig3CoXref : matrixBig3coMessageVal) {

			messageValIds.add(messageBig3CoXref
					.getMatrixShapeBig3COMessageXXrefId()
					.getMasterListMessageVal().getMasterListMessageVal1up());

		}

		List<MatrixShapeBig3COMessageXXrefData> matrixMessageDatas = new ArrayList<MatrixShapeBig3COMessageXXrefData>();

		for (MatrixShapeMessageXref messageXref : matrixShapeMessage) {

			MatrixShapeBig3COMessageXXrefData big3coMessageXXrefData = new MatrixShapeBig3COMessageXXrefData();
			big3coMessageXXrefData.seteBSInstance1up(messageXref
					.geteBSInstance1up());
			big3coMessageXXrefData.setCreateBy(messageXref.getCreateBy());
			big3coMessageXXrefData.setCreateDt(messageXref.getCreateDt());
			big3coMessageXXrefData.setUpdateBy(messageXref.getUpdateBy());
			big3coMessageXXrefData.setUpdateDt(messageXref.getUpdateDt());

			MasterListMessageValData messageValData = new MasterListMessageValData();
			messageValData.setMasterListMessageVal1up(messageXref
					.getMatrixShapeMessageXrefId().getMasterListMessageVal()
					.getMasterListMessageVal1up());
			messageValData.setMessageValue(messageXref
					.getMatrixShapeMessageXrefId().getMasterListMessageVal()
					.getMessageValue());
			messageValData.setMessageType(messageXref
					.getMatrixShapeMessageXrefId().getMasterListMessageVal()
					.getMessageType());

			big3coMessageXXrefData.setMasterListMessageVal(messageValData);

			big3coMessageXXrefData.setPkgVerBig3CO1up(benefitSelectionTierdata
					.getPkgVerBig3CO().getPkgVerBig3CO1up());

			big3coMessageXXrefData.setTreeShape1up(treeShape1up);

			if (messageValIds.contains(messageXref
					.getMatrixShapeMessageXrefId().getMasterListMessageVal()
					.getMasterListMessageVal1up())) {

				big3coMessageXXrefData.setEnableFlag(true);

			} else {
				big3coMessageXXrefData.setEnableFlag(false);
			}

			matrixMessageDatas.add(big3coMessageXXrefData);

		}

		return matrixMessageDatas;
	}

	@Transactional(readOnly = true)
	public List<MatrixShapeBig3COLimitXXrefData> getAllLimitValForBenefitSelectionOnPkgVersion(
			int BenefitSelectionTierdataId, int treeShapeId)
			throws DataAccessException {

		List<MatrixShapeBig3COLimitXXrefData> limitXXrefDatas = new ArrayList<MatrixShapeBig3COLimitXXrefData>();
		MatrixShapeBig3COLimitXXrefData limitXXrefData = null;
		int pkgVerBig3CoId = 0;

		try {
			List<MatrixShapeLimitXref> matrixShapeLimitXrefs = templateRepository
					.findActiveMatrixShapeLimitXrefById(treeShapeId);

			BenefitSelectionTierdata selectionTierdata = packageVersionRepository
					.findBenefitSelectionTierdataById(BenefitSelectionTierdataId);

			if (selectionTierdata != null) {

				pkgVerBig3CoId = selectionTierdata.getPkgVerBig3CO()
						.getPkgVerBig3CO1up();
			}

			List<MatrixShapeBig3COLimitXXref> big3coLimitXXrefs = packageRepository
					.findAllEnableLimitValForPkgVer(treeShapeId, pkgVerBig3CoId);

			List<Integer> masterListIds = new ArrayList<Integer>();

			for (MatrixShapeBig3COLimitXXref matrixShapeBig3COLimitXXref : big3coLimitXXrefs) {
				masterListIds.add(matrixShapeBig3COLimitXXref
						.getMatrixShapeBig3COLimitXXrefId()
						.getMasterListLimitVal().getMasterListLimitVal1up());

			}

			for (MatrixShapeLimitXref matrixShapeLimitXref : matrixShapeLimitXrefs) {

				limitXXrefData = new MatrixShapeBig3COLimitXXrefData();

				MasterListLimitValData limitValData = new MasterListLimitValData();

				limitValData.setMasterListLimitValId(matrixShapeLimitXref
						.getMatrixShapeLimitXrefId().getMasterListLimitVal()
						.getMasterListLimitVal1up());
				limitValData.setLimitName(matrixShapeLimitXref
						.getMatrixShapeLimitXrefId().getMasterListLimitVal()
						.getLimitName());
				limitValData.setLimitDescription(matrixShapeLimitXref
						.getMatrixShapeLimitXrefId().getMasterListLimitVal()
						.getLimitDescription());
				limitValData.setBenefitAmount(matrixShapeLimitXref
						.getMatrixShapeLimitXrefId().getMasterListLimitVal()
						.getBenefitAmount());
				limitValData.setQuantityFrom(matrixShapeLimitXref
						.getMatrixShapeLimitXrefId().getMasterListLimitVal()
						.getQuantityFrom());
				limitValData.setQuantityTo(matrixShapeLimitXref
						.getMatrixShapeLimitXrefId().getMasterListLimitVal()
						.getQuantityTo());
				limitValData.setReinstatementAmount(matrixShapeLimitXref
						.getMatrixShapeLimitXrefId().getMasterListLimitVal()
						.getReinstatementAmount());

				limitXXrefData.setMasterListLimitVal(limitValData);

				limitXXrefData.setPkgVerBig3CO1up(selectionTierdata
						.getPkgVerBig3CO().getPkgVerBig3CO1up());

				limitXXrefData.setTreeShape1up(treeShapeId);

				if (masterListIds.contains(matrixShapeLimitXref
						.getMatrixShapeLimitXrefId().getMasterListLimitVal()
						.getMasterListLimitVal1up())) {
					limitXXrefData.setEnable(true);
				} else {
					limitXXrefData.setEnable(false);
				}
				limitXXrefDatas.add(limitXXrefData);

			}
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		return limitXXrefDatas;
	}

	@SuppressWarnings("null")
	@Transactional
	public ServiceOperationResult saveAndUpdateBenefitSelectionInShapeValueBig3Co(
			int instanceTreeId,
			List<BenefitSelectionTierdataData> benefitSelectionTierDataList)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		if (benefitSelectionTierDataList == null) {
			throw new DataAccessException();
		}
		List<ExcelViewInfo> excelViewInfos = packageVersionRepository
				.findAllTreeShapeForCommonBenefit(instanceTreeId);
		try {

			for (BenefitSelectionTierdataData selectionTierdataData : benefitSelectionTierDataList) {

				ShapeValueBig3CO temp = null;
				for (ExcelViewInfo excelViewInfo : excelViewInfos) {

					if (selectionTierdataData.getApplyDeductibleShape() != null) {
						if (excelViewInfo.getHeader()
								.equals("Apply Deductible")
								&& selectionTierdataData
										.getApplyDeductibleShape().equals(
												excelViewInfo.getTreeShape()
														.getTreeShape1up())) {

							temp = packageRepository.findShapeValueBig3COById(
									selectionTierdataData
											.getApplyDeductibleShape(),
									instanceTreeId, selectionTierdataData
											.getPkgVerBig3CO1up());

							if (temp != null) {
								if (selectionTierdataData.getApplyDeductible() == true) {
									temp.setIfYNTestAnswerYesFlag(false);
									temp.setIfYNTestAnswerNoFlag(true);
								} else {
									temp.setIfYNTestAnswerYesFlag(true);
									temp.setIfYNTestAnswerNoFlag(false);
								}
								temp.setUpdateBy(selectionTierdataData
										.getUpdateBy());
								temp.setUpdateDt(new Date());
								packageVersionRepository.updateData(temp);
							} else {
								ShapeValueBig3COId shapeValueBig3COId = new ShapeValueBig3COId();
								shapeValueBig3COId
										.setTreeShape(templateRepository
												.findTreeShapeById(selectionTierdataData
														.getApplyDeductibleShape()));
								shapeValueBig3COId
										.setPkgVerBig3CO(packageVersionRepository
												.findPkgVerBig3COById(selectionTierdataData
														.getPkgVerBig3CO1up()));
								shapeValueBig3COId
										.setPkgVerInstanceTree(packageVersionRepository
												.findPkgVerInstanceTreeById(instanceTreeId));
								temp.setShapeValueBig3COId(shapeValueBig3COId);
								temp.seteBSInstance1up(selectionTierdataData
										.geteBSInstance1up());
								if (selectionTierdataData.getApplyDeductible() == true) {
									temp.setIfYNTestAnswerYesFlag(true);
									temp.setIfYNTestAnswerNoFlag(false);
								} else {
									temp.setIfYNTestAnswerYesFlag(false);
									temp.setIfYNTestAnswerNoFlag(true);
								}

								temp.setCreateBy(selectionTierdataData
										.getCreateBy());
								temp.setCreateDt(new Date());
								packageVersionRepository.saveData(temp);

							}

						}
					}
					if (selectionTierdataData.getApplyIndDeductibleShape() != null) {

						if (excelViewInfo.getHeader().equals("IND")
								&& selectionTierdataData
										.getApplyIndDeductibleShape().equals(
												excelViewInfo.getTreeShape()
														.getTreeShape1up())) {

							temp = packageRepository.findShapeValueBig3COById(
									selectionTierdataData
											.getApplyIndDeductibleShape(),
									instanceTreeId, selectionTierdataData
											.getPkgVerBig3CO1up());

							if (temp != null) {
								temp.setMasterListIntVal(masterListRepository
										.findMasterListIntValById(selectionTierdataData
												.getApplyIndDeductible1up()));
								temp.setUpdateBy(selectionTierdataData
										.getUpdateBy());
								temp.setUpdateDt(new Date());
								packageVersionRepository.updateData(temp);
							} else {
								ShapeValueBig3COId shapeValueBig3COId = new ShapeValueBig3COId();
								shapeValueBig3COId
										.setTreeShape(templateRepository
												.findTreeShapeById(selectionTierdataData
														.getApplyIndDeductibleShape()));
								shapeValueBig3COId
										.setPkgVerBig3CO(packageVersionRepository
												.findPkgVerBig3COById(selectionTierdataData
														.getPkgVerBig3CO1up()));
								shapeValueBig3COId
										.setPkgVerInstanceTree(packageVersionRepository
												.findPkgVerInstanceTreeById(instanceTreeId));
								temp.setShapeValueBig3COId(shapeValueBig3COId);
								temp.setMasterListIntVal(masterListRepository
										.findMasterListIntValById(selectionTierdataData
												.getApplyIndDeductible1up()));
								temp.seteBSInstance1up(selectionTierdataData
										.geteBSInstance1up());
								temp.setIfYNTestAnswerYesFlag(false);
								temp.setIfYNTestAnswerNoFlag(false);

								temp.setCreateBy(selectionTierdataData
										.getCreateBy());
								temp.setCreateDt(new Date());
								packageVersionRepository.saveData(temp);

							}

						}
					}
					if (selectionTierdataData.getApplyFamDeductibleShape() != null) {

						if (excelViewInfo.getHeader().equals("FAM")
								&& selectionTierdataData
										.getApplyFamDeductibleShape().equals(
												excelViewInfo.getTreeShape()
														.getTreeShape1up())) {

							temp = packageRepository.findShapeValueBig3COById(
									selectionTierdataData
											.getApplyFamDeductibleShape(),
									instanceTreeId, selectionTierdataData
											.getPkgVerBig3CO1up());

							if (temp != null) {
								temp.setMasterListIntVal(masterListRepository
										.findMasterListIntValById(selectionTierdataData
												.getApplyFamDeductible1up()));
								temp.setUpdateBy(selectionTierdataData
										.getUpdateBy());
								temp.setUpdateDt(new Date());
								packageVersionRepository.updateData(temp);
							} else {
								ShapeValueBig3COId shapeValueBig3COId = new ShapeValueBig3COId();
								shapeValueBig3COId
										.setTreeShape(templateRepository
												.findTreeShapeById(selectionTierdataData
														.getApplyFamDeductibleShape()));
								shapeValueBig3COId
										.setPkgVerBig3CO(packageVersionRepository
												.findPkgVerBig3COById(selectionTierdataData
														.getPkgVerBig3CO1up()));
								shapeValueBig3COId
										.setPkgVerInstanceTree(packageVersionRepository
												.findPkgVerInstanceTreeById(instanceTreeId));
								temp.setShapeValueBig3COId(shapeValueBig3COId);
								temp.setMasterListIntVal(masterListRepository
										.findMasterListIntValById(selectionTierdataData
												.getApplyFamDeductible1up()));
								temp.seteBSInstance1up(selectionTierdataData
										.geteBSInstance1up());
								temp.setIfYNTestAnswerYesFlag(false);
								temp.setIfYNTestAnswerNoFlag(false);

								temp.setCreateBy(selectionTierdataData
										.getCreateBy());
								temp.setCreateDt(new Date());
								packageVersionRepository.saveData(temp);

							}

						}
					}
					if (selectionTierdataData.getApplyDeductibleNoShape() != null) {

						if (excelViewInfo.getHeader().equals(
								"Apply Deductible-No")
								&& selectionTierdataData
										.getApplyDeductibleNoShape().equals(
												excelViewInfo.getTreeShape()
														.getTreeShape1up())) {

							temp = packageRepository.findShapeValueBig3COById(
									selectionTierdataData
											.getApplyDeductibleNoShape(),
									instanceTreeId, selectionTierdataData
											.getPkgVerBig3CO1up());

							if (temp != null) {
								temp.setMasterListStringVal(masterListRepository
										.findMasterListStringValById(selectionTierdataData
												.getApplyDeductibleNo1up()));
								temp.setUpdateBy(selectionTierdataData
										.getUpdateBy());
								temp.setUpdateDt(new Date());
								packageVersionRepository.updateData(temp);
							} else {
								ShapeValueBig3COId shapeValueBig3COId = new ShapeValueBig3COId();
								shapeValueBig3COId
										.setTreeShape(templateRepository
												.findTreeShapeById(selectionTierdataData
														.getApplyDeductibleNoShape()));
								shapeValueBig3COId
										.setPkgVerBig3CO(packageVersionRepository
												.findPkgVerBig3COById(selectionTierdataData
														.getPkgVerBig3CO1up()));
								shapeValueBig3COId
										.setPkgVerInstanceTree(packageVersionRepository
												.findPkgVerInstanceTreeById(instanceTreeId));
								temp.setShapeValueBig3COId(shapeValueBig3COId);
								temp.setMasterListStringVal(masterListRepository
										.findMasterListStringValById(selectionTierdataData
												.getApplyDeductibleNo1up()));
								temp.seteBSInstance1up(selectionTierdataData
										.geteBSInstance1up());
								temp.setIfYNTestAnswerYesFlag(false);
								temp.setIfYNTestAnswerNoFlag(false);

								temp.setCreateBy(selectionTierdataData
										.getCreateBy());
								temp.setCreateDt(new Date());
								packageVersionRepository.saveData(temp);

							}

						}
					}
					if (selectionTierdataData.getApplyCoinsuranceShape() != null) {
						if (excelViewInfo.getHeader().equals(
								"Apply Coinsurance")
								&& selectionTierdataData
										.getApplyCoinsuranceShape().equals(
												excelViewInfo.getTreeShape()
														.getTreeShape1up())) {

							temp = packageRepository.findShapeValueBig3COById(
									selectionTierdataData
											.getApplyCoinsuranceShape(),
									instanceTreeId, selectionTierdataData
											.getPkgVerBig3CO1up());

							if (temp != null) {
								if (selectionTierdataData.getApplyCoinsurance() == true) {
									temp.setIfYNTestAnswerYesFlag(true);
									temp.setIfYNTestAnswerNoFlag(false);
								} else {
									temp.setIfYNTestAnswerYesFlag(false);
									temp.setIfYNTestAnswerNoFlag(true);
								}
								temp.setUpdateBy(selectionTierdataData
										.getUpdateBy());
								temp.setUpdateDt(new Date());
								packageVersionRepository.updateData(temp);
							} else {
								ShapeValueBig3COId shapeValueBig3COId = new ShapeValueBig3COId();
								shapeValueBig3COId
										.setTreeShape(templateRepository
												.findTreeShapeById(selectionTierdataData
														.getApplyDeductibleNoShape()));
								shapeValueBig3COId
										.setPkgVerBig3CO(packageVersionRepository
												.findPkgVerBig3COById(selectionTierdataData
														.getPkgVerBig3CO1up()));
								shapeValueBig3COId
										.setPkgVerInstanceTree(packageVersionRepository
												.findPkgVerInstanceTreeById(instanceTreeId));
								temp.setShapeValueBig3COId(shapeValueBig3COId);
								temp.seteBSInstance1up(selectionTierdataData
										.geteBSInstance1up());
								if (selectionTierdataData.getApplyDeductible() == true) {
									temp.setIfYNTestAnswerYesFlag(true);
									temp.setIfYNTestAnswerNoFlag(false);
								} else {
									temp.setIfYNTestAnswerYesFlag(false);
									temp.setIfYNTestAnswerNoFlag(true);
								}

								temp.setCreateBy(selectionTierdataData
										.getCreateBy());
								temp.setCreateDt(new Date());
								packageVersionRepository.saveData(temp);

							}

						}
					}
					if (selectionTierdataData.getCoinsuranceYesValShape() != null) {
						if (excelViewInfo.getHeader().equals(
								"Apply Coinsurance-Yes")
								&& selectionTierdataData
										.getCoinsuranceYesValShape().equals(
												excelViewInfo.getTreeShape()
														.getTreeShape1up())) {

							temp = packageRepository.findShapeValueBig3COById(
									selectionTierdataData
											.getCoinsuranceYesValShape(),
									instanceTreeId, selectionTierdataData
											.getPkgVerBig3CO1up());

							if (temp != null) {
								temp.setMasterListIntVal(masterListRepository
										.findMasterListIntValById(selectionTierdataData
												.getCoinsuranceYesVal1up()));
								temp.setUpdateBy(selectionTierdataData
										.getUpdateBy());
								temp.setUpdateDt(new Date());
								packageVersionRepository.updateData(temp);
							} else {
								ShapeValueBig3COId shapeValueBig3COId = new ShapeValueBig3COId();
								shapeValueBig3COId
										.setTreeShape(templateRepository
												.findTreeShapeById(selectionTierdataData
														.getCoinsuranceYesValShape()));
								shapeValueBig3COId
										.setPkgVerBig3CO(packageVersionRepository
												.findPkgVerBig3COById(selectionTierdataData
														.getPkgVerBig3CO1up()));
								shapeValueBig3COId
										.setPkgVerInstanceTree(packageVersionRepository
												.findPkgVerInstanceTreeById(instanceTreeId));
								temp.setShapeValueBig3COId(shapeValueBig3COId);
								temp.setMasterListIntVal(masterListRepository
										.findMasterListIntValById(selectionTierdataData
												.getCoinsuranceYesVal1up()));
								temp.seteBSInstance1up(selectionTierdataData
										.geteBSInstance1up());
								temp.setIfYNTestAnswerYesFlag(false);
								temp.setIfYNTestAnswerNoFlag(false);

								temp.setCreateBy(selectionTierdataData
										.getCreateBy());
								temp.setCreateDt(new Date());
								packageVersionRepository.saveData(temp);

							}

						}
					}
					if (selectionTierdataData.getCoinsuranceNoValShape() != null) {
						if (excelViewInfo.getHeader().equals(
								"Apply Coinsurance-No")
								&& selectionTierdataData
										.getCoinsuranceNoValShape().equals(
												excelViewInfo.getTreeShape()
														.getTreeShape1up())) {

							temp = packageRepository.findShapeValueBig3COById(
									selectionTierdataData
											.getCoinsuranceNoValShape(),
									instanceTreeId, selectionTierdataData
											.getPkgVerBig3CO1up());

							if (temp != null) {
								temp.setMasterListStringVal(masterListRepository
										.findMasterListStringValById(selectionTierdataData
												.getCoinsuranceNoVal1up()));
								temp.setUpdateBy(selectionTierdataData
										.getUpdateBy());
								temp.setUpdateDt(new Date());
								packageVersionRepository.updateData(temp);
							} else {
								ShapeValueBig3COId shapeValueBig3COId = new ShapeValueBig3COId();
								shapeValueBig3COId
										.setTreeShape(templateRepository
												.findTreeShapeById(selectionTierdataData
														.getCoinsuranceNoValShape()));
								shapeValueBig3COId
										.setPkgVerBig3CO(packageVersionRepository
												.findPkgVerBig3COById(selectionTierdataData
														.getPkgVerBig3CO1up()));
								shapeValueBig3COId
										.setPkgVerInstanceTree(packageVersionRepository
												.findPkgVerInstanceTreeById(instanceTreeId));
								temp.setShapeValueBig3COId(shapeValueBig3COId);
								temp.setMasterListStringVal(masterListRepository
										.findMasterListStringValById(selectionTierdataData
												.getCoinsuranceNoVal1up()));
								temp.seteBSInstance1up(selectionTierdataData
										.geteBSInstance1up());
								temp.setIfYNTestAnswerYesFlag(false);
								temp.setIfYNTestAnswerNoFlag(false);

								temp.setCreateBy(selectionTierdataData
										.getCreateBy());
								temp.setCreateDt(new Date());
								packageVersionRepository.saveData(temp);

							}

						}
					}
					if (selectionTierdataData.getCopayShape() != null) {
						if (excelViewInfo.getHeader().equals("Copay")
								&& selectionTierdataData.getCopayShape()
										.equals(excelViewInfo.getTreeShape()
												.getTreeShape1up())) {

							temp = packageRepository.findShapeValueBig3COById(
									selectionTierdataData.getCopayShape(),
									instanceTreeId,
									selectionTierdataData.getPkgVerBig3CO1up());

							if (temp != null) {
								temp.setMasterListIntVal(masterListRepository
										.findMasterListIntValById(selectionTierdataData
												.getCopayVal1up()));
								temp.setUpdateBy(selectionTierdataData
										.getUpdateBy());
								temp.setUpdateDt(new Date());
								packageVersionRepository.updateData(temp);
							} else {
								ShapeValueBig3COId shapeValueBig3COId = new ShapeValueBig3COId();
								shapeValueBig3COId
										.setTreeShape(templateRepository
												.findTreeShapeById(selectionTierdataData
														.getCopayShape()));
								shapeValueBig3COId
										.setPkgVerBig3CO(packageVersionRepository
												.findPkgVerBig3COById(selectionTierdataData
														.getPkgVerBig3CO1up()));
								shapeValueBig3COId
										.setPkgVerInstanceTree(packageVersionRepository
												.findPkgVerInstanceTreeById(instanceTreeId));
								temp.setShapeValueBig3COId(shapeValueBig3COId);
								temp.setMasterListIntVal(masterListRepository
										.findMasterListIntValById(selectionTierdataData
												.getCopayVal1up()));
								temp.seteBSInstance1up(selectionTierdataData
										.geteBSInstance1up());
								temp.setIfYNTestAnswerYesFlag(false);
								temp.setIfYNTestAnswerNoFlag(false);

								temp.setCreateBy(selectionTierdataData
										.getCreateBy());
								temp.setCreateDt(new Date());
								packageVersionRepository.saveData(temp);

							}

						}
					}
					if (selectionTierdataData.getAllowedAmtShape() != null) {
						if (excelViewInfo.getHeader().equals("Allowed Amt")
								&& selectionTierdataData.getAllowedAmtShape()
										.equals(excelViewInfo.getTreeShape()
												.getTreeShape1up())) {

							temp = packageRepository.findShapeValueBig3COById(
									selectionTierdataData.getAllowedAmtShape(),
									instanceTreeId,
									selectionTierdataData.getPkgVerBig3CO1up());

							if (temp != null) {
								temp.setMasterListIntVal(masterListRepository
										.findMasterListIntValById(selectionTierdataData
												.getAllowedAmount1up()));
								temp.setUpdateBy(selectionTierdataData
										.getUpdateBy());
								temp.setUpdateDt(new Date());
								packageVersionRepository.updateData(temp);
							} else {
								ShapeValueBig3COId shapeValueBig3COId = new ShapeValueBig3COId();
								shapeValueBig3COId
										.setTreeShape(templateRepository
												.findTreeShapeById(selectionTierdataData
														.getAllowedAmtShape()));
								shapeValueBig3COId
										.setPkgVerBig3CO(packageVersionRepository
												.findPkgVerBig3COById(selectionTierdataData
														.getPkgVerBig3CO1up()));
								shapeValueBig3COId
										.setPkgVerInstanceTree(packageVersionRepository
												.findPkgVerInstanceTreeById(instanceTreeId));
								temp.setShapeValueBig3COId(shapeValueBig3COId);
								temp.setMasterListIntVal(masterListRepository
										.findMasterListIntValById(selectionTierdataData
												.getAllowedAmount1up()));
								temp.seteBSInstance1up(selectionTierdataData
										.geteBSInstance1up());
								temp.setIfYNTestAnswerYesFlag(false);
								temp.setIfYNTestAnswerNoFlag(false);

								temp.setCreateBy(selectionTierdataData
										.getCreateBy());
								temp.setCreateDt(new Date());
								packageVersionRepository.saveData(temp);

							}

						}
					}
					if (selectionTierdataData.getAllowedCtrShape() != null) {
						if (excelViewInfo.getHeader().equals("Allowed Ctr")
								&& selectionTierdataData.getAllowedCtrShape()
										.equals(excelViewInfo.getTreeShape()
												.getTreeShape1up())) {

							temp = packageRepository.findShapeValueBig3COById(
									selectionTierdataData.getAllowedCtrShape(),
									instanceTreeId,
									selectionTierdataData.getPkgVerBig3CO1up());

							if (temp != null) {
								temp.setMasterListIntVal(masterListRepository
										.findMasterListIntValById(selectionTierdataData
												.getAllowedCtr1up()));
								temp.setUpdateBy(selectionTierdataData
										.getUpdateBy());
								temp.setUpdateDt(new Date());
								packageVersionRepository.updateData(temp);
							} else {
								ShapeValueBig3COId shapeValueBig3COId = new ShapeValueBig3COId();
								shapeValueBig3COId
										.setTreeShape(templateRepository
												.findTreeShapeById(selectionTierdataData
														.getAllowedCtrShape()));
								shapeValueBig3COId
										.setPkgVerBig3CO(packageVersionRepository
												.findPkgVerBig3COById(selectionTierdataData
														.getPkgVerBig3CO1up()));
								shapeValueBig3COId
										.setPkgVerInstanceTree(packageVersionRepository
												.findPkgVerInstanceTreeById(instanceTreeId));
								temp.setShapeValueBig3COId(shapeValueBig3COId);
								temp.setMasterListIntVal(masterListRepository
										.findMasterListIntValById(selectionTierdataData
												.getAllowedCtr1up()));
								temp.seteBSInstance1up(selectionTierdataData
										.geteBSInstance1up());
								temp.setIfYNTestAnswerYesFlag(false);
								temp.setIfYNTestAnswerNoFlag(false);

								temp.setCreateBy(selectionTierdataData
										.getCreateBy());
								temp.setCreateDt(new Date());
								packageVersionRepository.saveData(temp);

							}

						}
					}
					if (selectionTierdataData.getFacetShape() != null) {
						if (excelViewInfo.getHeader().equals("Facets Rule")
								&& selectionTierdataData.getFacetShape()
										.equals(excelViewInfo.getTreeShape()
												.getTreeShape1up())) {

							temp = packageRepository.findShapeValueBig3COById(
									selectionTierdataData.getFacetShape(),
									instanceTreeId,
									selectionTierdataData.getPkgVerBig3CO1up());

							if (temp != null) {
								temp.setUpdateBy(selectionTierdataData
										.getUpdateBy());
								temp.setUpdateDt(new Date());
								packageVersionRepository.updateData(temp);
							} else {
								ShapeValueBig3COId shapeValueBig3COId = new ShapeValueBig3COId();
								shapeValueBig3COId
										.setTreeShape(templateRepository
												.findTreeShapeById(selectionTierdataData
														.getFacetShape()));
								shapeValueBig3COId
										.setPkgVerBig3CO(packageVersionRepository
												.findPkgVerBig3COById(selectionTierdataData
														.getPkgVerBig3CO1up()));
								shapeValueBig3COId
										.setPkgVerInstanceTree(packageVersionRepository
												.findPkgVerInstanceTreeById(instanceTreeId));
								temp.setShapeValueBig3COId(shapeValueBig3COId);
								temp.seteBSInstance1up(selectionTierdataData
										.geteBSInstance1up());
								temp.setIfYNTestAnswerYesFlag(false);
								temp.setIfYNTestAnswerNoFlag(false);

								temp.setCreateBy(selectionTierdataData
										.getCreateBy());
								temp.setCreateDt(new Date());
								packageVersionRepository.saveData(temp);

							}

						}
					}
				}
			}

		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error saving value for Service Type");
			throw new DataAccessException(e.getMessage());

		}

		return operationResult;

	}

	@SuppressWarnings("null")
	@Transactional
	public ServiceOperationResult saveAndUpdateBenefitSelectionInShapeValueBenefitOptionForBig3Co(
			int instanceTreeId,
			List<BenefitSelectionTierdataData> benefitSelectionTierDataList)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		if (benefitSelectionTierDataList == null) {
			throw new DataAccessException();
		}
		List<ExcelViewInfo> excelViewInfos = packageVersionRepository
				.findAllTreeShapeForCommonBenefit(instanceTreeId);
		try {

			for (BenefitSelectionTierdataData selectionTierdataData : benefitSelectionTierDataList) {

				ShapeValueBenefitOptionForBig3CO temp = null;
				for (ExcelViewInfo excelViewInfo : excelViewInfos) {

					if (selectionTierdataData.getApplyDeductibleShape() != null) {
						if (excelViewInfo.getHeader()
								.equals("Apply Deductible")
								&& selectionTierdataData
										.getApplyDeductibleShape().equals(
												excelViewInfo.getTreeShape()
														.getTreeShape1up())) {

							temp = packageRepository
									.findShapeValueBenefitOptionForBig3COById(
											selectionTierdataData
													.getApplyDeductibleShape(),
											instanceTreeId,
											selectionTierdataData
													.getPkgVerBig3CO1up(),
											selectionTierdataData
													.getBenefitOption1up());

							if (temp != null) {
								if (selectionTierdataData.getApplyDeductible() == true) {
									temp.setIfYNTestAnswerYesFlag(false);
									temp.setIfYNTestAnswerNoFlag(true);
								} else {
									temp.setIfYNTestAnswerYesFlag(true);
									temp.setIfYNTestAnswerNoFlag(false);
								}
								temp.setUpdateBy(selectionTierdataData
										.getUpdateBy());
								temp.setUpdateDt(new Date());
								packageVersionRepository.updateData(temp);
							} else {
								ShapeValueBenefitOptionForBig3COId shapeValueBenefitOptionForBig3COId = new ShapeValueBenefitOptionForBig3COId();
								shapeValueBenefitOptionForBig3COId
										.setTreeShape(templateRepository
												.findTreeShapeById(selectionTierdataData
														.getApplyDeductibleShape()));
								shapeValueBenefitOptionForBig3COId
										.setPkgVerBig3CO(packageVersionRepository
												.findPkgVerBig3COById(selectionTierdataData
														.getPkgVerBig3CO1up()));
								shapeValueBenefitOptionForBig3COId
										.setPkgVerInstanceTree(packageVersionRepository
												.findPkgVerInstanceTreeById(instanceTreeId));

								shapeValueBenefitOptionForBig3COId
										.setPkgVerBenefitOption(packageVersionRepository
												.findPkgVerBenefitOptionById(selectionTierdataData
														.getBenefitOption1up()));
								temp.setShapeValueBenefitOptionForBig3COId(shapeValueBenefitOptionForBig3COId);
								temp.seteBSInstance1up(selectionTierdataData
										.geteBSInstance1up());
								if (selectionTierdataData.getApplyDeductible() == true) {
									temp.setIfYNTestAnswerYesFlag(true);
									temp.setIfYNTestAnswerNoFlag(false);
								} else {
									temp.setIfYNTestAnswerYesFlag(false);
									temp.setIfYNTestAnswerNoFlag(true);
								}

								temp.setCreateBy(selectionTierdataData
										.getCreateBy());
								temp.setCreateDt(new Date());
								packageVersionRepository.saveData(temp);

							}

						}
					}
					if (selectionTierdataData.getApplyIndDeductibleShape() != null) {

						if (excelViewInfo.getHeader().equals("IND")
								&& selectionTierdataData
										.getApplyIndDeductibleShape().equals(
												excelViewInfo.getTreeShape()
														.getTreeShape1up())) {

							temp = packageRepository
									.findShapeValueBenefitOptionForBig3COById(
											selectionTierdataData
													.getApplyIndDeductibleShape(),
											instanceTreeId,
											selectionTierdataData
													.getPkgVerBig3CO1up(),
											selectionTierdataData
													.getBenefitOption1up());

							if (temp != null) {
								temp.setMasterListIntVal(masterListRepository
										.findMasterListIntValById(selectionTierdataData
												.getApplyIndDeductible1up()));
								temp.setUpdateBy(selectionTierdataData
										.getUpdateBy());
								temp.setUpdateDt(new Date());
								packageVersionRepository.updateData(temp);
							} else {
								ShapeValueBenefitOptionForBig3COId shapeValueBenefitOptionForBig3COId = new ShapeValueBenefitOptionForBig3COId();
								shapeValueBenefitOptionForBig3COId
										.setTreeShape(templateRepository
												.findTreeShapeById(selectionTierdataData
														.getApplyIndDeductibleShape()));
								shapeValueBenefitOptionForBig3COId
										.setPkgVerBig3CO(packageVersionRepository
												.findPkgVerBig3COById(selectionTierdataData
														.getPkgVerBig3CO1up()));
								shapeValueBenefitOptionForBig3COId
										.setPkgVerInstanceTree(packageVersionRepository
												.findPkgVerInstanceTreeById(instanceTreeId));

								shapeValueBenefitOptionForBig3COId
										.setPkgVerBenefitOption(packageVersionRepository
												.findPkgVerBenefitOptionById(selectionTierdataData
														.getBenefitOption1up()));
								temp.setShapeValueBenefitOptionForBig3COId(shapeValueBenefitOptionForBig3COId);
								temp.setMasterListIntVal(masterListRepository
										.findMasterListIntValById(selectionTierdataData
												.getApplyIndDeductible1up()));
								temp.seteBSInstance1up(selectionTierdataData
										.geteBSInstance1up());
								temp.setIfYNTestAnswerYesFlag(false);
								temp.setIfYNTestAnswerNoFlag(false);

								temp.setCreateBy(selectionTierdataData
										.getCreateBy());
								temp.setCreateDt(new Date());
								packageVersionRepository.saveData(temp);

							}

						}
					}
					if (selectionTierdataData.getApplyFamDeductibleShape() != null) {

						if (excelViewInfo.getHeader().equals("FAM")
								&& selectionTierdataData
										.getApplyFamDeductibleShape().equals(
												excelViewInfo.getTreeShape()
														.getTreeShape1up())) {

							temp = packageRepository
									.findShapeValueBenefitOptionForBig3COById(
											selectionTierdataData
													.getApplyFamDeductibleShape(),
											instanceTreeId,
											selectionTierdataData
													.getPkgVerBig3CO1up(),
											selectionTierdataData
													.getBenefitOption1up());

							if (temp != null) {
								temp.setMasterListIntVal(masterListRepository
										.findMasterListIntValById(selectionTierdataData
												.getApplyFamDeductible1up()));
								temp.setUpdateBy(selectionTierdataData
										.getUpdateBy());
								temp.setUpdateDt(new Date());
								packageVersionRepository.updateData(temp);
							} else {
								ShapeValueBenefitOptionForBig3COId shapeValueBenefitOptionForBig3COId = new ShapeValueBenefitOptionForBig3COId();
								shapeValueBenefitOptionForBig3COId
										.setTreeShape(templateRepository
												.findTreeShapeById(selectionTierdataData
														.getApplyFamDeductibleShape()));
								shapeValueBenefitOptionForBig3COId
										.setPkgVerBig3CO(packageVersionRepository
												.findPkgVerBig3COById(selectionTierdataData
														.getPkgVerBig3CO1up()));
								shapeValueBenefitOptionForBig3COId
										.setPkgVerInstanceTree(packageVersionRepository
												.findPkgVerInstanceTreeById(instanceTreeId));

								shapeValueBenefitOptionForBig3COId
										.setPkgVerBenefitOption(packageVersionRepository
												.findPkgVerBenefitOptionById(selectionTierdataData
														.getBenefitOption1up()));
								temp.setShapeValueBenefitOptionForBig3COId(shapeValueBenefitOptionForBig3COId);
								temp.setMasterListIntVal(masterListRepository
										.findMasterListIntValById(selectionTierdataData
												.getApplyFamDeductible1up()));
								temp.seteBSInstance1up(selectionTierdataData
										.geteBSInstance1up());
								temp.setIfYNTestAnswerYesFlag(false);
								temp.setIfYNTestAnswerNoFlag(false);

								temp.setCreateBy(selectionTierdataData
										.getCreateBy());
								temp.setCreateDt(new Date());
								packageVersionRepository.saveData(temp);

							}

						}
					}
					if (selectionTierdataData.getApplyDeductibleNoShape() != null) {

						if (excelViewInfo.getHeader().equals(
								"Apply Deductible-No")
								&& selectionTierdataData
										.getApplyDeductibleNoShape().equals(
												excelViewInfo.getTreeShape()
														.getTreeShape1up())) {

							temp = packageRepository
									.findShapeValueBenefitOptionForBig3COById(
											selectionTierdataData
													.getApplyDeductibleNoShape(),
											instanceTreeId,
											selectionTierdataData
													.getPkgVerBig3CO1up(),
											selectionTierdataData
													.getBenefitOption1up());

							if (temp != null) {
								temp.setMasterListStringVal(masterListRepository
										.findMasterListStringValById(selectionTierdataData
												.getApplyDeductibleNo1up()));
								temp.setUpdateBy(selectionTierdataData
										.getUpdateBy());
								temp.setUpdateDt(new Date());
								packageVersionRepository.updateData(temp);
							} else {
								ShapeValueBenefitOptionForBig3COId shapeValueBenefitOptionForBig3COId = new ShapeValueBenefitOptionForBig3COId();
								shapeValueBenefitOptionForBig3COId
										.setTreeShape(templateRepository
												.findTreeShapeById(selectionTierdataData
														.getApplyDeductibleNoShape()));
								shapeValueBenefitOptionForBig3COId
										.setPkgVerBig3CO(packageVersionRepository
												.findPkgVerBig3COById(selectionTierdataData
														.getPkgVerBig3CO1up()));
								shapeValueBenefitOptionForBig3COId
										.setPkgVerInstanceTree(packageVersionRepository
												.findPkgVerInstanceTreeById(instanceTreeId));

								shapeValueBenefitOptionForBig3COId
										.setPkgVerBenefitOption(packageVersionRepository
												.findPkgVerBenefitOptionById(selectionTierdataData
														.getBenefitOption1up()));
								temp.setShapeValueBenefitOptionForBig3COId(shapeValueBenefitOptionForBig3COId);
								temp.setMasterListStringVal(masterListRepository
										.findMasterListStringValById(selectionTierdataData
												.getApplyDeductibleNo1up()));
								temp.seteBSInstance1up(selectionTierdataData
										.geteBSInstance1up());
								temp.setIfYNTestAnswerYesFlag(false);
								temp.setIfYNTestAnswerNoFlag(false);

								temp.setCreateBy(selectionTierdataData
										.getCreateBy());
								temp.setCreateDt(new Date());
								packageVersionRepository.saveData(temp);

							}

						}
					}
				}
			}

		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error saving value for Service Type");
			throw new DataAccessException(e.getMessage());

		}

		return operationResult;
	}

	@Transactional(readOnly = true)
	public List<MatrixShapeBig3COMessageXXrefData> getAllEnableMessageValForBenefitForEachPkgVersion(
			int pkgVerBig3CoId, int treeShapeId) throws DataAccessException {

		List<MatrixShapeBig3COMessageXXrefData> messageXXrefDatas = new ArrayList<MatrixShapeBig3COMessageXXrefData>();

		List<MatrixShapeBig3COMessageXXref> big3coMessageXXrefs = null;

		try {

			big3coMessageXXrefs = packageVersionRepository
					.findAllEnableMessageValForPkgVer(pkgVerBig3CoId,
							treeShapeId);

			for (MatrixShapeBig3COMessageXXref messageXref : big3coMessageXXrefs) {

				MatrixShapeBig3COMessageXXrefData big3coMessageXXrefData = new MatrixShapeBig3COMessageXXrefData();

				// big3coMessageXXrefData.seteBSInstance1up(messageXref
				// .geteBSInstance1up());
				// big3coMessageXXrefData.setCreateBy(messageXref.getCreateBy());
				// big3coMessageXXrefData.setCreateDt(messageXref.getCreateDt());
				// big3coMessageXXrefData.setUpdateBy(messageXref.getUpdateBy());
				// big3coMessageXXrefData.setUpdateDt(messageXref.getUpdateDt());

				MasterListMessageValData messageValData = new MasterListMessageValData();
				messageValData
						.setMasterListMessageVal1up(messageXref
								.getMatrixShapeBig3COMessageXXrefId()
								.getMasterListMessageVal()
								.getMasterListMessageVal1up());
				messageValData.setMessageValue(messageXref
						.getMatrixShapeBig3COMessageXXrefId()
						.getMasterListMessageVal().getMessageValue());
				messageValData.setMessageType(messageXref
						.getMatrixShapeBig3COMessageXXrefId()
						.getMasterListMessageVal().getMessageType());

				big3coMessageXXrefData.setMasterListMessageVal(messageValData);

				// big3coMessageXXrefData.setPkgVerBig3CO1up(pkgVerBig3CoId);
				//
				// big3coMessageXXrefData.setTreeShape1up(treeShapeId);
				//
				// big3coMessageXXrefData.setEnableFlag(true);

				messageXXrefDatas.add(big3coMessageXXrefData);

			}

		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		return messageXXrefDatas;

	}

	@Transactional(readOnly = true)
	public List<MatrixShapeBig3COLimitXXrefData> getAllEnableLimitValForBenefitForEachPkgVersion(
			int pkgVerBig3CoId, int treeShapeId) throws DataAccessException {

		List<MatrixShapeBig3COLimitXXrefData> limitXXrefDatas = new ArrayList<MatrixShapeBig3COLimitXXrefData>();
		MatrixShapeBig3COLimitXXrefData limitXXrefData = null;
		List<MatrixShapeBig3COLimitXXref> big3coLimitXXrefs = null;

		try {

			big3coLimitXXrefs = packageRepository
					.findAllEnableLimitValForPkgVer(treeShapeId, pkgVerBig3CoId);

			for (MatrixShapeBig3COLimitXXref matrixShapeLimitXref : big3coLimitXXrefs) {

				limitXXrefData = new MatrixShapeBig3COLimitXXrefData();

				MasterListLimitValData limitValData = new MasterListLimitValData();

				limitValData.setMasterListLimitValId(matrixShapeLimitXref
						.getMatrixShapeBig3COLimitXXrefId()
						.getMasterListLimitVal().getMasterListLimitVal1up());
				limitValData.setLimitName(matrixShapeLimitXref
						.getMatrixShapeBig3COLimitXXrefId()
						.getMasterListLimitVal().getLimitName());
				limitValData.setLimitDescription(matrixShapeLimitXref
						.getMatrixShapeBig3COLimitXXrefId()
						.getMasterListLimitVal().getLimitDescription());
				limitValData.setBenefitAmount(matrixShapeLimitXref
						.getMatrixShapeBig3COLimitXXrefId()
						.getMasterListLimitVal().getBenefitAmount());
				limitValData.setQuantityFrom(matrixShapeLimitXref
						.getMatrixShapeBig3COLimitXXrefId()
						.getMasterListLimitVal().getQuantityFrom());
				limitValData.setQuantityTo(matrixShapeLimitXref
						.getMatrixShapeBig3COLimitXXrefId()
						.getMasterListLimitVal().getQuantityTo());
				limitValData.setReinstatementAmount(matrixShapeLimitXref
						.getMatrixShapeBig3COLimitXXrefId()
						.getMasterListLimitVal().getReinstatementAmount());

				limitXXrefData.setMasterListLimitVal(limitValData);

				// limitXXrefData.setPkgVerBig3CO1up(pkgVerBig3CoId);
				//
				// limitXXrefData.setTreeShape1up(treeShapeId);
				//
				// limitXXrefData.setEnable(true);

				limitXXrefDatas.add(limitXXrefData);

			}
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		return limitXXrefDatas;

	}

	@Transactional(readOnly = true)
	public List<MatrixShapeBig3COMessageXXrefData> getAllEnableMessageValForBenefitForEachBenefitOption(
			int pkgVerBig3CoId, int pkgBenOptionId, int treeShapeId)
			throws DataAccessException {

		List<MatrixShapeBig3COMessageXXrefData> messageXXrefDatas = new ArrayList<MatrixShapeBig3COMessageXXrefData>();

		List<MessageShapeBenefitOptionOverride> big3coMessageXXrefs = null;

		try {

			big3coMessageXXrefs = packageVersionRepository
					.findAllEnableMessageValForBenefitOption(pkgVerBig3CoId,
							pkgBenOptionId, treeShapeId);

			for (MessageShapeBenefitOptionOverride messageXref : big3coMessageXXrefs) {

				MatrixShapeBig3COMessageXXrefData big3coMessageXXrefData = new MatrixShapeBig3COMessageXXrefData();

				// big3coMessageXXrefData.seteBSInstance1up(messageXref
				// .geteBSInstance1up());
				// big3coMessageXXrefData.setCreateBy(messageXref.getCreateBy());
				// big3coMessageXXrefData.setCreateDt(messageXref.getCreateDt());
				// big3coMessageXXrefData.setUpdateBy(messageXref.getUpdateBy());
				// big3coMessageXXrefData.setUpdateDt(messageXref.getUpdateDt());

				MasterListMessageValData messageValData = new MasterListMessageValData();
				messageValData
						.setMasterListMessageVal1up(messageXref
								.getMessageShapeBenefitOptionOverrideId()
								.getMatrixShapeBig3COMessageXXrefId()
								.getMasterListMessageVal()
								.getMasterListMessageVal1up());
				messageValData.setMessageValue(messageXref
						.getMessageShapeBenefitOptionOverrideId()
						.getMatrixShapeBig3COMessageXXrefId()
						.getMasterListMessageVal().getMessageValue());
				messageValData.setMessageType(messageXref
						.getMessageShapeBenefitOptionOverrideId()
						.getMatrixShapeBig3COMessageXXrefId()
						.getMasterListMessageVal().getMessageType());

				big3coMessageXXrefData.setMasterListMessageVal(messageValData);

				// big3coMessageXXrefData.setPkgVerBig3CO1up(pkgVerBig3CoId);
				//
				// big3coMessageXXrefData.setTreeShape1up(treeShapeId);
				// big3coMessageXXrefData.setBenefitOption1up(messageXref
				// .getMessageShapeBenefitOptionOverrideId()
				// .getPkgVerBenefitOption().getPkgVerBenefitOption1up());
				//
				// big3coMessageXXrefData.setEnableFlag(true);

				messageXXrefDatas.add(big3coMessageXXrefData);

			}

		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		return messageXXrefDatas;

	}

	@Transactional(readOnly = true)
	public List<MatrixShapeBig3COLimitXXrefData> getAllEnableLimitValForBenefitForEachBenefitOption(
			int pkgVerBig3CoId, int benOptionId, int treeShapeId)
			throws DataAccessException {

		List<MatrixShapeBig3COLimitXXrefData> limitXXrefDatas = new ArrayList<MatrixShapeBig3COLimitXXrefData>();
		MatrixShapeBig3COLimitXXrefData limitXXrefData = null;
		List<LimitShapeBenefitOptionOverride> big3coLimitXXrefs = null;

		try {

			big3coLimitXXrefs = packageVersionRepository
					.findAllEnableLimitValForBenefitOption(pkgVerBig3CoId,
							benOptionId, treeShapeId);

			for (LimitShapeBenefitOptionOverride matrixShapeLimitXref : big3coLimitXXrefs) {

				limitXXrefData = new MatrixShapeBig3COLimitXXrefData();

				MasterListLimitValData limitValData = new MasterListLimitValData();

				limitValData.setMasterListLimitValId(matrixShapeLimitXref
						.getLimitShapeBenefitOptionOverrideId()
						.getMatrixShapeBig3COLimitXXrefId()
						.getMasterListLimitVal().getMasterListLimitVal1up());
				limitValData.setLimitName(matrixShapeLimitXref
						.getLimitShapeBenefitOptionOverrideId()
						.getMatrixShapeBig3COLimitXXrefId()
						.getMasterListLimitVal().getLimitName());
				limitValData.setLimitDescription(matrixShapeLimitXref
						.getLimitShapeBenefitOptionOverrideId()
						.getMatrixShapeBig3COLimitXXrefId()
						.getMasterListLimitVal().getLimitDescription());
				limitValData.setBenefitAmount(matrixShapeLimitXref
						.getLimitShapeBenefitOptionOverrideId()
						.getMatrixShapeBig3COLimitXXrefId()
						.getMasterListLimitVal().getBenefitAmount());
				limitValData.setQuantityFrom(matrixShapeLimitXref
						.getLimitShapeBenefitOptionOverrideId()
						.getMatrixShapeBig3COLimitXXrefId()
						.getMasterListLimitVal().getQuantityFrom());
				limitValData.setQuantityTo(matrixShapeLimitXref
						.getLimitShapeBenefitOptionOverrideId()
						.getMatrixShapeBig3COLimitXXrefId()
						.getMasterListLimitVal().getQuantityTo());
				limitValData.setReinstatementAmount(matrixShapeLimitXref
						.getLimitShapeBenefitOptionOverrideId()
						.getMatrixShapeBig3COLimitXXrefId()
						.getMasterListLimitVal().getReinstatementAmount());

				limitXXrefData.setMasterListLimitVal(limitValData);

				// limitXXrefData.setBenefitOption1up(matrixShapeLimitXref
				// .getLimitShapeBenefitOptionOverrideId()
				// .getPkgVerBenefitOption().getPkgVerBenefitOption1up());
				//
				// limitXXrefData.setPkgVerBig3CO1up(pkgVerBig3CoId);
				//
				// limitXXrefData.setTreeShape1up(treeShapeId);
				//
				// limitXXrefData.setEnable(true);

				limitXXrefDatas.add(limitXXrefData);

			}
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		return limitXXrefDatas;

	}

	@Transactional(readOnly = true)
	public List<MatrixShapeBig3COMessageXXrefData> getAllMessageValForBenefitSelectionOnBenefitOption(
			int benOptionTierData1up, int treeShape1up)
			throws DataAccessException {
		List<MessageShapeBenefitOptionOverride> matrixBig3coMessageVal = null;
		List<MatrixShapeMessageXref> matrixShapeMessage = null;
		BenefitOptionTierdata benefitOptionTierdata = null;

		try {
			matrixShapeMessage = packageVersionRepository
					.findAllMessageValByTreeShapeId(treeShape1up);

			benefitOptionTierdata = packageVersionRepository
					.findBenefitOptionTierdataById(benOptionTierData1up);

			if (benefitOptionTierdata.getPkgVerBig3CO().getPkgVerBig3CO1up() != null) {
				matrixBig3coMessageVal = packageVersionRepository
						.findAllEnableMessageValForBenefitOption(
								benefitOptionTierdata.getPkgVerBig3CO()
										.getPkgVerBig3CO1up(),
								benefitOptionTierdata.getBenefitOption()
										.getPkgVerBenefitOption1up(),
								treeShape1up);
			}

		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<Integer> messageValIds = new ArrayList<Integer>();

		for (MessageShapeBenefitOptionOverride messageBig3CoXref : matrixBig3coMessageVal) {

			messageValIds.add(messageBig3CoXref
					.getMessageShapeBenefitOptionOverrideId()
					.getMatrixShapeBig3COMessageXXrefId()
					.getMasterListMessageVal().getMasterListMessageVal1up());

		}

		List<MatrixShapeBig3COMessageXXrefData> matrixMessageDatas = new ArrayList<MatrixShapeBig3COMessageXXrefData>();

		for (MatrixShapeMessageXref messageXref : matrixShapeMessage) {

			MatrixShapeBig3COMessageXXrefData big3coMessageXXrefData = new MatrixShapeBig3COMessageXXrefData();
			big3coMessageXXrefData.seteBSInstance1up(messageXref
					.geteBSInstance1up());
			big3coMessageXXrefData.setCreateBy(messageXref.getCreateBy());
			big3coMessageXXrefData.setCreateDt(messageXref.getCreateDt());
			big3coMessageXXrefData.setUpdateBy(messageXref.getUpdateBy());
			big3coMessageXXrefData.setUpdateDt(messageXref.getUpdateDt());

			MasterListMessageValData messageValData = new MasterListMessageValData();
			messageValData.setMasterListMessageVal1up(messageXref
					.getMatrixShapeMessageXrefId().getMasterListMessageVal()
					.getMasterListMessageVal1up());
			messageValData.setMessageValue(messageXref
					.getMatrixShapeMessageXrefId().getMasterListMessageVal()
					.getMessageValue());
			messageValData.setMessageType(messageXref
					.getMatrixShapeMessageXrefId().getMasterListMessageVal()
					.getMessageType());

			big3coMessageXXrefData.setMasterListMessageVal(messageValData);

			big3coMessageXXrefData.setPkgVerBig3CO1up(benefitOptionTierdata
					.getPkgVerBig3CO().getPkgVerBig3CO1up());

			big3coMessageXXrefData.setTreeShape1up(treeShape1up);
			big3coMessageXXrefData.setBenefitOption1up(benefitOptionTierdata
					.getBenefitOption().getPkgVerBenefitOption1up());

			if (messageValIds.contains(messageXref
					.getMatrixShapeMessageXrefId().getMasterListMessageVal()
					.getMasterListMessageVal1up())) {

				big3coMessageXXrefData.setEnableFlag(true);

			} else {
				big3coMessageXXrefData.setEnableFlag(false);
			}

			matrixMessageDatas.add(big3coMessageXXrefData);

		}

		return matrixMessageDatas;
	}

	@Transactional(readOnly = true)
	public List<MatrixShapeBig3COLimitXXrefData> getAllLimitValForBenefitSelectionOnBenefitOption(
			int benOptionTierData1up, int treeShapeId)
			throws DataAccessException {

		List<MatrixShapeBig3COLimitXXrefData> limitXXrefDatas = new ArrayList<MatrixShapeBig3COLimitXXrefData>();
		MatrixShapeBig3COLimitXXrefData limitXXrefData = null;
		int pkgVerBig3CoId = 0;

		try {
			List<MatrixShapeLimitXref> matrixShapeLimitXrefs = templateRepository
					.findActiveMatrixShapeLimitXrefById(treeShapeId);

			BenefitOptionTierdata optionTierdata = packageVersionRepository
					.findBenefitOptionTierdataById(benOptionTierData1up);

			if (optionTierdata != null) {

				pkgVerBig3CoId = optionTierdata.getPkgVerBig3CO()
						.getPkgVerBig3CO1up();
			}

			List<LimitShapeBenefitOptionOverride> big3coLimitXXrefs = packageVersionRepository
					.findAllEnableLimitValForBenefitOption(pkgVerBig3CoId,
							optionTierdata.getBenefitOption()
									.getPkgVerBenefitOption1up(), treeShapeId);

			List<Integer> masterListIds = new ArrayList<Integer>();

			for (LimitShapeBenefitOptionOverride matrixShapeBig3COLimitXXref : big3coLimitXXrefs) {
				masterListIds.add(matrixShapeBig3COLimitXXref
						.getLimitShapeBenefitOptionOverrideId()
						.getMatrixShapeBig3COLimitXXrefId()
						.getMasterListLimitVal().getMasterListLimitVal1up());

			}

			for (MatrixShapeLimitXref matrixShapeLimitXref : matrixShapeLimitXrefs) {

				limitXXrefData = new MatrixShapeBig3COLimitXXrefData();

				MasterListLimitValData limitValData = new MasterListLimitValData();

				limitValData.setMasterListLimitValId(matrixShapeLimitXref
						.getMatrixShapeLimitXrefId().getMasterListLimitVal()
						.getMasterListLimitVal1up());
				limitValData.setLimitName(matrixShapeLimitXref
						.getMatrixShapeLimitXrefId().getMasterListLimitVal()
						.getLimitName());
				limitValData.setLimitDescription(matrixShapeLimitXref
						.getMatrixShapeLimitXrefId().getMasterListLimitVal()
						.getLimitDescription());
				limitValData.setBenefitAmount(matrixShapeLimitXref
						.getMatrixShapeLimitXrefId().getMasterListLimitVal()
						.getBenefitAmount());
				limitValData.setQuantityFrom(matrixShapeLimitXref
						.getMatrixShapeLimitXrefId().getMasterListLimitVal()
						.getQuantityFrom());
				limitValData.setQuantityTo(matrixShapeLimitXref
						.getMatrixShapeLimitXrefId().getMasterListLimitVal()
						.getQuantityTo());
				limitValData.setReinstatementAmount(matrixShapeLimitXref
						.getMatrixShapeLimitXrefId().getMasterListLimitVal()
						.getReinstatementAmount());

				limitXXrefData.setMasterListLimitVal(limitValData);

				limitXXrefData.setPkgVerBig3CO1up(optionTierdata
						.getPkgVerBig3CO().getPkgVerBig3CO1up());

				limitXXrefData.setBenefitOption1up(optionTierdata
						.getBenefitOption().getPkgVerBenefitOption1up());

				limitXXrefData.setTreeShape1up(treeShapeId);

				if (masterListIds.contains(matrixShapeLimitXref
						.getMatrixShapeLimitXrefId().getMasterListLimitVal()
						.getMasterListLimitVal1up())) {
					limitXXrefData.setEnable(true);
				} else {
					limitXXrefData.setEnable(false);
				}
				limitXXrefDatas.add(limitXXrefData);

			}
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		return limitXXrefDatas;
	}

	/**
	 * @param benefitSelectionTierData
	 *            benefitSelectionTierData data object
	 * @param pkgVerInstanceTreeId
	 *            Id of common benefit
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * @throws DataAccessException
	 */
	@Transactional
	public ServiceOperationResult updateDefaultValueOfBenefitSelectionForPackgeVersion(
			BenefitSelectionTierdataData benefitSelectionTierData,
			int pkgVerInstanceTreeId) throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		if (benefitSelectionTierData == null) {
			throw new DataAccessException();

		}
		try {

			ShapeValueTreeInstance instance = null;

			if (benefitSelectionTierData.getApplyDeductibleShape() != null) {
				instance = packageRepository.findShapeValueTreeInstanceById(
						benefitSelectionTierData.getApplyDeductibleShape(),
						pkgVerInstanceTreeId);
				if (instance == null) {
					operationResult.setOperationResult(OperationResult.FAILURE);
					messages.add("There is no Values to Update..");
					return operationResult;
				} else {
					if (benefitSelectionTierData.getApplyDeductible()) {
						instance.setIfYNTestAnswerYesFlag(true);
						instance.setIfYNTestAnswerNoFlag(false);
						instance.setUpdateBy(benefitSelectionTierData
								.getUpdateBy());
						instance.setUpdateDt(new Date());
					} else {
						instance.setIfYNTestAnswerYesFlag(false);
						instance.setIfYNTestAnswerNoFlag(true);
						instance.setUpdateBy(benefitSelectionTierData
								.getUpdateBy());
						instance.setUpdateDt(new Date());
					}

				}
				packageVersionRepository.updateData(instance);
			}

			if (benefitSelectionTierData.getApplyIndDeductibleShape() != null) {
				instance = packageRepository.findShapeValueTreeInstanceById(
						benefitSelectionTierData.getApplyIndDeductibleShape(),
						pkgVerInstanceTreeId);
				if (instance == null) {
					operationResult.setOperationResult(OperationResult.FAILURE);
					messages.add("There is no Values to Update..");
					return operationResult;
				} else {
					instance.setMasterListIntVal(masterListRepository
							.findMasterListIntValById(benefitSelectionTierData
									.getApplyIndDeductible1up()));
					instance.setUpdateBy(benefitSelectionTierData.getUpdateBy());
					instance.setUpdateDt(new Date());
				}
				packageVersionRepository.updateData(instance);
			}
			if (benefitSelectionTierData.getApplyFamDeductibleShape() != null) {
				instance = packageRepository.findShapeValueTreeInstanceById(
						benefitSelectionTierData.getApplyFamDeductibleShape(),
						pkgVerInstanceTreeId);
				if (instance == null) {
					operationResult.setOperationResult(OperationResult.FAILURE);
					messages.add("There is no Values to Update..");
					return operationResult;

				} else {
					instance.setMasterListIntVal(masterListRepository
							.findMasterListIntValById(benefitSelectionTierData
									.getApplyFamDeductible1up()));
					instance.setUpdateBy(benefitSelectionTierData.getUpdateBy());
					instance.setUpdateDt(new Date());

				}
				packageVersionRepository.updateData(instance);
			}
			if (benefitSelectionTierData.getApplyDeductibleNoShape() != null) {
				instance = packageRepository.findShapeValueTreeInstanceById(
						benefitSelectionTierData.getApplyDeductibleNoShape(),
						pkgVerInstanceTreeId);
				if (instance == null) {
					operationResult.setOperationResult(OperationResult.FAILURE);
					messages.add("There is no Values to Update..");
					return operationResult;

				} else {
					instance.setMasterListStringVal(masterListRepository
							.findMasterListStringValById(benefitSelectionTierData
									.getApplyDeductibleNo1up()));
					instance.setUpdateBy(benefitSelectionTierData.getUpdateBy());
					instance.setUpdateDt(new Date());

				}
				packageVersionRepository.updateData(instance);

			}

			if (benefitSelectionTierData.getApplyCoinsuranceShape() != null) {
				instance = packageRepository.findShapeValueTreeInstanceById(
						benefitSelectionTierData.getApplyCoinsuranceShape(),
						pkgVerInstanceTreeId);
				if (instance == null) {
					operationResult.setOperationResult(OperationResult.FAILURE);
					messages.add("There is no Values to Update..");
					return operationResult;

				} else {
					if (benefitSelectionTierData.getApplyCoinsurance()) {
						instance.setIfYNTestAnswerYesFlag(true);
						instance.setIfYNTestAnswerNoFlag(false);
						instance.setUpdateBy(benefitSelectionTierData
								.getUpdateBy());
						instance.setUpdateDt(new Date());
					} else {
						instance.setIfYNTestAnswerYesFlag(false);
						instance.setIfYNTestAnswerNoFlag(true);
						instance.setUpdateBy(benefitSelectionTierData
								.getUpdateBy());
						instance.setUpdateDt(new Date());
					}
				}
				packageVersionRepository.updateData(instance);
			}
			if (benefitSelectionTierData.getCoinsuranceYesValShape() != null) {
				instance = packageRepository.findShapeValueTreeInstanceById(
						benefitSelectionTierData.getCoinsuranceYesValShape(),
						pkgVerInstanceTreeId);
				if (instance == null) {
					operationResult.setOperationResult(OperationResult.FAILURE);
					messages.add("There is no Values to Update..");
					return operationResult;

				} else {
					instance.setMasterListIntVal(masterListRepository
							.findMasterListIntValById(benefitSelectionTierData
									.getCoinsuranceYesVal1up()));
					instance.setUpdateBy(benefitSelectionTierData.getUpdateBy());
					instance.setUpdateDt(new Date());
				}
				packageVersionRepository.updateData(instance);

			}
			if (benefitSelectionTierData.getCoinsuranceNoValShape() != null) {
				instance = packageRepository.findShapeValueTreeInstanceById(
						benefitSelectionTierData.getCoinsuranceNoValShape(),
						pkgVerInstanceTreeId);
				if (instance == null) {
					operationResult.setOperationResult(OperationResult.FAILURE);
					messages.add("There is no Values to Update..");
					return operationResult;
				} else {
					instance.setMasterListStringVal(masterListRepository
							.findMasterListStringValById(benefitSelectionTierData
									.getCoinsuranceNoVal1up()));
					instance.setUpdateBy(benefitSelectionTierData.getUpdateBy());
					instance.setUpdateDt(new Date());

				}
				packageVersionRepository.updateData(instance);
			}

			if (benefitSelectionTierData.getCopayShape() != null) {
				instance = packageRepository.findShapeValueTreeInstanceById(
						benefitSelectionTierData.getCopayShape(),
						pkgVerInstanceTreeId);
				if (instance == null) {
					operationResult.setOperationResult(OperationResult.FAILURE);
					messages.add("There is no Values to Update..");
					return operationResult;

				} else {
					instance.setMasterListIntVal(masterListRepository
							.findMasterListIntValById(benefitSelectionTierData
									.getCopayVal1up()));
					instance.setUpdateBy(benefitSelectionTierData.getUpdateBy());
					instance.setUpdateDt(new Date());

				}
				packageVersionRepository.updateData(instance);
			}
			if (benefitSelectionTierData.getAllowedAmtShape() != null) {
				instance = packageRepository.findShapeValueTreeInstanceById(
						benefitSelectionTierData.getAllowedAmtShape(),
						pkgVerInstanceTreeId);
				if (instance == null) {
					operationResult.setOperationResult(OperationResult.FAILURE);
					messages.add("There is no Values to Update..");
					return operationResult;

				} else {
					instance.setMasterListIntVal(masterListRepository
							.findMasterListIntValById(benefitSelectionTierData
									.getAllowedAmount1up()));
					instance.setUpdateBy(benefitSelectionTierData.getUpdateBy());
					instance.setUpdateDt(new Date());

				}
				packageVersionRepository.updateData(instance);
			}
			if (benefitSelectionTierData.getAllowedCtrShape() != null) {
				instance = packageRepository.findShapeValueTreeInstanceById(
						benefitSelectionTierData.getAllowedCtrShape(),
						pkgVerInstanceTreeId);
				if (instance == null) {
					operationResult.setOperationResult(OperationResult.FAILURE);
					messages.add("There is no Values to Update..");
					return operationResult;
				} else {
					instance.setMasterListIntVal(masterListRepository
							.findMasterListIntValById(benefitSelectionTierData
									.getAllowedCtr1up()));
					instance.setUpdateBy(benefitSelectionTierData.getUpdateBy());
					instance.setUpdateDt(new Date());

				}
				packageVersionRepository.updateData(instance);
			}
			if (benefitSelectionTierData.getFacetShape() != null) {
				instance = packageRepository.findShapeValueTreeInstanceById(
						benefitSelectionTierData.getFacetShape(),
						pkgVerInstanceTreeId);
				if (instance == null) {
					operationResult.setOperationResult(OperationResult.FAILURE);
					messages.add("There is no Values to Update..");
					return operationResult;

				} else {
					instance.setUpdateBy(benefitSelectionTierData.getUpdateBy());
					instance.setUpdateDt(new Date());

				}
				packageVersionRepository.updateData(instance);
			}

		} catch (PersistenceException e) {

			throw new DataAccessException(e.getMessage());
		}

		return operationResult;
	}

	@Transactional
	public ServiceOperationResult saveAndDeleteEnabledMessagesForBenefitSelectionForBenefitOption(
			List<MatrixShapeBig3COMessageXXrefData> benefitOptionOverrides,
			int treeShape1up, int pkgVerBig3CO1up) throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> message = new ArrayList<String>();

		List<MessageShapeBenefitOptionOverride> messageShapeBenefitOptionOverride = null;

		try {

			messageShapeBenefitOptionOverride = packageVersionRepository
					.findAllEnableMessageValForBenefitOption(
							benefitOptionOverrides.get(0).getPkgVerBig3CO1up(),
							benefitOptionOverrides.get(0).getBenefitOption1up(),
							treeShape1up);

		} catch (PersistenceException e) {
			throw new DataAccessException();
		}
		List<Integer> currentIdList = new ArrayList<Integer>();
		List<Integer> existingIdList = new ArrayList<Integer>();

		if (benefitOptionOverrides != null) {
			for (MatrixShapeBig3COMessageXXrefData messageBenOption : benefitOptionOverrides) {
				currentIdList
						.add(messageBenOption.getMasterListMessageVal1up());
			}
		}
		System.out.println(currentIdList.size());

		if (messageShapeBenefitOptionOverride != null) {
			for (MessageShapeBenefitOptionOverride matrixShapeMessageXref : messageShapeBenefitOptionOverride) {
				existingIdList
						.add(matrixShapeMessageXref
								.getMessageShapeBenefitOptionOverrideId()
								.getMatrixShapeBig3COMessageXXrefId()
								.getMasterListMessageVal()
								.getMasterListMessageVal1up());
			}
		}

		System.out.println(existingIdList.size());

		List<Integer> commonIdList = new ArrayList<Integer>(existingIdList);
		commonIdList.retainAll(currentIdList);

		existingIdList.removeAll(commonIdList); // remove all elements of
												// existingIdList present in
												// commonIdList
		currentIdList.removeAll(commonIdList); // remove all elements of
												// currentIdList present in
												// commonIdList
		System.out.println(existingIdList.size());

		if (benefitOptionOverrides != null) {
			try {
				for (Integer existingID : existingIdList) {

					packageRepository.deleteMatrixShapeBig3COMessageXXrefById(
							treeShape1up, pkgVerBig3CO1up, existingID);

					packageRepository
							.deleteMessageShapeBenefitOptionOverrideById(
									treeShape1up, benefitOptionOverrides.get(0)
											.getPkgVerBig3CO1up(),
									benefitOptionOverrides.get(0)
											.getBenefitOption1up(), existingID);

				}

				System.out.println(messageShapeBenefitOptionOverride.size());

				if (messageShapeBenefitOptionOverride.size() == 0) {
					packageRepository.deleteMatrixShapeBig3COXrefById(
							treeShape1up, pkgVerBig3CO1up);

				}

			} catch (PersistenceException e) {
				throw new DataAccessException();
			}
		}

		MessageShapeBenefitOptionOverride finalMatrixShapeMessageXref = new MessageShapeBenefitOptionOverride();

		if (benefitOptionOverrides != null) {
			for (int i = 0; i < benefitOptionOverrides.size(); i++) {
				if (currentIdList.remove(benefitOptionOverrides.get(i)
						.getMasterListMessageVal1up())) {

					finalMatrixShapeMessageXref
							.setCreateBy(benefitOptionOverrides.get(i)
									.getCreateBy());

					finalMatrixShapeMessageXref.setCreateDt(new Date());
					finalMatrixShapeMessageXref.seteBSInstance1up(1);

					MessageShapeBenefitOptionOverrideId id = new MessageShapeBenefitOptionOverrideId();

					try {

						id.setPkgVerBenefitOption(packageVersionRepository
								.findPkgVerBenefitOptionById(benefitOptionOverrides
										.get(i).getBenefitOption1up()));

						MatrixShapeBig3COMessageXXrefId id1 = new MatrixShapeBig3COMessageXXrefId();

						id1.setMasterListMessageVal(packageVersionRepository
								.findMasterListMessageValById(benefitOptionOverrides
										.get(i).getMasterListMessageVal1up()));

						MatrixShapeBig3COXrefId id2 = new MatrixShapeBig3COXrefId();

						id2.setTreeShape(packageVersionRepository
								.findTreeShapeById(benefitOptionOverrides
										.get(i).getTreeShape1up()));

						id2.setPkgVerBig3CO(packageVersionRepository
								.findPkgVerBig3COById(benefitOptionOverrides
										.get(i).getPkgVerBig3CO1up()));

						id1.setMatrixShapeBig3COXrefId(id2);

						id.setMatrixShapeBig3COMessageXXrefId(id1);

						finalMatrixShapeMessageXref
								.setMessageShapeBenefitOptionOverrideId(id);

						packageVersionRepository
								.saveData(finalMatrixShapeMessageXref);
					} catch (PersistenceException e) {
						operationResult
								.setOperationResult(OperationResult.WARNING);
						message.add("Error saving value for MatrixShapeMessageXref ");
					}
				} else {
					continue;
				}
			}
		}
		return operationResult;

	}

	/**
	 * @param pkgVersionId
	 * @return List<PkgVerBig3COData>
	 * @throws DataAccessException
	 *             <p>
	 *             This method Will return all service types for Package version
	 */

	@Transactional(readOnly = true)
	public List<PkgVerBig3COData> getAllServiceTypeForPKgVersion(
			int pkgVersionId) throws DataAccessException {
		List<PkgVerBig3CO> big3cos = null;
		List<PkgVerBig3COData> datas = new ArrayList<PkgVerBig3COData>();
		PkgVerBig3COData big3coData = new PkgVerBig3COData();

		try {
			big3cos = packageVersionRepository
					.findAllServicetypeforPkgVersion(pkgVersionId);

		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}
		if (big3cos != null && big3cos.size() > 0) {

			for (PkgVerBig3CO big3co : big3cos) {

				big3coData.setPkgVerBig3CO1up(big3co.getPkgVerBig3CO1up());
				if (big3co.getPkgMasterListForBig3CO() != null) {
					big3coData.setPkgMasterListForBig3COId(big3co
							.getPkgMasterListForBig3CO()
							.getPkgMasterListForBig3CO1up());

				}
				if (big3co.getPkgMasterListForBig3CO()
						.getMasterListServiceDefinition()
						.getMasterListStringValCat1() != null) {
					big3coData.setCategory1(big3co.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition()
							.getMasterListStringValCat1().getStringValue());

				}
				if (big3co.getPkgMasterListForBig3CO()
						.getMasterListServiceDefinition()
						.getMasterListStringValCat2() != null) {
					big3coData.setCategory2(big3co.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition()
							.getMasterListStringValCat2().getStringValue());
				}
				if (big3co.getPkgMasterListForBig3CO()
						.getMasterListServiceDefinition()
						.getMasterListStringValCat3() != null) {
					big3coData.setCategory3(big3co.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition()
							.getMasterListStringValCat3().getStringValue());
				}
				big3coData.setCreateBy(big3co.getCreateBy());
				big3coData.setCreateDt(big3co.getCreateDt());
				big3coData.seteBSInstance1up(big3co.geteBSInstance1up());

				big3coData.setIsCovered(big3co.getCoveredFlag());
				big3coData.setBenefitRule1Up(big3co.getPkgMasterListForBig3CO()
						.getMasterListStringVal().getMasterListStringVal1up());
				if (big3co.getPkgVersion() != null) {
					big3coData.setPkgVersionId(big3co.getPkgVersion()
							.getPkgVersion1up());
				}
				if (big3co.getPkgMasterListForBig3CO()
						.getMasterListServiceDefinition() != null) {
					big3coData.setPlaceOfService(big3co
							.getPkgMasterListForBig3CO()
							.getMasterListServiceDefinition()
							.getMasterListStringValPostString()
							.getStringValue());
				}

				big3coData.setsETRMessageSelected(big3co
						.getsETRMessageSelected());
				big3coData.setTreatAs100(big3co.getTreatAs100PctFlag());
				big3coData.setUpdateBy(big3co.getUpdateBy());
				big3coData.setUpdateDt(big3co.getUpdateDt());

				datas.add(big3coData);
			}

		} else {
			PkgVersion pkgVersion = packageVersionRepository
					.findPackageVersionById(pkgVersionId);
			int pkgId = pkgVersion.getBenefitPackage().getBenefitPackage1up();
			List<PkgMasterListForBig3CO> masterListForBig3COs = null;
			try {
				masterListForBig3COs = packageVersionRepository
						.findAllServiceTypeForPkg(pkgId);
			} catch (PersistenceException e) {
				throw new DataAccessException(e);
			}
			if (masterListForBig3COs != null && masterListForBig3COs.size() > 0) {
				for (PkgMasterListForBig3CO pkgMasterListForBig3COData : masterListForBig3COs) {

					big3coData.setBenefitRule1Up(pkgMasterListForBig3COData
							.getMasterListStringVal()
							.getMasterListStringVal1up());
					big3coData.setCategory1(pkgMasterListForBig3COData
							.getMasterListServiceDefinition()
							.getMasterListStringValCat1().getStringValue());
					big3coData.setCategory2(pkgMasterListForBig3COData
							.getMasterListServiceDefinition()
							.getMasterListStringValCat2().getStringValue());
					big3coData.setCategory3(pkgMasterListForBig3COData
							.getMasterListServiceDefinition()
							.getMasterListStringValCat3().getStringValue());

					packageVersionRepository
							.findPkgVerInstanceTreeByPkgVerId(pkgVersionId);

					big3coData.setCreateBy(pkgMasterListForBig3COData
							.getCreateBy());
					big3coData.setCreateDt(pkgMasterListForBig3COData
							.getCreateDt());
					big3coData.seteBSInstance1up(pkgMasterListForBig3COData
							.geteBSInstance1up());
					big3coData
							.setPkgMasterListForBig3COId(pkgMasterListForBig3COData
									.getPkgMasterListForBig3CO1up());
					big3coData.setPkgVersionId(pkgVersion.getPkgVersion1up());
					big3coData.setPlaceOfService(pkgMasterListForBig3COData
							.getMasterListServiceDefinition()
							.getMasterListStringValPostString()
							.getStringValue());
					big3coData.setUpdateBy(pkgMasterListForBig3COData
							.getUpdateBy());
					big3coData.setUpdateDt(pkgMasterListForBig3COData
							.getUpdateDt());

				}

			}
			datas.add(big3coData);

		}
		return datas;
	}

	/**
	 * @param pkgVerInstanceTreeId
	 *            primary key of PkgVersionInstanceTree
	 * @return List<ShapeValueInstanceTreeData>
	 * @throws DataAccessException
	 *             <p>
	 *             This method will return package version instance trees all
	 *             tree shape values
	 * 
	 */
	@Transactional(readOnly = true)
	public List<ShapeValueInstanceTreeData> getInstanceTreesAllTreeShapeValue(
			int pkgVerInstanceTreeId) throws DataAccessException {
		List<ShapeValueTreeInstance> instance = null;
		List<ShapeValueInstanceTreeData> datas = new ArrayList<ShapeValueInstanceTreeData>();
		try {
			instance = packageVersionRepository
					.findInstanceTreesAllTreeShapeValue(pkgVerInstanceTreeId);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		ShapeValueInstanceTreeData data = new ShapeValueInstanceTreeData();
		for (ShapeValueTreeInstance shapeValueTreeInstance : instance) {
			data.setCreateBy(shapeValueTreeInstance.getCreateBy());
			data.setCreateDt(shapeValueTreeInstance.getCreateDt());
			data.seteBSInstance1up(shapeValueTreeInstance.geteBSInstance1up());
			data.setIfYNTestAnswerNoFlag(shapeValueTreeInstance
					.getIfYNTestAnswerNoFlag());
			data.setIsOverrideAllowedFlag(shapeValueTreeInstance
					.getOverrideAllowedFlag());
			data.setNote2(shapeValueTreeInstance.getNote2());
			if (shapeValueTreeInstance.getShapeValueTreeInstanceId()
					.getPkgVerInstanceTree() != null) {
				data.setPkgVersionInstanceTree1up(shapeValueTreeInstance
						.getShapeValueTreeInstanceId().getPkgVerInstanceTree()
						.getPkgVerInstanceTree1up());
			}
			data.setSetValueValueAsInt1up(shapeValueTreeInstance
					.getMasterListIntVal().getMasterListIntVal1up());
			data.setSetValueValueAsString1up(shapeValueTreeInstance
					.getMasterListStringVal().getMasterListStringVal1up());
			if (shapeValueTreeInstance.getShapeValueTreeInstanceId()
					.getTreeShape() != null) {
				data.setTreeShape1up(shapeValueTreeInstance
						.getShapeValueTreeInstanceId().getTreeShape()
						.getTreeShape1up());
			}
			data.setUpdateBy(shapeValueTreeInstance.getUpdateBy());
			data.setUpdateDt(shapeValueTreeInstance.getUpdateDt());
			datas.add(data);
		}

		return datas;
	}

	@Transactional
	public ServiceOperationResult saveAndUpdateAllTreeShapesForInstanceTree(
			List<ShapeValueInstanceTreeData> instanceTreeDatas)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();
		if (instanceTreeDatas == null) {
			throw new DataAccessException();
		}

		try {

			for (ShapeValueInstanceTreeData instanceTreeData : instanceTreeDatas) {

				ShapeValueTreeInstance instance = null;
				instance = packageRepository.findShapeValueTreeInstanceById(
						instanceTreeData.getTreeShape1up(),
						instanceTreeData.getPkgVersionInstanceTree1up());
				if (instance != null) {
					instance.setUpdateBy(instanceTreeData.getUpdateBy());
					instance.setUpdateDt(new Date());
					instance.setOverrideAllowedFlag(instanceTreeData
							.getIsOverrideAllowedFlag());
					instance.setNote2(instanceTreeData.getNote2());
					instance.setIfYNTestAnswerYesFlag(instanceTreeData
							.getIfYNTestAnswerYesFlag());
					instance.setIfYNTestAnswerNoFlag(instanceTreeData
							.getIfYNTestAnswerNoFlag());
					instance.setMasterListIntVal(masterListRepository
							.findMasterListIntValById(instanceTreeData
									.getSetValueValueAsInt1up()));
					instance.setMasterListStringVal(masterListRepository
							.findMasterListStringValById(instanceTreeData
									.getSetValueValueAsString1up()));
					instance.seteBSInstance1up(instanceTreeData
							.geteBSInstance1up());
					packageVersionRepository.updateData(instance);
				} else {

					instance = new ShapeValueTreeInstance();
					ShapeValueTreeInstanceId valueTreeInstanceId = new ShapeValueTreeInstanceId();
					valueTreeInstanceId.setTreeShape(templateRepository
							.findTreeShapeById(instanceTreeData
									.getTreeShape1up()));
					valueTreeInstanceId
							.setPkgVerInstanceTree(packageVersionRepository
									.findPkgVerInstanceTreeById(instanceTreeData
											.getPkgVersionInstanceTree1up()));
					instance.setShapeValueTreeInstanceId(valueTreeInstanceId);
					instance.setCreateBy(instanceTreeData.getCreateBy());
					instance.setCreateDt(new Date());
					instance.setOverrideAllowedFlag(instanceTreeData
							.getIsOverrideAllowedFlag());
					instance.setNote2(instanceTreeData.getNote2());
					instance.setIfYNTestAnswerYesFlag(instanceTreeData
							.getIfYNTestAnswerYesFlag());
					instance.setIfYNTestAnswerNoFlag(instanceTreeData
							.getIfYNTestAnswerNoFlag());
					instance.setMasterListIntVal(masterListRepository
							.findMasterListIntValById(instanceTreeData
									.getSetValueValueAsInt1up()));
					instance.setMasterListStringVal(masterListRepository
							.findMasterListStringValById(instanceTreeData
									.getSetValueValueAsString1up()));
					instance.seteBSInstance1up(instanceTreeData
							.geteBSInstance1up());
					packageVersionRepository.saveData(instance);
				}

			}

		} catch (PersistenceException e) {

			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error in save Or update..");

		}

		return operationResult;

	}

}
