package com.n47.phonebook.services;

import java.util.List;

import com.n47.phonebook.entities.Contact;
import com.n47.phonebook.exceptions.ContactException;
import com.n47.phonebook.models.ContactModel;

public interface ContactService {
	
	public Contact createContact (ContactModel contact)throws ContactException;
	
	public List<Contact> searchContact(String name, String phoneNumber);
	public Boolean isPhoneNumberValid(String phoneNumber);
	public Boolean isStringNullOrEmpty(String string);


}
