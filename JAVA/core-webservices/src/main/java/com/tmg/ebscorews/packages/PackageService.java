package com.tmg.ebscorews.packages;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.BindingType;

import com.tmg.ebscore.dto.ServiceOperationResult;
import com.tmg.ebscore.dto.ebspackage.BenifitPackageData;
import com.tmg.ebscore.dto.ebspackage.PackageContainer;
import com.tmg.ebscore.dto.ebspackage.PackageSummaryData;
import com.tmg.ebscore.dto.ebspackage.PkgConfigOptionData;
import com.tmg.ebscore.dto.ebspackage.PkgConfigOptionFamilyData;
import com.tmg.ebscore.dto.ebspackage.PkgMasterListForBig3COData;
import com.tmg.ebscorews.common.ServiceFault;

@WebService(targetNamespace = "http://www.tmg.com/coreservices/PackageService")
@SOAPBinding(parameterStyle = ParameterStyle.WRAPPED, style = Style.DOCUMENT, use = Use.LITERAL)
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public interface PackageService {

	@WebMethod(operationName = "GetPackageList", action = "GetPackageList")
	public @WebResult(name = "PackageSummaryData", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	PackageContainer<PackageSummaryData> getPackageList(
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResult") int maxResults) throws ServiceFault;

	@WebMethod(operationName = "GetNoOfReleasedPackageVersions", action = "GetNoOfReleasedPackageVersions")
	public @WebResult(name = "NoOfReleasedPackageVersions", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	int getNoOfReleasedPackageVersions(
			@WebParam(name = "PackageId") int packageId) throws ServiceFault;

	@WebMethod(operationName = "SaveUpdatePackage", action = "SaveUpdatePackage")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	ServiceOperationResult saveUpdatePackage(
			@WebParam(name = "BenifitPackageData", targetNamespace = "http://www.tmg.com/coreservices/data") BenifitPackageData data)
			throws ServiceFault;

	@WebMethod(operationName = "CopyFromExistingPackage", action = "CopyFromExistingPackage")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	ServiceOperationResult copyFromExistingPackage(
			@WebParam(name = "BenifitPackageData", targetNamespace = "http://www.tmg.com/coreservices/data") BenifitPackageData data,
			@WebParam(name = "PkgversionId") List<Integer> pkgversionId,
			@WebParam(name = "ServiceType") boolean serviceType,
			@WebParam(name = "CommonBenefit") boolean commonBenefit,
			@WebParam(name = "BenefitOption") boolean benefitOption)
			throws ServiceFault;

	@WebMethod(operationName = "GetNoOfUnReleasedPackageVersions", action = "GetNoOfUnReleasedPackageVersions")
	public @WebResult(name = "NoOfUnReleasedPackageVersions")
	int getNoOfUnReleasedPackageVersions(
			@WebParam(name = "PackageId") int packageId) throws ServiceFault;

	@WebMethod(operationName = "GetPackageDetails", action = "GetPackageDetails")
	public @WebResult(name = "BenifitPackageData", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	BenifitPackageData getPackageDetails(@WebParam(name = "id") int id)
			throws ServiceFault;

	@WebMethod(operationName = "DeletePackage", action = "DeletePackage")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	ServiceOperationResult deletePackage(
			@WebParam(name = "PackageId") int packageId) throws ServiceFault;

	@WebMethod(operationName = "DeleteBenefitOption", action = "DeleteBenefitOption")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	ServiceOperationResult deleteBenefitOption(
			@WebParam(name = "PkgVerBenefitOption1up") int pkgVerBenefitOption1up)
			throws ServiceFault;

	@WebMethod(operationName = "GetAllBenefitOffering", action = "GetAllBenefitOffering")
	public @WebResult(name = "PkgConfigOptionDataContainer", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	PackageContainer<PkgConfigOptionData> getAllBenefitOffering(
			@WebParam(name = "Id") int id, @WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault;

	@WebMethod(operationName = "GetServiceTypeForPackage", action = "GetServiceTypeForPackage")
	public @WebResult(name = "PkgMasterListForBig3CODataContainer", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	PackageContainer<PkgMasterListForBig3COData> getServiceTypeForPackage(
			@WebParam(name = "Id") int id, @WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault;

	@WebMethod(operationName = "SaveAndUpdateBenefitOffering", action = "SaveAndUpdateBenefitOffering")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	ServiceOperationResult saveAndUpdateBenefitOffering(
			@WebParam(name = "PkgConfigOptionData", targetNamespace = "http://www.tmg.com/coreservices/data") PkgConfigOptionData data,
			@WebParam(name = "PkgConfigOptionFamilyData", targetNamespace = "http://www.tmg.com/coreservices/data") PkgConfigOptionFamilyData configOptionFamily,
			@WebParam(name = "BenefitOptionId") List<Integer> benefitOptionIds)
			throws ServiceFault;

	@WebMethod(operationName = "CreateBenefitOfferingFromExsisting", action = "CreateBenefitOfferingFromExsisting")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	ServiceOperationResult createBenefitOfferingFromExsisting(
			@WebParam(name = "PkgConfigOptionData", targetNamespace = "http://www.tmg.com/coreservices/data") PkgConfigOptionData optionData,
			@WebParam(name = "ExistingId") int existingId) throws ServiceFault;

	@WebMethod(operationName = "SaveServiceTypeForPackage", action = "SaveServiceTypeForPackage")
	public @WebResult(name = "ServiceOperationResult", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	ServiceOperationResult saveServiceTypeForPackage(
			@WebParam(name = "PkgMasterListForBig3COData", targetNamespace = "http://www.tmg.com/coreservices/data") List<PkgMasterListForBig3COData> dataList,
			@WebParam(name = "PackageID") int packageID) throws ServiceFault;

	@WebMethod(operationName = "GetAllConfigOptionFamilyByPackageId", action = "GetAllConfigOptionFamilyByPackageId")
	public @WebResult(name = "PkgConfigOptionFamilyData", targetNamespace = "http://www.tmg.com/coreservices/PackageService")
	List<PkgConfigOptionFamilyData> getAllConfigOptionFamilyByPackageId(
			@WebParam(name = "pkgId") int pkgId) throws ServiceFault;

}
