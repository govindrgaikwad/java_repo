package com.tmg.ebscore.dto.packageversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkServiceData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "NetworkServiceData", namespace = "http://www.tmg.com/coreservices/data")
public class NetworkServiceData {

	@XmlElement(name = "NetworkDetails")
	private PkgVerInstanceTreeData PkgVerInstanceTreeData;

	@XmlElement(name = "DefaultData")
	private BenefitSelectionTierdataData defaultData;

	@XmlElement(name = "ServiceData")
	private ServiceData serviceData;

	public PkgVerInstanceTreeData getPkgVerInstanceTreeData() {
		return PkgVerInstanceTreeData;
	}

	public void setPkgVerInstanceTreeData(
			PkgVerInstanceTreeData pkgVerInstanceTreeData) {
		PkgVerInstanceTreeData = pkgVerInstanceTreeData;
	}

	public ServiceData getServiceData() {
		return serviceData;
	}

	public void setServiceData(ServiceData serviceData) {
		this.serviceData = serviceData;
	}

	public BenefitSelectionTierdataData getDefaultData() {
		return defaultData;
	}

	public void setDefaultData(BenefitSelectionTierdataData defaultData) {
		this.defaultData = defaultData;
	}

}
