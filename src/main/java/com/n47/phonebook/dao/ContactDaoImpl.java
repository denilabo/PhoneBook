package com.n47.phonebook.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.n47.phonebook.entities.Contact;

@Repository
public class ContactDaoImpl implements ContactDao {

	private String CONTACT_SEARCH_QUERY = "select c from Contact c where c.name like '%%s%' or c.phoneNumber = '%s'";

	@PersistenceContext
	private EntityManager em;

	@Override
	public void savePhoneBook(Contact contact) {
		em.persist(contact);
		em.flush();
	}

	@Override
	public List<Contact> searchContact(String name, String phoneNumber) {
		List<Contact> phoneBook;
		phoneBook = em.createQuery(
				"select c from Contact c where c.name like '%" + name + "%' or c.phoneNumber='" + phoneNumber + "'")
				.getResultList();

		return phoneBook;
	}

}
