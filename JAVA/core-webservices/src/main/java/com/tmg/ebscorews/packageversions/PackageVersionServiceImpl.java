package com.tmg.ebscorews.packageversions;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tmg.ebscore.dto.ServiceOperationResult;
import com.tmg.ebscore.dto.masterlist.MasterListStringValData;
import com.tmg.ebscore.dto.packageversion.BenefitOptionBig3COData;
import com.tmg.ebscore.dto.packageversion.BenefitSelectionTierdataData;
import com.tmg.ebscore.dto.packageversion.MatrixShapeBig3COLimitXXrefData;
import com.tmg.ebscore.dto.packageversion.MatrixShapeBig3COMessageXXrefData;
import com.tmg.ebscore.dto.packageversion.PackageVersionBenefitOptionData;
import com.tmg.ebscore.dto.packageversion.PackageVersionContainer;
import com.tmg.ebscore.dto.packageversion.PackageVersionData;
import com.tmg.ebscore.dto.packageversion.PackageVersionSummaryData;
import com.tmg.ebscore.dto.packageversion.PkgVerBig3COData;
import com.tmg.ebscore.dto.packageversion.PkgVerInstanceTreeData;
import com.tmg.ebscore.dto.packageversion.ProductServiceData;
import com.tmg.ebscore.dto.packageversion.ShapeValueInstanceTreeData;
import com.tmg.ebscore.dto.packageversion.TierData;
import com.tmg.ebscore.orm.DataAccessException;
import com.tmg.ebscorews.common.ServiceFault;

