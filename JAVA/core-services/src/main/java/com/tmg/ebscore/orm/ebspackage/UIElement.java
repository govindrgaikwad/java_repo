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

@Entity
@Table(schema = "Nbd", name = "UIElement")
public class UIElement extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UIElement1up")
	private Integer uIElement1up;

	@Column(name = "UIElementName")
	private String uIElementName;

	@Column(name = "Label")
	private String label;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ParentUIElement1up")
	private UIElement uIElement;

	@Column(name = "IsContainer")
	private Boolean container;

	@Column(name = "Enabled")
	private Boolean enabled;

	@Column(name = "Visible")
	private Boolean visible;

	@Column(name = "Sequence")
	private Integer sequence;

	@Column(name = "RequiresValidation")
	private Boolean requiresValidation;

	@Column(name = "HelpText")
	private String helpText;

	@Column(name = "IsActive")
	private Boolean active;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AdminContentDesign1up")
	private AdminContentDesign adminContentDesign;

	@Column(name = "UIElementDataTypeID")
	private Integer uIElementDataTypeID;

	@Column(name = "IsDeleted")
	private Boolean deleted;

	@Column(name = "AllowBenefitOptionOverride")
	private Boolean allowBenefitOptionOverride;

	@OneToMany(mappedBy = "uIElement", fetch = FetchType.LAZY)
	private Set<UIElement> uiAdmin = new HashSet<UIElement>();

	public Set<UIElement> getUiAdmin() {
		return uiAdmin;
	}

	public void setUiAdmin(Set<UIElement> uiAdmin) {
		this.uiAdmin = uiAdmin;
	}

	public Integer getuIElement1up() {
		return uIElement1up;
	}

	public void setuIElement1up(Integer uIElement1up) {
		this.uIElement1up = uIElement1up;
	}

	public String getuIElementName() {
		return uIElementName;
	}

	public void setuIElementName(String uIElementName) {
		this.uIElementName = uIElementName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public UIElement getuIElement() {
		return uIElement;
	}

	public void setuIElement(UIElement uIElement) {
		this.uIElement = uIElement;
	}

	public Boolean getContainer() {
		return container;
	}

	public void setContainer(Boolean container) {
		this.container = container;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Boolean getRequiresValidation() {
		return requiresValidation;
	}

	public void setRequiresValidation(Boolean requiresValidation) {
		this.requiresValidation = requiresValidation;
	}

	public String getHelpText() {
		return helpText;
	}

	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public AdminContentDesign getAdminContentDesign() {
		return adminContentDesign;
	}

	public void setAdminContentDesign(AdminContentDesign adminContentDesign) {
		this.adminContentDesign = adminContentDesign;
	}

	public Integer getuIElementDataTypeID() {
		return uIElementDataTypeID;
	}

	public void setuIElementDataTypeID(Integer uIElementDataTypeID) {
		this.uIElementDataTypeID = uIElementDataTypeID;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Boolean getAllowBenefitOptionOverride() {
		return allowBenefitOptionOverride;
	}

	public void setAllowBenefitOptionOverride(Boolean allowBenefitOptionOverride) {
		this.allowBenefitOptionOverride = allowBenefitOptionOverride;
	}

}
