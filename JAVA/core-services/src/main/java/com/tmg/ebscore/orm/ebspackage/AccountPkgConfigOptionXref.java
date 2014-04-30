package com.tmg.ebscore.orm.ebspackage;

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

@Entity
@Table(schema = "Accn", name = "AccountPkgConfigOptionXref")
public class AccountPkgConfigOptionXref extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AccountPkgConfigOptionXref1up")
	private Integer accountPkgConfigOptionXref1up;

	@Column(name = "Account1up")
	private Integer account1up;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PkgConfigOption1up")
	private PkgConfigOption pkgConfigOption;

	@Column(name = "AccountWorkflowState1up")
	private Integer accountWorkflowState1up;

	@Column(name = "Notes")
	private String notes;

	public Integer getAccountPkgConfigOptionXref1up() {
		return accountPkgConfigOptionXref1up;
	}

	public void setAccountPkgConfigOptionXref1up(
			Integer accountPkgConfigOptionXref1up) {
		this.accountPkgConfigOptionXref1up = accountPkgConfigOptionXref1up;
	}

	public Integer getAccount1up() {
		return account1up;
	}

	public void setAccount1up(Integer account1up) {
		this.account1up = account1up;
	}

	public PkgConfigOption getPkgConfigOption() {
		return pkgConfigOption;
	}

	public void setPkgConfigOption(PkgConfigOption pkgConfigOption) {
		this.pkgConfigOption = pkgConfigOption;
	}

	public Integer getAccountWorkflowState1up() {
		return accountWorkflowState1up;
	}

	public void setAccountWorkflowState1up(Integer accountWorkflowState1up) {
		this.accountWorkflowState1up = accountWorkflowState1up;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
