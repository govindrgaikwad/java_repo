package com.tmg.ebscore.dto.packageversion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PkgVerInstanceTreeData", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "PkgVerInstanceTreeData", namespace = "http://www.tmg.com/coreservices/data")
public class PkgVerInstanceTreeData extends CoreCommonAttributes {

	@XmlElement(name = "PkgVersion1up")
	private Integer pkgVersion1up;

	@XmlElement(name = "TemplateTree1up")
	private Integer templateTree1up;

	@XmlElement(name = "PkgVerInstanceTree1up")
	private Integer pkgVerInstanceTree1up;

	@XmlElement(name = "CommonBenefitName")
	private String name;

	@XmlElement(name = "CommonBenefitDescription")
	private String description;

	public Integer getPkgVersion1up() {
		return pkgVersion1up;
	}

	public void setPkgVersion1up(Integer pkgVersion1up) {
		this.pkgVersion1up = pkgVersion1up;
	}

	public Integer getTemplateTree1up() {
		return templateTree1up;
	}

	public void setTemplateTree1up(Integer templateTree1up) {
		this.templateTree1up = templateTree1up;
	}

	public Integer getPkgVerInstanceTree1up() {
		return pkgVerInstanceTree1up;
	}

	public void setPkgVerInstanceTree1up(Integer pkgVerInstanceTree1up) {
		this.pkgVerInstanceTree1up = pkgVerInstanceTree1up;
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

}
