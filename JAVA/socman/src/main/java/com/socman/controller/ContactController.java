package com.socman.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.socman.data.service.ContactService;

import com.socman.data.orm.Contact;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("contact");
	}

	@RequestMapping(value = "/getContact", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<?> getContactList() {
		List<Contact> contacts = contactService.getContacts();
		return new ResponseEntity<List<?>>(contacts, HttpStatus.OK);
	}

	@RequestMapping(value = "/addContact/{contact}", method = RequestMethod.POST)
	public @ResponseBody
	void addContact(@PathVariable("contact") Contact contact) {
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(contact);
		contactService.saveContact(contacts);
	}

	@RequestMapping(value = "/UpdateContact/{contact}", method = RequestMethod.POST)
	public @ResponseBody
	void UpdateContact(@PathVariable("contact") Contact contact) {
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(contact);
		contactService.saveContact(contacts);
	}

	@RequestMapping(value = "/deleteContact/{contact}", method = RequestMethod.DELETE)
	public @ResponseBody
	void deleteContact(@PathVariable("contactID") Long contactID) {

		contactService.deleteContactById(contactID);
	}

	@RequestMapping(value = "/removeAllContacts", method = RequestMethod.DELETE)
	public @ResponseBody
	void removeAllContacts() {

		contactService.deleteContacts();
	}
}
