package com.tmg.ebscore.dto.packageversion;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductServiceData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "ProductServiceData", namespace = "http://www.tmg.com/coreservices/data")
public class ProductServiceData {

	@XmlElement(name = "ProductId")
	private Integer productId;

	@XmlElement(name = "Network")
	private List<NetworkServiceData> networkBenefit;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public List<NetworkServiceData> getNetworkBenefit() {
		return networkBenefit;
	}

	public void setNetworkBenefit(List<NetworkServiceData> networkBenefit) {
		this.networkBenefit = networkBenefit;
	}

}
