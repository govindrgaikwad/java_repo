package com.tmg.ebscore.dto.packageversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TierData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "TierData", namespace = "http://www.tmg.com/coreservices/data")
public class TierData {

	@XmlElement(name = "CoInsurance")
	private Integer coInsurance;

	@XmlElement(name = "CoPay")
	private Integer coPay;

	@XmlElement(name = "AllowedAmount")
	private Integer allowedAmount;

	@XmlElement(name = "AllowedCtrl")
	private Integer allowedCtrl;

	public Integer getCoInsurance() {
		return coInsurance;
	}

	public void setCoInsurance(Integer coInsurance) {
		this.coInsurance = coInsurance;
	}

	public Integer getCoPay() {
		return coPay;
	}

	public void setCoPay(Integer coPay) {
		this.coPay = coPay;
	}

	public Integer getAllowedAmount() {
		return allowedAmount;
	}

	public void setAllowedAmount(Integer allowedAmount) {
		this.allowedAmount = allowedAmount;
	}

	public Integer getAllowedCtrl() {
		return allowedCtrl;
	}

	public void setAllowedCtrl(Integer allowedCtrl) {
		this.allowedCtrl = allowedCtrl;
	}

}
