package com.tmg.ebscore.service;

import java.util.ArrayList;
import java.util.Date;
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
import com.tmg.ebscore.dto.ebspackage.BenifitPackageData;
import com.tmg.ebscore.dto.ebspackage.PackageContainer;
import com.tmg.ebscore.dto.ebspackage.PackageSummaryData;
import com.tmg.ebscore.dto.ebspackage.PkgConfigOptionData;
import com.tmg.ebscore.dto.ebspackage.PkgConfigOptionFamilyData;
import com.tmg.ebscore.dto.ebspackage.PkgMasterListForBig3COData;
import com.tmg.ebscore.orm.DataAccessException;
import com.tmg.ebscore.orm.ebspackage.AccountPkgConfigOptionXref;
import com.tmg.ebscore.orm.ebspackage.BenefitPackage;
import com.tmg.ebscore.orm.ebspackage.PkgConfigOption;
import com.tmg.ebscore.orm.ebspackage.PkgConfigOptionBenefitOptionXref;
import com.tmg.ebscore.orm.ebspackage.PkgConfigOptionFamily;
import com.tmg.ebscore.orm.ebspackage.PkgMasterListForBig3CO;
import com.tmg.ebscore.orm.packageversion.BenefitOptionBig3CO;
import com.tmg.ebscore.orm.packageversion.PkgVerBenefitOption;
import com.tmg.ebscore.orm.packageversion.PkgVerBig3CO;
import com.tmg.ebscore.orm.packageversion.PkgVerInstanceTree;
import com.tmg.ebscore.orm.packageversion.PkgVersion;
import com.tmg.ebscore.repository.MasterListRepository;
import com.tmg.ebscore.repository.PackageRepository;
import com.tmg.ebscore.repository.PackageVersionRepository;
import com.tmg.ebscore.repository.TemplateRepository;

@Service("packageService")
public class PackageService {

	@Autowired
	private PackageRepository packageRepository;

	@Autowired
	private PackageVersionRepository packageVersionRepository;

	@Autowired
	private TemplateRepository templateRepository;

	@Autowired
	private MasterListRepository masterListRepository;

	@Autowired
	private PackageVersionService packageVersionService;

	/**
	 * @param packageId
	 *            Primary Key of PackageTemplate table which is Foreign key in
	 *            PkgVersion table
	 * @return Number of Released Package Version for Each Package
	 * 
	 * @throws DataAccessException
	 * 
	 * 
	 */
	@Transactional(readOnly = true)
	public int getNoOfReleasedPackageVersions(int packageId)
			throws DataAccessException {

		int count = 0;

		List<Integer> wrkFlowState1Up = new ArrayList<Integer>();

		try {

			wrkFlowState1Up = templateRepository
					.getPkgVerWrkflowState(packageId);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);

		}

		for (Integer wrkFlowState : wrkFlowState1Up) {

			boolean flag = true;
			try {
				flag = templateRepository.getIsReleasedFlag(wrkFlowState);

			} catch (PersistenceException e) {

				throw new DataAccessException(e);

			}

			if (flag == true) {
				count++;
			} else {
				continue;
			}
		}

