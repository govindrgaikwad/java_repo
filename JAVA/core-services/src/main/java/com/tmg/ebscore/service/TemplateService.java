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
import com.tmg.ebscore.dto.ebspackage.BenifitPackageData;
import com.tmg.ebscore.dto.masterlist.MasterListIntValData;
import com.tmg.ebscore.dto.masterlist.MasterListStringValData;
import com.tmg.ebscore.dto.template.PackageTemplateData;
import com.tmg.ebscore.dto.template.PkgVersionWorkflowStateData;
import com.tmg.ebscore.dto.template.ShapeValueDefaultData;
import com.tmg.ebscore.dto.template.TemplateAllowsIntValData;
import com.tmg.ebscore.dto.template.TemplateAllowsLimitValData;
import com.tmg.ebscore.dto.template.TemplateAllowsMessageValData;
import com.tmg.ebscore.dto.template.TemplateAllowsServiceDefinitionData;
import com.tmg.ebscore.dto.template.TemplateAllowsStringValData;
import com.tmg.ebscore.dto.template.TemplateContainer;
import com.tmg.ebscore.dto.template.TemplateSummaryData;
import com.tmg.ebscore.dto.template.TemplateTreeData;
import com.tmg.ebscore.dto.template.TemplateWorkFlowStateData;
import com.tmg.ebscore.orm.DataAccessException;
import com.tmg.ebscore.orm.IsLocked;
import com.tmg.ebscore.orm.ebspackage.BenefitPackage;
import com.tmg.ebscore.orm.ebspackage.PkgConfigOption;
import com.tmg.ebscore.orm.masterlist.MasterListIntVal;
import com.tmg.ebscore.orm.masterlist.MasterListLimitVal;
import com.tmg.ebscore.orm.masterlist.MasterListMessageVal;
import com.tmg.ebscore.orm.masterlist.MasterListServiceDefinition;
import com.tmg.ebscore.orm.masterlist.MasterListStringVal;
import com.tmg.ebscore.orm.packageversion.PkgVerInstanceTree;
import com.tmg.ebscore.orm.packageversion.PkgVersion;
import com.tmg.ebscore.orm.packageversion.PkgVersionWorkflowState;
import com.tmg.ebscore.orm.template.MatrixShapeLimitXref;
import com.tmg.ebscore.orm.template.MatrixShapeMessageXref;
import com.tmg.ebscore.orm.template.PackageTemplate;
import com.tmg.ebscore.orm.template.ShapeValueDefault;
import com.tmg.ebscore.orm.template.TemplateAllowsIntVal;
import com.tmg.ebscore.orm.template.TemplateAllowsIntValId;
import com.tmg.ebscore.orm.template.TemplateAllowsLimitVal;
import com.tmg.ebscore.orm.template.TemplateAllowsLimitValId;
import com.tmg.ebscore.orm.template.TemplateAllowsMessageVal;
import com.tmg.ebscore.orm.template.TemplateAllowsMessageValId;
import com.tmg.ebscore.orm.template.TemplateAllowsServiceDefinition;
import com.tmg.ebscore.orm.template.TemplateAllowsServiceDefinitionId;
import com.tmg.ebscore.orm.template.TemplateAllowsStringVal;
import com.tmg.ebscore.orm.template.TemplateAllowsStringValId;
import com.tmg.ebscore.orm.template.TemplateTree;
import com.tmg.ebscore.orm.template.TemplateWorkflowState;
import com.tmg.ebscore.orm.template.TreeConnector;
import com.tmg.ebscore.orm.template.TreeShape;
import com.tmg.ebscore.repository.MasterListRepository;
import com.tmg.ebscore.repository.PackageRepository;
import com.tmg.ebscore.repository.PackageVersionRepository;
import com.tmg.ebscore.repository.TemplateRepository;

@Service("templateService")
public class TemplateService {

	@Autowired
	private TemplateRepository templateRepository;

	@Autowired
	private MasterListRepository masterListRepository;

	@Autowired
	private PackageService packageService;

	@Autowired
	private PackageVersionRepository packageVersionRepository;

	@Autowired
	private PackageRepository packageRepository;

	@Autowired
	private PackageVersionService packageVersionService;

