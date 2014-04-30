package com.tmg.ebscore.orm.template;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;

@Entity
@Table(schema = "Tmplt", name = "MatrixShapeMessageXref")
public class MatrixShapeMessageXref extends CoreCommonAttributes {

	@EmbeddedId
	private MatrixShapeMessageXrefId matrixShapeMessageXrefId;

	@Column(name = "IsAvailableFlag")
	private Boolean availableFlag;

	public MatrixShapeMessageXrefId getMatrixShapeMessageXrefId() {
		return matrixShapeMessageXrefId;
	}

	public void setMatrixShapeMessageXrefId(
			MatrixShapeMessageXrefId matrixShapeMessageXrefId) {
		this.matrixShapeMessageXrefId = matrixShapeMessageXrefId;
	}

	public Boolean getAvailableFlag() {
		return availableFlag;
	}

	public void setAvailableFlag(Boolean availableFlag) {
		this.availableFlag = availableFlag;
	}

}
