package com.socman.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socman.data.orm.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
