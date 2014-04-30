package com.socman.data.service;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socman.data.orm.Member;
import com.socman.data.orm.Property;
import com.socman.data.orm.Role;
import com.socman.data.orm.Society;
import com.socman.data.repository.SocietyRepository;
import com.socman.view.dto.ContactView;
import com.socman.view.dto.MemberView;
import com.socman.view.dto.SocietyView;

@Service("SocietyDataService")
public class SocietyDataService {

	@Autowired
	private SocietyRepository societyRepository;

	@Secured(value = { "ROLE_MEMBER" })
	@Transactional(readOnly = true)
	public SocietyView getSocietyData(int societyID) {
		Society soc = societyRepository.findSocietyData(societyID);

		SocietyView view = new SocietyView();
		view.setSocietyID(soc.getSocietyID());
		view.setSocietyName(soc.getSocietyName());

		ContactView contact = new ContactView();
		try {
			// BeanUtils.setProperty(contact, "",
			// BeanUtils.getProperty(soc.getContact(), ""));
			BeanUtils.setProperty(view, "societyID",
					BeanUtils.getProperty(soc, "societyID"));
			BeanUtils.setProperty(view, "societyName",
					BeanUtils.getProperty(soc, "societyName"));

			BeanUtils.setProperty(contact, "contactID",
					BeanUtils.getProperty(soc.getContact(), "contactID"));
			BeanUtils.setProperty(contact, "addressLine1",
					BeanUtils.getProperty(soc.getContact(), "addressLine1"));
			BeanUtils.setProperty(contact, "addressLine2",
					BeanUtils.getProperty(soc.getContact(), "addressLine2"));
			BeanUtils.setProperty(contact, "city",
					BeanUtils.getProperty(soc.getContact(), "city"));
			BeanUtils.setProperty(contact, "primaryContact",
					BeanUtils.getProperty(soc.getContact(), "primaryContact"));
			BeanUtils
					.setProperty(contact, "secondaryContact", BeanUtils
							.getProperty(soc.getContact(), "secondaryContact"));
			BeanUtils.setProperty(contact, "state",
					BeanUtils.getProperty(soc.getContact(), "state"));
			BeanUtils.setProperty(contact, "zipCode",
					BeanUtils.getProperty(soc.getContact(), "zipCode"));

			List<Member> officeBearers = societyRepository
					.getOfficeBearers(societyID);

			for (Member member : officeBearers) {
				MemberView temp = new MemberView();

				BeanUtils.setProperty(temp, "memberID",
						BeanUtils.getProperty(member, "memberID"));
				BeanUtils.setProperty(temp, "emailId",
						BeanUtils.getProperty(member, "emailId"));
				BeanUtils.setProperty(temp, "firstName",
						BeanUtils.getProperty(member, "firstName"));
				BeanUtils.setProperty(temp, "middleName",
						BeanUtils.getProperty(member, "middleName"));
				BeanUtils.setProperty(temp, "lastName",
						BeanUtils.getProperty(member, "lastName"));
				for (Role role : member.getRoles()) {
					temp.addRole(role.getRoleName());
				}

				view.addOfficeBearer(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		view.setContact(contact);

		return view;

	}

	public Member findByLoginId(String loginId) {
		return societyRepository.findMemberByLoginId(loginId);
	}

	public List<Property> getProperties() {
		return societyRepository.getProperties();
	}

	public List<Member> getAllMembers() {
		return societyRepository.getAllMembers();
	}

}
