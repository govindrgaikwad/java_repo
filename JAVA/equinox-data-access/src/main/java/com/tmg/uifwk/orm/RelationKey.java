package com.tmg.uifwk.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "DM", name = "RelationKeys")
public class RelationKey {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RelationKeyID")
	private Long relationKeyID;

	@ManyToOne
	@JoinColumn(name = "RelationID")
	private ObjectRelation relation;

	@ManyToOne
	@JoinColumn(name = "LHSAttrID")
	private Attribute LHSAttribute;

	@ManyToOne
	@JoinColumn(name = "RHSAttrID")
	private Attribute RHSAttribute;

	public Long getRelationKeyID() {
		return relationKeyID;
	}

	public void setRelationKeyID(Long relationKeyID) {
		this.relationKeyID = relationKeyID;
	}

	public ObjectRelation getRelation() {
		return relation;
	}

	public void setRelation(ObjectRelation relation) {
		this.relation = relation;
	}

	public Attribute getLHSAttribute() {
		return LHSAttribute;
	}

	public void setLHSAttribute(Attribute lHSAttribute) {
		LHSAttribute = lHSAttribute;
	}

	public Attribute getRHSAttribute() {
		return RHSAttribute;
	}

	public void setRHSAttribute(Attribute rHSAttribute) {
		RHSAttribute = rHSAttribute;
	}

}
