package com.tmg.ebscore.dto.packageversion;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageVerionContainer", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "PackageVerionContainer", namespace = "http://www.tmg.com/coreservices/data")
public class PackageVersionContainer<T> {

	@XmlElement(name = "PagesCount")
	private Integer pagesCount;

	@XmlElement(name = "TotalItems")
	private Long totalItems;

	@XmlElement(name = "ActionMessage")
	private String actionMessage;

	@XmlElement(name = "SearchMessage")
	private String searchMessage;

	@XmlElement(name = "Data")
	private List<T> data;

	public PackageVersionContainer() {

	}

	public PackageVersionContainer(Integer pagesCount, Long totalItems,
			String actionMessage, String searchMessage, List<T> data) {
		super();
		this.pagesCount = pagesCount;
		this.totalItems = totalItems;
		this.actionMessage = actionMessage;
		this.searchMessage = searchMessage;
		this.data = data;
	}

	public Integer getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount(Integer pagesCount) {
		this.pagesCount = pagesCount;
	}

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}

	public String getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}

	public String getSearchMessage() {
		return searchMessage;
	}

	public void setSearchMessage(String searchMessage) {
		this.searchMessage = searchMessage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	
}
