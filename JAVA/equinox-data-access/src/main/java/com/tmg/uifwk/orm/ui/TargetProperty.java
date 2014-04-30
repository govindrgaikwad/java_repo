package com.tmg.uifwk.orm.ui;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

/**
 * The persistent class for the TargetProperty database table.
 * 
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(schema = "UI", name = "TargetProperty")
public class TargetProperty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TargetPropertyID")
	private int targetPropertyID;

	@Column(name = "TargetPropertyName")
	private String targetPropertyName;

	// bi-directional many-to-one association to PropertyRuleMap
	@OneToMany(mappedBy = "targetProperty", fetch = FetchType.LAZY)
	private List<PropertyRuleMap> propertyRuleMaps;

	public TargetProperty() {
	}

	public int getTargetPropertyID() {
		return this.targetPropertyID;
	}

	public void setTargetPropertyID(int targetPropertyID) {
		this.targetPropertyID = targetPropertyID;
	}

	public String getTargetPropertyName() {
		return this.targetPropertyName;
	}

	public void setTargetPropertyName(String targetPropertyName) {
		this.targetPropertyName = targetPropertyName;
	}

	public List<PropertyRuleMap> getPropertyRuleMaps() {
		return this.propertyRuleMaps;
	}

	public void setPropertyRuleMaps(List<PropertyRuleMap> propertyRuleMaps) {
		this.propertyRuleMaps = propertyRuleMaps;
	}

	public PropertyRuleMap addPropertyRuleMap(PropertyRuleMap propertyRuleMap) {
		getPropertyRuleMaps().add(propertyRuleMap);
		propertyRuleMap.setTargetProperty(this);

		return propertyRuleMap;
	}

	public PropertyRuleMap removePropertyRuleMap(PropertyRuleMap propertyRuleMap) {
		getPropertyRuleMaps().remove(propertyRuleMap);
		propertyRuleMap.setTargetProperty(null);

		return propertyRuleMap;
	}

}