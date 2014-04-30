package com.tmg.ebscore.orm.ebspackage;

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
import com.tmg.ebscore.orm.masterlist.MasterListServiceDefinition;
import com.tmg.ebscore.orm.masterlist.MasterListStringVal;
import com.tmg.ebscore.orm.packageversion.PkgVerBig3CO;

@Entity
@Table(schema = "Pkg", name = "PkgMasterListForBig3CO")
public class PkgMasterListForBig3CO extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PkgMasterListForBig3CO1up")
	private Integer pkgMasterListForBig3CO1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BenefitPackage1up")
	private BenefitPackage benefitPackage;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterListServiceDefinition1up")
	private MasterListServiceDefinition masterListServiceDefinition;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NetworkStringVal1up")
	private MasterListStringVal masterListStringVal;

	@OneToMany(mappedBy = "pkgMasterListForBig3CO", fetch = FetchType.LAZY)
	private Set<PkgVerBig3CO> pkgBigCoI = new HashSet<PkgVerBig3CO>();

	public Set<PkgVerBig3CO> getPkgBigCoI() {
		return pkgBigCoI;
	}

	public void setPkgBigCoI(Set<PkgVerBig3CO> pkgBigCoI) {
		this.pkgBigCoI = pkgBigCoI;
	}

	public Integer getPkgMasterListForBig3CO1up() {
		return pkgMasterListForBig3CO1up;
	}

	public void setPkgMasterListForBig3CO1up(Integer pkgMasterListForBig3CO1up) {
		this.pkgMasterListForBig3CO1up = pkgMasterListForBig3CO1up;
	}

	public BenefitPackage getBenefitPackage() {
		return benefitPackage;
	}

	public void setBenefitPackage(BenefitPackage benefitPackage) {
		this.benefitPackage = benefitPackage;
	}

	public MasterListServiceDefinition getMasterListServiceDefinition() {
		return masterListServiceDefinition;
	}

	public void setMasterListServiceDefinition(
			MasterListServiceDefinition masterListServiceDefinition) {
		this.masterListServiceDefinition = masterListServiceDefinition;
	}

	public MasterListStringVal getMasterListStringVal() {
		return masterListStringVal;
	}

	public void setMasterListStringVal(MasterListStringVal masterListStringVal) {
		this.masterListStringVal = masterListStringVal;
	}

}
