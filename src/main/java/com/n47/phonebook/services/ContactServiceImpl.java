package com.n47.phonebook.services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n47.phonebook.dao.ContactDao;
import com.n47.phonebook.entities.Contact;
import com.n47.phonebook.exceptions.ContactException;
import com.n47.phonebook.models.ContactModel;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

	private static final String PHONE_NUMBER_REGEX = "(\\+389)(7[0125678])([\\d]{3})([\\d]{3})";

	private static final String CONTACT_NAME_ERROR_MESSAGE = "field_name";

	private static final String CONTACT_PHONE_NUMBER_ERROR_MESSAGE = "phoneNumber";

	@Autowired
	ContactDao contactDao;

	@Override
	@Transactional
	public Contact createContact(ContactModel contact) throws ContactException {
		if (isStringNullOrEmpty(contact.getName())) {
			throw new ContactException(CONTACT_NAME_ERROR_MESSAGE);
		} else if (isStringNullOrEmpty(contact.getPhoneNumber()) || !isPhoneNumberValid(contact.getPhoneNumber())) {
			throw new ContactException(CONTACT_PHONE_NUMBER_ERROR_MESSAGE);
		}
		Contact contactToSave = new Contact(contact.getName(),contact.getPhoneNumber());
		contactDao.savePhoneBook(contactToSave);
		return contactToSave;
	}

	@Override
	public Boolean isPhoneNumberValid(String phoneNumber) {

		Pattern ptr = Pattern.compile(PHONE_NUMBER_REGEX);
		Matcher match = ptr.matcher(phoneNumber);

		return match.matches();

	}

	@Override
	public List<Contact> searchContact(String name, String phoneNumber) {
		
		return contactDao.searchContact(name, phoneNumber);
	}

	@Override
	public Boolean isStringNullOrEmpty(String string) {

		return (string == null || string.isEmpty());
	}

}