@Component("PackageVersionService")
@WebService(name = "PackageVersionService", serviceName = "PackageVersionService", endpointInterface = "com.tmg.ebscorews.packageversions.PackageVersionService", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class PackageVersionServiceImpl implements PackageVersionService {

	private static Logger logger = Logger
			.getLogger(PackageVersionServiceImpl.class);

	@Resource(name = "packageVersionService")
	private com.tmg.ebscore.service.PackageVersionService packageVersionService;

	@Override
	@WebMethod(operationName = "GetPkgVerByPkgId", action = "GetPkgVerByPkgId")
	public @WebResult(name = "PackageVersionData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	PackageVersionContainer<PackageVersionData> getPkgVerByPkgId(
			@WebParam(name = "PkgID") int pkgId,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {

		try {
			return packageVersionService.getPkgVerByPkgId(pkgId, page,
					maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetAllBeneitOptionById", action = "GetAllBeneitOptionById")
	public @WebResult(name = "PackageVersionBenefitOptionData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	PackageVersionContainer<PackageVersionBenefitOptionData> getAllBeneitOptionById(
			@WebParam(name = "PkgVersionId") int pkgVersionId,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {
		try {
			return packageVersionService.getAllBeneitOptionById(pkgVersionId,
					page, maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetCommonBenefitForEachPackageVersion", action = "GetCommonBenefitForEachPackageVersion")
	public @WebResult(name = "PkgVerInstanceTreeData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	PackageVersionContainer<PkgVerInstanceTreeData> getCommonBenefitForEachPackageVersion(
			@WebParam(name = "PkgVersionId") int pkgVersionId,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {
		try {
			return packageVersionService.getCommonBenefitForEachPackageVersion(
					pkgVersionId, page, maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}

	}

	@Override
	@WebMethod(operationName = "GetPkgVerBig3CoByPkgVerId", action = "GetPkgVerBig3CoByPkgVerId")
	public @WebResult(name = "PkgVerBig3COData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	PackageVersionContainer<PkgVerBig3COData> getPkgVerBig3CoByPkgVerId(
			@WebParam(name = "PkgVersionId") int pkgVerId,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {
		try {
			return packageVersionService.getPkgVerBig3CoByPkgVerId(pkgVerId,
					page, maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}

	}

	@Override
	@WebMethod(operationName = "GetBenefitRuleMapper", action = "GetBenefitRuleMapper")
	public @WebResult(name = "MasterListStringValDataList", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	List<MasterListStringValData> getBenefitRuleMapper(
			@WebParam(name = "MasterListStringValIdList") List<Integer> masterListStringVal1up)
			throws ServiceFault {
		try {
			return packageVersionService
					.getBenefitRuleMapper(masterListStringVal1up);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetPackageVersionSummary", action = "GetPackageVersionSummary")
	public @WebResult(name = "PackageVersionData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	PackageVersionContainer<PackageVersionSummaryData> getPackageVersionSummary(
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {
		return packageVersionService.getPackageVersionSummary(page, maxResults);

	}

	@Override
	@WebMethod(operationName = "GetAllBenifitBasedOnCommonBenifitType", action = "GetAllBenifitBasedOnCommonBenifitType")
	public @WebResult(name = "BenefitSelectionTierdataData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	PackageVersionContainer<BenefitSelectionTierdataData> getAllBenifitBasedOnCommonBenifitType(
			@WebParam(name = "VersionInstance") int versionInstance,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {
		try {
			return packageVersionService.getAllBenifitBasedOnCommonBenifitType(
					versionInstance, page, maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}

	}

	@Override
	@WebMethod(operationName = "GetBenefitSelectionForBenefitOption", action = "GetBenefitSelectionForBenefitOption")
	public @WebResult(name = "BenefitOptionTierdataData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	PackageVersionContainer<BenefitSelectionTierdataData> getBenefitSelectionForBenefitOption(
			@WebParam(name = "VersionInstance") int versionInstance,
			@WebParam(name = "BenfitOptionId") int benfitOptionId,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {

		try {
			return packageVersionService.getBenefitSelectionForBenefitOption(
					versionInstance, benfitOptionId, page, maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}

	}

	@Override
	@WebMethod(operationName = "GetPkgVerDetailsByPkgVerId", action = "GetPkgVerDetailsByPkgVerId")
	public @WebResult(name = "PackageVersionData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	PackageVersionData getPkgVerDetailsByPkgVerId(
			@WebParam(name = "PkgVersionId") int pkgVerId) throws ServiceFault {
		try {
			return packageVersionService.getPkgVerDetailsByPkgVerId(pkgVerId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetCommonBenefitForEachPackageVersionByTemplateTree", action = "GetCommonBenefitForEachPackageVersionByTemplateTree")
	public @WebResult(name = "PkgVerInstanceTreeDataList", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	List<PkgVerInstanceTreeData> getCommonBenefitForEachPackageVersionByTemplateTree(
			@WebParam(name = "PkgVersionId") int pkgVerId,
			@WebParam(name = "templateTreeId") int templateTree1up)
			throws ServiceFault {
		try {
			return packageVersionService
					.getCommonBenefitForEachPackageVersionByTemplateTree(
							pkgVerId, templateTree1up);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetServicetypeForBenefitOption", action = "GetServicetypeForBenefitOption")
	public @WebResult(name = "BenefitOptionBig3CODataList", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	List<BenefitOptionBig3COData> getServicetypeForBenefitOption(
			@WebParam(name = "PkgVersionId") int pkgVersionId,
			@WebParam(name = "PkgVersionBenefitOptionId") int pkgVersionBenefitOptionId,
			@WebParam(name = "ShowAll") boolean showAll) throws ServiceFault {
		try {
			return packageVersionService.getServicetypeForBenefitOption(
					pkgVersionId, pkgVersionBenefitOptionId, showAll);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "saveAndUpdatePkgVerBig3Co", action = "saveAndUpdatePkgVerBig3Co")
	@WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	public ServiceOperationResult saveAndUpdatePkgVerBig3Co(
			@WebParam(name = "PkgVerBig3COData", targetNamespace = "http://www.tmg.com/coreservices/data") List<PkgVerBig3COData> big3coDatas)
			throws ServiceFault {
		try {
			return packageVersionService.saveAndUpdatePkgVerBig3Co(big3coDatas);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveUpdatePackageVersion", action = "SaveUpdatePackageVersion")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	ServiceOperationResult saveUpdatePackageVersion(
			@WebParam(name = "PackageVersionData", targetNamespace = "http://www.tmg.com/coreservices/data") PackageVersionData packageVersionData)
			throws ServiceFault {
		return packageVersionService
				.saveUpdatePackageVersion(packageVersionData);
	}

	@Override
	@WebMethod(operationName = "SaveBenefitBasedOnCommmomBenifit", action = "SaveBenefitBasedOnCommmomBenifit")
	public @WebResult(name = "SaveBenefitSelectionTierdataData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	ServiceOperationResult saveBenefitBasedOnCommmomBenifit(
			@WebParam(name = "BenefitSelectionTierDataList", targetNamespace = "http://www.tmg.com/coreservices/data") List<BenefitSelectionTierdataData> benefitSelectionTierDataList)
			throws ServiceFault {
		try {
			return packageVersionService
					.saveBenefitBasedOnCommmomBenifit(benefitSelectionTierDataList);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}

	}

	// @Override
	// @WebMethod(operationName = "SaveTierNumberForBenefitSelection", action =
	// "SaveTierNumberForBenefitSelection")
	// public @WebResult(name = "int")
	// int saveTierNumberForBenefitSelection(
	// @WebParam(name = "TierDataList", targetNamespace =
	// "http://www.tmg.com/coreservices/data") List<TierData> tierDatas)
	// throws ServiceFault {
	// try {
	// return packageVersionService
	// .saveTierNumberForBenefitSelection(tierDatas);
	// } catch (DataAccessException e) {
	// logger.error(e.getMessage(), e);
	// throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
	// e);
	// }
	// }
	//
	// @Override
	// @WebMethod(operationName = "ChangeWorkFlowStateOfPackageVersion", action
	// = "ChangeWorkFlowStateOfPackageVersion")
	// public @WebResult(name = "ServiceOperationResult")
	// ServiceOperationResult changeWorkFlowStateOfPackageVersion(
	// @WebParam(name = "PkgVersionId") int pkgVersionId,
	// @WebParam(name = "WorkFlowStateId") int workflowStateId)
	// throws ServiceFault {
	// try {
	// return packageVersionService.changeWorkFlowStateOfPackageVersion(
	// pkgVersionId, workflowStateId);
	// } catch (DataAccessException e) {
	// logger.error(e.getMessage(), e);
	// throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
	// e);
	// }
	// }

	@Override
	@WebMethod(operationName = "SaveNewCommonBenefitForPkgVersion", action = "SaveNewCommonBenefitForPkgVersion")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	ServiceOperationResult saveNewCommonBenefitForPkgVersion(
			@WebParam(name = "PkgVerInstanceTreeData", targetNamespace = "http://www.tmg.com/coreservices/data") PkgVerInstanceTreeData treeData)
			throws ServiceFault {
		try {
			return packageVersionService
					.saveNewCommonBenefitForPkgVersion(treeData);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error saving value:" + e.getMessage(), e);
		}

	}

	@Override
	@WebMethod(operationName = "CreateNewPackageVersion", action = "CreateNewPackageVersion")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	ServiceOperationResult createNewPackageVersion(
			@WebParam(name = "PackageVersionData", targetNamespace = "http://www.tmg.com/coreservices/data") PackageVersionData data)
			throws ServiceFault {
		try {
			return packageVersionService.createNewPackageVersion(data);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "CreateNewPackageVersionFromExistingVersion", action = "CreateNewPackageVersionFromExistingVersion")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	ServiceOperationResult createNewPackageVersionFromExistingVersion(
			@WebParam(name = "PackageVersionData", targetNamespace = "http://www.tmg.com/coreservices/data") PackageVersionData data)
			throws ServiceFault {
		try {
			return packageVersionService
					.createNewPackageVersionFromExistingVersion(data);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "DeletePackageVersion", action = "DeletePackageVersion")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	ServiceOperationResult deletePackageVersion(
			@WebParam(name = "pkgVerId") int pkgVerId) throws ServiceFault {
		try {
			return packageVersionService.deletePackageVersion(pkgVerId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Deleting PackageVersion:"
					+ e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "DeleteCommonBenefit", action = "DeleteCommonBenefit")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	ServiceOperationResult deleteCommonBenefit(
			@WebParam(name = "pkgVerInstTreeId") int pkgVerInstTreeId)
			throws ServiceFault {
		try {
			return packageVersionService.deleteCommonBenefit(pkgVerInstTreeId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Deleting CommonBenefit:"
					+ e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "DeleteBenefitOffering", action = "DeleteBenefitOffering")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	ServiceOperationResult deleteBenefitOffering(
			@WebParam(name = "pkgConfigOptionId") int pkgConfigOptionId)
			throws ServiceFault {
		try {
			return packageVersionService
					.deleteBenefitOffering(pkgConfigOptionId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Deleting BenefitOffering:"
					+ e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveAndUpdateBenefitOption", action = "SaveAndUpdateBenefitOption")
	public @WebResult(name = "ServiceResult")
	ServiceOperationResult saveAndUpdateBenefitOption(
			@WebParam(name = "BenefitOptionData", targetNamespace = "http://www.tmg.com/coreservices/data") PackageVersionBenefitOptionData benefitOptionData,
			@WebParam(name = "ListBenefitOptionBig3COData", targetNamespace = "http://www.tmg.com/coreservices/data") List<BenefitOptionBig3COData> big3coDatas,
			@WebParam(name = "ListBenefitOptionTierdataData", targetNamespace = "http://www.tmg.com/coreservices/data") List<BenefitSelectionTierdataData> optionTierdataDatas)
			throws ServiceFault {
		try {
			return packageVersionService.saveAndUpdateBenefitOption(
					benefitOptionData, big3coDatas, optionTierdataDatas);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveUpdateAndDeleteServiceTypeBenefitOption", action = "SaveUpdateAndDeleteServiceTypeBenefitOption")
	public @WebResult(name = "SaveServiceTypeBenefitOption")
	ServiceOperationResult saveUpdateAndDeleteServiceTypeBenefitOption(
			@WebParam(name = "BenefitOptionId") int benefitOptionId,
			@WebParam(name = "PackageVersionBenefitOptionData", targetNamespace = "http://www.tmg.com/coreservices/data") PackageVersionBenefitOptionData benefitOptionData,
			@WebParam(name = "BenefitOptionBig3COData", targetNamespace = "http://www.tmg.com/coreservices/data") List<BenefitOptionBig3COData> big3coDatas)
			throws ServiceFault {
		try {
			return packageVersionService
					.saveUpdateAndDeleteServiceTypeBenefitOption(
							benefitOptionId, benefitOptionData, big3coDatas);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveAndUpdateBenefitSelectionForBenefitOption", action = "SaveAndUpdateBenefitSelectionForBenefitOption")
	public @WebResult(name = "SaveBenefitSelectionForBenefitOption")
	ServiceOperationResult saveAndUpdateBenefitSelectionForBenefitOption(
			@WebParam(name = "BenefitOptionId") int benefitOptionId,
			@WebParam(name = "PackageVersionBenefitOptionData", targetNamespace = "http://www.tmg.com/coreservices/data") PackageVersionBenefitOptionData benefitOptionData,
			@WebParam(name = "BenefitOptionTierdataData", targetNamespace = "http://www.tmg.com/coreservices/data") List<BenefitSelectionTierdataData> optionTierdataDatas)
			throws ServiceFault {
		try {
			return packageVersionService
					.saveAndUpdateBenefitSelectionForBenefitOption(
							benefitOptionId, benefitOptionData,
							optionTierdataDatas);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveTierNumberForBenefitSelection", action = "SaveTierNumberForBenefitSelection")
	public @WebResult(name = "int")
	int saveTierNumberForBenefitSelection(
			@WebParam(name = "TierDataList") List<TierData> tierDatas)
			throws ServiceFault {
		try {
			return packageVersionService
					.saveTierNumberForBenefitSelection(tierDatas);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "CopyCommonBenefitFormExistingCommonBenefit", action = "CopyCommonBenefitFormExistingCommonBenefit")
	@WebResult(name = "copyCommonBenefitFormExistingCommonBenefit")
	public ServiceOperationResult copyCommonBenefitFormExistingCommonBenefit(
			PkgVerInstanceTreeData treeData, int oldInstanceTreeId)
			throws ServiceFault {
		try {
			return packageVersionService
					.copyCommonBenefitFormExistingCommonBenefit(treeData,
							oldInstanceTreeId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "GetMergedBenefitSelectionForANetwork", action = "GetMergedBenefitSelectionForANetwork")
	public @WebResult(name = "MergedBenefitSelectionTierdataData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	List<BenefitSelectionTierdataData> getMergedBenefitSelectionForANetwork(
			@WebParam(name = "InstanceTreeId") int instanceTreeId,
			@WebParam(name = "BenfitOptionId") List<Integer> benfitOptionIds,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {
		try {
			return packageVersionService.getMergedBenefitSelectionForANetwork(
					instanceTreeId, benfitOptionIds, page, maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "GetDefaultBenifitBasedOnPkgVerBenefitSlection", action = "GetDefaultBenifitBasedOnPkgVerBenefitSlection")
	public @WebResult(name = "BenefitSelectionDefaultData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	BenefitSelectionTierdataData getDefaultBenifitBasedOnPkgVerBenefitSlection(
			@WebParam(name = "VersionInstance") int versionInstance)
			throws ServiceFault {
		try {
			return packageVersionService
					.getDefaultBenifitBasedOnPkgVerBenefitSlection(versionInstance);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrive value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "GetAllMessageValForBenefitSelectionOnPkgVersion", action = "GetAllMessageValForBenefitSelectionOnPkgVersion")
	public @WebResult(name = "MatrixShapeBig3COMessageXXrefData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	List<MatrixShapeBig3COMessageXXrefData> getAllMessageValForBenefitSelectionOnPkgVersion(
			@WebParam(name = "benSelTierData1up") int benSelTierData1up,
			@WebParam(name = "treeShape1up") int treeShape1up)
			throws ServiceFault {
		try {
			return packageVersionService
					.getAllMessageValForBenefitSelectionOnPkgVersion(
							benSelTierData1up, treeShape1up);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrive value:" + e.getMessage(), e);
		}

	}

	@Override
	@WebMethod(operationName = "GetAllLimitValForBenefitSelectionOnPkgVersion", action = "GetAllLimitValForBenefitSelectionOnPkgVersion")
	public @WebResult(name = "MatrixShapeBig3COLimitXXrefData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	List<MatrixShapeBig3COLimitXXrefData> getAllLimitValForBenefitSelectionOnPkgVersion(
			@WebParam(name = "benSelTierData1up") int benSelTierData1up,
			@WebParam(name = "treeShape1up") int treeShape1up)
			throws ServiceFault {
		try {
			return packageVersionService
					.getAllLimitValForBenefitSelectionOnPkgVersion(
							benSelTierData1up, treeShape1up);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrive value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveAndUpdateBenefitSelectionInShapeValueBig3Co", action = "SaveAndUpdateBenefitSelectionInShapeValueBig3Co")
	public @WebResult(name = "SaveShapeValueBig3CoData")
	ServiceOperationResult saveAndUpdateBenefitSelectionInShapeValueBig3Co(
			@WebParam(name = "instanceTreeId") int instanceTreeId,
			@WebParam(name = "BenefitSelectionTierDataList", targetNamespace = "http://www.tmg.com/coreservices/data") List<BenefitSelectionTierdataData> benefitSelectionTierDataList)
			throws ServiceFault {

		try {
			return packageVersionService
					.saveAndUpdateBenefitSelectionInShapeValueBig3Co(
							instanceTreeId, benefitSelectionTierDataList);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "GetAllEnableLimitValForBenefitForEachPkgVersion", action = "GetAllEnableLimitValForBenefitForEachPkgVersion")
	public @WebResult(name = "MatrixShapeBig3COLimitXXrefEnableData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	List<MatrixShapeBig3COLimitXXrefData> getAllEnableLimitValForBenefitForEachPkgVersion(
			@WebParam(name = "pkgVerBig3CoId") int pkgVerBig3CoId,
			@WebParam(name = "treeShapeId") int treeShapeId)
			throws ServiceFault {
		try {
			return packageVersionService
					.getAllEnableLimitValForBenefitForEachPkgVersion(
							pkgVerBig3CoId, treeShapeId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrive value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "GetAllEnableMessageValForBenefitForEachPkgVersion", action = "GetAllEnableMessageValForBenefitForEachPkgVersion")
	public @WebResult(name = "MatrixShapeBig3COMessageXXrefEnableData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	List<MatrixShapeBig3COMessageXXrefData> getAllEnableMessageValForBenefitForEachPkgVersion(
			@WebParam(name = "pkgVerBig3CoId") int pkgVerBig3CoId,
			@WebParam(name = "treeShapeId") int treeShapeId)
			throws ServiceFault {
		try {
			return packageVersionService
					.getAllEnableMessageValForBenefitForEachPkgVersion(
							pkgVerBig3CoId, treeShapeId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrive value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "GetAllEnableLimitValForBenefitForEachBenefitOption", action = "GetAllEnableLimitValForBenefitForEachBenefitOption")
	public @WebResult(name = "LimitShapeBenefitOptionOverrideEnableData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	List<MatrixShapeBig3COLimitXXrefData> getAllEnableLimitValForBenefitForEachBenefitOption(
			@WebParam(name = "pkgVerBig3CoId") int pkgVerBig3CoId,
			@WebParam(name = "pkgBenOptionId") int pkgBenOptionId,
			@WebParam(name = "treeShapeId") int treeShapeId)
			throws ServiceFault {
		try {
			return packageVersionService
					.getAllEnableLimitValForBenefitForEachBenefitOption(
							pkgVerBig3CoId, pkgBenOptionId, treeShapeId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrive value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "GetAllEnableMessageValForBenefitForEachBenefitOption", action = "GetAllEnableMessageValForBenefitForEachBenefitOption")
	public @WebResult(name = "MessageShapeBenefitOptionOverrideEnableData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	List<MatrixShapeBig3COMessageXXrefData> getAllEnableMessageValForBenefitForEachBenefitOption(
			@WebParam(name = "pkgVerBig3CoId") int pkgVerBig3CoId,
			@WebParam(name = "pkgBenOptionId") int pkgBenOptionId,
			@WebParam(name = "treeShapeId") int treeShapeId)
			throws ServiceFault {
		try {
			return packageVersionService
					.getAllEnableMessageValForBenefitForEachBenefitOption(
							pkgVerBig3CoId, pkgBenOptionId, treeShapeId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrive value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "GetAllLimitValForBenefitSelectionOnBenefitOption", action = "GetAllLimitValForBenefitSelectionOnBenefitOption")
	public @WebResult(name = "LimitShapeBenefitOptionOverrideData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	List<MatrixShapeBig3COLimitXXrefData> getAllLimitValForBenefitSelectionOnBenefitOption(
			@WebParam(name = "benOptionTierData1up") int benOptionTierData1up,
			@WebParam(name = "treeShape1up") int treeShape1up)
			throws ServiceFault {
		try {
			return packageVersionService
					.getAllLimitValForBenefitSelectionOnBenefitOption(
							benOptionTierData1up, treeShape1up);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrive value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "GetAllMessageValForBenefitSelectionOnBenefitOption", action = "GetAllMessageValForBenefitSelectionOnBenefitOption")
	public @WebResult(name = "MessageShapeBenefitOptionOverrideData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	List<MatrixShapeBig3COMessageXXrefData> getAllMessageValForBenefitSelectionOnBenefitOption(
			@WebParam(name = "benOptionTierData1up") int benOptionTierData1up,
			@WebParam(name = "treeShape1up") int treeShape1up)
			throws ServiceFault {
		try {
			return packageVersionService
					.getAllMessageValForBenefitSelectionOnBenefitOption(
							benOptionTierData1up, treeShape1up);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrive value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "UpdateDefaultValueOfBenefitSelectionForPackgeVersion", action = "UpdateDefaultValueOfBenefitSelectionForPackgeVersion")
	public @WebResult(name = "DefaultValueOfBenefitSelectionData")
	ServiceOperationResult updateDefaultValueOfBenefitSelectionForPackgeVersion(
			@WebParam(name = "benefitSelectionTierData", targetNamespace = "http://www.tmg.com/coreservices/data") BenefitSelectionTierdataData benefitSelectionTierData,
			@WebParam(name = "instanceTreeId") int instanceTreeId)
			throws ServiceFault {
		try {
			return packageVersionService
					.updateDefaultValueOfBenefitSelectionForPackgeVersion(
							benefitSelectionTierData, instanceTreeId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "GetDefaultBenefitSelectionForBenefitOption", action = "GetDefaultBenefitSelectionForBenefitOption")
	public @WebResult(name = "DefaultBenefitOptionTierdataData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	BenefitSelectionTierdataData getDefaultBenefitSelectionForBenefitOption(
			@WebParam(name = "VersionInstance") int versionInstance,
			@WebParam(name = "BenfitOptionId") int benfitOptionId)
			throws ServiceFault {
		try {
			return packageVersionService
					.getDefaultBenefitSelectionForBenefitOption(
							versionInstance, benfitOptionId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrive value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveAndUpdateBenefitSelectionInShapeValueBenefitOptionForBig3Co", action = "SaveAndUpdateBenefitSelectionInShapeValueBenefitOptionForBig3Co")
	public @WebResult(name = "ShapeValueBenefitOptionForBig3CoData")
	ServiceOperationResult saveAndUpdateBenefitSelectionInShapeValueBenefitOptionForBig3Co(
			@WebParam(name = "instanceTreeId") int instanceTreeId,
			@WebParam(name = "BenefitSelectionTierDataList", targetNamespace = "http://www.tmg.com/coreservices/data") List<BenefitSelectionTierdataData> benefitSelectionTierDataList)
			throws ServiceFault {
		try {
			return packageVersionService
					.saveAndUpdateBenefitSelectionInShapeValueBenefitOptionForBig3Co(
							instanceTreeId, benefitSelectionTierDataList);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "GetAllBenefitsForAProduct", action = "GetAllBenefitsForAProduct")
	public @WebResult(name = "ProductServiceData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	ProductServiceData getAllBenefitsForAProduct(
			@WebParam(name = "ProductId") int productId,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {
		try {
			return packageVersionService.getAllBenefitsForAProduct(productId,
					page, maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrive value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "saveAndDeleteEnabledMessagesForBenefitSelectionForBenefitOption", action = "saveAndDeleteEnabledMessagesForBenefitSelectionForBenefitOption")
	public @WebResult(name = "MatrixShapeBig3COMessageXXrefData")
	ServiceOperationResult saveAndDeleteEnabledMessagesForBenefitSelectionForBenefitOption(
			@WebParam(name = "MatrixShapeBig3COMessageXXrefData", targetNamespace = "http://www.tmg.com/coreservices/data") List<MatrixShapeBig3COMessageXXrefData> big3coMessageXXrefData,
			@WebParam(name = "treeShape1up") int treeShape1up,
			@WebParam(name = "pkgVerBig3CO1up") int pkgVerBig3CO1up)
			throws ServiceFault {

		try {

			return packageVersionService
					.saveAndDeleteEnabledMessagesForBenefitSelectionForBenefitOption(
							big3coMessageXXrefData, treeShape1up,
							pkgVerBig3CO1up);
		} catch (DataAccessException e) {
			;
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrive value:" + e.getMessage(), e);
		}

	}

	@Override
	@WebMethod(operationName = "GetAllServiceTypeForPKgVersion", action = "GetAllServiceTypeForPKgVersion")
	public @WebResult(name = "PkgVerBig3COData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	List<PkgVerBig3COData> getAllServiceTypeForPKgVersion(
			@WebParam(name = "pkgVersionId") int pkgVersionId)
			throws ServiceFault {
		try {
			return packageVersionService
					.getAllServiceTypeForPKgVersion(pkgVersionId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrive value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "GetInstanceTreesAllTreeShapeValue", action = "GetInstanceTreesAllTreeShapeValue")
	public @WebResult(name = "ShapeValueInstanceTreeData", targetNamespace = "http://www.tmg.com/coreservices/PackageVersionService")
	List<ShapeValueInstanceTreeData> getInstanceTreesAllTreeShapeValue(
			@WebParam(name = "pkgVerInstanceTreeId") int pkgVerInstanceTreeId)
			throws ServiceFault {
		try {
			return packageVersionService
					.getInstanceTreesAllTreeShapeValue(pkgVerInstanceTreeId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrive value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveAndUpdateAllTreeShapesForInstanceTree", action = "SaveAndUpdateAllTreeShapesForInstanceTree")
	public @WebResult(name = "ServiceOperationResult")
	ServiceOperationResult saveAndUpdateAllTreeShapesForInstanceTree(
			@WebParam(name = "ShapeValueInstanceTreeData", targetNamespace = "http://www.tmg.com/coreservices/data") List<ShapeValueInstanceTreeData> instanceTreeDatas)
			throws ServiceFault {
		try {
			return packageVersionService
					.saveAndUpdateAllTreeShapesForInstanceTree(instanceTreeDatas);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error saving value:" + e.getMessage(), e);
		}
	}

}
