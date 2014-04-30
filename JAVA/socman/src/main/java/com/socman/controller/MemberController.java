package com.socman.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socman.data.orm.Member;
import com.socman.data.orm.Role;
import com.socman.data.repository.SocietyRepository;
import com.socman.data.service.SocietyDataService;
import com.socman.view.dto.ContactView;
import com.socman.view.dto.MemberView;
import com.socman.view.dto.MessageView;

@Controller
@RequestMapping("/protected/member")
public class MemberController {

	@Autowired
	SocietyRepository societyRepository;
	@Autowired
	SocietyDataService societyDataService;

	@RequestMapping(value = "/{userName}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ResponseEntity<?> getMemberDetails(@PathVariable("userName") String userName) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		User currentUser = (User) authentication.getPrincipal();

		Member member = societyRepository.findMemberByLoginId(currentUser
				.getUsername());
		MemberView view = new MemberView();
		view.setFirstName(member.getFirstName());
		view.setLastName(member.getLastName());
		Set<String> roles = new HashSet<String>();
		for (Role role : member.getRoles()) {
			roles.add(role.getRoleName());
		}
		view.setRoles(roles);
		return new ResponseEntity<MemberView>(view, HttpStatus.OK);
	}

	@RequestMapping(value = "/{userName}/messages", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ResponseEntity<?> getMemberMessages(
			@PathVariable("userName") String userName) {
		// Go to the DB..
		// Get
		List<MessageView> messages = new ArrayList<MessageView>();
		MessageView message = new MessageView();
		message.setAvatar("../admin/img/avatar.png");
		message.setMessageFrom("Govind");
		message.setShortMessage("Hello Everyone");

		MessageView message1 = new MessageView();
		message1.setAvatar("../admin/img/avatar2.png");
		message1.setMessageFrom("Ranjit");
		message1.setShortMessage("Hello World");
		messages.add(message);
		messages.add(message1);

		return new ResponseEntity<List<?>>(messages, HttpStatus.OK);
	}

	@RequestMapping(value = "/{userName}/tasks", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<?> getMemberTasks(@PathVariable("userName") String userName) {
		// Go to the DB..
		// Get

		return null;
	}

	@RequestMapping(value = "/{userName}/notifications", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<?> getMemberNotifications(
			@PathVariable("userName") String userName) {
		// Go to the DB..
		// Get
		List<String> notifications = new ArrayList<String>();
		notifications.add("5 new members joined today");
		notifications
				.add("Very long description here that may not fit into the page and may cause design problems");
		notifications.add("5 new members joined");
		notifications.add("25 sales made");
		notifications.add(" You changed your username");
		return new ResponseEntity<List<?>>(notifications, HttpStatus.OK);
	}

	@RequestMapping(value = "/members", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<?> getAllMembers() {
		// Go to the DB..
		// Get
		List<MemberView> view = new ArrayList<MemberView>();

		try {
			List<Member> members = societyDataService.getAllMembers();
			for (Member member : members) {
				MemberView memberView = new MemberView();
				memberView.setMemberID(member.getMemberID());
				memberView.setFirstName(member.getFirstName());
				memberView.setMiddleName(member.getMiddleName());
				memberView.setLastName(member.getLastName());
				memberView.setEnabled(member.getEnabled());
				memberView.setEmailId(member.getEmailId());
				Set<String> roles = new HashSet<String>();
				for (Role role : member.getRoles()) {
					roles.add(role.getRoleName());
				}
				memberView.setRoles(roles);
				ContactView contactView = new ContactView();
				contactView.setAddressLine1(member.getContact()
						.getAddressLine1());
				contactView.setAddressLine2(member.getContact()
						.getAddressLine2());
				contactView.setCity(member.getContact().getCity());
				contactView.setContactID(member.getContact().getContactID());
				contactView.setPrimaryContact(member.getContact()
						.getPrimaryContact());
				contactView.setSecondaryContact(member.getContact()
						.getSecondaryContact());
				contactView.setState(member.getContact().getState());
				contactView.setZipCode(member.getContact().getZipCode());
				memberView.setContact(contactView);
				view.add(memberView);
			}

		} catch (AccessDeniedException e) {
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<List<?>>(view, HttpStatus.OK);
	}
}
