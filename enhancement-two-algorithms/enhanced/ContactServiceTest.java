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
    /**
     * Test to ensure that adding a null contact throws a clear exception.
     */
    @Test
    public void rejectNullContact() {
        ContactService service = new ContactService();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
        assertEquals("Contact cannot be null.", exception.getMessage());
    }

    /**
     * Test to ensure that deleting with a blank contact ID throws a clear exception.
     */
    @Test
    public void rejectBlankContactIdForDelete() {
        ContactService service = new ContactService();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.deleteContact("   "));
        assertEquals("Contact ID cannot be null or blank.", exception.getMessage());
    }

    /**
     * Test to ensure that updating with a null contact ID throws a clear exception.
     */
    @Test
    public void rejectNullContactIdForUpdate() {
        ContactService service = new ContactService();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                service.updateContact(null, "Oliver", "White", "9998887777", "555 Elm Circle"));
        assertEquals("Contact ID cannot be null or blank.", exception.getMessage());
    }

    /**
     * Test to verify that partial updates only modify fields with provided values.
     */
    @Test
    public void partialUpdateOnlyChangesProvidedFields() {
        ContactService service = new ContactService();
        Contact contact = new Contact("205", "Noah", "Miller", "1112223333", "456 Cedar St");
        service.addContact(contact);

        service.updateContact("205", null, "Wilson", null, null);

        Contact updatedContact = service.getContact("205");
        assertEquals("Noah", updatedContact.getFirstName());
        assertEquals("Wilson", updatedContact.getLastName());
        assertEquals("1112223333", updatedContact.getPhone());
        assertEquals("456 Cedar St", updatedContact.getAddress());
    }

    /**
     * Test to verify that contactExists returns true for an existing contact.
     */
    @Test
    public void contactExistsReturnsTrueForExistingContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact("206", "Mia", "Garcia", "2223334444", "789 Palm Ave");
        service.addContact(contact);

        assertTrue(service.contactExists("206"));
    }

    /**
     * Test to verify that contactExists returns false for a missing contact.
     */
    @Test
    public void contactExistsReturnsFalseForMissingContact() {
        ContactService service = new ContactService();

        assertFalse(service.contactExists("999"));
    }

    /**
     * Test to verify that getContactCount tracks the number of stored contacts.
     */
    @Test
    public void contactCountUpdatesAfterAddAndDelete() {
        ContactService service = new ContactService();
        Contact contactOne = new Contact("207", "Ethan", "Hall", "3334445555", "123 River Rd");
        Contact contactTwo = new Contact("208", "Ava", "Young", "4445556666", "321 Lake Dr");

        service.addContact(contactOne);
        service.addContact(contactTwo);

        assertEquals(2, service.getContactCount());

        service.deleteContact("207");

        assertEquals(1, service.getContactCount());
    }
}
