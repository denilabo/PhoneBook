package com.n47.phonebook.dao;

import java.util.List;

import com.n47.phonebook.entities.Contact;

public interface ContactDao {
	
	public void savePhoneBook(Contact contact);
	public List<Contact> searchContact(String name, String phoneNumber);

}
