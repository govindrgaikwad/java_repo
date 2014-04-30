package com.tmg.ebscorews.packages;

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
import com.tmg.ebscore.dto.ebspackage.BenifitPackageData;
import com.tmg.ebscore.dto.ebspackage.PackageContainer;
import com.tmg.ebscore.dto.ebspackage.PackageSummaryData;
import com.tmg.ebscore.dto.ebspackage.PkgConfigOptionData;
import com.tmg.ebscore.dto.ebspackage.PkgConfigOptionFamilyData;
import com.tmg.ebscore.dto.ebspackage.PkgMasterListForBig3COData;
import com.tmg.ebscore.orm.DataAccessException;
import com.tmg.ebscorews.common.ServiceFault;

@Component("PackageService")
@WebService(name = "PackageService", serviceName = "PackageService", endpointInterface = "com.tmg.ebscorews.packages.PackageService", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class PackageServiceImpl implements PackageService {

	private static Logger logger = Logger.getLogger(PackageServiceImpl.class);

	@Resource(name = "packageService")
	private com.tmg.ebscore.service.PackageService packageService;

	@Override
	@WebMethod(operationName = "GetPackageList", action = "GetPackageList")
	public @WebResult(name = "PackageSummaryData", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	PackageContainer<PackageSummaryData> getPackageList(
			@WebParam(name = "page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {

		try {
			return packageService.getPackageList(page, maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetNoOfReleasedPackageVersions", action = "GetNoOfReleasedPackageVersions")
	public @WebResult(name = "NoOfReleasedPackageVersions")
	int getNoOfReleasedPackageVersions(
			@WebParam(name = "PackageId") int packageId) throws ServiceFault {
		try {
			return packageService.getNoOfReleasedPackageVersions(packageId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveUpdatePackage", action = "SaveUpdatePackage")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	ServiceOperationResult saveUpdatePackage(
			@WebParam(name = "BenifitPackageData", targetNamespace = "http://www.tmg.com/coreservices/data") BenifitPackageData data)
			throws ServiceFault {
		try {
			return packageService.saveUpdatePackage(data);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Saving data:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "CopyFromExistingPackage", action = "CopyFromExistingPackage")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	ServiceOperationResult copyFromExistingPackage(
			@WebParam(name = "BenifitPackageData", targetNamespace = "http://www.tmg.com/coreservices/data") BenifitPackageData data,
			@WebParam(name = "PkgversionId") List<Integer> pkgversionId,
			@WebParam(name = "ServiceType") boolean serviceType,
			@WebParam(name = "CommonBenefit") boolean commonBenefit,
			@WebParam(name = "BenefitOption") boolean benefitOption)
			throws ServiceFault {
		try {
			return packageService.copyFromExistingPackage(data, pkgversionId,
					serviceType, commonBenefit, benefitOption);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Copying data:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "NoOfUnReleasedPackageVersions", action = "NoOfUnReleasedPackageVersions")
	public @WebResult(name = "NoOfUnReleasedPackageVersions")
	int getNoOfUnReleasedPackageVersions(
			@WebParam(name = "PackageId") int packageId) throws ServiceFault {
		try {
			return packageService.getNoOfUnReleasedPackageVersions(packageId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error retrieving data:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "GetPackageDetails", action = "GetPackageDetails")
	public @WebResult(name = "BenifitPackageData", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	BenifitPackageData getPackageDetails(@WebParam(name = "id") int id)
			throws ServiceFault {
		try {
			return packageService.getPackageDetails(id);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error retrieving data:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "DeletePackage", action = "DeletePackage")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	ServiceOperationResult deletePackage(
			@WebParam(name = "PackageId") int packageId) throws ServiceFault {
		try {
			return packageService.deletePackage(packageId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error deleting data:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "DeleteBenefitOption", action = "DeleteBenefitOption")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	ServiceOperationResult deleteBenefitOption(
			@WebParam(name = "PkgVerBenefitOption1up") int pkgVerBenefitOption1up)
			throws ServiceFault {
		try {
			return packageService.deleteBenefitOption(pkgVerBenefitOption1up);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error deleting data:" + e.getMessage(), e);
		}

	}

	@Override
	@WebMethod(operationName = "GetAllBenefitOffering", action = "GetAllBenefitOffering")
	public @WebResult(name = "PkgConfigOptionDataContainer", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	PackageContainer<PkgConfigOptionData> getAllBenefitOffering(
			@WebParam(name = "Id") int id, @WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {
		try {
			return packageService.getAllBenefitOffering(id, page, maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetServiceTypeForPackage", action = "GetServiceTypeForPackage")
	public @WebResult(name = "PkgMasterListForBig3CODataContainer", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	PackageContainer<PkgMasterListForBig3COData> getServiceTypeForPackage(
			@WebParam(name = "Id") int id, @WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {
		try {
			return packageService
					.getServiceTypeForPackage(id, page, maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "CreateBenefitOfferingFromExsisting", action = "CreateBenefitOfferingFromExsisting")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	ServiceOperationResult createBenefitOfferingFromExsisting(
			@WebParam(name = "PkgConfigOptionData", targetNamespace = "http://www.tmg.com/coreservices/data") PkgConfigOptionData optionData,
			@WebParam(name = "ExistingId") int existingId) throws ServiceFault {
		try {
			return packageService.createBenefitOfferingFromExsisting(
					optionData, existingId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error creating value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveServiceTypeForPackage", action = "SaveServiceTypeForPackage")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	ServiceOperationResult saveServiceTypeForPackage(
			@WebParam(name = "PkgMasterListForBig3COData", targetNamespace = "http://www.tmg.com/coreservices/data") List<PkgMasterListForBig3COData> dataList,
			@WebParam(name = "PackageID") int packageID) throws ServiceFault {
		try {
			return packageService
					.saveServiceTypeForPackage(dataList, packageID);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "GetAllConfigOptionFamilyByPackageId", action = "GetAllConfigOptionFamilyByPackageId")
	public @WebResult(name = "PkgConfigOptionFamilyData", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	List<PkgConfigOptionFamilyData> getAllConfigOptionFamilyByPackageId(
			@WebParam(name = "pkgId") int pkgId) throws ServiceFault {
		try {
			return packageService.getAllConfigOptionFamilyByPackageId(pkgId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveAndUpdateBenefitOffering", action = "SaveAndUpdateBenefitOffering")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	ServiceOperationResult saveAndUpdateBenefitOffering(
			@WebParam(name = "PkgConfigOptionData", targetNamespace = "http://www.tmg.com/coreservices/data") PkgConfigOptionData optionData,
			@WebParam(name = "PkgConfigOptionFamilyData", targetNamespace = "http://www.tmg.com/coreservices/data") PkgConfigOptionFamilyData configOptionFamily,
			@WebParam(name = "BenefitOptionId") List<Integer> benefitOptionIds)
			throws ServiceFault {
		try {
			return packageService.saveAndUpdateBenefitOffering(optionData,
					configOptionFamily, benefitOptionIds);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}

	
	}

}
