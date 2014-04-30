package com.socman.data.orm;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the contact database table.
 * 
 */
@Entity
@Table(name = "Contact", schema = "soc")
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ContactID")
	private Integer contactID;

	@Column(name = "AddressLine1")
	private String addressLine1;

	@Column(name = "AddressLine2")
	private String addressLine2;

	@Column(name = "City")
	private String city;

	@Column(name = "PrimaryContact")
	private String primaryContact;

	@Column(name = "SecondaryContact")
	private String secondaryContact;

	@Column(name = "State")
	private String state;

	@Column(name = "ZipCode")
	private String zipCode;

	// bi-directional many-to-one association to Member
	@OneToMany(mappedBy = "contact", fetch = FetchType.LAZY)
	private List<Member> members;

	// bi-directional many-to-one association to Society
	@OneToMany(mappedBy = "contact", fetch = FetchType.LAZY)
	private List<Society> societies;

	public Contact() {
	}

	public Integer getContactID() {
		return contactID;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(String primaryContact) {
		this.primaryContact = primaryContact;
	}

	public void setContactID(Integer contactID) {
		this.contactID = contactID;
	}

	public String getSecondaryContact() {
		return this.secondaryContact;
	}

	public void setSecondaryContact(String secondaryContact) {
		this.secondaryContact = secondaryContact;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<Member> getMembers() {
		return this.members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public Member addMember(Member member) {
		getMembers().add(member);
		member.setContact(this);

		return member;
	}

	public Member removeMember(Member member) {
		getMembers().remove(member);
		member.setContact(null);

		return member;
	}

	public List<Society> getSocieties() {
		return this.societies;
	}

	public void setSocieties(List<Society> societies) {
		this.societies = societies;
	}

	public Society addSociety(Society society) {
		getSocieties().add(society);
		society.setContact(this);

		return society;
	}

	public Society removeSociety(Society society) {
		getSocieties().remove(society);
		society.setContact(null);

		return society;
	}

}