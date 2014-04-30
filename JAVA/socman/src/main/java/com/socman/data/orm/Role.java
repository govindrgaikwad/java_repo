package com.socman.data.orm;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name = "roles", schema = "soc")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RoleId")
	private Integer roleId;

	@Column(name = "RoleName")
	private String roleName;

	// bi-directional many-to-many association to Member
	@ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
	private List<Member> members;

	public Role() {
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Member> getMembers() {
		return this.members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

}