package com.socman.view.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MemberView {

	private Integer memberID;
	private String emailId;
	private Boolean enabled;
	private String firstName;
	private String middleName;
	private String lastName;
	
	private Set<String> roles;

	private ContactView contact;

	private List<MemberView> associatedMembers;

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ContactView getContact() {
		return contact;
	}

	public void setContact(ContactView contact) {
		this.contact = contact;
	}

	public List<MemberView> getAssociatedMembers() {
		return associatedMembers;
	}

	public void setAssociatedMembers(List<MemberView> associatedMembers) {
		this.associatedMembers = associatedMembers;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	public void addRole(String role) {
		if(this.roles == null) {
			this.roles = new HashSet<String>();
		}
		this.roles.add(role);
	}

}
