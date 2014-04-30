package com.tmg.ebscore.dto.template;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tmg.ebscore.dto.CoreCommonAttributes;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdminContentDesign", namespace = "http://www.tmg.com/coreservices/data")
@XmlRootElement(name = "AdminContentDesign", namespace = "http://www.tmg.com/coreservices/data")
public class AdminContentDesignData extends CoreCommonAttributes {

	@XmlElement(name = "PackageTemplate1up")
	private Integer packageTemplate1up;

	@XmlElement(name = "AdminContentDesign1up")
	private Integer adminContentDesign1up;
	
	@XmlElement(name = "ProductTypeID")
	private Integer productTypeID;
	
	@XmlElement(name = "AdminContentDesignName")
	private String adminContentDesignName;
	
	@XmlElement(name = "DisplayText")
	private String displayText;
	
	@XmlElement(name = "IsActive")
	private Boolean Active;

	@XmlElement(name = "Sequence")
	private Boolean sequence;

	@XmlElement(name = "Title")
	private String title;

	@XmlElement(name = "LabelContent")
	private Boolean labelContent;

	public Integer getPackageTemplate1up() {
		return packageTemplate1up;
	}

	public void setPackageTemplate1up(Integer packageTemplate1up) {
		this.packageTemplate1up = packageTemplate1up;
	}
	
	public Integer getAdminContentDesign1up() {
		return adminContentDesign1up;
	}

	public void setAdminContentDesign1up(Integer adminContentDesign1up) {
		this.adminContentDesign1up = adminContentDesign1up;
	}

	public Integer getProductTypeID() {
		return productTypeID;
	}

	public void setProductTypeID(Integer productTypeID) {
		this.productTypeID = productTypeID;
	}

	public String getAdminContentDesignName() {
		return adminContentDesignName;
	}

	public void setAdminContentDesignName(String adminContentDesignName) {
		this.adminContentDesignName = adminContentDesignName;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public Boolean getActive() {
		return Active;
	}

	public void setActive(Boolean active) {
		Active = active;
	}

	public Boolean getSequence() {
		return sequence;
	}

	public void setSequence(Boolean sequence) {
		this.sequence = sequence;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getLabelContent() {
		return labelContent;
	}

	public void setLabelContent(Boolean labelContent) {
		this.labelContent = labelContent;
	}

}
