package com.socman.data.orm;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.socman.data.base.BaseEntityAttribute;

/**
 * The persistent class for the member database table.
 * 
 */
@Entity
@Table(name = "Member", schema = "soc")
public class Member extends BaseEntityAttribute {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MemberID")
	private Integer memberID;

	@Column(name = "EmailId")
	private String emailId;

	@Column(name = "Enabled")
	private Boolean enabled;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "LoginID")
	private String loginID;

	@Column(name = "MiddleName")
	private String middleName;

	@Column(name = "Password")
	private String password;

	// bi-directional many-to-one association to Property
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PropertyID")
	private Property property;

	// bi-directional many-to-one association to Society
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SocietyID")
	private Society society;

	// bi-directional many-to-one association to Contact
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ContactID")
	private Contact contact;

	// bi-directional many-to-many association to Role
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "member_roles", joinColumns = { @JoinColumn(name = "MemberID") }, inverseJoinColumns = { @JoinColumn(name = "RoleId") })
	private List<Role> roles;

	public Member() {
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginID() {
		return this.loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Property getProperty() {
		return this.property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Society getSociety() {
		return this.society;
	}

	public void setSociety(Society society) {
		this.society = society;
	}

	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public Boolean getEnabled() {
		return enabled;
	}
	

}