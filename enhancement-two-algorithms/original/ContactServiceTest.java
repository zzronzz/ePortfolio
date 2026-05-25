package com.contacts.test;

import com.contacts.Contact;
import com.contacts.ContactService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {

    /**
     * Test to verify that a contact can be added with a unique ID.
     */
    @Test
    public void addNewContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact("201", "James", "Anderson", "1234567890", "789 Sunset Blvd");
        service.addContact(contact);

        assertNotNull(service.getContact("201"), "Contact should exist after being added.");
    }

    /**
     * Test to ensure that adding a contact with a duplicate ID is not allowed.
     */
    @Test
    public void rejectDuplicateContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact("202", "Emily", "Carter", "9876543210", "456 Maple Street");
        service.addContact(contact);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.addContact(contact));
        assertEquals("A contact with this ID already exists: 202", exception.getMessage());
    }

    /**
     * Test to verify that a contact can be deleted by its unique ID.
     */
    @Test
    public void removeExistingContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact("203", "Sophia", "Johnson", "5554443333", "321 Birch Lane");
        service.addContact(contact);
        service.deleteContact("203");

        assertNull(service.getContact("203"), "Contact should no longer exist after deletion.");
    }

    /**
     * Test to ensure that attempting to delete a non-existent contact throws an exception.
     */
    @Test
    public void failToRemoveNonexistentContact() {
        ContactService service = new ContactService();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.deleteContact("999"));
        assertEquals("Cannot delete. Contact ID not found: 999", exception.getMessage());
    }

    /**
     * Test to verify that a contact's fields (firstName, lastName, phone, address) can be updated.
     */
    @Test
    public void modifyContactDetails() {
        ContactService service = new ContactService();
        Contact contact = new Contact("204", "Liam", "Williams", "7778889999", "789 Cypress Ave");
        service.addContact(contact);

        service.updateContact("204", "Liam", "Brown", "8887776666", "123 Oak Drive");

        Contact updatedContact = service.getContact("204");
        assertEquals("Liam", updatedContact.getFirstName());
        assertEquals("Brown", updatedContact.getLastName());
        assertEquals("8887776666", updatedContact.getPhone());
        assertEquals("123 Oak Drive", updatedContact.getAddress());
    }

    /**
     * Test to ensure that attempting to update a non-existent contact throws an exception.
     */
    @Test
    public void failToModifyNonexistentContact() {
        ContactService service = new ContactService();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                service.updateContact("999", "Oliver", "White", "9998887777", "555 Elm Circle"));
        assertEquals("Cannot update. Contact ID not found: 999", exception.getMessage());
    }
}
