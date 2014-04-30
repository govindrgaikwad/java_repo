package com.tmg.ebscorews.template;

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
import com.tmg.ebscore.orm.template.TemplateWorkflowState;
import com.tmg.ebscorews.common.ServiceFault;

@Component("TemplateService")
@WebService(name = "TemplateService", serviceName = "TemplateService", endpointInterface = "com.tmg.ebscorews.template.TemplateService", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class TemplateServiceImpl implements TemplateService {

	private static Logger logger = Logger.getLogger(TemplateServiceImpl.class);

	@Resource(name = "templateService")
	private com.tmg.ebscore.service.TemplateService templateService;

	@Override
	@WebMethod(operationName = "GetTemplateList", action = "GetTemplateList")
	public @WebResult(name = "TemplateListData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<TemplateSummaryData> getTemplateList(
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResult") int maxResults)
			throws com.tmg.ebscorews.common.ServiceFault {

		try {
			return templateService.getTemplateList(page, maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new com.tmg.ebscorews.common.ServiceFault(
					"Error Retrieving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "DeleteTemplate", action = "DeleteTemplate")
	public @WebResult(name = "DeleteTemplateResult", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	ServiceOperationResult deleteTemplate(
			@WebParam(name = "TemplateId") int templateId) throws ServiceFault {
		try {
			return templateService.deleteTemplate(templateId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new com.tmg.ebscorews.common.ServiceFault(
					"Error Retrieving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "ChangeWorkFlowState", action = "ChangeWorkFlowState")
	public @WebResult(name = "SaveChangeWorkFlowState", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	ServiceOperationResult changeWorkFlowState(
			@WebParam(name = "TemplateWorkFlowid") int templateWorkFlowid,
			@WebParam(name = "PackageTemplateid") int packageTemplateid)
			throws ServiceFault {
		try {
			return templateService.changeWorkFlowState(templateWorkFlowid,
					packageTemplateid);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new com.tmg.ebscorews.common.ServiceFault(
					"Error Retrieving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "GetTemplateWorkFlowStateListAtTemplate", action = "GetTemplateWorkFlowStateListAtTemplate")
	public @WebResult(name = "TemplateWorkFlowStateData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<TemplateWorkFlowStateData> getTemplateWorkFlowStateListAtTemplate(
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults)
			throws com.tmg.ebscorews.common.ServiceFault {

		try {
			return templateService.getTemplateWorkFlowStateListAtTemplate(page,
					maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new com.tmg.ebscorews.common.ServiceFault(
					"Error Retrieving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "GetNoOFReleasedPackage", action = "GetNoOFReleasedPackage")
	public @WebResult(name = "NoOFReleasedPackages", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	int getNoOFReleasedPackage(@WebParam(name = "templateId") int tempId)
			throws ServiceFault {

		try {
			return templateService.getNoOFReleasedPackage(tempId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetNoOFUnReleasedPackage", action = "GetNoOFUnReleasedPackage")
	public @WebResult(name = "NoOFUnReleasedPackages", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	int getNoOFUnReleasedPackage(@WebParam(name = "templateId") int tempId)
			throws ServiceFault {
		try {
			return templateService.getNoOFUnReleasedPackage(tempId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetWorkFlowStateForEachTemplate", action = "GetWorkFlowStateForEachTemplate")
	public @WebResult(name = "PkgVersionWorkflowStateData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<PkgVersionWorkflowStateData> getWorkFlowStateForEachTemplate(
			@WebParam(name = "packageTemplateId") int id,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {
		try {
			return templateService.getWorkFlowStateForEachTemplate(id, page,
					maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetIntValListForTemplate", action = "GetIntValListForTemplate")
	public @WebResult(name = "TemplateAllowsIntValData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<TemplateAllowsIntValData> getIntValListForTemplate(
			@WebParam(name = "MasterListId") int masterListId,
			@WebParam(name = "TemplateId") int tempId,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {
		try {
			return templateService.getIntValListForTemplate(masterListId,
					tempId, page, maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetStringValListForTemplate", action = "GetStringValListForTemplate")
	public @WebResult(name = "TemplateAllowsStringValData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<TemplateAllowsStringValData> getStringValListForTemplate(
			@WebParam(name = "MasterListId") int masterListId,
			@WebParam(name = "TempId") int tempId,
			@WebParam(name = "page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {
		try {
			return templateService.getStringValListForTemplate(masterListId,
					tempId, page, maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetMessageValListForTemplate", action = "GetMessageValListForTemplate")
	public @WebResult(name = "TemplateAllowsMessageValData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<TemplateAllowsMessageValData> getMessageValListForTemplate(
			@WebParam(name = "MasterListId") int masterListId,
			@WebParam(name = "TempId") int tempId,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {

		try {
			return templateService.getMessageValListForTemplate(masterListId,
					tempId, page, maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}

	}

	@Override
	@WebMethod(operationName = "GetLimitValListForTemplate", action = "GetLimitValListForTemplate")
	public @WebResult(name = "TemplateAllowsLimitValData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<TemplateAllowsLimitValData> getLimitValListForTemplate(
			@WebParam(name = "MasterListId") int masterListId,
			@WebParam(name = "TempId") int tempId,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {

		try {
			return templateService.getLimitValListForTemplate(masterListId,
					tempId, page, maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}

	}

	@Override
	@WebMethod(operationName = "GetServiceDefinitionValListForTemplate", action = "GetServiceDefinitionValListForTemplate")
	public @WebResult(name = "TemplateAllowsServiceDefinitionData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<TemplateAllowsServiceDefinitionData> getServiceDefinitionValListForTemplate(
			@WebParam(name = "MasterListId") int masterListId,
			@WebParam(name = "TempId") int tempId,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {
		try {
			return templateService.getServiceDefinitionValListForTemplate(
					masterListId, tempId, page, maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}

	}

	@Override
	@WebMethod(operationName = "GetAllTemplateTreeForTemplate", action = "GetAllTemplateTreeForTemplate")
	public @WebResult(name = "TemplateTreeData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<TemplateTreeData> getAllTemplateTreeForTemplate(
			@WebParam(name = "Id") int id, @WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {
		try {
			return templateService.getAllTemplateTreeForTemplate(id, page,
					maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveTemplateFromExistingTemplate", action = "SaveTemplateFromExistingTemplate")
	public @WebResult(name = "SaveTemplateFromExistingTemplate", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	ServiceOperationResult saveTemplateFromExistingTemplate(
			@WebParam(name = "DataToBeSaved") PackageTemplateData data,
			@WebParam(name = "MasterListBooleanVal") Boolean masterList,
			@WebParam(name = "PackageWorkFlowStateBooleanVal") Boolean packageWorkFlowState,
			@WebParam(name = "TemplateTreeBooleanVal") Boolean TemplateTree)
			throws ServiceFault {
		try {
			return templateService.saveTemplateFromExistingTemplate(data,
					masterList, packageWorkFlowState, TemplateTree);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveUpdateTemplate", action = "SaveUpdateTemplate")
	public @WebResult(name = "SaveAndUpdateTemplate", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	ServiceOperationResult saveUpdateTemplate(
			@WebParam(name = "PackageTemplateData", targetNamespace = "http://www.tmg.com/coreservices/data") PackageTemplateData data)
			throws ServiceFault {
		try {
			return templateService.saveUpdateTemplate(data);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveUpdateAndDeleteAllowIntValForTemplate", action = "SaveUpdateAndDeleteAllowIntValForTemplate")
	public @WebResult(name = "SaveTemplateAllowsIntValData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	ServiceOperationResult saveUpdateAndDeleteAllowIntValForTemplate(
			@WebParam(name = "TemplateAllowsIntValData", targetNamespace = "http://www.tmg.com/coreservices/data") List<TemplateAllowsIntValData> allowsIntVals,
			@WebParam(name = "TempId") int tempId,
			@WebParam(name = "MasterListId") int mastId) throws ServiceFault {
		try {
			return templateService.saveUpdateAndDeleteAllowIntValForTemplate(
					allowsIntVals, tempId, mastId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveAndDeleteAllowLimitValForTemplate", action = "SaveAndDeleteAllowLimitValForTemplate")
	public @WebResult(name = "SaveTemplateAllowsLimitValData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	ServiceOperationResult saveAndDeleteAllowLimitValForTemplate(
			@WebParam(name = "TemplateAllowsLimitValData", targetNamespace = "http://www.tmg.com/coreservices/data") List<TemplateAllowsLimitValData> allowsLimitVals,
			@WebParam(name = "TempId") int tempId) throws ServiceFault {
		try {
			return templateService.saveAndDeleteAllowLimitValForTemplate(
					allowsLimitVals, tempId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveAndDeleteAllowServiceDefinitionForTemplate", action = "SaveAndDeleteAllowServiceDefinitionForTemplate")
	public @WebResult(name = "TemplateAllowsServiceDefinitionData")
	ServiceOperationResult saveAndDeleteAllowServiceDefinitionForTemplate(
			@WebParam(name = "TemplateAllowsServiceDefinitionData", targetNamespace = "http://www.tmg.com/coreservices/data") List<TemplateAllowsServiceDefinitionData> allowServiceDefinitionList,
			@WebParam(name = "TempId") int tempId) throws ServiceFault {
		try {
			return templateService
					.saveAndDeleteAllowServiceDefinitionForTemplate(
							allowServiceDefinitionList, tempId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveAndDeleteAllowMessageValForTemplate", action = "SaveAndDeleteAllowMessageValForTemplate")
	public @WebResult(name = "TemplateAllowsMessageValData")
	ServiceOperationResult saveAndDeleteAllowMessageValForTemplate(
			@WebParam(name = "TemplateAllowsMessageValData", targetNamespace = "http://www.tmg.com/coreservices/data") List<TemplateAllowsMessageValData> allowMessageValList,
			@WebParam(name = "TempId") int tempId) throws ServiceFault {
		try {
			return templateService.saveAndDeleteAllowMessageValForTemplate(
					allowMessageValList, tempId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveUpdateAndDeleteAllowStringValForTemplate", action = "SaveUpdateAndDeleteAllowStringValForTemplate")
	public @WebResult(name = "SaveTemplateAllowsStringValData")
	ServiceOperationResult saveUpdateAndDeleteAllowStringValForTemplate(
			@WebParam(name = "TemplateAllowsStringValData", targetNamespace = "http://www.tmg.com/coreservices/data") List<TemplateAllowsStringValData> allowsStringValDatas,
			@WebParam(name = "TemplateId") int templateId,
			@WebParam(name = "MasterId") int mastId) throws ServiceFault {
		try {
			return templateService
					.saveUpdateAndDeleteAllowStringValForTemplate(
							allowsStringValDatas, templateId, mastId);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveAndUpdatePkgVerWorkflowStateForTemplate", action = "SaveAndUpdatePkgVerWorkflowStateForTemplate")
	public @WebResult(name = "SavePkgVersionWorkflowStateData")
	ServiceOperationResult saveAndUpdatePkgVerWorkflowStateForTemplate(
			@WebParam(name = "PkgVersionWorkflowStateData", targetNamespace = "http://www.tmg.com/coreservices/data") List<PkgVersionWorkflowStateData> workFlowStateData)
			throws ServiceFault {
		try {
			return templateService
					.saveAndUpdatePkgVerWorkflowStateForTemplate(workFlowStateData);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "DeleteWorkFlowStateTemplate", action = "DeleteWorkFlowStateTemplate")
	public @WebResult(name = "DeleteWorkFlowState", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	ServiceOperationResult deleteWorkFlowStateTemplate(
			@WebParam(name = "Ids") List<Integer> ids) throws ServiceFault {
		try {
			return templateService.deleteWorkFlowStateTemplate(ids);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetPackagesForTemplate", action = "GetPackagesForTemplate")
	public @WebResult(name = "GetBenifitPackageData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateContainer<BenifitPackageData> getPackagesForTemplate(
			@WebParam(name = "TempId") int id,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResults") int maxResults) throws ServiceFault {
		try {
			return templateService.getPackagesForTemplate(id, page, maxResults);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetBenefitPackageType", action = "GetBenefitPackageType")
	public @WebResult(name = "BenefitPackageTypes", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	List<String> getBenefitPackageType(@WebParam(name = "MasterListId") int id)
			throws ServiceFault {
		try {
			return templateService.getBenefitPackageType(id);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetTemplateDetail", action = "GetTemplateDetail")
	public @WebResult(name = "TemplateDetailData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	PackageTemplateData getTemplateDetail(@WebParam(name = "TempId") int id)
			throws ServiceFault {
		try {
			return templateService.getTemplateDetail(id);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetTemplateWorkFlowStateById", action = "GetTemplateWorkFlowStateById")
	public @WebResult(name = "TemplateWorkflowStateData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	TemplateWorkflowState getTemplateWorkFlowStateById(
			@WebParam(name = "TemplateWorkflowStateId") int id)
			throws ServiceFault {
		try {
			return templateService.getTemplateWorkFlowStateById(id);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetAllTreeShapesForTemplateTree", action = "GetAllTreeShapesForTemplateTree")
	public @WebResult(name = "ShapeValueDefaultData", targetNamespace = "http://www.tmg.com/coreservices/TemplateService")
	List<ShapeValueDefaultData> getAllTreeShapesForTemplateTree(
			@WebParam(name = "TemplateTreeId") int id) throws ServiceFault {
		try {
			return templateService.getAllTreeShapesForTemplateTree(id);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

}
