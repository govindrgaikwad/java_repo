package com.tmg.ebscorews.masterlist;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tmg.ebscore.dto.Level;
import com.tmg.ebscore.dto.ServiceOperationResult;
import com.tmg.ebscore.dto.masterlist.MasterListAccumulationValData;
import com.tmg.ebscore.dto.masterlist.MasterListContainer;
import com.tmg.ebscore.dto.masterlist.MasterListData;
import com.tmg.ebscore.dto.masterlist.MasterListIntValData;
import com.tmg.ebscore.dto.masterlist.MasterListLimitValData;
import com.tmg.ebscore.dto.masterlist.MasterListMessageValData;
import com.tmg.ebscore.dto.masterlist.MasterListServiceDefinitionData;
import com.tmg.ebscore.dto.masterlist.MasterListStringValData;
import com.tmg.ebscore.orm.DataAccessException;
import com.tmg.ebscorews.common.ServiceFault;

@Component("MasterListService")
@WebService(name = "MasterListService", serviceName = "MasterListService", endpointInterface = "com.tmg.ebscorews.masterlist.MasterListService", targetNamespace = "http://www.tmg.com/coreservices/MasterListService")
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class MasterServiceImpl implements MasterListService {

	private static Logger logger = Logger.getLogger(MasterServiceImpl.class);

	@Resource(name = "masterListService")
	private com.tmg.ebscore.service.MasterListService masterListService;

	@Override
	@WebMethod(operationName = "GetMasterList", action = "GetMasterList")
	public @WebResult(name = "MasterListData", targetNamespace = "http://www.tmg.com/coreservices/MasterListService")
	MasterListContainer<MasterListData> getMasterList(
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResult") int maxResults,
			@WebParam(name = "ShowInactive") boolean showInactive,
			@WebParam(name = "Level") Level level) throws ServiceFault {

		try {
			return masterListService.getMasterList(page, maxResults,
					showInactive, level);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetMasterListIntVal", action = "GetMasterListIntVal")
	public @WebResult(name = "MasterListIntValData", targetNamespace = "http://www.tmg.com/coreservices/MasterListService")
	MasterListContainer<MasterListIntValData> getMasterListIntVal(
			@WebParam(name = "MasterList1up") int id,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResult") int maxResults) throws ServiceFault {
		try {
			return masterListService.getMasterListIntVal(id, page, maxResults);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetMasterListStringVal", action = "GetMasterListStringVal")
	public @WebResult(name = "MasterListStringValData", targetNamespace = "http://www.tmg.com/coreservices/MasterListService")
	MasterListContainer<MasterListStringValData> getMasterListStringVal(
			@WebParam(name = "MasterList1up") int id,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResult") int maxResults) throws ServiceFault {
		try {
			return masterListService.getMasterListStringVal(id, page,
					maxResults);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetMasterListLimitVal", action = "GetMasterListLimitVal")
	public @WebResult(name = "MasterListLimitValData", targetNamespace = "http://www.tmg.com/coreservices/MasterListService")
	MasterListContainer<MasterListLimitValData> getMasterListLimitVal(
			@WebParam(name = "MasterList1up") int id,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResult") int maxResults) throws ServiceFault {
		try {
			return masterListService
					.getMasterListLimitVal(id, page, maxResults);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetMasterListMessageVal", action = "GetMasterListMessageVal")
	public @WebResult(name = "MasterListMessageValData", targetNamespace = "http://www.tmg.com/coreservices/MasterListService")
	MasterListContainer<MasterListMessageValData> getMasterListMessageVal(
			@WebParam(name = "MasterList1up") int id,
			@WebParam(name = "MessageType") String messageType,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResult") int maxResults) throws ServiceFault {
		try {
			return masterListService.getMasterListMessageVal(id, messageType,
					page, maxResults);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetMasterListServiceDefinition", action = "GetMasterListServiceDefinition")
	public @WebResult(name = "MasterListServiceDefinitionData", targetNamespace = "http://www.tmg.com/coreservices/MasterListService")
	MasterListContainer<MasterListServiceDefinitionData> getMasterListServiceDefinition(
			@WebParam(name = "MasterList1up") int id,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResult") int maxResults) throws ServiceFault {
		try {
			return masterListService.getMasterListServiceDefinition(id, page,
					maxResults);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetMasterListAccumulationVal", action = "GetMasterListAccumulationVal")
	public @WebResult(name = "MasterListAccumulationValData", targetNamespace = "http://www.tmg.com/coreservices/MasterListService")
	MasterListContainer<MasterListAccumulationValData> getMasterListAccumulationVal(
			@WebParam(name = "MasterList1up") List<Integer> id,
			@WebParam(name = "Page") int page,
			@WebParam(name = "MaxResult") int maxResults) throws ServiceFault {
		try {
			return masterListService.getMasterListAccumulationVal(id, page,
					maxResults);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}

	}

	@Override
	@WebMethod(operationName = "DeleteMasterListMessageVal", action = "DeleteMasterListMessageVal")
	public @WebResult(name = "DeleteResult", targetNamespace = "http://www.tmg.com/coreservices/MasterListService")
	ServiceOperationResult deleteMasterListMessageVal(
			@WebParam(name = "MasterListMessageVal1up") List<Integer> ids)
			throws ServiceFault {
		try {
			return masterListService.deleteMasterListMessageVal(ids);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Deleting value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "DeleteMasterListStringVal", action = "DeleteMasterListStringVal")
	public @WebResult(name = "DeleteResult", targetNamespace = "http://www.tmg.com/coreservices/MasterListService")
	ServiceOperationResult deleteMasterListStringVal(
			@WebParam(name = "MasterListStringVal1up") List<Integer> ids)
			throws ServiceFault {
		try {
			return masterListService.deleteMasterListStringVal(ids);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Deleting value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "DeleteMasterListIntVal", action = "DeleteMasterListIntVal")
	public @WebResult(name = "DeleteResult", targetNamespace = "http://www.tmg.com/coreservices/MasterListService")
	ServiceOperationResult deleteMasterListIntVal(
			@WebParam(name = "MasterListIntVal1up") List<Integer> ids)
			throws ServiceFault {
		try {
			return masterListService.deleteMasterListIntVal(ids);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Deleting value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "DeleteMasterListServiceDefinition", action = "DeleteMasterListServiceDefinition")
	public @WebResult(name = "DeleteResult", targetNamespace = "http://www.tmg.com/coreservices/MasterListService")
	ServiceOperationResult deleteMasterListServiceDefinition(
			@WebParam(name = "MasterListServiceDefinition1up") List<Integer> ids)
			throws ServiceFault {
		try {
			return masterListService.deleteMasterListServiceDefinition(ids);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Deleting value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "DeleteMasterListAccumulationVal", action = "DeleteMasterListAccumulationVal")
	public @WebResult(name = "DeleteResult", targetNamespace = "http://www.tmg.com/coreservices/MasterListService")
	ServiceOperationResult deleteMasterListAccumulationVal(
			@WebParam(name = "Accumulation1up") List<Integer> ids)
			throws ServiceFault {
		try {
			return masterListService.deleteMasterListAccumulationVal(ids);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Deleting value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "DeleteMasterListLimitVal", action = "DeleteMasterListLimitVal")
	public @WebResult(name = "DeleteResult", targetNamespace = "http://www.tmg.com/coreservices/MasterListService")
	ServiceOperationResult deleteMasterListLimitVal(
			@WebParam(name = "MasterListLimitVal1up") List<Integer> ids)
			throws ServiceFault {
		try {
			return masterListService.deleteMasterListLimitVal(ids);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Deleting value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveUpdateMasterListIntVal", action = "SaveUpdateMasterListIntVal")
	public @WebResult(name = "SaveResult")
	ServiceOperationResult saveUpdateMasterListIntVal(
			@WebParam(name = "MasterListIntValDataList", targetNamespace = "http://www.tmg.com/coreservices/data") List<MasterListIntValData> masterListIntValData)
			throws ServiceFault {
		try {
			return masterListService
					.saveUpdateMasterListIntVal(masterListIntValData);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveUpdateMasterListStringVal", action = "SaveUpdateMasterListStringVal")
	public @WebResult(name = "SaveResult")
	ServiceOperationResult saveUpdateMasterListStringVal(
			@WebParam(name = "MasterListStringValDataList", targetNamespace = "http://www.tmg.com/coreservices/data") List<MasterListStringValData> masterListStringValData)
			throws ServiceFault {
		try {
			return masterListService
					.saveUpdateMasterListStringVal(masterListStringValData);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveUpdateMasterListLimitVal", action = "SaveUpdateMasterListLimitVal")
	public @WebResult(name = "SaveResult")
	ServiceOperationResult saveUpdateMasterListLimitVal(
			@WebParam(name = "MasterListLimitValDataList", targetNamespace = "http://www.tmg.com/coreservices/data") List<MasterListLimitValData> masterListLimitValData)
			throws ServiceFault {
		try {
			return masterListService
					.saveUpdateMasterListLimitVal(masterListLimitValData);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveUpdateMasterListServiceDefinition", action = "SaveUpdateMasterListServiceDefinition")
	public @WebResult(name = "SaveResult")
	ServiceOperationResult saveUpdateMasterListServiceDefinition(
			@WebParam(name = "MasterListServiceDefinitionDataList", targetNamespace = "http://www.tmg.com/coreservices/data") List<MasterListServiceDefinitionData> masterListServiceDefinitionData)
			throws ServiceFault {
		try {
			return masterListService
					.saveUpdateMasterListServiceDefinition(masterListServiceDefinitionData);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveUpdateMasterListAccumulationVal", action = "SaveUpdateMasterListAccumulationVal")
	public @WebResult(name = "SaveResult")
	ServiceOperationResult saveUpdateMasterListAccumulationVal(
			@WebParam(name = "MasterListAccumulationValDataList", targetNamespace = "http://www.tmg.com/coreservices/data") List<MasterListAccumulationValData> masterListAccumulationValData)
			throws ServiceFault {
		try {
			return masterListService
					.saveUpdateMasterListAccumulationVal(masterListAccumulationValData);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "SaveUpdateMasterListMessageVal", action = "SaveUpdateMasterListMessageVal")
	public @WebResult(name = "SaveResult")
	ServiceOperationResult saveUpdateMasterListMessageVal(
			@WebParam(name = "MasterListMessageValDataList", targetNamespace = "http://www.tmg.com/coreservices/data") List<MasterListMessageValData> masterListMessageValData)
			throws ServiceFault {
		try {
			return masterListService
					.saveUpdateMasterListMessageVal(masterListMessageValData);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Saving value:" + e.getMessage(), e);
		}
	}

	@Override
	@WebMethod(operationName = "GetMasterListLimitValById", action = "GetMasterListLimitValById")
	public @WebResult(name = "LimitValData", targetNamespace = "http://www.tmg.com/coreservices/MasterListService")
	MasterListLimitValData getMasterListLimitValById(
			@WebParam(name = "MasterListLimitVal1up") int id)
			throws ServiceFault {
		try {
			return masterListService.getMasterListLimitValById(id);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetAllMasterListStringVal", action = "GetAllMasterListStringVal")
	public @WebResult(name = "MasterListAllStringValData", targetNamespace = "http://www.tmg.com/coreservices/MasterListService")
	List<MasterListStringValData> getAllMasterListStringVal(
			@WebParam(name = "MasterListStringVal1up") List<Integer> ids)
			throws ServiceFault {
		try {
			return masterListService.getAllMasterListStringVal(ids);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

	@Override
	@WebMethod(operationName = "GetAllMasterListIntVal", action = "GetAllMasterListIntVal")
	public @WebResult(name = "MasterListAllIntValData", targetNamespace = "http://www.tmg.com/coreservices/MasterListService")
	List<MasterListIntValData> getAllMasterListIntVal(
			@WebParam(name = "MasterListIntVal1up") List<Integer> ids)
			throws ServiceFault {
		try {
			return masterListService.getAllMasterListIntVal(ids);

		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceFault("Error Retrieving value:" + e.getMessage(),
					e);
		}
	}

}
