package com.contacts;

public class Contact {
    private final String contactId; // Unique and immutable
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    /**
     * Constructor for the Contact object.
     * Implements the following requirements:
     * - The contact object shall have a required unique contact ID string that cannot be longer than 10 characters.
     *   The contact ID must be non-null, non-empty, and not updatable.
     * - The contact object shall have a required firstName and lastName field that cannot be longer than 10 characters and not null or empty.
     * - The contact object shall have a required phone field that must be exactly 10 digits and not null.
     * - The contact object shall have a required address field that must be no longer than 30 characters and not null or empty.
     */
    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        if (contactId == null || contactId.trim().isEmpty() || contactId.length() > 10) {
            throw new IllegalArgumentException("Invalid contact ID: Must be non-null, non-empty, and no longer than 10 characters.");
        }
        if (firstName == null || firstName.trim().isEmpty() || firstName.length() > 10) {
            throw new IllegalArgumentException("Invalid first name: Must be non-null, non-empty, and no longer than 10 characters.");
        }
        if (lastName == null || lastName.trim().isEmpty() || lastName.length() > 10) {
            throw new IllegalArgumentException("Invalid last name: Must be non-null, non-empty, and no longer than 10 characters.");
        }
        if (phone == null || phone.length() != 10 || !phone.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid phone: Must be exactly 10 digits.");
        }
        if (address == null || address.trim().isEmpty() || address.length() > 30) {
            throw new IllegalArgumentException("Invalid address: Must be non-null, non-empty, and no longer than 30 characters.");
        }

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    // Getter for contactId (immutable)
    public String getContactId() {
        return contactId;
    }

    // Getter and setter for firstName (with validation)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty() || firstName.length() > 10) {
            throw new IllegalArgumentException("Invalid first name: Must be non-null, non-empty, and no longer than 10 characters.");
        }
        this.firstName = firstName;
    }

    // Getter and setter for lastName (with validation)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty() || lastName.length() > 10) {
            throw new IllegalArgumentException("Invalid last name: Must be non-null, non-empty, and no longer than 10 characters.");
        }
        this.lastName = lastName;
    }

    // Getter and setter for phone (with validation)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.length() != 10 || !phone.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid phone: Must be exactly 10 digits.");
        }
        this.phone = phone;
    }

    // Getter and setter for address (with validation)
    public String getAddress() {
    	return address.trim();
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty() || address.length() > 30) {
            throw new IllegalArgumentException("Invalid address: Must be non-null, non-empty, and no longer than 30 characters.");
        }
        this.address = address.trim(); // Trim leading and trailing spaces
    }
}
