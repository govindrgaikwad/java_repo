package com.tmg.uifwk.orm.ui;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.tmg.uifwk.orm.BaseEntityAttributes;

/**
 * The persistent class for the UIElement database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "UIElement")
@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
@Table(schema = "UI", name = "UIElement")
@Inheritance(strategy = InheritanceType.JOINED)
public class UIElement extends BaseEntityAttributes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5255588221043230613L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UIElementID")
	private Integer UIElementID;

	@Column(name = "UIElementName")
	private String UIElementName;

	@Column(name = "Label")
	private String elementLabel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ParentUIElementID")
	private UIElement parentUIElement;

	@Column(name = "IsContainer")
	private boolean container;

	@Column(name = "Enabled")
	private boolean enabled;

	@Column(name = "Visible")
	private boolean visible;

	@Column(name = "Sequence")
	private int sequence;

	@Column(name = "RequiresValidation")
	private boolean requiresValidation;

	@Column(name = "HelpText")
	private String helpText;

	@Column(name = "IsActive")
	private boolean active;

	// bi-directional many-to-one association to FormDesign
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FormID")
	private FormDesign formDesign;

	// bi-directional many-to-one association to ApplicationDataType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UIElementDataTypeID")
	private ApplicationDataType applicationDataType;

	@Column(name = "HasCustomRule")
	private boolean hasCustomRule;

	@Column(name = "GeneratedName")
	private String generatedName;

	// bi-directional many-to-one association to UIElement

	// bi-directional many-to-one association to FormDesignVersionUIElementMap
	@OneToMany(mappedBy = "uielement", fetch = FetchType.LAZY)
	private List<FormDesignVersionUIElementMap> formDesignVersionUielementMaps;

	// bi-directional many-to-one association to PropertyRuleMap
	@OneToMany(mappedBy = "uielement", fetch = FetchType.LAZY)
	private List<PropertyRuleMap> propertyRuleMaps;

	// bi-directional many-to-one association to UIElement
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "UIElement")
	@OneToMany(mappedBy = "parentUIElement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UIElement> childElements;

	// bi-directional many-to-one association to Validator
	@OneToMany(mappedBy = "uielement", fetch = FetchType.LAZY)
	private List<Validator> validators;

	// // bi-directional many-to-one association to DataSourceMapping
	// @OneToMany(mappedBy = "dataSourceForUIElement")
	// private List<DataSourceMapping> dataSourceMappings1;

	// bi-directional many-to-one association to DataSourceMapping
	@OneToMany(mappedBy = "UIElementsInDataSource", fetch = FetchType.LAZY)
	private List<DataSourceMapping> UIElementsInDataSource;

	public UIElement() {
	}

	public Integer getUIElementID() {
		return this.UIElementID;
	}

	public void setUIElementID(Integer UIElementID) {
		this.UIElementID = UIElementID;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean getHasCustomRule() {
		return this.hasCustomRule;
	}

	public void setHasCustomRule(boolean hasCustomRule) {
		this.hasCustomRule = hasCustomRule;
	}

	public String getHelpText() {
		return this.helpText;
	}

	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isContainer() {
		return container;
	}

	public void setContainer(boolean container) {
		this.container = container;
	}

	public String getElementLabel() {
		return elementLabel;
	}

	public void setElementLabel(String elementLabel) {
		this.elementLabel = elementLabel;
	}

	public boolean getRequiresValidation() {
		return this.requiresValidation;
	}

	public void setRequiresValidation(boolean requiresValidation) {
		this.requiresValidation = requiresValidation;
	}

	public int getSequence() {
		return this.sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getUIElementName() {
		return this.UIElementName;
	}

	public void setUIElementName(String UIElementName) {
		this.UIElementName = UIElementName;
	}

	public boolean getVisible() {
		return this.visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public List<DataSourceMapping> getUIElementsInDataSource() {
		return UIElementsInDataSource;
	}

	public void setUIElementsInDataSource(
			List<DataSourceMapping> uIElementsInDataSource) {
		UIElementsInDataSource = uIElementsInDataSource;
	}

	public DataSourceMapping addDataSourceMappings2(
			DataSourceMapping uIElementsInDataSource) {
		getUIElementsInDataSource().add(uIElementsInDataSource);
		uIElementsInDataSource.setUIElementsInDataSource(this);

		return uIElementsInDataSource;
	}

	public DataSourceMapping removeDataSourceMappings2(
			DataSourceMapping uIElementsInDataSource) {
		getUIElementsInDataSource().remove(uIElementsInDataSource);
		uIElementsInDataSource.setUIElementsInDataSource(null);

		return uIElementsInDataSource;
	}

	public List<FormDesignVersionUIElementMap> getFormDesignVersionUielementMaps() {
		return this.formDesignVersionUielementMaps;
	}

	public void setFormDesignVersionUielementMaps(
			List<FormDesignVersionUIElementMap> formDesignVersionUielementMaps) {
		this.formDesignVersionUielementMaps = formDesignVersionUielementMaps;
	}

	public FormDesignVersionUIElementMap addFormDesignVersionUielementMap(
			FormDesignVersionUIElementMap formDesignVersionUielementMap) {
		getFormDesignVersionUielementMaps().add(formDesignVersionUielementMap);
		formDesignVersionUielementMap.setUielement(this);

		return formDesignVersionUielementMap;
	}

	public FormDesignVersionUIElementMap removeFormDesignVersionUielementMap(
			FormDesignVersionUIElementMap formDesignVersionUielementMap) {
		getFormDesignVersionUielementMaps().remove(
				formDesignVersionUielementMap);
		formDesignVersionUielementMap.setUielement(null);

		return formDesignVersionUielementMap;
	}

	public List<PropertyRuleMap> getPropertyRuleMaps() {
		return this.propertyRuleMaps;
	}

	public void setPropertyRuleMaps(List<PropertyRuleMap> propertyRuleMaps) {
		this.propertyRuleMaps = propertyRuleMaps;
	}

	public PropertyRuleMap addPropertyRuleMap(PropertyRuleMap propertyRuleMap) {
		getPropertyRuleMaps().add(propertyRuleMap);
		propertyRuleMap.setUielement(this);

		return propertyRuleMap;
	}

	public PropertyRuleMap removePropertyRuleMap(PropertyRuleMap propertyRuleMap) {
		getPropertyRuleMaps().remove(propertyRuleMap);
		propertyRuleMap.setUielement(null);

		return propertyRuleMap;
	}

	public ApplicationDataType getApplicationDataType() {
		return this.applicationDataType;
	}

	public void setApplicationDataType(ApplicationDataType applicationDataType) {
		this.applicationDataType = applicationDataType;
	}

	public FormDesign getFormDesign() {
		return this.formDesign;
	}

	public void setFormDesign(FormDesign formDesign) {
		this.formDesign = formDesign;
	}

	public UIElement getParentUIElement() {
		return parentUIElement;
	}

	public void setParentUIElement(UIElement parentUIElement) {
		this.parentUIElement = parentUIElement;
	}

	public List<UIElement> getChildElements() {
		return childElements;
	}

	public void setChildElements(List<UIElement> childElements) {
		this.childElements = childElements;
	}

	public UIElement addChildElement(UIElement uielement) {
		getChildElements().add(uielement);
		uielement.setParentUIElement(this);

		return uielement;
	}

	public UIElement removeChildElement(UIElement uielement) {
		getChildElements().remove(uielement);
		uielement.setParentUIElement(null);

		return uielement;
	}

	public List<Validator> getValidators() {
		return this.validators;
	}

	public void setValidators(List<Validator> validators) {
		this.validators = validators;
	}

	public Validator addValidator(Validator validator) {
		getValidators().add(validator);
		validator.setUielement(this);

		return validator;
	}

	public Validator removeValidator(Validator validator) {
		getValidators().remove(validator);
		validator.setUielement(null);

		return validator;
	}

	public String getGeneratedName() {
		return generatedName;
	}

	public void setGeneratedName(String generatedName) {
		this.generatedName = generatedName;
	}

}