package com.tmg.ebscore.orm.template;

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
import com.tmg.ebscore.orm.masterlist.MasterListStringVal;

@Entity
@Table(schema = "Tmplt", name = "TreeConnector")
public class TreeConnector extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TreeConnector1up")
	private Integer treeConnector1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TemplateTree1up")
	private TemplateTree templateTree;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FromShape1up")
	private TreeShape fromShape;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ToShape1up")
	private TreeShape toShape;

	@Column(name = "IsFromIfYesPortFlag")
	private Boolean fromIfYesPortFlag;

	@Column(name = "IsFromIfNoPortFlag")
	private Boolean fromIfNoPortFlag;

	@Column(name = "FromOrder")
	private Integer fromOrder;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SwitchOptionStringVal1up")
	private MasterListStringVal switchOptionStringVal;

	@Column(name = "GoXamXMLBlob")
	private String goXamXMLBlob;

	public Integer getTreeConnector1up() {
		return treeConnector1up;
	}

	public void setTreeConnector1up(Integer treeConnector1up) {
		this.treeConnector1up = treeConnector1up;
	}

	public TemplateTree getTemplateTree() {
		return templateTree;
	}

	public void setTemplateTree(TemplateTree templateTree) {
		this.templateTree = templateTree;
	}

	public TreeShape getFromShape() {
		return fromShape;
	}

	public void setFromShape(TreeShape fromShape) {
		this.fromShape = fromShape;
	}

	public TreeShape getToShape() {
		return toShape;
	}

	public void setToShape(TreeShape toShape) {
		this.toShape = toShape;
	}

	public Boolean getFromIfYesPortFlag() {
		return fromIfYesPortFlag;
	}

	public void setFromIfYesPortFlag(Boolean fromIfYesPortFlag) {
		this.fromIfYesPortFlag = fromIfYesPortFlag;
	}

	public Boolean getFromIfNoPortFlag() {
		return fromIfNoPortFlag;
	}

	public void setFromIfNoPortFlag(Boolean fromIfNoPortFlag) {
		this.fromIfNoPortFlag = fromIfNoPortFlag;
	}

	public Integer getFromOrder() {
		return fromOrder;
	}

	public void setFromOrder(Integer fromOrder) {
		this.fromOrder = fromOrder;
	}

	public MasterListStringVal getSwitchOptionStringVal() {
		return switchOptionStringVal;
	}

	public void setSwitchOptionStringVal(
			MasterListStringVal switchOptionStringVal) {
		this.switchOptionStringVal = switchOptionStringVal;
	}

	public String getGoXamXMLBlob() {
		return goXamXMLBlob;
	}

	public void setGoXamXMLBlob(String goXamXMLBlob) {
		this.goXamXMLBlob = goXamXMLBlob;
	}

}
