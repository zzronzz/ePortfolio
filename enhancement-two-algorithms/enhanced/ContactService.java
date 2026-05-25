package com.contacts;

import java.util.HashMap;
import java.util.Map;

public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    /**
     * Adds a new contact to the service.
     * The contact must not be null and must have a unique contact ID.
     *
     * @param contact The Contact object to add.
     */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null.");
        }

        String contactId = contact.getContactId();

        if (contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("A contact with this ID already exists: " + contactId);
        }

        contacts.put(contactId, contact);
    }

    /**
     * Deletes a contact from the service by its unique ID.
     *
     * @param contactId The unique ID of the contact to delete.
     */
    public void deleteContact(String contactId) {
        validateContactId(contactId);

        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Cannot delete. Contact ID not found: " + contactId);
        }

        contacts.remove(contactId);
    }

    /**
     * Updates a contact's fields by its unique ID.
     * Null field values are ignored so partial updates are supported.
     *
     * @param contactId The unique ID of the contact to update.
     * @param firstName The new first name.
     * @param lastName  The new last name.
     * @param phone     The new phone number.
     * @param address   The new address.
     */
    public void updateContact(String contactId, String firstName, String lastName, String phone, String address) {
        validateContactId(contactId);

        Contact contact = contacts.get(contactId);

        if (contact == null) {
            throw new IllegalArgumentException("Cannot update. Contact ID not found: " + contactId);
        }

        if (firstName != null) {
            contact.setFirstName(firstName);
        }

        if (lastName != null) {
            contact.setLastName(lastName);
        }

        if (phone != null) {
            contact.setPhone(phone);
        }

        if (address != null) {
            contact.setAddress(address);
        }
    }

    /**
     * Retrieves a contact by its unique ID.
     *
     * @param contactId The unique ID of the contact to retrieve.
     * @return The Contact object if found, otherwise null.
     */
    public Contact getContact(String contactId) {
        validateContactId(contactId);
        return contacts.get(contactId);
    }

    /**
     * Checks whether a contact exists by contact ID.
     *
     * @param contactId The unique ID to check.
     * @return true if the contact exists, false otherwise.
     */
    public boolean contactExists(String contactId) {
        validateContactId(contactId);
        return contacts.containsKey(contactId);
    }

    /**
     * Returns the total number of contacts stored in the HashMap.
     *
     * @return The number of contacts.
     */
    public int getContactCount() {
        return contacts.size();
    }

    /**
     * Validates contact ID input before HashMap operations.
     *
     * @param contactId The contact ID to validate.
     */
    private void validateContactId(String contactId) {
        if (contactId == null || contactId.trim().isEmpty()) {
            throw new IllegalArgumentException("Contact ID cannot be null or blank.");
        }
    }
}