		return count;
	}

	/*
	 * if (packageWorkFlowState == true) {
	 * 
	 * List<PkgVersionWorkflowState> workFlowStateList = templateRepository
	 * .findAllWorkFlowStateById(existingTemplate .getPackageTemplate1up());
	 * 
	 * for (PkgVersionWorkflowState pkgVersionWorkflowState : workFlowStateList)
	 * {
	 * 
	 * templateRepository.detachedEntity(pkgVersionWorkflowState);
	 * 
	 * 
	 * pkgVersionWorkflowState.setPackageTemplate(temp);
	 * 
	 * pkgVersionWorkflowState.setPkgVersionWorkflowState1up(null);
	 * 
	 * templateRepository.saveData(pkgVersionWorkflowState); }
	 */

	/**
	 * @param data
	 *            It takes BenifitPackageData object
	 * @return ServiceOperationResult
	 * @throws DataAccessException
	 *             <p>
	 *             this method creates new package
	 */
	@Transactional
	public ServiceOperationResult saveUpdatePackage(BenifitPackageData data)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		if (data == null) {
			throw new DataAccessException();
		}

		try {
			BenefitPackage benefitPackage = null;

			if (data.getBenefitPackage1up() != null
					&& data.getBenefitPackage1up() > 0) {
				benefitPackage = packageRepository.findBenefitPackageById(data
						.getBenefitPackage1up());
				benefitPackage.setUpdateBy(data.getUpdateBy());
				benefitPackage.setUpdateDt(new Date());
				benefitPackage.setDescription(data.getPackageDescription());

				benefitPackage.setId(data.getPackageId());
				benefitPackage.seteBSInstance1up(data.geteBSInstance1up());
				benefitPackage.setName(data.getPackageName());
				packageRepository.updateData(benefitPackage);

			} else {
				benefitPackage = new BenefitPackage();
				benefitPackage.setCreateBy(data.getCreateBy());
				benefitPackage.setCreateDt(new Date());

				if (data.getPackageTemplate1up() != null) {
					benefitPackage.setPackageTemplate(templateRepository
							.findPackageTemplateById(data
									.getPackageTemplate1up()));
				}

				benefitPackage.setDescription(data.getPackageDescription());

				benefitPackage.setId(data.getPackageId());
				benefitPackage.seteBSInstance1up(data.geteBSInstance1up());
				benefitPackage.setName(data.getPackageName());

				packageRepository.saveData(benefitPackage);

			}

		} catch (Exception e) {

			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error saving value for BenefitPackage");
			throw new DataAccessException(e);
		}
		return operationResult;

	}

	/**
	 * @param data
	 *            It takes BenifitPackageData object.
	 * @param oldPackageId
	 *            Package Id from data object
	 * @param pkgversionId
	 *            List of package version id.
	 * @param serviceType
	 *            Flag from data object
	 * @param commonBenefit
	 *            Flag from data object
	 * @param benefitOption
	 *            Flag from data object
	 * @return ServiceOperationResult
	 * @throws DataAccessException
	 */
	@Transactional
	public ServiceOperationResult copyFromExistingPackage(
			BenifitPackageData data, List<Integer> pkgversionId,
			boolean serviceType, boolean commonBenefit, boolean benefitOption)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		try {

			BenefitPackage exPackage = packageRepository
					.findBenefitPackageById(data.getBenefitPackage1up());
			BenefitPackage benefitPackage = new BenefitPackage();
			benefitPackage.setCreateBy(data.getCreateBy());
			benefitPackage.setCreateDt(new Date());
			benefitPackage.setId(data.getPackageId());
			benefitPackage.setName(data.getPackageName());
			benefitPackage.setDescription(data.getPackageDescription());
			benefitPackage.seteBSInstance1up(exPackage.geteBSInstance1up());
			benefitPackage.setPackageTemplate(templateRepository
					.findPackageTemplateById(data.getPackageTemplate1up()));
			packageRepository.saveData(benefitPackage);
			System.out.println("Genereated Id:"
					+ benefitPackage.getBenefitPackage1up());

			for (Integer pkgId : pkgversionId) {
				PkgVersion version = new PkgVersion();

				PkgVersion existingPkgVersion = packageVersionRepository
						.findPackageVersionById(pkgId);
				version.setBenefitPackage(benefitPackage);
				version.setVersionNumber(existingPkgVersion.getVersionNumber());
				version.setName(existingPkgVersion.getName());
				version.setDescription(existingPkgVersion.getDescription());
				version.setPkgVersionWorkflowState(existingPkgVersion
						.getPkgVersionWorkflowState());
				version.setCreateBy(existingPkgVersion.getCreateBy());
				version.setCreateDt(new Date());
				version.seteBSInstance1up(existingPkgVersion
						.geteBSInstance1up());

				version.setCancelDate(existingPkgVersion.getCancelDate());
				version.setCreateBy(existingPkgVersion.getCreateBy());
				version.setCreateDt(new Date());
				version.seteBSInstance1up(existingPkgVersion
						.geteBSInstance1up());
				version.setEffectiveDate(existingPkgVersion.getEffectiveDate());
				packageVersionRepository.saveData(version);
				System.out.println(version.getPkgVersion1up());

				if (serviceType == true) {

					List<PkgVerBig3CO> big3cos = packageVersionRepository
							.findPkgVerBig3CoByPkgVerId(pkgId);

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

						packageVersionRepository.saveData(newPkgVerBig3CO);

					}

				}

				if (commonBenefit == true) {

					List<PkgVerInstanceTree> instanceTrees = packageVersionRepository
							.findPkgVerInstTreeByPkgVerId(pkgId);
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
				if (benefitOption == true) {
					List<PkgVerBenefitOption> benefitOptions = packageVersionRepository
							.findPkgVerBenefitOptionByPkgVerId(pkgId);

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

						packageVersionRepository.saveData(newBenefitOption);
					}

				} else {
					operationResult.setOperationResult(OperationResult.WARNING);
					messages.add("Error creating new PackageVersion  ");
				}

			}

		} catch (Exception e) {

			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error creating new BeneFitPackage");
			throw new DataAccessException(e);
		}

		return null;
	}

	/**
	 * @param packageId
	 *            Primary Key of PackageTemplate table which is Foreign key in
	 *            PkgVersion table
	 * 
	 * @return Number of UnReleased Package Version for Each Package
	 * 
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	public int getNoOfUnReleasedPackageVersions(int packageId)
			throws DataAccessException {

		int count = 0;

		List<Integer> wrkFlowState1Up = new ArrayList<Integer>();

		try {

			wrkFlowState1Up = templateRepository
					.getPkgVerWrkflowState(packageId);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);

		}

		for (Integer wrkFlowState : wrkFlowState1Up) {

			boolean flag = true;
			try {
				flag = templateRepository.getIsReleasedFlag(wrkFlowState);
			} catch (PersistenceException e) {
				throw new DataAccessException(e);

			}

			if (flag == false) {
				count++;
			} else {
				continue;
			}
		}

		return count;
	}

	/**
	 * @param id
	 *            Primary Key of BenefitPackage table
	 * 
	 * @return BenifitPackageData Object
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This Method will return the general Information of a Package
	 *             By packageId.
	 */
	@Transactional(readOnly = true)
	public BenifitPackageData getPackageDetails(int id)
			throws DataAccessException {

		BenefitPackage benefitPackage = null;
		try {

			benefitPackage = packageRepository.findBenefitPackageById(id);

		} catch (PersistenceException e) {
			throw new DataAccessException(e);

		}

		BenifitPackageData benefitPackageData = new BenifitPackageData();

		benefitPackageData.setBenefitPackage1up(benefitPackage
				.getBenefitPackage1up());

		if (benefitPackage.getPackageTemplate() != null) {
			benefitPackageData.setPackageTemplate1up(benefitPackage
					.getPackageTemplate().getPackageTemplate1up());
		}

		benefitPackageData.setPackageId(benefitPackage.getId());
		benefitPackageData.setPackageName(benefitPackage.getName());
		benefitPackageData.setPackageDescription(benefitPackage
				.getDescription());

		benefitPackageData
				.seteBSInstance1up(benefitPackage.geteBSInstance1up());

		benefitPackageData.setCreateBy(benefitPackage.getCreateBy());
		benefitPackageData.setCreateDt(benefitPackage.getCreateDt());
		benefitPackageData.setUpdateBy(benefitPackage.getUpdateBy());
		benefitPackageData.setUpdateDt(benefitPackage.getUpdateDt());

		return benefitPackageData;

	}

	/**
	 * @param page
	 *            Corresponding Page Number for Pagination
	 * 
	 * @param maxResults
	 *            Maximum number of rows displayed in a page
	 * 
	 * @return {@link PackageContainer} It returns the list of all
	 *         PackageSummaryData objects , PagesCount, TotalItems.
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             Whenever user click on Package On HomePage Of UI this method
	 *             shows the list of All Available Package details with
	 *             respective Template Details.
	 */
	@Transactional(readOnly = true)
	public PackageContainer<PackageSummaryData> getPackageList(int page,
			int maxResults) throws DataAccessException {

		Page<BenefitPackage> packageVal = null;
		try {

			packageVal = packageRepository.findPackageList(new PageRequest(
					page, maxResults, new Sort(Sort.Direction.ASC, "name")));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);

		}

		List<PackageSummaryData> packageSummaryListData = new ArrayList<PackageSummaryData>();

		for (BenefitPackage benefitPackage : packageVal) {

			PackageSummaryData packageSummaryData = new PackageSummaryData();

			if (benefitPackage.getPackageTemplate() != null) {

				packageSummaryData.setPackageTemplate1up(benefitPackage
						.getPackageTemplate().getPackageTemplate1up());

				packageSummaryData.setTemplateID(benefitPackage
						.getPackageTemplate().getId());

				packageSummaryData.setTemplateName(benefitPackage
						.getPackageTemplate().getName());
			}

			packageSummaryData.setBenefitPackage1up(benefitPackage
					.getBenefitPackage1up());

			packageSummaryData.setPackageId(benefitPackage.getId());

			packageSummaryData.setPackageName(benefitPackage.getName());

			packageSummaryData.setBenefitPackageTypeAsString1up(benefitPackage
					.getPackageTemplate().getBenefitPackageTypeAsString()
					.getMasterListStringVal1up());

			packageSummaryData.setNoOfPackageVersions(packageRepository
					.findNoOfPackageVersionsById(benefitPackage
							.getBenefitPackage1up()));

			packageSummaryData.setNoOfReleasedPackageVersions(this
					.getNoOfReleasedPackageVersions(benefitPackage
							.getBenefitPackage1up()));

			packageSummaryData.seteBSInstance1up(benefitPackage
					.geteBSInstance1up());
			packageSummaryData.setCreateBy(benefitPackage.getCreateBy());
			packageSummaryData.setCreateDt(benefitPackage.getCreateDt());
			packageSummaryData.setUpdateBy(benefitPackage.getUpdateBy());
			packageSummaryData.setUpdateDt(benefitPackage.getUpdateDt());

			packageSummaryListData.add(packageSummaryData);

		}

		PackageContainer<PackageSummaryData> result = new PackageContainer<PackageSummaryData>();
		result.setData(packageSummaryListData);
		result.setPagesCount(packageVal.getTotalPages());
		result.setTotalItems(packageVal.getTotalElements());
		return result;

	}

	/**
	 * @param = benefitPackage1up
	 * @return = ServiceOperationResult
	 * @throws DataAccessException
	 */
	@Transactional
	public ServiceOperationResult deletePackage(int packageId)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		try {
			// packageRepository.deletePkgNoticeByPackageId(packageId);

			BenefitPackage benPackage = packageRepository
					.findBenefitPackageSetById(packageId);
			Set<PkgVersion> pkgVersions = benPackage.getPkgBenefitPkg();
			for (PkgVersion pkgVersion : pkgVersions) {
				packageVersionService.deletePackageVersion(pkgVersion
						.getPkgVersion1up());
			}

			Set<PkgConfigOptionFamily> configOptionFamilies = benPackage
					.getBenPkgFamily();
			System.out.println(configOptionFamilies);
			for (PkgConfigOptionFamily pkgConfigOptionFamily : configOptionFamilies) {

				Set<PkgConfigOption> configOptions = pkgConfigOptionFamily
						.getConfigOptionFamily();
				System.out.println(configOptions);
				List<AccountPkgConfigOptionXref> accountPkgConfigOptionXrefs = packageRepository
						.findAllAccountPkgConfigOptionXref();
				List<Integer> accountPkgIdList = new ArrayList<Integer>();
				for (AccountPkgConfigOptionXref accountPkgConfigOptionXref : accountPkgConfigOptionXrefs) {
					accountPkgIdList.add(accountPkgConfigOptionXref
							.getPkgConfigOption().getPkgConfigOption1up());
				}
				System.out.println(accountPkgIdList);
				for (PkgConfigOption pkgConfigOption : configOptions) {
					packageRepository
							.deletePkgConfigOptionBenefitOptionXrefByPkgConfigOptionId(pkgConfigOption
									.getPkgConfigOption1up());
					packageRepository
							.deletePkgVersionProductXrefByPkgConfigOptionId(pkgConfigOption
									.getPkgConfigOption1up());
					if (accountPkgIdList.contains(pkgConfigOption
							.getPkgConfigOption1up())) {
						operationResult
								.setOperationResult(OperationResult.FAILURE);
						messages.add("Error deleting value for Package");
						return operationResult;
					}
				}

				packageRepository
						.deletePkgConfigOptionByPkgConfigOptionFamilyId(pkgConfigOptionFamily
								.getPkgConfigOptionFamily1up());

			}
			packageRepository
					.deletePkgConfigOptionFamilyByBenefitPackageId(packageId);

			Set<PkgMasterListForBig3CO> pkgMasterListForBig3COs = benPackage
					.getBenPkgMasterList();

			for (PkgMasterListForBig3CO pkgMasterListForBig3CO : pkgMasterListForBig3COs) {

				Set<PkgVerBig3CO> pkgVerBig3COs = pkgMasterListForBig3CO
						.getPkgBigCoI();
				for (PkgVerBig3CO pkgVerBig3CO : pkgVerBig3COs) {

					packageRepository
							.deleteBenefitOptionBig3COByPkgVerBig3COId(pkgVerBig3CO
									.getPkgVerBig3CO1up());

					packageRepository
							.deleteShapeValueBenefitOptionForBig3COByPkgVerBig3COId(pkgVerBig3CO
									.getPkgVerBig3CO1up());

					packageRepository
							.deleteShapeValueBig3COByPkgVerBig3COId(pkgVerBig3CO
									.getPkgVerBig3CO1up());

					packageRepository
							.deleteLimitShapeBenefitOptionOverrideByPkgVerBig3COId(pkgVerBig3CO
									.getPkgVerBig3CO1up());

					packageRepository
							.deleteMessageShapeBenefitOptionOverrideByPkgVerBig3COId(pkgVerBig3CO
									.getPkgVerBig3CO1up());
					packageRepository
							.deleteMatrixShapeBig3COMessageXXrefByPkgVerBig3COId(pkgVerBig3CO
									.getPkgVerBig3CO1up());
					packageRepository
							.deleteMatrixShapeBig3COLimitXXrefByPkgVerBig3COId(pkgVerBig3CO
									.getPkgVerBig3CO1up());

					packageRepository
							.deleteMatrixShapeBig3COXrefByPkgVerBig3COId(pkgVerBig3CO
									.getPkgVerBig3CO1up());
				}
				packageRepository
						.deletePkgVerBig3COBypkgMasterListForBig3COId(pkgMasterListForBig3CO
								.getPkgMasterListForBig3CO1up());

			}
			packageRepository
					.deletepkgMasterListForBig3COByBenefitPackageId(packageId);

			packageRepository.deleteBenefitPackageById(packageId);
			System.out.println("Hello");

		} catch (PersistenceException e) {
			throw new DataAccessException(e.getMessage());

		}

		return operationResult;

	}

	/**
	 * @param pkgVerBenefitOption1up
	 * @return ServiceOperationResult
	 * @throws DataAccessException
	 */
	@Transactional
	public ServiceOperationResult deleteBenefitOption(int pkgVerBenefitOption1up)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		try {

			packageRepository
					.deleteBenefitOptionBig3CoByConfigId(pkgVerBenefitOption1up);

			packageRepository
					.deletePkgConfigOptionBenefitOptionXrefByConfigId(pkgVerBenefitOption1up);

			packageRepository
					.deleteShapeValueBenefitOptionForBig3COByConfigId(pkgVerBenefitOption1up);

			packageRepository
					.deleteLimitShapeBenefitOptionOverrideByConfigId(pkgVerBenefitOption1up);

			packageRepository
					.deleteMessageShapeBenefitOptionOverrideByConfigId(pkgVerBenefitOption1up);

			packageVersionRepository
					.deletePkgVerBenefitOptionById(pkgVerBenefitOption1up);
			System.out.println("Hello");

		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.FAILURE);
			messages.add("Error while deleting BenefitOption "
					+ pkgVerBenefitOption1up);
			throw new DataAccessException(e);

		}
		return operationResult;
	}

	/**
	 * @param id
	 *            = benefitPackage1up
	 * @param page
	 *            = Corresponding Page Number for Pagination
	 * @param maxResults
	 *            = max no. of rows in a page
	 * @return = PackageContainer<PkgConfigOptionData>
	 * @throws DataAccessException
	 */
	@Transactional
	public PackageContainer<PkgConfigOptionData> getAllBenefitOffering(int id,
			int page, int maxResults) throws DataAccessException {

		Page<PkgConfigOption> confOptions = null;

		List<PkgConfigOptionBenefitOptionXref> confOptionXref = null;
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> list1 = new ArrayList<Integer>();
		Page<PkgVersion> pkgVers = null;
		List<PkgConfigOptionData> pkgConfigOptionDataList = new ArrayList<PkgConfigOptionData>();
		try {

			pkgVers = packageVersionRepository.findPackageVersionByPkgId(id,
					new PageRequest(page, maxResults));
			for (PkgVersion pkgVer : pkgVers) {
				confOptions = packageRepository
						.findAllPkgConfigOptionByPkgVerId(pkgVer
								.getPkgVersion1up(), new PageRequest(page,
								maxResults));

				for (PkgConfigOption pkgConfigOption : confOptions) {
					list.add(pkgConfigOption.getPkgConfigOption1up());
				}

				confOptionXref = packageRepository
						.findAllPkgConfigOptionBenefitOptionXrefBypkgConfigOptionId(list);

				for (PkgConfigOptionBenefitOptionXref xref : confOptionXref) {
					list1.add(xref.getPkgConfigOption().getPkgConfigOption1up());
				}

				for (PkgConfigOption pkgConfigOption : confOptions) {

					PkgConfigOptionData data = new PkgConfigOptionData();

					data.setPkgConfigOption1up(pkgConfigOption
							.getPkgConfigOption1up());

					data.setAllowUse(pkgConfigOption.getDoNotUse());

					data.setSalesCancelDate1(pkgConfigOption
							.getSalesCancelDate());

					data.setClaimsCancelDate(pkgConfigOption
							.getClaimsCancelDate());

					data.setClaimsEffectiveDate(pkgConfigOption
							.getClaimsEffectiveDate());

					data.setEffectiveDate(pkgConfigOption
							.getSalesEffectiveDate());

					data.setConfigOptionFamily1up(pkgConfigOption
							.getPkgConfigOptionFamily()
							.getPkgConfigOptionFamily1up());

					data.setPackageVersion(pkgConfigOption.getPkgVersion()
							.getPkgVersion1up());

					data.setWorkflowState(pkgConfigOption
							.getPkgVersionWorkflowState()
							.getPkgVersionWorkflowState1up());

					if (list1.contains(pkgConfigOption.getPkgConfigOption1up())) {
						List<PkgConfigOptionBenefitOptionXref> optionXrefs = packageRepository
								.findPkgConfigOptionBenefitOptionXrefByConfigId(pkgConfigOption
										.getPkgConfigOption1up());
						List<Integer> ids = new ArrayList<Integer>();
						for (PkgConfigOptionBenefitOptionXref ref : optionXrefs) {

							ids.add(ref.getPkgVerBenefitOption()
									.getPkgVerBenefitOption1up());
						}

						data.setBenefitOption(ids);
					}

					pkgConfigOptionDataList.add(data);
				}
			}
		} catch (PersistenceException e) {
			throw new DataAccessException();
		}

		PackageContainer<PkgConfigOptionData> container = new PackageContainer<PkgConfigOptionData>();

		container.setData(pkgConfigOptionDataList);
		container.setPagesCount(confOptions.getTotalPages());
		container.setTotalItems(confOptions.getTotalElements());

		return container;
	}

	/**
	 * @param id
	 *            = benefitPackage1up
	 * @param page
	 *            = Corresponding Page Number for Pagination
	 * @param maxResults
	 *            = max no. of rows in a page
	 * @return = PackageContainer<PkgMasterListForBig3COData>
	 * @throws DataAccessException
	 * 
	 */

	@Transactional
	public ServiceOperationResult saveServiceTypeForPackage(
			List<PkgMasterListForBig3COData> dataList, int packageID)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		if (dataList == null) {
			throw new DataAccessException();
		}
		List<PkgMasterListForBig3CO> listForBig3COs = null;
		try {
			listForBig3COs = packageRepository
					.findAllServiceTypeForAPackage(packageID);

		} catch (PersistenceException e) {
			throw new DataAccessException(e);

		}
		List<Integer> listBig3COIds = new ArrayList<Integer>();
		for (PkgMasterListForBig3CO pkgMasterListForBig3CO : listForBig3COs) {

			listBig3COIds.add(pkgMasterListForBig3CO
					.getPkgMasterListForBig3CO1up());
		}

		System.out.println(listBig3COIds.size());

		List<PkgMasterListForBig3CO> big3cosList = new ArrayList<PkgMasterListForBig3CO>();
		try {
			for (PkgMasterListForBig3COData big3coData : dataList) {

				PkgMasterListForBig3CO big3co = new PkgMasterListForBig3CO();

				System.out.println(listBig3COIds.contains(big3coData
						.getPkgMasterListForBig3COId()));

				if (listBig3COIds.contains(big3coData
						.getPkgMasterListForBig3COId())) {
					throw new DataAccessException(
							"Duplicate Entry Not all allowed");

				} else {

					big3co.setBenefitPackage(packageRepository
							.findBenefitPackageById(big3coData
									.getBenefitPackage1up()));

					big3co.setMasterListServiceDefinition(masterListRepository
							.findMasterListServiceDefinitionById(big3coData
									.getMasterListServiceDefinitionId()));

					big3co.setMasterListStringVal(masterListRepository
							.findMasterListStringValById(big3coData
									.getBenefitRule1Up()));

					big3co.setCreateBy(big3coData.getCreateBy());

					big3co.setCreateDt(new Date());

					big3co.seteBSInstance1up(big3coData.geteBSInstance1up());

					big3cosList.add(big3co);

				}

			}
			for (PkgMasterListForBig3CO pkgMasterListForBig3CO : big3cosList) {

				packageRepository.saveData(pkgMasterListForBig3CO);

			}

		} catch (PersistenceException e) {

			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error saving value for BenefitPackage");
			throw new DataAccessException(e);
		}
		return operationResult;

	}

	@Transactional(readOnly = true)
	public PackageContainer<PkgMasterListForBig3COData> getServiceTypeForPackage(
			int id, int page, int maxResults) throws DataAccessException {

		Page<PkgMasterListForBig3CO> pkgMastBig3CO = null;
		List<Integer> serviceTypeInUseList = new ArrayList<Integer>();
		try {
			pkgMastBig3CO = packageRepository
					.findPkgMasterListForBig3COByPkgId(id, new PageRequest(
							page, maxResults));
			serviceTypeInUseList = packageRepository
					.findSetOfPkgVerBig3COUseAndUnUse(id);

		} catch (PersistenceException e) {
			throw new DataAccessException();

		}

		List<PkgMasterListForBig3COData> pkgMasterListData = new ArrayList<PkgMasterListForBig3COData>();

		for (PkgMasterListForBig3CO pkgMasterListForBig3Co : pkgMastBig3CO) {

			PkgMasterListForBig3COData data = new PkgMasterListForBig3COData();

			if (serviceTypeInUseList != null) {
				data.setInUse(serviceTypeInUseList
						.contains(pkgMasterListForBig3Co
								.getPkgMasterListForBig3CO1up()));
			} else {
				data.setInUse(false);

			}
			// System.out.println(serviceTypeInUseList
			// .contains(pkgMasterListForBig3Co
			// .getPkgMasterListForBig3CO1up()));
			if (pkgMasterListForBig3Co.getMasterListServiceDefinition() != null)

			{
				data.setMasterListServiceDefinitionId(pkgMasterListForBig3Co
						.getMasterListServiceDefinition()
						.getMasterListServiceDefinition1up());

			}

			if (pkgMasterListForBig3Co.getMasterListStringVal() != null) {
				data.setBenefitRule1Up(pkgMasterListForBig3Co
						.getMasterListStringVal().getMasterListStringVal1up());
			}

			if (pkgMasterListForBig3Co.getMasterListServiceDefinition()
					.getMasterListStringValCat1() != null) {
				data.setCategory1(pkgMasterListForBig3Co
						.getMasterListServiceDefinition()
						.getMasterListStringValCat1().getStringValue());

			}
			if (pkgMasterListForBig3Co.getMasterListServiceDefinition()
					.getMasterListStringValCat2() != null) {
				data.setCategory2(pkgMasterListForBig3Co
						.getMasterListServiceDefinition()
						.getMasterListStringValCat2().getStringValue());

			}
			if (pkgMasterListForBig3Co.getMasterListServiceDefinition()
					.getMasterListStringValCat3() != null) {
				data.setCategory3(pkgMasterListForBig3Co
						.getMasterListServiceDefinition()
						.getMasterListStringValCat3().getStringValue());

			}
			pkgMasterListData.add(data);
		}
		PackageContainer<PkgMasterListForBig3COData> container = new PackageContainer<PkgMasterListForBig3COData>();

		container.setData(pkgMasterListData);
		container.setPagesCount(pkgMastBig3CO.getTotalPages());
		container.setTotalItems(pkgMastBig3CO.getTotalElements());

		return container;
	}

	/**
	 * @param optionData
	 *            It takes PkgConfigOptionData object
	 * @return ServiceOperationResult
	 * @throws DataAccessException
	 *             <p>
	 *             This method creates new BenefitOffering for a package
	 */
	@Transactional
	public ServiceOperationResult saveAndUpdateBenefitOffering(
			PkgConfigOptionData optionData,
			PkgConfigOptionFamilyData configOptionFamily,
			List<Integer> benefitOptionIds) throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		try {
			PkgConfigOption newConfigOption = null;
			if (optionData.getPkgConfigOption1up() != null
					&& optionData.getPkgConfigOption1up() > 0) {

				newConfigOption = packageRepository
						.findPkgConfigOptionById(optionData
								.getPkgConfigOption1up());

				newConfigOption.setUpdateBy(optionData.getUpdateBy());
				newConfigOption.setUpdateDt(new Date());
			} else {
				newConfigOption = new PkgConfigOption();
				newConfigOption.setCreateBy(optionData.getCreateBy());
				newConfigOption.setCreateDt(new Date());

			}

			newConfigOption.setClaimsCancelDate(optionData
					.getClaimsCancelDate());
			newConfigOption.setClaimsEffectiveDate(optionData
					.getClaimsEffectiveDate());
			newConfigOption
					.setSalesCancelDate(optionData.getSalesCancelDate1());
			newConfigOption.setClaimsEffectiveDate(optionData
					.getClaimsEffectiveDate());
			newConfigOption.setDescription(optionData.getDescription());
			newConfigOption.setDoNotUse(optionData.getAllowUse());
			newConfigOption.seteBSInstance1up(optionData.geteBSInstance1up());

			newConfigOption
					.setMasterListStringVal(masterListRepository
							.findMasterListStringValById(optionData
									.getMarketSegment()));

			newConfigOption.setPkgVersion(packageVersionRepository
					.findPackageVersionById(optionData.getPackageVersion()));
			newConfigOption.setPkgVersionWorkflowState(packageVersionRepository
					.findPkgVersionWorkflowStateById(optionData
							.getWorkflowState()));

			PkgConfigOptionFamily family = null;
			if (configOptionFamily.getConfigOptionFamily1up() != null
					&& configOptionFamily.getConfigOptionFamily1up() > 0) {

				family = packageRepository
						.findPkgConfigOptionFamilyById(configOptionFamily
								.getConfigOptionFamily1up());
				// family.setUpdateBy(configOptionFamily.getUpdateBy());
				// family.setUpdateDt(new Date());

			} else {
				family = new PkgConfigOptionFamily();
				family.setCreateBy(configOptionFamily.getCreateBy());
				family.setCreateDt(new Date());
			}
			family.setBenefitPackage(packageRepository
					.findBenefitPackageById(configOptionFamily
							.getBenefitPackage1up()));
			family.setConfigOptionFamilyName(configOptionFamily.getFamilyName());

			family.seteBSInstance1up(configOptionFamily.geteBSInstance1up());
			packageVersionRepository.saveData(family);
			newConfigOption.setPkgConfigOptionFamily(family);

			packageRepository.saveData(newConfigOption);
			try {
				associateBenefitOptionInBenefitOffering(benefitOptionIds);
				for (Integer benefitOptionId : benefitOptionIds) {
					PkgConfigOptionBenefitOptionXref benefitOptionXref = new PkgConfigOptionBenefitOptionXref();
					benefitOptionXref.seteBSInstance1up(optionData
							.geteBSInstance1up());
					benefitOptionXref.setCreateBy(optionData.getCreateBy());
					benefitOptionXref.setCreateDt(new Date());
					benefitOptionXref.setPkgConfigOption(newConfigOption);
					benefitOptionXref
							.setPkgVerBenefitOption(packageVersionRepository
									.findPkgVerBenefitOptionById(benefitOptionId));
					packageRepository.saveData(benefitOptionXref);

				}

			} catch (DataAccessException e) {
				throw new DataAccessException(e.getMessage());
			}

		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error creating BenefitOffering  ");
			throw new DataAccessException(e);
		}
		return operationResult;
	}

	private void associateBenefitOptionInBenefitOffering(
			List<Integer> benefitOptionIds) throws DataAccessException {

		List<BenefitOptionBig3CO> benefitOptionBig3COs = new ArrayList<BenefitOptionBig3CO>();
		benefitOptionBig3COs = packageVersionRepository
				.findListOfPkgVerBenefitOptionBig3CosById(benefitOptionIds);

		for (BenefitOptionBig3CO benefitOption1Big3CO : benefitOptionBig3COs) {
			int count = 0;
			for (BenefitOptionBig3CO benefitOption2Big3CO : benefitOptionBig3COs) {
				if (benefitOption1Big3CO.getPkgVerBig3CO().getPkgVerBig3CO1up() == benefitOption2Big3CO
						.getPkgVerBig3CO().getPkgVerBig3CO1up()
						&& benefitOption1Big3CO.getPkgVerInstanceTree()
								.getPkgVerInstanceTree1up() == benefitOption2Big3CO
								.getPkgVerInstanceTree()
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

	/**
	 * @param optionData
	 *            It takes PkgConfigOptionData object
	 * @return ServiceOperationResult
	 * @throws DataAccessException
	 *             <p>
	 *             This method creates new BenefitOffering from exsisting
	 *             BenefitOffering
	 */
	@Transactional
	public ServiceOperationResult createBenefitOfferingFromExsisting(
			PkgConfigOptionData optionData, int existingId)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		PkgConfigOption newConfigOption = new PkgConfigOption();

		try {
			PkgConfigOption exsistingOption = packageRepository
					.findPkgConfigOptionById(existingId);

			newConfigOption.setClaimsCancelDate(optionData
					.getClaimsCancelDate());
			newConfigOption.setClaimsEffectiveDate(optionData
					.getClaimsEffectiveDate());
			newConfigOption.setCreateBy(optionData.getCreateBy());
			newConfigOption.setCreateDt(new Date());
			newConfigOption.setDescription(optionData.getDescription());
			newConfigOption.setDoNotUse(optionData.getAllowUse());
			newConfigOption.seteBSInstance1up(optionData.geteBSInstance1up());
			newConfigOption
					.setMasterListStringVal(masterListRepository
							.findMasterListStringValById(optionData
									.getMarketSegment()));
			newConfigOption.setPkgConfigOptionFamily(exsistingOption
					.getPkgConfigOptionFamily());
			newConfigOption.setPkgVersion(packageVersionRepository
					.findPackageVersionById(optionData.getPackageVersion()));
			newConfigOption.setPkgVersionWorkflowState(packageVersionRepository
					.findPkgVersionWorkflowStateById(optionData
							.getWorkflowState()));
			newConfigOption
					.setSalesCancelDate(optionData.getSalesCancelDate1());
			newConfigOption
					.setSalesEffectiveDate(optionData.getEffectiveDate());
			packageRepository.saveData(newConfigOption);

			// Save data in PkgConfigOptionBenefitOptionXref
			List<PkgConfigOptionBenefitOptionXref> benefitOptionXrefs = packageRepository
					.findPkgConfigOptionBenefitOptionXrefByConfigId(optionData
							.getPkgConfigOption1up());
			List<Integer> dtoXrefs = optionData.getBenefitOption();
			for (PkgConfigOptionBenefitOptionXref pkgConfigOptionBenefitOptionXref : benefitOptionXrefs) {

				if (dtoXrefs.contains(pkgConfigOptionBenefitOptionXref
						.getPkgVerBenefitOption().getPkgVerBenefitOption1up())) {

					PkgConfigOptionBenefitOptionXref benefitOptionXref = new PkgConfigOptionBenefitOptionXref();
					benefitOptionXref.setCreateBy(optionData.getCreateBy());
					benefitOptionXref.setCreateDt(new Date());
					benefitOptionXref.seteBSInstance1up(optionData
							.geteBSInstance1up());
					benefitOptionXref.setPkgConfigOption(newConfigOption);
					benefitOptionXref
							.setPkgVerBenefitOption(pkgConfigOptionBenefitOptionXref
									.getPkgVerBenefitOption());
					packageRepository.saveData(benefitOptionXref);
				}

			}
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error creating BenefitOffering  ");
			throw new DataAccessException(e);
		}

		return operationResult;
	}

	@Transactional(readOnly = true)
	public List<PkgConfigOptionFamilyData> getAllConfigOptionFamilyByPackageId(
			int pkgId) throws DataAccessException {

		List<PkgConfigOptionFamily> configOptionFamilies = new ArrayList<PkgConfigOptionFamily>();
		try {
			configOptionFamilies = packageRepository
					.findAllConfigOptionFamilyByPackageId(pkgId);

		} catch (PersistenceException e) {
			throw new DataAccessException();

		}

		List<PkgConfigOptionFamilyData> configOptionFamilyDatas = new ArrayList<PkgConfigOptionFamilyData>();

		for (PkgConfigOptionFamily configOptionFamily : configOptionFamilies) {

			PkgConfigOptionFamilyData data = new PkgConfigOptionFamilyData();

			data.setPkgConfigOption1up(configOptionFamily
					.getPkgConfigOptionFamily1up());

			if (configOptionFamily.getBenefitPackage() != null) {
				data.setBenefitPackage1up(configOptionFamily
						.getBenefitPackage().getBenefitPackage1up());
			}

			data.setFamilyName(configOptionFamily.getConfigOptionFamilyName());

			data.setCreateBy(configOptionFamily.getCreateBy());
			data.setCreateDt(configOptionFamily.getCreateDt());
			data.seteBSInstance1up(configOptionFamily.geteBSInstance1up());
			data.setUpdateBy(configOptionFamily.getUpdateBy());
			data.setUpdateDt(configOptionFamily.getUpdateDt());

			configOptionFamilyDatas.add(data);
		}

		return configOptionFamilyDatas;
	}

}
