package com.socman.data.orm;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the property database table.
 * 
 */
@Entity
@Table(name = "Property", schema = "soc")
public class Property implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PropertyID")
	private Integer propertyID;

	@Column(name = "PropertyName")
	private String propertyName;

	@Column(name = "PropertyType")
	private String propertyType;

	// bi-directional many-to-one association to Member
	@OneToMany(mappedBy = "property",fetch = FetchType.LAZY)
	private List<Member> members;

	public Property() {
	}

	public Integer getPropertyID() {
		return propertyID;
	}

	public void setPropertyID(Integer propertyID) {
		this.propertyID = propertyID;
	}

	public String getPropertyName() {
		return this.propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyType() {
		return this.propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public List<Member> getMembers() {
		return this.members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public Member addMember(Member member) {
		getMembers().add(member);
		member.setProperty(this);

		return member;
	}

	public Member removeMember(Member member) {
		getMembers().remove(member);
		member.setProperty(null);

		return member;
	}

}