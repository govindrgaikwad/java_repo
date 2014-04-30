package com.socman.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socman.data.orm.Contact;
import com.socman.data.repository.ContactRepository;

@Service("ContactService")
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Transactional(readOnly = true)
	public List<Contact> getContacts() {
		return contactRepository.findAll();

	}

	@Transactional(readOnly = true)
	public Contact getContactByID(Long contactID) {
		return contactRepository.findOne(contactID);
	}

	@Transactional
	public void deleteContacts() {
		contactRepository.deleteAll();
	}

	@Transactional
	public void deleteContactById(Long contactID) {
		contactRepository.delete(contactID);
	}

	@Transactional
	public void saveContact(List<Contact> contacts) {
		contactRepository.save(contacts);
	}

}
