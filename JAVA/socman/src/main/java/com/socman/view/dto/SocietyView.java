package com.socman.view.dto;

import java.util.HashSet;
import java.util.Set;

public class SocietyView {

	private Integer societyID;
	private String societyName;
	private ContactView contact;

	private Set<MemberView> officeBearers;

	public Integer getSocietyID() {
		return societyID;
	}

	public void setSocietyID(Integer societyID) {
		this.societyID = societyID;
	}

	public String getSocietyName() {
		return societyName;
	}

	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}

	public ContactView getContact() {
		return contact;
	}

	public void setContact(ContactView contact) {
		this.contact = contact;
	}

	public Set<MemberView> getOfficeBearers() {
		return officeBearers;
	}

	public void setOfficeBearers(Set<MemberView> officeBearers) {
		this.officeBearers = officeBearers;
	}
	
	public void addOfficeBearer(MemberView officeBearer) {
		if(this.officeBearers == null) {
			this.officeBearers = new HashSet<MemberView>();
		}
		this.officeBearers.add(officeBearer);
	}

}
