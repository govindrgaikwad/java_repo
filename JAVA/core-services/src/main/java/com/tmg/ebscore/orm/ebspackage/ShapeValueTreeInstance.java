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
@Table(schema = "Pkg", name = "ShapeValueTreeInstance")
public class ShapeValueTreeInstance extends CoreCommonAttributes {

	@EmbeddedId
	private ShapeValueTreeInstanceId shapeValueTreeInstanceId;

	@Column(name = "IsOverrideAllowedFlag")
	private Boolean overrideAllowedFlag;

	@Column(name = "Note2")
	private String note2;

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

	public ShapeValueTreeInstanceId getShapeValueTreeInstanceId() {
		return shapeValueTreeInstanceId;
	}

	public void setShapeValueTreeInstanceId(
			ShapeValueTreeInstanceId shapeValueTreeInstanceId) {
		this.shapeValueTreeInstanceId = shapeValueTreeInstanceId;
	}

	public Boolean getOverrideAllowedFlag() {
		return overrideAllowedFlag;
	}

	public void setOverrideAllowedFlag(Boolean overrideAllowedFlag) {
		this.overrideAllowedFlag = overrideAllowedFlag;
	}

	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
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

}
