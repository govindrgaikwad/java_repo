package com.tmg.ebscore.dto.template;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemplateTree", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "TemplateTree", namespace = "http://www.tmg.com/coreservices/data")
public class TemplateTreeData extends CoreCommonAttributes {

	@XmlElement(name = "InUse")
	private Boolean inUse;

	@XmlElement(name = "IsLocked")
	private Boolean locked;

	@XmlElement(name = "PackageTemplate1up")
	private Integer packageTemplate1up;

	@XmlElement(name = "TemplateTree1up")
	private Integer templateTree1up;

	@XmlElement(name = "Name")
	private String name;

	@XmlElement(name = "Description")
	private String description;

	@XmlElement(name = "NoUsedInCommonBenefit")
	private Integer noUsedInCommonBenefit;
	
	@XmlElement(name = "GoXamXMLBlob")
	private String goXamXMLBlob;

	public Boolean getInUse() {
		return inUse;
	}

	public void setInUse(Boolean inUse) {
		this.inUse = inUse;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Integer getPackageTemplate1up() {
		return packageTemplate1up;
	}

	public void setPackageTemplate1up(Integer packageTemplate1up) {
		this.packageTemplate1up = packageTemplate1up;
	}

	public Integer getTemplateTree1up() {
		return templateTree1up;
	}
	
	

	public String getGoXamXMLBlob() {
		return goXamXMLBlob;
	}

	public void setGoXamXMLBlob(String goXamXMLBlob) {
		this.goXamXMLBlob = goXamXMLBlob;
	}

	public void setTemplateTree1up(Integer templateTree1up) {
		this.templateTree1up = templateTree1up;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNoUsedInCommonBenefit() {
		return noUsedInCommonBenefit;
	}

	public void setNoUsedInCommonBenefit(Integer noUsedInCommonBenefit) {
		this.noUsedInCommonBenefit = noUsedInCommonBenefit;
	}

}
