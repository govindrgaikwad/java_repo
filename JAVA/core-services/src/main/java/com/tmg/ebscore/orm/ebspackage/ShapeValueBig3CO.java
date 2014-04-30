package com.tmg.ebscore.orm.ebspackage;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;
import com.tmg.ebscore.orm.masterlist.MasterListIntVal;
import com.tmg.ebscore.orm.masterlist.MasterListStringVal;

@Entity
@Table(schema = "Pkg", name = "ShapeValueBig3CO")
public class ShapeValueBig3CO extends CoreCommonAttributes {

	@EmbeddedId
	private ShapeValueBig3COId shapeValueBig3COId;

	@Column(name = "IfYNTestAnswerYesFlag")
	private Boolean ifYNTestAnswerYesFlag;

	@Column(name = "IfYNTestAnswerNoFlag")
	private Boolean ifYNTestAnswerNoFlag;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SetValueValueAsInt1up")
	private MasterListIntVal masterListIntVal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SetValueValueAsString1up")
	private MasterListStringVal masterListStringVal;

	@Column(name = "AltRule")
	private String altRule;

	public ShapeValueBig3COId getShapeValueBig3COId() {
		return shapeValueBig3COId;
	}

	public void setShapeValueBig3COId(ShapeValueBig3COId shapeValueBig3COId) {
		this.shapeValueBig3COId = shapeValueBig3COId;
	}

	public Boolean getIfYNTestAnswerYesFlag() {
		return ifYNTestAnswerYesFlag;
	}

	public void setIfYNTestAnswerYesFlag(Boolean ifYNTestAnswerYesFlag) {
		this.ifYNTestAnswerYesFlag = ifYNTestAnswerYesFlag;
	}

	public Boolean getIfYNTestAnswerNoFlag() {
		return ifYNTestAnswerNoFlag;
	}

	public void setIfYNTestAnswerNoFlag(Boolean ifYNTestAnswerNoFlag) {
		this.ifYNTestAnswerNoFlag = ifYNTestAnswerNoFlag;
	}

	public MasterListIntVal getMasterListIntVal() {
		return masterListIntVal;
	}

	public void setMasterListIntVal(MasterListIntVal masterListIntVal) {
		this.masterListIntVal = masterListIntVal;
	}

	public MasterListStringVal getMasterListStringVal() {
		return masterListStringVal;
	}

	public void setMasterListStringVal(MasterListStringVal masterListStringVal) {
		this.masterListStringVal = masterListStringVal;
	}

	public String getAltRule() {
		return altRule;
	}

	public void setAltRule(String altRule) {
		this.altRule = altRule;
	}

}
