package com.tmg.ebscore.orm.template;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;

@Entity
@Table(schema = "Tmplt", name = "MatrixShapeLimitXref")
public class MatrixShapeLimitXref extends CoreCommonAttributes {

	@EmbeddedId
	private MatrixShapeLimitXrefId matrixShapeLimitXrefId;

	@Column(name = "IsAvailableFlag")
	private Boolean availableFlag;

	public MatrixShapeLimitXrefId getMatrixShapeLimitXrefId() {
		return matrixShapeLimitXrefId;
	}

	public void setMatrixShapeLimitXrefId(
			MatrixShapeLimitXrefId matrixShapeLimitXrefId) {
		this.matrixShapeLimitXrefId = matrixShapeLimitXrefId;
	}

	public Boolean getAvailableFlag() {
		return availableFlag;
	}

	public void setAvailableFlag(Boolean availableFlag) {
		this.availableFlag = availableFlag;
	}

}
