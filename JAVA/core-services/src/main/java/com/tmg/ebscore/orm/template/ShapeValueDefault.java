package com.tmg.ebscore.orm.template;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;
import com.tmg.ebscore.orm.masterlist.MasterListIntVal;
import com.tmg.ebscore.orm.masterlist.MasterListStringVal;

@Entity
@Table(schema = "Tmplt", name = "ShapeValueDefault")
public class ShapeValueDefault extends CoreCommonAttributes {

	@Id
	@Column(name = "TreeShape1up")
	private Integer shapeValueDefault1up;
	
	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TreeShape1up")
	private TreeShape treeShape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TemplateTree1up")
	private TemplateTree templateTree;

	@Column(name = "IfYNTestAnswerYesFlag")
	private Boolean ifYNTestAnswerYesFlag;

	@Column(name = "IfYNTestAnswerNoFlag")
	private Boolean ifYNTestAnswerNoFlag;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SetValueValueAsInt1up")
	private MasterListIntVal setValueValueAsInt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SetValueValueAsString1up")
	private MasterListStringVal setValueValueAsString;

	public Integer getShapeValueDefault1up() {
		return shapeValueDefault1up;
	}

	public void setShapeValueDefault1up(Integer shapeValueDefault1up) {
		this.shapeValueDefault1up = shapeValueDefault1up;
	}

	public TreeShape getTreeShape() {
		return treeShape;
	}

	public void setTreeShape(TreeShape treeShape) {
		this.treeShape = treeShape;
	}

	public TemplateTree getTemplateTree() {
		return templateTree;
	}

	public void setTemplateTree(TemplateTree templateTree) {
		this.templateTree = templateTree;
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

	public MasterListIntVal getSetValueValueAsInt() {
		return setValueValueAsInt;
	}

	public void setSetValueValueAsInt(MasterListIntVal setValueValueAsInt) {
		this.setValueValueAsInt = setValueValueAsInt;
	}

	public MasterListStringVal getSetValueValueAsString() {
		return setValueValueAsString;
	}

	public void setSetValueValueAsString(
			MasterListStringVal setValueValueAsString) {
		this.setValueValueAsString = setValueValueAsString;
	}

}
