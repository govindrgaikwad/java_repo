package com.tmg.ebscore.orm.packageversion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;
import com.tmg.ebscore.orm.ebspackage.UIElement;

@Entity
@Table(schema = "Nbd", name = "PackageVersionUIElementValue")
public class PackageVersionUIElementValue extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PackageVersionUIElementValue1up")
	private Integer packageVersionUIElementValue1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PackageVersion1up")
	private PkgVersion pkgVersion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UIElement1up")
	private UIElement uIElement;

	@Column(name = "UIElementValue")
	private String uIElementValue;

	@Column(name = "UIElementDataType1up")
	private String uIElementDataType1up;

	public Integer getPackageVersionUIElementValue1up() {
		return packageVersionUIElementValue1up;
	}

	public void setPackageVersionUIElementValue1up(
			Integer packageVersionUIElementValue1up) {
		this.packageVersionUIElementValue1up = packageVersionUIElementValue1up;
	}

	public PkgVersion getPkgVersion() {
		return pkgVersion;
	}

	public void setPkgVersion(PkgVersion pkgVersion) {
		this.pkgVersion = pkgVersion;
	}

	public UIElement getuIElement() {
		return uIElement;
	}

	public void setuIElement(UIElement uIElement) {
		this.uIElement = uIElement;
	}

	public String getuIElementValue() {
		return uIElementValue;
	}

	public void setuIElementValue(String uIElementValue) {
		this.uIElementValue = uIElementValue;
	}

	public String getuIElementDataType1up() {
		return uIElementDataType1up;
	}

	public void setuIElementDataType1up(String uIElementDataType1up) {
		this.uIElementDataType1up = uIElementDataType1up;
	}
	
	
}
