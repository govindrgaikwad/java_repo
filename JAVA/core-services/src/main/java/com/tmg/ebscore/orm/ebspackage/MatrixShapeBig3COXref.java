package com.tmg.ebscore.orm.ebspackage;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;

@Entity
@Table(schema = "Pkg", name = "MatrixShapeBig3COXref")
public class MatrixShapeBig3COXref extends CoreCommonAttributes {

	@EmbeddedId
	private MatrixShapeBig3COXrefId matrixShapeBig3COXrefId;

	@OneToMany(mappedBy = "matrixShapeBig3COXref", fetch = FetchType.LAZY)
	private Set<MatrixShapeBig3COLimitXXref> big3coLimitXXrefs = new HashSet<MatrixShapeBig3COLimitXXref>();


	@OneToMany(mappedBy = "matrixShapeBig3COXref", fetch = FetchType.LAZY)
	private Set<MatrixShapeBig3COMessageXXref> big3coMessageXXrefs = new HashSet<MatrixShapeBig3COMessageXXref>();

	@Column(name = "IsAvailableFlag")
	private Boolean availableFlag;

	public Set<MatrixShapeBig3COMessageXXref> getBig3coMessageXXrefs() {
		return big3coMessageXXrefs;
	}

	public void setBig3coMessageXXrefs(
			Set<MatrixShapeBig3COMessageXXref> big3coMessageXXrefs) {
		this.big3coMessageXXrefs = big3coMessageXXrefs;
	}

	public Set<MatrixShapeBig3COLimitXXref> getBig3coLimitXXrefs() {
		return big3coLimitXXrefs;
	}

	public void setBig3coLimitXXrefs(
			Set<MatrixShapeBig3COLimitXXref> big3coLimitXXrefs) {
		this.big3coLimitXXrefs = big3coLimitXXrefs;
	}

	public MatrixShapeBig3COXrefId getMatrixShapeBig3COXrefId() {
		return matrixShapeBig3COXrefId;
	}

	public void setMatrixShapeBig3COXrefId(
			MatrixShapeBig3COXrefId matrixShapeBig3COXrefId) {
		this.matrixShapeBig3COXrefId = matrixShapeBig3COXrefId;
	}

	public Boolean getAvailableFlag() {
		return availableFlag;
	}

	public void setAvailableFlag(Boolean availableFlag) {
		this.availableFlag = availableFlag;
	}

}
