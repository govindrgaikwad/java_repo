package com.tmg.ebscore.dto.packageversion;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "ServiceData", namespace = "http://www.tmg.com/coreservices/data")
public class ServiceData {

	@XmlElement(name = "ServiceType")
	private List<BenefitSelectionTierdataData> networkBenefits;

	public List<BenefitSelectionTierdataData> getNetworkBenefits() {
		return networkBenefits;
	}

	public void setNetworkBenefits(
			List<BenefitSelectionTierdataData> networkBenefits) {
		this.networkBenefits = networkBenefits;
	}

}
