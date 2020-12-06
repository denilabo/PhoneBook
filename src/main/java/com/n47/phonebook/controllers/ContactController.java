package com.n47.phonebook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.n47.phonebook.entities.Contact;
import com.n47.phonebook.exceptions.ContactException;
import com.n47.phonebook.models.ContactError;
import com.n47.phonebook.models.ContactModel;
import com.n47.phonebook.services.ContactService;

@RestController
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping(value = "/phonebook/contacts", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Contact> createContact(@RequestBody ContactModel contact) throws ContactException {

		return new ResponseEntity<Contact>(contactService.createContact(contact), HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "/phonebook/contacts", method = RequestMethod.GET)
	public List<Contact> searchContacts(@RequestParam String name, @RequestParam String phoneNumber){
		return contactService.searchContact(name, phoneNumber);
	}

	@ExceptionHandler(ContactException.class)
	public ResponseEntity<ContactError> handleUserNotFoundException(Exception ex) {
		ContactError errorResponse = new ContactError();
		errorResponse.setCode("INVALID_PAYLOAD");
		errorResponse.setMessage(ex.getMessage());
		return new ResponseEntity<ContactError>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
