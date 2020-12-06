package com.n47.phonebook.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n47.phonebook.entities.Contact;
import com.n47.phonebook.models.ContactModel;
import com.n47.phonebook.services.ContactService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PhoneBookIntegrationtest {
	
	 private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	    @Autowired
	    private MockMvc mockMvc;
	    
	    @Autowired
	    private ContactService contactService;

	    @Test
	    public void shouldCreateContact() throws Exception {
	        Contact createdContact = createContact("Deki", "+38971640861");
	        assertThat(createdContact.getId()).isNotNull();
	        assertThat(createdContact.getPhoneNumber()).isNotEmpty();
	        assertThat(createdContact.getPhoneNumber()).isNotNull();
	        assertThat(createdContact.getName()).isNotEmpty();
	        assertThat(createdContact.getName()).isNotNull();
	        assertTrue(contactService.isPhoneNumberValid(createdContact.getPhoneNumber()));

	    }
	    
	    @Test
	    public void shouldGetCreatedContact() throws Exception {
	      getContact("Deki","22");

	    }

	    private void getContact(String name, String phoneNumber) throws Exception {
	        MvcResult receivedUser = this.mockMvc.perform(MockMvcRequestBuilders
	                .get(String.format("/phonebook/contacts?name=%s&phoneNumber=%s", name,phoneNumber))
	                .contentType(MediaType.APPLICATION_JSON))
	                .andDo(print())
	                .andExpect(status().isOk())
	                .andReturn();

	    }

	    private Contact createContact(String name, String phoneNumber) throws Exception {
	        ContactModel contact = new 	ContactModel();
	        contact.setName(name);
	        contact.setPhoneNumber(phoneNumber);

	        String postValue = OBJECT_MAPPER.writeValueAsString(contact);

	        MvcResult storyResult = mockMvc.perform(MockMvcRequestBuilders
	                .post("/phonebook/contacts/")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(postValue))
	                .andExpect(status().isCreated())
	                .andDo(print())
	                .andReturn();

	        return OBJECT_MAPPER.readValue(storyResult.getResponse().getContentAsString(), Contact.class);
	    }

}
