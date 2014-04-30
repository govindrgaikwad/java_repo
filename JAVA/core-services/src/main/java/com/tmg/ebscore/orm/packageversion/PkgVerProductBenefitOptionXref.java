package com.tmg.ebscore.orm.packageversion;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tmg.ebscore.orm.CoreCommonAttributes;

@Entity
@Table(schema = "Pkg", name = "PkgVerProductBenefitOptionXref")
public class PkgVerProductBenefitOptionXref extends CoreCommonAttributes {
	@EmbeddedId
	private PkgVerProductBenefitOptionXrefId pkgVerProductBenefitOptionXrefId;

	@Column(name = "IsUsedInProductFlag")
	private Boolean usedInProductFlag;

	@Column(name = "OrderToApply")
	private Integer orderToApply;

	public PkgVerProductBenefitOptionXrefId getPkgVerProductBenefitOptionXrefId() {
		return pkgVerProductBenefitOptionXrefId;
	}

	public void setPkgVerProductBenefitOptionXrefId(
			PkgVerProductBenefitOptionXrefId pkgVerProductBenefitOptionXrefId) {
		this.pkgVerProductBenefitOptionXrefId = pkgVerProductBenefitOptionXrefId;
	}

	public Boolean getUsedInProductFlag() {
		return usedInProductFlag;
	}

	public void setUsedInProductFlag(Boolean usedInProductFlag) {
		this.usedInProductFlag = usedInProductFlag;
	}

	public Integer getOrderToApply() {
		return orderToApply;
	}

	public void setOrderToApply(Integer orderToApply) {
		this.orderToApply = orderToApply;
	}

}
