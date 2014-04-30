package com.tmg.ebscore.orm.masterlist;

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
import com.tmg.ebscore.orm.ebspackage.MatrixShapeBig3COMessageXXref;
import com.tmg.ebscore.orm.ebspackage.MessageShapeBenefitOptionOverride;
import com.tmg.ebscore.orm.template.MatrixShapeMessageXref;
import com.tmg.ebscore.orm.template.TemplateAllowsMessageVal;

@Entity
@Table(schema = "Mast", name = "MasterListMessageVal")
public class MasterListMessageVal extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MasterListMessageVal1up")
	private Integer masterListMessageVal1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterList1Up")
	private MasterList masterListMessage;

	@Column(name = "MessageCategory")
	private String messageCategory;

	@Column(name = "MessageValue")
	private String messageValue;

	@Column(name = "MessageType")
	private String messageType;

	@OneToMany(mappedBy = "matrixShapeMessageXrefId.masterListMessageVal", fetch = FetchType.LAZY)
	private Set<MatrixShapeMessageXref> masterMessageXref = new HashSet<MatrixShapeMessageXref>();

	@OneToMany(mappedBy = "templateAllowsMessageValId.masterListMessageVal", fetch = FetchType.LAZY)
	private Set<TemplateAllowsMessageVal> templateAllowMessage = new HashSet<TemplateAllowsMessageVal>();

	@OneToMany(mappedBy = "matrixShapeBig3COMessageXXrefId.masterListMessageVal", fetch = FetchType.LAZY)
	private Set<MatrixShapeBig3COMessageXXref> treeShapeMessageValXref = new HashSet<MatrixShapeBig3COMessageXXref>();

	@OneToMany(mappedBy = "messageShapeBenefitOptionOverrideId.matrixShapeBig3COMessageXXrefId.masterListMessageVal", fetch = FetchType.LAZY)
	private Set<MessageShapeBenefitOptionOverride> treeShapeMessageOption = new HashSet<MessageShapeBenefitOptionOverride>();

	public Set<MessageShapeBenefitOptionOverride> getTreeShapeMessageOption() {
		return treeShapeMessageOption;
	}

	public void setTreeShapeMessageOption(
			Set<MessageShapeBenefitOptionOverride> treeShapeMessageOption) {
		this.treeShapeMessageOption = treeShapeMessageOption;
	}

	public Set<MatrixShapeBig3COMessageXXref> getTreeShapeMessageValXref() {
		return treeShapeMessageValXref;
	}

	public void setTreeShapeMessageValXref(
			Set<MatrixShapeBig3COMessageXXref> treeShapeMessageValXref) {
		this.treeShapeMessageValXref = treeShapeMessageValXref;
	}

	public Set<TemplateAllowsMessageVal> getTemplateAllowMessage() {
		return templateAllowMessage;
	}

	public void setTemplateAllowMessage(
			Set<TemplateAllowsMessageVal> templateAllowMessage) {
		this.templateAllowMessage = templateAllowMessage;
	}

	public Set<MatrixShapeMessageXref> getMasterMessageXref() {
		return masterMessageXref;
	}

	public void setMasterMessageXref(
			Set<MatrixShapeMessageXref> masterMessageXref) {
		this.masterMessageXref = masterMessageXref;
	}

	public Integer getMasterListMessageVal1up() {
		return masterListMessageVal1up;
	}

	public void setMasterListMessageVal1up(Integer masterListMessageVal1up) {
		this.masterListMessageVal1up = masterListMessageVal1up;
	}

	public MasterList getMasterListMessage() {
		return masterListMessage;
	}

	public void setMasterListMessage(MasterList masterListMessage) {
		this.masterListMessage = masterListMessage;
	}

	public String getMessageCategory() {
		return messageCategory;
	}

	public void setMessageCategory(String messageCategory) {
		this.messageCategory = messageCategory;
	}

	public String getMessageValue() {
		return messageValue;
	}

	public void setMessageValue(String messageValue) {
		this.messageValue = messageValue;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

}
