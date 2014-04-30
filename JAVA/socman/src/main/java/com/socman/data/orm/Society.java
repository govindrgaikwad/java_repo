package com.socman.data.orm;

import java.io.Serializable;
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

/**
 * The persistent class for the society database table.
 * 
 */
@Entity
@Table(name = "Society", schema = "soc")
public class Society implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SocietyID")
	private Integer societyID;

	@Column(name = "SocietyName")
	private String societyName;

	// bi-directional many-to-one association to Member
	@OneToMany(mappedBy = "society", fetch = FetchType.LAZY)
	private Set<Member> members;

	@OneToMany(mappedBy = "society", fetch = FetchType.LAZY)
	private Set<Transaction> transactions;

	// bi-directional many-to-one association to Contact
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ContactID")
	private Contact contact;

	public Society() {
	}

	public Integer getSocietyID() {
		return societyID;
	}

	public void setSocietyID(Integer societyID) {
		this.societyID = societyID;
	}

	public String getSocietyName() {
		return this.societyName;
	}

	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}

	public Set<Member> getMembers() {
		return this.members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

	public Member addMember(Member member) {
		getMembers().add(member);
		member.setSociety(this);

		return member;
	}

	public Member removeMember(Member member) {
		getMembers().remove(member);
		member.setSociety(null);

		return member;
	}

	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}