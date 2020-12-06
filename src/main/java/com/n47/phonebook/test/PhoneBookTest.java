package com.n47.phonebook.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.n47.phonebook.controllers.ContactController;
import com.n47.phonebook.entities.Contact;
import com.n47.phonebook.exceptions.ContactException;
import com.n47.phonebook.models.ContactModel;
import com.n47.phonebook.services.ContactService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneBookTest {

	@Autowired
	private ContactController contactController;

	@Autowired
	private ContactService contactService;


	@Test
	public void validPayLoadTest() throws ContactException {
		ContactModel contact = new ContactModel();
		contact.setName("Deni");
		contact.setPhoneNumber("+38971640861");


		String contactName = contact.getName();
		String phoneNumber = contact.getPhoneNumber();
		assertNotNull(contact);
		assertFalse(contactService.isStringNullOrEmpty(contactName));
		assertFalse(contactService.isStringNullOrEmpty(phoneNumber));
		assertTrue(contactService.isPhoneNumberValid(phoneNumber));
		contactController.createContact(contact);
		assertNotNull(contactController.searchContacts(contactName, phoneNumber).get(0));

	}

	@Test
	public void fieldNameNullOrEmptyTest() {
		Contact contact = new Contact("","");
		assertTrue(contactService.isStringNullOrEmpty(contact.getName()));
        assertThat(contact.getName()).isNullOrEmpty();



	}

	@Test
	public void phoneNumberNullOrEmptyTest() {
		Contact contact = new Contact("","");
		assertTrue(contactService.isStringNullOrEmpty(contact.getPhoneNumber()));
        assertThat(contact.getPhoneNumber()).isNullOrEmpty();



	}

	@Test
	public void PhoneNumberInvalidTest() {
		Contact contact = new Contact("deni", "1234");
		assertFalse(contactService.isPhoneNumberValid(contact.getPhoneNumber()));

	}

		

}
