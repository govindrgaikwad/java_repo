package com.tmg.ebscorews.template;

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
import com.tmg.ebscore.orm.template.TemplateWorkflowState;
import com.tmg.ebscorews.common.ServiceFault;

@WebService(targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
@SOAPBinding(parameterStyle = ParameterStyle.WRAPPED, style = Style.DOCUMENT, use = Use.LITERAL)
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public interface TemplateService {

	@WebMethod(operationName = "GetTemplateList", action = "GetTemplateList")
	public @WebResult(name = "TemplateListData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<TemplateSummaryData> getTemplateList(
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResult") int maxResults) throws ServiceFault;

	@WebMethod(operationName = "DeleteTemplate", action = "DeleteTemplate")
	public @WebResult(name = "DeleteTemplateResult", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	ServiceOperationResult deleteTemplate(
			@WebParam(name = "TemplateId") int templateId) throws ServiceFault;

	@WebMethod(operationName = "ChangeWorkFlowState", action = "ChangeWorkFlowState")
	public @WebResult(name = "SaveChangeWorkFlowState")
	ServiceOperationResult changeWorkFlowState(
			@WebParam(name = "TemplateWorkFlowid") int templateWorkFlowid,
			@WebParam(name = "PackageTemplateid") int packageTemplateid)
			throws ServiceFault;

	@WebMethod(operationName = "GetTemplateWorkFlowStateListAtTemplate", action = "GetTemplateWorkFlowStateListAtTemplate")
	public @WebResult(name = "TemplateWorkFlowData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<TemplateWorkFlowStateData> getTemplateWorkFlowStateListAtTemplate(
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault;

	@WebMethod(operationName = "GetNoOFReleasedPackage", action = "GetNoOFReleasedPackage")
	public @WebResult(name = "NoOFReleasedPackages", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	int getNoOFReleasedPackage(@WebParam(name = "templateId") int tempId)
			throws ServiceFault;

	@WebMethod(operationName = "GetNoOFUnReleasedPackage", action = "GetNoOFUnReleasedPackage")
	public @WebResult(name = "NoOFUnReleasedPackages", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	int getNoOFUnReleasedPackage(@WebParam(name = "templateId") int tempId)
			throws ServiceFault;

	@WebMethod(operationName = "GetPackagesForTemplate", action = "GetPackagesForTemplate")
	public @WebResult(name = "GetBenifitPackageData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<BenifitPackageData> getPackagesForTemplate(
			@WebParam(name = "TempId") int id,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault;

	@WebMethod(operationName = "GetIntValListForTemplate", action = "GetIntValListForTemplate")
	public @WebResult(name = "TemplateAllowsIntValData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<TemplateAllowsIntValData> getIntValListForTemplate(
			@WebParam(name = "MasterListId") int masterListId,
			@WebParam(name = "TemplateId") int tempId,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault;

	@WebMethod(operationName = "GetStringValListForTemplate", action = "GetStringValListForTemplate")
	public @WebResult(name = "TemplateAllowsStringValData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<TemplateAllowsStringValData> getStringValListForTemplate(
			@WebParam(name = "MasterListId") int masterListId,
			@WebParam(name = "TempId") int tempId,
			@WebParam(name = "page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault;

	@WebMethod(operationName = "GetMessageValListForTemplate", action = "GetMessageValListForTemplate")
	public @WebResult(name = "TemplateAllowsMessageValData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<TemplateAllowsMessageValData> getMessageValListForTemplate(
			@WebParam(name = "MasterListId") int masterListId,
			@WebParam(name = "TempId") int tempId,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault;

	@WebMethod(operationName = "GetLimitValListForTemplate", action = "GetLimitValListForTemplate")
	public @WebResult(name = "TemplateAllowsLimitValData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<TemplateAllowsLimitValData> getLimitValListForTemplate(
			@WebParam(name = "MasterListId") int masterListId,
			@WebParam(name = "TempId") int tempId,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault;

	@WebMethod(operationName = "GetServiceDefinitionValListForTemplate", action = "GetServiceDefinitionValListForTemplate")
	public @WebResult(name = "TemplateAllowsServiceDefinitionData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<TemplateAllowsServiceDefinitionData> getServiceDefinitionValListForTemplate(
			@WebParam(name = "MasterListId") int masterListId,
			@WebParam(name = "TempId") int tempId,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault;

	@WebMethod(operationName = "GetWorkFlowStateForEachTemplate", action = "GetWorkFlowStateForEachTemplate")
	public @WebResult(name = "PkgVersionWorkflowStateData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<PkgVersionWorkflowStateData> getWorkFlowStateForEachTemplate(
			@WebParam(name = "packageTemplateId") int id,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault;

	@WebMethod(operationName = "GetAllTemplateTreeForTemplate", action = "GetAllTemplateTreeForTemplate")
	public @WebResult(name = "TemplateTreeData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<TemplateTreeData> getAllTemplateTreeForTemplate(
			@WebParam(name = "TempId") int id,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault;

	@WebMethod(operationName = "SaveTemplateFromExistingTemplate", action = "SaveTemplateFromExistingTemplate")
	public @WebResult(name = "SaveTemplateFromExistingTemplate", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	ServiceOperationResult saveTemplateFromExistingTemplate(
			@WebParam(name = "PackageTemplateData", targetNamespace = "http://www.tmg.com/coreservices/data") PackageTemplateData data,
			@WebParam(name = "MasterListBooleanVal") Boolean masterList,
			@WebParam(name = "PackageWorkFlowStateBooleanVal") Boolean packageWorkFlowState,
			@WebParam(name = "TemplateTreeBooleanVal") Boolean TemplateTree)
			throws ServiceFault;

	@WebMethod(operationName = "SaveUpdateTemplate", action = "SaveUpdateTemplate")
	public @WebResult(name = "SaveResult")
	ServiceOperationResult saveUpdateTemplate(
			@WebParam(name = "PackageTemplateData", targetNamespace = "http://www.tmg.com/coreservices/data") PackageTemplateData data)
			throws ServiceFault;

	@WebMethod(operationName = "SaveUpdateAndDeleteAllowIntValForTemplate", action = "SaveUpdateAndDeleteAllowIntValForTemplate")
	public @WebResult(name = "SaveTemplateAllowsIntValData")
	ServiceOperationResult saveUpdateAndDeleteAllowIntValForTemplate(
			@WebParam(name = "TemplateAllowsIntValData", targetNamespace = "http://www.tmg.com/coreservices/data") List<TemplateAllowsIntValData> allowsIntVals,
			@WebParam(name = "TempId") int tempId,
			@WebParam(name = "MasterListId") int mastId) throws ServiceFault;

	@WebMethod(operationName = "SaveAndDeleteAllowLimitValForTemplate", action = "SaveAndDeleteAllowLimitValForTemplate")
	public @WebResult(name = "SaveTemplateAllowsLimitValData")
	ServiceOperationResult saveAndDeleteAllowLimitValForTemplate(
			@WebParam(name = "TemplateAllowsLimitValData", targetNamespace = "http://www.tmg.com/coreservices/data") List<TemplateAllowsLimitValData> allowsLimitVals,
			@WebParam(name = "TempId") int tempId) throws ServiceFault;

	@WebMethod(operationName = "SaveAndDeleteAllowServiceDefinitionForTemplate", action = "SaveAndDeleteAllowServiceDefinitionForTemplate")
	public @WebResult(name = "SaveTemplateAllowsServiceDefinitionData")
	ServiceOperationResult saveAndDeleteAllowServiceDefinitionForTemplate(
			@WebParam(name = "TemplateAllowsServiceDefinitionData", targetNamespace = "http://www.tmg.com/coreservices/data") List<TemplateAllowsServiceDefinitionData> allowServiceDefinitionList,
			@WebParam(name = "TempId") int tempId) throws ServiceFault;

	@WebMethod(operationName = "SaveAndDeleteAllowMessageValForTemplate", action = "SaveAndDeleteAllowMessageValForTemplate")
	public @WebResult(name = "SaveTemplateAllowsMessageValData")
	ServiceOperationResult saveAndDeleteAllowMessageValForTemplate(
			@WebParam(name = "AllowMessageValList", targetNamespace = "http://www.tmg.com/coreservices/data") List<TemplateAllowsMessageValData> allowMessageValList,
			@WebParam(name = "TempId") int tempId) throws ServiceFault;

	@WebMethod(operationName = "SaveUpdateAndDeleteAllowStringValForTemplate", action = "SaveUpdateAndDeleteAllowStringValForTemplate")
	public @WebResult(name = "SaveTemplateAllowsStringValData")
	ServiceOperationResult saveUpdateAndDeleteAllowStringValForTemplate(
			@WebParam(name = "TemplateAllowsStringValData", targetNamespace = "http://www.tmg.com/coreservices/data") List<TemplateAllowsStringValData> allowsStringValDatas,
			@WebParam(name = "TemplateId") int templateId,
			@WebParam(name = "MasterId") int mastId) throws ServiceFault;

	@WebMethod(operationName = "SaveAndUpdatePkgVerWorkflowStateForTemplate", action = "SaveAndUpdatePkgVerWorkflowStateForTemplate")
	public @WebResult(name = "SavePkgVersionWorkflowStateData")
	ServiceOperationResult saveAndUpdatePkgVerWorkflowStateForTemplate(
			@WebParam(name = "PkgVersionWorkflowStateData", targetNamespace = "http://www.tmg.com/coreservices/data") List<PkgVersionWorkflowStateData> workFlowStateData)
			throws ServiceFault;

	@WebMethod(operationName = "DeleteWorkFlowStateTemplate", action = "DeleteWorkFlowStateTemplate")
	public @WebResult(name = "DeleteWorkFlowState", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	ServiceOperationResult deleteWorkFlowStateTemplate(
			@WebParam(name = "Ids") List<Integer> ids) throws ServiceFault;

	@WebMethod(operationName = "GetBenefitPackageType", action = "GetBenefitPackageType")
	public @WebResult(name = "BenefitPackageTypes", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	List<String> getBenefitPackageType(@WebParam(name = "MasterListId") int id)
			throws ServiceFault;

	@WebMethod(operationName = "GetTemplateDetail", action = "GetTemplateDetail")
	public @WebResult(name = "TemplateDetailData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	PackageTemplateData getTemplateDetail(@WebParam(name = "TempId") int id)
			throws ServiceFault;

	@WebMethod(operationName = "GetTemplateWorkFlowStateById", action = "GetTemplateWorkFlowStateById")
	public @WebResult(name = "TemplateWorkflowStateData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateWorkflowState getTemplateWorkFlowStateById(
			@WebParam(name = "TemplateWorkflowStateId") int id)
			throws ServiceFault;
	
	@WebMethod(operationName = "GetAllTreeShapesForTemplateTree", action = "GetAllTreeShapesForTemplateTree")
	public @WebResult(name = "ShapeValueDefaultData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	List<ShapeValueDefaultData> getAllTreeShapesForTemplateTree(@WebParam(name = "TemplateTreeId") int id)
			throws ServiceFault;

}
