package com.contacts;

import java.util.HashMap;
import java.util.Map;

public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    /**
     * Adds a new contact to the service.
     * Implements the requirement:
     * - The contact service shall be able to add contacts with a unique ID.
     * 
     * @param contact The Contact object to add.
     */
    public void addContact(Contact contact) {
        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException("A contact with this ID already exists: " + contact.getContactId());
        }
        contacts.put(contact.getContactId(), contact);
    }

    /**
     * Deletes a contact from the service by its unique ID.
     * Implements the requirement:
     * - The contact service shall be able to delete contacts per contact ID.
     * 
     * @param contactId The unique ID of the contact to delete.
     */
    public void deleteContact(String contactId) {
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Cannot delete. Contact ID not found: " + contactId);
        }
        contacts.remove(contactId);
    }

    /**
     * Updates a contact's fields (firstName, lastName, phone, address) by its unique ID.
     * Implements the requirement:
     * - The contact service shall be able to update contact fields per contact ID.
     * 
     * @param contactId The unique ID of the contact to update.
     * @param firstName The new first name (if updating).
     * @param lastName  The new last name (if updating).
     * @param phone     The new phone number (if updating).
     * @param address   The new address (if updating).
     */
    public void updateContact(String contactId, String firstName, String lastName, String phone, String address) {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Cannot update. Contact ID not found: " + contactId);
        }
        if (firstName != null) contact.setFirstName(firstName);
        if (lastName != null) contact.setLastName(lastName);
        if (phone != null) contact.setPhone(phone);
        if (address != null) contact.setAddress(address);
    }

    /**
     * Retrieves a contact by its unique ID.
     * 
     * @param contactId The unique ID of the contact to retrieve.
     * @return The Contact object if found, otherwise null.
     */
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }
}
