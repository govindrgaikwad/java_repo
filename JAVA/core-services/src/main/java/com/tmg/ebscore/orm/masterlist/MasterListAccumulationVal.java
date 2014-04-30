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

@Entity
@Table(schema = "Mast", name = "MasterListAccumulationVal")
public class MasterListAccumulationVal extends CoreCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Accumulation1up")
	private Integer accumulation1up;

	@Column(name = "Number")
	private Integer number;

	@Column(name = "Description")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MasterList1up")
	private MasterList masterListAccumVal;

	@OneToMany(mappedBy = "accumulation", fetch = FetchType.LAZY)
	private Set<MasterListIntVal> accumInt = new HashSet<MasterListIntVal>();

	@OneToMany(mappedBy = "masterListAcumlationVal", fetch = FetchType.LAZY)
	private Set<MasterListLimitVal> masterLimitAccum = new HashSet<MasterListLimitVal>();

	public Set<MasterListLimitVal> getMasterLimitAccum() {
		return masterLimitAccum;
	}

	public void setMasterLimitAccum(Set<MasterListLimitVal> masterLimitAccum) {
		this.masterLimitAccum = masterLimitAccum;
	}

	public Set<MasterListIntVal> getAccumInt() {
		return accumInt;
	}

	public void setAccumInt(Set<MasterListIntVal> accumInt) {
		this.accumInt = accumInt;
	}

	public Integer getAccumulation1up() {
		return accumulation1up;
	}

	public void setAccumulation1up(Integer accumulation1up) {
		this.accumulation1up = accumulation1up;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MasterList getMasterListAccumVal() {
		return masterListAccumVal;
	}

	public void setMasterListAccumVal(MasterList masterListAccumVal) {
		this.masterListAccumVal = masterListAccumVal;
	}

}
