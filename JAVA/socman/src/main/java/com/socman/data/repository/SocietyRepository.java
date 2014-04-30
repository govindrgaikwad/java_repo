package com.socman.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.socman.data.orm.Member;
import com.socman.data.orm.Property;
import com.socman.data.orm.Society;

public interface SocietyRepository extends JpaRepository<Society, Long>,
		SocietyCustomRepository {

	@Query(value = "SELECT soc from Society soc "
			+ "LEFT JOIN FETCH soc.contact "
			+ "WHERE soc.societyID = :societyID ")
	public Society findSocietyData(@Param("societyID") int societyID);

	@Query(value = "SELECT distinct list from Member list "
			+ "LEFT JOIN FETCH list.roles role "
			+ "WHERE list.society.societyID = :societyID "
			+ "AND role.roleName IN ('ROLE_SECRETARY', 'ROLE_TREASURER', 'ROLE_CHAIRMAN')")
	public List<Member> getOfficeBearers(@Param("societyID") int societyID);

	@Query(value = "SELECT mem from Member mem " + "LEFT JOIN FETCH mem.roles "
			+ "WHERE mem.loginID = :loginID ")
	public Member findMemberByLoginId(@Param("loginID") String loginID);

	@Query(value = "SELECT list from Property list")
	public List<Property> getProperties();

	@Query(value = "SELECT distinct list from Member list "
			+ "LEFT JOIN FETCH list.roles role "
			+ "LEFT JOIN FETCH list.contact contact ")
	public List<Member> getAllMembers();

}