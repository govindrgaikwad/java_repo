package com.tmg.ebscore.orm.ebspackage;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;
import com.tmg.ebscore.orm.template.PackageTemplate;

@Entity
@Table(schema = "Nbd", name = "AdminContentDesign")
public class AdminContentDesign extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AdminContentDesign1up")
	private Integer adminContentDesign1up;

	@Column(name = "ProductTypeID")
	private Integer productTypeID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PackageTemplate1up")
	private PackageTemplate packageTemplate;

	@Column(name = "AdminContentDesignName")
	private String adminContentDesignName;

	@Column(name = "DisplayText")
	private String displayText;

	@Column(name = "IsActive")
	private Boolean active;

	@OneToMany(mappedBy = "adminContentDesign", fetch = FetchType.LAZY)
	private Set<UIElement> uiAdmin = new HashSet<UIElement>();

	public Set<UIElement> getUiAdmin() {
		return uiAdmin;
	}

	public void setUiAdmin(Set<UIElement> uiAdmin) {
		this.uiAdmin = uiAdmin;
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

	public PackageTemplate getPackageTemplate() {
		return packageTemplate;
	}

	public void setPackageTemplate(PackageTemplate packageTemplate) {
		this.packageTemplate = packageTemplate;
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
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