	/**
	 * @param page
	 *            corresponding page no.
	 * @param maxResults
	 *            Maximum no. rows displayed in a page.
	 * @return {@link TemplateContainer} It returns the list of all
	 *         TemplateSummaryData objects, PagesCount, TotalItems.
	 * @throws DataAccessException
	 *             <p>
	 *             This method retrieve the list of all available Template list
	 *             their work flow state, No of packages for specific Template.
	 *             This method also return the total number of released and
	 *             unreleased package version for for a specific template.
	 */
	@Transactional(readOnly = true)
	public TemplateContainer<TemplateSummaryData> getTemplateList(int page,
			int maxResults) throws DataAccessException {

		Page<PackageTemplate> packageTemplateVal = null;

		try {
			packageTemplateVal = templateRepository
					.findTemplateList(new PageRequest(page, maxResults));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<PackageTemplate> ListPackageTemplate = packageTemplateVal
				.getContent();

		List<Integer> tempSummary = new ArrayList<Integer>();

		for (PackageTemplate packageTemplate : ListPackageTemplate) {

			tempSummary.add(packageTemplate.getPackageTemplate1up());

		}

		List<Integer> inUseTemplateList = templateRepository
				.findSetOfBenefitPackageByTemplateId(tempSummary);

		List<TemplateSummaryData> templateSummaryListData = new ArrayList<TemplateSummaryData>();

		for (PackageTemplate packageTemplate : packageTemplateVal) {

			TemplateSummaryData templateSummaryData = new TemplateSummaryData();

			templateSummaryData.setPackageTemplate1Up(packageTemplate
					.getPackageTemplate1up());
			if (inUseTemplateList != null) {
				templateSummaryData.setInUse(inUseTemplateList
						.contains(packageTemplate.getPackageTemplate1up()));
			} else {
				templateSummaryData.setInUse(false);
			}

			templateSummaryData.setLocked(this.isLockedTemplate(packageTemplate
					.getPackageTemplate1up()));
			templateSummaryData.setTemplateID(packageTemplate.getId());
			templateSummaryData.setTemplateName(packageTemplate.getName());

			if (packageTemplate.getBenefitPackageTypeAsString() != null) {
				templateSummaryData.setTemplateType(packageTemplate
						.getBenefitPackageTypeAsString().getStringValue());
			}

			if (packageTemplate.getTemplateWorkflowState() != null) {

				templateSummaryData.setWorkflowState(packageTemplate
						.getTemplateWorkflowState().getName());

				templateSummaryData.setReleased(packageTemplate
						.getTemplateWorkflowState().getReleasedFlag());
			}

			try {
				templateSummaryData.setNoOfPackages(templateRepository
						.findNoOfPackageById(packageTemplate
								.getPackageTemplate1up()));
			} catch (PersistenceException e) {
				throw new DataAccessException(e);
			}

			templateSummaryData.setNoOfReleasedPackageVersions(this
					.getNoOfReleasedPackageVersions(packageTemplate
							.getPackageTemplate1up()));

			templateSummaryData.setNoOfUnReleasedPackageVersions(this
					.getNoOfUnreleasedPackageVersions(packageTemplate
							.getPackageTemplate1up()));

			templateSummaryData.seteBSInstance1up(packageTemplate
					.geteBSInstance1up());
			templateSummaryData.setCreateBy(packageTemplate.getCreateBy());
			templateSummaryData.setCreateDt(packageTemplate.getCreateDt());
			templateSummaryData.setUpdateBy(packageTemplate.getUpdateBy());
			templateSummaryData.setUpdateDt(packageTemplate.getUpdateDt());

			templateSummaryListData.add(templateSummaryData);

		}

		TemplateContainer<TemplateSummaryData> result = new TemplateContainer<TemplateSummaryData>();
		result.setData(templateSummaryListData);
		result.setPagesCount(packageTemplateVal.getTotalPages());
		result.setTotalItems(packageTemplateVal.getTotalElements());
		return result;

	}

	/**
	 * @param page
	 *            corresponding page no.
	 * @param maxResults
	 *            Maximum no. rows displayed in a page.
	 * @return {@link TemplateContainer} It returns the list of all
	 *         TemplateWorkFlowStateData objects, PagesCount, TotalItems.
	 * @throws DataAccessException
	 *             <p>
	 *             Whenever user create a new template the workflowstate must be
	 *             set from available workflowstate in template level. This
	 *             method provide the list of available workflowstate.
	 */
	@Transactional(readOnly = true)
	public TemplateContainer<TemplateWorkFlowStateData> getTemplateWorkFlowStateListAtTemplate(
			int page, int maxResults) throws DataAccessException {

		Page<TemplateWorkflowState> templateWorkFlowState = null;

		try {
			templateWorkFlowState = templateRepository
					.findTemplateWorkFlowStateListAtTemplate(new PageRequest(
							page, maxResults, new Sort(Sort.Direction.ASC,
									"name")));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<TemplateWorkFlowStateData> templateWorkFlowStateListData = new ArrayList<TemplateWorkFlowStateData>();

		for (TemplateWorkflowState tWorkflowState : templateWorkFlowState) {

			TemplateWorkFlowStateData templateWorkFlowStateData = new TemplateWorkFlowStateData();

			templateWorkFlowStateData
					.setTemplateWorkflowState1up(tWorkflowState
							.getTemplateWorkflowState1up());

			templateWorkFlowStateData.setWorkflowName(tWorkflowState.getName());
			templateWorkFlowStateData.setWorkflowDescription(tWorkflowState
					.getDescription());
			templateWorkFlowStateData.setReleased(tWorkflowState
					.getReleasedFlag());
			templateWorkFlowStateData.setDisplayOrder(tWorkflowState
					.getDisplayOrder());

			templateWorkFlowStateData.seteBSInstance1up(tWorkflowState
					.geteBSInstance1up());
			templateWorkFlowStateData.setCreateBy(tWorkflowState.getCreateBy());
			templateWorkFlowStateData.setCreateDt(tWorkflowState.getCreateDt());
			templateWorkFlowStateData.setUpdateBy(tWorkflowState.getUpdateBy());
			templateWorkFlowStateData.setUpdateDt(tWorkflowState.getUpdateDt());

			templateWorkFlowStateListData.add(templateWorkFlowStateData);

		}

		TemplateContainer<TemplateWorkFlowStateData> result = new TemplateContainer<TemplateWorkFlowStateData>();
		result.setData(templateWorkFlowStateListData);
		result.setPagesCount(templateWorkFlowState.getTotalPages());
		result.setTotalItems(templateWorkFlowState.getTotalElements());
		return result;

	}

	/**
	 * @param ids
	 *            List of PK of PackageTemplate
	 * @return ServiceOperationResult
	 * @throws DataAccessException
	 */
	@Transactional
	public ServiceOperationResult deleteWorkFlowStateTemplate(List<Integer> ids)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();

		List<String> messages = new ArrayList<String>();
		try {
			for (Integer id : ids) {

				int WorkFlowStateCount = templateRepository
						.findNoOfPackagesInThisState(id);
				if (WorkFlowStateCount < 1) {

					PkgVersionWorkflowState versionWorkflowState = templateRepository
							.findSetOfPkgConfigOptionAndPkgVersionByWorkFlowStateId(id);

					Set<PkgVersion> pkgVersions = versionWorkflowState
							.getPkgVerWorkFlow();
					if (pkgVersions != null) {
						for (PkgVersion pkgVersion : pkgVersions) {

							packageVersionService
									.deletePackageVersion(pkgVersion
											.getPkgVersion1up());
						}
					}

					Set<PkgConfigOption> configOptions = versionWorkflowState
							.getConfigOptionVerState();

					for (PkgConfigOption pkgConfigOption : configOptions) {

						templateRepository
								.deleteFromPkgConfigOptionBenefitOptionXrefByPkgConfigOptionId(pkgConfigOption
										.getPkgConfigOption1up());

					}
					templateRepository
							.deletePkgConfigOptionByPkgVersionWorkflowStateId(id);
					templateRepository.deletePkgVersionWorkflowStateById(id);

				} else {
					operationResult.setOperationResult(OperationResult.FAILURE);
					messages.add("Error deleteing Template ");
					return operationResult;
				}

			}

		} catch (PersistenceException e) {
			throw new DataAccessException(e.getMessage());
		}

		return operationResult;

	}

	/**
	 * @param id
	 *            templateID
	 * @param page
	 *            corresponding page no.
	 * @param maxResults
	 *            Maximum no. rows displayed in a page.
	 * @return It returns the list of all benifitPackageData objects,
	 *         PagesCount, TotalItems.
	 * @throws DataAccessException
	 *             <p>
	 *             This method retrieve the list of all packages list related to
	 *             particular template including id,name & description. This
	 *             method also return the total number of package versions for a
	 *             specific template
	 * 
	 */
	@Transactional(readOnly = true)
	public TemplateContainer<BenifitPackageData> getPackagesForTemplate(int id,
			int page, int maxResults) throws DataAccessException {

		Page<BenefitPackage> benifitPackageVal = null;

		try {
			benifitPackageVal = templateRepository.findPackagesForTemplate(id,
					new PageRequest(page, maxResults, new Sort(
							Sort.Direction.ASC, "name")));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<BenifitPackageData> benifitPackageDatas = new ArrayList<BenifitPackageData>();

		for (BenefitPackage benifitPackage : benifitPackageVal) {

			BenifitPackageData benifitPackageData = new BenifitPackageData();

			benifitPackageData.setBenefitPackage1up(benifitPackage
					.getBenefitPackage1up());

			if (benifitPackage.getPackageTemplate() != null) {

				benifitPackageData.setPackageTemplate1up(benifitPackage
						.getPackageTemplate().getPackageTemplate1up());
			}

			benifitPackageData.seteBSInstance1up(benifitPackage
					.geteBSInstance1up());
			benifitPackageData.setPackageId(benifitPackage.getId());
			benifitPackageData.setPackageName(benifitPackage.getName());
			benifitPackageData.setPackageDescription(benifitPackage
					.getDescription());

			try {
				benifitPackageData.setNoOfVersions(templateRepository
						.getPackagesVersion(benifitPackage
								.getBenefitPackage1up()));
			} catch (PersistenceException e) {
				throw new DataAccessException(e);
			}

			benifitPackageData.setCreateBy(benifitPackage.getCreateBy());
			benifitPackageData.setCreateDt(benifitPackage.getCreateDt());
			benifitPackageData.setCreateDt(benifitPackage.getCreateDt());
			benifitPackageData.setUpdateDt(benifitPackage.getUpdateDt());

			benifitPackageDatas.add(benifitPackageData);
		}

		TemplateContainer<BenifitPackageData> result = new TemplateContainer<BenifitPackageData>();
		result.setData(benifitPackageDatas);
		result.setPagesCount(benifitPackageVal.getTotalPages());
		result.setTotalItems(benifitPackageVal.getTotalElements());
		return result;

	}

	/**
	 * @param tempId
	 *            templateId we get this id from UI when we click on the
	 *            particular template.
	 * @return int It will return the number of unreleased package versions
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	public int getNoOfUnreleasedPackageVersions(int tempId)
			throws DataAccessException {

		List<Integer> pckIds = null;

		try {
			pckIds = templateRepository.getBenefitPackageId(tempId);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		// for (Integer integer : pckIds) {
		// // System.out.println("packageIds:" + integer);
		// }

		// List<Integer> wrkFlowState1Up = new ArrayList<Integer>();
		int count = 0;
		for (Integer pckId : pckIds) {
			List<Integer> wrkFlowState1Up = new ArrayList<Integer>();

			try {
				wrkFlowState1Up = templateRepository
						.getPkgVerWrkflowState(pckId);
			} catch (PersistenceException e) {
				throw new DataAccessException(e);
			}

			// for (Integer integer : wrkFlowState1Up) {
			// // System.out.println("workflow state::" + integer);
			// }

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
		}

		return count;

	}

	/**
	 * @param id
	 *            templateID
	 * @return It returns the no packages for particular templateId
	 * @throws DataAccessException
	 *             <p>
	 *             This method gives us the no of packages related to that
	 *             particular template.
	 */
	@Transactional(readOnly = true)
	public int getNoOfPackages(int id) throws DataAccessException {

		int result = 0;

		try {
			result = templateRepository.findNoOfPackageById(id);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		return result;
	}

	/**
	 * @param id
	 *            templateID
	 * @return It returns List of String
	 * @throws DataAccessException
	 *             <p>
	 *             When a user creates a new template, the types of packages
	 *             will be given by this method.
	 */
	@Transactional(readOnly = true)
	public List<String> getBenefitPackageType(int id)
			throws DataAccessException {

		List<String> benefitPackageType = null;

		try {
			benefitPackageType = templateRepository.findTemplateTypes(id);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		return benefitPackageType;
	}

	@Transactional(readOnly = true)
	public int getNoOfReleasedPackageVersions(int tempId)
			throws DataAccessException {
		List<Integer> pckIds = null;

		try {
			pckIds = templateRepository.getBenefitPackageId(tempId);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}
		// for (Integer integer : pckIds) {
		// // System.out.println("packageIds:" + integer);
		// }
		int count = 0;
		for (Integer pckId : pckIds) {
			List<Integer> wrkFlowState1Up = new ArrayList<Integer>();

			try {
				wrkFlowState1Up = templateRepository
						.getPkgVerWrkflowState(pckId);
			} catch (PersistenceException e) {
				throw new DataAccessException(e);
			}

			// for (Integer integer : wrkFlowState1Up) {
			// // System.out.println("workflow state::" + integer);
			// }
			for (Integer wrkFlowState : wrkFlowState1Up) {

				boolean flag = true;
				;
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

		}
		return count;
	}

	/**
	 * @param tempId
	 *            = It is the PackageTemplate1up primary key for corresponding
	 *            template
	 * @return = It returns boolean true or false
	 *         <p>
	 *         This method checks if the corresponding template is locked or
	 *         not. The template is locked if atleast one package is released.
	 */
	@Transactional(readOnly = true)
	public boolean isLockedTemplate(int tempId) throws DataAccessException {
		int count = 0;
		try {
			count = this.getNoOfReleasedPackageVersions(tempId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if (count > 0) {
			return true;
		} else
			return false;

	}

	/**
	 * @param templateWorkFlowid
	 *            Template Work Flow id to update in Package Template
	 * @param packageTemplateid
	 *            Package Template id
	 * @return operation result SUCCESS, WARNING
	 * @throws DataAccessException
	 * 
	 */
	@Transactional
	public ServiceOperationResult changeWorkFlowState(int templateWorkFlowid,
			int packageTemplateid) throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();

		try {

			templateRepository.updateWorkflowStateforPackage(
					templateWorkFlowid, packageTemplateid);

		} catch (PersistenceException e) {
			throw new DataAccessException(e.getMessage());
		}

		return operationResult;
	}

	/**
	 * @param id
	 *            templateId Template id to get template detail
	 * @return It will return template detail to data transfer object
	 *         PackageTemplateData
	 * @see This method will return template detail for edit/viewing.
	 * @throws DataAccessException
	 * 
	 */
	@Transactional(readOnly = true)
	public PackageTemplateData getTemplateDetail(int id)
			throws DataAccessException {
		PackageTemplateData data = new PackageTemplateData();

		PackageTemplate packageTemplate = null;
		try {
			packageTemplate = templateRepository.findPackageTemplateById(id);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}
		data.setCreateBy(packageTemplate.getCreateBy());
		if (packageTemplate.getBenefitPackageTypeAsString() != null) {
			data.setBenefitPackageTypeAsString1up(packageTemplate
					.getBenefitPackageTypeAsString()
					.getMasterListStringVal1up());
		}
		data.setCreateDt(packageTemplate.getCreateDt());
		data.setDescription(packageTemplate.getDescription());
		data.seteBSInstance1up(packageTemplate.geteBSInstance1up());
		data.setPackageTemplate1Up(packageTemplate.getPackageTemplate1up());
		data.setTemplateID(packageTemplate.getId());
		data.setTemplateName(packageTemplate.getName());
		data.setUpdateBy(packageTemplate.getUpdateBy());
		data.setUpdateDt(packageTemplate.getUpdateDt());
		if (packageTemplate.getTemplateWorkflowState() != null) {
			data.setTemplateWorkflowState1up(packageTemplate
					.getTemplateWorkflowState().getTemplateWorkflowState1up());

		}

		return data;

	}

	/**
	 * @param templateId
	 *            Primary Key of PackageTemplate Table
	 * 
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method will delete Template and delete its child entry
	 *             also
	 */
	@Transactional
	public ServiceOperationResult deleteTemplate(int templateId)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		Boolean isLocked = this.isLockedTemplate(templateId);

		try {
			if (isLocked == false) {
				templateRepository.deleteExcelViewInfoByTemplateId(templateId);

				templateRepository
						.deleteTemplateAllowIntValByTemplateId(templateId);

				templateRepository
						.deleteTemplateAllowStringValByTemplateId(templateId);

				templateRepository
						.deleteTemplateAllowMessageValByTemplateId(templateId);

				templateRepository
						.deleteTemplateAllowLimitValByTemplateId(templateId);

				templateRepository
						.deleteTemplateAllowServiceDefinitionByTemplateId(templateId);

				PackageTemplate template = templateRepository
						.findPackageTemplateSetById(templateId);

				Set<BenefitPackage> packageList = template
						.getTemplateBenefitPkg();

				for (BenefitPackage benefitPackage : packageList) {
					packageService.deletePackage(benefitPackage
							.getBenefitPackage1up());
				}

				Set<PkgVersionWorkflowState> workflowList = template
						.getPkgVerWorkflow();
				if (workflowList != null) {
					for (PkgVersionWorkflowState pkgVersionWorkflowState : workflowList) {
						Set<PkgVersion> pkgVersions = pkgVersionWorkflowState
								.getPkgVerWorkFlow();
						for (PkgVersion pkgVersion : pkgVersions) {

							packageVersionService
									.deletePackageVersion(pkgVersion
											.getPkgVersion1up());
						}

						Set<PkgConfigOption> configOptions = pkgVersionWorkflowState
								.getConfigOptionVerState();
						// .out.println(configOptions.size());
						for (PkgConfigOption pkgConfigOption : configOptions) {

							templateRepository
									.deleteFromPkgConfigOptionBenefitOptionXrefByPkgConfigOptionId(pkgConfigOption
											.getPkgConfigOption1up());

						}
						templateRepository
								.deletePkgConfigOptionByPkgVersionWorkflowStateId(pkgVersionWorkflowState
										.getPkgVersionWorkflowState1up());
						templateRepository
								.deletePkgVersionWorkflowStateById(pkgVersionWorkflowState
										.getPkgVersionWorkflowState1up());

					}
					Set<TemplateTree> templateTrees = template
							.getTemplateTree();
					for (TemplateTree templateTree : templateTrees) {
						TemplateTree templateTree2 = templateRepository
								.findTemplateTreeSetById(templateTree
										.getTemplateTree1up());
						if (templateTree2 != null) {

							Set<PkgVerInstanceTree> pkgVerInstanceTrees = templateTree2
									.getPkgtempTree();
							for (PkgVerInstanceTree pkgVerInstanceTree : pkgVerInstanceTrees) {
								packageVersionService
										.deleteCommonBenefit(pkgVerInstanceTree
												.getPkgVerInstanceTree1up());
							}

							templateRepository
									.deleteShapeValueDefaultByTemplateTreeId(templateTree2
											.getTemplateTree1up());

							templateRepository
									.deleteTreeConnectorByTemplateTreeId(templateTree2
											.getTemplateTree1up());

							Set<TreeShape> treeShapes = templateTree2
									.getShapeTree();

							for (TreeShape treeShape : treeShapes) {

								templateRepository
										.deleteShapeValueBenefitOptionForBig3COByTreeShapeId(treeShape
												.getTreeShape1up());

								templateRepository
										.deleteExcelViewInfoByTreeShapeId(treeShape
												.getTreeShape1up());

								templateRepository
										.deleteShapeValueDefaultByTreeShapeId(treeShape
												.getTreeShape1up());

								templateRepository
										.deleteTreeConnectorByTreeShapeFromId(treeShape
												.getTreeShape1up());

								templateRepository
										.deleteTreeConnectorByTreeShapeToId(treeShape
												.getTreeShape1up());

								templateRepository
										.deleteShapeValueBig3COByTreeShapeId(treeShape
												.getTreeShape1up());

								templateRepository
										.deleteShapeValueInstanceTreeByTreeShapeId(treeShape
												.getTreeShape1up());

								templateRepository
										.deleteLimitShapeBenefitOptionOverrideByTreeShapeId(treeShape
												.getTreeShape1up());

								templateRepository
										.deleteMessageShapeBenefitOptionOverrideByTreeShapeId(treeShape
												.getTreeShape1up());

								templateRepository
										.deleteMatrixShapeBig3COLimitXXrefByTreeShapeId(treeShape
												.getTreeShape1up());

								templateRepository
										.deleteMatrixShapeBig3COMessageXXrefByTreeShapeId(treeShape
												.getTreeShape1up());

								templateRepository
										.deleteMatrixShapeLimitXrefByTreeShapeId(treeShape
												.getTreeShape1up());

								templateRepository
										.deleteMatrixShapeMessageXrefByTreeShapeId(treeShape
												.getTreeShape1up());

								templateRepository
										.deleteMatrixShapeBig3COXrefByTreeShapeId(treeShape
												.getTreeShape1up());

								templateRepository
										.deleteTreeShapeById(treeShape
												.getTreeShape1up());
								// System.out.println("Hello");

							}
						}

						templateRepository.deleteTemplateTreeById(templateTree
								.getTemplateTree1up());

					}
				}
				templateRepository.deletePackageTemplateById(templateId);
			} else {
				operationResult.setOperationResult(OperationResult.FAILURE);
				messages.add("Error deleteing Template ");
				return operationResult;
			}

		}

		catch (PersistenceException e) {

			throw new DataAccessException(e.getMessage());

		}

		return operationResult;

	}

	@Transactional(readOnly = true)
	public TemplateWorkflowState getTemplateWorkFlowStateById(int id)
			throws DataAccessException {

		TemplateWorkflowState data = null;
		try {
			data = templateRepository.findWorkFlowStateGetById(id);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}
		return data;
	}

	/**
	 * @param data
	 *            object of dto PackageTemplateData is passed
	 * @return It returns ServiceOperationResult
	 * @throws DataAccessException
	 *             <p>
	 *             It saves the general info of a perticular template
	 */

	@Transactional
	public ServiceOperationResult saveUpdateTemplate(PackageTemplateData data)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		if (data == null) {
			throw new DataAccessException();
		}

		PackageTemplate temp = null;

		if (data.getPackageTemplate1Up() != null
				&& data.getPackageTemplate1Up() > 0) {

			try {
				temp = templateRepository.findPackageTemplateById(data
						.getPackageTemplate1Up());
			} catch (PersistenceException e) {
				throw new DataAccessException(e);
			}

			temp.setUpdateBy(data.getUpdateBy());
			temp.setUpdateDt(new Date());
		} else {
			temp = new PackageTemplate();
			temp.setCreateBy(data.getCreateBy());
			temp.setCreateDt(new Date());
		}
		if (data.getBenefitPackageTypeAsString1up() != null) {

			try {
				temp.setBenefitPackageTypeAsString(masterListRepository
						.findMasterListStringValById(data
								.getBenefitPackageTypeAsString1up()));
			} catch (PersistenceException e) {
				throw new DataAccessException(e);
			}
		}
		if (data.getTemplateWorkflowState1up() != null) {

			try {
				temp.setTemplateWorkflowState(templateRepository
						.findWorkFlowStateGetById(data
								.getTemplateWorkflowState1up()));
			} catch (PersistenceException e) {
				throw new DataAccessException(e);
			}

		}

		temp.setDescription(data.getDescription());
		temp.seteBSInstance1up(data.geteBSInstance1up());
		temp.setId(data.getTemplateID());
		temp.setName(data.getTemplateName());

		try {
			if (temp.getPackageTemplate1up() != null
					&& temp.getPackageTemplate1up() > 0) {

				try {
					templateRepository.updateData(temp);
				} catch (PersistenceException e) {
					throw new DataAccessException(e);
				}

			} else {
				try {
					templateRepository.saveData(temp);
				} catch (PersistenceException e) {
					throw new DataAccessException(e);
				}
			}

		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error saving value for MasterListIntVal Abbrev: "
					+ temp.getDescription());

		}

		return operationResult;
	}

	@Transactional
	/**
	 * @param id
	 *            packageTemplate1Up
	 * 
	 * @param page
	 * 
	 *            corresponding page no.
	 * @param maxResults
	 *            Maximum no. rows displayed in a page.
	 * @return {@link TemplateContainer} It returns the list of all
	 *         TemplateAllowsIntValData objects, PagesCount, TotalItems.
	 * 
	 * @throws DataAccessException
	 *             <p>
	 *             This method retrieve the list of all integer values for
	 *             particular template.
	 */
	public TemplateContainer<TemplateAllowsIntValData> getAllowIntValForTemplate(
			int id, int page, int maxResults) throws DataAccessException {

		Page<TemplateAllowsIntVal> allowIntvalList = null;
		try {
			allowIntvalList = templateRepository.findAllowedIntValForTemplate(
					id, new PageRequest(page, maxResults, new Sort(
							Sort.Direction.ASC, "defaultValFlag")));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<TemplateAllowsIntValData> allowIntvalListData = new ArrayList<TemplateAllowsIntValData>();

		for (TemplateAllowsIntVal allowIntVal : allowIntvalList) {

			TemplateAllowsIntValData allowIntvalData = new TemplateAllowsIntValData();

			allowIntvalData.setPackageTemplate1Up(allowIntVal
					.getTemplateAllowsIntValId().getPackageTemplate()
					.getPackageTemplate1up());

			allowIntvalData.setMasterListIntVal1up(allowIntVal
					.getTemplateAllowsIntValId().getMasterListIntVal()
					.getMasterListIntVal1up());
			allowIntvalData.setAbbrev(allowIntVal.getTemplateAllowsIntValId()
					.getMasterListIntVal().getAbbrev());
			allowIntvalData.setValue(allowIntVal.getTemplateAllowsIntValId()
					.getMasterListIntVal().getIntAsDecimalValue());

			allowIntvalData.setDefaultValFlag(allowIntVal.getDefaultValFlag());

			allowIntvalData.seteBSInstance1up(allowIntVal.geteBSInstance1up());
			allowIntvalData.setCreateBy(allowIntVal.getCreateBy());
			allowIntvalData.setCreateDt(allowIntVal.getCreateDt());
			allowIntvalData.setUpdateBy(allowIntVal.getUpdateBy());
			allowIntvalData.setUpdateDt(allowIntVal.getUpdateDt());

			allowIntvalListData.add(allowIntvalData);

		}

		TemplateContainer<TemplateAllowsIntValData> result = new TemplateContainer<TemplateAllowsIntValData>();
		result.setData(allowIntvalListData);
		result.setPagesCount(allowIntvalList.getTotalPages());
		result.setTotalItems(allowIntvalList.getTotalElements());
		return result;
	}

	/**
	 * @param id
	 *            packageTemplate1Up
	 * @param page
	 *            corresponding page no.
	 * @param maxResults
	 *            Maximum no. rows displayed in a page.
	 * @return {@link TemplateContainer} It returns the list of all
	 *         TemplateAllowsStringValData objects, PagesCount, TotalItems.
	 * @throws DataAccessException
	 *             <p>
	 *             This method retrieve the list of all String values for
	 *             particular template.
	 */
	@Transactional(readOnly = true)
	public TemplateContainer<TemplateAllowsStringValData> getAllowStringValForTemplate(
			int id, int page, int maxResults) throws DataAccessException {

		Page<TemplateAllowsStringVal> allowStringvalList = null;
		try {
			allowStringvalList = templateRepository
					.findAllowedStringValForTemplate(id, new PageRequest(page,
							maxResults, new Sort(Sort.Direction.ASC,
									"defaultValFlag")));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<TemplateAllowsStringValData> allowStringvalListData = new ArrayList<TemplateAllowsStringValData>();

		for (TemplateAllowsStringVal allowStringVal : allowStringvalList) {

			TemplateAllowsStringValData allowStringValData = new TemplateAllowsStringValData();
			allowStringValData.setPackageTemplate1Up(allowStringVal
					.getTemplateAllowsStringValId().getPackageTemplate()
					.getPackageTemplate1up());
			allowStringValData.setMasterListStringVal1up(allowStringVal
					.getTemplateAllowsStringValId().getMasterListStringVal()
					.getMasterListStringVal1up());
			allowStringValData.setDefaultValFlag(allowStringVal
					.getDefaultValFlag());

			allowStringValData.setAbbrev(allowStringVal
					.getTemplateAllowsStringValId().getMasterListStringVal()
					.getAbbrev());
			allowStringValData.setValue(allowStringVal
					.getTemplateAllowsStringValId().getMasterListStringVal()
					.getStringValue());

			allowStringValData.seteBSInstance1up(allowStringVal
					.geteBSInstance1up());
			allowStringValData.setCreateBy(allowStringVal.getCreateBy());
			allowStringValData.setCreateDt(allowStringVal.getCreateDt());
			allowStringValData.setUpdateBy(allowStringVal.getUpdateBy());
			allowStringValData.setUpdateDt(allowStringVal.getUpdateDt());

			allowStringvalListData.add(allowStringValData);
		}

		TemplateContainer<TemplateAllowsStringValData> result = new TemplateContainer<TemplateAllowsStringValData>();
		result.setData(allowStringvalListData);
		result.setPagesCount(allowStringvalList.getTotalPages());
		result.setTotalItems(allowStringvalList.getTotalElements());
		return result;

	}

	/**
	 * @param id
	 *            packageTemplate1Up
	 * @param page
	 *            corresponding page no.
	 * @param maxResults
	 *            Maximum no. rows displayed in a page.
	 * @return {@link TemplateContainer} It returns the list of all
	 *         TemplateAllowsLimitValData objects, PagesCount, TotalItems.
	 * @throws DataAccessException
	 *             <p>
	 *             This method retrieve the list of all allowed limit values for
	 *             particular template.
	 */
	@Transactional(readOnly = true)
	public TemplateContainer<TemplateAllowsLimitValData> getAllowsLimitForTemplate(
			int id, int page, int maxResults) throws DataAccessException {

		Page<TemplateAllowsLimitVal> allowLimitvalList;
		try {
			allowLimitvalList = templateRepository
					.findAllowedlimitValForTemplate(id, new PageRequest(page,
							maxResults, new Sort(Sort.Direction.ASC,
									"templateAllowsLimitValId")));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}
		List<TemplateAllowsLimitValData> allowLimitvalListData = new ArrayList<TemplateAllowsLimitValData>();

		for (TemplateAllowsLimitVal allowLimitVal : allowLimitvalList) {

			TemplateAllowsLimitValData allowLimitValData = new TemplateAllowsLimitValData();
			allowLimitValData.setPackageTemplate1Up(allowLimitVal
					.getTemplateAllowsLimitValId().getPackageTemplate()
					.getPackageTemplate1up());
			allowLimitValData.setMasterListLimitVal1up(allowLimitVal
					.getTemplateAllowsLimitValId().getMasterListLimitVal()
					.getMasterListLimitVal1up());

			allowLimitValData.setLimitName(allowLimitVal
					.getTemplateAllowsLimitValId().getMasterListLimitVal()
					.getLimitName());

			allowLimitValData.setLimitDescription(allowLimitVal
					.getTemplateAllowsLimitValId().getMasterListLimitVal()
					.getLimitDescription());

			allowLimitValData.setLimitCategory(allowLimitVal
					.getTemplateAllowsLimitValId().getMasterListLimitVal()
					.getLimitCategory());

			allowLimitValData.seteBSInstance1up(allowLimitVal
					.geteBSInstance1up());
			allowLimitValData.setCreateBy(allowLimitVal.getCreateBy());
			allowLimitValData.setCreateDt(allowLimitVal.getCreateDt());
			allowLimitValData.setUpdateBy(allowLimitVal.getUpdateBy());
			allowLimitValData.setUpdateDt(allowLimitVal.getUpdateDt());

			allowLimitvalListData.add(allowLimitValData);

		}
		TemplateContainer<TemplateAllowsLimitValData> result = new TemplateContainer<TemplateAllowsLimitValData>();
		result.setData(allowLimitvalListData);
		result.setPagesCount(allowLimitvalList.getTotalPages());
		result.setTotalItems(allowLimitvalList.getTotalElements());
		return result;

	}

	/**
	 * @param id
	 *            packageTemplate1Up
	 * @param page
	 *            corresponding page no.
	 * 
	 * @param maxResults
	 *            Maximum no. rows displayed in a page.
	 * @return {@link TemplateContainer} It returns the list of all
	 *         TemplateAllowsMessageValData objects, PagesCount, TotalItems.
	 * 
	 * @throws DataAccessException
	 *             <p>
	 *             This method retrieve the list of all allowed message values
	 *             for particular template.
	 */
	@Transactional(readOnly = true)
	public TemplateContainer<TemplateAllowsMessageValData> getAllowMessageValForTemplate(
			int id, int page, int maxResults) throws DataAccessException {

		Page<TemplateAllowsMessageVal> allowMessagevalList = null;
		try {
			allowMessagevalList = templateRepository
					.findAllowedMessageValForTemplate(id, new PageRequest(page,
							maxResults, new Sort(Sort.Direction.ASC,
									"templateAllowsMessageValId")));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<TemplateAllowsMessageValData> allowMessagevalListData = new ArrayList<TemplateAllowsMessageValData>();

		for (TemplateAllowsMessageVal allowMessageVal : allowMessagevalList) {

			TemplateAllowsMessageValData allowMessagevalData = new TemplateAllowsMessageValData();

			allowMessagevalData.setPackageTemplate1Up(allowMessageVal
					.getTemplateAllowsMessageValId().getPackageTemplate()
					.getPackageTemplate1up());
			allowMessagevalData.setMasterListMessageVal1up(allowMessageVal
					.getTemplateAllowsMessageValId().getMasterListMessageVal()
					.getMasterListMessageVal1up());
			allowMessagevalData.setMessageValue(allowMessageVal
					.getTemplateAllowsMessageValId().getMasterListMessageVal()
					.getMessageValue());

			allowMessagevalData.setMessageType(allowMessageVal
					.getTemplateAllowsMessageValId().getMasterListMessageVal()
					.getMessageType());

			allowMessagevalData.setMessageCategory(allowMessageVal
					.getTemplateAllowsMessageValId().getMasterListMessageVal()
					.getMessageCategory());

			allowMessagevalData.seteBSInstance1up(allowMessageVal
					.geteBSInstance1up());
			allowMessagevalData.setCreateBy(allowMessageVal.getCreateBy());
			allowMessagevalData.setCreateDt(allowMessageVal.getCreateDt());
			allowMessagevalData.setUpdateBy(allowMessageVal.getUpdateBy());
			allowMessagevalData.setUpdateDt(allowMessageVal.getUpdateDt());

			allowMessagevalListData.add(allowMessagevalData);

		}
		TemplateContainer<TemplateAllowsMessageValData> result = new TemplateContainer<TemplateAllowsMessageValData>();
		result.setData(allowMessagevalListData);
		result.setPagesCount(allowMessagevalList.getTotalPages());
		result.setTotalItems(allowMessagevalList.getTotalElements());
		return result;
	}

	/**
	 * @param id
	 *            packageTemplate1Up
	 * @param page
	 *            corresponding page no.
	 * @param maxResults
	 *            Maximum no. rows displayed in a page.
	 * @return {@link TemplateContainer} It returns the list of all
	 *         TemplateAllowsServiceDefinitionData objects, PagesCount,
	 *         TotalItems.
	 * @throws DataAccessException
	 *             <p>
	 *             This method retrieve the list of all allowed Service
	 *             Definition values for particular template.
	 */
	@Transactional(readOnly = true)
	public TemplateContainer<TemplateAllowsServiceDefinitionData> getAllowServiceDefinitionForTemplate(
			int id, int page, int maxResults) throws DataAccessException {

		Page<TemplateAllowsServiceDefinition> allowServiceDefinitionList = null;
		try {
			allowServiceDefinitionList = templateRepository
					.findAllowedServiceDefinitionForTemplate(id,
							new PageRequest(page, maxResults, new Sort(
									Sort.Direction.ASC,
									"templateAllowsServiceDefinitionId")));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<TemplateAllowsServiceDefinitionData> allowServiceDefinitionListData = new ArrayList<TemplateAllowsServiceDefinitionData>();

		for (TemplateAllowsServiceDefinition allowServiceDefinition : allowServiceDefinitionList) {

			TemplateAllowsServiceDefinitionData allowServiceDefinitionData = new TemplateAllowsServiceDefinitionData();

			allowServiceDefinitionData
					.setPackageTemplate1Up(allowServiceDefinition
							.getTemplateAllowsServiceDefinitionId()
							.getPackageTemplate().getPackageTemplate1up());
			allowServiceDefinitionData
					.setMasterListServiceDefinition1up(allowServiceDefinition
							.getTemplateAllowsServiceDefinitionId()
							.getMasterListServiceDefinition()
							.getMasterListServiceDefinition1up());

			allowServiceDefinitionData.seteBSInstance1up(allowServiceDefinition
					.geteBSInstance1up());
			allowServiceDefinitionData.setCreateBy(allowServiceDefinition
					.getCreateBy());
			allowServiceDefinitionData.setCreateDt(allowServiceDefinition
					.getCreateDt());
			allowServiceDefinitionData.setUpdateBy(allowServiceDefinition
					.getUpdateBy());
			allowServiceDefinitionData.setUpdateDt(allowServiceDefinition
					.getUpdateDt());

			allowServiceDefinitionListData.add(allowServiceDefinitionData);

		}
		TemplateContainer<TemplateAllowsServiceDefinitionData> result = new TemplateContainer<TemplateAllowsServiceDefinitionData>();
		result.setData(allowServiceDefinitionListData);
		result.setPagesCount(allowServiceDefinitionList.getTotalPages());
		result.setTotalItems(allowServiceDefinitionList.getTotalElements());
		return result;
	}

	@Transactional(readOnly = true)
	public List<Integer> getListOfInUseTemplateStringVal(int val1up, int tempId)
			throws DataAccessException {

		Set<MasterListStringVal> stringValList1 = null;
		Set<MasterListStringVal> stringValList2 = null;
		Set<MasterListStringVal> stringValList3 = null;
		Set<MasterListStringVal> stringValList4 = null;
		Set<MasterListStringVal> stringValList5 = null;
		Set<MasterListStringVal> stringValList6 = null;
		Set<MasterListStringVal> stringValList7 = null;
		Set<MasterListStringVal> stringValList8 = null;
		Set<MasterListServiceDefinition> stringValList9 = null;

		List<Integer> masterStringValList = new ArrayList<Integer>();

		try {

			stringValList1 = templateRepository
					.findSetOfShapeValueDefaultUseAndUnUseTemplateStringValue(
							val1up, tempId);

			stringValList2 = templateRepository
					.findSetOfShapeValueTreeInstanceUseAndUnUseTemplateStringValue(
							val1up, tempId);

			stringValList3 = templateRepository
					.findSetOfShapeValueBig3COUseAndUnUseTemplateStringValue(
							val1up, tempId);

			stringValList4 = templateRepository
					.findSetOfShapeValueBenefitOptionForBig3COUseAndUnUseTemplateStringValue(
							val1up, tempId);

			stringValList9 = templateRepository
					.findSetOfPkgMasterListForBig3COForSeviceDefnUseAndUnUseTemplateStringValue(tempId);

			List<Integer> listInt = new ArrayList<Integer>();
			for (MasterListServiceDefinition listServiceDefinition : stringValList9) {
				listInt.add(listServiceDefinition
						.getMasterListServiceDefinition1up());
			}
			stringValList5 = templateRepository
					.findSetOfPkgMasterListForBig3COForServiceUseAndUnUseTemplateStringValue(val1up);

			stringValList8 = templateRepository
					.findSetOfPkgMasterListForBig3COForNetworkUseAndUnUseTemplateStringValue(
							val1up, tempId);

			stringValList6 = templateRepository
					.findSetOfPkgConfigOptionUseAndUnUseTemplateStringValue(
							val1up, tempId);

			stringValList7 = templateRepository
					.findSetOfTreeConnectorUseAndUnUseTemplateStringValue(
							val1up, tempId);

			if (stringValList1 != null) {
				for (MasterListStringVal masterListStringVal : stringValList1) {

					if (masterListStringVal.getShapeString().size() > 0) {
						masterStringValList.add(masterListStringVal
								.getMasterListStringVal1up());
					}
				}
			}
			if (stringValList2 != null) {
				for (MasterListStringVal masterListStringVal : stringValList2) {
					if (masterListStringVal.getShapeIntstanceTree().size() > 0) {
						masterStringValList.add(masterListStringVal
								.getMasterListStringVal1up());
					}
				}
			}
			if (stringValList3 != null) {
				for (MasterListStringVal masterListStringVal : stringValList3) {
					if (masterListStringVal.getShapeStringBig3Co().size() > 0) {
						masterStringValList.add(masterListStringVal
								.getMasterListStringVal1up());
					}
				}
			}
			if (stringValList4 != null) {
				for (MasterListStringVal masterListStringVal : stringValList4) {
					if (masterListStringVal.getShapeValueStringBig3Co().size() > 0) {
						masterStringValList.add(masterListStringVal
								.getMasterListStringVal1up());
					}

				}
			}
			if (stringValList5 != null) {
				for (MasterListStringVal masterListStringVal : stringValList5) {
					Set<MasterListServiceDefinition> definitions1 = masterListStringVal
							.getMasterCat1();
					for (MasterListServiceDefinition masterListServiceDefinition : definitions1) {
						if (listInt.contains(masterListServiceDefinition
								.getMasterListServiceDefinition1up())) {
							masterStringValList.add(masterListStringVal
									.getMasterListStringVal1up());
						}
					}
					Set<MasterListServiceDefinition> definitions2 = masterListStringVal
							.getMasterCat2();
					for (MasterListServiceDefinition masterListServiceDefinition : definitions2) {
						if (listInt.contains(masterListServiceDefinition
								.getMasterListServiceDefinition1up())) {
							masterStringValList.add(masterListStringVal
									.getMasterListStringVal1up());
						}
					}

					Set<MasterListServiceDefinition> definitions3 = masterListStringVal
							.getMasterCat3();
					for (MasterListServiceDefinition masterListServiceDefinition : definitions3) {
						if (listInt.contains(masterListServiceDefinition
								.getMasterListServiceDefinition1up())) {
							masterStringValList.add(masterListStringVal
									.getMasterListStringVal1up());
						}
					}

					Set<MasterListServiceDefinition> definitions4 = masterListStringVal
							.getMasterPost();
					for (MasterListServiceDefinition masterListServiceDefinition : definitions4) {
						if (listInt.contains(masterListServiceDefinition
								.getMasterListServiceDefinition1up())) {
							masterStringValList.add(masterListStringVal
									.getMasterListStringVal1up());
						}
					}

				}
			}
			if (stringValList6 != null) {
				for (MasterListStringVal masterListStringVal : stringValList6) {
					if (masterListStringVal.getConfigOptionVer().size() > 0) {
						masterStringValList.add(masterListStringVal
								.getMasterListStringVal1up());
					}

				}
			}
			if (stringValList7 != null) {
				for (MasterListStringVal masterListStringVal : stringValList7) {
					if (masterListStringVal.getTreeSwitchOption().size() > 0) {
						masterStringValList.add(masterListStringVal
								.getMasterListStringVal1up());
					}

				}
			}

			if (stringValList8 != null) {
				for (MasterListStringVal masterListStringVal : stringValList8) {
					if (masterListStringVal.getBenPkgMasterList().size() > 0) {
						masterStringValList.add(masterListStringVal
								.getMasterListStringVal1up());
					}
				}
			}
		} catch (PersistenceException e) {
			throw new DataAccessException();
		}

		return masterStringValList;
	}

	@Transactional(readOnly = true)
	public TemplateContainer<TemplateAllowsStringValData> getStringValListForTemplate(
			int masterListId, int tempId, int page, int maxResults)
			throws DataAccessException {

		Page<MasterListStringVal> stringvalList = null;
		try {
			stringvalList = masterListRepository.findStringValMasterList(
					masterListId, new PageRequest(page, maxResults));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<Integer> stringInUseList = this.getListOfInUseTemplateStringVal(
				masterListId, tempId);
		Set<Integer> stringInUseSet = null;
		if (stringInUseList != null) {
			stringInUseSet = new HashSet<Integer>(stringInUseList);
		}

		List<Integer> stringList = templateRepository
				.findAllowedOrNotStringValForTemplate(tempId);
		List<Integer> stringLists = templateRepository
				.findDefaultStringValForTemplate(tempId);
		List<Integer> lockedDatas = templateRepository.findLockedValues(
				masterListId, tempId, IsLocked.STRINGTEMP);
		List<TemplateAllowsStringValData> allowStringvalListData = new ArrayList<TemplateAllowsStringValData>();
		for (MasterListStringVal stringVal : stringvalList) {
			TemplateAllowsStringValData allowsStringValData = new TemplateAllowsStringValData();
			allowsStringValData.setAbbrev(stringVal.getAbbrev());
			allowsStringValData.setCreateBy(stringVal.getCreateBy());
			allowsStringValData.setCreateDt(stringVal.getCreateDt());
			if (stringInUseSet != null) {
				if (stringInUseSet.contains(stringVal
						.getMasterListStringVal1up()) == true) {
					allowsStringValData.setInUse(true);
				} else {
					allowsStringValData.setInUse(false);
				}

			}

			allowsStringValData.setMasterListStringVal1up(stringVal
					.getMasterListStringVal1up());

			if (stringList != null) {
				allowsStringValData.setAllowed(stringList.contains(stringVal
						.getMasterListStringVal1up()));
			} else {
				allowsStringValData.setAllowed(false);
			}

			if (lockedDatas.contains(stringVal.getMasterListStringVal1up())) {
				allowsStringValData.setLocked(true);

			} else {
				allowsStringValData.setLocked(false);

			}

			if (stringLists != null) {
				allowsStringValData.setDefaultValFlag(stringLists
						.contains(stringVal.getMasterListStringVal1up()));
			} else {
				allowsStringValData.setDefaultValFlag(false);
			}
			allowStringvalListData.add(allowsStringValData);

		}
		TemplateContainer<TemplateAllowsStringValData> result = new TemplateContainer<TemplateAllowsStringValData>();
		result.setData(allowStringvalListData);
		result.setPagesCount(stringvalList.getTotalPages());
		result.setTotalItems(stringvalList.getTotalElements());

		return result;

	}

	/**
	 * @param tempId
	 * @return It returns integer
	 * @throws DataAccessException
	 *             <p>
	 *             This method gives the count of packages those are released on
	 *             particular tempId
	 */
	public int getNoOFReleasedPackage(int tempId) throws DataAccessException {

		List<Integer> pckIds = null;
		try {
			pckIds = templateRepository.getBenefitPackageId(tempId);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}
		int count = 0;

		for (Integer pckId : pckIds) {

			int pckVersions = 0;
			try {
				pckVersions = packageService
						.getNoOfReleasedPackageVersions(pckId);
			} catch (PersistenceException e) {
				throw new DataAccessException(e);
			}

			if (pckVersions > 0)
				count++;
			else
				continue;

		}
		return count;
	}

	/**
	 * @param tempId
	 * @return It returns integer
	 * @throws DataAccessException
	 *             <p>
	 *             This method gives the count of packages those are unreleased
	 *             on particular tempId
	 * 
	 */
	public int getNoOFUnReleasedPackage(int tempId) throws DataAccessException {

		List<Integer> pckIds = null;
		try {
			pckIds = templateRepository.getBenefitPackageId(tempId);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}
		int count = 0;

		for (Integer pckId : pckIds) {

			int pckVersions = 0;
			try {
				pckVersions = packageService
						.getNoOfUnReleasedPackageVersions(pckId);
			} catch (PersistenceException e) {
				throw new DataAccessException(e);
			}

			if (pckVersions > 0)
				count++;
			else
				continue;

		}
		return count;
	}

	@Transactional
	public ServiceOperationResult saveAndDeleteAllowMessageValForTemplate(
			List<TemplateAllowsMessageValData> allowMessageValList, int tempId)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> message = new ArrayList<String>();

		List<TemplateAllowsMessageVal> msgVal = null;

		try {
			msgVal = templateRepository
					.findAllowedMessageValForTemplate(tempId);

		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<Integer> currentList = new ArrayList<Integer>();
		List<Integer> existingList = new ArrayList<Integer>();

		if (allowMessageValList != null) {
			for (TemplateAllowsMessageValData data : allowMessageValList) {

				currentList.add(data.getMasterListMessageVal1up());
			}
		}
		if (msgVal != null) {
			for (TemplateAllowsMessageVal TemplatemsgVal : msgVal) {
				existingList
						.add(TemplatemsgVal.getTemplateAllowsMessageValId()
								.getMasterListMessageVal()
								.getMasterListMessageVal1up());
			}
		}

		List<Integer> common = new ArrayList<Integer>(existingList);

		common.retainAll(currentList);

		if (common != null) {
			existingList.removeAll(common);
			currentList.removeAll(common);
		}

		common.retainAll(currentList); // common from existing list and
										// current list
		existingList.removeAll(common); // delete extra entries from existing
										// list
		currentList.removeAll(common); // save the extra
										// entries from current list
										//
		// ///////delete logic

		if (msgVal != null) {
			for (Integer existingId : existingList) {
				try {
					templateRepository.deleteTemplateAllowsMessageValById(
							tempId, existingId);
				} catch (Exception e) {
					throw new DataAccessException(e);
				}

			}
		}

		// ///////////save logic

		TemplateAllowsMessageVal finalMessageVal = new TemplateAllowsMessageVal();

		if (allowMessageValList != null) {
			for (int i = 0; i < allowMessageValList.size(); i++) {

				if (currentList.remove(allowMessageValList.get(i)
						.getMasterListMessageVal1up())) {

					finalMessageVal.setCreateBy(allowMessageValList.get(i)
							.getCreateBy());
					finalMessageVal.setCreateDt(new Date());
					finalMessageVal.seteBSInstance1up(allowMessageValList
							.get(i).geteBSInstance1up());

					TemplateAllowsMessageValId id = new TemplateAllowsMessageValId();

					try {

						id.setMasterListMessageVal(masterListRepository
								.findMasterListMessageValListById(allowMessageValList
										.get(i).getMasterListMessageVal1up()));

						id.setPackageTemplate(templateRepository
								.findPackageTemplateById(tempId));
						finalMessageVal.setTemplateAllowsMessageValId(id);
						templateRepository.saveData(finalMessageVal);

					} catch (PersistenceException e) {
						operationResult
								.setOperationResult(OperationResult.WARNING);
						message.add("Error saving value for TemplateAllowsMessageVal");
					}

				}

				else {
					continue;
				}
			}
		}
		return operationResult;
	}

	@Transactional(readOnly = true)
	public List<Integer> getListOfInUseTemplateServiceDefination(int val1up,
			int tempId) throws DataAccessException {

		List<MasterListServiceDefinition> serviceValList = null;
		List<Integer> masterServiceValList = new ArrayList<Integer>();

		try {

			serviceValList = templateRepository
					.findSetOfPkgMasterListForBig3COUseAndUnUseTemplateServiceDefinition(
							val1up, tempId);

			if (serviceValList != null) {
				for (MasterListServiceDefinition definition : serviceValList) {

					if (definition.getBenPkgMasterList().size() > 0) {
						masterServiceValList.add(definition
								.getMasterListServiceDefinition1up());
					}
				}
			}

		} catch (PersistenceException e) {
			throw new DataAccessException();
		}

		return masterServiceValList;
	}

	/**
	 * @param MasterListId
	 *            This is the Masterlist datatype for Service Definition.
	 * @param tempId
	 *            package Template Id for for which Service Definition values to
	 *            be applied.
	 * @param page
	 *            no of pages.
	 * @param maxResults
	 *            max no of page results to show.
	 * @return This method retrieve the list of all Service Definition values
	 *         for particular template
	 */
	@Transactional(readOnly = true)
	public TemplateContainer<TemplateAllowsServiceDefinitionData> getServiceDefinitionValListForTemplate(
			int masterListId, int tempId, int page, int maxResults)
			throws DataAccessException {

		Page<MasterListServiceDefinition> serviceDefinitionList = null;
		try {
			serviceDefinitionList = masterListRepository
					.findMasterListServiceDefination(masterListId,
							new PageRequest(page, maxResults));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<Integer> serviceInUseList = this
				.getListOfInUseTemplateServiceDefination(masterListId, tempId);

		List<Integer> lockedDatas = templateRepository
				.findLockedValuesForServiceDefinition(masterListId, tempId);

		Set<Integer> serviceInUseSet = null;
		if (serviceInUseList != null) {
			serviceInUseSet = new HashSet<Integer>(serviceInUseList);
		}
		List<TemplateAllowsServiceDefinitionData> allowsServiceDefinitionDatas = new ArrayList<TemplateAllowsServiceDefinitionData>();

		List<Integer> serviceDefinitionIds = templateRepository
				.findAllowedOrNotServiceDefinitionListForTemplate(tempId);
		for (MasterListServiceDefinition masterListServiceDefinition : serviceDefinitionList) {
			TemplateAllowsServiceDefinitionData allowsServiceDefinitionData = new TemplateAllowsServiceDefinitionData();

			if (serviceInUseSet != null) {
				if (serviceInUseSet.contains(masterListServiceDefinition
						.getMasterListServiceDefinition1up()) == true) {
					allowsServiceDefinitionData.setInUse(true);
				} else {
					allowsServiceDefinitionData.setInUse(false);
				}

			}

			if (serviceDefinitionIds != null) {
				allowsServiceDefinitionData.setAllowed(serviceDefinitionIds
						.contains(masterListServiceDefinition
								.getMasterListServiceDefinition1up()));
			} else {
				allowsServiceDefinitionData.setAllowed(false);
			}
			if (lockedDatas.contains(masterListServiceDefinition
					.getMasterListServiceDefinition1up())) {
				allowsServiceDefinitionData.setLocked(true);

			} else {
				allowsServiceDefinitionData.setLocked(false);

			}

			if (masterListServiceDefinition.getMasterListStringValCat1() != null) {
				allowsServiceDefinitionData
						.setCategory1(masterListServiceDefinition
								.getMasterListStringValCat1().getStringValue());
			}
			if (masterListServiceDefinition.getMasterListStringValCat2() != null) {
				allowsServiceDefinitionData
						.setCategory2(masterListServiceDefinition
								.getMasterListStringValCat2().getStringValue());
			}
			if (masterListServiceDefinition.getMasterListStringValCat3() != null) {
				allowsServiceDefinitionData
						.setCategory3(masterListServiceDefinition
								.getMasterListStringValCat3().getStringValue());
			}
			if (masterListServiceDefinition.getMasterListStringValPostString() != null) {
				allowsServiceDefinitionData
						.setPlaceOfService(masterListServiceDefinition
								.getMasterListStringValPostString()
								.getStringValue());
			}

			allowsServiceDefinitionDatas.add(allowsServiceDefinitionData);
		}

		TemplateContainer<TemplateAllowsServiceDefinitionData> result = new TemplateContainer<TemplateAllowsServiceDefinitionData>();
		result.setData(allowsServiceDefinitionDatas);
		result.setPagesCount(serviceDefinitionList.getTotalPages());
		result.setTotalItems(serviceDefinitionList.getTotalElements());

		return result;

	}

	@Transactional(readOnly = true)
	public List<Integer> getListOfInUseTemplateMessageVal(int val1up, int tempId)
			throws DataAccessException {

		Set<MasterListMessageVal> messageValList = null;
		List<Integer> masterMessageValList = new ArrayList<Integer>();

		try {

			messageValList = templateRepository
					.findSetOfMatrixShapeMessageXrefUseAndUnUseTemplateMessageValue(
							val1up, tempId);

			if (messageValList != null) {
				for (MasterListMessageVal masterListMessageVal : messageValList) {

					if (masterListMessageVal.getMasterMessageXref().size() > 0) {
						masterMessageValList.add(masterListMessageVal
								.getMasterListMessageVal1up());
					}
				}
			}

		} catch (PersistenceException e) {
			throw new DataAccessException();
		}

		return masterMessageValList;
	}

	/**
	 * @param MasterListId
	 *            This is the Masterlist datatype for Service Definition.
	 * @param tempId
	 *            package Template Id for for which Message values to be
	 *            applied.
	 * @param page
	 *            no of pages.
	 * @param maxResults
	 *            max no of page results to show.
	 * @return This method retrieve the list of all Message values for
	 *         particular template
	 */
	@Transactional(readOnly = true)
	public TemplateContainer<TemplateAllowsMessageValData> getMessageValListForTemplate(
			int masterListId, int tempId, int page, int maxResults)
			throws DataAccessException {

		Page<MasterListMessageVal> messageValList = null;
		try {
			messageValList = masterListRepository.findMessageVal(masterListId,
					new PageRequest(page, maxResults));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}
		List<Integer> messageInUseList = this.getListOfInUseTemplateMessageVal(
				masterListId, tempId);

		List<Integer> lockedDatas = templateRepository.findLockedValues(
				masterListId, tempId, IsLocked.MESSAGETEMP);

		Set<Integer> messageInUseSet = null;
		if (messageInUseList != null) {
			messageInUseSet = new HashSet<Integer>(messageInUseList);
		}
		List<TemplateAllowsMessageValData> allowsMessageValDatas = new ArrayList<TemplateAllowsMessageValData>();

		List<Integer> msgValIds = templateRepository
				.findAllowedOrNotMessageValForTemplate(tempId);

		for (MasterListMessageVal messageVal : messageValList) {

			TemplateAllowsMessageValData allowsMessageValData = new TemplateAllowsMessageValData();

			if (messageInUseSet != null) {

				if (messageInUseSet.contains(messageVal
						.getMasterListMessageVal1up()) == true) {
					allowsMessageValData.setInUse(true);
				} else {
					allowsMessageValData.setInUse(false);
				}

			}

			if (msgValIds != null) {
				allowsMessageValData.setAllowed(msgValIds.contains(messageVal
						.getMasterListMessageVal1up()));
			} else {
				allowsMessageValData.setAllowed(false);
			}

			if (lockedDatas.contains(messageVal.getMasterListMessageVal1up())) {
				allowsMessageValData.setLocked(true);

			} else {
				allowsMessageValData.setLocked(false);

			}
			allowsMessageValData.setMessageValue(messageVal.getMessageValue());
			allowsMessageValData.setMessageCategory(messageVal
					.getMessageCategory());

			allowsMessageValDatas.add(allowsMessageValData);
		}

		TemplateContainer<TemplateAllowsMessageValData> container = new TemplateContainer<TemplateAllowsMessageValData>();
		container.setData(allowsMessageValDatas);
		container.setPagesCount(messageValList.getTotalPages());
		container.setTotalItems(messageValList.getTotalElements());

		return container;
	}

	/**
	 * @param allowsIntVals
	 *            This is a List of TemplateAllowsIntValData
	 * @param tempId
	 *            This Primary key Of PackageTemplate
	 * @return ServiceOperationResult
	 *         <p>
	 *         This method saves object of TemplateAllowsIntVal those are added
	 *         to the Data Object.
	 * @throws DataAccessException
	 */

	@SuppressWarnings({ "unused", "null" })
	@Transactional
	public ServiceOperationResult saveUpdateAndDeleteAllowIntValForTemplate(
			List<TemplateAllowsIntValData> allowsIntVals, int tempId, int mastId)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();

		List<String> message = new ArrayList<String>();

		List<TemplateAllowsIntVal> intVals = null;
		List<MasterListIntVal> list = null;

		TemplateAllowsIntVal allowsIntVal = new TemplateAllowsIntVal();
		try {
			list = masterListRepository
					.findMasterListIntValListByMastreListId(mastId);
			for (MasterListIntVal masterListIntVal : list) {

				allowsIntVal = templateRepository.findTemplateAllowsIntValById(
						tempId, masterListIntVal.getMasterListIntVal1up());
				intVals.add(allowsIntVal);
			}

		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}
		List<Integer> currentIdList = new ArrayList<Integer>();
		List<Integer> existingIdList = new ArrayList<Integer>();

		if (allowsIntVals != null) {

			for (TemplateAllowsIntValData templateAllowsIntVal : allowsIntVals) {

				currentIdList
						.add(templateAllowsIntVal.getMasterListIntVal1up());
			}
		}

		if (intVals != null) {

			for (TemplateAllowsIntVal temAllowsIntVal : intVals) {

				existingIdList.add(temAllowsIntVal.getTemplateAllowsIntValId()
						.getMasterListIntVal().getMasterListIntVal1up());
			}
		}

		for (TemplateAllowsIntValData data : allowsIntVals) {

			for (TemplateAllowsIntVal intVal : intVals) {

				if (data.getMasterListIntVal1up() == intVal
						.getTemplateAllowsIntValId().getMasterListIntVal()
						.getMasterListIntVal1up()
						&& data.getDefaultValFlag()) {

					TemplateAllowsIntVal val = templateRepository
							.findTemplateAllowsIntValById(tempId,
									data.getMasterListIntVal1up());

					val.setDefaultValFlag(true);

					templateRepository.updateDefaultFlagForIntVal(tempId);
					templateRepository.updateData(val);
				}
			}
		}

		List<Integer> commonIdList = new ArrayList<Integer>(existingIdList);

		commonIdList.retainAll(currentIdList);

		if (commonIdList != null) {
			existingIdList.removeAll(commonIdList);
			currentIdList.removeAll(commonIdList);
		}

		if (intVals != null) {

			for (Integer existingId : existingIdList) {

				try {
					templateRepository.deleteTemplateAllowsIntValById(tempId,
							existingId);
				} catch (PersistenceException e) {
					throw new DataAccessException(e);
				}
			}
		}

		TemplateAllowsIntVal finalIntVal = new TemplateAllowsIntVal();

		if (allowsIntVals != null) {

			for (TemplateAllowsIntValData intVal : allowsIntVals) {

				if (currentIdList.remove(intVal.getMasterListIntVal1up())) {

					finalIntVal.setCreateBy(intVal.getCreateBy());
					finalIntVal.setCreateDt(new Date());
					finalIntVal.setDefaultValFlag(intVal.getDefaultValFlag());
					finalIntVal.seteBSInstance1up(intVal.geteBSInstance1up());

					TemplateAllowsIntValId id = new TemplateAllowsIntValId();
					try {
						id.setMasterListIntVal(masterListRepository
								.findMasterListIntValById(intVal
										.getMasterListIntVal1up()));
						id.setPackageTemplate(templateRepository
								.findPackageTemplateById(tempId));
						finalIntVal.setTemplateAllowsIntValId(id);

						if (intVal.getDefaultValFlag()) {
							templateRepository
									.updateDefaultFlagForIntVal(tempId);
						}

						templateRepository.saveData(finalIntVal);
					} catch (PersistenceException e) {
						operationResult
								.setOperationResult(OperationResult.WARNING);
						message.add("Error saving value for TemplateAllowsServiceDefinition");
					}
				} else {
					continue;
				}
			}
		}

		return operationResult;

	}

	/**
	 * @param allowsLimitVals
	 *            List of allowLimitVals from data object
	 * @param tempId
	 *            template id
	 * @return ServiceOperationResult
	 *         <p>
	 *         This method saves object of TemplateAllowsLimitVal those are
	 *         added to the Data Object.
	 * @throws DataAccessException
	 */
	@Transactional
	public ServiceOperationResult saveAndDeleteAllowLimitValForTemplate(
			List<TemplateAllowsLimitValData> allowsLimitVals, int tempId)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> message = new ArrayList<String>();

		List<TemplateAllowsLimitVal> limitVals = null;

		try {
			limitVals = templateRepository
					.findAllowedLimitListValForTemplate(tempId);

		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}
		List<Integer> currentIdList = new ArrayList<Integer>();
		List<Integer> existingIdList = new ArrayList<Integer>();

		if (allowsLimitVals != null) {

			for (TemplateAllowsLimitValData templateAllowsLimitVal : allowsLimitVals) {

				currentIdList.add(templateAllowsLimitVal
						.getMasterListLimitVal1up());
			}

		}
		if (limitVals != null) {

			for (TemplateAllowsLimitVal temAllowsLimitVal : limitVals) {

				existingIdList.add(temAllowsLimitVal
						.getTemplateAllowsLimitValId().getMasterListLimitVal()
						.getMasterListLimitVal1up());
			}

		}
		List<Integer> commonIdList = new ArrayList<Integer>(existingIdList);
		commonIdList.retainAll(currentIdList);

		existingIdList.removeAll(commonIdList);
		currentIdList.removeAll(commonIdList);

		if (limitVals != null) {

			for (Integer existingId : existingIdList) {

				try {
					templateRepository.deleteTemplateAllowsLimitValById(tempId,
							existingId);
				} catch (PersistenceException e) {
					throw new DataAccessException(e);
				}
			}
		}
		TemplateAllowsLimitVal finalLimitVal = new TemplateAllowsLimitVal();

		if (allowsLimitVals != null) {

			for (int i = 0; i < allowsLimitVals.size(); i++) {

				if (currentIdList.remove(allowsLimitVals.get(i)
						.getMasterListLimitVal1up())) {

					finalLimitVal.setCreateBy(allowsLimitVals.get(i)
							.getCreateBy());
					finalLimitVal.setCreateDt(new Date());

					finalLimitVal.seteBSInstance1up(allowsLimitVals.get(i)
							.geteBSInstance1up());
					TemplateAllowsLimitValId id = new TemplateAllowsLimitValId();
					try {
						id.setMasterListLimitVal(masterListRepository
								.findMasterListLimitValById(allowsLimitVals
										.get(i).getMasterListLimitVal1up()));

						id.setPackageTemplate(templateRepository
								.findPackageTemplateById(tempId));
						finalLimitVal.setTemplateAllowsLimitValId(id);

						templateRepository.saveData(finalLimitVal);
					} catch (PersistenceException e) {
						operationResult
								.setOperationResult(OperationResult.WARNING);
						message.add("Error saving value for TemplateAllowsLimitVal");
					}

				} else {

					continue;
				}
			}
		}

		return operationResult;
	}

	/**
	 * @param allowServiceDefinitionList
	 *            List Of TemplateAllowsServiceDefinitionData Object For Save
	 * 
	 * @param tempId
	 *            Primary Key of PackageTemplate Table which is Foreign key in
	 *            TemplateAllowsServiceDefinition
	 * 
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This Method Save ServiceDefinition Data when allowed is true
	 *             and also Delete the data from table which are previously
	 *             allowed true but Currently False.
	 * 
	 */
	@Transactional
	public ServiceOperationResult saveAndDeleteAllowServiceDefinitionForTemplate(
			List<TemplateAllowsServiceDefinitionData> allowServiceDefinitionList,
			int tempId) throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		List<TemplateAllowsServiceDefinition> list = null;

		try {
			list = templateRepository
					.findAllowedServiceDefinitionListForTemplate(tempId);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<Integer> currentList = new ArrayList<Integer>();
		List<Integer> existingList = new ArrayList<Integer>();
		if (allowServiceDefinitionList != null) {
			for (TemplateAllowsServiceDefinitionData data : allowServiceDefinitionList) {

				currentList.add(data.getMasterListServiceDefinition1up());
			}
		}
		if (list != null) {
			for (TemplateAllowsServiceDefinition serviceDefinition : list) {
				if (serviceDefinition.getTemplateAllowsServiceDefinitionId() != null) {
					if (serviceDefinition
							.getTemplateAllowsServiceDefinitionId()
							.getMasterListServiceDefinition() != null) {
						existingList.add(serviceDefinition
								.getTemplateAllowsServiceDefinitionId()
								.getMasterListServiceDefinition()
								.getMasterListServiceDefinition1up());
					}
				}
			}
		}

		List<Integer> common = new ArrayList<Integer>(existingList);
		common.retainAll(currentList);
		if (common != null) {
			existingList.removeAll(common);
			currentList.removeAll(common);
		}

		if (existingList != null) {
			for (Integer existing : existingList) {

				try {
					templateRepository
							.deleteTemplateAllowsServiceDefinitionById(tempId,
									existing);
				} catch (PersistenceException e) {
					throw new DataAccessException(e);
				}

			}
		}

		List<TemplateAllowsServiceDefinition> toSaveServiceDefinitionList = new ArrayList<TemplateAllowsServiceDefinition>();

		if (currentList != null) {

			for (TemplateAllowsServiceDefinitionData templateAllowsServiceDefinition : allowServiceDefinitionList) {
				if (currentList.contains(templateAllowsServiceDefinition
						.getMasterListServiceDefinition1up())) {

					TemplateAllowsServiceDefinition toSaveServiceDefinition = new TemplateAllowsServiceDefinition();
					try {
						if (templateAllowsServiceDefinition
								.getPackageTemplate1Up() != null
								&& templateAllowsServiceDefinition
										.getMasterListServiceDefinition1up() != null) {
							TemplateAllowsServiceDefinitionId definitionId = new TemplateAllowsServiceDefinitionId();
							definitionId
									.setMasterListServiceDefinition(masterListRepository
											.findMasterListServiceDefinitionById(templateAllowsServiceDefinition
													.getMasterListServiceDefinition1up()));
							definitionId.setPackageTemplate(templateRepository
									.findPackageTemplateById(tempId));
							toSaveServiceDefinition
									.setTemplateAllowsServiceDefinitionId(definitionId);

						}
					} catch (PersistenceException e) {
						throw new DataAccessException(e);
					}

					toSaveServiceDefinition
							.seteBSInstance1up(templateAllowsServiceDefinition
									.geteBSInstance1up());
					toSaveServiceDefinition
							.setCreateBy(templateAllowsServiceDefinition
									.getCreateBy());
					toSaveServiceDefinition.setCreateDt(new Date());

					toSaveServiceDefinitionList.add(toSaveServiceDefinition);
				}
				for (TemplateAllowsServiceDefinition saveServiceDefinition : toSaveServiceDefinitionList) {

					try {
						templateRepository.saveData(saveServiceDefinition);
					} catch (PersistenceException e) {
						operationResult
								.setOperationResult(OperationResult.WARNING);
						messages.add("Error saving value for TemplateAllowsServiceDefinition");
					}

				}
			}

		}

		return operationResult;

	}

	@Transactional(readOnly = true)
	public List<Integer> getListOfInUseTemplateLimitVal(int val1up, int tempId)
			throws DataAccessException {

		Set<MasterListLimitVal> limitValList = null;
		List<Integer> masterLimitValList = new ArrayList<Integer>();

		try {

			limitValList = templateRepository
					.findSetOfMatrixShapeLimitXrefUseAndUnUseTemplateLimitValue(
							val1up, tempId);

			if (limitValList != null) {
				for (MasterListLimitVal masterListLimitVal : limitValList) {

					if (masterListLimitVal.getMasterShapeLimit().size() > 0) {
						masterLimitValList.add(masterListLimitVal
								.getMasterListLimitVal1up());
					}
				}
			}

		} catch (PersistenceException e) {
			throw new DataAccessException();
		}

		return masterLimitValList;
	}

	/**
	 * @param masterListId
	 *            Primary key MasterList1up of MasterList Table
	 * 
	 * @param tempId
	 *            Primary key PackageTemplate1up of PackageTemplate Table
	 * 
	 * @param page
	 *            corresponding page no.
	 * 
	 * @param maxResults
	 *            Maximum no. rows displayed in a page.
	 * 
	 * @return{@link TemplateContainer} It returns the list of all
	 *               TemplateAllowsLimitValData objects, PagesCount, TotalItems.
	 * 
	 * @throws DataAccessException
	 * 
	 *             <p>
	 *             This method return the list of MasterListLimitVal object and
	 *             their allowed, inuse and islocked functionality
	 */
	@Transactional(readOnly = true)
	public TemplateContainer<TemplateAllowsLimitValData> getLimitValListForTemplate(
			int masterListId, int tempId, int page, int maxResults)
			throws DataAccessException {

		Page<MasterListLimitVal> limitValList = null;
		try {
			limitValList = masterListRepository.findMasterListLimitVal(
					masterListId, new PageRequest(page, maxResults));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<Integer> listLimit = templateRepository
				.findAllowedOrNotLimitListValForTemplate(tempId);

		List<Integer> limitInUseList = this.getListOfInUseTemplateLimitVal(
				masterListId, tempId);

		List<Integer> lockedDatas = templateRepository.findLockedValues(
				masterListId, tempId, IsLocked.LIMITTEMP);

		Set<Integer> limitInUseSet = null;
		if (limitInUseList != null) {
			limitInUseSet = new HashSet<Integer>(limitInUseList);
		}
		List<TemplateAllowsLimitValData> allowLimitValListData = new ArrayList<TemplateAllowsLimitValData>();

		for (MasterListLimitVal limitVal : limitValList) {

			TemplateAllowsLimitValData allowLimitValData = new TemplateAllowsLimitValData();

			if (limitInUseSet != null) {
				if (limitInUseSet.contains(limitVal.getMasterListLimitVal1up()) == true) {
					allowLimitValData.setInUse(true);
				} else {
					allowLimitValData.setInUse(false);
				}

			}

			if (listLimit != null) {
				allowLimitValData.setAllowed(listLimit.contains(limitVal
						.getMasterListLimitVal1up()));
			} else {
				allowLimitValData.setAllowed(false);
			}

			if (lockedDatas.contains(limitVal.getMasterListLimitVal1up())) {
				allowLimitValData.setLocked(true);

			} else {
				allowLimitValData.setLocked(false);

			}

			allowLimitValData.setLimitName(limitVal.getLimitName());
			allowLimitValData.setLimitDescription(limitVal
					.getLimitDescription());
			allowLimitValData.setLimitCategory(limitVal.getLimitCategory());
			if (limitVal.getMasterListStringValCoverge() != null) {
				allowLimitValData.setCoverageLevel(limitVal
						.getMasterListStringValCoverge().getStringValue());
			}
			if (limitVal.getMasterListStringValTimePeriod() != null) {
				allowLimitValData.setTimePeriod(limitVal
						.getMasterListStringValTimePeriod().getStringValue());
			}
			if (limitVal.getMasterListStringValQuanQualifier() != null) {
				allowLimitValData
						.setQuantityQualifier(limitVal
								.getMasterListStringValQuanQualifier()
								.getStringValue());
			}

			allowLimitValData.setQuantityTo(limitVal.getQuantityTo());
			allowLimitValData.setQuantityFrom(limitVal.getQuantityFrom());
			allowLimitValData.setBenefitAmount(limitVal.getBenefitAmount());
			allowLimitValData.setPackageTemplate1Up(tempId);

			allowLimitValData.setMasterListLimitVal1up(limitVal
					.getMasterListLimitVal1up());

			allowLimitValData.seteBSInstance1up(limitVal.geteBSInstance1up());
			allowLimitValData.setCreateBy(limitVal.getCreateBy());
			allowLimitValData.setCreateDt(limitVal.getCreateDt());
			allowLimitValData.setUpdateBy(limitVal.getUpdateBy());
			allowLimitValData.setUpdateDt(limitVal.getUpdateDt());

			allowLimitValListData.add(allowLimitValData);

		}

		TemplateContainer<TemplateAllowsLimitValData> result = new TemplateContainer<TemplateAllowsLimitValData>();
		result.setData(allowLimitValListData);
		result.setPagesCount(limitValList.getTotalPages());
		result.setTotalItems(limitValList.getTotalElements());
		return result;
	}

	@Transactional(readOnly = true)
	public List<Integer> getListOfInUseTemplateIntVal(int val1up, int tempId)
			throws DataAccessException {

		Set<MasterListIntVal> intValList1 = null;
		Set<MasterListIntVal> intValList2 = null;
		Set<MasterListIntVal> intValList3 = null;
		Set<MasterListIntVal> intValList4 = null;
		List<Integer> masterintValList = new ArrayList<Integer>();

		try {

			intValList1 = templateRepository
					.findSetOfShapeValueDefaultUseAndUnUseTemplateIntValue(
							val1up, tempId);

			intValList2 = templateRepository
					.findSetOfShapeValueTreeInstanceUseAndUnUseTemplateIntValue(
							val1up, tempId);

			intValList3 = templateRepository
					.findSetOfShapeValueBig3COUseAndUnUseTemplateIntValue(
							val1up, tempId);

			intValList4 = templateRepository
					.findSetOfShapeValueBenefitOptionForBig3COUseAndUnUseTemplateIntValue(
							val1up, tempId);
			if (intValList1 != null) {
				for (MasterListIntVal masterListIntVal : intValList1) {

					if (masterListIntVal.getShapeInt().size() > 0) {
						masterintValList.add(masterListIntVal
								.getMasterListIntVal1up());
					}
				}
			}
			if (intValList2 != null) {
				for (MasterListIntVal masterListIntVal : intValList2) {
					if (masterListIntVal.getShapeIntstanceTree().size() > 0) {
						masterintValList.add(masterListIntVal
								.getMasterListIntVal1up());
					}
				}
			}
			if (intValList3 != null) {
				for (MasterListIntVal masterListIntVal : intValList3) {
					if (masterListIntVal.getShapeIntBig3Co().size() > 0) {
						masterintValList.add(masterListIntVal
								.getMasterListIntVal1up());
					}
				}
			}
			if (intValList4 != null) {
				for (MasterListIntVal masterListIntVal : intValList4) {
					if (masterListIntVal.getShapeValueIntBig3Co().size() > 0) {
						masterintValList.add(masterListIntVal
								.getMasterListIntVal1up());
					}

				}
			}
		} catch (PersistenceException e) {
			throw new DataAccessException();
		}

		return masterintValList;
	}

	/**
	 * @param masterListId
	 *            primary key for masterlistIntval
	 * 
	 * @param tempId
	 *            primary key for particular template
	 * @param page
	 *            corresponding page no.
	 * 
	 * @param maxResults
	 *            Maximum no. rows displayed in a page.
	 * 
	 * @return It returns the list of all TemplateAllowsIntValData objects,
	 *         PagesCount, TotalItems.
	 * @throws DataAccessException
	 *             <p>
	 *             This method return the list of TemplateAllowsIntValData
	 *             object and their allowed, inuse and islocked functionality
	 */
	@Transactional(readOnly = true)
	public TemplateContainer<TemplateAllowsIntValData> getIntValListForTemplate(
			int masterListId, int tempId, int page, int maxResults)
			throws DataAccessException {

		Page<MasterListIntVal> intvalList = null;
		try {
			intvalList = masterListRepository.findIntValMasterList(
					masterListId, new PageRequest(page, maxResults));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<Integer> intInUseList = this.getListOfInUseTemplateIntVal(
				masterListId, tempId);
		Set<Integer> intInUseSet = null;
		if (intInUseList != null) {
			intInUseSet = new HashSet<Integer>(intInUseList);
		}

		List<Integer> lockedDatas = templateRepository.findLockedValues(
				masterListId, tempId, IsLocked.INTTEMP);

		List<Integer> intList = templateRepository
				.findAllowedOrNotIntValForTemplate(tempId);
		List<Integer> intLists = templateRepository
				.findDefaultIntValForTemplate(tempId);
		List<TemplateAllowsIntValData> allowIntvalListData = new ArrayList<TemplateAllowsIntValData>();
		for (MasterListIntVal intVal : intvalList) {

			TemplateAllowsIntValData allowIntvalData = new TemplateAllowsIntValData();

			if (intInUseSet != null) {
				if (intInUseSet.contains(intVal.getMasterListIntVal1up()) == true) {
					allowIntvalData.setInUse(true);

				} else {
					allowIntvalData.setInUse(false);
				}

			}

			if (intList != null) {
				allowIntvalData.setAllowed(intList.contains(intVal
						.getMasterListIntVal1up()));
			} else {
				allowIntvalData.setAllowed(false);
			}

			allowIntvalData.setMasterListIntVal1up(intVal
					.getMasterListIntVal1up());

			if (intLists != null) {
				allowIntvalData.setDefaultValFlag(intLists.contains(intVal
						.getMasterListIntVal1up()));
			} else {
				allowIntvalData.setDefaultValFlag(false);
			}

			if (lockedDatas.contains(intVal.getMasterListIntVal1up())) {
				allowIntvalData.setLocked(true);

			} else {
				allowIntvalData.setLocked(false);

			}

			allowIntvalData.setAbbrev(intVal.getAbbrev());
			allowIntvalData.setValue(intVal.getIntAsDecimalValue());
			allowIntvalData.seteBSInstance1up(intVal.geteBSInstance1up());
			allowIntvalData.setCreateBy(intVal.getCreateBy());
			allowIntvalData.setCreateDt(intVal.getCreateDt());
			allowIntvalData.setUpdateBy(intVal.getUpdateBy());
			allowIntvalData.setUpdateDt(intVal.getUpdateDt());

			allowIntvalListData.add(allowIntvalData);

		}

		TemplateContainer<TemplateAllowsIntValData> result = new TemplateContainer<TemplateAllowsIntValData>();
		result.setData(allowIntvalListData);
		result.setPagesCount(intvalList.getTotalPages());
		result.setTotalItems(intvalList.getTotalElements());
		return result;
	}

	/**
	 * @param tempId
	 *            primary key for particular template
	 * @return It returns the list of packages.
	 * 
	 * @throws DataAccessException
	 *             <p>
	 *             This method return the list of number of packages in a
	 *             workflowState for particular template.
	 */
	@Transactional(readOnly = true)
	public List<Integer> getNumberOfPackagesForParticularTemplate(int tempId)
			throws DataAccessException {

		List<BenefitPackage> benefitPackageList = null;
		List<PkgVersion> packageVersionList = null;

		List<Integer> countList = new ArrayList<Integer>();
		try {
			benefitPackageList = templateRepository
					.findAllBenefitPackagesById(tempId);

		} catch (PersistenceException e) {
			throw new DataAccessException(e);

		}

		try {

			List<Integer> pKgversionWorkflowState = new ArrayList<Integer>();
			for (BenefitPackage pkg : benefitPackageList) {

				try {
					packageVersionList = templateRepository
							.findAllWorkFlowStateOfPkgVersionByPackageId(pkg
									.getBenefitPackage1up());
				} catch (PersistenceException e) {
					throw new DataAccessException(e);
				}

				for (PkgVersion pkgVersion : packageVersionList) {
					pKgversionWorkflowState.add(pkgVersion
							.getPkgVersionWorkflowState()
							.getPkgVersionWorkflowState1up());
				}

			}
			List<PkgVersionWorkflowState> workFlowStateList = new ArrayList<PkgVersionWorkflowState>();
			List<Integer> listWorkFlowState = new ArrayList<Integer>();
			try {
				workFlowStateList = templateRepository
						.findAllWorkFlowStateById(tempId);
				for (PkgVersionWorkflowState pkgVersionWorkflowState : workFlowStateList) {
					listWorkFlowState.add(pkgVersionWorkflowState
							.getPkgVersionWorkflowState1up());
				}

			} catch (PersistenceException e) {
				throw new DataAccessException(e);
			}

			for (Integer integer1 : listWorkFlowState) {
				int count = 0;
				for (Integer integer2 : pKgversionWorkflowState) {
					if (integer1 == integer2)
						count++;
				}

				countList.add(count);

			}

		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		return countList;

	}

	/**
	 * @param id
	 *            primary key for particular template
	 * 
	 * @param page
	 *            corresponding page no.
	 * 
	 * @param maxResults
	 *            Maximum no. rows displayed in a page.
	 * 
	 * @return It returns the list of all PkgVersionWorkflowStateData objects,
	 *         PagesCount, TotalItems.
	 * @throws DataAccessException
	 *             <p>
	 *             This method return the list of PkgVersionWorkflowState object
	 *             and their number Of packages in workFlowState for particular
	 *             template functionality.
	 */

	@Transactional(readOnly = true)
	public TemplateContainer<PkgVersionWorkflowStateData> getWorkFlowStateForEachTemplate(
			int id, int page, int maxResults) throws DataAccessException {

		Page<PkgVersionWorkflowState> allowWorkFlowStatevalList = null;
		try {
			allowWorkFlowStatevalList = templateRepository
					.findAllowedWorkFlowStatesForTemplate(id, new PageRequest(
							page, maxResults));
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		List<PkgVersionWorkflowStateData> allowWorkFlowStateListData = new ArrayList<PkgVersionWorkflowStateData>();
		List<Integer> workFlowList = this
				.getNumberOfPackagesForParticularTemplate(id);
		for (int i = 0; i < allowWorkFlowStatevalList.getContent().size(); i++) {
			PkgVersionWorkflowStateData allowWorkFlowStateData = new PkgVersionWorkflowStateData();

			if (allowWorkFlowStatevalList.getContent().get(i)
					.getPackageTemplate() != null) {
				allowWorkFlowStateData
						.setPackageTemplate1Up(allowWorkFlowStatevalList
								.getContent().get(i).getPackageTemplate()
								.getPackageTemplate1up());
			}
			allowWorkFlowStateData
					.setPkgVersionWorkflowState1up(allowWorkFlowStatevalList
							.getContent().get(i)
							.getPkgVersionWorkflowState1up());
			allowWorkFlowStateData.setName(allowWorkFlowStatevalList
					.getContent().get(i).getName());
			allowWorkFlowStateData.setDescription(allowWorkFlowStatevalList
					.getContent().get(i).getDescription());
			allowWorkFlowStateData.seteBSInstance1up(allowWorkFlowStatevalList
					.getContent().get(i).geteBSInstance1up());
			allowWorkFlowStateData.setReleasedFlag(allowWorkFlowStatevalList
					.getContent().get(i).getReleasedFlag());
			allowWorkFlowStateData.setDefaultValFlag(allowWorkFlowStatevalList
					.getContent().get(i).getDefaultValFlag());
			allowWorkFlowStateData.setDisplayOrder(allowWorkFlowStatevalList
					.getContent().get(i).getDisplayOrder());
			allowWorkFlowStateData.setCreateBy(allowWorkFlowStatevalList
					.getContent().get(i).getCreateBy());
			allowWorkFlowStateData.setCreateDt(allowWorkFlowStatevalList
					.getContent().get(i).getCreateDt());
			allowWorkFlowStateData.setUpdateBy(allowWorkFlowStatevalList
					.getContent().get(i).getUpdateBy());
			allowWorkFlowStateData.setUpdateDt(allowWorkFlowStatevalList
					.getContent().get(i).getUpdateDt());
			allowWorkFlowStateData.setNoOfPackages(workFlowList.get(i));

			allowWorkFlowStateListData.add(allowWorkFlowStateData);
		}

		TemplateContainer<PkgVersionWorkflowStateData> result = new TemplateContainer<PkgVersionWorkflowStateData>();
		result.setData(allowWorkFlowStateListData);
		result.setPagesCount(allowWorkFlowStatevalList.getTotalPages());
		result.setTotalItems(allowWorkFlowStatevalList.getTotalElements());
		return result;

	}

	@Transactional
	public ServiceOperationResult saveUpdateAndDeleteAllowStringValForTemplate(
			List<TemplateAllowsStringValData> allowsStringValDatas,
			int templateId, int mastId) throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();

		List<String> messages = new ArrayList<String>();
		List<TemplateAllowsStringVal> stringVals = new ArrayList<TemplateAllowsStringVal>();
		List<MasterListStringVal> list = new ArrayList<MasterListStringVal>();
		TemplateAllowsStringVal allowsStringVal = new TemplateAllowsStringVal();

		try {

			list = masterListRepository
					.findMasterListStringValListByMastreListId(mastId);
			if (list != null) {
				for (MasterListStringVal masterListStringVal : list) {
					allowsStringVal = templateRepository
							.findTemplateAllowsStringValById(templateId,
									masterListStringVal
											.getMasterListStringVal1up());
					stringVals.add(allowsStringVal);

				}
			}

			List<Integer> currentIdList = new ArrayList<Integer>();
			List<Integer> existingIdList = new ArrayList<Integer>();

			if (allowsStringValDatas != null) {

				for (TemplateAllowsStringValData data : allowsStringValDatas) {

					currentIdList.add(data.getMasterListStringVal1up());
				}
			}

			if (stringVals != null) {

				for (TemplateAllowsStringVal val : stringVals) {

					existingIdList.add(val.getTemplateAllowsStringValId()
							.getMasterListStringVal()
							.getMasterListStringVal1up());
				}
			}

			for (TemplateAllowsStringValData templateAllowsStringVal : allowsStringValDatas) {

				for (TemplateAllowsStringVal temAllowsStringVal : stringVals) {
					if (templateAllowsStringVal.getMasterListStringVal1up() == temAllowsStringVal
							.getTemplateAllowsStringValId()
							.getMasterListStringVal()
							.getMasterListStringVal1up()
							&& templateAllowsStringVal.getDefaultValFlag()) {

						TemplateAllowsStringVal stringVal = templateRepository
								.findTemplateAllowsStringValById(templateId,
										templateAllowsStringVal
												.getMasterListStringVal1up());

						stringVal.setDefaultValFlag(true);

						templateRepository
								.updateDefaultFlagForStringVal(templateId);
						templateRepository.updateData(stringVal);

					}

				}
			}
			List<Integer> commonIdList = new ArrayList<Integer>(existingIdList);
			commonIdList.retainAll(currentIdList);
			if (commonIdList != null) {
				existingIdList.removeAll(commonIdList);
				currentIdList.removeAll(commonIdList);
			}

			if (existingIdList != null) {
				for (Integer existingId : existingIdList) {
					templateRepository.deleteTemplateAllowsStringValById(
							templateId, existingId);
				}
			}

			TemplateAllowsStringVal finalStringVal = new TemplateAllowsStringVal();

			if (currentIdList != null) {
				for (TemplateAllowsStringValData allowsStringValData : allowsStringValDatas) {
					if (currentIdList.contains(allowsStringValData
							.getMasterListStringVal1up())) {
						finalStringVal.setCreateBy(allowsStringValData
								.getCreateBy());
						finalStringVal.setCreateDt(new Date());
						finalStringVal.setDefaultValFlag(allowsStringValData
								.getDefaultValFlag());
						finalStringVal.seteBSInstance1up(allowsStringValData
								.geteBSInstance1up());

						TemplateAllowsStringValId id = new TemplateAllowsStringValId();

						id.setMasterListStringVal(masterListRepository
								.findMasterListStringValById(allowsStringValData
										.getMasterListStringVal1up()));

						id.setPackageTemplate(templateRepository
								.findPackageTemplateById(templateId));

						finalStringVal.setTemplateAllowsStringValId(id);
						if (allowsStringValData.getDefaultValFlag()) {
							templateRepository
									.updateDefaultFlagForStringVal(templateId);

						}
						templateRepository.saveData(finalStringVal);
					}
				}
			}
		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error saving value for TemplateAllowsStringVal");
		}

		return operationResult;
	}

	/**
	 * @param workFlowStateData
	 *            List of PkgVersionWorkflowState Objects
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * 
	 * @throws DataAccessException
	 *             <p>
	 *             This method insert PkgVersionWorkflowState object into
	 *             PkgVersionWorkflowState table. It returns SUCCESS if all
	 *             objects inserted successfully. It returns WARNING if at least
	 *             one unsuccessful. Otherwise it throws exception
	 */
	@Transactional
	public ServiceOperationResult saveAndUpdatePkgVerWorkflowStateForTemplate(
			List<PkgVersionWorkflowStateData> workFlowStateData)
			throws DataAccessException {
		ServiceOperationResult operationResult = new ServiceOperationResult();
		List<String> messages = new ArrayList<String>();

		if (workFlowStateData == null) {
			throw new DataAccessException();
		}

		List<PkgVersionWorkflowState> pkgVersionWorkflowStateList = new ArrayList<PkgVersionWorkflowState>();
		try {

			for (PkgVersionWorkflowStateData pkgVersionWorkflowStateData : workFlowStateData) {

				PkgVersionWorkflowState pkgVersionWorkflowState = new PkgVersionWorkflowState();

				pkgVersionWorkflowState
						.seteBSInstance1up(pkgVersionWorkflowStateData
								.geteBSInstance1up());

				PackageTemplate template = templateRepository
						.findPackageTemplateById(pkgVersionWorkflowStateData
								.getPackageTemplate1Up());
				pkgVersionWorkflowState.setPackageTemplate(template);

				pkgVersionWorkflowState.setName(pkgVersionWorkflowStateData
						.getName());
				pkgVersionWorkflowState
						.setReleasedFlag(pkgVersionWorkflowStateData
								.getReleasedFlag());
				pkgVersionWorkflowState
						.setDefaultValFlag(pkgVersionWorkflowStateData
								.getDefaultValFlag());
				pkgVersionWorkflowState
						.setDescription(pkgVersionWorkflowStateData
								.getDescription());
				pkgVersionWorkflowState
						.setDisplayOrder(pkgVersionWorkflowStateData
								.getDisplayOrder());

				pkgVersionWorkflowState.setCreateBy(pkgVersionWorkflowStateData
						.getCreateBy());

				pkgVersionWorkflowState.setCreateDt(new Date());
				pkgVersionWorkflowState.setUpdateDt(new Date());
				pkgVersionWorkflowState.setUpdateBy(pkgVersionWorkflowStateData
						.getUpdateBy());

				pkgVersionWorkflowStateList.add(pkgVersionWorkflowState);

			}

			for (PkgVersionWorkflowState workFlowStateVal : pkgVersionWorkflowStateList) {

				if (workFlowStateVal.getPackageTemplate()
						.getPackageTemplate1up() != null) {
					templateRepository.updateData(workFlowStateVal);
				} else {
					templateRepository.saveData(workFlowStateVal);
				}
			}
		} catch (PersistenceException e) {

			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error saving value forPkgVersionWorkflowState");
		}

		return operationResult;
	}

	/**
	 * @param data
	 *            PackageTemplateData
	 * @param masterList
	 *            Boolean value masterList
	 * @param packageWorkFlowState
	 *            packageWorkFlowState
	 * @param TemplateTree
	 *            TemplateTree
	 * @return ServiceOperationResult Object which has value from { SUCCESS,
	 *         FAILURE, WARNING }
	 * @throws DataAccessException
	 *             <p>
	 *             This Method Create And Save Template from Existing Template.
	 */

	@Transactional
	public ServiceOperationResult saveTemplateFromExistingTemplate(
			PackageTemplateData data, Boolean masterList,
			Boolean packageWorkFlowState, Boolean TemplateTree)
			throws DataAccessException {

		ServiceOperationResult operationResult = new ServiceOperationResult();

		PackageTemplate temp = new PackageTemplate();

		List<String> messages = new ArrayList<String>();

		if (data == null) {
			throw new DataAccessException();
		}

		try {
			PackageTemplate existingTemplate = templateRepository
					.findPackageTemplateById(data.getPackageTemplate1Up());

			temp.setBenefitPackageTypeAsString(masterListRepository
					.findMasterListStringValById(data
							.getBenefitPackageTypeAsString1up()));

			temp.setTemplateWorkflowState(templateRepository
					.findWorkFlowStateGetById(data
							.getTemplateWorkflowState1up()));

			temp.setDescription(data.getDescription());

			temp.setId(data.getTemplateID());
			temp.setName(data.getTemplateName());

			temp.seteBSInstance1up(data.geteBSInstance1up());

			temp.setCreateBy(data.getCreateBy());

			temp.setCreateDt(new Date());

			templateRepository.saveData(temp);

			if (masterList == true) {

				List<TemplateAllowsIntVal> inValList = templateRepository
						.findAllowedIntValForTemplate(existingTemplate
								.getPackageTemplate1up());
				for (TemplateAllowsIntVal templateAllowsIntVal : inValList) {

					templateRepository.detachedEntity(templateAllowsIntVal);
					// templateAllowsIntVal.setCreateBy();
					templateAllowsIntVal.setUpdateDt(null);
					templateAllowsIntVal.setCreateDt(new Date());
					templateAllowsIntVal.getTemplateAllowsIntValId()
							.setPackageTemplate(temp);
					templateRepository.saveData(templateAllowsIntVal);

				}

				List<TemplateAllowsStringVal> stringValList = templateRepository
						.findAllowedStringValForTemplate(existingTemplate
								.getPackageTemplate1up());
				for (TemplateAllowsStringVal templateAllowsStringVal : stringValList) {

					templateRepository.detachedEntity(templateAllowsStringVal);
					templateAllowsStringVal.setUpdateDt(null);
					templateAllowsStringVal.setCreateDt(new Date());
					templateAllowsStringVal.getTemplateAllowsStringValId()
							.setPackageTemplate(temp);
					templateRepository.saveData(templateAllowsStringVal);
				}
				List<TemplateAllowsMessageVal> messageValList = templateRepository
						.findAllowedMessageValForTemplate(existingTemplate
								.getPackageTemplate1up());
				for (TemplateAllowsMessageVal messageVal : messageValList) {
					templateRepository.detachedEntity(messageVal);
					messageVal.setUpdateDt(null);
					messageVal.setCreateDt(new Date());
					messageVal.getTemplateAllowsMessageValId()
							.setPackageTemplate(temp);
					templateRepository.saveData(messageVal);
				}

				List<TemplateAllowsLimitVal> limitValList = templateRepository
						.findAllowedLimitListValForTemplate(existingTemplate
								.getPackageTemplate1up());
				for (TemplateAllowsLimitVal templateAllowsLimitVal : limitValList) {
					templateRepository.detachedEntity(templateAllowsLimitVal);
					templateAllowsLimitVal.setUpdateDt(null);
					templateAllowsLimitVal.setCreateDt(new Date());
					templateAllowsLimitVal.getTemplateAllowsLimitValId()
							.setPackageTemplate(temp);
					templateRepository.saveData(templateAllowsLimitVal);
				}
				List<TemplateAllowsServiceDefinition> serviceDefinitionsList = templateRepository
						.findAllowedServiceDefinitionListForTemplate(existingTemplate
								.getPackageTemplate1up());
				for (TemplateAllowsServiceDefinition templateAllowsServiceDefinition : serviceDefinitionsList) {
					templateRepository
							.detachedEntity(templateAllowsServiceDefinition);
					templateAllowsServiceDefinition.setUpdateDt(null);
					templateAllowsServiceDefinition.setCreateDt(new Date());
					templateAllowsServiceDefinition
							.getTemplateAllowsServiceDefinitionId()
							.setPackageTemplate(temp);
					templateRepository
							.saveData(templateAllowsServiceDefinition);
				}
			}

			if (TemplateTree == true) {

				List<TemplateTree> templateTree = templateRepository
						.findAllowsTemplateTreeById(existingTemplate
								.getPackageTemplate1up());

				List<TreeConnector> treeConnectors = templateRepository
						.findTreeConnectorsById(existingTemplate
								.getPackageTemplate1up());

				List<ShapeValueDefault> shapeValueDefaults = templateRepository
						.findTreeShapeValueDefaultById(existingTemplate
								.getPackageTemplate1up());

				List<TreeShape> treeShape = templateRepository
						.findTreeShapesById(existingTemplate
								.getPackageTemplate1up());

				for (TemplateTree templateTreeData : templateTree) {

					templateRepository.detachedEntity(templateTreeData);
					templateTreeData.setPackageTemplate(temp);
					templateTreeData.setUpdateDt(null);
					templateTreeData.setCreateDt(new Date());
					templateTreeData.setTemplateTree1up(null);
					templateRepository.saveData(templateTreeData);

					for (TreeConnector treeConnectorData : treeConnectors) {

						treeConnectorData.setTemplateTree(templateTreeData);

						templateRepository.detachedEntity(treeConnectorData);
						treeConnectorData.setUpdateDt(null);
						treeConnectorData.setCreateDt(new Date());
						treeConnectorData.setTreeConnector1up(null);
						templateRepository.saveData(treeConnectorData);

					}

					for (TreeShape treeShapeData : treeShape) {
						int i = 0;
						List<MatrixShapeLimitXref> matrixShapeLimitXref = templateRepository
								.findMatrixShapeLimitXrefById(treeShapeData
										.getTreeShape1up());
						List<MatrixShapeMessageXref> matrixShapeMessageXref = templateRepository
								.findMatrixShapeMessageXrefById(treeShapeData
										.getTreeShape1up());

						treeShapeData.setTemplateTree(templateTreeData);

						templateRepository.detachedEntity(treeShapeData);
						treeShapeData.setUpdateDt(null);
						treeShapeData.setCreateDt(new Date());
						treeShapeData.setTreeShape1up(null);

						templateRepository.saveData(treeShapeData);

						ShapeValueDefault shapeValueDefault = shapeValueDefaults
								.get(i);
						shapeValueDefault.setTreeShape(treeShapeData);
						shapeValueDefault.setTemplateTree(templateTreeData);
						shapeValueDefault.setUpdateDt(null);
						shapeValueDefault.setCreateDt(new Date());
						shapeValueDefault.setShapeValueDefault1up(null);
						templateRepository.detachedEntity(shapeValueDefault);

						templateRepository.saveData(shapeValueDefault);

						for (MatrixShapeLimitXref matrixShapeLimitXrefData : matrixShapeLimitXref) {

							matrixShapeLimitXrefData.setUpdateDt(null);
							matrixShapeLimitXrefData.setCreateDt(new Date());
							matrixShapeLimitXrefData
									.getMatrixShapeLimitXrefId().setTreeShape(
											treeShapeData);
							templateRepository
									.detachedEntity(matrixShapeLimitXrefData);
							templateRepository
									.saveData(matrixShapeLimitXrefData);
						}

						for (MatrixShapeMessageXref matrixShapeMessageXrefData : matrixShapeMessageXref) {

							matrixShapeMessageXrefData.setUpdateDt(null);
							matrixShapeMessageXrefData.setCreateDt(new Date());
							matrixShapeMessageXrefData
									.getMatrixShapeMessageXrefId()
									.setTreeShape(treeShapeData);
							templateRepository
									.detachedEntity(matrixShapeMessageXrefData);
							templateRepository
									.saveData(matrixShapeMessageXrefData);
						}

						i++;
					}

				}

			}

			if (packageWorkFlowState == true) {

				List<PkgVersionWorkflowState> workFlowStateList = templateRepository
						.findAllWorkFlowStateById(existingTemplate
								.getPackageTemplate1up());

				for (PkgVersionWorkflowState pkgVersionWorkflowState : workFlowStateList) {

					templateRepository.detachedEntity(pkgVersionWorkflowState);
					pkgVersionWorkflowState.setUpdateDt(null);
					pkgVersionWorkflowState.setCreateDt(new Date());
					pkgVersionWorkflowState.setPackageTemplate(temp);

					pkgVersionWorkflowState.setPkgVersionWorkflowState1up(null);

					templateRepository.saveData(pkgVersionWorkflowState);
				}

			}

		} catch (PersistenceException e) {
			operationResult.setOperationResult(OperationResult.WARNING);
			messages.add("Error saving values");

		}

		return operationResult;

	}

	@Transactional(readOnly = true)
	public int getCommonUsedBenifit(int id) throws DataAccessException {

		int instanceTreeVal = 0;
		try {
			instanceTreeVal = templateRepository.findCommonUsedBenifit(id);
		} catch (PersistenceException e) {
			throw new DataAccessException(e);
		}

		return instanceTreeVal;
	}

	@Transactional(readOnly = true)
	public TemplateContainer<TemplateTreeData> getAllTemplateTreeForTemplate(
			int id, int page, int maxResults) throws DataAccessException {

		Page<TemplateTree> allowTemplateTreeList = templateRepository
				.findAllowedTemplateTreeForTemplate(id, new PageRequest(page,
						maxResults));

		List<TemplateTreeData> allowTemplateTreeDataList = new ArrayList<TemplateTreeData>();

		for (TemplateTree allowTemplateTree : allowTemplateTreeList) {

			TemplateTreeData allowTemplateTreeData = new TemplateTreeData();

			if (this.getCommonUsedBenifit(allowTemplateTree
					.getTemplateTree1up()) > 0) {
				allowTemplateTreeData.setInUse(true);
			} else {
				allowTemplateTreeData.setInUse(false);
			}
			allowTemplateTreeData.setLocked(true);

			allowTemplateTreeData.setPackageTemplate1up(allowTemplateTree
					.getPackageTemplate().getPackageTemplate1up());

			allowTemplateTreeData.setTemplateTree1up(allowTemplateTree
					.getTemplateTree1up());

			allowTemplateTreeData.seteBSInstance1up(allowTemplateTree
					.geteBSInstance1up());

			allowTemplateTreeData.setNoUsedInCommonBenefit(this
					.getCommonUsedBenifit(allowTemplateTree
							.getTemplateTree1up()));
			allowTemplateTreeData.setName(allowTemplateTree.getName());
			allowTemplateTreeData.setDescription(allowTemplateTree
					.getDescription());
			allowTemplateTreeData.setGoXamXMLBlob(allowTemplateTree
					.getGoXamXMLBlob());
			allowTemplateTreeData.setCreateBy(allowTemplateTree.getCreateBy());
			allowTemplateTreeData.setCreateDt(allowTemplateTree.getCreateDt());
			allowTemplateTreeData.setUpdateBy(allowTemplateTree.getUpdateBy());
			allowTemplateTreeData.setUpdateDt(allowTemplateTree.getUpdateDt());

			allowTemplateTreeDataList.add(allowTemplateTreeData);

		}
		TemplateContainer<TemplateTreeData> result = new TemplateContainer<TemplateTreeData>();
		result.setData(allowTemplateTreeDataList);
		result.setPagesCount(allowTemplateTreeList.getTotalPages());
		result.setTotalItems(allowTemplateTreeList.getTotalElements());
		return result;

	}

	@Transactional(readOnly = true)
	public List<ShapeValueDefaultData> getAllTreeShapesForTemplateTree(int id)
			throws DataAccessException {

		List<ShapeValueDefault> shapeValueList = templateRepository
				.findAllTreeShapesByTemplateTreeId(id);

		List<ShapeValueDefaultData> shapeValueDataList = new ArrayList<ShapeValueDefaultData>();

		for (ShapeValueDefault shapeValue : shapeValueList) {

			ShapeValueDefaultData shapeValueData = new ShapeValueDefaultData();

			shapeValueData.seteBSInstance1up(shapeValue.geteBSInstance1up());
			if (shapeValue.getTreeShape() != null) {
				shapeValueData.setTreeShape1up(shapeValue.getTreeShape()
						.getTreeShape1up());
			}

			if (shapeValue.getTemplateTree() != null) {
				shapeValueData.setTemplateTree1up(shapeValue.getTemplateTree()
						.getTemplateTree1up());
			}

			if (shapeValue.getSetValueValueAsInt() != null) {
				MasterListIntValData data = new MasterListIntValData();
				data.setMasterListIntVal1up(shapeValue.getSetValueValueAsInt()
						.getMasterListIntVal1up());
				data.setIntAsDecimalValue(shapeValue.getSetValueValueAsInt()
						.getIntAsDecimalValue());
				data.setAbbrev(shapeValue.getSetValueValueAsInt().getAbbrev());
				shapeValueData.setIntValData(data);
			}
			if (shapeValue.getSetValueValueAsString() != null) {
				MasterListStringValData data = new MasterListStringValData();
				data.setMasterListStringVal1up(shapeValue
						.getSetValueValueAsString().getMasterListStringVal1up());
				data.setStringValue(shapeValue.getSetValueValueAsString()
						.getStringValue());
				data.setAbbrev(shapeValue.getSetValueValueAsString()
						.getAbbrev());
				shapeValueData.setStringValData(data);
			}

			shapeValueData.setApplyYesFlag(shapeValue
					.getIfYNTestAnswerYesFlag());
			shapeValueData.setApplyNoFlag(shapeValue.getIfYNTestAnswerNoFlag());

			shapeValueData.setCreateBy(shapeValueData.getCreateBy());
			shapeValueData.setCreateDt(shapeValueData.getCreateDt());
			shapeValueData.setUpdateBy(shapeValueData.getUpdateBy());
			shapeValueData.setUpdateDt(shapeValueData.getUpdateDt());

			shapeValueDataList.add(shapeValueData);

		}

		return shapeValueDataList;

	}

}
